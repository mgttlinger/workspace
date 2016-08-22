package term.operations.unitary;

import term.ITerm;
import term.constant.Constant;
import term.operations.UnitaryOperation;
import term.variable.Variable;

public class Sgn extends UnitaryOperation {

	protected Sgn(ITerm one) {
		super(one);
	}

	@Override
	public ITerm derivation(Variable var) {
		// TODO: die ableitung ist noch nicht definiert.
		return Constant.NaN;
	}

	@Override
	public double evaluate() {
		return Math.signum(one.evaluate());
	}
}
