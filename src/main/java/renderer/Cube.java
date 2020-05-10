package renderer;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

class Cube {
    final float scale = 0.245f;
    float rquad = 0.0f;
    Vector3 rotation = new Vector3(0,0,0);
    GL2 gl = null;
    Square front = new Square(FaceColor.WHITE.value,FaceOrientation.FRONT.points);
    Square top = new Square(FaceColor.ORANGE.value,FaceOrientation.TOP.points);
    Square left = new Square(FaceColor.GREEN.value,FaceOrientation.LEFT.points);
    Square right = new Square(FaceColor.BLUE.value,FaceOrientation.RIGHT.points);
    Square back = new Square(FaceColor.YELLOW.value,FaceOrientation.BACK.points);
    Square bottom = new Square(FaceColor.RED.value,FaceOrientation.BOTTOM.points);
    Vector3 shift;
    Vector3 rotate;
    static float rotSpeed = 1f;
    Vector3 rotlimit = new Vector3(0f,0f,0f);
    boolean rotating = false;
    public Cube() {
    }
    public Cube(Vector3 shift) {
        this.shift = shift;
    }
    void draw(GLAutoDrawable drawable){
        gl = drawable.getGL().getGL2();
        gl.glLoadIdentity();
        gl.glTranslatef(0f, 0f, -5.f);
//        gl.glRotatef(rquad,rotate.x,rotate.y,rotate.z);
//        if(rotation.x == 90)
//            rotate.z = rotate.y;

        if(rotate.x != 0)
            rotateX();
        else if (rotate.y != 0)
            rotateY();
        else if (rotate.z !=0)
            rotateZ();
//            rotateNone();
        gl.glPushMatrix();
        gl.glBegin(GL2.GL_QUADS);
        displaySquare(front);
        displaySquare(left);
        displaySquare(right);
        displaySquare(top);
        displaySquare(back);
        displaySquare(bottom);

        gl.glEnd();
        gl.glPopMatrix();
        System.out.printf("rotation:%s rotlimit => rotlimit:%s %n",rotation,rotlimit);
//        boolean clockwise = (rotate.x+ rotate.y + rotate.z < 0);
//        System.out.println("clockwise : "+clockwise);
//        if((clockwise && rquad <= rotlimit) || (!clockwise && rquad >= rotlimit) ){
//            stopRotation();
//        }
//        if(rotation.x <= rotlimit.x)
//            stopRotation();
//        else{
//            System.out.println((rotate.x + rotate.y + rotate.z)+" is delta");
//            rquad += (rotate.x + rotate.y + rotate.z)*rotSpeed;
////            rquad += (rotate.x + rotate.y + rotate.z)*rotSpeed;
////            rquad = rquad %360;
//        }
//        System.out.printf("rotation : %s === rotlimit : [%.1f]%n",rotation,rotlimit);
    }
    public void rotateNone(){
        if(rotate.x != 0){
//            gl.glRotatef(rotation.x,rotate.x,0,0);
            rotating = false;
//            if((rotate.x > 0)){
//                if(rotation.x > rotlimit) {
//                    rotating = false;
//                    return;
//                }
//                rotation.x += rotSpeed;
//            }else{
//                if(rotation.x < rotlimit) {
//                    rotating = false;
//                    return;
//                }
//                rotation.x -= rotSpeed;
//            }
        }
    }
    public void rotateX(){
        if(rotate.x != 0) {
            gl.glRotatef(rotation.x, 1, 0, 0);
            System.out.println(rotate.x);
            if ((rotate.x > 0)) {
                if (rotation.x >= rotlimit.x) {
                    stopRotation();
//                    rotating = false;
//                    return;
                }else
                rotation.x += rotSpeed;
            } else if(rotate.x < 0){
                if (rotation.x <= rotlimit.x) {
//                    rotating = false;
//                    return;
                    stopRotation();
                }else
                rotation.x -= rotSpeed;
            }
        }
    }

    public void rotateY(){
        if(rotate.y != 0) {
            gl.glRotatef(rotation.y, 0, 1, 0);
            System.out.println(rotate.y);
            if ((rotate.y > 0)) {
                if (rotation.y >= rotlimit.y) {
                    stopRotation();
//                    rotating = false;
//                    return;
                }else
                    rotation.y += rotSpeed;
            } else if(rotate.y < 0){
                if (rotation.y <= rotlimit.y) {
//                    rotating = false;
//                    return;
                    stopRotation();
                }else
                    rotation.y -= rotSpeed;
            }
        }
    }

    public void rotateZ(){
        if(rotate.z != 0) {
            gl.glRotatef(rotation.z, 0, 0, 1);
            System.out.println(rotate.z);
            if ((rotate.z > 0)) {
                if (rotation.z >= rotlimit.z) {
                    stopRotation();
//                    rotating = false;
//                    return;
                }else
                    rotation.z += rotSpeed;
            } else if(rotate.z < 0){
                if (rotation.z <= rotlimit.z) {
//                    rotating = false;
//                    return;
                    stopRotation();
                }else
                    rotation.z -= rotSpeed;
            }
        }
    }

    public void displaySquare(Square face) {
//        gl.glRotatef(rquad,rotate.x,rotate.y,rotate.z);
//        gl.glPushMatrix();
        displayColor(face.color);
        for(Vector3 point:face.vertices){
            displayVertex(point);
        }
//        gl.glPopMatrix();
    }

    void displayColor(Vector3 color){
        gl.glColor3f(color.x,color.y,color.z);
    }
    void displayVertex(Vector3 vertex){
        gl.glVertex3f(scale*vertex.x+shift.x,scale*vertex.y+shift.y,scale*vertex.z+shift.z);
    }
    void setRotation(Vector3 r,int numRotations){
        rotate = r;
//        rotation.x=0;
//        rotation.y=0;
//        rotation.z=0;
//        rotlimit.x = 0;
//        rotlimit.y = 0;
//        rotlimit.z = 0;

        if(rotate == Rotation.NONE.value) {
            rotating = false;
        }
        else {
            rotating = true;
        }

//        if(rotlimit < -360f)
//        rquad = -(-rquad%360);



//        System.out.printf("rolimit calc : rquad[%d], num rotates[%d], rotlimit++ [%f]%n",Math.round(rquad),numRotations,(-90f*numRotations));
//        if(rotation.x == 90){
//            System.out.println("rotation : "+rotation);
//            rotate.x = rotate.z;
//            rotate.z = rotate.y;
//            rotate.y = rotate.x;
//            rotate.x = 0;
//        }

        if(rotate.x == 1)
            rotlimit.x -= (-90f*numRotations);
        if(rotate.x == -1)
            rotlimit.x += (-90f*numRotations);

        if(rotate.y == 1)
            rotlimit.y -= (-90f*numRotations);
        if(rotate.y == -1)
            rotlimit.y += (-90f*numRotations);

        if(rotate.z == 1)
            rotlimit.z -= (-90f*numRotations);
        if(rotate.z == -1)
            rotlimit.z += (-90f*numRotations);

//        else
//            rotlimit += (90f*numRotations);
        System.out.println("rotlimit : "+rotlimit+" -> rotate :"+rotate);
//            if (rquad > -90f)
//                rotlimit = -90f;
//            else if (rquad > -180f)
//                rotlimit = -180f;
//            else if (rquad > -270f)
//                rotlimit = -270f;
//            else
//                rotlimit = -360f;
//            if (rotlimit > -90f)
//                rotlimit = -90f;
//            if (rotlimit < -360f){
//                rotlimit = -(-rotlimit%360);
//                rquad = -(-rquad%360);
//            }
    }
    void stopRotation(){
        System.out.println("STOP!");
//        if(rotate.x < -90f && rotate.y > -180f)
//            rotate.x = -90f;
//        rotate.x = 0;
//        rotate = Rotation.NONE.value;
//        setRotation(Rotation.NONE.value,0);
//        rquad=-(Math.round(-rquad)%360);
//        rquad = rotlimit;
//        if(rquad -90f > -360f)
//        rotlimit = rquad + (-90f);
//        rotlimit = rquad +
//        if(rquad > -90f)
//            rotlimit = -90f;
//        else if(rquad > -180f)
//            rotlimit = -180f;
//        else if(rquad > -270f)
//            rotlimit = -270f;
//        else
//            rotlimit = -360f;
        rotating = false;
    }
    boolean isRotating(){
//        System.out.println("rotating is "+rotating+" : "+rotate);
        return rotating;
    }
}