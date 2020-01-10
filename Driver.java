import java.util.Scanner;
class Driver{
    // public currentBase
    static Rubik cube = new Rubik();
    public static void main(String [] args){
        // Face face =  new Face(Color.BLUE,Orientation.FRONT);
        // try{
        Scanner scInput = new Scanner(System.in);
        boolean clockwise = true;
        // int face;
        String instr;
        boolean extra = false;
        String line;
        while(scInput.hasNext()){
            line = scInput.nextLine();
            // System.out.println(line);
            Scanner scLine = new Scanner(line).useDelimiter(" ");
            while(scLine.hasNext()){
                // face = scLine.nextInt();
                // face = face % 6;
                instr = scLine.nextLine();
                System.out.println("Instruction : "+instr);
                if(instr.length() > 1){
                    clockwise = (!instr.contains("\'"));
                    extra = (instr.contains("2"));
                }
                // System.out.println("something");
                switch(instr.charAt(0)){
                    case 'F':
                        F(clockwise,extra);
                        break;
                    case 'B':
                        B(clockwise,extra);
                        break;
                    case 'L':
                        L(clockwise,extra);
                        break;
                    default:
                        break;
                }

                // cube.rotateFace(clockwise, face);
                System.err.print(cube.toString());
            }
        }
    }
    public static void F(boolean clockwise,boolean extra){
        cube.rotateFace(clockwise, 0);
        if(extra)
            cube.rotateFace(clockwise, 0);
    }
    public static void D(boolean clockwise,boolean extra){
        cube.rotateFace(clockwise, 1);
        if(extra)
            cube.rotateFace(clockwise, 1);
    }
    public static void R(boolean clockwise,boolean extra){
        cube.rotateFace(clockwise, 2);
        if(extra)
            cube.rotateFace(clockwise, 2);
    }
    public static void L(boolean clockwise,boolean extra){
        cube.rotateFace(clockwise, 3);
        if(extra)
            cube.rotateFace(clockwise, 3);
    }
    public static void B(boolean clockwise,boolean extra){
        cube.rotateFace(clockwise, 4);
        if(extra)
            cube.rotateFace(clockwise, 4);
    }
    public static void U(boolean clockwise,boolean extra){
        cube.rotateFace(clockwise, 5);
        if(extra)
            cube.rotateFace(clockwise, 5);
    }
        // catch();
        // System.out.println(cube);
}