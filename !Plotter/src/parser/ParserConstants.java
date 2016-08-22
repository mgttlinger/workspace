package parser;

import java.util.Hashtable;

import parser.environment.Environment;
import parser.exceptions.ParserException;
import parser.token.TokenSet;
import term.ITerm;
import term.constant.Constant;
import term.variable.Variable;

public class ParserConstants extends Parser {

	@Override
	protected ITerm parse(TokenSet input, Environment env)
			throws ParserException {
		insure(input.getSize() == 1);
		for (Constant c : env.getConstants())
			if (c.getName().equals(input.get(0)))
				return c;
		try {
			return new Constant(Double.parseDouble(input.get(0)));
		} catch (NumberFormatException e) {
		}
		throw fail();
	}

	@Override
	protected boolean allowedToken(String input) {
		if (input.equals("e") || input.equals("pi"))
			return true;
		try {
			Double.parseDouble(input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
