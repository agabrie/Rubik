package renderer;

import java.util.ArrayList;

import com.jogamp.opengl.GLAutoDrawable;

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
        System.out.println("###################DRAW####################");
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
//    int front[][]= {
//            {0,1,2},
//            {3,4,5},
//            {6,7,8}
//    };
    /*


                    F
                0   1   2
                3   4   5
                6   7   8

                    L
                17  9   0
                20  12  3
                23  14  6


                    B
                19  18  17
                22  21  20
                25  24  23
     */
//    int frontbuffer[][] = front.clone();
    int front[]={
            0,  1,  2,
            3,  4,  5,
            6,  7,  8
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
            8, 16, 25
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
        System.out.println("Setting Rotation"+r);
        for(int index :face){
//            if(rubik.get(index).canRotate();
                rubik.get(index).setRotation(r.value,numRotations);
        }
        System.out.println("Stopped Setting");
    }
    int TL = 0;
    int T  = 1;
    int TR = 2;
    int L  = 3;
    int C  = 4;
    int R  = 5;
    int BL = 6;
    int B  = 7;
    int BR = 8;
    void printfacevalues(int[]face){
        int i = 0;
        for(int value:face){
            System.out.printf("[%2d]",value);
            i++;
            if(i % 3 == 0)
                System.out.println();
        }
    }
//    static void rotateMatrix(int N, int mat[][])
//    {
//        // Consider all squares one by one
//        for (int x = 0; x < N / 2; x++) {
//            // Consider elements in group of 4 in
//            // current square
//            for (int y = x; y < N - x - 1; y++) {
//                // store current cell in temp variable
//                int temp = mat[x][y];
//
//                // move values from right to top
//                mat[x][y] = mat[y][N - 1 - x];
//
//                // move values from bottom to right
//                mat[y][N - 1 - x] = mat[N - 1 - x][N - 1 - y];
//
//                // move values from left to bottom
//                mat[N - 1 - x][N - 1 - y] = mat[N - 1 - y][x];
//
//                // assign temp to left
//                mat[N - 1 - y][x] = temp;
//            }
//        }
//    }
    void compareFace(){
        System.out.println(" -------------------------Left Face:-----------------------------");
        printfacevalues(left);
        System.out.println(" -------------------------Left BUFFER:---------------------------");
        printfacevalues(leftbuffer);

        System.out.println(" -------------------------Front Face:-----------------------------");
        printfacevalues(front);
        System.out.println(" -------------------------Front BUFFER:---------------------------");
        printfacevalues(frontbuffer);
    }
    void L(boolean clockwise,int numRotations){
        compareFace();
        if (clockwise){
            rotateFace(Rotation.X,left,numRotations);
            /*
            int temp1 = leftbuffer[BR];
            leftbuffer[BR] = leftbuffer[TR];
            leftbuffer[TR] = leftbuffer[TL];
            leftbuffer[TL] = leftbuffer[BL];
            leftbuffer[BL] = temp1;

            temp1 = leftbuffer[R];
            leftbuffer[R] = leftbuffer[T];
            leftbuffer[T] = leftbuffer[L];
            leftbuffer[L] = leftbuffer[B];
            leftbuffer[B] = temp1;

            temp1 = frontbuffer[TL];
            int temp2 = frontbuffer[L];
            int temp3 = frontbuffer[BL];
//
            frontbuffer[TL] = topbuffer[TL];
            frontbuffer[L] = topbuffer[L];
            frontbuffer[BL] = topbuffer[BL];
//
            topbuffer[TL] = backbuffer[BR];
            topbuffer[L] = backbuffer[R];
            topbuffer[BL] = backbuffer[TR];
//
//
            backbuffer[TR] = bottombuffer[BL];
            backbuffer[R] = bottombuffer[L];
            backbuffer[BR] = bottombuffer[TL];
//
            bottombuffer[TL] = temp1;
            bottombuffer[L] = temp2;
            bottombuffer[BL] = temp3;
            */
//            temp1 = frontbuffer
//            rotateMatrix(3,frontbuffer);
        }
        else {
            rotateFace(Rotation.Xi, left, numRotations);
        }
    }
    void R(boolean clockwise,int numRotations){
        if (clockwise)
            rotateFace(Rotation.Xi,right,numRotations);
        else
            rotateFace(Rotation.X,right,numRotations);
    }
    void B(boolean clockwise,int numRotations){
        if (clockwise)
            rotateFace(Rotation.Z,back,numRotations);
        else
            rotateFace(Rotation.Zi,back,numRotations);
    }
    void U(boolean clockwise,int numRotations){
        if (clockwise)
            rotateFace(Rotation.Yi,top,numRotations);
        else
            rotateFace(Rotation.Y,top,numRotations);
    }
    void D(boolean clockwise,int numRotations){
        if (clockwise)
            rotateFace(Rotation.Y,bottom,numRotations);
        else
            rotateFace(Rotation.Yi,bottom,numRotations);
    }
    void F(boolean clockwise,int numRotations){
//        System.out.println("F is running");
        compareFace();
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
            rotateFace(Rotation.Zi,front,numRotations);
        }
        else
            rotateFace(Rotation.Z,front,numRotations);
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