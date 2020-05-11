package enums;

public enum Orientation {
	UP(0), DOWN(1), LEFT(2), RIGHT(3), FRONT(4), BACK(5);

	private int value;

	private Orientation(int value) {
		this.value = value;
	}
}