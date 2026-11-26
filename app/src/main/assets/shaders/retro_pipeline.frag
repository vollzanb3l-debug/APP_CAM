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

// Video-ready frame context uniforms (bound by EffectUniforms; used by grain/time-based stages in M4+)
uniform float uTimeSec;
uniform int uFrameIndex;
uniform float uGrainSeed;
uniform int uQualityLevel; // 0 = preview, 1 = video, 2 = photo

vec2 mapImageUvToSource(vec2 imgUv) {
    return (uTexMatrix * vec4(imgUv, 0.0, 1.0)).xy;
}

vec2 shortSideStepToUv(float radiusShortSide) {
    float aspect = max(uImageAspect, 0.01);
    return (aspect >= 1.0)
        ? vec2(radiusShortSide / aspect, radiusShortSide)
        : vec2(radiusShortSide, radiusShortSide * aspect);
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
    } else {
        // Preview / video quality tier: fast 5-tap diagonal Gaussian kernel (weights sum to 1.0)
        vec3 diag =
            texture(uInputTexture, mapImageUvToSource(imgUv + vec2(stepUv.x, stepUv.y))).rgb +
            texture(uInputTexture, mapImageUvToSource(imgUv + vec2(-stepUv.x, stepUv.y))).rgb +
            texture(uInputTexture, mapImageUvToSource(imgUv + vec2(stepUv.x, -stepUv.y))).rgb +
            texture(uInputTexture, mapImageUvToSource(imgUv + vec2(-stepUv.x, -stepUv.y))).rgb;
        blurred = center.rgb * 0.36 + diag * 0.16;
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
    } else {
        // Preview / video quality tier: fast 8-tap single-ring highlight glow
        glow = sampleHighlightRing(imgUv, stepUv, threshold);
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

    float baseNoise = valueNoise(grainCoord, uGrainSeed) * 2.0 - 1.0;
    float lumaNoise = baseNoise;
    if (uQualityLevel >= 2) {
        float fineNoise = valueNoise(grainCoord * 2.07 + vec2(17.3, 9.1), uGrainSeed * 1.31) * 2.0 - 1.0;
        float fineWeight = clamp(uGrainRoughness, 0.0, 1.0) * 0.35;
        lumaNoise = mix(baseNoise, fineNoise, fineWeight);
    }

    vec3 grainVec = vec3(lumaNoise);
    if (uGrainChroma > 0.0001) {
        float cr = valueNoise(grainCoord + vec2(31.7, 11.3), uGrainSeed + 7.0) * 2.0 - 1.0;
        float cg = valueNoise(grainCoord + vec2(13.1, 47.9), uGrainSeed + 19.0) * 2.0 - 1.0;
        float cb = valueNoise(grainCoord + vec2(59.3, 23.5), uGrainSeed + 37.0) * 2.0 - 1.0;
        grainVec = mix(grainVec, vec3(cr, cg, cb), clamp(uGrainChroma, 0.0, 1.0) * 0.65);
    }

    float luma = dot(c, vec3(0.2126, 0.7152, 0.0722));
    float centeredLuma = 2.0 * luma - 1.0;
    float midtoneWeight = 1.0 - 0.55 * centeredLuma * centeredLuma;
    float strength = clamp(uGrainAmount, 0.0, 1.0) * 0.22 * midtoneWeight;
    return clamp(c + grainVec * strength, 0.0, 1.0);
}

void main() {
    // Stage 1: Geometry (normalized upright image coordinates)
    vec2 imgUv = vImageUv;

    // Stage 2: Optics (softness -> chromatic aberration -> bloom/halation -> vignette)
    vec4 sampleColor = sampleWithSoftness(imgUv);
    vec3 color = applyChromaticAberration(sampleColor.rgb, imgUv);
    color = applyBloomAndHalation(color, imgUv);
    color = color * computeVignetteFactor(imgUv);

    // Stage 3 & 4: Sensor/Camera + Film color grade & fade
    color = applyColorGrade(color);

    // Stage 4b: Film grain & sensor noise (short-side relative, video-ready seed)
    color = applyFilmGrain(color, imgUv);

    fragColor = vec4(color, sampleColor.a);
}
