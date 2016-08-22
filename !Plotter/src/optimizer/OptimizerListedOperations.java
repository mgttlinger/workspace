package optimizer;

import term.ITerm;

public class OptimizerListedOperations implements IOptimizer {

//	private static ITerm subAddition(Addition in) {
//		ITerm one = in.getOperandOne();
//		ITerm two = in.getOperandTwo();
//
//		if (one instanceof Sum) {
//			if (two instanceof Sum)
//				return ((Sum) one).extend(two);
//			return ((Sum) one).extend(two);
//		}
//		if (two instanceof Sum)
//			return ((Sum) two).extend(one);
//		return new Sum(one, two);
//	}
//
//	private static ITerm subMultiplication(Multiplication in) {
//		ITerm one = in.getOperandOne();
//		ITerm two = in.getOperandTwo();
//
//		if (one instanceof Product) {
//			if (two instanceof Product)
//				return ((Product) one).extend(two);
//			return ((Product) one).extend(two);
//		}
//		if (two instanceof Product)
//			return ((Product) two).extend(one);
//		return new Product(one, two);
//	}
	
	@Override
	public ITerm optimize(ITerm term) {
//		if (term instanceof Addition)
//			return subAddition((Addition) term);
//		if (term instanceof Multiplication)
//			return subMultiplication((Multiplication) term);
		return term;
	}
}
