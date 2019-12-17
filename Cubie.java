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


