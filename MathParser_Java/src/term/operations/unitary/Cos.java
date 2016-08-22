package term.operations.unitary;

import term.ITerm;
import term.constant.Constant;
import term.operations.UnitaryOperation;
import term.operations.binary.Multiplication;
import term.variable.Variable;

public class Cos extends UnitaryOperation {

	public static ITerm construct(ITerm one) {
		if (!one.isVariable())
			return new Constant(Math.cos(one.evaluate()));
		return new Cos(one);
	}

	public Cos(ITerm one) {
		super(one);
	}

	@Override
	public ITerm derivation(Variable var) {
		return new Multiplication(one.derivation(var), Negativ.construct(Sin.construct(one)));
	}

	@Override
	public double evaluate() {
		return Math.cos(one.evaluate());
	}
	

	@Override
	public ITerm substitude(ITerm del, ITerm ins) {
		return new Cos(one.substitude(del, ins));
	}
}
