package solver;

import enums.*;
import rubik.*;

public class WhiteCorners extends LayerSolution {
	Face white = Driver.solved.faces[Color.WHITE.value];

	WhiteCorners() {
		tosolve.add(white.getCubie(Relation.TL));
		tosolve.add(white.getCubie(Relation.TR));
		tosolve.add(white.getCubie(Relation.BL));
		tosolve.add(white.getCubie(Relation.BR));
	}

	public void solve() {

		while (!testPosition()) {
			for (Cubie corner : tosolve) {
				FLCorner(corner);
			}
		}
		if (Driver.parts) {
			if (Driver.output)
				System.out.println(
						Coloreths.FCyan.color + "white corners solved : " + Coloreths.Reset.color + testPosition());
			Driver.displayResults("White Corners");
		}
	}

	void FLCorner(Cubie corner) {
		corner.updateCoordinates();
		Control.switchFace(corner.getExpectedFace());
		while (!corner.testCoordinate()) {
			switch (corner.getRelevantFace()) {
				case OPPOSITE:
					if (corner.getCurrentPosition() != getCorrectPosition(corner.getExpectedCoordinate())) {
						Control.B();
					}
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
			corner.updateCoordinates();
		}
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
		switch (cubie.getCurrentPosition()) {
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
			default:
				break;
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

}