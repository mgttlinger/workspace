package optimizer;

import term.ITerm;

public interface IOptimizer {
	public static final IOptimizer optimizer=new OptimizerSet();
	public static boolean enable=true;
	
	
	public ITerm optimize(ITerm term);
}
