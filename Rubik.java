class Rubik{
    Face face[] = new Face[6];
    public Rubik(){
            face[0] = new Face(Color.WHITE, Orientation.FRONT);
            face[1] = new Face(Color.RED, Orientation.BACK);
            face[2] = new Face(Color.BLUE, Orientation.UP);
            face[3] = new Face(Color.GREEN, Orientation.DOWN);
            face[4] = new Face(Color.YELLOW, Orientation.LEFT);
            face[5] = new Face(Color.ORANGE, Orientation.RIGHT);
            
            face[0].adjacentsAssign(face[5], face[2], face[1], face[3]);
            face[1].adjacentsAssign(face[0], face[2], face[4], face[3]);
            face[2].adjacentsAssign(face[5], face[4], face[1], face[0]);
            face[3].adjacentsAssign(face[5], face[0], face[1], face[4]);
            face[4].adjacentsAssign(face[5], face[3], face[1], face[2]);
            face[5].adjacentsAssign(face[4], face[2], face[0], face[3]);
            System.err.print(toString());
    }

    public void rotateFace(boolean clockwise,int faceNum){
        // Stringtemp
        // Cubie temp;
        // temp = face[faceNum].adjacents[1].cubie[0];
        if(clockwise){
            clockwiseAdjacents(faceNum);
            clockwiseFace(faceNum);
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
