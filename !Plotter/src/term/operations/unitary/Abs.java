package term.operations.unitary;

import term.ITerm;
import term.operations.UnitaryOperation;
import term.variable.Variable;

public class Abs extends UnitaryOperation {

	public Abs(ITerm one) {
		super(one);
	}

	@Override
	public ITerm derivation(Variable var) {
		return new Sgn(one.derivation(var));
	}

	@Override
	public double evaluate() {
		return Math.abs(one.evaluate());
	}
}
