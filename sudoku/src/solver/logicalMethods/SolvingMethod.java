package solver.logicalMethods;

import model.Block;
import model.Options;
import model.Unit;
import model.dimensions.Dimensions;
import model.field.ModelField;

// Methoden und Variablen für den Umgang mit dem Solver.

public abstract class SolvingMethod {
	protected final int[] countOptions(Unit s) {
		Dimensions dim = s.getDimensions();
		int[] counted = new int[dim.mn];
		for (Block block : s) {
			for (int j = 0; j < dim.mn; j++)
				if (block.isOption(j))
					counted[j]++;
		}
		return counted;
	}

	protected final Unit intersection(Unit a, Unit b) {
		Dimensions dim = a.getDimensions();
		Unit c = new Unit(dim);
		for (Block block : a)
			if (b.isInside(block))
				c.append(block);
		return c;
	}

	protected final Options mergeOptionsOr(Options a, Options b) {
		Dimensions dim = a.getDimensions();
		Options r = new Options(dim);
		for (int i = 0; i < dim.mn; i++)
			if (!a.isOption(i) && !b.isOption(i))
				r.makeFalse(i);
		return r;
	}

	public abstract boolean solve(ModelField f);
}
