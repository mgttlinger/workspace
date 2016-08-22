package term.operations.unitary;

import term.ITerm;
import term.constant.Constant;
import term.operations.UnitaryOperation;
import term.operations.binary.Division;
import term.variable.Variable;

public class Log extends UnitaryOperation {

	public static ITerm construct(ITerm one) {
		if (!one.isVariable())
			return new Constant(Math.log(one.evaluate()));
		return new Log(one);
	}

	public Log(ITerm one) {
		super(one);
	}

	@Override
	public ITerm derivation(Variable var) {
		return new Division(one.derivation(var), one);
	}

	@Override
	public double evaluate() {
		return Math.log(one.evaluate());
	}
	

	@Override
	public ITerm substitude(ITerm del, ITerm ins) {
		return new Log(one.substitude(del, ins));
	}
}
