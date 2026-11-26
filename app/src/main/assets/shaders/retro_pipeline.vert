#version 300 es

layout(location = 0) in vec2 aPosition;
layout(location = 1) in vec2 aTexCoord;

uniform vec4 uImageUvRect;

out vec2 vImageUv;

void main() {
    gl_Position = vec4(aPosition, 0.0, 1.0);
    vImageUv = uImageUvRect.xy + aTexCoord * uImageUvRect.zw;
}
