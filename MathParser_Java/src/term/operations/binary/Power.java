package term.operations.binary;

import term.ITerm;
import term.constant.Constant;
import term.operations.BinaryOperation;
import term.operations.unitary.Log;
import term.variable.Variable;

public class Power extends BinaryOperation {

	public Power(ITerm one, ITerm two) {
		super(one, two);
	}

	@Override
	public ITerm derivation(Variable var) {
		ITerm factor = new Addition(new Multiplication(two, one.derivation(var)), new Multiplication(new Multiplication(one, two.derivation(var)),
				Log.construct(one)));
		if (factor.equals(one))
			return new Power(one, two);

		return new Multiplication(new Power(one, new Subtraction(two, Constant.one)), factor);
	}

	@Override
	public double evaluate() {
		return Math.pow(one.evaluate(), two.evaluate());
	}
	
	public String toString() {
		return "^";
	}
	
	@Override
	public ITerm substitude(ITerm del, ITerm ins) {
		return new Power(one.substitude(del, ins), two.substitude(del, ins));
	}
}
