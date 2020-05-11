package solver;

import enums.*;
import rubik.*;

public class WhiteCross extends LayerSolution {
	Face white = Driver.solved.faces[Color.WHITE.value];

	WhiteCross() {
		tosolve.add(white.getCubie(Relation.T));
		tosolve.add(white.getCubie(Relation.L));
		tosolve.add(white.getCubie(Relation.R));
		tosolve.add(white.getCubie(Relation.B));
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

	public void solve() {
		while (!testPosition()) {
			for (Cubie edge : tosolve) {
				moveToFront(edge);
			}
		}
		if (Driver.parts) {
			if (Driver.output)
				System.out.println(
						Coloreths.FCyan.color + "white cross solved : " + Coloreths.Reset.color + testPosition());
			Driver.displayResults("White Cross");
		}
	}

	void moveToFront(Cubie cubie) {
		cubie.updateCoordinates();
		Control.switchFace(cubie.getExpectedFace());
		while (!cubie.testCoordinate()) {
			switch (cubie.getRelevantFace()) {
				case OPPOSITE:
					if (cubie.getCurrentPosition() != getCorrectPosition(cubie.getExpectedCoordinate()))
						Control.B();
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
							Control.U2();
							break;
						case B:
							Control.D2();
							break;
						case L:
							Control.L2();
							break;
						case R:
							Control.R2();
							break;
						default:
							break;
					}
					break;
			}
			cubie.updateCoordinates();
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

}