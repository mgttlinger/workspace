package term.operations.binary;

import term.ITerm;
import term.operations.BinaryOperation;
import term.variable.Variable;

public class Multiplication extends BinaryOperation {

	public Multiplication(ITerm one, ITerm two) {
		super(one, two);
	}

	@Override
	public ITerm derivation(Variable var) {
		return new Addition(new Multiplication(one, two.derivation(var)), new Multiplication(one.derivation(var), two));
	}

	@Override
	public double evaluate() {
		return one.evaluate() * two.evaluate();
	}
	
	public String toString() {
		return "("+one.toString() + ")*(" + two.toString()+")";
	}
}
