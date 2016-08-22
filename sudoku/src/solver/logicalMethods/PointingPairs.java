package solver.logicalMethods;

import model.Block;
import model.Unit;
import model.field.ModelField;

/* Wenn die Schnittmenge von einem superblock und einer Zeile (Spalte)
 * in einer Option in der Anzahl übereinstimmt, dann kann diese Option 
 * in allen Zellen von SuperBlock und Zeile (Spalte), die nicht Teilmenge
 * der Schnittmenge sind, gelöscht werden. */

public class PointingPairs extends SolvingMethod {
	private void action(Unit a, Unit b, Unit inter, int o) {
		for (Block block : a)
			if (block.isOption(o))
				if (!inter.isInside(block)) {
					block.makeFalse(o);
				}
		for (Block block : b)
			if (block.isOption(o))
				if (!inter.isInside(block)) {
					block.makeFalse(o);
				}
	}

	private boolean search(Unit superBlock, Unit second, Unit inter) {
		int[] optionsSB = countOptions(superBlock);
		int[] optionsSecond = countOptions(second);
		int[] optionsInter = countOptions(inter);
		boolean success = false;
		for (int i = 0; i < optionsInter.length; i++)
			if (optionsInter[i] > 0 && optionsSB[i] != optionsSecond[i])
				if (optionsInter[i] == optionsSB[i] || optionsInter[i] == optionsSecond[i]) {
					success = true;
					action(superBlock, second, inter, i);
				}
		return success;
	}

	@Override
	public boolean solve(ModelField f) {
		boolean success = false;
		for (int r = 0; r < f.units.length; r++)
			for (Unit a : f.units[r])
				for (int r2 = r + 1; r2 < f.units.length; r2++) {
					for (Unit b : f.units[r2]) {
						Unit inter = intersection(a, b);
						if (inter.getSize() > 1)
							success |= search(a, b, inter);
					}
				}
		return success;
	}
}
