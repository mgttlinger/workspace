package solver.logicalMethods;

import model.Block;
import model.Unit;
import model.field.ModelField;

// Wenn in einer Einheit (Zeile, Spalte, SuperBlock) eine Option in einem einzigen Block vorkommt, dann wird diese dort gesetzt.

public class SingleInUnit extends SolvingMethod {
	@Override
	public boolean solve(ModelField f) {
		boolean success = false;
		for (Unit[] rule : f.units)
			for (Unit unit : rule)
				success |= solveUnit(unit);
		return success;
	}

	private boolean solveUnit(Unit s) {
		boolean b = false;
		int[] c = countOptions(s);
		for (int i = 0; i < c.length; i++)
			if (c[i] == 1) {
				b = true;
				for (Block block : s)
					if (block.isOption(i)) {
						block.setIndex(i);
						break;
					}
			}
		return b;
	}
}
