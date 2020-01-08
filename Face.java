class Face{
    public int numcubies = 8;
    Cubie cubie[] = new Cubie[numcubies];
    Face adjacents[] = new Face[4];
    Cubie center;
    
    public Face(Color c, Orientation o){
        center = new Cubie(c, o);
        setCubie(c, o);
        // printface();
    }
    public void setCubie(Color c, Orientation o){
        for(int i = 0;i < numcubies;i++){
            cubie[i]= new Cubie(c, o);
        }
    }
    public void adjacentsAssign(Face top, Face left, Face right, Face bottom){
        adjacents[0] = top;
        adjacents[1] = left;
        adjacents[2] = right;
        adjacents[3] = bottom;
    }

    public void printface(){
        for(int i = 0;i < numcubies;i++){
            if(i == 4)
                System.out.print(center.toString());
            if(i == 2 || i == 4 || i == 7)
                System.out.println(cubie[i].toString());
            else
                System.out.print(cubie[i].toString());
            }
            System.out.println();
    }
}