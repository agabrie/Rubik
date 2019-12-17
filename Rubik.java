class Rubik{
    Face face[] = new Face[6];
    public Rubik(){
            face[0] = new Face(Color.WHITE, Orientation.FRONT);
            face[1] = new Face(Color.RED, Orientation.BACK);
            face[2] = new Face(Color.BLUE, Orientation.UP);
            face[3] = new Face(Color.GREEN, Orientation.DOWN);
            face[4] = new Face(Color.YELLOW, Orientation.LEFT);
            face[5] = new Face(Color.ORANGE, Orientation.RIGHT);
    }
}
