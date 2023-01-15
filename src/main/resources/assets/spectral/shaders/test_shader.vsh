#version 330 core
in vec3 Pos;

void main()
{
    gl_Position = vec4(Pos.x, Pos.y, Pos.z, 1.0);
}