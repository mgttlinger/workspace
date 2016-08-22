package parser;

import parser.environment.Environment;
import parser.exceptions.ParserException;
import parser.token.TokenSet;
import term.ITerm;
import term.operations.unitary.Negativ;

public class ParserSign extends Parser {
	@Override
	protected ITerm parse(TokenSet input, Environment env) throws ParserException {
		if (input.fit(0, "-"))
			return Negativ.construct(super.parse(input.subset(1, 0), env));
		if (input.fit(0, "+"))
			return super.parse(input.subset(1, 0), env);
		throw fail();
	}

	@Override
	protected boolean allowedToken(String input) {
		return input.equals("-") || input.equals("+");
	}
}
