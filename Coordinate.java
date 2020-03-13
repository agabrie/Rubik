/**
 *
 * @author Abduraghmaan G
 */
public class Coordinate {

    int face;
    int cubie;

    public Coordinate(int face, int cubie) {
        this.face = face;
        this.cubie = cubie;
    }

    @Override
    public String toString() {
        return String.format("face:[%s] => cubie[%d]", Driver.cube.faces[this.face].center.toString(), this.cubie);
    }
}
