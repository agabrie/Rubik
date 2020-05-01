package rubik;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Abduraghmaan G
 */
public class Solver {

	public Color frontface = Color.WHITE;

	public HashMap<String, String> rotations;

	public Color oppositeFace(Color face) {
		Color opposite_face;
		switch (face) {
			case WHITE:
				opposite_face = Color.YELLOW;
				break;
			case YELLOW:
				opposite_face = Color.WHITE;
				break;
			case RED:
				opposite_face = Color.ORANGE;
				break;
			case ORANGE:
				opposite_face = Color.RED;
				break;
			case GREEN:
				opposite_face = Color.BLUE;
				break;
			case BLUE:
				opposite_face = Color.GREEN;
				break;
			default:
				opposite_face = Color.YELLOW;
				break;
		}
		return opposite_face;
	}

	public HashMap<Enum<?>, Runnable> orient = new HashMap<Enum<?>, Runnable>() {
		/**
		 *
		 */
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
				// System.out.println(rotations);
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
				// System.out.println(rotations);
			});
			put(Color.RED, () -> {
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

	public void switchFace(Color f) {
		frontface = f;
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
		Face white = Driver.solved.faces[Color.WHITE.value];
		Cubie cross[] = { white.getCubie(Relation.T), white.getCubie(Relation.L), white.getCubie(Relation.R),
				white.getCubie(Relation.B) };
		while (!testCross(cross)) {
			for (Cubie edge : cross) {
				moveToFront(edge);
			}
		}
		System.out.println("white cross solved : " + testCross(cross));
	}

	boolean testCross(Cubie cross[]) {
		for (int i = 0; i < 4; i++) {
			Coordinate currentPosition = Driver.cube.findEdge(cross[i % 4]);
			Coordinate expectedPosition = Driver.solved.findEdge(cross[i % 4]);
			// System.out.println(currentPosition+" vs "+expectedPosition);
			if (!expectedPosition.equals(currentPosition))
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
			default:break;
		}
	}

	void moveToFront(Cubie edge) {
		// edge needed to solve
		// System.out.println("edge to solve : "+ edge.fulldetail());
		Coordinate currentPosition = Driver.cube.findEdge(edge);
		Coordinate expectedPosition = Driver.solved.findEdge(edge);
		switchFace(expectedPosition.face);
		while (currentPosition.face != expectedPosition.face || currentPosition.cubie != expectedPosition.cubie) {
			switch (expectedPosition.getRelevantFace(currentPosition)) {
				case OPPOSITE:
					if (currentPosition.cubie != getCorrectPosition(expectedPosition))
						Driver.execute(rotations.get("B"));
					currentPosition = Driver.cube.findEdge(edge);
					if (currentPosition.cubie == getCorrectPosition(expectedPosition))
						rotateRelevant(expectedPosition, true, false);
					break;
				case LEFT:
					wcLeftFace(expectedPosition, currentPosition);
					break;
				case RIGHT:
					wcRightFace(expectedPosition, currentPosition);
					break;
				case TOP:
					wcTopFace(expectedPosition, currentPosition);
					break;
				case BOTTOM:
					wcBottomFace(expectedPosition, currentPosition);
					break;
				default:
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
						default:break;
					}
					break;
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
			default:break;
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
			default:break;
		}
	}

	public void wcRightFace(Coordinate expectedPosition, Coordinate currentPosition) {
		if (expectedPosition.cubie == Relation.L) {
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
		} else if (expectedPosition.cubie == Relation.R) {
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
		} else if (expectedPosition.cubie == Relation.B) {
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
				default:break;
			}
		} else if (expectedPosition.cubie == Relation.T) {
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
				default:break;
			}
		} else
			rotateRelevant(expectedPosition, false, true);
	}

	public void wcLeftFace(Coordinate expectedPosition, Coordinate currentPosition) {
		if (expectedPosition.cubie == Relation.L) {
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
		} else if (expectedPosition.cubie == Relation.R) {
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
		} else if (expectedPosition.cubie == Relation.B) {
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
					default:break;
			}
		} else if (expectedPosition.cubie == Relation.T) {
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
				default:break;
			}
		} else
			rotateRelevant(expectedPosition, false, true);
	}

	public Relation getCorrectPosition(Coordinate expected) {
		if (expected.cubie == Relation.L)
			return Relation.R;
		if (expected.cubie == Relation.R)
			return Relation.L;
		if (expected.face == Color.ORANGE || expected.face == Color.RED) {
			if (expected.cubie == Relation.T)
				return Relation.B;
			if (expected.cubie == Relation.B)
				return Relation.T;
		}
		return expected.cubie;
	}

	public boolean isOppositeColor(Color current, Color expected) {
		return (current == oppositeFace(expected));
	}

	void currentFace() {
		System.out.println("front face: " + Driver.cube.faces[frontface.value].center.colorString());
	}

	void simplesolve(List<String> instructions) {
		Driver.solution = true;
		Driver.moves = 0;
		Driver.instructions.clear();
		WhiteCross();

		Driver.instructions = Driver.summarise(Driver.instructions);
		Driver.moves = Driver.instructions.size();
		System.out.println(Driver.instructions);
	}

}
