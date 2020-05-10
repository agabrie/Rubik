package renderer;

import java.util.ArrayList;

enum FaceColor {
    WHITE(1,1,1),
    RED(1,0,0),
    GREEN(0,0,1),
    BLUE(0,0,1),
    ORANGE(1,0,1),
    YELLOW(1,1,0);

    Vector3 value;
    FaceColor(float x,float y, float z){
        value = new Vector3(x,y,z);
    }
}

enum FaceOrientation{
    FRONT(new Vector3(-1,1,1),new Vector3(1,1,1),new Vector3(1,-1,1),new Vector3(-1,-1,1)),
    BACK(new Vector3(-1,1,-1),new Vector3(1,1,-1),new Vector3(1,-1,-1),new Vector3(-1,-1,-1)),
    LEFT(new Vector3(-1,1,-1),new Vector3(-1,1,1),new Vector3(-1,-1,1),new Vector3(-1,-1,-1)),
    RIGHT(new Vector3(1,1,-1),new Vector3(1,1,1),new Vector3(1,-1,1),new Vector3(1,-1,-1)),
    TOP(new Vector3(-1,1,-1),new Vector3(1,1,-1),new Vector3(1,1,1),new Vector3(-1,1,1)),
    BOTTOM(new Vector3(-1,-1,-1),new Vector3(1,-1,-1),new Vector3(1,-1,1),new Vector3(-1,-1,1));

//    LEFT(),
//    RIGHT(),
//    BOTTOM(),
//    FRONT(),
//    BACK();
//            gl.glVertex3f(-scale + shift.x, scale + shift.y, scale + shift.z);

    ArrayList<Vector3> points;
    final float scale = 0.245f;
    FaceOrientation(Vector3 p1,Vector3 p2,Vector3 p3,Vector3 p4){
        points = new ArrayList<Vector3>();
        points.add(p1);
        points.add(p2);
        points.add(p3);
        points.add(p4);
    }
}

enum Rotation{
    X(1,0,0),
    Xi(-1,0,0),
    Y(0,1,0),
    Yi(0,-1,0),
    Z(0,0,1),
    Zi(0,0,-1),
    ALL(1,1,1),
    ALLi(-1,-1,-1),
    NONE(0,0,0);
    Vector3 value;
    Rotation(float x,float y,float z){
        value = new Vector3(x, y, z);
    }
}