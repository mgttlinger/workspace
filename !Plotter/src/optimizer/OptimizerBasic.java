package optimizer;

import term.ITerm;
import term.constant.Constant;
import term.operations.binary.Addition;
import term.operations.binary.Division;
import term.operations.binary.Multiplication;
import term.operations.binary.Power;
import term.operations.binary.Subtraction;
import term.operations.unitary.Exp;
import term.operations.unitary.Negativ;

public class OptimizerBasic implements IOptimizer {

	private static ITerm subAddition(Addition in) {
		ITerm one = in.getOperandOne();
		ITerm two = in.getOperandTwo();

		// a + 0 = a
		if (two.equals(Constant.zero))
			return one;
		// 0 + a = a
		if (one.equals(Constant.zero))
			return two;
		// a + a = 2a
		if (one.equals(two))
			return new Multiplication(new Constant(2), one);

		return new Addition(one, two);
	}

	private static ITerm subSubtraction(Subtraction in) {
		ITerm one = in.getOperandOne();
		ITerm two = in.getOperandTwo();
		
		// a - a = 0
		if (one.equals(two))
			return Constant.zero;
		// a - 0 = a
		if (two.equals(Constant.zero))
			return one;
		// 0 - a = -a
		if (one.equals(Constant.zero))
			return Negativ.construct(two);
		return new Subtraction(one, two);
	}
	
	private static ITerm subMultiplication(Multiplication in) {
		ITerm one = in.getOperandOne();
		ITerm two = in.getOperandTwo();

		// 0 * a = 0
		// a * 0 = 0
		if (one.equals(Constant.zero) || two.equals(Constant.zero))
			return Constant.zero;
		// 1 * a = a
		if (one.equals(Constant.one))
			return two;
		// a * 1 = a
		if (two.equals(Constant.one))
			return one;
		// a * a = a^2
		if (two.equals(one))
			return new Power(one, Constant.two);
		return in;
	}

	private static ITerm subDivision(Division in) {
		ITerm one = in.getOperandOne();
		ITerm two = in.getOperandTwo();

		// a / a = 1
		if (one.equals(two))
			return Constant.one;
		// a / 1 = a
		if (two.equals(Constant.one))
			return one;
		// a / 0 = NaN
		if (two.equals(Constant.zero))
			return Constant.NaN;
		// 0 / a = 0
		if (one.equals(Constant.zero))
			return Constant.zero;
		return in;
	}

	private static ITerm subPower(Power in) {
		ITerm one = in.getOperandOne();
		ITerm two = in.getOperandTwo();

		// a ^ 1 = a
		if (two.equals(Constant.one))
			return one;
		// 1 ^ a = 1
		if (one.equals(Constant.one))
			return Constant.one;
		// 0 ^ 0 = NaN
		if (one.equals(Constant.zero) && two.equals(Constant.zero))
			return Constant.NaN;
		// 0 ^ a = 0
		if (one.equals(Constant.zero))
			return Constant.zero;
		// a ^ 0 = 1
		if (two.equals(Constant.zero))
			return Constant.one;
		// e ^ a = exp(a)
		if (one.equals(Constant.e))
			return new Exp(two);

		return in;
	}

	@Override
	public ITerm optimize(ITerm term) {
		if (term instanceof Addition)
			return subAddition((Addition) term);
		if(term instanceof Subtraction)
			return subSubtraction((Subtraction) term);
		if (term instanceof Multiplication)
			return subMultiplication((Multiplication) term);
		if (term instanceof Division)
			return subDivision((Division) term);
		if (term instanceof Power)
			return subPower((Power) term);
		return term;
	}
}
