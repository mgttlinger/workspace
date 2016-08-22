package parser;

import parser.environment.Environment;
import parser.exceptions.ParserException;
import parser.token.TokenSet;
import term.ITerm;
import term.operations.special.AblNum;
import term.variable.Variable;

public class ParserBinaryFunctions extends Parser {
	@Override
	protected ITerm parse(TokenSet input, Environment env) throws ParserException {
		// <Funktionsname> <(> <Ausdruck> <,> <Ausdruck> <)>
		insure(input.fit(1, "("));
		insure(input.fit(-1, ")"));

		String functionsName = input.get(0);
		TokenSet argumentList = input.subset(2, -1);

		ITerm[] arguments = trySplitAndParse(argumentList, env, ",");

		switch (functionsName) {
		case "abl":
			insure(arguments[1] instanceof Variable);
			return arguments[0].derivation((Variable) arguments[1]);
		case "ablNum":
			insure(arguments[1] instanceof Variable);
			return new AblNum(arguments[0], (Variable) arguments[1]);
		}
		throw fail();
	}

	@Override
	protected boolean allowedToken(String input) {
		return input.equals("abl");
	}
}
