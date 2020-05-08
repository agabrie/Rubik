package rubik;

/**
 *
 * @author Abduraghmaan G
 */
enum Color {
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
}

enum Orientation {
    UP(0), DOWN(1), LEFT(2), RIGHT(3), FRONT(4), BACK(5);

    private int value;

    private Orientation(int value) {
        this.value = value;
    }
}

enum Relation{
    TL(0),
    T(1),
    TR(2),
    L(3),
    R(4),
    BL(5),
    B(6),
    BR(7),

    TOP(0),
    BOTTOM(1),
    LEFT(2),
    RIGHT(3),
    CURRENT(4),
    OPPOSITE(5);

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

enum Coloreths {
    Black("\u001b[30m"),
    // Red ("\u001b[31m"),
    Red("\033[30;41m"), // 41
    Green("\033[30;42m"), // 32
    Yellow("\033[30;43m"), // 33
    Blue("\033[30;104m"), // 34
    Magenta("\033[30;45m"), // 35
    Cyan("\033[30;46m"), // 36
    White("\033[30;47m"), // 37
    Orange("\033[30;45m"), // o
    Reset("\u001b[0m"); //

    public String color;

    private Coloreths(String color) {
        this.color = color;
    }
}
