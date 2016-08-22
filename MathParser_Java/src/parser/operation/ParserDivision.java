package parser.operation;

import term.ITerm;
import term.operations.binary.Division;

public class ParserDivision extends ParserOperation {

	public ParserDivision() {
		super("/");
	}

	@Override
	public ITerm construct(ITerm op1, ITerm op2) {
		return new Division(op1, op2);
	}
}
