package parser.operation;

import term.ITerm;
import term.operations.binary.Subtraction;

public class ParserSubtraction extends ParserOperation {

	public ParserSubtraction() {
		super("-");
	}

	@Override
	public ITerm construct(ITerm op1, ITerm op2) {
		return new Subtraction(op1, op2);
	}
}
