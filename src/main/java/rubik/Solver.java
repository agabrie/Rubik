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
		// U R U' R' U' F' U F
		// System.out.println("TR +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		Driver.execute(rotations.get("U"));
		Driver.execute(rotations.get("R"));
		Driver.execute(rotations.get("U") + "'");
		Driver.execute(rotations.get("R") + "'");
		Driver.execute(rotations.get("U") + "'");
		Driver.execute(rotations.get("F") + "'");
		Driver.execute(rotations.get("U"));
		Driver.execute(rotations.get("F"));
		// System.out.println("TR ------------------------------------------------------------------------------------------------------");
	}

	void Top2LeftEdge() {
		// U' L' U L U F U' F'
		// System.out.println("TL +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		Driver.execute(rotations.get("U") + "'");
		Driver.execute(rotations.get("L") + "'");
		Driver.execute(rotations.get("U"));
		Driver.execute(rotations.get("L"));
		Driver.execute(rotations.get("U"));
		Driver.execute(rotations.get("F"));
		Driver.execute(rotations.get("U") + "'");
		Driver.execute(rotations.get("F") + "'");
		// System.out.println("TL ------------------------------------------------------------------------------------------------------");
	}
	void Bottom2LeftEdge() {
		// D L D' L' D' F' D F
		// System.out.println("BL +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		Driver.execute(rotations.get("D"));
		Driver.execute(rotations.get("L"));
		Driver.execute(rotations.get("D") + "'");
		Driver.execute(rotations.get("L") + "'");
		Driver.execute(rotations.get("D") + "'");
		Driver.execute(rotations.get("F") + "'");
		Driver.execute(rotations.get("D"));
		Driver.execute(rotations.get("F"));
		// System.out.println("BL ------------------------------------------------------------------------------------------------------");
	}

	void Bottom2RightEdge() {
		// D' R' D R D F D' F'
		// System.out.println("BR +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		Driver.execute(rotations.get("D") + "'");
		Driver.execute(rotations.get("R") + "'");
		Driver.execute(rotations.get("D"));
		Driver.execute(rotations.get("R"));
		Driver.execute(rotations.get("D"));
		Driver.execute(rotations.get("F"));
		Driver.execute(rotations.get("D") + "'");
		Driver.execute(rotations.get("F") + "'");
		// System.out.println("BR -------------------------------------------------------------------------------------------------------");
	}

	void Left2TopEdge(){
		// B U B' U' B' L' B L
		// System.out.println("LT +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		Driver.execute(rotations.get("L"));
		Driver.execute(rotations.get("U"));
		Driver.execute(rotations.get("L")+"'");
		Driver.execute(rotations.get("U")+"'");
		Driver.execute(rotations.get("L")+"'");
		Driver.execute(rotations.get("F")+"'");
		Driver.execute(rotations.get("L"));
		Driver.execute(rotations.get("F"));
		// System.out.println("LT ------------------------------------------------------------------------------------------------------");
	}

	void Left2BottomEdge(){
		// B' D' B D B L B' L'
		// System.out.println("LB +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		Driver.execute(rotations.get("L")+"'");
		Driver.execute(rotations.get("D")+"'");
		Driver.execute(rotations.get("L"));
		Driver.execute(rotations.get("D"));
		Driver.execute(rotations.get("L"));
		Driver.execute(rotations.get("F"));
		Driver.execute(rotations.get("L")+"'");
		Driver.execute(rotations.get("F")+"'");
		// System.out.println("LB ------------------------------------------------------------------------------------------------------");
		
	}

	void Right2TopEdge(){
		// B' U' B U B R B' R'
		// System.out.println("RT +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		Driver.execute(rotations.get("R")+"'");
		Driver.execute(rotations.get("U")+"'");
		Driver.execute(rotations.get("R"));
		Driver.execute(rotations.get("U"));
		Driver.execute(rotations.get("R"));
		Driver.execute(rotations.get("F"));
		Driver.execute(rotations.get("R")+"'");
		Driver.execute(rotations.get("F")+"'");
		// System.out.println("RT ------------------------------------------------------------------------------------------------------");
	}
	void Right2BottomEdge(){
		// B D B' D' B' R' B R
		// System.out.println("RB +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		Driver.execute(rotations.get("R"));
		Driver.execute(rotations.get("D"));
		Driver.execute(rotations.get("R")+"'");
		Driver.execute(rotations.get("D")+"'");
		Driver.execute(rotations.get("R")+"'");
		Driver.execute(rotations.get("F")+"'");
		Driver.execute(rotations.get("R"));
		Driver.execute(rotations.get("F"));
		// System.out.println("RB --------------------------------------------------------------------------------------------------------");
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
		if(Driver.output && Driver.parts)
			System.out.println("white cross solved : " + testPosition(cross));
	}

	void WhiteCorners(){
		// System.out.println("First Layer!");
		Face white = Driver.solved.faces[Color.WHITE.value];
		Cubie cross[] = { white.getCubie(Relation.TL), white.getCubie(Relation.TR), white.getCubie(Relation.BL),
				white.getCubie(Relation.BR) };
		while (!testPosition(cross)) {
			for (Cubie corner : cross) {
				FLCorner(corner);
			}
		}
		if(Driver.output && Driver.parts)
			System.out.println("white corners solved : " + testPosition(cross));
	}
	void FirstLayer(){
		WhiteCross();
		WhiteCorners();
	}
	void SecondLayer(){
		Face orange = Driver.solved.faces[Color.ORANGE.value];
		Face green = Driver.solved.faces[Color.GREEN.value];
		Face red = Driver.solved.faces[Color.RED.value];
		Face blue = Driver.solved.faces[Color.BLUE.value];
		Cubie cross[] = {
					orange.getCubie(Relation.L), orange.getCubie(Relation.R),
					green.getCubie(Relation.T), green.getCubie(Relation.B),
					red.getCubie(Relation.L), red.getCubie(Relation.R),
					blue.getCubie(Relation.T), blue.getCubie(Relation.B),
		};

		int i = 0;
		while (!testPosition(cross)) {
			if(i++ == 5)
				break;
			for (Cubie edge : cross) {
				System.out.println(edge.fulldetail());
				SLEdge(edge);
			}
		}
		if(Driver.output && Driver.parts)
			System.out.println("second layer solved : " + testPosition(cross));
	}

	void SLEdge(Cubie edge){
		Coordinate currentPosition = Driver.cube.findCoordinate(edge);
		Coordinate expectedPosition = Driver.solved.findCoordinate(edge);
		// switchFace(expectedPosition.face);
		int i =0;
		while (!currentPosition.equals(expectedPosition)) {
			switchFace(expectedPosition.face);

			if(i++ == 10)
				return;
			if(currentPosition.face != expectedPosition.face){
				// switch(currentPosition.face){
				// 	case YELLOW:
				// 		return;
				// }
				if((currentPosition.face == Color.GREEN && currentPosition.cubie == Relation.L ) ||
				(currentPosition.face == Color.BLUE && currentPosition.cubie == Relation.R )||
				(currentPosition.face == Color.ORANGE && currentPosition.cubie == Relation.T )||
				(currentPosition.face == Color.RED && currentPosition.cubie == Relation.B )
				){
					switchFace(Color.WHITE);
					Driver.execute("B");
				}
				// return;
			}
			else{
				System.out.println(currentPosition.face+" -> "+currentPosition.cubie+"2"+expectedPosition.cubie);
				// System.out.println("Expected position"+expectedPosition.cubie);
				switch(currentPosition.cubie){
					case T:
						switch(expectedPosition.cubie){
							case L:
								Top2LeftEdge();break;
							case R:
								Top2RightEdge();break;
								default:
								if(expectedPosition.face == Color.GREEN)
									Left2TopEdge();
								else
									Right2TopEdge();
								break;
						}
						break;
					case B:
						switch(expectedPosition.cubie){
							case L:
								Bottom2LeftEdge();break;
							case R:
								Bottom2RightEdge();break;
							default:
							if(expectedPosition.face == Color.GREEN)
									Left2BottomEdge();
								else
									Right2BottomEdge();
							break;
						}
						break;
					case L:
						switch(expectedPosition.cubie){
							case T:
								Left2TopEdge(); break;
							case B:
								Left2BottomEdge(); break;
							default:
								if(expectedPosition.face == Color.ORANGE)
									Top2LeftEdge();
								else
									Bottom2LeftEdge();
								break;
						}
						break;
					case R:
						switch(expectedPosition.cubie){
							case T:
								Right2TopEdge();break;
							case B:
								Right2BottomEdge(); break;
							default:
							if(expectedPosition.face == Color.ORANGE)
									Top2RightEdge();
								else
									Bottom2RightEdge();
							break;
						}
						break;
				}
				// System.out.println(Driver.cube);
			}
			// switch (expectedPosition.getRelevantFace(currentPosition)) {
			// 	case OPPOSITE:
			// 		break;
			// 	case LEFT:
			// 		break;
			// 	case RIGHT:
			// 		break;
			// 	case TOP:
			// 		if(expectedPosition.face == Color.ORANGE)
			// 			return;
			// 			break;
			// 	case BOTTOM:
			// 		break;
			// 	default:
			// 		break;
			// }
			currentPosition = Driver.cube.findCoordinate(edge);
		}

	}
	void FLCorner(Cubie corner){
		Coordinate currentPosition = Driver.cube.findCoordinate(corner);
		Coordinate expectedPosition = Driver.solved.findCoordinate(corner);
		switchFace(expectedPosition.face);
		while (!currentPosition.equals(expectedPosition)) {
			switch (expectedPosition.getRelevantFace(currentPosition)) {
				case OPPOSITE:
					if (currentPosition.cubie != getCorrectPosition(expectedPosition))
						Driver.execute(rotations.get("B"));
					currentPosition = Driver.cube.findCoordinate(corner);
					if (currentPosition.cubie == getCorrectPosition(expectedPosition)){
						flback(expectedPosition, currentPosition);
					;}
					break;
				case LEFT:
					flleft(expectedPosition, currentPosition);
					break;
				case RIGHT:
					flright(expectedPosition, currentPosition);
					break;
				case TOP:
					fltop(expectedPosition,currentPosition);
					break;
				case BOTTOM:
					flbottom(expectedPosition, currentPosition);
					break;
				default:
					switch (currentPosition.cubie) {
						case TR:
							Driver.execute(rotations.get("U") + "'");
							Driver.execute(rotations.get("B") + "'");
							Driver.execute(rotations.get("U"));
							break;
						case BR:
							Driver.execute(rotations.get("D"));
							Driver.execute(rotations.get("B"));
							Driver.execute(rotations.get("D") + "'");
							break;
						case TL:
							Driver.execute(rotations.get("U"));
							Driver.execute(rotations.get("B"));
							Driver.execute(rotations.get("U")+"'");
							break;
						case BL:
							Driver.execute(rotations.get("D")+"'");
							Driver.execute(rotations.get("B")+"'");
							Driver.execute(rotations.get("D"));
							break;
						default:break;
					}
					break;
			}
			currentPosition = Driver.cube.findCoordinate(corner);
		}

	}
	boolean testPosition(Cubie cross[]) {
		for (Cubie c:cross) {
			Coordinate currentPosition = Driver.cube.findCoordinate(c);
			Coordinate expectedPosition = Driver.solved.findCoordinate(c);
			if (!expectedPosition.equals(currentPosition))
				return false;
		}
		return true;
	}
	void flback(Coordinate expectedPosition,Coordinate currentPosition){
		switch(currentPosition.cubie){
			case BL:
				//R' U' B2 U R
				Driver.execute(rotations.get("R") + "'");
				Driver.execute(rotations.get("U") + "'");
				Driver.execute(rotations.get("B") + "2");
				Driver.execute(rotations.get("U"));
				Driver.execute(rotations.get("R"));
				break;
			case BR:
				//L U B2 U' L'
				Driver.execute(rotations.get("L"));
				Driver.execute(rotations.get("U"));
				Driver.execute(rotations.get("B") + "2");
				Driver.execute(rotations.get("U") + "'");
				Driver.execute(rotations.get("L") + "'");
				break;
			case TR:
				//L' D' B2 D L
				Driver.execute(rotations.get("L")+"'");
				Driver.execute(rotations.get("D")+"'");
				Driver.execute(rotations.get("B") + "2");
				Driver.execute(rotations.get("D"));
				Driver.execute(rotations.get("L"));
				break;
			case TL:
				//R D B2 D' R'
				Driver.execute(rotations.get("R"));
				Driver.execute(rotations.get("D"));
				Driver.execute(rotations.get("B") + "2");
				Driver.execute(rotations.get("D")+"'");
				Driver.execute(rotations.get("R")+"'");
				break;
		}
	}
	void flleft(Coordinate expectedPosition,Coordinate currentPosition){
		switch(currentPosition.cubie){
			case TR://U B U'
			Driver.execute(rotations.get("U"));
			Driver.execute(rotations.get("B"));
			Driver.execute(rotations.get("U")+"'");
				break;
			case BR://D' B' D
			Driver.execute(rotations.get("D")+"'");
			Driver.execute(rotations.get("B")+"'");
			Driver.execute(rotations.get("D"));
				break;
			default:
				Driver.execute(rotations.get("B"));
				// Driver.execute(rotations.get("B") + "'");
				break;
		}
	}
	void flright(Coordinate expectedPosition,Coordinate currentPosition){
		switch(currentPosition.cubie){
			case TL://U' B' U
			Driver.execute(rotations.get("U")+"'");
			Driver.execute(rotations.get("B")+"'");
			Driver.execute(rotations.get("U"));
				break;
			case BL://D B D'
			Driver.execute(rotations.get("D"));
			Driver.execute(rotations.get("B"));
			Driver.execute(rotations.get("D")+"'");
				break;
			default:
				Driver.execute(rotations.get("B"));
				break;
		}
	}
	void flbottom(Coordinate expectedPosition,Coordinate currentPosition){
		switch(currentPosition.cubie){
			case TR://R' B' R
			Driver.execute(rotations.get("R")+"'");
			Driver.execute(rotations.get("B")+"'");
			Driver.execute(rotations.get("R"));
				break;
			case TL://L B L'
				Driver.execute(rotations.get("L"));
				Driver.execute(rotations.get("B"));
				Driver.execute(rotations.get("L")+"'");
				break;
			default:
				Driver.execute(rotations.get("B"));break;
		}
	}
	void fltop(Coordinate expectedPosition,Coordinate currentPosition){
		switch(currentPosition.cubie){
			case TR:
				switch (expectedPosition.cubie) {
					case TR:
						//B R B' R'
						Driver.execute(rotations.get("B"));
						Driver.execute(rotations.get("R"));
						Driver.execute(rotations.get("B") + "'");
						Driver.execute(rotations.get("R") + "'");
						break;
					case TL:
						//B2 U B' U'
						Driver.execute(rotations.get("B") + "2");
						Driver.execute(rotations.get("U"));
						Driver.execute(rotations.get("B") + "'");
						Driver.execute(rotations.get("U") + "'");
						break;
					case BR:
						//D B' D' B
						Driver.execute(rotations.get("D"));
						Driver.execute(rotations.get("B") + "'");
						Driver.execute(rotations.get("D") + "'");
						Driver.execute(rotations.get("B"));
						break;
					case BL:
						//B' L B' L'
						Driver.execute(rotations.get("B") + "'");
						Driver.execute(rotations.get("L"));
						Driver.execute(rotations.get("B") + "'");
						Driver.execute(rotations.get("L") + "'");
						break;
					default:
						break;
				}
			break;
			case TL:
			// System.out.println("TL top running");
				switch (expectedPosition.cubie) {
					case TR:
						//B2 U' B U
						Driver.execute(rotations.get("B") + "2");
						Driver.execute(rotations.get("U") + "'");
						Driver.execute(rotations.get("B"));
						Driver.execute(rotations.get("U"));
						break;
					case TL:
						//B R B' R'
						Driver.execute(rotations.get("B")+"'");
						Driver.execute(rotations.get("L")+"'");
						Driver.execute(rotations.get("B"));
						Driver.execute(rotations.get("L"));
						break;
					case BL:
					// 	//D B' D' B
						Driver.execute(rotations.get("D")+"'");
						Driver.execute(rotations.get("B"));
						Driver.execute(rotations.get("D"));
						Driver.execute(rotations.get("B")+"'");
						break;
					case BR:
					// 	//B' L B' L'
						Driver.execute(rotations.get("B"));
						Driver.execute(rotations.get("R")+"'");
						Driver.execute(rotations.get("B"));
						Driver.execute(rotations.get("R"));
						break;
					default:
						break;
				}
			break;
			case BR:
				Driver.execute(rotations.get("R"));
				Driver.execute(rotations.get("B"));
				Driver.execute(rotations.get("R")+"'");
				break;
			case BL:
				Driver.execute(rotations.get("L")+"'");
				Driver.execute(rotations.get("B")+"'");
				Driver.execute(rotations.get("L"));
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
		Coordinate currentPosition = Driver.cube.findCoordinate(edge);
		Coordinate expectedPosition = Driver.solved.findCoordinate(edge);
		switchFace(expectedPosition.face);
		while (!currentPosition.equals(expectedPosition)){
			switch (expectedPosition.getRelevantFace(currentPosition)) {
				case OPPOSITE:
					if (currentPosition.cubie != getCorrectPosition(expectedPosition))
						Driver.execute(rotations.get("B"));
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
			currentPosition = Driver.cube.findCoordinate(edge);
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

	void currentFace() {
		System.out.println("front face: " + Driver.cube.faces[frontface.value].center.colorString());
	}

	void simplesolve(List<String> instructions) {
		Driver.solution = true;
		Driver.moves = 0;
		Driver.instructions.clear();
		if(Driver.parts)
		{
			// WhiteCross();
			// Driver.instructions = Driver.summarise(Driver.instructions);
			// Driver.moves = Driver.instructions.size();
			// if(Driver.output)
            //     System.out.println(Driver.cube);
			// System.out.println("White Cross : "+Driver.instructions);
            // System.out.println("Number of moves : " + Driver.moves);
			// Driver.instructions.clear();
			// WhiteCorners();
			// Driver.instructions = Driver.summarise(Driver.instructions);
			// Driver.moves = Driver.instructions.size();
			// if(Driver.output)
            //     System.out.println(Driver.cube);
			// System.out.println("First Layer : "+Driver.instructions);
            // System.out.println("Number of moves : " + Driver.moves);
			// Driver.instructions.clear();
			FirstLayer();
			Driver.instructions = Driver.summarise(Driver.instructions);
			Driver.moves = Driver.instructions.size();
			if(Driver.output)
                System.out.println(Driver.cube);
			System.out.println("First Layer : "+Driver.instructions);
            System.out.println("Number of moves : " + Driver.moves);
			Driver.instructions.clear();
			SecondLayer();
			Driver.instructions = Driver.summarise(Driver.instructions);
			Driver.moves = Driver.instructions.size();
			if(Driver.output)
                System.out.println(Driver.cube);
			System.out.println("Second Layer : "+Driver.instructions);
            System.out.println("Number of moves : " + Driver.moves);
			Driver.instructions.clear();
		}
		else{
			// WhiteCross();
			// WhiteCorners();
			FirstLayer();
			SecondLayer();
			Driver.instructions = Driver.summarise(Driver.instructions);
			Driver.moves = Driver.instructions.size();
			if(Driver.output)
                System.out.println("Final solution:\n" + Driver.cube);
			System.out.println("Solution : "+Driver.instructions);
			System.out.println("Number of moves in final solution : " + Driver.moves);
		}
	}

}
