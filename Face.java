class Face{
    Cubie cubie[] = new Cubie[9];
    Face adjacents[] = new Face[4];
    public Face(Color c, Orientation o){
        int i = 0;
        for(Cubie x:cubie){
            x = new Cubie(c, o);
            i++;
            if(i % 3 == 0)
                System.out.println(x);
            else
                System.out.print(x);
            }
            System.out.println();
    }
    public void adjacentsAssign(Face top, Face left, Face right, Face bottom){
        adjacents[0] = top;
        adjacents[1] = left;
        adjacents[2] = right;
        adjacents[3] = bottom;
    }
}