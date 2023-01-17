#version 330 core
in vec3 Position;

uniform mat4 ProjMat;

void main()
{
    //gl_Position = vec4(Position, 1.0); // renders in screen space
    gl_Position = ProjMat * vec4(Position.x, Position.y, Position.z, 1.0); //would hopefully render in 3d space
}