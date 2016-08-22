package term.operations.special;

import static java.lang.Double.NaN;
import term.ITerm;
import term.operations.UnitaryOperation;
import term.variable.Variable;

public class Integral extends UnitaryOperation {

	private final Variable var;
	private final ITerm from, to;

	public static final double aufgen = 1e-5;

	public Integral(ITerm one, Variable var, ITerm from, ITerm to) {
		super(one);
		this.var = var;
		this.from = from;
		this.to = to;
	}

	@Override
	public ITerm[] getTerms() {
		return new ITerm[] { one, var, from, to };
	}

	@Override
	public ITerm derivation(Variable var) {
		// TODO: vollkommen unsupported
		throw new UnsupportedOperationException();
		//return Constant.NaN;
	}

	@Override
	public double evaluate() {
		// function integral(lower, upper) {
		// gen = Math.abs(upper - lower) * aufgen;
		// var b = 0;
		// for (var i = lower; i <= upper; i += gen) b += this(i);
		// return b * gen;
		// }
		double from = this.from.evaluate();
		double to = this.to.evaluate();
		if (from >= to)
			return NaN;

		double gen = (to - from) * aufgen;
		double sum = 0;
		for (double x = from; x < to; x += gen) {
			var.assign(x);
			sum+=one.evaluate();
			
		}
		System.out.println(sum);
		return sum*gen;
	}
}
