package parser.operation;

import term.ITerm;
import term.operations.binary.Addition;

public class ParserAddition extends ParserOperation {

	public ParserAddition() {
		super("+");
	}

	@Override
	public ITerm construct(ITerm op1, ITerm op2) {
		return new Addition(op1, op2);
	}
}
