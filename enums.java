enum Color{
	RED,
	GREEN,
	YELLOW,
	BLUE,
	WHITE,
	ORANGE 
}
enum Orientation{
	UP,
	DOWN,
	LEFT,
	RIGHT,
	FRONT,
	BACK
}
enum Coloreths{
Black	("\u001b[30m"),
Red		("\u001b[31m"),
Green	("\u001b[32m"),
Yellow	("\u001b[33m"),
Blue	("\u001b[34m"),
Magenta	("\u001b[35m"),
Cyan	("\u001b[36m"),
White	("\u001b[37m"),
Reset	("\u001b[0m");

public String color;
	private Coloreths(String color)
	{
		this.color = color;
	}
}