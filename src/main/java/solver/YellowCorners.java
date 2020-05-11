package solver;

import enums.*;
import rubik.*;

public class YellowCorners extends LayerSolution {
	Face yellow = Driver.solved.faces[Color.YELLOW.value];

	YellowCorners() {
		tosolve.add(yellow.getCubie(Relation.TL));
		tosolve.add(yellow.getCubie(Relation.TR));
		tosolve.add(yellow.getCubie(Relation.BL));
		tosolve.add(yellow.getCubie(Relation.BR));
	}

	void solve() {
		int i = 0;
		while (i != 4) {
			i = 0;
			Cubie anchor = null;
			for (Cubie corner : tosolve) {

				if (testCornerPosition(corner)) {
					i++;
					anchor = corner;
				}
			}

			if (i == 1) {
				repositionYellowCorners(anchor);
			} else if (i == 0)
				repositionYellowCorners(tosolve.get(0));
		}
		if (Driver.output && Driver.parts)
			System.out.println(Coloreths.FCyan.color + "yellow corners positioned!" + Coloreths.Reset.color);
		Control.switchFace(Color.ORANGE);
		for (Cubie corner : tosolve) {

			reorientYellowCorner(corner);
		}
		if (Driver.output && Driver.parts)
			System.out.println(Coloreths.FCyan.color + "yellow corners oriented!" + Coloreths.Reset.color);
		while (!testPosition()) {
			Control.U();
		}
	}

	boolean testCornerPosition(Cubie corner) {
		boolean oriented = false;
		if (corner.testCoordinate())
			return true;
		switch (corner.getExpectedPosition()) {
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

	void repositionYellowCorners(Cubie anchor) {
		switch (anchor.getExpectedPosition()) {
			default:
				break;
			case TL:
				Control.switchFace(Color.ORANGE);
				Sequences.repositionTLAnchor();
				break;
			case TR:
				Control.switchFace(Color.GREEN);
				Sequences.repositionTRAnchor();
				break;
			case BL:
				Control.switchFace(Color.BLUE);

				Sequences.repositionBLAnchor();
				break;
			case BR:
				Control.switchFace(Color.RED);
				Sequences.repositionBRAnchor();
				break;
		}
	}

	void reorientYellowCorner(Cubie corner) {
		if (corner.getCurrentFace() != Color.YELLOW) {
			while (!(corner.getCurrentFace() == Color.ORANGE && corner.getCurrentPosition() == Relation.TR)
					&& !(corner.getCurrentFace() == Color.BLUE && corner.getCurrentPosition() == Relation.TR)) {
				Control.U();
				corner.updateCoordinates();
			}
			while (corner.getCurrentFace() != Color.YELLOW) {
				Sequences.reorientCornerTR();
				corner.updateCoordinates();
			}
		}
	}
}