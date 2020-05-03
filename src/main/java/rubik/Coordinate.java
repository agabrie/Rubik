
/**
 *
 * @author Abduraghmaan G
 */
package rubik;
 public class Coordinate {
    Color face;
    Relation cubie;

    public Coordinate(Color face, Relation cubie) {
        this.face = face;
        this.cubie = cubie;
    }

    public Relation getRelevantFace(Coordinate other) {
        Relation relevant;
        switch (this.face) {
            default:
                relevant = relativeToWhite(other.face);
                break;
            case BLUE:
                relevant = relativeToBlue(other.face);
                break;
            case GREEN:
                relevant = relativeToGreen(other.face);
                break;
            case ORANGE:
                relevant = relativeToOrange(other.face);
                break;
            case RED:
                relevant = relativeToRed(other.face);
                break;
            case YELLOW:
                relevant = relativeToYellow(other.face);
                break;
        }
        return relevant;
    }

    public Relation relativeToWhite(Color face) {
        Relation relative;
        switch (face) {
            case GREEN:
                relative = Relation.LEFT;
                break;
            case BLUE:
                relative = Relation.RIGHT;
                break;
            case ORANGE:
                relative = Relation.TOP;
                break;
            case RED:
                relative = Relation.BOTTOM;
                break;
            case YELLOW:
                relative = Relation.OPPOSITE;
                break;
            default:
                relative = Relation.CURRENT;
                break;
        }
        return relative;
    }

    public Relation relativeToYellow(Color face) {
        Relation relative;
        switch (face) {
            case GREEN:
                relative = Relation.RIGHT;
                break;
            case BLUE:
                relative = Relation.LEFT;
                break;
            case ORANGE:
                relative = Relation.TOP;
                break;
            case RED:
                relative = Relation.BOTTOM;
                break;
            case YELLOW:
                relative = Relation.CURRENT;
                break;
            default:
                relative = Relation.OPPOSITE;
                break;
        }
        return relative;
    }

    public Relation relativeToGreen(Color face) {
        Relation relative;
        switch (face) {
            case GREEN:
                relative = Relation.CURRENT;
                break;
            case BLUE:
                relative = Relation.OPPOSITE;
                break;
            case ORANGE:
                relative = Relation.TOP;
                break;
            case RED:
                relative = Relation.BOTTOM;
                break;
            case YELLOW:
                relative = Relation.LEFT;
                break;
            default:
                relative = Relation.RIGHT;
                break;
        }
        return relative;
    }

    public Relation relativeToBlue(Color face) {
        Relation relative;
        switch (face) {
            case GREEN:
                relative = Relation.OPPOSITE;
                break;
            case BLUE:
                relative = Relation.CURRENT;
                break;
            case ORANGE:
                relative = Relation.TOP;
                break;
            case RED:
                relative = Relation.BOTTOM;
                break;
            case YELLOW:
                relative = Relation.RIGHT;
                break;
            default:
                relative = Relation.LEFT;
                break;
        }
        return relative;
    }

    public Relation relativeToOrange(Color face) {
        Relation relative;
        switch (face) {
            case GREEN:
                relative = Relation.LEFT;
                break;
            case BLUE:
                relative = Relation.RIGHT;
                break;
            case ORANGE:
                relative = Relation.CURRENT;
                break;
            case RED:
                relative = Relation.OPPOSITE;
                break;
            case YELLOW:
                relative = Relation.TOP;
                break;
            default:
                relative = Relation.BOTTOM;
                break;
        }
        return relative;
    }

    public Relation relativeToRed(Color face) {
        Relation relative;
        switch (face) {
            case GREEN:
                relative = Relation.LEFT;
                break;
            case BLUE:
                relative = Relation.RIGHT;
                break;
            case ORANGE:
                relative = Relation.OPPOSITE;
                break;
            case RED:
                relative = Relation.CURRENT;
                break;
            case YELLOW:
                relative = Relation.BOTTOM;
                break;
            default:
                relative = Relation.TOP;
                break;
        }
        return relative;
    }

    @Override
    public String toString() {
        return String.format("face:[%s] => cubie[%d]", Driver.cube.faces[this.face.value].center.toString(),
                this.cubie.value);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Coordinate)) {
            return false;
        }
        Coordinate c = (Coordinate) o;
        // compare detail strings
        if (c.face == this.face && c.cubie == this.cubie)
            return true;
        return false;
    }
}
