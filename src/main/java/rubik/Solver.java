package rubik;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.net.ssl.SSLKeyException;
import javax.sound.midi.SysexMessage;
import javax.swing.text.Position;

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
		// System.out.println("TR
		// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		Driver.execute(rotations.get("U"));
		Driver.execute(rotations.get("R"));
		Driver.execute(rotations.get("U") + "'");
		Driver.execute(rotations.get("R") + "'");
		Driver.execute(rotations.get("U") + "'");
		Driver.execute(rotations.get("F") + "'");
		Driver.execute(rotations.get("U"));
		Driver.execute(rotations.get("F"));
		// System.out.println("TR
		// ------------------------------------------------------------------------------------------------------");
	}

	void Top2LeftEdge() {
		// U' L' U L U F U' F'
		// System.out.println("TL
		// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		Driver.execute(rotations.get("U") + "'");
		Driver.execute(rotations.get("L") + "'");
		Driver.execute(rotations.get("U"));
		Driver.execute(rotations.get("L"));
		Driver.execute(rotations.get("U"));
		Driver.execute(rotations.get("F"));
		Driver.execute(rotations.get("U") + "'");
		Driver.execute(rotations.get("F") + "'");
		// System.out.println("TL
		// ------------------------------------------------------------------------------------------------------");
	}

	void Bottom2LeftEdge() {
		// D L D' L' D' F' D F
		// System.out.println("BL
		// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		Driver.execute(rotations.get("D"));
		Driver.execute(rotations.get("L"));
		Driver.execute(rotations.get("D") + "'");
		Driver.execute(rotations.get("L") + "'");
		Driver.execute(rotations.get("D") + "'");
		Driver.execute(rotations.get("F") + "'");
		Driver.execute(rotations.get("D"));
		Driver.execute(rotations.get("F"));
		// System.out.println("BL
		// ------------------------------------------------------------------------------------------------------");
	}

	void Bottom2RightEdge() {
		// D' R' D R D F D' F'
		// System.out.println("BR
		// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		Driver.execute(rotations.get("D") + "'");
		Driver.execute(rotations.get("R") + "'");
		Driver.execute(rotations.get("D"));
		Driver.execute(rotations.get("R"));
		Driver.execute(rotations.get("D"));
		Driver.execute(rotations.get("F"));
		Driver.execute(rotations.get("D") + "'");
		Driver.execute(rotations.get("F") + "'");
		// System.out.println("BR
		// -------------------------------------------------------------------------------------------------------");
	}

	void Left2TopEdge() {
		// B U B' U' B' L' B L
		// System.out.println("LT
		// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		Driver.execute(rotations.get("L"));
		Driver.execute(rotations.get("U"));
		Driver.execute(rotations.get("L") + "'");
		Driver.execute(rotations.get("U") + "'");
		Driver.execute(rotations.get("L") + "'");
		Driver.execute(rotations.get("F") + "'");
		Driver.execute(rotations.get("L"));
		Driver.execute(rotations.get("F"));
		// System.out.println("LT
		// ------------------------------------------------------------------------------------------------------");
	}

	void Left2BottomEdge() {
		// B' D' B D B L B' L'
		// System.out.println("LB
		// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		Driver.execute(rotations.get("L") + "'");
		Driver.execute(rotations.get("D") + "'");
		Driver.execute(rotations.get("L"));
		Driver.execute(rotations.get("D"));
		Driver.execute(rotations.get("L"));
		Driver.execute(rotations.get("F"));
		Driver.execute(rotations.get("L") + "'");
		Driver.execute(rotations.get("F") + "'");
		// System.out.println("LB
		// ------------------------------------------------------------------------------------------------------");

	}

	void Right2TopEdge() {
		// B' U' B U B R B' R'
		// System.out.println("RT
		// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		Driver.execute(rotations.get("R") + "'");
		Driver.execute(rotations.get("U") + "'");
		Driver.execute(rotations.get("R"));
		Driver.execute(rotations.get("U"));
		Driver.execute(rotations.get("R"));
		Driver.execute(rotations.get("F"));
		Driver.execute(rotations.get("R") + "'");
		Driver.execute(rotations.get("F") + "'");
		// System.out.println("RT
		// ------------------------------------------------------------------------------------------------------");
	}

	void Right2BottomEdge() {
		// B D B' D' B' R' B R
		// System.out.println("RB
		// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		Driver.execute(rotations.get("R"));
		Driver.execute(rotations.get("D"));
		Driver.execute(rotations.get("R") + "'");
		Driver.execute(rotations.get("D") + "'");
		Driver.execute(rotations.get("R") + "'");
		Driver.execute(rotations.get("F") + "'");
		Driver.execute(rotations.get("R"));
		Driver.execute(rotations.get("F"));
		// System.out.println("RB
		// --------------------------------------------------------------------------------------------------------");
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
		// System.out.println("First Layer!");
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
		if(Driver.parts)
			displayResults("White Cross");
		WhiteCorners();
		if(Driver.parts)
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

		// int i = 0;
		while (!testPosition(cross)) {
			// if(i++ == 5)
			// break;
			for (Cubie edge : cross) {
				// System.out.println(edge.fulldetail());
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

		switchFace(Color.ORANGE);
		ep = Driver.solved.findCoordinate(cross[0]);
		while (!ep.equals(cp = Driver.cube.findCoordinate(cross[0]))) {
			Driver.execute(rotations.get("U"));
		}
		Coordinate greenP = Driver.cube.findCoordinate(cross[1]);
		// System.out.println("current : "+Driver.cube);
		// System.out.println("Green : "+greenP.cubie);
		switch (greenP.cubie) {
			case L:
				// System.out.println("L");
				swapRightAdjacents(Color.ORANGE);
				Driver.execute(rotations.get("U"));
				break;
			case B:
				// System.out.println("B");
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
		switchFace(f);
		switch (f) {
			case ORANGE: {
				// R U R' U R U2 R' U
				Driver.execute(rotations.get("R"));
				Driver.execute(rotations.get("U"));
				Driver.execute(rotations.get("R") + "'");
				Driver.execute(rotations.get("U"));
				Driver.execute(rotations.get("R"));
				Driver.execute(rotations.get("U") + "2");
				Driver.execute(rotations.get("R") + "'");
				Driver.execute(rotations.get("U"));
				// Driver.execute(rotations.get("R"));
			}
				break;
			case RED: {
				// L D L' D L D2 L' D
				Driver.execute(rotations.get("L"));
				Driver.execute(rotations.get("D"));
				Driver.execute(rotations.get("L") + "'");
				Driver.execute(rotations.get("D"));
				Driver.execute(rotations.get("L"));
				Driver.execute(rotations.get("D") + "2");
				Driver.execute(rotations.get("L") + "'");
				Driver.execute(rotations.get("D"));
			}
				break;
			case GREEN: {
				// U L U' L U L2 U' L
				Driver.execute(rotations.get("U"));
				Driver.execute(rotations.get("L"));
				Driver.execute(rotations.get("U") + "'");
				Driver.execute(rotations.get("L"));
				Driver.execute(rotations.get("U"));
				Driver.execute(rotations.get("L") + "2");
				Driver.execute(rotations.get("U") + "'");
				Driver.execute(rotations.get("L"));

			}
				break;
			case BLUE: {
				// D R D' R D R2 D' R
				Driver.execute(rotations.get("D"));
				Driver.execute(rotations.get("R"));
				Driver.execute(rotations.get("D") + "'");
				Driver.execute(rotations.get("R"));
				Driver.execute(rotations.get("D"));
				Driver.execute(rotations.get("R") + "2");
				Driver.execute(rotations.get("D") + "'");
				Driver.execute(rotations.get("R"));
			}
		}
	}

	void swapRightAdjacents(Color f) {
		switchFace(f);
		switch (f) {
			case ORANGE: {
				// L' U' L U' L' U2 L U'
				Driver.execute(rotations.get("L") + "'");
				Driver.execute(rotations.get("U") + "'");
				Driver.execute(rotations.get("L"));
				Driver.execute(rotations.get("U") + "'");
				Driver.execute(rotations.get("L") + "'");
				Driver.execute(rotations.get("U") + "2");
				Driver.execute(rotations.get("L"));
				Driver.execute(rotations.get("U") + "'");
				// Driver.execute(rotations.get("R")+"'");

			}
				break;
			case RED: {
				// R' D' R D' R' D2 R D'
				Driver.execute(rotations.get("R") + "'");
				Driver.execute(rotations.get("D") + "'");
				Driver.execute(rotations.get("R"));
				Driver.execute(rotations.get("D") + "'");
				Driver.execute(rotations.get("R") + "'");
				Driver.execute(rotations.get("D") + "2");
				Driver.execute(rotations.get("R"));
				Driver.execute(rotations.get("D") + "'");
				// Driver.execute(rotations.get("R")+"'");
			}
				break;
			case GREEN: {
				// D' L' D L' D' L2 D L'
				Driver.execute(rotations.get("D") + "'");
				Driver.execute(rotations.get("L") + "'");
				Driver.execute(rotations.get("D"));
				Driver.execute(rotations.get("L") + "'");
				Driver.execute(rotations.get("D") + "'");
				Driver.execute(rotations.get("L") + "2");
				Driver.execute(rotations.get("D"));
				Driver.execute(rotations.get("L") + "'");
				// Driver.execute(rotations.get("D")+"'");
			}
				break;
			case BLUE: {
				// U' R' U R' U' R2 U R'
				Driver.execute(rotations.get("U") + "'");
				Driver.execute(rotations.get("L") + "'");
				Driver.execute(rotations.get("U"));
				Driver.execute(rotations.get("L") + "'");
				Driver.execute(rotations.get("U") + "'");
				Driver.execute(rotations.get("L") + "2");
				Driver.execute(rotations.get("U"));
				Driver.execute(rotations.get("L") + "'");
				// Driver.execute(rotations.get("U")+"'");
			}
		}
	}

	// int getCurrentLeftAdjacent(int i, Cubie cross[]) {

	// return i;
	// }

	Relation getCorrectLeft(Coordinate current) {
		Relation relevant;
		switch (current.cubie) {
			// case T:
			// relevant = Relation.L;
			// break;
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

	// void positionYC(Cubie edge) {
	// Coordinate expectedPosition = Driver.solved.findCoordinate(edge);
	// Coordinate currentPosition = Driver.cube.findCoordinate(edge);
	// switchFace(expectedPosition.face);
	// while (!currentPosition.equals(expectedPosition)) {
	// switch (expectedPosition.face) {
	// case ORANGE:
	// Driver.execute(rotations.get("U"));
	// }
	// }

	// }

	void FinalLayer() {
		YellowCross();
		if(Driver.parts)
			displayResults("Yellow Cross");
		YellowCorners();
		if(Driver.parts)
			displayResults("Yellow Corners");
	}

	void YellowCorners() {
		Face yellow = Driver.solved.faces[Color.YELLOW.value];
		Cubie cross[] = { yellow.getCubie(Relation.TL), yellow.getCubie(Relation.TR), yellow.getCubie(Relation.BL),
				yellow.getCubie(Relation.BR) };
		int i = 0;
		// int counter = 0;
		while(i != 4){
			// if(counter++ > 3)
				// break;
			i = 0;
			Cubie anchor = null;
			for (Cubie corner : cross) {
				// System.out.println(corner.fulldetail());
				if(testCornerPosition(corner)){
					i++;
					anchor = corner;
				}
			}
			// System.out.println("Correctly positioned : "+i);
			if(i == 1){
				repositionYellowCorners(anchor);
			}else if(i == 0)
				repositionYellowCorners(cross[0]);
		}
		if (Driver.output && Driver.parts)
			System.out.println("yellow corners positioned!");
		switchFace(Color.ORANGE);
		for(Cubie corner:cross){
			// System.out.println(corner.fulldetail());
			reorientYellowCorner(corner);
		}
		if (Driver.output && Driver.parts)
			System.out.println("yellow corners oriented!");
		while(!testPosition(cross)){
			Driver.execute(rotations.get("U"));
		}
	}

	void reorientYellowCorner(Cubie corner){
		// Coordinate expectedPosition = Driver.solved.findCoordinate(corner);
		Coordinate currentPosition = Driver.cube.findCoordinate(corner);
		// switchFace(Color.ORANGE);
		// while()
		// System.out.println("Current Position -> "+currentPosition);
		if(currentPosition.face != Color.YELLOW){
			// switchFace(Color.WHITE);
			while(!(currentPosition.face == Color.ORANGE && currentPosition.cubie == Relation.TR) && !(currentPosition.face == Color.BLUE && currentPosition.cubie == Relation.TR)){
				// System.out.println(frontface);
				Driver.execute(rotations.get("U"));
				currentPosition = Driver.cube.findCoordinate(corner);
				// System.out.println("rotate top to : \n"+Driver.cube);
			}
			// System.out.println("Position : "+currentPosition);
			// switchFace(Color.ORANGE);
			while(currentPosition.face != Color.YELLOW)
			{
				// R' D' R D
				Driver.execute(rotations.get("R")+"'");
				Driver.execute(rotations.get("D")+"'");
				Driver.execute(rotations.get("R"));
				Driver.execute(rotations.get("D"));
				currentPosition = Driver.cube.findCoordinate(corner);
				// System.out.println("after weird : "+currentPosition+"\n check\n"+Driver.cube);
				
			}
		}
		// else
			// Driver.execute("U");
	}
	void repositionYellowCorners(Cubie anchor){
		// System.out.println(anchor.fulldetail());
		Coordinate expectedPosition = Driver.solved.findCoordinate(anchor);
		// System.out.println("anchor position : "+expectedPosition.cubie);
		switch(expectedPosition.cubie){
			case TL:
				switchFace(Color.ORANGE);				
				// U R U' L'        U R' U' L
				Driver.execute(rotations.get("U"));
				Driver.execute(rotations.get("R"));				
				Driver.execute(rotations.get("U")+"'");				
				Driver.execute(rotations.get("L")+"'");

				Driver.execute(rotations.get("U"));				
				Driver.execute(rotations.get("R")+"'");				
				Driver.execute(rotations.get("U")+"'");				
				Driver.execute(rotations.get("L"));				
				break;
			case TR:
				switchFace(Color.GREEN);
				// L U L' D' L U' L' D
				Driver.execute(rotations.get("L"));
				Driver.execute(rotations.get("U"));				
				Driver.execute(rotations.get("L")+"'");				
				Driver.execute(rotations.get("D")+"'");

				Driver.execute(rotations.get("L"));				
				Driver.execute(rotations.get("U")+"'");				
				Driver.execute(rotations.get("L")+"'");				
				Driver.execute(rotations.get("D"));	
				break;
			case BL:
				switchFace(Color.BLUE);
				// R D R' U' R D' R' U

				Driver.execute(rotations.get("R"));
				Driver.execute(rotations.get("D"));				
				Driver.execute(rotations.get("R")+"'");				
				Driver.execute(rotations.get("U")+"'");

				Driver.execute(rotations.get("R"));				
				Driver.execute(rotations.get("D")+"'");				
				Driver.execute(rotations.get("R")+"'");				
				Driver.execute(rotations.get("U"));	
				break;
			case BR:
				switchFace(Color.RED);
				// D L D' R'        D L' D' R
				Driver.execute(rotations.get("D"));
				Driver.execute(rotations.get("L"));				
				Driver.execute(rotations.get("D")+"'");				
				Driver.execute(rotations.get("R")+"'");

				Driver.execute(rotations.get("D"));				
				Driver.execute(rotations.get("L")+"'");				
				Driver.execute(rotations.get("D")+"'");				
				Driver.execute(rotations.get("R"));	
				break;
		}
		// System.out.println("anchor face : "+frontface);
		// System.out.println("cube after reposition:\n"+Driver.cube);
	}
	boolean testCornerPosition(Cubie corner) {
		Coordinate currentPosition = Driver.cube.findCoordinate(corner);
		Coordinate expectedPosition = Driver.solved.findCoordinate(corner);
		boolean oriented = false;
		// System.out.println(expectedPosition.cubie);
		if(currentPosition.equals(expectedPosition))
			return true;
		switch (expectedPosition.cubie) {
			case TL:
				// System.out.println("TL -> "+currentPosition.cubie);
				if (currentPosition.cubie == Relation.TR) {
					// System.out.println("TR true");
					oriented = (currentPosition.face == Color.BLUE || currentPosition.face == Color.ORANGE);
					// System.out.println("face true");
				} else
					oriented = false;
				break;
			case TR:
				// System.out.println("TR -> "+currentPosition.cubie);
				if (currentPosition.cubie == Relation.TL) {
					oriented = (currentPosition.face == Color.GREEN || currentPosition.face == Color.ORANGE);
				} else
					oriented = false;
				break;
			case BL:
				// System.out.println("BL -> "+currentPosition.cubie);

				if (currentPosition.cubie == Relation.BR) {
					oriented = (currentPosition.face == Color.BLUE || currentPosition.face == Color.RED);
				} else
					oriented = false;
				break;
			case BR:
				// System.out.println("BR -> "+currentPosition.cubie);
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
		switchFace(Color.ORANGE);
		int x = 0;
		int i = 0;
		int numYellow;
		while (x++ < 4 && checkYellowCross() != 4) {
			numYellow = checkYellowCross();
			if (numYellow > 1)
				if (Driver.cube.faces[Color.YELLOW.value].cubie[Relation.T.value].color[0] == Color.YELLOW)
					Driver.execute(rotations.get("U"));
			i = 0;
			while (i < 4 && Driver.cube.faces[Color.YELLOW.value].cubie[Relation.R.value].color[0] != Color.YELLOW) {
				Driver.execute(rotations.get("U") + "'");
				i++;
			}
			// // F R U R' U' F'
			Driver.execute(rotations.get("F"));
			Driver.execute(rotations.get("R"));
			Driver.execute(rotations.get("U"));
			Driver.execute(rotations.get("R") + "'");
			Driver.execute(rotations.get("U") + "'");
			Driver.execute(rotations.get("F") + "'");
			// System.out.println("cube after yellow cross test : \n"+Driver.cube);
			// x++;
		}

	}

	int checkYellowCross() {
		Face yellow = Driver.cube.faces[Color.YELLOW.value];
		Cubie cross[] = { yellow.getCubie(Relation.T), yellow.getCubie(Relation.R), yellow.getCubie(Relation.L),
				yellow.getCubie(Relation.B) };
		// System.out.println("the current face:\n"+yellow.toString());
		int numYellow = 0;
		for (Cubie edge : cross) {
			// // Coordinate current = Driver.solved.findCoordinate(edge);
			// System.out.println(edge.color[0]);
			if (edge.color[0] == Color.YELLOW)
				numYellow++;
			// return false;
		}
		// System.out.println("numYellow : "+numYellow);
		return numYellow;
	}

	void SLEdge(Cubie edge) {
		Coordinate currentPosition = Driver.cube.findCoordinate(edge);
		Coordinate expectedPosition = Driver.solved.findCoordinate(edge);
		// switchFace(expectedPosition.face);
		// int i =0;
		while (!currentPosition.equals(expectedPosition)) {
			switchFace(expectedPosition.face);

			// if(i++ == 10)
			// return;
			if (currentPosition.face != expectedPosition.face) {
				// switch(currentPosition.face){
				// case YELLOW:
				// return;
				// }
				if ((currentPosition.face == Color.GREEN && currentPosition.cubie == Relation.L)
						|| (currentPosition.face == Color.BLUE && currentPosition.cubie == Relation.R)
						|| (currentPosition.face == Color.ORANGE && currentPosition.cubie == Relation.T)
						|| (currentPosition.face == Color.RED && currentPosition.cubie == Relation.B)) {
					switchFace(Color.WHITE);
					Driver.execute("B");
				} else {
					switchFace(currentPosition.face);
					// System.out.println(currentPosition.toString()+" ->
					// "+expectedPosition.toString());
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
				// return;
			} else {
				// System.out.println(currentPosition.face+" ->
				// "+currentPosition.cubie+"2"+expectedPosition.cubie);
				// System.out.println("Expected position"+expectedPosition.cubie);
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
				}
				// System.out.println(Driver.cube);
			}
			// switch (expectedPosition.getRelevantFace(currentPosition)) {
			// case OPPOSITE:
			// break;
			// case LEFT:
			// break;
			// case RIGHT:
			// break;
			// case TOP:
			// if(expectedPosition.face == Color.ORANGE)
			// return;
			// break;
			// case BOTTOM:
			// break;
			// default:
			// break;
			// }
			currentPosition = Driver.cube.findCoordinate(edge);
		}

	}

	void FLCorner(Cubie corner) {
		Coordinate currentPosition = Driver.cube.findCoordinate(corner);
		Coordinate expectedPosition = Driver.solved.findCoordinate(corner);
		switchFace(expectedPosition.face);
		while (!currentPosition.equals(expectedPosition)) {
			switch (expectedPosition.getRelevantFace(currentPosition)) {
				case OPPOSITE:
					if (currentPosition.cubie != getCorrectPosition(expectedPosition))
						Driver.execute(rotations.get("B"));
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
							Driver.execute(rotations.get("U") + "'");
							break;
						case BL:
							Driver.execute(rotations.get("D") + "'");
							Driver.execute(rotations.get("B") + "'");
							Driver.execute(rotations.get("D"));
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
				Driver.execute(rotations.get("R") + "'");
				Driver.execute(rotations.get("U") + "'");
				Driver.execute(rotations.get("B") + "2");
				Driver.execute(rotations.get("U"));
				Driver.execute(rotations.get("R"));
				break;
			case BR:
				// L U B2 U' L'
				Driver.execute(rotations.get("L"));
				Driver.execute(rotations.get("U"));
				Driver.execute(rotations.get("B") + "2");
				Driver.execute(rotations.get("U") + "'");
				Driver.execute(rotations.get("L") + "'");
				break;
			case TR:
				// L' D' B2 D L
				Driver.execute(rotations.get("L") + "'");
				Driver.execute(rotations.get("D") + "'");
				Driver.execute(rotations.get("B") + "2");
				Driver.execute(rotations.get("D"));
				Driver.execute(rotations.get("L"));
				break;
			case TL:
				// R D B2 D' R'
				Driver.execute(rotations.get("R"));
				Driver.execute(rotations.get("D"));
				Driver.execute(rotations.get("B") + "2");
				Driver.execute(rotations.get("D") + "'");
				Driver.execute(rotations.get("R") + "'");
				break;
		}
	}

	void flleft(Coordinate expectedPosition, Coordinate currentPosition) {
		switch (currentPosition.cubie) {
			case TR:// U B U'
				Driver.execute(rotations.get("U"));
				Driver.execute(rotations.get("B"));
				Driver.execute(rotations.get("U") + "'");
				break;
			case BR:// D' B' D
				Driver.execute(rotations.get("D") + "'");
				Driver.execute(rotations.get("B") + "'");
				Driver.execute(rotations.get("D"));
				break;
			default:
				Driver.execute(rotations.get("B"));
				// Driver.execute(rotations.get("B") + "'");
				break;
		}
	}

	void flright(Coordinate expectedPosition, Coordinate currentPosition) {
		switch (currentPosition.cubie) {
			case TL:// U' B' U
				Driver.execute(rotations.get("U") + "'");
				Driver.execute(rotations.get("B") + "'");
				Driver.execute(rotations.get("U"));
				break;
			case BL:// D B D'
				Driver.execute(rotations.get("D"));
				Driver.execute(rotations.get("B"));
				Driver.execute(rotations.get("D") + "'");
				break;
			default:
				Driver.execute(rotations.get("B"));
				break;
		}
	}

	void flbottom(Coordinate expectedPosition, Coordinate currentPosition) {
		switch (currentPosition.cubie) {
			case TR:// R' B' R
				Driver.execute(rotations.get("R") + "'");
				Driver.execute(rotations.get("B") + "'");
				Driver.execute(rotations.get("R"));
				break;
			case TL:// L B L'
				Driver.execute(rotations.get("L"));
				Driver.execute(rotations.get("B"));
				Driver.execute(rotations.get("L") + "'");
				break;
			default:
				Driver.execute(rotations.get("B"));
				break;
		}
	}

	void fltop(Coordinate expectedPosition, Coordinate currentPosition) {
		switch (currentPosition.cubie) {
			case TR:
				switch (expectedPosition.cubie) {
					case TR:
						// B R B' R'
						Driver.execute(rotations.get("B"));
						Driver.execute(rotations.get("R"));
						Driver.execute(rotations.get("B") + "'");
						Driver.execute(rotations.get("R") + "'");
						break;
					case TL:
						// B2 U B' U'
						Driver.execute(rotations.get("B") + "2");
						Driver.execute(rotations.get("U"));
						Driver.execute(rotations.get("B") + "'");
						Driver.execute(rotations.get("U") + "'");
						break;
					case BR:
						// D B' D' B
						Driver.execute(rotations.get("D"));
						Driver.execute(rotations.get("B") + "'");
						Driver.execute(rotations.get("D") + "'");
						Driver.execute(rotations.get("B"));
						break;
					case BL:
						// B' L B' L'
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
						// B2 U' B U
						Driver.execute(rotations.get("B") + "2");
						Driver.execute(rotations.get("U") + "'");
						Driver.execute(rotations.get("B"));
						Driver.execute(rotations.get("U"));
						break;
					case TL:
						// B R B' R'
						Driver.execute(rotations.get("B") + "'");
						Driver.execute(rotations.get("L") + "'");
						Driver.execute(rotations.get("B"));
						Driver.execute(rotations.get("L"));
						break;
					case BL:
						// //D B' D' B
						Driver.execute(rotations.get("D") + "'");
						Driver.execute(rotations.get("B"));
						Driver.execute(rotations.get("D"));
						Driver.execute(rotations.get("B") + "'");
						break;
					case BR:
						// //B' L B' L'
						Driver.execute(rotations.get("B"));
						Driver.execute(rotations.get("R") + "'");
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
				Driver.execute(rotations.get("R") + "'");
				break;
			case BL:
				Driver.execute(rotations.get("L") + "'");
				Driver.execute(rotations.get("B") + "'");
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
			default:
				break;
		}
	}

	void moveToFront(Cubie edge) {
		// edge needed to solve
		// System.out.println("edge to solve : "+ edge.fulldetail());
		Coordinate currentPosition = Driver.cube.findCoordinate(edge);
		Coordinate expectedPosition = Driver.solved.findCoordinate(edge);
		switchFace(expectedPosition.face);
		while (!currentPosition.equals(expectedPosition)) {
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
			default:
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
			default:
				break;
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
				default:
					break;
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
				default:
					break;
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

	void currentFace() {
		System.out.println("front face: " + Driver.cube.faces[frontface.value].center.colorString());
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
		if (Driver.parts) {
			FirstLayer();
			// displayResults("First Layer");
			SecondLayer();
			displayResults("Second Layer");
			FinalLayer();
			// displayResults("Final Layer");
		} else {
			// WhiteCross();
			// WhiteCorners();
			FirstLayer();
			SecondLayer();
			FinalLayer();
			displayResults("Final Solution");
			// Driver.instructions = Driver.summarise(Driver.instructions);
			// Driver.moves = Driver.instructions.size();
			// System.out.println("Final Solution : ");
			// if (Driver.output)
			// System.out.println(Driver.cube);
			// if (Driver.lined)
			// for (String s : Driver.instructions)
			// System.out.println(s);
			// else
			// System.out.println(Driver.instructions);
			// System.out.println("Number of moves in final solution : " + Driver.moves);
		}
	}

}
