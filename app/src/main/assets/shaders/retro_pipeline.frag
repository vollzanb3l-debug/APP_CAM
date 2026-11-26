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

// Video-ready frame context uniforms (bound by EffectUniforms; used by grain/time-based stages in M4+)
uniform float uTimeSec;
uniform int uFrameIndex;
uniform float uGrainSeed;
uniform int uQualityLevel; // 0 = preview, 1 = video, 2 = photo

vec2 mapImageUvToSource(vec2 imgUv) {
    return (uTexMatrix * vec4(imgUv, 0.0, 1.0)).xy;
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

void main() {
    // Stage 1: Geometry (normalized upright image coordinates)
    vec2 imgUv = vImageUv;

    // Stage 2: Optics (vignette in normalized coordinates)
    vec4 sampleColor = texture(uInputTexture, mapImageUvToSource(imgUv));
    vec3 color = sampleColor.rgb * computeVignetteFactor(imgUv);

    // Stage 3 & 4: Sensor/Camera + Film color grade & fade
    color = applyColorGrade(color);

    fragColor = vec4(color, sampleColor.a);
}
