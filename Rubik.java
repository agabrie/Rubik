/**
 *
 * @author Abduraghmaan G
 */
public class Rubik {

    Face[] faces = new Face[6];
    public final int WHITE = 0;
    public final int RED = 1;
    public final int BLUE = 2;
    public final int GREEN = 3;
    public final int YELLOW = 4;
    public final int ORANGE = 5;

    public final int TL = 0;
    public final int T = 1;
    public final int TR = 2;
    public final int L = 3;
    public final int R = 4;
    public final int BL = 5;
    public final int B = 6;
    public final int BR = 7;

    public boolean clockwise = true;
    public boolean extra = false;

    public Rubik() {
        faces[WHITE] = new Face(Color.WHITE, Orientation.FRONT);
        faces[RED] = new Face(Color.RED, Orientation.BACK);
        faces[BLUE] = new Face(Color.BLUE, Orientation.UP);
        faces[GREEN] = new Face(Color.GREEN, Orientation.DOWN);
        faces[YELLOW] = new Face(Color.YELLOW, Orientation.LEFT);
        faces[ORANGE] = new Face(Color.ORANGE, Orientation.RIGHT);

        faces[WHITE].adjacentsAssign(faces[ORANGE], faces[BLUE], faces[RED], faces[GREEN]);
        faces[RED].adjacentsAssign(faces[WHITE], faces[BLUE], faces[YELLOW], faces[GREEN]);
        faces[BLUE].adjacentsAssign(faces[ORANGE], faces[YELLOW], faces[RED], faces[WHITE]);
        faces[GREEN].adjacentsAssign(faces[ORANGE], faces[WHITE], faces[RED], faces[YELLOW]);
        faces[YELLOW].adjacentsAssign(faces[ORANGE], faces[GREEN], faces[RED], faces[BLUE]);
        faces[ORANGE].adjacentsAssign(faces[YELLOW], faces[BLUE], faces[WHITE], faces[GREEN]);
        // System.err.print(toString());
    }

    public void F() {
        rotateFace(clockwise, WHITE);
        if (extra) {
            rotateFace(clockwise, WHITE);
        }
    }

    public void D() {
        clockwiseFace(BLUE);
        antiClockwiseFace(GREEN);

        clockwiseFace(YELLOW);
        clockwiseFace(YELLOW);

        rotateFace(clockwise, RED);
        if (extra) {
            rotateFace(clockwise, RED);
        }

        antiClockwiseFace(YELLOW);
        antiClockwiseFace(YELLOW);

        antiClockwiseFace(BLUE);
        clockwiseFace(GREEN);
    }

    public void R() {
        clockwiseFace(ORANGE);
        antiClockwiseFace(RED);
        rotateFace(clockwise, BLUE);
        if (extra) {
            rotateFace(clockwise, BLUE);
        }
        antiClockwiseFace(ORANGE);
        clockwiseFace(RED);
    }

    public void L() {
        antiClockwiseFace(ORANGE);
        clockwiseFace(RED);
        rotateFace(clockwise, GREEN);
        if (extra) {
            rotateFace(clockwise, GREEN);
        }
        clockwiseFace(ORANGE);
        antiClockwiseFace(RED);
    }

    public void B() {
        for (int i = 0; i < 2; i++) {
            clockwiseFace(ORANGE);
            antiClockwiseFace(RED);
        }
        rotateFace(clockwise, YELLOW);
        if (extra) {
            rotateFace(clockwise, YELLOW);
        }
        for (int i = 0; i < 2; i++) {
            clockwiseFace(RED);
            antiClockwiseFace(ORANGE);
        }
    }

    public void U() {
        clockwiseFace(GREEN);
        antiClockwiseFace(BLUE);

        clockwiseFace(YELLOW);
        clockwiseFace(YELLOW);

        rotateFace(clockwise, ORANGE);
        if (extra) {
            rotateFace(clockwise, ORANGE);
        }

        antiClockwiseFace(YELLOW);
        antiClockwiseFace(YELLOW);

        clockwiseFace(BLUE);
        antiClockwiseFace(GREEN);
    }

    public void rotateFace(boolean clockwise, int faceNum) {
        // Stringtemp
        // Cubie temp;
        // temp = faces[faceNum].adjacents[1].cubie[0];
        if (clockwise) {
            clockwiseAdjacents(faceNum);
            clockwiseFace(faceNum);
        } else {
            antiClockwiseAdjacents(faceNum);
            antiClockwiseFace(faceNum);
        }

    }

    public void antiClockwiseFace(int faceNum) {
        for (int i = 0; i < 3; i++) {
            clockwiseFace(faceNum);
        }
    }

    public void antiClockwiseAdjacents(int faceNum) {
        for (int i = 0; i < 3; i++) {
            clockwiseAdjacents(faceNum);
        }
    }

    public void clockwiseFace(int faceNum) {
        Cubie temp1 = faces[faceNum].cubie[BL];
        faces[faceNum].cubie[BL] = faces[faceNum].cubie[BR];
        faces[faceNum].cubie[BR] = faces[faceNum].cubie[TR];
        faces[faceNum].cubie[TR] = faces[faceNum].cubie[TL];
        faces[faceNum].cubie[TL] = temp1;

        temp1 = faces[faceNum].cubie[L];
        faces[faceNum].cubie[L] = faces[faceNum].cubie[B];
        faces[faceNum].cubie[B] = faces[faceNum].cubie[R];
        faces[faceNum].cubie[R] = faces[faceNum].cubie[T];
        faces[faceNum].cubie[T] = temp1;
        // Color temp1 = faces[faceNum].cubie[5].color;
        // faces[faceNum].cubie[5].color = faces[faceNum].cubie[7].color;
        // faces[faceNum].cubie[7].color = faces[faceNum].cubie[2].color;
        // faces[faceNum].cubie[2].color = faces[faceNum].cubie[0].color;
        // faces[faceNum].cubie[0].color = temp1;

        // temp1 = faces[faceNum].cubie[3].color;
        // faces[faceNum].cubie[3].color = faces[faceNum].cubie[6].color;
        // faces[faceNum].cubie[6].color = faces[faceNum].cubie[4].color;
        // faces[faceNum].cubie[4].color = faces[faceNum].cubie[1].color;
        // faces[faceNum].cubie[1].color = temp1;
    }

    public void clockwiseAdjacents(int faceNum) {
        Face top = faces[faceNum].adjacents[0];
        Face right = faces[faceNum].adjacents[1];
        Face bottom = faces[faceNum].adjacents[2];
        Face left = faces[faceNum].adjacents[3];
        // System.out.println(top.toString()+left.toString()+right.toString()+bottom.toString());
        Cubie temp1 = left.cubie[7];
        Cubie temp2 = left.cubie[4];
        Cubie temp3 = left.cubie[2];

        left.cubie[7] = bottom.cubie[2];
        left.cubie[4] = bottom.cubie[1];
        left.cubie[2] = bottom.cubie[0];

        bottom.cubie[2] = right.cubie[0];
        bottom.cubie[1] = right.cubie[3];
        bottom.cubie[0] = right.cubie[5];

        right.cubie[0] = top.cubie[5];
        right.cubie[3] = top.cubie[6];
        right.cubie[5] = top.cubie[7];

        top.cubie[5] = temp1;
        top.cubie[6] = temp2;
        top.cubie[7] = temp3;
        // Color temp1 = faces[faceNum].adjacents[3].cubie[7].color;
        // Color temp2 = faces[faceNum].adjacents[3].cubie[4].color;
        // Color temp3 = faces[faceNum].adjacents[3].cubie[2].color;

        // faces[faceNum].adjacents[3].cubie[7].color =
        // faces[faceNum].adjacents[2].cubie[2].color;
        // faces[faceNum].adjacents[3].cubie[4].color =
        // faces[faceNum].adjacents[2].cubie[1].color;
        // faces[faceNum].adjacents[3].cubie[2].color =
        // faces[faceNum].adjacents[2].cubie[0].color;
        // faces[faceNum].adjacents[2].cubie[2].color =
        // faces[faceNum].adjacents[1].cubie[0].color;
        // faces[faceNum].adjacents[2].cubie[1].color =
        // faces[faceNum].adjacents[1].cubie[3].color;
        // faces[faceNum].adjacents[2].cubie[0].color =
        // faces[faceNum].adjacents[1].cubie[5].color;
        // faces[faceNum].adjacents[1].cubie[0].color =
        // faces[faceNum].adjacents[0].cubie[5].color;
        // faces[faceNum].adjacents[1].cubie[3].color =
        // faces[faceNum].adjacents[0].cubie[6].color;
        // faces[faceNum].adjacents[1].cubie[5].color =
        // faces[faceNum].adjacents[0].cubie[7].color;
        // faces[faceNum].adjacents[0].cubie[5].color = temp1;
        // faces[faceNum].adjacents[0].cubie[6].color = temp2;
        // faces[faceNum].adjacents[0].cubie[7].color = temp3;
    }

    @Override
    public String toString() {
        String s = "";

        for (int x = 0; x < 6; x++) {
            s += faces[x].toString() + "\n";
        }
        return s;
    }

    public Cubie cubieAt(Coordinate c) {
        return faces[c.face].cubie[c.cubie];
    }

    public Coordinate findEdge(Cubie edge) {
        // Rubik cube = Driver.cube;
        // ArrayList<>
        int f = 0;
        for (Face face : faces) {
            // System.out.println("looking in faces :"+faces.center.colorString());
            int c = 0;
            for (Cubie cubie : face.cubie) {
                // System.out.println("looking at cubie :"+cubie.fulldetail());
                if (cubie.equals(edge)) {
                    Coordinate p = new Coordinate(f, c);
                    return (p);
                }
                c++;
            }
            f++;
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /*
         * Check if o is an instance of Complex or not "null instanceof [type]" also
         * returns false
         */
        if (!(o instanceof Rubik)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Rubik c = (Rubik) o;
        // compare detail strings
        return (toString().equals(c.toString()));
    }
}
