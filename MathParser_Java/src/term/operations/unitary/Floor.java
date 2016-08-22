package term.operations.unitary;

import term.ITerm;
import term.operations.UnitaryOperation;
import term.variable.Variable;

public class Floor extends UnitaryOperation {

	public Floor(ITerm one) {
		super(one);
	}

	@Override
	public ITerm derivation(Variable var) {
		throw new UnsupportedOperationException();
	}

	@Override
	public double evaluate() {
		return Math.floor(one.evaluate());
	}
	

	@Override
	public ITerm substitude(ITerm del, ITerm ins) {
		return new Floor(one.substitude(del, ins));
	}
}
