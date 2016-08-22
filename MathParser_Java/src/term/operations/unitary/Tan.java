package term.operations.unitary;

import term.ITerm;
import term.constant.Constant;
import term.operations.UnitaryOperation;
import term.operations.binary.Multiplication;
import term.operations.binary.Power;
import term.variable.Variable;

public class Tan extends UnitaryOperation {

	public static ITerm construct(ITerm one) {
		if (!one.isVariable())
			return new Constant(Math.tan(one.evaluate()));
		return new Tan(one);
	}

	public Tan(ITerm one) {
		super(one);
	}

	@Override
	public ITerm derivation(Variable var) {
		return new Multiplication(one.derivation(var), new Power(Cos.construct(one), new Constant(-2)));
	}

	@Override
	public double evaluate() {
		return Math.tan(one.evaluate());
	}
	

	@Override
	public ITerm substitude(ITerm del, ITerm ins) {
		return new Tan(one.substitude(del, ins));
	}

}
