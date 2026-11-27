precision highp float;

in vec2 vImageUv;
out vec4 fragColor;

#ifdef USE_EXTERNAL_OES
uniform samplerExternalOES uInputTexture;
#else
uniform sampler2D uInputTexture;
#endif

uniform mat4 uTexMatrix;
uniform float uImageAspect;

// Geometry (Stage 1)
uniform float uDistortionK1;

// Optics (Stage 2)
uniform float uSoftness;
uniform float uChromaticAberration;
uniform float uBloomIntensity;
uniform float uBloomThreshold;
uniform float uBloomRadius;
uniform float uHalationIntensity;
uniform vec3 uHalationColor;
uniform float uVignetteStrength;
uniform float uVignetteRadius;

// Color grade & tone (Stages 3 & 4)
uniform float uTemp;
uniform float uTint;
uniform float uContrast;
uniform float uSaturation;
uniform float uHighlightRolloff;
uniform float uShadowLift;
uniform float uBlackLift;
uniform vec3 uHighlightTint;
uniform vec3 uShadowTint;
uniform float uTintStrength;

// Film grain & sensor noise (Stage 4b)
uniform float uGrainAmount;
uniform float uGrainChroma;
uniform float uGrainSize;
uniform float uGrainRoughness;

// Overlay (Stage 5)
uniform float uLightLeakIntensity;
uniform float uLightLeakSeed;
uniform float uDateStampIntensity;
uniform vec3 uDateStampColor;
uniform int uDateYmd;

// Video-ready frame context uniforms (bound by EffectUniforms; used by grain/time-based stages in M4+)
uniform float uTimeSec;
uniform int uFrameIndex;
uniform float uGrainSeed;
uniform int uQualityLevel; // -1 = preview_low (auto-downgraded), 0 = preview, 1 = video, 2 = photo

vec2 mapImageUvToSource(vec2 imgUv) {
    return (uTexMatrix * vec4(imgUv, 0.0, 1.0)).xy;
}

vec2 shortSideStepToUv(float radiusShortSide) {
    float aspect = max(uImageAspect, 0.01);
    return (aspect >= 1.0)
        ? vec2(radiusShortSide / aspect, radiusShortSide)
        : vec2(radiusShortSide, radiusShortSide * aspect);
}

vec2 applyLensDistortion(vec2 imgUv) {
    float k1 = clamp(uDistortionK1, -0.5, 0.5);
    if (abs(k1) <= 0.0001) {
        return imgUv;
    }
    float aspect = max(uImageAspect, 0.01);
    vec2 aspectScale = (aspect >= 1.0)
        ? vec2(aspect, 1.0)
        : vec2(1.0, 1.0 / aspect);
    vec2 centered = imgUv - vec2(0.5);
    vec2 metric = centered * aspectScale;
    float maxR2 = dot(vec2(0.5) * aspectScale, vec2(0.5) * aspectScale);
    float normR2 = dot(metric, metric) / max(maxR2, 0.0001);

    float factor = (k1 < 0.0)
        ? ((1.0 + (-k1) * 0.65 * normR2) / (1.0 + (-k1) * 0.65))
        : (1.0 - k1 * 0.45 * normR2);
    return clamp(vec2(0.5) + centered * factor, 0.0, 1.0);
}

vec4 sampleWithSoftness(vec2 imgUv) {
    vec4 center = texture(uInputTexture, mapImageUvToSource(imgUv));
    if (uSoftness <= 0.0001) {
        return center;
    }
    float s = clamp(uSoftness, 0.0, 1.0);
    vec2 stepUv = shortSideStepToUv(s * 0.0045);

    vec3 blurred;
    if (uQualityLevel >= 2) {
        // Photo quality tier: 9-tap two-ring Gaussian kernel (weights sum to 1.0)
        vec2 innerStep = stepUv * 0.65;
        vec2 outerStep = stepUv * 1.15;
        vec3 axial =
            texture(uInputTexture, mapImageUvToSource(imgUv + vec2(innerStep.x, 0.0))).rgb +
            texture(uInputTexture, mapImageUvToSource(imgUv - vec2(innerStep.x, 0.0))).rgb +
            texture(uInputTexture, mapImageUvToSource(imgUv + vec2(0.0, innerStep.y))).rgb +
            texture(uInputTexture, mapImageUvToSource(imgUv - vec2(0.0, innerStep.y))).rgb;
        vec3 diag =
            texture(uInputTexture, mapImageUvToSource(imgUv + vec2(outerStep.x, outerStep.y))).rgb +
            texture(uInputTexture, mapImageUvToSource(imgUv + vec2(-outerStep.x, outerStep.y))).rgb +
            texture(uInputTexture, mapImageUvToSource(imgUv + vec2(outerStep.x, -outerStep.y))).rgb +
            texture(uInputTexture, mapImageUvToSource(imgUv + vec2(-outerStep.x, -outerStep.y))).rgb;
        blurred = center.rgb * 0.24 + axial * 0.12 + diag * 0.07;
    } else if (uQualityLevel >= 0) {
        // Preview / video quality tier: fast 5-tap diagonal Gaussian kernel (weights sum to 1.0)
        vec3 diag =
            texture(uInputTexture, mapImageUvToSource(imgUv + vec2(stepUv.x, stepUv.y))).rgb +
            texture(uInputTexture, mapImageUvToSource(imgUv + vec2(-stepUv.x, stepUv.y))).rgb +
            texture(uInputTexture, mapImageUvToSource(imgUv + vec2(stepUv.x, -stepUv.y))).rgb +
            texture(uInputTexture, mapImageUvToSource(imgUv + vec2(-stepUv.x, -stepUv.y))).rgb;
        blurred = center.rgb * 0.36 + diag * 0.16;
    } else {
        // Auto-downgraded preview tier (PREVIEW_LOW): 3-tap opposing diagonal blur (weights sum to 1.0)
        vec3 diagPair =
            texture(uInputTexture, mapImageUvToSource(imgUv + vec2(stepUv.x, stepUv.y))).rgb +
            texture(uInputTexture, mapImageUvToSource(imgUv - vec2(stepUv.x, stepUv.y))).rgb;
        blurred = center.rgb * 0.50 + diagPair * 0.25;
    }
    return vec4(mix(center.rgb, blurred, s * 0.78), center.a);
}

vec2 computeChromaticAberrationOffset(vec2 imgUv) {
    float amount = clamp(uChromaticAberration, 0.0, 1.0);
    if (amount <= 0.0001) {
        return vec2(0.0);
    }
    vec2 centered = imgUv - vec2(0.5);
    float r2 = dot(centered, centered);
    float maxShift = amount * 0.008;
    vec2 aspectScale = shortSideStepToUv(1.0);
    return centered * (0.4 + 1.6 * r2) * maxShift * aspectScale * 2.0;
}

vec3 applyChromaticAberration(vec3 baseColor, vec2 imgUv) {
    if (uChromaticAberration <= 0.0001) {
        return baseColor;
    }
    vec2 offset = computeChromaticAberrationOffset(imgUv);
    float r = texture(uInputTexture, mapImageUvToSource(clamp(imgUv + offset, 0.0, 1.0))).r;
    float b = texture(uInputTexture, mapImageUvToSource(clamp(imgUv - offset, 0.0, 1.0))).b;
    return vec3(r, baseColor.g, b);
}

vec3 extractHighlight(vec3 c, float threshold) {
    float luma = dot(c, vec3(0.2126, 0.7152, 0.0722));
    float knee = 0.12;
    float weight = smoothstep(threshold - knee, threshold + knee, luma);
    float excess = max(luma - threshold * 0.85, 0.0);
    return c * (weight * excess);
}

vec3 sampleHighlightRing(vec2 imgUv, vec2 stepUv, float threshold) {
    vec2 diag = stepUv * 0.70710678;
    vec3 sum =
        extractHighlight(texture(uInputTexture, mapImageUvToSource(imgUv + vec2(stepUv.x, 0.0))).rgb, threshold) +
        extractHighlight(texture(uInputTexture, mapImageUvToSource(imgUv - vec2(stepUv.x, 0.0))).rgb, threshold) +
        extractHighlight(texture(uInputTexture, mapImageUvToSource(imgUv + vec2(0.0, stepUv.y))).rgb, threshold) +
        extractHighlight(texture(uInputTexture, mapImageUvToSource(imgUv - vec2(0.0, stepUv.y))).rgb, threshold) +
        extractHighlight(texture(uInputTexture, mapImageUvToSource(imgUv + vec2(diag.x, diag.y))).rgb, threshold) +
        extractHighlight(texture(uInputTexture, mapImageUvToSource(imgUv + vec2(-diag.x, diag.y))).rgb, threshold) +
        extractHighlight(texture(uInputTexture, mapImageUvToSource(imgUv + vec2(diag.x, -diag.y))).rgb, threshold) +
        extractHighlight(texture(uInputTexture, mapImageUvToSource(imgUv + vec2(-diag.x, -diag.y))).rgb, threshold);
    return sum * 0.125;
}

vec3 sampleHighlightRingFast(vec2 imgUv, vec2 stepUv, float threshold) {
    vec2 diag = stepUv * 0.70710678;
    vec3 sum =
        extractHighlight(texture(uInputTexture, mapImageUvToSource(imgUv + vec2(diag.x, diag.y))).rgb, threshold) +
        extractHighlight(texture(uInputTexture, mapImageUvToSource(imgUv + vec2(-diag.x, diag.y))).rgb, threshold) +
        extractHighlight(texture(uInputTexture, mapImageUvToSource(imgUv + vec2(diag.x, -diag.y))).rgb, threshold) +
        extractHighlight(texture(uInputTexture, mapImageUvToSource(imgUv + vec2(-diag.x, -diag.y))).rgb, threshold);
    return sum * 0.25;
}

vec3 applyBloomAndHalation(vec3 baseColor, vec2 imgUv) {
    float bloomAmt = clamp(uBloomIntensity, 0.0, 1.0);
    float halationAmt = clamp(uHalationIntensity, 0.0, 1.0);
    if (bloomAmt <= 0.0001 && halationAmt <= 0.0001) {
        return baseColor;
    }

    float threshold = clamp(uBloomThreshold, 0.1, 0.99);
    float effRadius = max(uBloomRadius, 0.006);
    vec2 stepUv = shortSideStepToUv(effRadius);

    vec3 glow;
    if (uQualityLevel >= 2) {
        // Photo quality tier: 16-tap dual-ring highlight glow
        vec3 innerGlow = sampleHighlightRing(imgUv, stepUv * 0.55, threshold);
        vec3 outerGlow = sampleHighlightRing(imgUv, stepUv * 1.25, threshold);
        glow = innerGlow * 0.55 + outerGlow * 0.45;
    } else if (uQualityLevel >= 0) {
        // Preview / video quality tier: fast 8-tap single-ring highlight glow
        glow = sampleHighlightRing(imgUv, stepUv, threshold);
    } else {
        // Auto-downgraded preview tier (PREVIEW_LOW): 4-tap diagonal highlight ring
        glow = sampleHighlightRingFast(imgUv, stepUv, threshold);
    }

    vec3 bloomAdd = glow * bloomAmt * 1.15;
    float glowLuma = dot(glow, vec3(0.2126, 0.7152, 0.0722));
    vec3 halationAdd = uHalationColor * glowLuma * halationAmt * 1.35;
    vec3 combinedGlow = bloomAdd + halationAdd;
    return clamp(baseColor + combinedGlow * (1.0 - baseColor * 0.35), 0.0, 1.0);
}

float computeVignetteFactor(vec2 imgUv) {
    if (uVignetteStrength <= 0.0001) {
        return 1.0;
    }
    float aspect = max(uImageAspect, 0.01);
    vec2 aspectScale = (aspect >= 1.0)
        ? vec2(aspect, 1.0)
        : vec2(1.0, 1.0 / aspect);
    vec2 centered = (imgUv - vec2(0.5)) * aspectScale;
    float cornerDist = length(vec2(0.5) * aspectScale);
    float normDist = length(centered) / max(cornerDist, 0.0001);

    float inner = clamp(uVignetteRadius * 0.55, 0.05, 0.95);
    float outer = max(inner + 0.35, uVignetteRadius);
    float falloff = smoothstep(inner, outer, normDist);
    return 1.0 - clamp(uVignetteStrength, 0.0, 1.0) * falloff * 0.85;
}

vec3 applyColorGrade(vec3 c) {
    // 1. White balance gain
    vec3 wbGain = max(vec3(
        1.0 + uTemp * 0.25 + uTint * 0.10,
        1.0 - uTint * 0.15,
        1.0 - uTemp * 0.25 + uTint * 0.10
    ), vec3(0.1));
    c = clamp(c * wbGain, 0.0, 1.0);

    // 2. Shadow lift
    vec3 inv = 1.0 - c;
    c = clamp(c + uShadowLift * inv * inv, 0.0, 1.0);

    // 3. Smooth highlight rolloff above 0.5
    vec3 over = max(c - 0.5, 0.0);
    c = clamp(c - uHighlightRolloff * 0.18 * over * over * 4.0, 0.0, 1.0);

    // 4. Contrast around mid-gray 0.5
    c = clamp((c - 0.5) * uContrast + 0.5, 0.0, 1.0);

    // 5. Saturation (Rec.709 luma)
    float satLuma = dot(c, vec3(0.2126, 0.7152, 0.0722));
    c = clamp(mix(vec3(satLuma), c, uSaturation), 0.0, 1.0);

    // 6. Film split-tone tint
    if (uTintStrength > 0.0001) {
        float toneLuma = dot(c, vec3(0.2126, 0.7152, 0.0722));
        vec3 targetTint = mix(uShadowTint, uHighlightTint, toneLuma);
        c = clamp(mix(c, targetTint, uTintStrength), 0.0, 1.0);
    }

    // 7. Film black lift / fade
    c = clamp(c * (1.0 - uBlackLift) + uBlackLift, 0.0, 1.0);
    return c;
}

float hash12(vec2 p) {
    vec3 p3 = fract(vec3(p.xyx) * 0.1031);
    p3 += dot(p3, p3.yzx + 33.33);
    return fract((p3.x + p3.y) * p3.z);
}

float valueNoise(vec2 coord, float seedOffset) {
    vec2 i = floor(coord);
    vec2 f = fract(coord);
    vec2 u = f * f * (3.0 - 2.0 * f);
    float a = hash12(i + vec2(seedOffset, seedOffset * 0.37));
    float b = hash12(i + vec2(1.0 + seedOffset, seedOffset * 0.37));
    float c = hash12(i + vec2(seedOffset, 1.0 + seedOffset * 0.37));
    float d = hash12(i + vec2(1.0 + seedOffset, 1.0 + seedOffset * 0.37));
    return mix(mix(a, b, u.x), mix(c, d, u.x), u.y);
}

vec3 applyFilmGrain(vec3 c, vec2 imgUv) {
    if (uGrainAmount <= 0.0001) {
        return c;
    }
    float aspect = max(uImageAspect, 0.01);
    vec2 shortSidePos = (aspect >= 1.0)
        ? vec2(imgUv.x * aspect, imgUv.y)
        : vec2(imgUv.x, imgUv.y / aspect);
    float cellSize = max(uGrainSize, 0.0005);
    vec2 grainCoord = shortSidePos / cellSize;

    vec3 grainVec;
    if (uQualityLevel < 0) {
        // Auto-downgraded preview tier (PREVIEW_LOW): fast cell hash preserves grain size & mood with >80% fewer hash ops
        vec2 cell = floor(grainCoord);
        float lumaFast = hash12(cell + vec2(uGrainSeed, uGrainSeed * 0.37)) * 2.0 - 1.0;
        grainVec = vec3(lumaFast);
        if (uGrainChroma > 0.0001) {
            float cr = hash12(cell + vec2(31.7 + uGrainSeed, 11.3)) * 2.0 - 1.0;
            float cb = hash12(cell + vec2(59.3 + uGrainSeed, 23.5)) * 2.0 - 1.0;
            float cg = -0.5 * (cr + cb);
            grainVec = mix(grainVec, vec3(cr, cg, cb), clamp(uGrainChroma, 0.0, 1.0) * 0.65);
        }
    } else {
        float baseNoise = valueNoise(grainCoord, uGrainSeed) * 2.0 - 1.0;
        float lumaNoise = baseNoise;
        if (uQualityLevel >= 2) {
            float fineNoise = valueNoise(grainCoord * 2.07 + vec2(17.3, 9.1), uGrainSeed * 1.31) * 2.0 - 1.0;
            float fineWeight = clamp(uGrainRoughness, 0.0, 1.0) * 0.35;
            lumaNoise = mix(baseNoise, fineNoise, fineWeight);
        }

        grainVec = vec3(lumaNoise);
        if (uGrainChroma > 0.0001) {
            float cr = valueNoise(grainCoord + vec2(31.7, 11.3), uGrainSeed + 7.0) * 2.0 - 1.0;
            float cg = valueNoise(grainCoord + vec2(13.1, 47.9), uGrainSeed + 19.0) * 2.0 - 1.0;
            float cb = valueNoise(grainCoord + vec2(59.3, 23.5), uGrainSeed + 37.0) * 2.0 - 1.0;
            grainVec = mix(grainVec, vec3(cr, cg, cb), clamp(uGrainChroma, 0.0, 1.0) * 0.65);
        }
    }

    float luma = dot(c, vec3(0.2126, 0.7152, 0.0722));
    float centeredLuma = 2.0 * luma - 1.0;
    float midtoneWeight = 1.0 - 0.55 * centeredLuma * centeredLuma;
    float strength = clamp(uGrainAmount, 0.0, 1.0) * 0.22 * midtoneWeight;
    return clamp(c + grainVec * strength, 0.0, 1.0);
}

vec3 applyLightLeak(vec3 baseColor, vec2 imgUv) {
    float strength = clamp(uLightLeakIntensity, 0.0, 1.0);
    if (strength <= 0.0001) {
        return baseColor;
    }
    float seed = fract(abs(uLightLeakSeed));
    float edgeU = (seed < 0.5) ? (seed * 0.40) : (1.0 - (seed - 0.5) * 0.40);
    float centerV = 0.25 + fract(seed * 2.37) * 0.55;
    float wave = sin((imgUv.y - centerV) * 5.0 + seed * 6.2831853) * 0.035;

    float dx = (imgUv.x - edgeU) + wave;
    float dy = (imgUv.y - centerV) * 0.55;
    float primaryBand = exp(-(dx * dx * 18.0 + dy * dy * 4.5));

    float dx2 = imgUv.x - edgeU;
    float dy2 = imgUv.y - (1.0 - centerV);
    float secondaryFlare = exp(-(dx2 * dx2 * 6.0 + dy2 * dy2 * 8.0)) * 0.45;

    float mask = clamp(primaryBand + secondaryFlare, 0.0, 1.0);
    vec3 coreColor = vec3(1.0, 0.46, 0.14);
    vec3 edgeColor = vec3(0.88, 0.18, 0.12);
    vec3 leakColor = mix(edgeColor, coreColor, clamp(primaryBand, 0.0, 1.0));
    vec3 leakRgb = leakColor * (mask * strength * 0.85);
    return clamp(vec3(1.0) - (vec3(1.0) - baseColor) * (vec3(1.0) - leakRgb), 0.0, 1.0);
}

int sevenSegMask(int d) {
    if (d == 0) return 63;
    if (d == 1) return 6;
    if (d == 2) return 91;
    if (d == 3) return 79;
    if (d == 4) return 102;
    if (d == 5) return 109;
    if (d == 6) return 125;
    if (d == 7) return 7;
    if (d == 8) return 127;
    if (d == 9) return 111;
    return 0;
}

float sdSegment(vec2 p, vec2 a, vec2 b) {
    vec2 pa = p - a;
    vec2 ba = b - a;
    float h = clamp(dot(pa, ba) / max(dot(ba, ba), 0.000001), 0.0, 1.0);
    return length(pa - ba * h);
}

float sdSevenSegDigit(vec2 p, int digit) {
    int mask = sevenSegMask(digit);
    float d = 100000.0;
    if ((mask & 1) != 0) d = min(d, sdSegment(p, vec2(0.24, 0.12), vec2(0.76, 0.12)));
    if ((mask & 2) != 0) d = min(d, sdSegment(p, vec2(0.78, 0.16), vec2(0.78, 0.46)));
    if ((mask & 4) != 0) d = min(d, sdSegment(p, vec2(0.78, 0.54), vec2(0.78, 0.84)));
    if ((mask & 8) != 0) d = min(d, sdSegment(p, vec2(0.24, 0.88), vec2(0.76, 0.88)));
    if ((mask & 16) != 0) d = min(d, sdSegment(p, vec2(0.22, 0.54), vec2(0.22, 0.84)));
    if ((mask & 32) != 0) d = min(d, sdSegment(p, vec2(0.22, 0.16), vec2(0.22, 0.46)));
    if ((mask & 64) != 0) d = min(d, sdSegment(p, vec2(0.25, 0.50), vec2(0.75, 0.50)));
    return d;
}

vec3 applyDateStamp(vec3 baseColor, vec2 imgUv) {
    float strength = clamp(uDateStampIntensity, 0.0, 1.0);
    if (strength <= 0.0001) {
        return baseColor;
    }

    float aspect = max(uImageAspect, 0.01);
    float widthShort = (aspect >= 1.0) ? aspect : 1.0;
    float heightShort = (aspect >= 1.0) ? 1.0 : (1.0 / aspect);

    vec2 pos = vec2(imgUv.x * widthShort, imgUv.y * heightShort);
    float boxH = 0.036;
    float slotW = boxH * 0.62;
    float boxW = slotW * 9.0;
    float margin = 0.045;

    vec2 origin = vec2(widthShort - margin - boxW, heightShort - margin - boxH);
    vec2 local = pos - origin;
    float pad = boxH * 0.35;
    if (local.x < -pad || local.x > boxW + pad || local.y < -pad || local.y > boxH + pad) {
        return baseColor;
    }

    int slotIndex = clamp(int(floor(local.x / slotW)), 0, 8);
    vec2 cell = vec2((local.x - float(slotIndex) * slotW) / slotW, local.y / boxH);

    int safeYmd = clamp(uDateYmd, 0, 999999);
    float dist = 100000.0;
    if (slotIndex == 0) {
        dist = sdSegment(cell, vec2(0.62, 0.12), vec2(0.52, 0.34));
    } else if (slotIndex == 1) {
        dist = sdSevenSegDigit(cell, (safeYmd / 100000) % 10);
    } else if (slotIndex == 2) {
        dist = sdSevenSegDigit(cell, (safeYmd / 10000) % 10);
    } else if (slotIndex == 3 || slotIndex == 6) {
        dist = length(cell - vec2(0.50, 0.86)) - 0.07;
    } else if (slotIndex == 4) {
        dist = sdSevenSegDigit(cell, (safeYmd / 1000) % 10);
    } else if (slotIndex == 5) {
        dist = sdSevenSegDigit(cell, (safeYmd / 100) % 10);
    } else if (slotIndex == 7) {
        dist = sdSevenSegDigit(cell, (safeYmd / 10) % 10);
    } else {
        dist = sdSevenSegDigit(cell, safeYmd % 10);
    }

    float thickness = 0.068;
    float core = clamp(1.0 - smoothstep(thickness * 0.65, thickness * 1.25, dist), 0.0, 1.0);
    float glow = clamp(exp(-max(dist, 0.0) * 7.5) * 0.85, 0.0, 1.0);

    vec3 darkened = baseColor * (1.0 - glow * 0.25 * strength);
    vec3 coreBoost = min(uDateStampColor * vec3(1.15, 1.12, 1.05), vec3(1.0));
    vec3 withGlow = darkened + uDateStampColor * (glow * 0.55 * strength);
    return clamp(mix(withGlow, coreBoost, core * strength), 0.0, 1.0);
}

void main() {
    // Stage 1: Geometry (normalized upright image coordinates + lens distortion)
    vec2 imgUv = applyLensDistortion(vImageUv);

    // Stage 2: Optics (softness -> chromatic aberration -> bloom/halation -> vignette)
    vec4 sampleColor = sampleWithSoftness(imgUv);
    vec3 color = applyChromaticAberration(sampleColor.rgb, imgUv);
    color = applyBloomAndHalation(color, imgUv);
    color = color * computeVignetteFactor(vImageUv);

    // Stage 3 & 4: Sensor/Camera + Film color grade & fade
    color = applyColorGrade(color);

    // Stage 4b: Film grain & sensor noise (short-side relative, video-ready seed)
    color = applyFilmGrain(color, vImageUv);

    // Stage 5: Overlay (optional light leak + original procedural digital date stamp)
    color = applyLightLeak(color, vImageUv);
    color = applyDateStamp(color, vImageUv);

    fragColor = vec4(color, sampleColor.a);
}
