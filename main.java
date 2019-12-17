enum Color{
    RED,
    GREEN,
    YELLOW,
    BLUE,
    WHITE,
    ORANGE 
}
enum Orientation{
    UP,
    DOWN,
    LEFT,
    RIGHT,
    FRONT,
    BACK
}

class Cubie{
    Color color;
    Orientation orientation;

    public Cubie(Color c, Orientation o){
        this.color = c;
        this.orientation = o;
    }

    public String colorString(){
        String color;
        switch(this.color){
            case RED:
                color = "R";
                break;
            case GREEN:
                color = "G";
                break;
            case YELLOW:
                color = "Y";
                break;
            case BLUE:
                color = "B";
                break;
            case WHITE:
                color = "W";
                break;
            case ORANGE:
                color = "O";
                break;
            default :
                color = "W";
                break;
        }
        return color;
    }
    public String orientationString(){
        String orient;
        switch(this.orientation){
            case DOWN:
                orient = "DOWN";
                break;
            case LEFT:
                orient = "LEFT";
                break;
            case RIGHT:
                orient = "RIGHT";
                break;
            case FRONT:
                orient = "FRONT";
                break;
            case BACK:
                orient = "BACK";
                break;
            default:
                orient = "UP";
                break;
        }
        return orient;
    }
    
    public String toString(){
        String color = colorString();
        // String orient = orientationString();
        return String.format("[%s]",color);
    }
}

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
class Driver{
    public static void main(String [] args){
        // Face face =  new Face(Color.BLUE,Orientation.FRONT);
        Rubik cube = new Rubik();
        // System.out.println(cube);
    }
}