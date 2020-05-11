package solver;

import enums.*;
import rubik.*;

public class SecondLayer extends LayerSolution {
	Face orange = Driver.solved.faces[Color.ORANGE.value];
	Face green = Driver.solved.faces[Color.GREEN.value];
	Face red = Driver.solved.faces[Color.RED.value];
	Face blue = Driver.solved.faces[Color.BLUE.value];

	SecondLayer() {
		tosolve.add(orange.getCubie(Relation.L));
		tosolve.add(orange.getCubie(Relation.R));
		tosolve.add(green.getCubie(Relation.T));
		tosolve.add(green.getCubie(Relation.B));
		tosolve.add(red.getCubie(Relation.L));
		tosolve.add(red.getCubie(Relation.R));
		tosolve.add(blue.getCubie(Relation.T));
		tosolve.add(blue.getCubie(Relation.B));
	}

	boolean testPosition() {
		for (Cubie c : tosolve) {
			c.updateCoordinates();
			if (!c.testCoordinate())
				return false;
		}
		return true;
	}

	public void solve() {
		while (!testPosition()) {
			for (Cubie edge : tosolve) {
				SLEdge(edge);
			}
		}
		if (Driver.parts) {
			if (Driver.output)
				System.out.println(
						Coloreths.FCyan.color + "second Layer solved : " + Coloreths.Reset.color + testPosition());
			Driver.displayResults("Second Layer");
		}
	}

	void SLEdge(Cubie edge) {
		while (!edge.testCoordinate()) {
			Control.switchFace(edge.getExpectedFace());
			if (!edge.testFace()) {
				if (edge.testCurrentCoordinate(Color.GREEN, Relation.L)
						|| edge.testCurrentCoordinate(Color.BLUE, Relation.R)
						|| edge.testCurrentCoordinate(Color.ORANGE, Relation.T)
						|| edge.testCurrentCoordinate(Color.RED, Relation.B)) {
					Control.switchFace(Color.WHITE);
					Driver.execute("B");
				} else {
					if (!repositionEdge(edge))
						break;
				}
			} else {
				switch (edge.getCurrentPosition()) {
					case T:
						topEdge(edge);
						break;
					case B:
						bottomEdge(edge);
						break;
					case L:
						leftEdge(edge);
						break;
					case R:
						rightEdge(edge);
						break;
					default:
						break;
				}
			}
			edge.updateCoordinates();
		}

	}

	boolean repositionEdge(Cubie cubie) {
		Control.switchFace(cubie.getCurrentFace());
		switch (cubie.getCurrentFace()) {
			case RED:
				if (cubie.getCurrentPosition() == Relation.R)
					Sequences.Bottom2RightEdge();
				else
					Sequences.Bottom2LeftEdge();
				break;
			case ORANGE:
				if (cubie.getCurrentPosition() == Relation.R)
					Sequences.Top2RightEdge();
				else
					Sequences.Top2LeftEdge();
				break;
			case BLUE:
				if (cubie.getCurrentPosition() == Relation.T)
					Sequences.Right2TopEdge();
				else
					Sequences.Right2BottomEdge();
				break;
			case GREEN:
				if (cubie.getCurrentPosition() == Relation.T)
					Sequences.Left2TopEdge();
				else
					Sequences.Left2BottomEdge();
				break;
			default:
				return false;
		}
		return true;
	}

	void topEdge(Cubie cubie) {
		switch (cubie.getExpectedPosition()) {
			case L:
				Sequences.Top2LeftEdge();
				break;
			case R:
				Sequences.Top2RightEdge();
				break;
			default:
				if (cubie.getExpectedFace() == Color.GREEN)
					Sequences.Left2TopEdge();
				else
					Sequences.Right2TopEdge();
				break;
		}
	}

	void bottomEdge(Cubie cubie) {
		switch (cubie.getExpectedPosition()) {
			case L:
				Sequences.Bottom2LeftEdge();
				break;
			case R:
				Sequences.Bottom2RightEdge();
				break;
			default:
				if (cubie.getCurrentFace() == Color.GREEN)
					Sequences.Left2BottomEdge();
				else
					Sequences.Right2BottomEdge();
				break;
		}
	}

	void leftEdge(Cubie cubie) {
		switch (cubie.getExpectedPosition()) {
			case T:
				Sequences.Left2TopEdge();
				break;
			case B:
				Sequences.Left2BottomEdge();
				break;
			default:
				if (cubie.getExpectedFace() == Color.ORANGE)
					Sequences.Top2LeftEdge();
				else
					Sequences.Bottom2LeftEdge();
				break;
		}
	}

	void rightEdge(Cubie cubie) {
		switch (cubie.getExpectedPosition()) {
			case T:
				Sequences.Right2TopEdge();
				break;
			case B:
				Sequences.Right2BottomEdge();
				break;
			default:
				if (cubie.getExpectedFace() == Color.ORANGE)
					Sequences.Top2RightEdge();
				else
					Sequences.Bottom2RightEdge();
				break;
		}
	}
}