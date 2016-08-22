package term.operations.unitary;

import term.ITerm;
import term.constant.Constant;
import term.operations.UnitaryOperation;
import term.operations.binary.Multiplication;
import term.variable.Variable;

public class Exp extends UnitaryOperation {

	public static ITerm construct(ITerm one) {
		if (!one.isVariable())
			return new Constant(Math.exp(one.evaluate()));
		return new Exp(one);
	}

	public Exp(ITerm one) {
		super(one);
	}

	@Override
	public ITerm derivation(Variable var) {
		return new Multiplication(one.derivation(var), this);
	}

	@Override
	public double evaluate() {
		return Math.exp(one.evaluate());
	}

}
