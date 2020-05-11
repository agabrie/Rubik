package solver;

import rubik.Driver;
import java.util.List;

public class Solver {
	public void simplesolve(List<String> instructions) {

		LayerSolution layers[] = { new WhiteCross(), new WhiteCorners(), new SecondLayer(), new YellowCross(),
				new YellowCorners() };
		for (LayerSolution layer : layers) {
			layer.solve();
		}
		Driver.displayResults("Final Solution");
	}
}
