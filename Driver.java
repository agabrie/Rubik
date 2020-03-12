import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

class Driver{
	static Rubik cube = new Rubik();
	static Rubik solved = new Rubik();
	static boolean verbose = false;
	static int moves = 0;
	static HashMap<Character, Runnable> instruct;

	public static void invoke(List<String> instructions){
		for(String instr:instructions)
			execute(instr);
	}
	
	public static void execute(String instr){
		try {
			System.out.println("Instruction : "+instr);
			if(instr.length() == 2){
				// clockwise = (!(instr.charAt(1) == '\''));
				cube.extra = (instr.contains("2"));
				if(!cube.extra)
					cube.clockwise = (!(instr.contains("\'")));
				// extra = (instr.charAt(1)=='2');
				instr = instr.replace("2", "");
				instr = instr.replace("\'", "");
			}
				
			if(instr.length() != 1){
				throw new Exception("incorrect instruction format : "+ instr);
			}

			instruct.get(instr.charAt(0)).run();
			
			moves++;
			if(verbose)
				System.err.println(cube.toString()+"Number of Moves : "+moves);
		} catch (Exception e) {
			System.out.println(e);
			System.exit(0);
		}
	}
	

	public static boolean binaryquestion(String s){
		String input;
		Scanner scInput;
		boolean output = false;
		scInput = new Scanner(System.in);
		try {
			System.out.println(s+"? Y/N");
			input = scInput.nextLine();
			if(input.equalsIgnoreCase("Y"))
				output = true;
			else if (!input.equalsIgnoreCase("N")){
				scInput.close();
				throw new Exception("Invalid Input: 'Y' or 'N' expected");
			}
		} catch (Exception e) {
			System.out.println(e);
			System.exit(0);
		}
		return output;
	}
	public static void main(String [] args){
		try{
			// Solver solver = new Solver();
			instruct = new HashMap<Character,Runnable>();
			instruct.put('F',()->cube.F());
			instruct.put('D',()->cube.D());
			instruct.put('R',()->cube.R());
			instruct.put('L',()->cube.L());
			instruct.put('B',()->cube.B());
			instruct.put('U',()->cube.U());

			System.out.println(cube);
			List<String> scramble= new ArrayList<String>();
			if(!(args.length == 1))
				debug();
				// throw new Exception("Argument required");
			Collections.addAll(scramble, args[0].toUpperCase().split(" "));
			verbose = binaryquestion("turn verbose mode on");
				invoke(scramble);
			System.out.println("Post scramble:\n"+cube);
			if(binaryquestion("Do you wish to see solution")){
				verbose = binaryquestion("With vebose mode turned on");
				Solver.simplesolve(scramble);
			}
			System.out.println("Final solution:\n"+cube);
		}catch(Exception e){
			System.out.println(e);
			System.exit(0);
		}
	}
	public static void debug(){
		try{
			Scanner scInput = new Scanner(System.in);
			while(scInput.hasNextLine()){
				execute(scInput.nextLine());
				System.out.println(cube);
			}
		}catch(Exception e){
			System.out.println(e);
			System.exit(0);
		}
	}
}