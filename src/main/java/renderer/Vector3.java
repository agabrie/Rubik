package renderer;
class Vector3 {
    float x;
    float y;
    float z;

    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    @Override
    public String toString(){
        return String.format("( %.1f, %.1f, %.1f)",this.x,this.y,this.z);
    }

    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /*
         * Check if o is an instance of Complex or not "null instanceof [type]" also
         * returns false
         */
        if (!(o instanceof Vector3)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Vector3 v = (Vector3) o;
        // compare detail strings
        return (v.x == this.x && v.y == this.y && v.z == this.z);
    }
}