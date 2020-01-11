import java.util.Scanner;
class Driver{
    // public currentBase
    public static void main(String [] args){
        Rubik cube = new Rubik();
        Scanner scInput = new Scanner(System.in);
        
        String line;
        while(scInput.hasNext()){
            line = scInput.nextLine();
            // System.out.println(line);
            Scanner scLine = new Scanner(line).useDelimiter(" ");
            String instr;
            boolean clockwise;
            boolean extra;
            while(scLine.hasNext()){
                clockwise = true;
                extra = false;
                instr = scLine.nextLine();
                System.out.println("Instruction : "+instr);
                if(instr.length() == 2){
                    clockwise = (!(instr.charAt(1) == '\''));
                    // clockwise = (!instr.contains("\'"));
                    extra = (instr.charAt(1)=='2');
                    // extra = (instr.contains("2"));
                }
                switch(instr.charAt(0)){
                    case 'F':
                        cube.F(clockwise,extra);
                        break;
                    case 'D':
                        cube.D(clockwise,extra);
                        break;
                    case 'R':
                        cube.R(clockwise,extra);
                        break;
                    case 'L':
                        cube.L(clockwise,extra);
                        break;
                    case 'B':
                        cube.B(clockwise,extra);
                        break;
                    case 'U':
                        cube.U(clockwise,extra);
                        break;
                    default:
                        break;
                }
                System.err.print(cube.toString());
            }
        }
    }
    
}