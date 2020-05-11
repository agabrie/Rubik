package solver;

import java.util.ArrayList;
import java.util.List;
import enums.*;
import rubik.*;

public abstract class LayerSolution {
	List<Cubie> tosolve = new ArrayList<Cubie>();

	boolean testPosition() {
		for (Cubie c : tosolve) {
			c.updateCoordinates();
			if (!c.testCoordinate())
				return false;
		}
		return true;
	}

	abstract void solve();

	Relation getCorrectPosition(Coordinate expected) {
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
}