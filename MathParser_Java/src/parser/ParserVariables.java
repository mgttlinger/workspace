package parser;

import parser.environment.Environment;
import parser.exceptions.ParserException;
import parser.token.TokenSet;
import term.ITerm;
import term.variable.Variable;

public class ParserVariables extends Parser {

	@Override
	protected ITerm parse(TokenSet input, Environment env) throws ParserException {
		insure(input.getSize()==1);
		for(Variable v:env.getVariables()){
			if(v.getName().equals(input.get(0)))
				return v;
		}
		throw fail();
	}

	@Override
	protected boolean allowedToken(String input) {
		if (input.length() == 1)
			return input.charAt(0) >= 'a' && input.charAt(0) <= 'z';
		return false;
	}
}
