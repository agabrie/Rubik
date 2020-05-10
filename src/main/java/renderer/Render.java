package renderer;

// import com.jogamp.graph.geom.Vertex;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
//import javafx.geometry.Orientation;
// import jogamp.opengl.glu.gl2.nurbs.GLUgl2nurbsImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
// import java.util.List;




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
    private float rquad = 0.0f;
    static Rubik rubik;
    static int index = -1;
    ArrayList<String> instructions = new ArrayList<>();
//    private int  next = KeyEvent.VK_ENTER;
    private static Point center;
    private Point mousePoint;



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
//            try {
//                TimeUnit.MINUTES.sleep(1);
//            }catch (Exception e){;}
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
        // r.instructions.add("L2");
        // r.instructions.add("L'");
        // r.instructions.add("L'");

        // r.instructions.add("R");
        // r.instructions.add("R'");
        // r.instructions.add("R2");
        // r.instructions.add("R'");
        // r.instructions.add("R'");

        r.instructions.add("F");
        r.instructions.add("F'");
        r.instructions.add("F2");
        r.instructions.add("F'");
        r.instructions.add("F'");

        r.instructions.add("B");
        r.instructions.add("B'");
        r.instructions.add("B2");
        r.instructions.add("B'");
        r.instructions.add("B'");


        r.instructions.add("U");
        r.instructions.add("U'");
        r.instructions.add("U2");
        r.instructions.add("U'");
        r.instructions.add("U'");

        r.instructions.add("D");
        r.instructions.add("D'");
        r.instructions.add("D2");
        r.instructions.add("D'");
        r.instructions.add("D'");

        r.instructions.add("L");
        r.instructions.add("R");


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
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        final FPSAnimator animator = new FPSAnimator(glcanvas, 300, true);
        animator.start();

    }//end of main

}//end of classimport javax.media.opengl.GL2;