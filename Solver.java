
// import java.lang.annotation.Retention;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// import javax.swing.text.Position;

// import sun.net.www.content.audio.basic;
// import sun.reflect.generics.tree.Tree;

/**
 *
 * @author Abduraghmaan G
 */
public class Solver {

	public int frontface = 0;
	public final int WHITE = 0;
	public final int RED = 1;
	public final int BLUE = 2;
	public final int GREEN = 3;
	public final int YELLOW = 4;
	public final int ORANGE = 5;

	public final int TL = 0;
	public final int T = 1;
	public final int TR = 2;
	public final int L = 3;
	public final int R = 4;
	public final int BL = 5;
	public final int B = 6;
	public final int BR = 7;

	public HashMap<String, String> rotations;

	public int oppositeFace(int face) {
		int opposite_face;
		switch (face) {
			case WHITE:
				opposite_face = YELLOW;
				break;
			case YELLOW:
				opposite_face = WHITE;
				break;
			case RED:
				opposite_face = ORANGE;
				break;
			case ORANGE:
				opposite_face = RED;
				break;
			case GREEN:
				opposite_face = BLUE;
				break;
			case BLUE:
				opposite_face = GREEN;
				break;
			default:
				opposite_face = YELLOW;
				break;
		}
		return opposite_face;
	}

	public HashMap<Integer, Runnable> orient = new HashMap<Integer, Runnable>() {
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

	public void switchFace(int f) {
		frontface = f;
		// System.out.println("gihihihihihihhhhhiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
		orient.get(frontface).run();
	}

	void RTrigger() {
		List<String> instructions = new ArrayList<>();
		instructions.add(rotations.get("R"));
		instructions.add(rotations.get("U"));
		instructions.add(rotations.get("R") + "'");
		instructions.add(rotations.get("U") + "'");
		Driver.invoke(instructions);
	}

	void LTrigger() {
		List<String> instructions = new ArrayList<>();
		instructions.add(rotations.get("L") + "'");
		instructions.add(rotations.get("U") + "'");
		instructions.add(rotations.get("L"));
		instructions.add(rotations.get("U"));
		Driver.invoke(instructions);
	}

	void Top2RightEdge() {
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

	void Top2LeftEdge() {
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

	void WhiteCross() {
		Face white = Driver.solved.faces[WHITE];
		Cubie cross[] = { white.cubie[T], white.cubie[L], white.cubie[R], white.cubie[B] };
		int counter = 0;
		// for(int i = 0; i < 4;i++){
		// Coordinate currentPosition = Driver.cube.findEdge(cross[i%4]);
		// Coordinate expectedPosition = Driver.solved.findEdge(cross[i%4]);
		// if(expectedPosition.equals(currentPosition)){
		// counter++;
		// if(counter == 4)
		// break;
		// }else{
		while (!testCross(cross)) {
			moveToFront(cross[0]);
			moveToFront(cross[1]);
			moveToFront(cross[2]);
			moveToFront(cross[3]);
		}

		System.out.println("white cross solved : " + testCross(cross));
		// i = 0;
		// }
		// i = 0;

		// }
		// for(Cubie edge : cross) {
		// moveToFront(edge);
		// }
	}

	boolean testCross(Cubie cross[]) {
		for (int i = 0; i < 4; i++) {
			Coordinate currentPosition = Driver.cube.findEdge(cross[i % 4]);
			Coordinate expectedPosition = Driver.solved.findEdge(cross[i % 4]);
			// System.out.println(currentPosition+" vs "+expectedPosition);
			if (expectedPosition.face != currentPosition.face || expectedPosition.cubie != currentPosition.cubie)
				return false;
		}
		return true;
	}

	void rotateRelevant(Coordinate expected, boolean again, boolean reverse) {
		String mod = "";
		if (again)
			mod = "2";
		else if (reverse) {
			mod = "'";
		}
		// System.out.println(expected.cubie);
		switch (expected.cubie) {
			case T:
				Driver.execute(rotations.get("U") + mod);
				break;
			case B:
				Driver.execute(rotations.get("D") + mod);
				break;
			case L:
				Driver.execute(rotations.get("L") + mod);
				break;
			case R:
				Driver.execute(rotations.get("R") + mod);
				break;

			// case L: Driver.execute(rotations.get((oppositeFace(expected.face) != YELLOW ?
			// "L" : "R")) + mod); break;
			// case R: Driver.execute(rotations.get((oppositeFace(expected.face) != YELLOW ?
			// "R" : "L")) + mod); break;
		}
	}

	void moveToFront(Cubie edge) {
		// edge needed to solve
		// System.out.println("edge to solve : "+ edge.fulldetail());
		Coordinate currentPosition = Driver.cube.findEdge(edge);
		Coordinate expectedPosition = Driver.solved.findEdge(edge);
		switchFace(expectedPosition.face);
		while (currentPosition.face != expectedPosition.face || currentPosition.cubie != expectedPosition.cubie) {
			if (isOppositeColor(currentPosition.face, expectedPosition.face)) {
				if (currentPosition.cubie != getCorrectPosition(expectedPosition))
					Driver.execute(rotations.get("B"));
				currentPosition = Driver.cube.findEdge(edge);
				if (currentPosition.cubie == getCorrectPosition(expectedPosition))
					rotateRelevant(expectedPosition, true, false);
			} else {
				if (currentPosition.face == getLeftFace(expectedPosition.face))
					wcLeftFace(expectedPosition, currentPosition);
				else if (currentPosition.face == getRightFace(expectedPosition.face))
					wcRightFace(expectedPosition, currentPosition);
				else if (currentPosition.face == getTopFace(expectedPosition.face))
					wcTopFace(expectedPosition, currentPosition);
				else if (currentPosition.face == getBottomFace(expectedPosition.face))
					wcBottomFace(expectedPosition, currentPosition);
				else {
					switch (currentPosition.cubie) {
						case T:
							Driver.execute(rotations.get("U") + 2);
							break;
						case B:
							Driver.execute(rotations.get("D") + 2);
							break;
						case L:
							Driver.execute(rotations.get("L") + 2);
							break;
						case R:
							Driver.execute(rotations.get("R") + 2);
							break;
					}
					break;
				}
			}
			currentPosition = Driver.cube.findEdge(edge);
		}
	}

	public void wcBottomFace(Coordinate expectedPosition, Coordinate currentPosition) {
		switch (currentPosition.cubie) {
			case R: // R B
				Driver.execute(rotations.get("R") + "'");
				Driver.execute(rotations.get("B"));
				Driver.execute(rotations.get("R"));
				break;
			case L: // L' B
				Driver.execute(rotations.get("L"));
				Driver.execute(rotations.get("B"));
				Driver.execute(rotations.get("L"));
				break;
			case B:// B
				Driver.execute(rotations.get("B"));
				break;
			case T: // F L F'
				Driver.execute(rotations.get("F"));
				Driver.execute(rotations.get("L"));
				Driver.execute(rotations.get("F") + "'");
				break;
		}
	}

	public void wcTopFace(Coordinate expectedPosition, Coordinate currentPosition) {
		switch (currentPosition.cubie) {
			case R: // R B
				Driver.execute(rotations.get("R"));
				Driver.execute(rotations.get("B"));
				Driver.execute(rotations.get("R") + "'");
				break;
			case L: // L' B
				Driver.execute(rotations.get("L") + "'");
				Driver.execute(rotations.get("B"));
				Driver.execute(rotations.get("L"));
				break;
			case B:// F R F'
				Driver.execute(rotations.get("F"));
				Driver.execute(rotations.get("R"));
				Driver.execute(rotations.get("F") + "'");
				break;
			case T:// B
				Driver.execute(rotations.get("B"));
				break;
		}
	}

	public void wcRightFace(Coordinate expectedPosition, Coordinate currentPosition) {
		if (expectedPosition.cubie == L) {
			switch (currentPosition.cubie) {
				case T: // F U F'
					Driver.execute(rotations.get("F"));
					Driver.execute(rotations.get("U"));
					Driver.execute(rotations.get("F") + "'");
					break;
				case B: // F' D' F
					Driver.execute(rotations.get("F") + "'");
					Driver.execute(rotations.get("D") + "'");
					Driver.execute(rotations.get("F"));
					break;
				default:
					Driver.execute(rotations.get("R"));
					break;
			}
		} else if (expectedPosition.cubie == R) {
			switch (currentPosition.cubie) {
				case T: // F' U F
					Driver.execute(rotations.get("F") + "'");
					Driver.execute(rotations.get("U"));
					Driver.execute(rotations.get("F"));
					break;
				case B: // F D' F'
					Driver.execute(rotations.get("F"));
					Driver.execute(rotations.get("D") + "'");
					Driver.execute(rotations.get("F") + "'");
					break;
				default:
					Driver.execute(rotations.get("R"));
					break;
			}
		} else if (expectedPosition.cubie == B) {
			switch (currentPosition.cubie) {
				case L:// L' D L
					Driver.execute(rotations.get("R") + "'");
					Driver.execute(rotations.get("D") + "'");
					Driver.execute(rotations.get("R"));
					break;
				case R:// L D
					Driver.execute(rotations.get("R"));
					Driver.execute(rotations.get("D") + "'");
					Driver.execute(rotations.get("R") + "'");
					break;
				case B:
					Driver.execute(rotations.get("D") + "'");
					break;
				case T:// U B U'
					Driver.execute(rotations.get("U") + "'");
					Driver.execute(rotations.get("B"));
					Driver.execute(rotations.get("U"));
					break;
			}
		} else if (expectedPosition.cubie == T) {
			switch (currentPosition.cubie) {
				case L:// L U' L'
					Driver.execute(rotations.get("R"));
					Driver.execute(rotations.get("U"));
					Driver.execute(rotations.get("R") + "'");
					break;
				case R:// L' U'
					Driver.execute(rotations.get("R") + "'");
					Driver.execute(rotations.get("U"));
					Driver.execute(rotations.get("R"));
					break;
				case T: // U'
					Driver.execute(rotations.get("U"));
					break;
				case B: // D' B D
					Driver.execute(rotations.get("D"));
					Driver.execute(rotations.get("B"));
					Driver.execute(rotations.get("D") + "'");
					break;
			}
		} else
			rotateRelevant(expectedPosition, false, true);
	}

	public void wcLeftFace(Coordinate expectedPosition, Coordinate currentPosition) {
		if (expectedPosition.cubie == L) {
			switch (currentPosition.cubie) {
				case T: // F U' F'
					Driver.execute(rotations.get("F"));
					Driver.execute(rotations.get("U") + "'");
					Driver.execute(rotations.get("F") + "'");
					break;
				case B: // F' D F
					Driver.execute(rotations.get("F") + "'");
					Driver.execute(rotations.get("D"));
					Driver.execute(rotations.get("F"));
					break;
				default:
					Driver.execute(rotations.get("L"));
					break;
			}
		} else if (expectedPosition.cubie == R) {
			switch (currentPosition.cubie) {
				case T: // F' U' F
					Driver.execute(rotations.get("F") + "'");
					Driver.execute(rotations.get("U") + "'");
					Driver.execute(rotations.get("F"));
					break;
				case B: // F D F'
					Driver.execute(rotations.get("F"));
					Driver.execute(rotations.get("D"));
					Driver.execute(rotations.get("F") + "'");
					break;
				default:
					Driver.execute(rotations.get("L"));
					break;
			}
		} else if (expectedPosition.cubie == B) {
			switch (currentPosition.cubie) {
				case L:// L' D L
					Driver.execute(rotations.get("L") + "'");
					Driver.execute(rotations.get("D"));
					Driver.execute(rotations.get("L"));
					break;
				case R:// L D
					Driver.execute(rotations.get("L"));
					Driver.execute(rotations.get("D"));
					Driver.execute(rotations.get("L") + "'");
					break;
				case B:
					Driver.execute(rotations.get("D"));
					break;
				case T:// U B U'
					Driver.execute(rotations.get("U"));
					Driver.execute(rotations.get("B"));
					Driver.execute(rotations.get("U") + "'");
					break;
			}
		} else if (expectedPosition.cubie == T) {
			switch (currentPosition.cubie) {
				case L:
					// L U' L'
					Driver.execute(rotations.get("L"));
					Driver.execute(rotations.get("U") + "'");
					Driver.execute(rotations.get("L") + "'");
					break;
				case R:
					// L' U'
					Driver.execute(rotations.get("L") + "'");
					Driver.execute(rotations.get("U") + "'");
					Driver.execute(rotations.get("L"));
					break;
				case T: // U'
					Driver.execute(rotations.get("U") + "'");
					break;
				case B: // D' B D
					Driver.execute(rotations.get("D") + "'");
					Driver.execute(rotations.get("B"));
					Driver.execute(rotations.get("D"));
					break;
			}
		} else
			rotateRelevant(expectedPosition, false, true);
	}

	public int getLeftFace(int face) {
		int l_face;
		switch (face) {
			case WHITE:
				l_face = GREEN;
				break;
			case YELLOW:
				l_face = BLUE;
				break;
			case RED:
				l_face = GREEN;
				break;
			case ORANGE:
				l_face = GREEN;
				break;
			case GREEN:
				l_face = YELLOW;
				break;
			case BLUE:
				l_face = WHITE;
				break;
			default:
				l_face = GREEN;
				break;
		}
		return l_face;
	}

	public int getRightFace(int face) {
		int r_face;
		switch (face) {
			case WHITE:
				r_face = BLUE;
				break;
			case YELLOW:
				r_face = GREEN;
				break;
			case RED:
				r_face = BLUE;
				break;
			case ORANGE:
				r_face = BLUE;
				break;
			case GREEN:
				r_face = WHITE;
				break;
			case BLUE:
				r_face = YELLOW;
				break;
			default:
				r_face = BLUE;
				break;
		}
		return r_face;
	}

	public int getTopFace(int face) {
		int t_face;
		switch (face) {
			case WHITE:
				t_face = ORANGE;
				break;
			case YELLOW:
				t_face = ORANGE;
				break;
			case RED:
				t_face = WHITE;
				break;
			case ORANGE:
				t_face = YELLOW;
				break;
			case GREEN:
				t_face = ORANGE;
				break;
			case BLUE:
				t_face = ORANGE;
				break;
			default:
				t_face = ORANGE;
				break;
		}
		return t_face;
	}

	public int getBottomFace(int face) {
		int b_face;
		switch (face) {
			case WHITE:
				b_face = RED;
				break;
			case YELLOW:
				b_face = RED;
				break;
			case RED:
				b_face = YELLOW;
				break;
			case ORANGE:
				b_face = WHITE;
				break;
			case GREEN:
				b_face = RED;
				break;
			case BLUE:
				b_face = RED;
				break;
			default:
				b_face = RED;
				break;
		}
		return b_face;
	}

	public int getCorrectPosition(Coordinate expected) {
		if (expected.cubie == L)
			return R;
		if (expected.cubie == R)
			return L;
		if (expected.face == ORANGE || expected.face == RED) {
			if (expected.cubie == T)
				return B;
			if (expected.cubie == B)
				return T;
		}
		return expected.cubie;
	}

	public boolean isOppositeColor(int current, int expected) {
		return (current == oppositeFace(expected));
	}

	// void getCross(Face f) {
	// int diff[] = { 1, 3, 4, 6 };
	// // for(int val:diff){
	// for (int i = 0; i < diff.length; i++) {
	// Coordinate c = Driver.cube.findEdge(f.cubie[diff[i]]);
	// // diff[i] = (c.face.equals (Driver.cube.faces[c.face].center.colorString())?
	// // c.cubie:null);
	// }
	// // }
	// }

	void currentFace() {
		System.out.println("front face: " + Driver.cube.faces[frontface].center.colorString());
	}

	void simplesolve(List<String> instructions) {
		Driver.solution = true;
		Driver.moves = 0;
		Driver.instructions.clear();
		WhiteCross();
		// System.out.println(Driver.instructions);
		// System.out.println(extend());
		Driver.instructions = Driver.summarise(Driver.instructions);
		Driver.moves = Driver.instructions.size();
		System.out.println(Driver.instructions);
	}

}
