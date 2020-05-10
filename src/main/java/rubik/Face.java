package rubik;

/**
 *
 * @author Abduraghmaan G
 */
public final class Face {

    public int numcubies = 8;
    Cubie cubie[] = new Cubie[numcubies];
    Face adjacents[] = new Face[4];
    Cubie center;
    Color color;

    Face(Color c, Orientation o) {
        center = new Cubie(c, o);
        setCubie(c, o);
        color = c;
    }

    public Cubie getCubie(Relation r) {
        return cubie[r.value];
    }

    /**
     *
     * @param c
     * @param o
     */
    void setCubie(Color c, Orientation o) {
        for (int i = 0; i < numcubies; i++) {
            cubie[i] = new Cubie(c, o);
        }
    }

    public void adjacentsAssign(Face top, Face right, Face bottom, Face left) {
        adjacents[0] = top;
        adjacents[1] = right;
        adjacents[2] = bottom;
        adjacents[3] = left;

        cubie[Relation.T.value].setAdjacents(top.cubie[Relation.B.value]);
        cubie[Relation.R.value].setAdjacents(right.cubie[Relation.L.value]);
        cubie[Relation.B.value].setAdjacents(bottom.cubie[Relation.T.value]);
        cubie[Relation.L.value].setAdjacents(left.cubie[Relation.R.value]);

        cubie[Relation.TL.value].setAdjacents(top.cubie[Relation.BL.value], left.cubie[Relation.TR.value]);
        cubie[Relation.TR.value].setAdjacents(top.cubie[Relation.BR.value], right.cubie[Relation.TL.value]);
        cubie[Relation.BL.value].setAdjacents(bottom.cubie[Relation.TL.value], left.cubie[Relation.BR.value]);
        cubie[Relation.BR.value].setAdjacents(bottom.cubie[Relation.TR.value], right.cubie[Relation.BL.value]);
    }

    public void printface() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < numcubies; i++) {
            if (i == 4) {
                s += center.toString();
            }
            if (i == 2 || i == 4 || i == 7) {
                s += cubie[i].toString() + "\n";
            } else {
                s += cubie[i].toString();
            }
        }
        return s;
    }
}
