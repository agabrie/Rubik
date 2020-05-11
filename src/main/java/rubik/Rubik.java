package rubik;

import enums.*;

public class Rubik {

    public Face[] faces = new Face[6];

    public boolean clockwise = true;
    public boolean extra = false;

    public Rubik() {
        faces[Color.WHITE.value] = new Face(Color.WHITE, Orientation.FRONT);
        faces[Color.RED.value] = new Face(Color.RED, Orientation.BACK);
        faces[Color.BLUE.value] = new Face(Color.BLUE, Orientation.UP);
        faces[Color.GREEN.value] = new Face(Color.GREEN, Orientation.DOWN);
        faces[Color.YELLOW.value] = new Face(Color.YELLOW, Orientation.LEFT);
        faces[Color.ORANGE.value] = new Face(Color.ORANGE, Orientation.RIGHT);

        faces[Color.WHITE.value].adjacentsAssign(faces[Color.ORANGE.value], faces[Color.BLUE.value],
                faces[Color.RED.value], faces[Color.GREEN.value]);
        faces[Color.RED.value].adjacentsAssign(faces[Color.WHITE.value], faces[Color.BLUE.value],
                faces[Color.YELLOW.value], faces[Color.GREEN.value]);
        faces[Color.BLUE.value].adjacentsAssign(faces[Color.ORANGE.value], faces[Color.YELLOW.value],
                faces[Color.RED.value], faces[Color.WHITE.value]);
        faces[Color.GREEN.value].adjacentsAssign(faces[Color.ORANGE.value], faces[Color.WHITE.value],
                faces[Color.RED.value], faces[Color.YELLOW.value]);
        faces[Color.YELLOW.value].adjacentsAssign(faces[Color.ORANGE.value], faces[Color.GREEN.value],
                faces[Color.RED.value], faces[Color.BLUE.value]);
        faces[Color.ORANGE.value].adjacentsAssign(faces[Color.YELLOW.value], faces[Color.BLUE.value],
                faces[Color.WHITE.value], faces[Color.GREEN.value]);
    }

    public void F() {
        rotateFace(clockwise, Color.WHITE);
        if (extra) {
            rotateFace(clockwise, Color.WHITE);
        }
    }

    public void D() {
        clockwiseFace(Color.BLUE);
        antiClockwiseFace(Color.GREEN);

        clockwiseFace(Color.YELLOW);
        clockwiseFace(Color.YELLOW);

        rotateFace(clockwise, Color.RED);
        if (extra) {
            rotateFace(clockwise, Color.RED);
        }

        antiClockwiseFace(Color.YELLOW);
        antiClockwiseFace(Color.YELLOW);

        antiClockwiseFace(Color.BLUE);
        clockwiseFace(Color.GREEN);
    }

    public void R() {
        clockwiseFace(Color.ORANGE);
        antiClockwiseFace(Color.RED);
        rotateFace(clockwise, Color.BLUE);
        if (extra) {
            rotateFace(clockwise, Color.BLUE);
        }
        antiClockwiseFace(Color.ORANGE);
        clockwiseFace(Color.RED);
    }

    public void L() {
        antiClockwiseFace(Color.ORANGE);
        clockwiseFace(Color.RED);
        rotateFace(clockwise, Color.GREEN);
        if (extra) {
            rotateFace(clockwise, Color.GREEN);
        }
        clockwiseFace(Color.ORANGE);
        antiClockwiseFace(Color.RED);
    }

    public void B() {
        for (int i = 0; i < 2; i++) {
            clockwiseFace(Color.ORANGE);
            antiClockwiseFace(Color.RED);
        }
        rotateFace(clockwise, Color.YELLOW);
        if (extra) {
            rotateFace(clockwise, Color.YELLOW);
        }
        for (int i = 0; i < 2; i++) {
            clockwiseFace(Color.RED);
            antiClockwiseFace(Color.ORANGE);
        }
    }

    public void U() {
        clockwiseFace(Color.GREEN);
        antiClockwiseFace(Color.BLUE);

        clockwiseFace(Color.YELLOW);
        clockwiseFace(Color.YELLOW);

        rotateFace(clockwise, Color.ORANGE);
        if (extra) {
            rotateFace(clockwise, Color.ORANGE);
        }

        antiClockwiseFace(Color.YELLOW);
        antiClockwiseFace(Color.YELLOW);

        clockwiseFace(Color.BLUE);
        antiClockwiseFace(Color.GREEN);
    }

    public void rotateFace(boolean clockwise, Color faceNum) {
        if (clockwise) {
            clockwiseAdjacents(faceNum);
            clockwiseFace(faceNum);
        } else {
            antiClockwiseAdjacents(faceNum);
            antiClockwiseFace(faceNum);
        }

    }

    public void antiClockwiseFace(Color face_num) {
        for (int i = 0; i < 3; i++) {
            clockwiseFace(face_num);
        }
    }

    public void antiClockwiseAdjacents(Color faceNum) {
        for (int i = 0; i < 3; i++) {
            clockwiseAdjacents(faceNum);
        }
    }

    public void clockwiseFace(Color face_num) {
        int faceNum = face_num.value;
        Cubie temp1 = faces[faceNum].cubie[Relation.BL.value];
        faces[faceNum].cubie[Relation.BL.value] = faces[faceNum].cubie[Relation.BR.value];
        faces[faceNum].cubie[Relation.BR.value] = faces[faceNum].cubie[Relation.TR.value];
        faces[faceNum].cubie[Relation.TR.value] = faces[faceNum].cubie[Relation.TL.value];
        faces[faceNum].cubie[Relation.TL.value] = temp1;

        temp1 = faces[faceNum].cubie[Relation.L.value];
        faces[faceNum].cubie[Relation.L.value] = faces[faceNum].cubie[Relation.B.value];
        faces[faceNum].cubie[Relation.B.value] = faces[faceNum].cubie[Relation.R.value];
        faces[faceNum].cubie[Relation.R.value] = faces[faceNum].cubie[Relation.T.value];
        faces[faceNum].cubie[Relation.T.value] = temp1;

    }

    public void clockwiseAdjacents(Color face_num) {
        int faceNum = face_num.value;
        Face top = faces[faceNum].adjacents[0];
        Face right = faces[faceNum].adjacents[1];
        Face bottom = faces[faceNum].adjacents[2];
        Face left = faces[faceNum].adjacents[3];

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
        return faces[c.face.value].cubie[c.position.value];
    }

    public Coordinate findCoordinate(Cubie edge) {
        int f = 0;
        Color color = Color.WHITE;
        for (Face face : faces) {
            int c = 0;
            Relation r = Relation.B;
            for (Cubie cubie : face.cubie) {
                if (cubie.equals(edge)) {
                    Coordinate p = new Coordinate(color.getFace(f), r.getCubie(c));
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

        if (o == this) {
            return true;
        }

        if (!(o instanceof Rubik)) {
            return false;
        }

        Rubik c = (Rubik) o;

        return (toString().equals(c.toString()));
    }
}
