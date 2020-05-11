package enums;

public enum Relation {
	TL(0), T(1), TR(2), L(3), R(4), BL(5), B(6), BR(7),

	TOP(0), BOTTOM(1), LEFT(2), RIGHT(3), CURRENT(4), OPPOSITE(5);

	public int value;

	private Relation(int value) {
		this.value = value;
	}

	public Relation getCubie(int value) {
		switch (value) {
			case 0:
				return TL;
			case 1:
				return T;
			case 2:
				return TR;
			case 3:
				return L;
			case 4:
				return R;
			case 5:
				return BL;
			case 6:
				return B;
			case 7:
				return BR;
			default:
				return TL;
		}
	}

	public int index = 0;

	public int next() {
		index++;
		return index;
	}

	public void setIndex(int value) {
		index = value;
	}
}