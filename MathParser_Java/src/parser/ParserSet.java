package parser;

import parser.environment.Environment;
import parser.exceptions.ParserException;
import parser.token.TokenSet;
import term.ITerm;

public class ParserSet extends Parser {
	// constants ::= <Konstant>
	// variables ::= <Variable>
	// Parentheses ::= <(> <Ausdruck> <)>
	// operations ::= <Ausdruck> <Operation> <Ausdruck>
	// unitaryFunctions ::= <Funktionsname> <(> <Ausdruck> <)>
	// binaryFunctions ::= <Funktionsname> <(> <Ausdruck> <,> <Ausdruck> <)>
	// sign ::= (<->|<+>) <Ausdruck>

	private final Parser[] parsers;
	
	protected ParserSet(Parser... parsers){
		this.parsers=parsers;
	}

	@Override
	protected ITerm parse(TokenSet input, Environment env) throws ParserException {
		insure(countParentheses(input) == 0);
		insure(input.getSize()!=0);
		
		for (Parser p : parsers) {
			try {
				return p.parse(input, env);
			} catch (ParserException e) {
			}
		}

		throw fail();
	}

	@Override
	protected boolean allowedToken(String input) {
		for (Parser p : parsers)
			if (p.allowedToken(input))
				return true;
		return false;
	}

}
