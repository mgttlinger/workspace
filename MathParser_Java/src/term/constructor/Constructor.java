package term.constructor;

import java.util.Arrays;

import term.ITerm;
import term.operations.binary.Addition;
import term.operations.binary.Division;
import term.operations.binary.Multiplication;
import term.operations.binary.Power;
import term.operations.binary.Subtraction;
import term.operations.unitary.Acos;
import term.operations.unitary.Asin;
import term.operations.unitary.Atan;
import term.operations.unitary.Cos;
import term.operations.unitary.Exp;
import term.operations.unitary.Log;
import term.operations.unitary.Sin;
import term.operations.unitary.Tan;

public class Constructor {
	private static ITerm constructUnitary(String type, ITerm one) {
		switch (type) {
		case "acos":
			return new Acos(one);
		case "asin":
			return new Asin(one);
		case "atan":
			return new Atan(one);
		case "cos":
			return new Cos(one);
		case "exp":
			return new Exp(one);
		case "log":
			return new Log(one);
		case "sin":
			return new Sin(one);
		case "tan":
			return new Tan(one);
		}
		throw new IllegalArgumentException();
	}

	private static ITerm constructBinary(String type, ITerm one, ITerm two) {
		switch (type) {
		case "*":
			return new Multiplication(one, two);
		case "/":
			return new Division(one, two);
		case "^":
			return new Power(one, two);
		case "-":
			return new Subtraction(one, two);
		case "+":
			return new Addition(one, two);
		}
		throw new IllegalArgumentException();
	}

	private static ITerm constructListed(String type, ITerm... terms) {
		switch (type) {
		case "+":
			return new Addition(terms[0], constructListed(type, Arrays.copyOfRange(terms, 1, terms.length)));
		case "*":
			return new Multiplication(terms[0], constructListed(type, Arrays.copyOfRange(terms, 1, terms.length)));
		}
		throw new IllegalArgumentException();
	}

	public static ITerm construct(String type, ITerm... terms) {
		switch (terms.length) {
		case 1:
			return constructUnitary(type, terms[0]);
		case 2:
			return constructBinary(type, terms[0], terms[1]);
		default:
			return constructListed(type, terms);
		}
	}
}
