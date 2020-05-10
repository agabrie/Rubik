package rubik;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Abduraghmaan G
 */
public class Solver {

	// public Color frontface = Color.WHITE;

	// public HashMap<String, String> rotations;
	// Control controller;
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

	// public HashMap<Enum<?>, Runnable> orient = new HashMap<Enum<?>, Runnable>() {
	// private static final long serialVersionUID = 1L;

	// {
	// put(Color.WHITE, () -> {
	// rotations = new HashMap<>();
	// rotations.put("U", "U");
	// rotations.put("L", "L");
	// rotations.put("R", "R");
	// rotations.put("D", "D");
	// rotations.put("F", "F");
	// rotations.put("B", "B");
	// });
	// put(Color.YELLOW, () -> {
	// rotations = new HashMap<>();
	// rotations.put("U", "U");
	// rotations.put("L", "R");
	// rotations.put("R", "L");
	// rotations.put("D", "D");
	// rotations.put("F", "B");
	// rotations.put("B", "F");
	// });
	// put(Color.GREEN, () -> {
	// rotations = new HashMap<>();
	// rotations.put("U", "U");
	// rotations.put("L", "B");
	// rotations.put("R", "F");
	// rotations.put("D", "D");
	// rotations.put("F", "L");
	// rotations.put("B", "R");
	// });
	// put(Color.BLUE, () -> {
	// rotations = new HashMap<>();
	// rotations.put("U", "U");
	// rotations.put("L", "F");
	// rotations.put("R", "B");
	// rotations.put("D", "D");
	// rotations.put("F", "R");
	// rotations.put("B", "L");
	// });
	// put(Color.ORANGE, () -> {
	// rotations = new HashMap<>();
	// rotations.put("U", "B");
	// rotations.put("L", "L");
	// rotations.put("R", "R");
	// rotations.put("D", "F");
	// rotations.put("F", "U");
	// rotations.put("B", "D");
	// });
	// put(Color.RED, () -> {
	// rotations = new HashMap<>();
	// rotations.put("U", "F");
	// rotations.put("L", "L");
	// rotations.put("R", "R");
	// rotations.put("D", "B");
	// rotations.put("F", "D");
	// rotations.put("B", "U");
	// });
	// }
	// };

	// public void Control.switchFace(Color f) {
	// frontface = f;
	// orient.get(frontface).run();
	// }

	void RTrigger() {
		List<String> instructions = new ArrayList<>();
		Control.R();
		Control.U();
		Control.Ri();
		Control.Ui();
		// Driver.invoke(instructions);
	}

	void LTrigger() {
		List<String> instructions = new ArrayList<>();
		Control.Li();
		Control.Ui();
		Control.L();
		Control.U();
		// Driver.invoke(instructions);
	}

	void Top2RightEdge() {
		// U R U' R' U' F' U F
		Control.U();
		Control.R();
		Control.Ui();
		Control.Ri();
		Control.Ui();
		Control.Fi();
		Control.U();
		Control.F();
	}

	void Top2LeftEdge() {
		// U' L' U L U F U' F'
		Control.Ui();
		Control.Li();
		Control.U();
		Control.L();
		Control.U();
		Control.F();
		Control.Ui();
		Control.Fi();
	}

	void Bottom2LeftEdge() {
		// D L D' L' D' F' D F
		Control.D();
		Control.L();
		Control.Di();
		Control.Li();
		Control.Di();
		Control.Fi();
		Control.D();
		Control.F();
	}

	void Bottom2RightEdge() {
		// D' R' D R D F D' F'
		Control.Di();
		Control.Ri();
		Control.D();
		Control.R();
		Control.D();
		Control.F();
		Control.Di();
		Control.Fi();
	}

	void Left2TopEdge() {
		// B U B' U' B' L' B L
		Control.L();
		Control.U();
		Control.Li();
		Control.Ui();
		Control.Li();
		Control.Fi();
		Control.L();
		Control.F();
	}

	void Left2BottomEdge() {
		// B' D' B D B L B' L'
		Control.Di();
		Control.L();
		Control.D();
		Control.L();
		Control.F();
		Control.Li();
		Control.Fi();
	}

	void Right2TopEdge() {
		// B' U' B U B R B' R'
		Control.Ri();
		Control.Ui();
		Control.R();
		Control.U();
		Control.R();
		Control.F();
		Control.Ri();
		Control.Fi();
	}

	void Right2BottomEdge() {
		// B D B' D' B' R' B R
		Control.R();
		Control.D();
		Control.Ri();
		Control.Di();
		Control.Ri();
		Control.Fi();
		Control.R();
		Control.F();
	}

	void WhiteCross() {
		Face white = Driver.solved.faces[Color.WHITE.value];
		Cubie cross[] = { white.getCubie(Relation.T), white.getCubie(Relation.L), white.getCubie(Relation.R),
				white.getCubie(Relation.B) };
		while (!testPosition(cross)) {
			for (Cubie edge : cross) {
				moveToFront(edge);
			}
		}
		if (Driver.output && Driver.parts)
			System.out.println("white cross solved : " + testPosition(cross));
	}

	void WhiteCorners() {
		Face white = Driver.solved.faces[Color.WHITE.value];
		Cubie cross[] = { white.getCubie(Relation.TL), white.getCubie(Relation.TR), white.getCubie(Relation.BL),
				white.getCubie(Relation.BR) };
		while (!testPosition(cross)) {
			for (Cubie corner : cross) {
				FLCorner(corner);
			}
		}
		if (Driver.output && Driver.parts)
			System.out.println("white corners solved : " + testPosition(cross));
	}

	void FirstLayer() {
		WhiteCross();
		if (Driver.parts)
			displayResults("White Cross");
		WhiteCorners();
		if (Driver.parts)
			displayResults("White Corners");
	}

	void SecondLayer() {
		Face orange = Driver.solved.faces[Color.ORANGE.value];
		Face green = Driver.solved.faces[Color.GREEN.value];
		Face red = Driver.solved.faces[Color.RED.value];
		Face blue = Driver.solved.faces[Color.BLUE.value];
		Cubie cross[] = { orange.getCubie(Relation.L), orange.getCubie(Relation.R), green.getCubie(Relation.T),
				green.getCubie(Relation.B), red.getCubie(Relation.L), red.getCubie(Relation.R),
				blue.getCubie(Relation.T), blue.getCubie(Relation.B), };

		while (!testPosition(cross)) {
			for (Cubie edge : cross) {
				SLEdge(edge);
			}
		}
		if (Driver.output && Driver.parts)
			System.out.println("second layer solved : " + testPosition(cross));
	}

	void YellowCross() {
		Face yellow = Driver.solved.faces[Color.YELLOW.value];
		Cubie cross[] = { yellow.getCubie(Relation.T), yellow.getCubie(Relation.R), yellow.getCubie(Relation.B),
				yellow.getCubie(Relation.L) };
		orientYC();
		Coordinate ep, cp;

		Control.switchFace(Color.ORANGE);
		ep = Driver.solved.findCoordinate(cross[0]);
		while (!ep.equals(cp = Driver.cube.findCoordinate(cross[0]))) {
			Control.U();
		}
		Coordinate greenP = Driver.cube.findCoordinate(cross[1]);
		switch (greenP.cubie) {
			case L:
				swapRightAdjacents(Color.ORANGE);
				Control.U();
				break;
			case B:
				swapRightAdjacents(Color.RED);
				break;
			default:
				break;
		}
		Coordinate redP = Driver.cube.findCoordinate(cross[2]);
		if (redP.cubie == Relation.L) {
			swapLeftAdjacents(Color.RED);
		}

		if (Driver.output && Driver.parts)
			System.out.println("yellow cross confirmed : " + testPosition(cross));

	}

	void swapLeftAdjacents(Color f) {
		Control.switchFace(f);
		switch (f) {
			default:
				break;
			case ORANGE: {
				// R U R' U R U2 R' U
				Control.R();
				Control.U();
				Control.Ri();
				Control.U();
				Control.R();
				Control.U2();
				Control.Ri();
				Control.U();
			}
				break;
			case RED: {
				// L D L' D L D2 L' D
				Control.L();
				Control.D();
				Control.Li();
				Control.D();
				Control.L();
				Control.D2();
				Control.Li();
				Control.D();
			}
				break;
			case GREEN: {
				// U L U' L U L2 U' L
				Control.U();
				Control.L();
				Control.Ui();
				Control.L();
				Control.U();
				Control.L2();
				Control.Ui();
				Control.L();

			}
				break;
			case BLUE: {
				// D R D' R D R2 D' R
				Control.D();
				Control.R();
				Control.Di();
				Control.R();
				Control.D();
				Control.R2();
				Control.Di();
				Control.R();
			}
				break;
		}
	}

	void swapRightAdjacents(Color f) {
		Control.switchFace(f);
		switch (f) {
			default:
				break;
			case ORANGE: {
				// L' U' L U' L' U2 L U'
				Control.Li();
				Control.Ui();
				Control.L();
				Control.Ui();
				Control.Li();
				Control.U2();
				Control.L();
				Control.Ui();

			}
				break;
			case RED: {
				// R' D' R D' R' D2 R D'
				Control.Ri();
				Control.Di();
				Control.R();
				Control.Di();
				Control.Ri();
				Control.D2();
				Control.R();
				Control.Di();
			}
				break;
			case GREEN: {
				// D' L' D L' D' L2 D L'
				Control.Di();
				Control.Li();
				Control.D();
				Control.Li();
				Control.Di();
				Control.L2();
				Control.D();
				Control.Li();
			}
				break;
			case BLUE: {
				// U' R' U R' U' R2 U R'
				Control.Ui();
				Control.Li();
				Control.U();
				Control.Li();
				Control.Ui();
				Control.L2();
				Control.U();
				Control.Li();
			}
		}
	}

	Relation getCorrectLeft(Coordinate current) {
		Relation relevant;
		switch (current.cubie) {
			case L:
				relevant = Relation.B;
				break;
			case B:
				relevant = Relation.R;
				break;
			case R:
				relevant = Relation.T;
				break;
			default:
				relevant = Relation.L;
				break;
		}
		return relevant;
	}

	void FinalLayer() {
		YellowCross();
		if (Driver.parts)
			displayResults("Yellow Cross");
		YellowCorners();
			displayResults("Final Solution");
	}

	void YellowCorners() {
		Face yellow = Driver.solved.faces[Color.YELLOW.value];
		Cubie cross[] = { yellow.getCubie(Relation.TL), yellow.getCubie(Relation.TR), yellow.getCubie(Relation.BL),
				yellow.getCubie(Relation.BR) };
		int i = 0;

		while (i != 4) {

			i = 0;
			Cubie anchor = null;
			for (Cubie corner : cross) {

				if (testCornerPosition(corner)) {
					i++;
					anchor = corner;
				}
			}

			if (i == 1) {
				repositionYellowCorners(anchor);
			} else if (i == 0)
				repositionYellowCorners(cross[0]);
		}
		if (Driver.output && Driver.parts)
			System.out.println("yellow corners positioned!");
		Control.switchFace(Color.ORANGE);
		for (Cubie corner : cross) {

			reorientYellowCorner(corner);
		}
		if (Driver.output && Driver.parts)
			System.out.println("yellow corners oriented!");
		while (!testPosition(cross)) {
			Control.U();
		}
	}

	void reorientYellowCorner(Cubie corner) {
		Coordinate currentPosition = Driver.cube.findCoordinate(corner);
		if (currentPosition.face != Color.YELLOW) {
			while (!(currentPosition.face == Color.ORANGE && currentPosition.cubie == Relation.TR)
					&& !(currentPosition.face == Color.BLUE && currentPosition.cubie == Relation.TR)) {
				Control.U();
				currentPosition = Driver.cube.findCoordinate(corner);
			}
			while (currentPosition.face != Color.YELLOW) {
				// R' D' R D
				Control.Ri();
				Control.Di();
				Control.R();
				Control.D();
				currentPosition = Driver.cube.findCoordinate(corner);
			}
		}
	}

	void repositionYellowCorners(Cubie anchor) {
		Coordinate expectedPosition = Driver.solved.findCoordinate(anchor);
		switch (expectedPosition.cubie) {
			default:
				break;
			case TL:
				Control.switchFace(Color.ORANGE);
				// U R U' L' U R' U' L
				Control.U();
				Control.R();
				Control.Ui();
				Control.Li();

				Control.U();
				Control.Ri();
				Control.Ui();
				Control.L();
				break;
			case TR:
				Control.switchFace(Color.GREEN);
				// L U L' D' L U' L' D
				Control.L();
				Control.U();
				Control.Li();
				Control.Di();

				Control.L();
				Control.Ui();
				Control.Li();
				Control.D();
				break;
			case BL:
				Control.switchFace(Color.BLUE);
				// R D R' U' R D' R' U

				Control.R();
				Control.D();
				Control.Ri();
				Control.Ui();

				Control.R();
				Control.Di();
				Control.Ri();
				Control.U();
				break;
			case BR:
				Control.switchFace(Color.RED);
				// D L D' R' D L' D' R
				Control.D();
				Control.L();
				Control.Di();
				Control.Ri();

				Control.D();
				Control.Li();
				Control.Di();
				Control.R();
				break;
		}
	}

	boolean testCornerPosition(Cubie corner) {
		Coordinate currentPosition = Driver.cube.findCoordinate(corner);
		Coordinate expectedPosition = Driver.solved.findCoordinate(corner);
		boolean oriented = false;
		if (currentPosition.equals(expectedPosition))
			return true;
		switch (expectedPosition.cubie) {
			case TL:
				if (currentPosition.cubie == Relation.TR) {
					oriented = (currentPosition.face == Color.BLUE || currentPosition.face == Color.ORANGE);
				} else
					oriented = false;
				break;
			case TR:
				if (currentPosition.cubie == Relation.TL) {
					oriented = (currentPosition.face == Color.GREEN || currentPosition.face == Color.ORANGE);
				} else
					oriented = false;
				break;
			case BL:

				if (currentPosition.cubie == Relation.BR) {
					oriented = (currentPosition.face == Color.BLUE || currentPosition.face == Color.RED);
				} else
					oriented = false;
				break;
			case BR:
				if (currentPosition.cubie == Relation.BL) {
					oriented = (currentPosition.face == Color.GREEN || currentPosition.face == Color.RED);
				} else
					oriented = false;
				break;
			default:
				break;
		}
		return oriented;
	}

	void orientYC() {
		Control.switchFace(Color.ORANGE);
		int x = 0;
		int i = 0;
		int numYellow;
		while (x++ < 4 && checkYellowCross() != 4) {
			numYellow = checkYellowCross();
			if (numYellow > 1)
				if (Driver.cube.faces[Color.YELLOW.value].cubie[Relation.T.value].color[0] == Color.YELLOW)
					Control.U();
			i = 0;
			while (i < 4 && Driver.cube.faces[Color.YELLOW.value].cubie[Relation.R.value].color[0] != Color.YELLOW) {
				Control.Ui();
				i++;
			}
			// // F R U R' U' F'
			Control.F();
			Control.R();
			Control.U();
			Control.Ri();
			Control.Ui();
			Control.Fi();
		}

	}

	int checkYellowCross() {
		Face yellow = Driver.cube.faces[Color.YELLOW.value];
		Cubie cross[] = { yellow.getCubie(Relation.T), yellow.getCubie(Relation.R), yellow.getCubie(Relation.L),
				yellow.getCubie(Relation.B) };
		int numYellow = 0;
		for (Cubie edge : cross) {
			if (edge.color[0] == Color.YELLOW)
				numYellow++;
		}
		return numYellow;
	}

	void SLEdge(Cubie edge) {
		Coordinate currentPosition = Driver.cube.findCoordinate(edge);
		Coordinate expectedPosition = Driver.solved.findCoordinate(edge);
		while (!currentPosition.equals(expectedPosition)) {
			Control.switchFace(expectedPosition.face);
			if (currentPosition.face != expectedPosition.face) {
				if ((currentPosition.face == Color.GREEN && currentPosition.cubie == Relation.L)
						|| (currentPosition.face == Color.BLUE && currentPosition.cubie == Relation.R)
						|| (currentPosition.face == Color.ORANGE && currentPosition.cubie == Relation.T)
						|| (currentPosition.face == Color.RED && currentPosition.cubie == Relation.B)) {
					Control.switchFace(Color.WHITE);
					Driver.execute("B");
				} else {
					Control.switchFace(currentPosition.face);
					switch (currentPosition.face) {
						case RED:
							if (currentPosition.cubie == Relation.R)
								Bottom2RightEdge();
							else
								Bottom2LeftEdge();
							break;
						case ORANGE:
							if (currentPosition.cubie == Relation.R)
								Top2RightEdge();
							else
								Top2LeftEdge();
							break;
						case BLUE:
							if (currentPosition.cubie == Relation.T)
								Right2TopEdge();
							else
								Right2BottomEdge();
							break;
						case GREEN:
							if (currentPosition.cubie == Relation.T)
								Left2TopEdge();
							else
								Left2BottomEdge();
							break;
						default:
							return;
					}

				}
			} else {
				switch (currentPosition.cubie) {
					case T:
						switch (expectedPosition.cubie) {
							case L:
								Top2LeftEdge();
								break;
							case R:
								Top2RightEdge();
								break;
							default:
								if (expectedPosition.face == Color.GREEN)
									Left2TopEdge();
								else
									Right2TopEdge();
								break;
						}
						break;
					case B:
						switch (expectedPosition.cubie) {
							case L:
								Bottom2LeftEdge();
								break;
							case R:
								Bottom2RightEdge();
								break;
							default:
								if (expectedPosition.face == Color.GREEN)
									Left2BottomEdge();
								else
									Right2BottomEdge();
								break;
						}
						break;
					case L:
						switch (expectedPosition.cubie) {
							case T:
								Left2TopEdge();
								break;
							case B:
								Left2BottomEdge();
								break;
							default:
								if (expectedPosition.face == Color.ORANGE)
									Top2LeftEdge();
								else
									Bottom2LeftEdge();
								break;
						}
						break;
					case R:
						switch (expectedPosition.cubie) {
							case T:
								Right2TopEdge();
								break;
							case B:
								Right2BottomEdge();
								break;
							default:
								if (expectedPosition.face == Color.ORANGE)
									Top2RightEdge();
								else
									Bottom2RightEdge();
								break;
						}
						break;
					default:
						break;
				}
			}

			currentPosition = Driver.cube.findCoordinate(edge);
		}

	}

	void FLCorner(Cubie corner) {
		Coordinate currentPosition = Driver.cube.findCoordinate(corner);
		Coordinate expectedPosition = Driver.solved.findCoordinate(corner);
		Control.switchFace(expectedPosition.face);
		while (!currentPosition.equals(expectedPosition)) {
			switch (expectedPosition.getRelevantFace(currentPosition)) {
				case OPPOSITE:
					if (currentPosition.cubie != getCorrectPosition(expectedPosition))
						Control.B();
					currentPosition = Driver.cube.findCoordinate(corner);
					if (currentPosition.cubie == getCorrectPosition(expectedPosition)) {
						flback(expectedPosition, currentPosition);
						;
					}
					break;
				case LEFT:
					flleft(expectedPosition, currentPosition);
					break;
				case RIGHT:
					flright(expectedPosition, currentPosition);
					break;
				case TOP:
					fltop(expectedPosition, currentPosition);
					break;
				case BOTTOM:
					flbottom(expectedPosition, currentPosition);
					break;
				default:
					switch (currentPosition.cubie) {
						case TR:
							Control.Ui();
							Control.Bi();
							Control.U();
							break;
						case BR:
							Control.D();
							Control.B();
							Control.Di();
							break;
						case TL:
							Control.U();
							Control.B();
							Control.Ui();
							break;
						case BL:
							Control.Di();
							Control.Bi();
							Control.D();
							break;
						default:
							break;
					}
					break;
			}
			currentPosition = Driver.cube.findCoordinate(corner);
		}

	}

	boolean testPosition(Cubie cross[]) {
		for (Cubie c : cross) {
			Coordinate currentPosition = Driver.cube.findCoordinate(c);
			Coordinate expectedPosition = Driver.solved.findCoordinate(c);
			if (!expectedPosition.equals(currentPosition))
				return false;
		}
		return true;
	}

	void flback(Coordinate expectedPosition, Coordinate currentPosition) {
		switch (currentPosition.cubie) {
			case BL:
				// R' U' B2 U R
				Control.Ri();
				Control.Ui();
				Control.B2();
				Control.U();
				Control.R();
				break;
			case BR:
				// L U B2 U' L'
				Control.L();
				Control.U();
				Control.B2();
				Control.Ui();
				Control.Li();
				break;
			case TR:
				// L' D' B2 D L
				Control.Li();
				Control.Di();
				Control.B2();
				Control.D();
				Control.L();
				break;
			case TL:
				// R D B2 D' R'
				Control.R();
				Control.D();
				Control.B2();
				Control.Di();
				Control.Ri();
				break;
			default:
				break;
		}
	}

	void flleft(Coordinate expectedPosition, Coordinate currentPosition) {
		switch (currentPosition.cubie) {
			case TR:// U B U'
				Control.U();
				Control.B();
				Control.Ui();
				break;
			case BR:// D' B' D
				Control.Di();
				Control.Bi();
				Control.D();
				break;
			default:
				Control.B();
				break;
		}
	}

	void flright(Coordinate expectedPosition, Coordinate currentPosition) {
		switch (currentPosition.cubie) {
			case TL:// U' B' U
				Control.Ui();
				Control.Bi();
				Control.U();
				break;
			case BL:// D B D'
				Control.D();
				Control.B();
				Control.Di();
				break;
			default:
				Control.B();
				break;
		}
	}

	void flbottom(Coordinate expectedPosition, Coordinate currentPosition) {
		switch (currentPosition.cubie) {
			case TR:// R' B' R
				Control.Ri();
				Control.Bi();
				Control.R();
				break;
			case TL:// L B L'
				Control.L();
				Control.B();
				Control.Li();
				break;
			default:
				Control.B();
				break;
		}
	}

	void fltop(Coordinate expectedPosition, Coordinate currentPosition) {
		switch (currentPosition.cubie) {
			case TR:
				switch (expectedPosition.cubie) {
					case TR:
						// B R B' R'
						Control.B();
						Control.R();
						Control.Bi();
						Control.Ri();
						break;
					case TL:
						// B2 U B' U'
						Control.B2();
						Control.U();
						Control.Bi();
						Control.Ui();
						break;
					case BR:
						// D B' D' B
						Control.D();
						Control.Bi();
						Control.Di();
						Control.B();
						break;
					case BL:
						// B' L B' L'
						Control.Bi();
						Control.L();
						Control.Bi();
						Control.Li();
						break;
					default:
						break;
				}
				break;
			case TL:
				switch (expectedPosition.cubie) {
					case TR:
						// B2 U' B U
						Control.B2();
						Control.Ui();
						Control.B();
						Control.U();
						break;
					case TL:
						// B R B' R'
						Control.Bi();
						Control.Li();
						Control.B();
						Control.L();
						break;
					case BL:
						// //D B' D' B
						Control.Di();
						Control.B();
						Control.D();
						Control.Bi();
						break;
					case BR:
						// //B' L B' L'
						Control.B();
						Control.Ri();
						Control.B();
						Control.R();
						break;
					default:
						break;
				}
				break;
			case BR:
				Control.R();
				Control.B();
				Control.Ri();
				break;
			case BL:
				Control.Li();
				Control.Bi();
				Control.L();
				break;
		}
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
				switch (mod) {
					case "":
						Control.U();
						break;
					case "'":
						Control.Ui();
						break;
					case "2":
						Control.U2();
						break;
				}
				break;
			case B:
				switch (mod) {
					case "":
						Control.D();
						break;
					case "'":
						Control.Di();
						break;
					case "2":
						Control.D2();
						break;
				}
				break;
			case L:
				switch (mod) {
					case "":
						Control.L();
						break;
					case "'":
						Control.Li();
						break;
					case "2":
						Control.L2();
						break;
				}
				break;
			case R:
				switch (mod) {
					case "":
						Control.R();
						break;
					case "'":
						Control.Ri();
						break;
					case "2":
						Control.R2();
						break;
				}
				break;
			default:
				break;
		}
	}

	void moveToFront(Cubie edge) {
		Coordinate currentPosition = Driver.cube.findCoordinate(edge);
		Coordinate expectedPosition = Driver.solved.findCoordinate(edge);
		Control.switchFace(expectedPosition.face);
		while (!currentPosition.equals(expectedPosition)) {
			switch (expectedPosition.getRelevantFace(currentPosition)) {
				case OPPOSITE:
					if (currentPosition.cubie != getCorrectPosition(expectedPosition))
						Control.B();
					currentPosition = Driver.cube.findCoordinate(edge);
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
							// Driver.execute(rotations.get("U") + 2);
							Control.U2();
							break;
						case B:
							Control.D2();
							// Driver.execute(rotations.get("D") + 2);
							break;
						case L:
							Control.L2();
							// Driver.execute(rotations.get("L") + 2);
							break;
						case R:
							Control.R2();

							// Driver.execute(rotations.get("R") + 2);
							break;
						default:
							break;
					}
					break;
			}
			currentPosition = Driver.cube.findCoordinate(edge);
		}
	}

	public void wcBottomFace(Coordinate expectedPosition, Coordinate currentPosition) {
		switch (currentPosition.cubie) {
			case R: // R B
				Control.Ri();
				Control.B();
				Control.R();
				break;
			case L: // L' B
				Control.L();
				Control.B();
				Control.L();
				break;
			case B:// B
				Control.B();
				break;
			case T: // F L F'
				Control.F();
				Control.L();
				Control.Fi();
				break;
			default:
				break;
		}
	}

	public void wcTopFace(Coordinate expectedPosition, Coordinate currentPosition) {
		switch (currentPosition.cubie) {
			case R: // R B
				Control.R();
				Control.B();
				Control.Ri();
				break;
			case L: // L' B
				Control.Li();
				Control.B();
				Control.L();
				break;
			case B:// F R F'
				Control.F();
				Control.R();
				Control.Fi();
				break;
			case T:// B
				Control.B();
				break;
			default:
				break;
		}
	}

	public void wcRightFace(Coordinate expectedPosition, Coordinate currentPosition) {
		if (expectedPosition.cubie == Relation.L) {
			switch (currentPosition.cubie) {
				case T: // F U F'
					Control.F();
					Control.U();
					Control.Fi();
					break;
				case B: // F' D' F
					Control.Fi();
					Control.Di();
					Control.F();
					break;
				default:
					Control.R();
					break;
			}
		} else if (expectedPosition.cubie == Relation.R) {
			switch (currentPosition.cubie) {
				case T: // F' U F
					Control.Fi();
					Control.U();
					Control.F();
					break;
				case B: // F D' F'
					Control.F();
					Control.Di();
					Control.Fi();
					break;
				default:
					Control.R();
					break;
			}
		} else if (expectedPosition.cubie == Relation.B) {
			switch (currentPosition.cubie) {
				case L:// L' D L
					Control.Ri();
					Control.Di();
					Control.R();
					break;
				case R:// L D
					Control.R();
					Control.Di();
					Control.Ri();
					break;
				case B:
					Control.Di();
					break;
				case T:// U B U'
					Control.Ui();
					Control.B();
					Control.U();
					break;
				default:
					break;
			}
		} else if (expectedPosition.cubie == Relation.T) {
			switch (currentPosition.cubie) {
				case L:// L U' L'
					Control.R();
					Control.U();
					Control.Ri();
					break;
				case R:// L' U'
					Control.Ri();
					Control.U();
					Control.R();
					break;
				case T: // U'
					Control.U();
					break;
				case B: // D' B D
					Control.D();
					Control.B();
					Control.Di();
					break;
				default:
					break;
			}
		} else
			rotateRelevant(expectedPosition, false, true);
	}

	public void wcLeftFace(Coordinate expectedPosition, Coordinate currentPosition) {
		if (expectedPosition.cubie == Relation.L) {
			switch (currentPosition.cubie) {
				case T: // F U' F'
					Control.F();
					Control.Ui();
					Control.Fi();
					break;
				case B: // F' D F
					Control.Fi();
					Control.D();
					Control.F();
					break;
				default:
					Control.L();
					break;
			}
		} else if (expectedPosition.cubie == Relation.R) {
			switch (currentPosition.cubie) {
				case T: // F' U' F
					Control.Fi();
					Control.Ui();
					Control.F();
					break;
				case B: // F D F'
					Control.F();
					Control.D();
					Control.Fi();
					break;
				default:
					Control.L();
					break;
			}
		} else if (expectedPosition.cubie == Relation.B) {
			switch (currentPosition.cubie) {
				case L:// L' D L
					Control.Li();
					Control.D();
					Control.L();
					break;
				case R:// L D
					Control.L();
					Control.D();
					Control.Li();
					break;
				case B:
					Control.D();
					break;
				case T:// U B U'
					Control.U();
					Control.B();
					Control.Ui();
					break;
				default:
					break;
			}
		} else if (expectedPosition.cubie == Relation.T) {
			switch (currentPosition.cubie) {
				case L:
					// L U' L'
					Control.L();
					Control.Ui();
					Control.Li();
					break;
				case R:
					// L' U'
					Control.Li();
					Control.Ui();
					Control.L();
					break;
				case T: // U'
					Control.Ui();
					break;
				case B: // D' B D
					Control.Di();
					Control.B();
					Control.D();
					break;
				default:
					break;
			}
		} else
			rotateRelevant(expectedPosition, false, true);
	}

	public Relation getCorrectPosition(Coordinate expected) {
		if (expected.cubie == Relation.L)
			return Relation.R;
		if (expected.cubie == Relation.R)
			return Relation.L;

		if (expected.cubie == Relation.TL)
			return Relation.TR;
		if (expected.cubie == Relation.TR)
			return Relation.TL;
		if (expected.cubie == Relation.BL)
			return Relation.BR;
		if (expected.cubie == Relation.BR)
			return Relation.BL;

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

	// void currentFace() {
	// 	System.out.println("front face: " + Driver.cube.faces[frontface.value].center.colorString());
	// }

	void displayResults(String display) {
		Driver.instructions = Driver.summarise(Driver.instructions);
		Driver.moves = Driver.instructions.size();
		System.out.println(display + " : ");
		if (Driver.output)
			System.out.println(Driver.cube);
		if (Driver.lined)
			for (String s : Driver.instructions)
				System.out.println(s);
		else
			System.out.println(Driver.instructions);
		System.out.println("Number of moves : " + Driver.moves);
		Driver.instructions.clear();
	}

	void simplesolve(List<String> instructions) {
		Driver.solution = true;
		Driver.moves = 0;
		Driver.instructions.clear();
		FirstLayer();
		SecondLayer();
		if (Driver.parts)
			displayResults("Second Layer");
		FinalLayer();
	}

}
