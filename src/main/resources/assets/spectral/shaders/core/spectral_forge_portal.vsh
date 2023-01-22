#version 330 core
in vec3 Position;

uniform mat4 ProjMat;

void main()
{
    gl_Position = ProjMat * vec4(Position.x, Position.y, Position.z, 1.0);
}