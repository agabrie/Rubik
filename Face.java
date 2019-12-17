class Face{
    Cubie cubie[] = new Cubie[9];
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
}