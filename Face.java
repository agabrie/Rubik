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
    public final int TL = 0;
    public final int T = 1;
    public final int TR = 2;
    public final int L = 3;
    public final int R = 4;
    public final int BL = 5;
    public final int B = 6;
    public final int BR = 7;

    Face(Color c, Orientation o) {
        center = new Cubie(c, o);
        setCubie(c, o);
        color = c;
        // printface();
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

        cubie[T].setAdjacents(top.cubie[B]);
        cubie[R].setAdjacents(right.cubie[L]);
        cubie[B].setAdjacents(bottom.cubie[T]);
        cubie[L].setAdjacents(left.cubie[R]);

        cubie[TL].setAdjacents(top.cubie[BL], left.cubie[TR]);
        cubie[TR].setAdjacents(top.cubie[BR], right.cubie[TL]);
        cubie[BL].setAdjacents(bottom.cubie[TL], left.cubie[BR]);
        cubie[BR].setAdjacents(bottom.cubie[TR], right.cubie[BL]);
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
        // s+="\n";
        return s;
    }
}
