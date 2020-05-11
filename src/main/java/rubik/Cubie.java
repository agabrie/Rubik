package rubik;

import enums.*;

public class Cubie {

    public Color color[] = new Color[3];
    Cubie adjacents[] = new Cubie[2];
    Orientation orientation;
    Coordinate currentCoordinate;
    Coordinate expectedCoordinate;

    Cubie(Color c, Orientation o) {
        this.color[0] = c;
        this.orientation = o;
    }

    public void setAdjacents(Cubie edge) {
        color[1] = edge.color[0];
        adjacents[0] = edge;
    }

    public Relation getCurrentPosition() {
        updateCoordinates();
        return currentCoordinate.position;
    }

    public Relation getExpectedPosition() {
        return expectedCoordinate.position;
    }

    public Color getCurrentFace() {
        updateCoordinates();
        return currentCoordinate.face;
    }

    public Color getExpectedFace() {
        return expectedCoordinate.face;
    }

    public boolean testFace() {
        updateCoordinates();
        return currentCoordinate.face == expectedCoordinate.face;
    }

    public boolean testPosition() {
        updateCoordinates();
        return currentCoordinate.position == expectedCoordinate.position;
    }

    public boolean testCoordinate() {
        updateCoordinates();
        return currentCoordinate.equals(expectedCoordinate);
    }

    public Relation getRelevantFace() {
        updateCoordinates();
        return expectedCoordinate.getRelevantFace(currentCoordinate);
    }

    public void updateCoordinates() {
        currentCoordinate = Driver.cube.findCoordinate(this);
        expectedCoordinate = Driver.solved.findCoordinate(this);
    }

    public Coordinate getCurrentCoordinate() {
        updateCoordinates();
        return currentCoordinate;
    }

    public Coordinate getExpectedCoordinate() {
        return expectedCoordinate;
    }

    public void setAdjacents(Cubie c1, Cubie c2) {
        color[1] = c1.color[0];
        color[2] = c2.color[0];
        adjacents[0] = c1;
        adjacents[1] = c2;
    }

    public String colorString() {
        Color c = this.color[0];
        return String.format("%s[%c]%s", c.mod.color, c.ref, Coloreths.Reset.color);
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

    public boolean testCurrentCoordinate(Color face, Relation position) {
        updateCoordinates();
        return (currentCoordinate.face == face && currentCoordinate.position == position);
    }

    @Override
    public String toString() {
        String str = colorString();
        return String.format("%s", str);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Cubie)) {
            return false;
        }
        Cubie c = (Cubie) o;
        return (fulldetail().equals(c.fulldetail()));
    }
}
