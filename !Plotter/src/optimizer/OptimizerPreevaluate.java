package optimizer;

import term.ITerm;
import term.constant.Constant;

public class OptimizerPreevaluate implements IOptimizer {
	@Override
	public ITerm optimize(ITerm term) {
		if (term instanceof Constant)
			return term;
		if (term.isVariable())
			return term;
		return new Constant(term.evaluate());
	}
}
