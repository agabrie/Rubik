package enums;

/**
 *
 * @author Abduraghmaan G
 */

public enum Coloreths {
    Black("\u001b[30m"), //
    Red("\033[30;41m"), // 41
    Green("\033[30;42m"), // 32
    Yellow("\033[30;43m"), // 33
    Blue("\033[30;104m"), // 34
    Magenta("\033[30;45m"), // 35
    Cyan("\033[30;46m"), // 36
    White("\033[30;47m"), // 37
    Orange("\033[30;101m"), // o
    //
    FRed("\033[31;40m"), // 41
    FGreen("\033[92;40m"), // 32
    FYellow("\033[33;40m"), // 33
    FBlue("\033[94;40m"), // 34
    FMagenta("\033[95;40m"), // 35
    FCyan("\033[36;40m"), // 36
    FWhite("\033[37;40m"), // 37
    FOrange("\033[91;40m"), // 101
    //
    Reset("\u001b[0m"); //

    public String color;

    private Coloreths(String color) {
        this.color = color;
    }
}
