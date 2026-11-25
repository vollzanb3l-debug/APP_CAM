precision highp float;

in vec2 vTexCoord;
out vec4 fragColor;

#ifdef USE_EXTERNAL_OES
uniform samplerExternalOES uInputTexture;
#else
uniform sampler2D uInputTexture;
#endif

// M1 passthrough; M2-M4 extend this single shared shader for preview and capture.
vec4 processPixel(vec2 uv) {
    return texture(uInputTexture, uv);
}

void main() {
    fragColor = processPixel(vTexCoord);
}
