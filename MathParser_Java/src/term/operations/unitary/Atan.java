package term.operations.unitary;

import term.ITerm;
import term.constant.Constant;
import term.operations.UnitaryOperation;
import term.operations.binary.Addition;
import term.operations.binary.Division;
import term.operations.binary.Multiplication;
import term.variable.Variable;

public class Atan extends UnitaryOperation {

	public static ITerm construct(ITerm one) {
		if (!one.isVariable())
			return new Constant(Math.atan(one.evaluate()));
		return new Atan(one);
	}

	public Atan(ITerm one) {
		super(one);
	}

	@Override
	public ITerm derivation(Variable var) {
		return new Division(one.derivation(var), new Addition(new Multiplication(one, one), Constant.one));
	}

	@Override
	public double evaluate() {
		return Math.atan(one.evaluate());
	}


	@Override
	public ITerm substitude(ITerm del, ITerm ins) {
		return new Atan(one.substitude(del, ins));
	}
}
