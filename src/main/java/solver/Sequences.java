package solver;

public class Sequences {
	static void RTrigger() {
		Control.R();
		Control.U();
		Control.Ri();
		Control.Ui();
	}

	static void LTrigger() {
		Control.Li();
		Control.Ui();
		Control.L();
		Control.U();
	}

	static void Top2RightEdge() {
		// U R U' R' U' F' U F
		Control.U();
		Control.R();
		Control.Ui();
		Control.Ri();
		Control.Ui();
		Control.Fi();
		Control.U();
		Control.F();
	}

	static void Top2LeftEdge() {
		// U' L' U L U F U' F'
		Control.Ui();
		Control.Li();
		Control.U();
		Control.L();
		Control.U();
		Control.F();
		Control.Ui();
		Control.Fi();
	}

	static void Bottom2LeftEdge() {
		// D L D' L' D' F' D F
		Control.D();
		Control.L();
		Control.Di();
		Control.Li();
		Control.Di();
		Control.Fi();
		Control.D();
		Control.F();
	}

	static void Bottom2RightEdge() {
		// D' R' D R D F D' F'
		Control.Di();
		Control.Ri();
		Control.D();
		Control.R();
		Control.D();
		Control.F();
		Control.Di();
		Control.Fi();
	}

	static void Left2TopEdge() {
		// B U B' U' B' L' B L
		Control.L();
		Control.U();
		Control.Li();
		Control.Ui();
		Control.Li();
		Control.Fi();
		Control.L();
		Control.F();
	}

	static void Left2BottomEdge() {
		// B' D' B D B L B' L'
		Control.Di();
		Control.L();
		Control.D();
		Control.L();
		Control.F();
		Control.Li();
		Control.Fi();
	}

	static void Right2TopEdge() {
		// B' U' B U B R B' R'
		Control.Ri();
		Control.Ui();
		Control.R();
		Control.U();
		Control.R();
		Control.F();
		Control.Ri();
		Control.Fi();
	}

	static void Right2BottomEdge() {
		// B D B' D' B' R' B R
		Control.R();
		Control.D();
		Control.Ri();
		Control.Di();
		Control.Ri();
		Control.Fi();
		Control.R();
		Control.F();
	}

	static void OrientTopEdge() {
		// // F R U R' U' F'
		Control.F();
		Control.R();
		Control.U();
		Control.Ri();
		Control.Ui();
		Control.Fi();
	}

	static void swapTopEdgeClockwise() {
		// R U R' U R U2 R' U
		Control.R();
		Control.U();
		Control.Ri();
		Control.U();
		Control.R();
		Control.U2();
		Control.Ri();
		Control.U();
	}

	static void swapBottomEdgeClockwise() {
		// L D L' D L D2 L' D
		Control.L();
		Control.D();
		Control.Li();
		Control.D();
		Control.L();
		Control.D2();
		Control.Li();
		Control.D();
	}

	static void swapLeftEdgeClockwise() {
		// U L U' L U L2 U' L
		Control.U();
		Control.L();
		Control.Ui();
		Control.L();
		Control.U();
		Control.L2();
		Control.Ui();
		Control.L();

	}

	static void swapRightEdgeClockwise() {
		// D R D' R D R2 D' R
		Control.D();
		Control.R();
		Control.Di();
		Control.R();
		Control.D();
		Control.R2();
		Control.Di();
		Control.R();
	}

	static void swapTopEdgeCounter() {
		// L' U' L U' L' U2 L U'
		Control.Li();
		Control.Ui();
		Control.L();
		Control.Ui();
		Control.Li();
		Control.U2();
		Control.L();
		Control.Ui();

	}

	static void swapBottomEdgeCounter() {
		// R' D' R D' R' D2 R D'
		Control.Ri();
		Control.Di();
		Control.R();
		Control.Di();
		Control.Ri();
		Control.D2();
		Control.R();
		Control.Di();
	}

	static void swapLeftEdgeCounter() {
		// D' L' D L' D' L2 D L'
		Control.Di();
		Control.Li();
		Control.D();
		Control.Li();
		Control.Di();
		Control.L2();
		Control.D();
		Control.Li();
	}

	static void swapRightEdgeCounter() {
		// U' R' U R' U' R2 U R'
		Control.Ui();
		Control.Li();
		Control.U();
		Control.Li();
		Control.Ui();
		Control.L2();
		Control.U();
		Control.Li();
	}

	static void repositionTLAnchor() {
		// U R U' L' U R' U' L
		Control.U();
		Control.R();
		Control.Ui();
		Control.Li();

		Control.U();
		Control.Ri();
		Control.Ui();
		Control.L();
	}

	static void repositionTRAnchor() {

		// L U L' D' L U' L' D
		Control.L();
		Control.U();
		Control.Li();
		Control.Di();

		Control.L();
		Control.Ui();
		Control.Li();
		Control.D();
	}

	static void repositionBLAnchor() {

		// R D R' U' R D' R' U
		Control.R();
		Control.D();
		Control.Ri();
		Control.Ui();

		Control.R();
		Control.Di();
		Control.Ri();
		Control.U();
	}

	static void repositionBRAnchor() {

		// D L D' R' D L' D' R
		Control.D();
		Control.L();
		Control.Di();
		Control.Ri();

		Control.D();
		Control.Li();
		Control.Di();
		Control.R();
	}

	static void reorientCornerTR() {
		// R' D' R D
		Control.Ri();
		Control.Di();
		Control.R();
		Control.D();
	}
}