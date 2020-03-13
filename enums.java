/**
 *
 * @author Abduraghmaan G
 */
enum Color {
    RED,
    GREEN,
    YELLOW,
    BLUE,
    WHITE,
    ORANGE
}

enum Orientation {
    UP,
    DOWN,
    LEFT,
    RIGHT,
    FRONT,
    BACK
}

enum Coloreths {
    Black("\u001b[30m"),
    // Red		("\u001b[31m"),
    Red("\033[30;41m"),
    Green("\033[30;42m"), //32
    Yellow("\033[30;43m"), //33
    Blue("\033[30;104m"), //34
    Magenta("\033[30;45m"), //35
    Cyan("\033[30;46m"), //36
    White("\033[30;47m"), //37
    Orange("\033[30;45m"), //o
    Reset("\u001b[0m"); //

    public String color;

    private Coloreths(String color) {
        this.color = color;
    }
}
