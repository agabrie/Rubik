import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author Abduraghmaan G
 */
public class Cubie {

    Color color[] = new Color[3];
    Cubie adjacents[] = new Cubie[2];
    Orientation orientation;

    Cubie(Color c, Orientation o) {
        this.color[0] = c;
        this.orientation = o;
        // System.out.println(colorString());
    }

    public void setAdjacents(Cubie edge) {
        color[1] = edge.color[0];
        adjacents[0] = edge;
    }

    public void setAdjacents(Cubie c1, Cubie c2) {
        color[1] = c1.color[0];
        color[2] = c2.color[0];
        adjacents[0] = c1;
        adjacents[1] = c2;
    }

    public String colorString() {
        String str;
        switch (this.color[0]) {
            case RED:
                str = Coloreths.Red.color + "[R]" + Coloreths.Reset.color;
                break;
            case GREEN:
                str = Coloreths.Green.color + "[G]" + Coloreths.Reset.color;
                break;
            case YELLOW:
                str = Coloreths.Yellow.color + "[Y]" + Coloreths.Reset.color;
                break;
            case BLUE:
                str = Coloreths.Blue.color + "[B]" + Coloreths.Reset.color;
                break;
            case WHITE:
                str = Coloreths.White.color + "[W]" + Coloreths.Reset.color;
                break;
            case ORANGE:
                str = Coloreths.Orange.color + "[O]" + Coloreths.Reset.color;
                break;
            default:
                str = Coloreths.White.color + "[W]" + Coloreths.Reset.color;
                break;
        }
        return str;
    }

    public String fulldetail() {
        String s = colorString();
        for (Cubie c : adjacents) {
            if (c != null) {
                s += "->" + c.colorString();
            }
        }
        return s;
    }

    public String orientationString() {
        String orient;
        switch (this.orientation) {
            case DOWN:
                orient = "DOWN";
                break;
            case LEFT:
                orient = "LEFT";
                break;
            case RIGHT:
                orient = "RIGHT";
                break;
            case FRONT:
                orient = "FRONT";
                break;
            case BACK:
                orient = "BACK";
                break;
            default:
                orient = "UP";
                break;
        }
        return orient;
    }

    @Override
    public String toString() {
        String str = colorString();
        // String orient = orientationString();
        // System.out.println("something");
        return String.format("%s", str);
    }

    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true   
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not "null instanceof [type]" also returns false */
        if (!(o instanceof Cubie)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members  
        Cubie c = (Cubie) o;
        // compare detail strings
        return (fulldetail().equals(c.fulldetail()));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Arrays.deepHashCode(this.color);
        hash = 79 * hash + Arrays.deepHashCode(this.adjacents);
        hash = 79 * hash + Objects.hashCode(this.orientation);
        return hash;
    }

}
