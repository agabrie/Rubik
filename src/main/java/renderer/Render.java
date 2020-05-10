package renderer;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

// import rubik.*;


class Vector3 {
    float x;
    float y;
    float z;

    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    @Override
    public String toString(){
        return String.format("( %.1f, %.1f, %.1f)",this.x,this.y,this.z);
    }

    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /*
         * Check if o is an instance of Complex or not "null instanceof [type]" also
         * returns false
         */
        if (!(o instanceof Vector3)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Vector3 v = (Vector3) o;
        // compare detail strings
        return (v.x == this.x && v.y == this.y && v.z == this.z);
    }
}

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
class Square extends Shape{
    Vector3 color;
    Square(Vector3 color,List<Vector3> vertices){
        this.color = color;
        this.vertices = vertices;
    }
}
//class Line{
//    double x;
//    double y;
//    Vertex v1;
//    Vertex v2;
//    public Line(GL2 gl){
//
//    }
//}
//class Box{
//    glVertex
//    Box(float x,float y,float z,float len){
//
//    }
//}
enum FaceColor{
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
    float rotlimit = -90f;
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

            rotateX();
            rotateY();
            rotateZ();
        gl.glBegin(GL2.GL_QUADS);

        displaySquare(front);
        displaySquare(left);
        displaySquare(right);
        displaySquare(top);
        displaySquare(back);
        displaySquare(bottom);
        gl.glEnd();

//        System.out.printf("rotation : %s === rotlimit : [%.1f]%n",rotation,rotlimit);
    }
    public void rotateX(){
        if(rotate.x != 0){
            gl.glRotatef(rotation.x,1,0,0);
            if((rotate.x > 0)){
                if(rotation.x > rotlimit) {
                    rotating = false;
                    return;
                }
                rotation.x += rotSpeed;
            }else{
                if(rotation.x < rotlimit) {
                    rotating = false;
                    return;
                }
                rotation.x -= rotSpeed;
            }
        }
    }
    public void rotateY(){
        if(rotate.y != 0){
            gl.glRotatef(rotation.y,0,1,0);
            if((rotate.y > 0)){
                if(rotation.y > rotlimit) {
                    rotating = false;
                    return;
                }
                rotation.y += rotSpeed;
            }else{
                if(rotation.y < rotlimit) {
                    rotating = false;
                    return;
                }
                rotation.y -= rotSpeed;
            }
        }
    }public void rotateZ(){
        if(rotate.z != 0){
            gl.glRotatef(rotation.z,0,0,1);
            if((rotate.z > 0)){
                if(rotation.z > rotlimit) {
                    rotating = false;
                    return;
                }
                rotation.z += rotSpeed;
            }else{
                if(rotation.z < rotlimit) {
                    rotating = false;
                    return;
                }
                rotation.z -= rotSpeed;
            }
        }
    }
    public void displaySquare(Square face) {
            displayColor(face.color);
            for(Vector3 point:face.vertices){
                displayVertex(point);
            }
    }

    void displayColor(Vector3 color){
        gl.glColor3f(color.x,color.y,color.z);
    }
    void displayVertex(Vector3 vertex){
        gl.glVertex3f(scale*vertex.x+shift.x,scale*vertex.y+shift.y,scale*vertex.z+shift.z);
    }
    void setRotation(Vector3 r,int numRotations){
        rotate = r;
        if(rotate == Rotation.NONE.value)
            rotating = false;
        else
            rotating = true;

//        if(rotlimit < -360f)
//        rquad = -(-rquad%360);



//        System.out.printf("rolimit calc : rquad[%d], num rotates[%d], rotlimit++ [%f]%n",Math.round(rquad),numRotations,(-90f*numRotations));


            rotlimit = Math.round(rquad)+(-90f*numRotations);
            if (rquad > -90f)
                rotlimit = -90f;
            else if (rquad > -180f)
                rotlimit = -180f;
            else if (rquad > -270f)
                rotlimit = -270f;
            else
                rotlimit = -360f;
//            if (rotlimit > -90f)
//                rotlimit = -90f;
//            if (rotlimit < -360f){
//                rotlimit = -(-rotlimit%360);
//                rquad = -(-rquad%360);
//            }
    }
    void stopRotation(){
//        System.out.println("STOP!");
//        if(rotate.x < -90f && rotate.y > -180f)
//            rotate.x = -90f;
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
    }
    boolean isRotating(){
//        System.out.println("rotating is "+rotating+" : "+rotate);
        return rotating;
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
class Rubik {
    ArrayList<Cube> rubik = new ArrayList<Cube>();
    float scale = .5f;
    Vector3 xcw = new Vector3(1.0f,0.0f,0.0f);
    Vector3 xcc = new Vector3(-1.0f,0.0f,0.0f);
    Vector3 ycw = new Vector3(0.0f,1.0f,0.0f);
    Vector3 ycc = new Vector3(0.0f,-1.0f,0.0f);
    Vector3 zcw = new Vector3(0.0f,0.0f,1.0f);
    Vector3 zcc = new Vector3(0.0f,0.0f,-1.0f);
    Vector3 non = new Vector3(0.0f,0.0f,0.0f);
    GLAutoDrawable drawable;
    Rubik(GLAutoDrawable drawable){
        init();
        this.drawable = drawable;
    }
    void init(){


        rubik.add(new Cube(new Vector3(-scale,scale,scale)));//0
        rubik.add(new Cube(new Vector3(0,scale,scale)));//1
        rubik.add(new Cube(new Vector3(scale,scale,scale)));//2
//
        rubik.add(new Cube(new Vector3(-scale,0,scale)));//3
        rubik.add(new Cube(new Vector3(0,0,scale)));//4
        rubik.add(new Cube(new Vector3(scale,0,scale)));//5
//
        rubik.add(new Cube(new Vector3(-scale,-scale,scale)));//6
        rubik.add(new Cube(new Vector3(0,-scale,scale)));//7
        rubik.add(new Cube(new Vector3(scale,-scale,scale)));//8

        ////////////////////////////////////////////////////////////////

        rubik.add(new Cube(new Vector3(-scale,scale,0)));//9
        rubik.add(new Cube(new Vector3(0,scale,0)));//10
        rubik.add(new Cube(new Vector3(scale,scale,0)));//11
//
        rubik.add(new Cube(new Vector3(-scale,0,0)));//12
////        rubik.add(new Cube(new Vector3(0,0,0),drawable,xcw));
        rubik.add(new Cube(new Vector3(scale,0,0)));//13
//
        rubik.add(new Cube(new Vector3(-scale,-scale,0)));//14
        rubik.add(new Cube(new Vector3(0,-scale,0)));//15
        rubik.add(new Cube(new Vector3(scale,-scale,0)));//16

/////////////////////////////////////////////////////////////////////////

        rubik.add(new Cube(new Vector3(-scale,scale,-scale)));//17
        rubik.add(new Cube(new Vector3(0,scale,-scale)));//18
        rubik.add(new Cube(new Vector3(scale,scale,-scale)));//19

        rubik.add(new Cube(new Vector3(-scale,0,-scale)));//20
        rubik.add(new Cube(new Vector3(0,0,-scale)));//21
        rubik.add(new Cube(new Vector3(scale,0,-scale)));//22

        rubik.add(new Cube(new Vector3(-scale,-scale,-scale)));//23
        rubik.add(new Cube(new Vector3(0,-scale,-scale)));//24
        rubik.add(new Cube(new Vector3(scale,-scale,-scale)));//25

        for(Cube cubie:rubik){
            cubie.setRotation(Rotation.NONE.value,0);
        }
    }
    boolean isrotating = false;
    void draw(){
        int numRotating = 0;
        for(Cube cubie:rubik){
//            rotateFace(Rotation.Z,front);
//            canrotate = cubie.canRotate();
//            if(!isrotating && cubie.isRotating())
//                isrotating = cubie.isRotating();
            if (cubie.isRotating()) {
//                System.out.println("is rotating");
                numRotating++;
            }
//            System.out.println("number of cubes rotating : "+numRotating);
            cubie.draw(drawable);
        }
        if(numRotating > 0)
            isrotating = true;
        else
            isrotating = false;
    }
    int front[]= {
            0,1,2,
            3,4,5,
            6,7,8
    };
    int frontbuffer[] = front.clone();
    int back[]= {
            19, 18, 17,
            22, 21, 20,
            25, 24, 23
    };
    int backbuffer[] = back.clone();
    int left[]={
            17, 9, 0,
            20, 12, 3,
            23, 14, 6
    };
    int leftbuffer[] = left.clone();
    int right[]={
            2, 11, 19,
            5, 13, 22,
            8, 16, 2
    };
    int rightbuffer[] = right.clone();
    int top[]={
            17,18,19,
            9,10,11,
            0,1,2
    };
    int topbuffer[] = top.clone();
    int bottom[]={
            6,7,8,
            14,15,16,
            23,24,25
    };
    int bottombuffer[] = bottom;
    void rotateFace(Rotation r,int []face,int numRotations){
        for(int index :face){
//            if(rubik.get(index).canRotate())
                rubik.get(index).setRotation(r.value,numRotations);
        }
    }
    void L(boolean clockwise,int numRotations){
        if (clockwise){
            rotateFace(Rotation.X,left,numRotations);/*
            int temp1 = leftbuffer[6];
            leftbuffer[6] = leftbuffer[8];
            leftbuffer[8] = leftbuffer[2];
            leftbuffer[2] = leftbuffer[0];
            leftbuffer[0] = temp1;

            temp1 = leftbuffer[3];
            leftbuffer[3] = leftbuffer[7];
            leftbuffer[7] = leftbuffer[5];
            leftbuffer[5] = leftbuffer[1];
            leftbuffer[1] = temp1;

            temp1 = frontbuffer[0];
            int temp2 = frontbuffer[3];
            int temp3 = frontbuffer[6];
//
            frontbuffer[0] = topbuffer[0];
            frontbuffer[3] = topbuffer[3];
            frontbuffer[6] = topbuffer[6];
//
            topbuffer[0] = backbuffer[8];
            topbuffer[3] = backbuffer[5];
            topbuffer[6] = backbuffer[2];
//
//
            backbuffer[8] = bottombuffer[0];
            backbuffer[5] = bottombuffer[3];
            backbuffer[2] = bottombuffer[6];
//
            bottombuffer[0] = temp1;
            bottombuffer[3] = temp2;
            bottombuffer[6] = temp3;

//            temp1 = frontbuffer
*/
        }
        else {
            rotateFace(Rotation.Xi, left, numRotations);
        }
    }
    void R(boolean clockwise,int numRotations){
        if (clockwise)
            rotateFace(Rotation.X,right,numRotations);
        else
            rotateFace(Rotation.Xi,right,numRotations);
    }
    void B(boolean clockwise,int numRotations){
        if (clockwise)
            rotateFace(Rotation.Zi,back,numRotations);
        else
            rotateFace(Rotation.Z,back,numRotations);
    }
    void U(boolean clockwise,int numRotations){
        if (clockwise)
            rotateFace(Rotation.Y,top,numRotations);
        else
            rotateFace(Rotation.Yi,top,numRotations);
    }
    void D(boolean clockwise,int numRotations){
        if (clockwise)
            rotateFace(Rotation.Yi,bottom,numRotations);
        else
            rotateFace(Rotation.Y,bottom,numRotations);
    }
    void F(boolean clockwise,int numRotations){
//        System.out.println("F is running");
        if (clockwise){
            /*
            int temp1 = frontbuffer[6];
            frontbuffer[6] = frontbuffer[8];
            frontbuffer[8] = frontbuffer[2];
            frontbuffer[2] = frontbuffer[0];
            frontbuffer[0] = temp1;

            temp1 = frontbuffer[3];
            frontbuffer[3] = frontbuffer[7];
            frontbuffer[7] = frontbuffer[5];
            frontbuffer[5] = frontbuffer[1];
            frontbuffer[1] = temp1;

            temp1 = leftbuffer[2];
            int temp2 = leftbuffer[5];
            int temp3 = leftbuffer[8];

            leftbuffer[2] = bottombuffer[0];
            leftbuffer[5] = bottombuffer[1];
            leftbuffer[8] = bottombuffer[2];

            bottombuffer[0] = rightbuffer[6];
            bottombuffer[1] = rightbuffer[3];
            bottombuffer[2] = rightbuffer[0];

            rightbuffer[6] = topbuffer[8];
            rightbuffer[3] = topbuffer[7];
            rightbuffer[0] = topbuffer[6];

            topbuffer[8] = temp1;
            topbuffer[7] = temp2;
            topbuffer[6] = temp3;

            //            frontbuffer[2] = topbuffer[3];
//            frontbuffer[6] = topbuffer[6];
//            frontbuffer[6] = topbuffer[6];
//
//            topbuffer[0] = backbuffer[8];
//            topbuffer[3] = backbuffer[5];
//            topbuffer[6] = backbuffer[2];
//
//
//            backbuffer[8] = bottombuffer[0];
//            backbuffer[5] = bottombuffer[3];
//            backbuffer[2] = bottombuffer[6];
//
//            bottombuffer[0] = temp1;
//            bottombuffer[3] = temp2;
//            bottombuffer[6] = temp3;

             */
            rotateFace(Rotation.Z,front,numRotations);
        }
        else
            rotateFace(Rotation.Zi,front,numRotations);
    }
    void save(){
        top = topbuffer;
        bottom = bottombuffer;
        left = leftbuffer;
        right = rightbuffer;
        front = frontbuffer;
        back = backbuffer;
    }
    boolean isRotating(){
        return isrotating;
    }
}
//class KeyInput implements KeyListener{
//    @Override
//    public void keyPressed(KeyEvent e) {
//        System.out.println(e.getKeyChar()+" pressed");
//    }
//
//    @Override
//    public void keyReleased(KeyEvent e) {
//        System.out.println(e.getKeyChar()+" released");
//    }
//
//    @Override
//    public void keyTyped(KeyEvent e) {
//        System.out.println(e.getKeyChar()+" typed");
//    }
//}

public class Render implements GLEventListener{
    public static DisplayMode dm, dm_old;
    private GLU glu = new GLU();
   //  private float rquad = 0.0f;
    static Rubik rubik;
    static int index = -1;
    ArrayList<String> instructions = new ArrayList<>();
//    private int  next = KeyEvent.VK_ENTER;
   //  private static Point center;
   //  private Point mousePoint;



    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
//        rubik.rotateFrontFace();
//
//        System.out.println("rubik is rotating ? :"+rubik.isRotating());

        if(index < instructions.size()-1 && !rubik.isRotating()){
         index++;
            String instruction = instructions.get(index);
            switch (instruction){
                case "L":rubik.L(true,1);System.out.println("L command");break;
                case "R":rubik.R(true,1);System.out.println("R command");break;
                case "F":rubik.F(true,1);System.out.println("F command");break;
                case "B":rubik.B(true,1);System.out.println("B command");break;
                case "U":rubik.U(true,1);System.out.println("U command");break;
                case "D":rubik.D(true,1);System.out.println("D command");break;

                case "L2":rubik.L(true,2);System.out.println("L command");break;
                case "R2":rubik.R(true,2);System.out.println("R command");break;
                case "F2":rubik.F(true,2);System.out.println("F command");break;
                case "B2":rubik.B(true,2);System.out.println("B command");break;
                case "U2":rubik.U(true,2);System.out.println("U command");break;
                case "D2":rubik.D(true,2);System.out.println("D command");break;

                case "L'":rubik.L(false,1);System.out.println("L' command");break;
                case "R'":rubik.R(false,1);System.out.println("R' command");break;
                case "F'":rubik.F(false,1);System.out.println("F' command");break;
                case "B'":rubik.B(false,1);System.out.println("B' command");break;
                case "U'":rubik.U(false,1);System.out.println("U' command");break;
                case "D'":rubik.D(false,1);System.out.println("D' command");break;
                default:break;
            }
            rubik.save();
            next = false;
        }

//            rubik.L(true,1);
//            if(!rubik.isRotating())
//                rubik.F(true,3);
        rubik.draw();
//        if(index == instructions.size()) {
//            System.out.println("END");
//            System.exit(1);
//        }
    }

    @Override
    public void dispose(GLAutoDrawable arg0) {
        //method body
    }

    @Override
    public void init(GLAutoDrawable drawable) {

        final GL2 gl = drawable.getGL().getGL2();
        gl.glShadeModel(GL2.GL_SMOOTH);
        gl.glClearColor(0f, 0f, 0f, 0f);
        gl.glClearDepth(1.0f);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glDepthFunc(GL2.GL_LEQUAL);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
        rubik = new Rubik(drawable);
//        drawable.addGLEventListener(r);
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

        // TODO Auto-generated method stub
        final GL2 gl = drawable.getGL().getGL2();
        if (height == 0)
            height = 1;

        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
//        rubik.L(true,1);
    }
    static boolean next = false;
    public static void main(String[] args) {

        //getting the capabilities object of GL2 profile
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        Toolkit t = Toolkit.getDefaultToolkit();
        Image img= new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB);
        Cursor pointer = t.createCustomCursor(img,new Point(0,0),"none");
        // The canvas
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        Render r = new Render();
//        r.instructions.add("F");
        r.instructions.add("L");
        r.instructions.add("L'");
        r.instructions.add("L2");

        r.instructions.add("R");
        r.instructions.add("R'");
        r.instructions.add("R2");

        r.instructions.add("F");
        r.instructions.add("F'");
        r.instructions.add("F2");

        r.instructions.add("B");
        r.instructions.add("B'");
        r.instructions.add("B2");

        r.instructions.add("U");
        r.instructions.add("U'");
        r.instructions.add("U2");

        r.instructions.add("D");
        r.instructions.add("D'");
        r.instructions.add("D2");

//        r.instructions.add("F");
//        r.instructions.add("F");
//        r.instructions.add("F");

//        r.instructions.add("L");

//        rubik.L(true,1);
//        Render r2 = new Render();
//        Rubik r = new Rubik(drawable);
//        rubik.F(true);
//        KeyInput keylistener = new KeyInput();
        glcanvas.addGLEventListener(r);
        glcanvas.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println(e.getKeyChar()+" pressed");
            }

            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println(e.getKeyChar()+" released");
                next = true;
            }

            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println(e.getKeyChar()+" typed");
            }
        });
        glcanvas.setSize(400, 400);

        //creating frame
        final JFrame frame = new JFrame("cube");

        //adding canvas to frame
        frame.getContentPane().add(glcanvas);
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        final FPSAnimator animator = new FPSAnimator(glcanvas, 300, true);
        animator.start();

    }//end of main

}//end of classimport javax.media.opengl.GL2;