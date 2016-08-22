package solver.logicalMethods;

import model.Block;
import model.Unit;
import model.dimensions.Dimensions;
import model.field.ModelField;

// Wenn in einer Einheit (Zeile, Spalte, SuperBlock) eine Option in einem einzigen Block vorkommt, dann wird diese dort gesetzt.

public class SingleInUnit extends SolvingMethod {
	public SingleInUnit(Dimensions dim) {
		super(dim);
	}

	@Override
	public boolean solve(ModelField f) {
		boolean success = false;
		for (int r = 0; r < f.rules.length; r++)
			for (Unit u : f.units[r])
				success |= solveUnit(u);
		return success;
	}

	private boolean solveUnit(Unit s) {
		boolean b = false;
		int[] c = countOptions(s);
		for (int i = 0; i < dim.mn; i++)
			if (c[i] == 1) {
				b = true;
				for (Block block : s)
					if (block.isOption(i)) {
						block.setIndex(i);
						stateSuccess(block);
						break;
					}
			}
		return b;
	}
}
