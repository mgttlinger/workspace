package term.operations.unitary;

import term.ITerm;
import term.constant.Constant;
import term.operations.UnitaryOperation;
import term.operations.binary.Multiplication;
import term.variable.Variable;

public class Sin extends UnitaryOperation {

	public static ITerm construct(ITerm one) {
		if (!one.isVariable())
			return new Constant(Math.sin(one.evaluate()));
		return new Sin(one);
	}

	public Sin(ITerm one) {
		super(one);
	}

	@Override
	public ITerm derivation(Variable var) {
		return new Multiplication(one.derivation(var), Cos.construct(one));
	}

	@Override
	public double evaluate() {
		return Math.sin(one.evaluate());
	}

}
