package solver.logicalMethods;

import model.Block;
import model.Options;
import model.Unit;
import model.dimensions.Dimensions;
import model.field.ModelField;

// Methoden und Variablen für den Umgang mit dem Solver.

public abstract class SolvingMethod {
	final protected Dimensions dim;
	public boolean mute = true;

	protected SolvingMethod(Dimensions dim) {
		this.dim = dim;
	}

	protected final int[] countOptions(Unit s) {
		int[] counted = new int[dim.mn];
		for (Block block : s) {
			if (block == null)
				break;
			for (int j = 0; j < dim.mn; j++)
				if (block.isOption(j))
					counted[j]++;
		}
		return counted;
	}

	protected final Unit intersection(Unit a, Unit b) {
		Unit c = new Unit(dim, 0, "intersection");
		for (Block block : a)
			if (b.isInside(block))
				c.append(block);
		return c;
	}

	protected final Options mergeOptionsOr(Options a, Options b) {
		Options r = new Options(dim);
		for (int i = 0; i < dim.mn; i++)
			if (!a.isOption(i) && !b.isOption(i))
				r.makeFalse(i);
		return r;
	}

	public abstract boolean solve(ModelField f);

	protected final void stateReduction(Block b) {
		if (!mute)
			System.out.printf("%s: %s.options was reduced%n", this.getClass().getName(), b.stringPos());
	}

	protected final void stateSuccess(Block b) {
		if (!mute)
			System.out.printf("%s: %s was set to %i%n", this.getClass().getName(), b.stringPos(), b.getValue());
	}
}
