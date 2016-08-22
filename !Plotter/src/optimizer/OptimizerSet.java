package optimizer;

import term.ITerm;

public class OptimizerSet implements IOptimizer {
	private final static IOptimizer[] optimizers = new IOptimizer[] { new OptimizerPreevaluate(), new OptimizerBasic(), new OptimizerListedOperations() };

	@Override
	public ITerm optimize(ITerm term) {
		if(term==null)
			return null;
		if(IOptimizer.enable)
		for (IOptimizer o : optimizers) {
			term = o.optimize(term);
		}
		return term;
	}
}
