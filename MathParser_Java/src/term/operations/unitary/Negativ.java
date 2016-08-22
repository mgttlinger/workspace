package term.operations.unitary;

import term.ITerm;
import term.constant.Constant;
import term.operations.UnitaryOperation;
import term.variable.Variable;

public class Negativ extends UnitaryOperation {

	public static ITerm construct(ITerm one) {
		if (!one.isVariable())
			return new Constant(-one.evaluate());
		if(one instanceof Negativ)
			return ((Negativ) one).one;
		return new Negativ(one);
	}

	private Negativ(ITerm one) {
		super(one);
	}

	@Override
	public ITerm derivation(Variable var) {
		return new Negativ(one.derivation(var));
	}

	@Override
	public double evaluate() {
		return -one.evaluate();
	}


	@Override
	public ITerm substitude(ITerm del, ITerm ins) {
		return new Negativ(one.substitude(del, ins));
	}
}
