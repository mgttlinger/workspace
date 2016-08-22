package solver.logicalMethods;

import model.Block;
import model.field.ModelField;

// Wenn f�r einen Einheit (Block, Zeile, Spalte) nur 1 Option m�glich ist, dann wird diese gesetzt.

public class SingleOption extends SolvingMethod {
	@Override
	public boolean solve(ModelField f) {
		boolean success = false;
		for (Block b : f.blocks)
			if (b.getNumberOptions() == 1) {
				b.setIndex(b.getFirstOption());
				success = true;
			}
		return success;
	}
}
