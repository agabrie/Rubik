package rubik;

import java.util.HashMap;

public class Control {
	static private Color frontface = Color.WHITE;
	static private HashMap<String, String> rotations;
	static private HashMap<Enum<?>, Runnable> orient = new HashMap<Enum<?>, Runnable>() {
		private static final long serialVersionUID = 1L;

		{
			put(Color.WHITE, () -> {
				rotations = new HashMap<>();
				rotations.put("U", "U");
				rotations.put("L", "L");
				rotations.put("R", "R");
				rotations.put("D", "D");
				rotations.put("F", "F");
				rotations.put("B", "B");
			});
			put(Color.YELLOW, () -> {
				rotations = new HashMap<>();
				rotations.put("U", "U");
				rotations.put("L", "R");
				rotations.put("R", "L");
				rotations.put("D", "D");
				rotations.put("F", "B");
				rotations.put("B", "F");
			});
			put(Color.GREEN, () -> {
				rotations = new HashMap<>();
				rotations.put("U", "U");
				rotations.put("L", "B");
				rotations.put("R", "F");
				rotations.put("D", "D");
				rotations.put("F", "L");
				rotations.put("B", "R");
			});
			put(Color.BLUE, () -> {
				rotations = new HashMap<>();
				rotations.put("U", "U");
				rotations.put("L", "F");
				rotations.put("R", "B");
				rotations.put("D", "D");
				rotations.put("F", "R");
				rotations.put("B", "L");
			});
			put(Color.ORANGE, () -> {
				rotations = new HashMap<>();
				rotations.put("U", "B");
				rotations.put("L", "L");
				rotations.put("R", "R");
				rotations.put("D", "F");
				rotations.put("F", "U");
				rotations.put("B", "D");
			});
			put(Color.RED, () -> {
				rotations = new HashMap<>();
				rotations.put("U", "F");
				rotations.put("L", "L");
				rotations.put("R", "R");
				rotations.put("D", "B");
				rotations.put("F", "D");
				rotations.put("B", "U");
			});
		}
	};

	static public void switchFace(Color f) {
		frontface = f;
		orient.get(frontface).run();
	}
	static void R(){
		Driver.execute(rotations.get("R"));
	}
	static void Ri(){
		Driver.execute(rotations.get("R")+"'");
	}
	static void R2(){
		Driver.execute(rotations.get("R")+"2");
	}
	
	static void L(){
		Driver.execute(rotations.get("L"));
	}
	static void Li(){
		Driver.execute(rotations.get("L")+"'");
	}
	static void L2(){
		Driver.execute(rotations.get("L")+"2");
	}
	
	static void U(){
		Driver.execute(rotations.get("U"));
	}
	static void Ui(){
		Driver.execute(rotations.get("U")+"'");
	}
	static void U2(){
		Driver.execute(rotations.get("U")+"2");
	}
	
	static void D(){
		Driver.execute(rotations.get("D"));
	}
	static void Di(){
		Driver.execute(rotations.get("D")+"'");
	}
	static void D2(){
		Driver.execute(rotations.get("D")+"2");
	}

	static void F(){
		Driver.execute(rotations.get("F"));
	}
	static void Fi(){
		Driver.execute(rotations.get("F")+"'");
	}
	static void F2(){
		Driver.execute(rotations.get("F")+"2");
	}

	static void B(){
		Driver.execute(rotations.get("B"));
	}
	static void Bi(){
		Driver.execute(rotations.get("B")+"'");
	}
	static void B2(){
		Driver.execute(rotations.get("B")+"2");
	}
}