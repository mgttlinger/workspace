package term.operations.unitary;

import term.ITerm;
import term.constant.Constant;
import term.operations.UnitaryOperation;
import term.operations.binary.Division;
import term.operations.binary.Multiplication;
import term.operations.binary.Power;
import term.operations.binary.Subtraction;
import term.variable.Variable;

public class Asin extends UnitaryOperation {

	public static ITerm construct(ITerm one) {
		if (!one.isVariable())
			return new Constant(Math.asin(one.evaluate()));
		return new Asin(one);
	}

	public Asin(ITerm one) {
		super(one);
	}

	@Override
	public ITerm derivation(Variable var) {
		return new Division(one.derivation(var),
				new Power(new Subtraction(Constant.one, new Multiplication(one, one)), new Constant(0.5)));
	}

	@Override
	public double evaluate() {
		return Math.asin(one.evaluate());
	}


	@Override
	public ITerm substitude(ITerm del, ITerm ins) {
		return new Asin(one.substitude(del, ins));
	}
}
