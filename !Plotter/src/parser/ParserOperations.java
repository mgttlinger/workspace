package parser;

import java.util.Hashtable;

import parser.environment.Environment;
import parser.exceptions.ParserException;
import parser.token.TokenSet;
import term.ITerm;
import term.operations.binary.Addition;
import term.operations.binary.Division;
import term.operations.binary.Multiplication;
import term.operations.binary.Power;
import term.operations.binary.Subtraction;
import term.variable.Variable;

public class ParserOperations extends Parser {

	@Override
	protected ITerm parse(TokenSet input, Environment env) throws ParserException {
		// <Ausdruck> (<+> | <->) <Ausdruck>
		try{
			ITerm[] ops=trySplitAndParseWithoutFirstAndLast(input, env, "+");
			return new Addition(ops[0], ops[1]);
		}catch(ParserException e){
		}
		try{
			ITerm[] ops=trySplitAndParseWithoutFirstAndLast(input, env, "-");
			return new Subtraction(ops[0], ops[1]);
		}catch(ParserException e){
		}

		// <Ausdruck> (<*> | </>) <Ausdruck>
		try{
			ITerm[] ops=trySplitAndParseWithoutFirstAndLast(input, env, "*");
			return new Multiplication(ops[0], ops[1]);
		}catch(ParserException e){
		}
		try{
			ITerm[] ops=trySplitAndParseWithoutFirstAndLast(input, env, "/");
			return new Division(ops[0], ops[1]);
		}catch(ParserException e){
		}

		// <Ausdruck> <^> <Ausdruck>
		try{
			ITerm[] ops=trySplitAndParseWithoutFirstAndLast(input, env, "^");
			return new Power(ops[0], ops[1]);
		}catch(ParserException e){
		}

		throw fail();
	}

	@Override
	protected boolean allowedToken(String input) {
		return input.equals("+") || input.equals("-") || input.equals("*") || input.equals("/") || input.equals("^");
	}
}
