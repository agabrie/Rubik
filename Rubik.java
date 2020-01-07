class Rubik{
    Face face[] = new Face[6];
    public Rubik(){
            face[0] = new Face(Color.WHITE, Orientation.FRONT);
            face[1] = new Face(Color.RED, Orientation.BACK);
            face[2] = new Face(Color.BLUE, Orientation.UP);
            face[3] = new Face(Color.GREEN, Orientation.DOWN);
            face[4] = new Face(Color.YELLOW, Orientation.LEFT);
            face[5] = new Face(Color.ORANGE, Orientation.RIGHT);
            
            face[0].adjacentsAssign(face[5], face[3], face[2], face[1]);
            face[1].adjacentsAssign(face[0], face[3], face[2], face[4]);
            face[2].adjacentsAssign(face[5], face[0], face[4], face[1]);
            face[3].adjacentsAssign(face[5], face[4], face[0], face[1]);
            face[4].adjacentsAssign(face[5], face[2], face[3], face[1]);
            face[5].adjacentsAssign(face[4], face[3], face[2], face[0]);
    }

    public void rotateFace(int faceNum){
        // Stringtemp
        Cubie temp;
        temp = face[faceNum].adjacents[1].cubie[0];
        face[faceNum].adjacents[1].cubie[0] = face[faceNum].adjacents[1].cubie[5];
    }
}
