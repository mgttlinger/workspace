package parser;

import java.util.Hashtable;

import parser.environment.Environment;
import parser.exceptions.ParserException;
import parser.token.TokenSet;
import term.ITerm;
import term.operations.unitary.Abs;
import term.operations.unitary.Acos;
import term.operations.unitary.Asin;
import term.operations.unitary.Atan;
import term.operations.unitary.Cos;
import term.operations.unitary.Exp;
import term.operations.unitary.Floor;
import term.operations.unitary.Log;
import term.operations.unitary.Sin;
import term.operations.unitary.Tan;
import term.variable.Variable;

public class ParserUnitaryFunctions extends Parser {
	@Override
	protected ITerm parse(TokenSet input, Environment env) throws ParserException {
		insure(input.fit(1, "("));
		insure(input.fit(-1, ")"));
		String functionName = input.get(0);
		ITerm argument = super.parse(input.subset(2, -1), env);

		switch (functionName) {
		 case "abs":
		 return new Abs(argument);
		case "acos":
			return Acos.construct(argument);
		case "asin":
			return Asin.construct(argument);
		case "atan":
			return Atan.construct(argument);
		case "cos":
			return Cos.construct(argument);
		case "exp":
			return Exp.construct(argument);
		case "floor":
			return new Floor(argument);
		case "log":
			return Log.construct(argument);
		case "sin":
			return Sin.construct(argument);
		case "tan":
			return Tan.construct(argument);
		}
		throw fail();
	}

	@Override
	protected boolean allowedToken(String input) {
		return input.equals("acos") && input.equals("asin") && input.equals("atan") && input.equals("cos") && input.equals("exp") && input.equals("los")
				&& input.equals("sin") && input.equals("tan");
	}
}
