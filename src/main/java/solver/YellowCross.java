package solver;

import rubik.*;
import enums.*;

public class YellowCross extends LayerSolution {
	Face yellow = Driver.solved.faces[Color.YELLOW.value];

	YellowCross() {
		tosolve.add(yellow.getCubie(Relation.T));
		tosolve.add(yellow.getCubie(Relation.R));
		tosolve.add(yellow.getCubie(Relation.B));
		tosolve.add(yellow.getCubie(Relation.L));
	}

	void solve() {
		orientYC();
		Control.switchFace(Color.ORANGE);

		while (!tosolve.get(0).testCoordinate()) {
			Control.U();
		}
		// Coordinate greenP = Driver.cube.findCoordinate(cross[1]);
		switch (tosolve.get(1).getCurrentPosition()) {
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

		if (tosolve.get(2).getCurrentPosition() == Relation.L) {
			swapLeftAdjacents(Color.RED);
		}

		if (Driver.parts) {
			if (Driver.output)
				System.out.println(
						Coloreths.FCyan.color + "yellow cross solved : " + Coloreths.Reset.color + testPosition());
			Driver.displayResults("Yellow Cross");
		}

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
			Sequences.OrientTopEdge();

		}

	}

	void swapLeftAdjacents(Color f) {
		Control.switchFace(f);
		switch (f) {
			default:
				break;
			case ORANGE:
				Sequences.swapTopEdgeClockwise();
				break;
			case RED:
				Sequences.swapBottomEdgeClockwise();
				break;
			case GREEN:
				Sequences.swapLeftEdgeClockwise();
				break;
			case BLUE:
				Sequences.swapRightEdgeClockwise();
				break;
		}
	}

	void swapRightAdjacents(Color f) {
		Control.switchFace(f);
		switch (f) {
			default:
				break;
			case ORANGE:
				Sequences.swapTopEdgeCounter();
				break;
			case RED:
				Sequences.swapBottomEdgeCounter();
				break;
			case GREEN:
				Sequences.swapLeftEdgeCounter();
				break;
			case BLUE:
				Sequences.swapRightEdgeCounter();
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
}