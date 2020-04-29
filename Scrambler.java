import java.util.ArrayList;
import java.util.List;

public class Scrambler{
	public int num_moves = 0;
	public String faces[] = {"U","D","L","R","F","B"};
	public String modifiers[] = {"","'", "2"};
	public List<String> instructions = new ArrayList<String>();
	
	public Scrambler(){
	}
	public void setNumMoves(int num_moves){
		this.num_moves = num_moves;
	}
	public int generateRandomNumber(int range){
		int random = (int) (Math.random() * range);
		return random;
	}

	public void generateScramble(){
		for(int i = 0; i < num_moves; i++){
			// int random = Math.random(0);
			String face = faces[generateRandomNumber(6)];
			String modifier = modifiers[generateRandomNumber(3)];
			instructions.add(face + modifier);
		}
	}
	public String getScrambleString(){
		// String scramble = "\"";
		String scramble = "";
		int num_moves = this.num_moves;
		for(String s:this.instructions){
			num_moves--;
			scramble += s + (num_moves == 0 ? "":" ");
		}
		// scramble += "\"";
		return scramble;
	}
}
// class ScramblerRun{
// 	static Scrambler scr;
// 	public static void main(String [] args) {
// 		int num_moves = 10;
// 		if(args.length > 0)
// 			num_moves = Integer.parseInt(args[0]);
// 		scr = new Scrambler(num_moves);
// 		scr.generateScramble();
// 		String scramble = scr.getScrambleString();
// 		System.out.println(scramble);
// 	}
// }