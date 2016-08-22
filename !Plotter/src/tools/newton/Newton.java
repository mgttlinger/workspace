package tools.newton;

import term.ITerm;
import term.operations.binary.Division;
import term.variable.Variable;

public class Newton {
	private static int maxIterations = 1000;

	public static double solve(ITerm term, Variable x, double start) {
		ITerm newtonFunction = new Division(term, term.derivation(x));
		for (int i = 0; i < maxIterations; i++) {
			x.assign(start);
			start -= newtonFunction.evaluate();
		}
		return start;
	}

}
