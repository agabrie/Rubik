package renderer;

import java.util.List;

class Square extends Shape {
    Vector3 color;
    Square(Vector3 color,List<Vector3> vertices){
        this.color = color;
        this.vertices = vertices;
    }
}