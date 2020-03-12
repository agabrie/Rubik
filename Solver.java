import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solver{
	public static Orientation frontface = Orientation.FRONT;

	public static HashMap<String, String> rotations;
	public static HashMap<Orientation, Runnable> orient = new HashMap<Orientation,Runnable>(){
		{
			put(Orientation.FRONT,()->{
				rotations = new HashMap<String, String>();
				rotations.put("U", "U");
				rotations.put("L", "L");
				rotations.put("R", "R");
				rotations.put("D", "D");
				rotations.put("F", "F");
				rotations.put("B", "B");
				// System.out.println(rotations);
			});
			put(Orientation.BACK,()->{
				rotations = new HashMap<String, String>();
				rotations.put("U", "U");
				rotations.put("L", "R");
				rotations.put("R", "L");
				rotations.put("D", "D");
				rotations.put("F", "B");
				rotations.put("B", "F");
			});
			put(Orientation.LEFT,()->{
				rotations = new HashMap<String, String>();
				rotations.put("U", "U");
				rotations.put("L", "B");
				rotations.put("R", "F");
				rotations.put("D", "D");
				rotations.put("F", "L");
				rotations.put("B", "R");
			});
			put(Orientation.RIGHT,()->{
				rotations = new HashMap<String, String>();
				rotations.put("U", "U");
				rotations.put("L", "F");
				rotations.put("R", "B");
				rotations.put("D", "D");
				rotations.put("F", "R");
				rotations.put("B", "L");
			});
			put(Orientation.UP,()->{
				rotations = new HashMap<String, String>();
				rotations.put("U", "B");
				rotations.put("L", "L");
				rotations.put("R", "R");
				rotations.put("D", "F");
				rotations.put("F", "U");
				rotations.put("B", "D");
				// System.out.println(rotations);
			});
			put(Orientation.DOWN,()->{
				rotations = new HashMap<String, String>();
				rotations.put("U", "F");
				rotations.put("L", "L");
				rotations.put("R", "R");
				rotations.put("D", "B");
				rotations.put("F", "D");
				rotations.put("B", "U");
				// System.out.println(rotations);
			});
		}
	};
	public static void switchFace(Orientation o){
		frontface = o;
		// System.out.println("gihihihihihihhhhhiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
		orient.get(frontface).run();
	}

	static void RTrigger(){
		List<String> instructions = new ArrayList<String>();
		instructions.add(rotations.get("R"));
		instructions.add(rotations.get("U"));
		instructions.add(rotations.get("R")+"'");
		instructions.add(rotations.get("U")+"'");
		Driver.invoke(instructions);
	}
	static void LTrigger(){
		List<String> instructions = new ArrayList<String>();
		instructions.add(rotations.get("L")+"'");
		instructions.add(rotations.get("U")+"'");
		instructions.add(rotations.get("L"));
		instructions.add(rotations.get("U"));
		Driver.invoke(instructions);
	}
	static void Top2RightEdge(){
		List<String> instructions = new ArrayList<String>();
		instructions.add(rotations.get("U"));
		instructions.add(rotations.get("R"));
		instructions.add(rotations.get("U")+"'");
		instructions.add(rotations.get("R")+"'");
		instructions.add(rotations.get("U")+"'");
		instructions.add(rotations.get("F")+"'");
		instructions.add(rotations.get("U"));
		instructions.add(rotations.get("F"));
	}
	static void Top2LeftEdge(){
		List<String> instructions = new ArrayList<String>();
		instructions.add(rotations.get("U")+"'");
		instructions.add(rotations.get("L")+"'");
		instructions.add(rotations.get("U"));
		instructions.add(rotations.get("L"));
		instructions.add(rotations.get("U"));
		instructions.add(rotations.get("F"));
		instructions.add(rotations.get("U")+"'");
		instructions.add(rotations.get("F")+"'");
	}
	static void simplesolve(List<String> instructions){
		switchFace(Orientation.LEFT);
		RTrigger();
		// Driver.execute(rotations.get("R"));
		// Collections.reverse(instructions);
		// for(String instr : instructions){
		// 	if(instr.contains("\'")){
		// 		Driver.execute(instr.replace("\'", "2"));
		// 		Driver.execute(instr);
		// 	}
		// 	else if(instr.contains("2")){
		// 		Driver.execute(instr);
		// 	}
		// 	else
		// 		Driver.execute(instr+'\'');
		// }
	}

	public void FindEdge(Color color){
		Rubik cube = Driver.cube;
		for(Face f : cube.face){
			for(Cubie c: f.cubie){
				return ;
			}
		}
	}
}