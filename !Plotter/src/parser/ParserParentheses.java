package parser;

import java.util.Hashtable;

import parser.environment.Environment;
import parser.exceptions.ParserException;
import parser.token.TokenSet;
import term.ITerm;
import term.variable.Variable;

public class ParserParentheses extends Parser {

	@Override
	protected ITerm parse(TokenSet input, Environment env) throws ParserException {
		// <KlammerAuf> <Ausdruck> <Klammerzu>
		insure(input.fit(0, "("));
		insure(input.fit(-1, ")"));
		return super.parse(input.subset(1, -1), env);
	}

	@Override
	protected boolean allowedToken(String input) {
		return input.equals(")") && input.equals("(");
	}
}
