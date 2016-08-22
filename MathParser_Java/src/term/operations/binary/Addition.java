package term.operations.binary;

import term.ITerm;
import term.operations.BinaryOperation;
import term.variable.Variable;

public class Addition extends BinaryOperation {

	public Addition(ITerm one, ITerm two) {
		super(one, two);
	}

	@Override
	public ITerm derivation(Variable var) {
		return new Addition(one.derivation(var), two.derivation(var));
	}

	@Override
	public double evaluate() {
		return one.evaluate() + two.evaluate();
	}
	

	public String toString() {
		return "+";
	}

	@Override
	public ITerm substitude(ITerm del, ITerm ins) {
		return new Addition(one.substitude(del, ins), two.substitude(del, ins));
	}
}
