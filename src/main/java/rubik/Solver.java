package rubik;

import java.util.List;

/**
 *
 * @author Abduraghmaan G
 */
public class Solver {
	void RTrigger() {
		Control.R();
		Control.U();
		Control.Ri();
		Control.Ui();
	}

	void LTrigger() {
		Control.Li();
		Control.Ui();
		Control.L();
		Control.U();
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
		
		// System.out.println("Trying White Cross");


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
		System.out.println("Trying White Corners");

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
		// Coordinate ep, cp;

		Control.switchFace(Color.ORANGE);
		// ep = Driver.solved.findCoordinate(cross[0]);
		while (!cross[0].testCoordinate()) {
			Control.U();
		}
		// Coordinate greenP = Driver.cube.findCoordinate(cross[1]);
		switch (cross[1].getCurrentPosition()) {
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
		// Coordinate redP = Driver.cube.findCoordinate(cross[2]);
		
		if (cross[2].getCurrentPosition() == Relation.L) {
			swapLeftAdjacents(Color.RED);
		}

		if (Driver.output && Driver.parts)
			System.out.println("yellow cross confirmed : " + testPosition(cross));

	}
	void swapTopEdgeClockwise(){
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
	void swapBottomEdgeClockwise(){
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
	void swapLeftEdgeClockwise(){
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
	void swapRightEdgeClockwise(){
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
	void swapLeftAdjacents(Color f) {
		Control.switchFace(f);
		switch (f) {
			default:
				break;
			case ORANGE:
				swapTopEdgeClockwise();
				break;
			case RED:
				swapBottomEdgeClockwise();
				break;
			case GREEN: 
			swapLeftEdgeClockwise();
				break;
			case BLUE: 
			swapRightEdgeClockwise();
				break;
		}
	}
	void swapTopEdgeCounter(){
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
	void swapBottomEdgeCounter(){
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
	void swapLeftEdgeCounter(){
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
	void swapRightEdgeCounter(){
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

	void swapRightAdjacents(Color f) {
		Control.switchFace(f);
		switch (f) {
			default:
				break;
			case ORANGE:
			swapTopEdgeCounter();
				break;
			case RED:
			swapBottomEdgeCounter();
				break;
			case GREEN:
			swapLeftEdgeCounter();
				break;
			case BLUE: 
			swapRightEdgeCounter();
		}
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
		// Coordinate currentPosition = Driver.cube.findCoordinate(corner);
		if (corner.getCurrentFace() != Color.YELLOW) {
			while (!(corner.getCurrentFace() == Color.ORANGE && corner.getCurrentPosition()== Relation.TR)
					&& !(corner.getCurrentFace() == Color.BLUE && corner.getCurrentPosition() == Relation.TR)) {
				Control.U();
				corner.updateCoordinates();
				// currentPosition = Driver.cube.findCoordinate(corner);
			}
			while (corner.getCurrentFace() != Color.YELLOW) {
				// R' D' R D
				Control.Ri();
				Control.Di();
				Control.R();
				Control.D();
				corner.updateCoordinates();
				// currentPosition = Driver.cube.findCoordinate(corner);
			}
		}
	}

	void repositionYellowCorners(Cubie anchor) {
		// Coordinate expectedPosition = Driver.solved.findCoordinate(anchor);
		switch (anchor.getExpectedPosition()) {
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
		// Coordinate currentPosition = Driver.cube.findCoordinate(corner);
		// Coordinate expectedPosition = Driver.solved.findCoordinate(corner);
		boolean oriented = false;
		if (corner.testCoordinate())
			return true;
		// switch (expectedPosition.cubie) {
			switch(corner.getExpectedPosition()){
			case TL:
				if (corner.getCurrentPosition() == Relation.TR) {
					oriented = (corner.getCurrentFace() == Color.BLUE || corner.getCurrentFace() == Color.ORANGE);
				} else
					oriented = false;
				break;
			case TR:
				if (corner.getCurrentPosition() == Relation.TL) {
					oriented = (corner.getCurrentFace() == Color.GREEN || corner.getCurrentFace() == Color.ORANGE);
				} else
					oriented = false;
				break;
			case BL:

				if (corner.getCurrentPosition() == Relation.BR) {
					oriented = (corner.getCurrentFace() == Color.BLUE || corner.getCurrentFace() == Color.RED);
				} else
					oriented = false;
				break;
			case BR:
				if (corner.getCurrentPosition() == Relation.BL) {
					oriented = (corner.getCurrentFace() == Color.GREEN || corner.getCurrentFace() == Color.RED);
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
		// Coordinate currentPosition = Driver.cube.findCoordinate(edge);
		// Coordinate expectedPosition = Driver.solved.findCoordinate(edge);
		while (!edge.testCoordinate()) {
			Control.switchFace(edge.getExpectedFace());
			if (edge.testFace()) {
				if (edge.testCurrentCoordinate(Color.GREEN,Relation.L)
				||edge.testCurrentCoordinate(Color.BLUE, Relation.R)
				||edge.testCurrentCoordinate(Color.ORANGE, Relation.T)
				||edge.testCurrentCoordinate(Color.RED, Relation.B)) {
					Control.switchFace(Color.WHITE);
					Driver.execute("B");
				} else {
					Control.switchFace(edge.getCurrentFace());
					switch (edge.getCurrentFace()) {
						case RED:
							if (edge.getCurrentPosition() == Relation.R)
								Bottom2RightEdge();
							else
								Bottom2LeftEdge();
							break;
						case ORANGE:
							if (edge.getCurrentPosition() == Relation.R)
								Top2RightEdge();
							else
								Top2LeftEdge();
							break;
						case BLUE:
							if (edge.getCurrentPosition() == Relation.T)
								Right2TopEdge();
							else
								Right2BottomEdge();
							break;
						case GREEN:
							if (edge.getCurrentPosition() == Relation.T)
								Left2TopEdge();
							else
								Left2BottomEdge();
							break;
						default:
							return;
					}

				}
			} else {
				switch (edge.getCurrentPosition()) {
					case T:
						switch (edge.getExpectedPosition()) {
							case L:
								Top2LeftEdge();
								break;
							case R:
								Top2RightEdge();
								break;
							default:
								if (edge.getExpectedFace() == Color.GREEN)
									Left2TopEdge();
								else
									Right2TopEdge();
								break;
						}
						break;
					case B:
						switch (edge.getExpectedPosition()) {
							case L:
								Bottom2LeftEdge();
								break;
							case R:
								Bottom2RightEdge();
								break;
							default:
								if (edge.getCurrentFace() == Color.GREEN)
									Left2BottomEdge();
								else
									Right2BottomEdge();
								break;
						}
						break;
					case L:
						switch (edge.getExpectedPosition()) {
							case T:
								Left2TopEdge();
								break;
							case B:
								Left2BottomEdge();
								break;
							default:
								if (edge.getExpectedFace() == Color.ORANGE)
									Top2LeftEdge();
								else
									Bottom2LeftEdge();
								break;
						}
						break;
					case R:
						switch (edge.getExpectedPosition()) {
							case T:
								Right2TopEdge();
								break;
							case B:
								Right2BottomEdge();
								break;
							default:
								if (edge.getExpectedFace() == Color.ORANGE)
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
			edge.updateCoordinates();
			// currentPosition = Driver.cube.findCoordinate(edge);
		}

	}

	void FLCorner(Cubie corner) {
		System.out.println("Trying FLCorner");
		// Coordinate currentPosition = Driver.cube.findCoordinate(corner);
		// Coordinate expectedPosition = Driver.solved.findCoordinate(corner);
		corner.updateCoordinates();
		Control.switchFace(corner.getExpectedFace());
		while (!corner.testPosition()) {
			System.out.println(corner.testCoordinate());
			switch (corner.getRelevantFace()) {
				// System.out.println("getRelevant is broke");
				case OPPOSITE:
				System.out.println("opposite is broke");
					if (corner.getCurrentPosition() != getCorrectPosition(corner.getExpectedCoordinate())){
						Control.B();
					}
					// currentPosition = Driver.cube.findCoordinate(corner);
					corner.updateCoordinates();
					if (corner.getCurrentPosition() == getCorrectPosition(corner.getExpectedCoordinate())) {
						flback(corner);
					}
					break;
				case LEFT:
					flleft(corner);
					break;
				case RIGHT:
					flright(corner);
					break;
				case TOP:
					fltop(corner);
					break;
				case BOTTOM:
					System.out.println("Botom is broke");
					flbottom(corner);
					break;
				default:
					switch (corner.getCurrentPosition()) {
						case TR:
							Control.Ui();
							Control.Bi();
							Control.U();
							break;
						case BR:
							System.out.println("BR");
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
					// corner.updateCoordinates();
					break;
			}
			// currentPosition = Driver.cube.findCoordinate(corner);
			corner.updateCoordinates();
		}
		// System.out.println("Cube :\n"+Driver.cube);

	}

	boolean testPosition(Cubie cross[]) {
		for (Cubie c : cross) {
			// Coordinate currentPosition = Driver.cube.findCoordinate(c);
			// Coordinate expectedPosition = Driver.solved.findCoordinate(c);
			c.updateCoordinates();
			System.out.printf("Current [%s] => Expected[%s]%n",c.getCurrentCoordinate(),c.getExpectedCoordinate());
			if (!c.testCoordinate())
				return false;
		}
		return true;
	}

	void flback(Cubie cubie) {
		switch (cubie.getCurrentPosition()) {
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

	void flleft(Cubie cubie) {
		switch (cubie.getCurrentPosition()){
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

	void flright(Cubie cubie) {
		switch (cubie.getCurrentPosition()) {
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

	void flbottom(Cubie cubie) {
		switch (cubie.getCurrentPosition()) {
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

	void fltop(Cubie cubie) {
		switch (cubie.getCurrentPosition()) {
			case TR:
				switch (cubie.getExpectedPosition()) {
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
				switch (cubie.getExpectedPosition()) {
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
		switch (expected.position) {
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

	void moveToFront(Cubie cubie) {
		// System.out.println("Trying move to front");
		// Coordinate currentPosition = Driver.cube.findCoordinate(edge);
		// Coordinate expectedPosition = Driver.solved.findCoordinate(edge);
		cubie.updateCoordinates();
		Control.switchFace(cubie.getExpectedFace());
		while (!cubie.testCoordinate()) {
			switch (cubie.getRelevantFace()) {
				case OPPOSITE:
					if (cubie.getCurrentPosition() != getCorrectPosition(cubie.getExpectedCoordinate()))
						Control.B();
					// currentPosition = Driver.cube.findCoordinate(edge);
					if (cubie.getCurrentPosition() == getCorrectPosition(cubie.getExpectedCoordinate()))
						rotateRelevant(cubie.getExpectedCoordinate(), true, false);
					break;
				case LEFT:
					wcLeftFace(cubie);
					break;
				case RIGHT:
					wcRightFace(cubie);
					break;
				case TOP:
					wcTopFace(cubie);
					break;
				case BOTTOM:
					wcBottomFace(cubie);
					break;
				default:
					switch (cubie.getCurrentPosition()) {
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
			cubie.updateCoordinates();
			// currentPosition = Driver.cube.findCoordinate(edge);
		}
	}

	public void wcBottomFace(Cubie cubie) {
		switch (cubie.getCurrentPosition()) {
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

	public void wcTopFace(Cubie cubie) {
		switch (cubie.getCurrentPosition()) {
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

	public void wcRightFace(Cubie cubie) {
		if (cubie.getExpectedPosition() == Relation.L) {
			switch (cubie.getCurrentPosition()) {
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
		} else if (cubie.getExpectedPosition() == Relation.R) {
			switch (cubie.getCurrentPosition()) {
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
		} else if (cubie.getExpectedPosition() == Relation.B) {
			switch (cubie.getCurrentPosition()) {
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
		} else if (cubie.getExpectedPosition() == Relation.T) {
			switch (cubie.getCurrentPosition()) {
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
			rotateRelevant(cubie.getExpectedCoordinate(), false, true);
	}

	public void wcLeftFace(Cubie cubie) {
		if (cubie.getExpectedPosition() == Relation.L) {
			switch (cubie.getCurrentPosition()) {
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
		} else if (cubie.getExpectedPosition() == Relation.R) {
			switch (cubie.getCurrentPosition()) {
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
		} else if (cubie.getExpectedPosition() == Relation.B) {
			switch (cubie.getCurrentPosition()) {
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
		} else if (cubie.getExpectedPosition() == Relation.T) {
			switch (cubie.getCurrentPosition()) {
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
			rotateRelevant(cubie.getExpectedCoordinate(), false, true);
	}

	public Relation getCorrectPosition(Coordinate expected) {
		if (expected.position == Relation.L)
			return Relation.R;
		if (expected.position == Relation.R)
			return Relation.L;

		if (expected.position == Relation.TL)
			return Relation.TR;
		if (expected.position == Relation.TR)
			return Relation.TL;
		if (expected.position == Relation.BL)
			return Relation.BR;
		if (expected.position == Relation.BR)
			return Relation.BL;

		if (expected.face == Color.ORANGE || expected.face == Color.RED) {
			if (expected.position == Relation.T)
				return Relation.B;
			if (expected.position == Relation.B)
				return Relation.T;

		}
		return expected.position;
	}

	public boolean isOppositeColor(Color current, Color expected) {
		return (current == expected.oppositeFace());
	}

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
