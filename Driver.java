import java.util.*;
import java.io.*;

class Driver{
	static Rubik cube = new Rubik();
	static boolean verbose = false;
	public static void invoke(String instr){
		boolean clockwise;
		boolean extra;
			clockwise = true;
			extra = false;
			System.out.println("Instruction : "+instr);
			if(instr.length() == 2){
				// clockwise = (!(instr.charAt(1) == '\''));
				clockwise = (!(instr.contains("\'")));
				// extra = (instr.charAt(1)=='2');
				extra = (instr.contains("2"));
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
					// throw new Exception("Incorrect rotation");
					break;
			}
			if(verbose)
				System.err.print(cube.toString());
		}
	static void simplesolve(List<String> scramble){
		Collections.reverse(scramble);
		for(String instr : scramble){
			if(instr.contains("\'")){
				invoke(instr.replace("\'", "2"));
				// invoke(instr.replace("\'", ""));
				invoke(instr);
			}
			else if(instr.contains("2")){
				invoke(instr);
			}
			else
				invoke(instr+'\'');
		}
	}
	public static void main(String [] args){
		try{
			System.out.println(cube);
			
			List<String> scramble= new ArrayList<String>();
			if(!(args.length == 1))
				throw new Exception("Argument required");
			Collections.addAll(scramble, args[0].toUpperCase().split(" "));
			for(String instr:scramble)
				invoke(instr);
			System.out.println(cube);

			Scanner scInput = new Scanner(System.in);
			System.out.println("Do You Wish to see soluion? Y/N");
			if(scInput.nextLine().equalsIgnoreCase("Y"))
				simplesolve(scramble);
			System.out.println(cube);
			
		}catch(Exception e){
			System.out.println(e);
		}
	}
}