package parser.operation;

import term.ITerm;
import term.operations.binary.Power;

public class ParserPower extends ParserOperation {

	public ParserPower() {
		super("^");
	}

	@Override
	public ITerm construct(ITerm op1, ITerm op2) {
		return new Power(op1, op2);
	}
}
