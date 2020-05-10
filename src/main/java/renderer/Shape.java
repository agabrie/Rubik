package renderer;

import java.util.ArrayList;
import java.util.List;

class Shape {
    List<Vector3> vertices = new ArrayList<Vector3>();

    public Shape(List<Vector3> vertices) {
        for (Vector3 coord : vertices) {
            this.vertices.add(coord);
        }
    }

    public Shape() {
    }
}