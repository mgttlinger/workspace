package parser.operation;

import term.ITerm;
import term.operations.binary.Multiplication;

public class ParserMultiplication extends ParserOperation {

	public ParserMultiplication() {
		super("*");
	}

	@Override
	public ITerm construct(ITerm op1, ITerm op2) {
		return new Multiplication(op1, op2);
	}
}
