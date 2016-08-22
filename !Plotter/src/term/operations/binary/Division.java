package term.operations.binary;

import term.ITerm;
import term.operations.BinaryOperation;
import term.variable.Variable;

public class Division extends BinaryOperation {

	public Division(ITerm one, ITerm two) {
		super(one, two);
	}

	@Override
	public ITerm derivation(Variable var) {
		return new Division(new Subtraction(new Multiplication(one.derivation(var), two), new Multiplication(one, two.derivation(var))),
				new Multiplication(two, two));
	}

	@Override
	public double evaluate() {
		return one.evaluate() / two.evaluate();
	}
	
	public String toString() {
		return "("+one.toString() + ")/(" + two.toString()+")";
	}
}
