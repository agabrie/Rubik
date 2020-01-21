import java.util.*;

class Driver{
	static Rubik cube = new Rubik();
	static boolean verbose = false;
	static int moves = 0;
	
	public static void invoke(List<String> instructions){
		for(String instr:instructions)
			execute(instr);
	}
	
	public static void execute(String instr){
		try {
			boolean clockwise;
			boolean extra;
			clockwise = true;
			extra = false;
			System.out.println("Instruction : "+instr);
			if(instr.length() == 2){
				// clockwise = (!(instr.charAt(1) == '\''));
				extra = (instr.contains("2"));
				if(!extra)
					clockwise = (!(instr.contains("\'")));
				// extra = (instr.charAt(1)=='2');
				instr = instr.replace("2", "");
				instr = instr.replace("\'", "");
			}
				
			if(instr.length() != 1){
				throw new Exception("incorrect instruction format : "+ instr);
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
					throw new Exception("no instruction given");
					// break;
			}
			moves++;
			if(verbose)
				System.err.println(cube.toString()+"Number of Moves : "+moves);
		} catch (Exception e) {
			System.out.println(e);
			System.exit(0);
		}
	}
	static void RTrigger(){
		List<String> instructions = new ArrayList<String>();
		instructions.add("R");
		instructions.add("U");
		instructions.add("R'");
		instructions.add("U'");
		invoke(instructions);
	}
	static void LTrigger(){
		List<String> instructions = new ArrayList<String>();
		instructions.add("L'");
		instructions.add("U'");
		instructions.add("L");
		instructions.add("U");
	}
	static void T2RE(){
		List<String> instructions = new ArrayList<String>();
		instructions.add("U");
		instructions.add("R");
		instructions.add("U'");
		instructions.add("R'");
		instructions.add("U'");
		instructions.add("F'");
		instructions.add("U");
		instructions.add("F");
	}
	static void T2LE(){
		List<String> instructions = new ArrayList<String>();
		instructions.add("U'");
		instructions.add("L'");
		instructions.add("U");
		instructions.add("L");
		instructions.add("U");
		instructions.add("F");
		instructions.add("U'");
		instructions.add("F'");
	}
	static void simplesolve(List<String> instructions){
		Collections.reverse(instructions);
		for(String instr : instructions){
			if(instr.contains("\'")){
				execute(instr.replace("\'", "2"));
				execute(instr);
			}
			else if(instr.contains("2")){
				execute(instr);
			}
			else
				execute(instr+'\'');
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
			System.out.println(cube);
			List<String> scramble= new ArrayList<String>();
			if(!(args.length == 1))
				throw new Exception("Argument required");
			Collections.addAll(scramble, args[0].toUpperCase().split(" "));
			verbose = binaryquestion("turn verbose mode on");
				invoke(scramble);
			System.out.println("Post scramble:\n"+cube);
			if(binaryquestion("Do you wish to see solution")){
				verbose = binaryquestion("With vebose mode turned on");
				simplesolve(scramble);
			}
			System.out.println("Final solution:\n"+cube);
		}catch(Exception e){
			System.out.println(e);
			System.exit(0);
		}
	}
}