package enums;

public enum Color {
	WHITE(0, Coloreths.White, 'W'), RED(1, Coloreths.Red, 'R'), BLUE(2, Coloreths.Blue, 'B'),
	GREEN(3, Coloreths.Green, 'G'), YELLOW(4, Coloreths.Yellow, 'Y'), ORANGE(5, Coloreths.Orange, 'O');

	public int value;
	public Coloreths mod;
	public char ref;
	public String color;

	private Color(int value, Coloreths mod, char ref) {
		this.value = value;
		this.mod = mod;
		this.ref = ref;
		this.color = mod.color + "[" + ref + "]" + Coloreths.Reset.color;
	}

	public Color getFace(int value) {
		switch (value) {
			case 0:
				return WHITE;
			case 1:
				return RED;
			case 2:
				return BLUE;
			case 3:
				return GREEN;
			case 4:
				return YELLOW;
			case 5:
				return ORANGE;
			default:
				return WHITE;
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

	public Color oppositeFace() {
		Color opposite_face;
		switch (this) {
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
}