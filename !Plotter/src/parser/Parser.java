package parser;

import java.util.Arrays;
import java.util.Hashtable;

import parser.environment.Environment;
import parser.exceptions.ParserException;
import parser.token.TokenSet;
import term.ITerm;
import term.variable.Variable;

public abstract class Parser {

	// constants ::= <Konstant>
	// variables ::= <Variable>
	// Parentheses ::= <(> <Ausdruck> <)>
	// operations ::= <Ausdruck> <Operation> <Ausdruck>
	// unitaryFunctions ::= <Funktionsname> <(> <Ausdruck> <)>
	// binaryFunctions ::= <Funktionsname> <(> <Ausdruck> <,> <Ausdruck> <)>
	// sign ::= (<->|<+>) <Ausdruck>

	private static Parser instance = new ParserSet(new ParserConstants(), new ParserVariables(), new ParserParentheses(), new ParserOperations(),
			new ParserUnitaryFunctions(), new ParserBinaryFunctions(), new ParserSign());

	public static ITerm parse(String input, Environment env) throws ParserException {
		return instance.parse(new TokenSet(input), env);
	}

	protected static int countParentheses(TokenSet input) {
		int counter = 0;
		for (int i = 0; i < input.getSize(); i++) {
			String c = input.get(i);
			if ("(".equals(c))
				counter++;
			else if (")".equals(c))
				counter--;
			if (counter == -1)
				return -1;
		}
		return counter;
	}

	protected static TokenSet[] splitBy(TokenSet input, int index) throws ParserException {
		TokenSet a = input.subset(0, index);
		TokenSet b = input.subset(index + 1, 0);

		insure(countParentheses(a) == 0);
		insure(countParentheses(b) == 0);

		return new TokenSet[] { a, b };
	}

	protected static TokenSet[] trySplitBy(TokenSet input, String splitter) throws ParserException {
		int i = input.search(splitter);
		insure(i != -1);
		return splitBy(input, i);
	}

	protected static ITerm[] trySplitAndParse(TokenSet input, Environment env, String splitter) throws ParserException {
		for (int i = 0; i < input.getSize(); i++) {
			try {
				insure(input.fit(i, splitter));
				return parseAll(splitBy(input, i), env);
			} catch (ParserException e) {
			}
		}
		throw fail();
	}
	
	protected static ITerm[] trySplitAndParseWithoutFirstAndLast(TokenSet input, Environment env, String splitter) throws ParserException {
		for (int i = 1; i < input.getSize()-1; i++) {
			try {
				insure(input.fit(i, splitter));
				return parseAll(splitBy(input, i), env);
			} catch (ParserException e) {
			}
		}
		throw fail();
	}

	public static ITerm[] parseAll(TokenSet[] input, Environment env) throws ParserException {
		ITerm[] rv = new ITerm[input.length];
		for (int i = 0; i < input.length; i++)
			rv[i] = instance.parse(input[i], env);
		return rv;
	}

	protected static void insure(boolean b) throws ParserException {
		if (!b)
			throw fail();
	}

	protected static ParserException fail() {
		return new ParserException();
	}

	protected ITerm parse(TokenSet input, Environment env) throws ParserException {
		return instance.parse(input, env);
	}

	protected abstract boolean allowedToken(String input);
}
