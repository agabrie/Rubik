import java.util.Scanner;
class Driver{
    public static void main(String [] args){
        // Face face =  new Face(Color.BLUE,Orientation.FRONT);
        Rubik cube = new Rubik();
        // try{
        Scanner scInput = new Scanner(System.in);
        boolean clockwise = true;
        int face;
        String line;
        while(scInput.hasNext()){
            line = scInput.nextLine();
            // System.out.println(line);
            Scanner scLine = new Scanner(line).useDelimiter(" ");
            while(scLine.hasNext()){
                face = scLine.nextInt();
                face = face % 6;
                clockwise = scLine.next().equals("cw");
                cube.rotateFace(clockwise, face);
                System.err.print(cube.toString());
            }
        }
    }
        // catch();
        // System.out.println(cube);
}