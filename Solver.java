import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.text.Position;

class Solver{
	public static int frontface = 0;
	public static final int WHITE = 0;
    public static final int RED = 1;
    public static final int BLUE = 2;
    public static final int GREEN = 3;
    public static final int YELLOW = 4;
	public static final int ORANGE = 5;

	public static final int TL = 0;
    public static final int T = 1;
    public static final int TR = 2;
    public static final int L = 3;
    public static final int R = 4;
    public static final int BL = 5;
    public static final int B = 6;
    public static final int BR = 7;

	public static HashMap<String, String> rotations;
	public static HashMap<Integer, Runnable> orient = new HashMap<Integer,Runnable>(){
		{
			put(WHITE,()->{
				rotations = new HashMap<String, String>();
				rotations.put("U", "U");
				rotations.put("L", "L");
				rotations.put("R", "R");
				rotations.put("D", "D");
				rotations.put("F", "F");
				rotations.put("B", "B");
				// System.out.println(rotations);
			});
			put(YELLOW,()->{
				rotations = new HashMap<String, String>();
				rotations.put("U", "U");
				rotations.put("L", "R");
				rotations.put("R", "L");
				rotations.put("D", "D");
				rotations.put("F", "B");
				rotations.put("B", "F");
			});
			put(GREEN,()->{
				rotations = new HashMap<String, String>();
				rotations.put("U", "U");
				rotations.put("L", "B");
				rotations.put("R", "F");
				rotations.put("D", "D");
				rotations.put("F", "L");
				rotations.put("B", "R");
			});
			put(BLUE,()->{
				rotations = new HashMap<String, String>();
				rotations.put("U", "U");
				rotations.put("L", "F");
				rotations.put("R", "B");
				rotations.put("D", "D");
				rotations.put("F", "R");
				rotations.put("B", "L");
			});
			put(ORANGE,()->{
				rotations = new HashMap<String, String>();
				rotations.put("U", "B");
				rotations.put("L", "L");
				rotations.put("R", "R");
				rotations.put("D", "F");
				rotations.put("F", "U");
				rotations.put("B", "D");
				// System.out.println(rotations);
			});
			put(RED,()->{
				rotations = new HashMap<String, String>();
				rotations.put("U", "F");
				rotations.put("L", "L");
				rotations.put("R", "R");
				rotations.put("D", "B");
				rotations.put("F", "D");
				rotations.put("B", "U");
				// System.out.println(rotations);
			});
		}
	};
	public static void switchFace(int f){
		frontface = f;
		// System.out.println("gihihihihihihhhhhiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
		orient.get(frontface).run();
	}

	static void RTrigger(){
		List<String> instructions = new ArrayList<String>();
		instructions.add(rotations.get("R"));
		instructions.add(rotations.get("U"));
		instructions.add(rotations.get("R")+"'");
		instructions.add(rotations.get("U")+"'");
		Driver.invoke(instructions);
	}
	static void LTrigger(){
		List<String> instructions = new ArrayList<String>();
		instructions.add(rotations.get("L")+"'");
		instructions.add(rotations.get("U")+"'");
		instructions.add(rotations.get("L"));
		instructions.add(rotations.get("U"));
		Driver.invoke(instructions);
	}
	static void Top2RightEdge(){
		List<String> instructions = new ArrayList<String>();
		instructions.add(rotations.get("U"));
		instructions.add(rotations.get("R"));
		instructions.add(rotations.get("U")+"'");
		instructions.add(rotations.get("R")+"'");
		instructions.add(rotations.get("U")+"'");
		instructions.add(rotations.get("F")+"'");
		instructions.add(rotations.get("U"));
		instructions.add(rotations.get("F"));
	}
	static void Top2LeftEdge(){
		List<String> instructions = new ArrayList<String>();
		instructions.add(rotations.get("U")+"'");
		instructions.add(rotations.get("L")+"'");
		instructions.add(rotations.get("U"));
		instructions.add(rotations.get("L"));
		instructions.add(rotations.get("U"));
		instructions.add(rotations.get("F"));
		instructions.add(rotations.get("U")+"'");
		instructions.add(rotations.get("F")+"'");
	}
	static void WhiteCross(){
		Face white = Driver.solved.face[WHITE];
		Cubie cross[] = {white.cubie[T],white.cubie[L], white.cubie[R], white.cubie[B]};
		for(int i = 0; i < cross.length ;i++){
			moveToFront(cross[i]);
		}
	}
	static void moveToFront(Cubie edge){
		Coordinate start = Driver.cube.findEdge(edge);
		Coordinate dest = Driver.solved.findEdge(edge);
		System.out.println("position of : "+edge.fulldetail());
		System.out.println(start);
		switchFace(start.face);
		if (start.face == dest.face){
			while(start.cubie != dest.cubie)
			{
				System.out.println(start.cubie+","+dest.cubie);
				start = Driver.cube.findEdge(edge);
				Driver.execute(rotations.get("F"));
			}
		}else{
			if(frontface != YELLOW){
				System.out.println(edge.adjacents[0].colorString());
				System.out.println(Driver.cube.face[start.face].center);
				if(edge.adjacents[0].colorString().equals( Driver.cube.face[start.face].center.colorString()))
				{
					List<String> instructions = new ArrayList<String>();
					switch (start.cubie){
						case T:
							instructions.add(rotations.get("F"));
							instructions.add(rotations.get("U")+"'");
							instructions.add(rotations.get("R"));
							instructions.add(rotations.get("U"));
							break;
						case R:
							// instructions.add(rotations.get("F"));
							instructions.add(rotations.get("U")+"'");
							instructions.add(rotations.get("R"));
							instructions.add(rotations.get("U"));
							break;
						case L:
							// instructions.add(rotations.get("F"));
							instructions.add(rotations.get("U"));
							instructions.add(rotations.get("L")+"'");
							instructions.add(rotations.get("U")+"'");
							break;
						case B:
							instructions.add(rotations.get("F"));
							instructions.add(rotations.get("U"));
							instructions.add(rotations.get("L")+"'");
							instructions.add(rotations.get("U")+"'");
							break;
						default:break;
					}
					Driver.invoke(instructions);
				}

			}
		// if(frontface == YELLOW){
		// 	Driver.execute(rotations.get("U2"));
		// }
		// else{
		// switch(start.cubie){
		// 	case L:
		// 		Driver.execute(rotations.get("L")+"'");
		// 		break;
		// 	case R:
		// 		Driver.execute(rotations.get("R"));
		// 		break;
		// 	case T:
		// 		Driver.execute(rotations.get("U")+"'");
		// 	default:break;
		// }
	}
	}
	static void simplesolve(List<String> instructions){
		switchFace(GREEN);
		RTrigger();
		WhiteCross();
		// Cubie find = Driver.solved.face[WHITE].cubie[T];
		// System.out.println("position of : "+find.fulldetail());
		// System.out.println(findEdge(find));
		// System.out.println(Driver.cube.face[0].cubie[3].fulldetail());
		// Driver.execute(rotations.get("R"));
		// Collections.reverse(instructions);
		// for(String instr : instructions){
		// 	if(instr.contains("\'")){
		// 		Driver.execute(instr.replace("\'", "2"));
		// 		Driver.execute(instr);
		// 	}
		// 	else if(instr.contains("2")){
		// 		Driver.execute(instr);
		// 	}
		// 	else
		// 		Driver.execute(instr+'\'');
		// }
	}
	

}
