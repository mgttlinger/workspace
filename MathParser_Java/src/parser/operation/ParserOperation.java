package parser.operation;

import parser.Parser;
import parser.environment.Environment;
import parser.exceptions.ParserException;
import parser.token.TokenSet;
import term.ITerm;

public abstract class ParserOperation extends Parser {

	private final String sign;

	public ParserOperation(String sign) {
		this.sign = sign;
	}

	@Override
	protected ITerm parse(TokenSet input, Environment env)
			throws ParserException {
		// <Ausdruck> <sign> <Ausdruck>
		try {
			ITerm[] ops = trySplitAndParseWithoutFirstAndLast(input, env, sign);
			return construct(ops[0], ops[1]);
		} catch (ParserException e) {
		}
		throw fail();
	}

	@Override
	protected boolean allowedToken(String input) {
		return input.equals(sign);
	}

	public abstract ITerm construct(ITerm op1, ITerm op2);
}
