class Cubie{
    Color color;
    Orientation orientation;

    public Cubie(Color c, Orientation o){
        this.color = c;
        this.orientation = o;
        // System.out.println(colorString());
    }

    public String colorString(){
        String color;
        switch(this.color){
            case RED:
                color = Coloreths.Red.color+"[R]"+Coloreths.Reset.color;
                break;
            case GREEN:
                color = Coloreths.Green.color+"[G]"+Coloreths.Reset.color;
                break;
            case YELLOW:
                color = Coloreths.Yellow.color+"[Y]"+Coloreths.Reset.color;
                break;
            case BLUE:
                color = Coloreths.Blue.color+"[B]"+Coloreths.Reset.color;
                break;
            case WHITE:
                color = Coloreths.White.color+"[W]"+Coloreths.Reset.color;
                break;
            case ORANGE:
                color = Coloreths.Magenta.color+"[O]"+Coloreths.Reset.color;
                break;
            default :
                color = Coloreths.White.color+"[W]"+Coloreths.Reset.color;
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
        // System.out.println("something");
        return String.format("%s",color);
    }
}
