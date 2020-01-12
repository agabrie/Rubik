import java.util.*;

class Driver{
	static Rubik cube = new Rubik();
	static boolean verbose = false;
	public static void invoke(String instr){
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
			if(verbose)
				System.err.print(cube.toString());
			} catch (Exception e) {
				System.out.println(e);
				System.exit(0);
			}
		}
	static void simplesolve(List<String> scramble){
		Collections.reverse(scramble);
		for(String instr : scramble){
			if(instr.contains("\'")){
				invoke(instr.replace("\'", "2"));
				invoke(instr);
			}
			else if(instr.contains("2")){
				invoke(instr);
			}
			else
				invoke(instr+'\'');
		}
	}
	public static boolean binaryquestion(String s){
		try {
			Scanner scInput = new Scanner(System.in);
			System.out.println(s+"? Y/N");
			String input = scInput.nextLine();
			scInput.close();
			if(input.equalsIgnoreCase("Y"))
				return true;
			else if (!input.equalsIgnoreCase("N"))
				throw new Exception("Invalid Input: 'Y' or 'N' expected");
		} catch (Exception e) {
			System.out.println(e);
			System.exit(0);
		}
		return false;
	}
	public static void main(String [] args){
		try{
			System.out.println(cube);
			List<String> scramble= new ArrayList<String>();
			if(!(args.length == 1))
				throw new Exception("Argument required");
			Collections.addAll(scramble, args[0].toUpperCase().split(" "));
			verbose = binaryquestion("turn verbose mode on");
			for(String instr:scramble)
				invoke(instr);
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