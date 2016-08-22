package term.operations.special;

import term.ITerm;
import term.operations.UnitaryOperation;
import term.variable.Variable;

public class AblNum extends UnitaryOperation{

	private final Variable var;

	private static double ablgen = 5e-5;
	private static double ablgen_two = 2 * ablgen;

	public static void setAblgen(double ablgen) {
		AblNum.ablgen = ablgen;
		ablgen_two = 2 * ablgen;
	}

	public AblNum(ITerm term, Variable var) {
		super(term);
		this.var = var;
	}

	@Override
	public double evaluate() {
		// function(f, x) { return (f(x + ablgen) - f(x - ablgen)) / ablgend;}
		double x0 = var.evaluate();
		var.assign(x0 + ablgen);
		double a = one.evaluate();
		var.assign(x0 - ablgen);
		double b = one.evaluate();

		var.assign(x0);

		return (a - b) / ablgen_two;
	}

	@Override
	public ITerm[] getTerms() {
		return new ITerm[] { one, var };
	}

	@Override
	public ITerm derivation(Variable var) {
		return new AblNum(this, var);
	}

}
