package solver;

import model.dimensions.Dimensions;
import model.field.ModelField;
import solver.logicalMethods.MethodList;
import solver.logicalMethods.SolvingMethod;

public class BackUpOfLogicalSolver extends Solver {
	private final MethodList methods;

	public BackUpOfLogicalSolver(Dimensions dim) {
		super(dim);
		methods = new MethodList(dim);
	}

	@Override
	public void run(ModelField f) {
		log("Starting Solving");

		doWhileMethods: do {
			for (SolvingMethod method : methods)
				if (method.solve(f)) {
					log(String.format("Trying Method: %30s -> Successfull", method.getClass().getName()));
					continue doWhileMethods;
				} else
					log(String.format("Trying Method: %30s", method.getClass().getName()));
			break doWhileMethods;
		} while (!f.finished());

		log("Ended Solving");
	}
}
