precision highp float;

in vec2 vTexCoord;
out vec4 fragColor;

#ifdef USE_EXTERNAL_OES
uniform samplerExternalOES uInputTexture;
#else
uniform sampler2D uInputTexture;
#endif

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
    vec4 sampleColor = texture(uInputTexture, vTexCoord);
    fragColor = vec4(applyColorGrade(sampleColor.rgb), sampleColor.a);
}
