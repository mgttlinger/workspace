package solver;

import model.field.ModelField;
import solver.logicalMethods.MethodList;
import solver.logicalMethods.SolvingMethod;

public class LogicalSolver {
	public static void run(ModelField f) {
		MethodList methods = new MethodList(f.getDimensions());

		Methods: while (!f.finished()) {
			for (SolvingMethod method : methods)
				if (method.solve(f))
					continue Methods;
			break Methods;
		}
	}
}
