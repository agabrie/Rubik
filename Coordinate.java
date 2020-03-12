class Coordinate{
	int face;
	int cubie;
	public Coordinate(int face, int cubie){
		this.face = face;
		this.cubie = cubie;
	}
	public String toString(){
		return String.format("face:[%s] => cubie[%d]", Driver.cube.face[this.face].center.toString(), this.cubie);
	}
}