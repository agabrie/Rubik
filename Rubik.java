class Rubik{
    Face face[] = new Face[6];
    public final int WHITE = 0;
    public final int RED = 1;
    public final int BLUE = 2;
    public final int GREEN = 3;
    public final int YELLOW = 4;
    public final int ORANGE = 5;


    public Rubik(){
            face[WHITE] = new Face(Color.WHITE, Orientation.FRONT);
            face[RED] = new Face(Color.RED, Orientation.BACK);
            face[BLUE] = new Face(Color.BLUE, Orientation.UP);
            face[GREEN] = new Face(Color.GREEN, Orientation.DOWN);
            face[YELLOW] = new Face(Color.YELLOW, Orientation.LEFT);
            face[ORANGE] = new Face(Color.ORANGE, Orientation.RIGHT);
            
            face[WHITE].adjacentsAssign(face[ORANGE], face[BLUE], face[RED], face[GREEN]);
            face[RED].adjacentsAssign(face[WHITE], face[BLUE], face[YELLOW], face[GREEN]);
            face[BLUE].adjacentsAssign(face[ORANGE], face[YELLOW], face[RED], face[WHITE]);
            face[GREEN].adjacentsAssign(face[ORANGE], face[WHITE], face[RED], face[YELLOW]);
            face[YELLOW].adjacentsAssign(face[ORANGE], face[GREEN], face[RED], face[BLUE]);
            face[ORANGE].adjacentsAssign(face[YELLOW], face[BLUE], face[WHITE], face[GREEN]);
            // System.err.print(toString());
    }
    public void F(boolean clockwise,boolean extra){
        rotateFace(clockwise, WHITE);
        if(extra)
            rotateFace(clockwise, WHITE);
    }
    public void D(boolean clockwise,boolean extra){
        clockwiseFace(BLUE);
        antiClockwiseFace(GREEN);

        clockwiseFace(YELLOW);
        clockwiseFace(YELLOW);

        rotateFace(clockwise, RED);
        if(extra)
            rotateFace(clockwise, RED);
        
        antiClockwiseFace(YELLOW);
        antiClockwiseFace(YELLOW);
        
        
        antiClockwiseFace(BLUE);
        clockwiseFace(GREEN);
    }
    public void R(boolean clockwise,boolean extra){
        clockwiseFace(ORANGE);
        antiClockwiseFace(RED);
        rotateFace(clockwise, BLUE);
        if(extra)
            rotateFace(clockwise, BLUE);
        antiClockwiseFace(ORANGE);
        clockwiseFace(RED);
    }
    public void L(boolean clockwise,boolean extra){
        antiClockwiseFace(ORANGE);
        clockwiseFace(RED);
        rotateFace(clockwise, GREEN);
        if(extra)
            rotateFace(clockwise, GREEN);
        clockwiseFace(ORANGE);
        antiClockwiseFace(RED);
    }
    public void B(boolean clockwise,boolean extra){
        for (int i = 0; i < 2; i++) {   
            clockwiseFace(ORANGE);
            antiClockwiseFace(RED);
        }
        rotateFace(clockwise, YELLOW);
        if(extra)
            rotateFace(clockwise, YELLOW);
        for (int i = 0; i < 2; i++) {   
            clockwiseFace(RED);
            antiClockwiseFace(ORANGE);
        }
    }
    public void U(boolean clockwise,boolean extra){
        clockwiseFace(GREEN);
        antiClockwiseFace(BLUE);

        clockwiseFace(YELLOW);
        clockwiseFace(YELLOW);
        
        rotateFace(clockwise, ORANGE);
        if(extra)
            rotateFace(clockwise, ORANGE);
        
        antiClockwiseFace(YELLOW);
        antiClockwiseFace(YELLOW);
        
        clockwiseFace(BLUE);
        antiClockwiseFace(GREEN);
    }
    public void rotateFace(boolean clockwise,int faceNum){
        // Stringtemp
        // Cubie temp;
        // temp = face[faceNum].adjacents[1].cubie[0];
        if(clockwise){
            clockwiseAdjacents(faceNum);
            clockwiseFace(faceNum);
        }else{
            antiClockwiseAdjacents(faceNum);
            antiClockwiseFace(faceNum);
        }
        
    }
    public void antiClockwiseFace(int faceNum){
        for (int i = 0; i < 3; i++) {
            clockwiseFace(faceNum);
        }
    }
    public void antiClockwiseAdjacents(int faceNum){
        for (int i = 0; i < 3; i++) {
            clockwiseAdjacents(faceNum);
        }
    }
    public void clockwiseFace(int faceNum){
        Color temp1 = face[faceNum].cubie[5].color;  
        face[faceNum].cubie[5].color =  face[faceNum].cubie[7].color;
        face[faceNum].cubie[7].color =  face[faceNum].cubie[2].color;
        face[faceNum].cubie[2].color =  face[faceNum].cubie[0].color;
        face[faceNum].cubie[0].color =  temp1;

        temp1 = face[faceNum].cubie[3].color;  
        face[faceNum].cubie[3].color =  face[faceNum].cubie[6].color;
        face[faceNum].cubie[6].color =  face[faceNum].cubie[4].color;
        face[faceNum].cubie[4].color =  face[faceNum].cubie[1].color;
        face[faceNum].cubie[1].color =  temp1;
    }

    public void clockwiseAdjacents(int faceNum){
        Color temp1 = face[faceNum].adjacents[3].cubie[7].color;
        Color temp2 = face[faceNum].adjacents[3].cubie[4].color;
        Color temp3 = face[faceNum].adjacents[3].cubie[2].color;        
        
        face[faceNum].adjacents[3].cubie[7].color = face[faceNum].adjacents[2].cubie[2].color;
        face[faceNum].adjacents[3].cubie[4].color = face[faceNum].adjacents[2].cubie[1].color;
        face[faceNum].adjacents[3].cubie[2].color = face[faceNum].adjacents[2].cubie[0].color;

        face[faceNum].adjacents[2].cubie[2].color = face[faceNum].adjacents[1].cubie[0].color;
        face[faceNum].adjacents[2].cubie[1].color = face[faceNum].adjacents[1].cubie[3].color;
        face[faceNum].adjacents[2].cubie[0].color = face[faceNum].adjacents[1].cubie[5].color;

        face[faceNum].adjacents[1].cubie[0].color = face[faceNum].adjacents[0].cubie[5].color;
        face[faceNum].adjacents[1].cubie[3].color = face[faceNum].adjacents[0].cubie[6].color;
        face[faceNum].adjacents[1].cubie[5].color = face[faceNum].adjacents[0].cubie[7].color;

        face[faceNum].adjacents[0].cubie[5].color = temp1;
        face[faceNum].adjacents[0].cubie[6].color = temp2;
        face[faceNum].adjacents[0].cubie[7].color = temp3;
    }
    public String toString(){
        String s = "";

        for(int x = 0; x < 6;x++){
            s += face[x].toString()+"\n";
        }
        return s;
    }
}
