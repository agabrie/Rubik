import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.text.Position;


/**
 *
 * @author Abduraghmaan G
 */
public class Solver {

	public static int frontface = 0;
	public static final int WHITE = 0;
	public static final int RED = 1;
	public static final int BLUE = 2;
	public static final int GREEN = 3;
	public static final int YELLOW = 4;
	public static final int ORANGE = 5;

	public static final int TL = 0;
	public static final int T = 1;
	public static final int TR = 2;
	public static final int L = 3;
	public static final int R = 4;
	public static final int BL = 5;
	public static final int B = 6;
	public static final int BR = 7;

	public static HashMap<String, String> rotations;
	public static int oppositeFace(int face){
		int opposite_face;
		switch (face){
			case WHITE : opposite_face = YELLOW; break;
			case YELLOW : opposite_face = WHITE; break;
			case RED : opposite_face = ORANGE; break;
			case ORANGE : opposite_face = RED; break;
			case GREEN : opposite_face = BLUE; break;
			case BLUE : opposite_face = GREEN; break;
			default : opposite_face = YELLOW; break;
		}
		return opposite_face;
	}
	public static HashMap<Integer, Runnable> orient = new HashMap<Integer, Runnable>() {
		{
			put(WHITE, () -> {
				rotations = new HashMap<>();
				rotations.put("U", "U");
				rotations.put("L", "L");
				rotations.put("R", "R");
				rotations.put("D", "D");
				rotations.put("F", "F");
				rotations.put("B", "B");
				// System.out.println(rotations);
			});
			put(YELLOW, () -> {
				rotations = new HashMap<>();
				rotations.put("U", "U");
				rotations.put("L", "R");
				rotations.put("R", "L");
				rotations.put("D", "D");
				rotations.put("F", "B");
				rotations.put("B", "F");
			});
			put(GREEN, () -> {
				rotations = new HashMap<>();
				rotations.put("U", "U");
				rotations.put("L", "B");
				rotations.put("R", "F");
				rotations.put("D", "D");
				rotations.put("F", "L");
				rotations.put("B", "R");
			});
			put(BLUE, () -> {
				rotations = new HashMap<>();
				rotations.put("U", "U");
				rotations.put("L", "F");
				rotations.put("R", "B");
				rotations.put("D", "D");
				rotations.put("F", "R");
				rotations.put("B", "L");
			});
			put(ORANGE, () -> {
				rotations = new HashMap<>();
				rotations.put("U", "B");
				rotations.put("L", "L");
				rotations.put("R", "R");
				rotations.put("D", "F");
				rotations.put("F", "U");
				rotations.put("B", "D");
				// System.out.println(rotations);
			});
			put(RED, () -> {
				rotations = new HashMap<>();
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

	public static void switchFace(int f) {
		frontface = f;
		// System.out.println("gihihihihihihhhhhiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
		orient.get(frontface).run();
	}

	static void RTrigger() {
		List<String> instructions = new ArrayList<>();
		instructions.add(rotations.get("R"));
		instructions.add(rotations.get("U"));
		instructions.add(rotations.get("R") + "'");
		instructions.add(rotations.get("U") + "'");
		Driver.invoke(instructions);
	}

	static void LTrigger() {
		List<String> instructions = new ArrayList<>();
		instructions.add(rotations.get("L") + "'");
		instructions.add(rotations.get("U") + "'");
		instructions.add(rotations.get("L"));
		instructions.add(rotations.get("U"));
		Driver.invoke(instructions);
	}

	static void Top2RightEdge() {
		List<String> instructions = new ArrayList<>();
		instructions.add(rotations.get("U"));
		instructions.add(rotations.get("R"));
		instructions.add(rotations.get("U") + "'");
		instructions.add(rotations.get("R") + "'");
		instructions.add(rotations.get("U") + "'");
		instructions.add(rotations.get("F") + "'");
		instructions.add(rotations.get("U"));
		instructions.add(rotations.get("F"));
		Driver.invoke(instructions);
	}

	static void Top2LeftEdge() {
		List<String> instructions = new ArrayList<>();
		instructions.add(rotations.get("U") + "'");
		instructions.add(rotations.get("L") + "'");
		instructions.add(rotations.get("U"));
		instructions.add(rotations.get("L"));
		instructions.add(rotations.get("U"));
		instructions.add(rotations.get("F"));
		instructions.add(rotations.get("U") + "'");
		instructions.add(rotations.get("F") + "'");
		Driver.invoke(instructions);

	}

	static void WhiteCross() {
		Face white = Driver.solved.faces[WHITE];
		Cubie cross[] = { white.cubie[T], white.cubie[L], white.cubie[R], white.cubie[B] };
		for (Cubie edge : cross) {
			moveToFront(edge);
		}
	}
	static void rotateRelevant(Coordinate expected,boolean again){
			String mod = "";
			if(again)
				mod = "2";
			switch(expected.cubie){
				case T: Driver.execute(rotations.get("U")+mod);break;
				case B: Driver.execute(rotations.get("D")+mod);break;	
				case L: Driver.execute(rotations.get("L")+mod);break;	
				case R: Driver.execute(rotations.get("R")+mod);break;	

				// case L: Driver.execute(rotations.get((oppositeFace(expected.face) != YELLOW ? "L" : "R")) + mod); break;
				// case R: Driver.execute(rotations.get((oppositeFace(expected.face) != YELLOW ? "R" : "L")) + mod); break;
			}
	}
	static void moveToFront(Cubie edge){
		// edge needed to solve
		System.out.println("edge to solve : "+ edge.fulldetail());
		Coordinate currentPosition = Driver.cube.findEdge(edge);
		Coordinate expectedPosition = Driver.solved.findEdge(edge);
		System.out.println("current position of cube : "+currentPosition);
		System.out.println("expected position of cube : "+expectedPosition);
		switchFace(expectedPosition.face);
		//check if face is yellow
		if(isOppositeColor(currentPosition.face, expectedPosition.face))
		{
			// System.out.println("face is opposite? => true");
			while(currentPosition.cubie != getCorrectPosition(expectedPosition)){
				// System.out.println("not in position -> "+getCorrectPosition(expectedPosition));
				// System.out.println("rather => "+currentPosition.cubie);
				Driver.execute(rotations.get("B"));
				currentPosition = Driver.cube.findEdge(edge);
			}
			if(currentPosition.cubie == getCorrectPosition(expectedPosition)){
				// System.out.println("updated position :"+currentPosition.cubie);
				// Driver.execute(rotations.get("U"),);
				rotateRelevant(expectedPosition, true);
			}
		}
		else{
			System.out.println("face is opposite? => false");
			if(currentPosition.cubie == leftFace(expectedPosition.face)){
				System.out.println("face is left");
			}
		}
	}
	public static int leftFace(int face){
		int left_face;
		switch (face){
			case WHITE : left_face = GREEN; break;
			case YELLOW : left_face = BLUE; break;
			case RED : left_face = GREEN; break;
			case ORANGE : left_face = GREEN; break;
			case GREEN : left_face = YELLOW; break;
			case BLUE : left_face = WHITE; break;
			default : left_face = GREEN; break;
		}
		return left_face;
	}
	public static int getCorrectPosition(Coordinate expected){
		if(expected.cubie == L)return R;
		if(expected.cubie == R)return L;
		if(expected.face == ORANGE || expected.face == RED){
			if(expected.cubie == T) return B;
			if(expected.cubie == B) return T;
		}
		return expected.cubie;
	}
	public static boolean isOppositeColor(int current, int expected){
		return (current == oppositeFace(expected));
	}
	// static void moveToFront(Cubie edge) {
	// 	Coordinate start = Driver.cube.findEdge(edge);
	// 	Coordinate dest = Driver.solved.findEdge(edge);
	// 	System.out.println("position of : " + edge.fulldetail());
	// 	System.out.println(start);
	// 	switchFace(start.face);
	// 	currentFace();
	// 	// Driver.execute("U");
	// 	// Driver.execute(rotations.get("U"));
	// 	// Driver.execute(rotations.get("U"));
	// 	// Driver.execute(rotations.get("U"));
	// 	if (true) {
	// 		System.out.println("test if start face equals dest face?");
	// 		if (start.face == dest.face) {
	// 			System.out.println("true");
	// 			// while (start.cubie != dest.cubie) {
	// 			// System.out.println(start.cubie + "," + dest.cubie);
	// 			// start = Driver.cube.findEdge(edge);
	// 			// Driver.execute(rotations.get("F"));
	// 			// }
	// 		} else {
	// 			System.out.println("false");
	// 			System.out.println("test is front face yellow?");
	// 			if (frontface != YELLOW) {
	// 				System.out.println("false");
	// 				System.out.println("current edge being found adjacent color => "+edge.adjacents[0].colorString());
	// 				System.out.println("color of face where cubie is situated => "+Driver.cube.faces[start.face].center);
	// 				if (edge.adjacents[0].colorString().equals(Driver.cube.faces[start.face].center.colorString())) {
	// 					List<String> instructions = new ArrayList<>();
	// 					switch (start.cubie) {
	// 						case T:
	// 							instructions.add(rotations.get("F"));
	// 							instructions.add(rotations.get("U") + "'");
	// 							instructions.add(rotations.get("R"));
	// 							instructions.add(rotations.get("U"));
	// 							break;
	// 						case R:
	// 							// instructions.add(rotations.get("F"));
	// 							instructions.add(rotations.get("U") + "'");
	// 							instructions.add(rotations.get("R"));
	// 							instructions.add(rotations.get("U"));
	// 							break;
	// 						case L:
	// 							// instructions.add(rotations.get("F"));
	// 							instructions.add(rotations.get("U"));
	// 							instructions.add(rotations.get("L") + "'");
	// 							instructions.add(rotations.get("U") + "'");
	// 							break;
	// 						case B:
	// 							instructions.add(rotations.get("F"));
	// 							instructions.add(rotations.get("U"));
	// 							instructions.add(rotations.get("L") + "'");
	// 							instructions.add(rotations.get("U") + "'");
	// 							break;
	// 						default:
	// 							break;
	// 					}
	// 					Driver.invoke(instructions);
	// 				}
	// 				if (frontface == GREEN) {
	// 					System.out.println("its green");
	// 					if (start.cubie == dest.cubie) {
	// 						Driver.execute(rotations.get("U")+"'");
	// 						// Top2RightEdge();
	// 					}
	// 				}
	// 			} else {
	// 			System.out.println("true");
	// 				if (!Driver.cube.cubieAt(new Coordinate(YELLOW, start.cubie)).colorString()
	// 						.equals(edge.colorString())) {
	// 					switch (start.cubie) {
	// 						case T:
	// 							Driver.execute(rotations.get("U") + 2);
	// 							break;
	// 						case B:
	// 							Driver.execute(rotations.get("D") + 2);
	// 							break;
	// 						case L:
	// 							Driver.execute(rotations.get("L") + 2);
	// 							break;
	// 						case R:
	// 							Driver.execute(rotations.get("R") + 2);
	// 							break;
	// 						default:
	// 							break;
	// 					}
	// 				} else {
	// 					int counterpart = (start.cubie == L ? R : L);
	// 					while (Driver.cube.cubieAt(new Coordinate(WHITE, counterpart)).colorString()
	// 							.equals(edge.colorString())) {
	// 						System.out.println("cubie to replace"
	// 								+ Driver.cube.cubieAt(new Coordinate(WHITE, counterpart)).fulldetail());
	// 						Driver.execute(rotations.get("B"));
	// 					}
	// 					switch (start.cubie) {
	// 						case R:
	// 							Driver.execute(rotations.get("R") + 2);
	// 							break;
	// 						case L:
	// 							Driver.execute(rotations.get("L") + 2);
	// 							break;
	// 						default:
	// 							break;
	// 					}
	// 				}
	// 			}
	// 		}
	// 	}
	// }

	static void getCross(Face f) {
		int diff[] = { 1, 3, 4, 6 };
		// for(int val:diff){
		for (int i = 0; i < diff.length; i++) {
			Coordinate c = Driver.cube.findEdge(f.cubie[diff[i]]);
			// diff[i] = (c.face.equals (Driver.cube.faces[c.face].center.colorString())?
			// c.cubie:null);
		}
		// }
	}

	static void currentFace() {
		System.out.println("front face: " + Driver.cube.faces[frontface].center.colorString());
	}

	static void simplesolve(List<String> instructions) {
		switchFace(GREEN);
		Driver.solution = true;
		Driver.moves = 0;
		RTrigger();
		switchFace(BLUE);
		RTrigger();
		WhiteCross();
		// Cubie find = Driver.solved.faces[WHITE].cubie[T];
		// System.out.println("position of : "+find.fulldetail());
		// System.out.println(findEdge(find));
		// System.out.println(Driver.cube.faces[0].cubie[3].fulldetail());
		// Driver.execute(rotations.get("R"));
		// Collections.reverse(instructions);
		// for(String instr : instructions){
		// if(instr.contains("\'")){
		// Driver.execute(instr.replace("\'", "2"));
		// Driver.execute(instr);
		// }
		// else if(instr.contains("2")){
		// Driver.execute(instr);
		// }
		// else
		// Driver.execute(instr+'\'');
		// }
	}

}
