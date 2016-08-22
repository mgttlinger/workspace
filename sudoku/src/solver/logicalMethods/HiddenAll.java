package solver.logicalMethods;

import model.Block;
import model.Unit;
import model.dimensions.Dimensions;
import model.field.ModelField;

// Wenn in einer Einheit n Felder die gesamten Möglichkeiten auf n Werte darstellen,
// dann können alle Möglichkeiten (ungleich der n Möglichkeiten, innerhalb der n Felder)  gelöscht werden.

public class HiddenAll extends SolvingMethod {

	private void next(int[] in, int mn) {
		in[in.length - 1]++;
		for (int i = in.length - 1; i > 0; i--)
			if (in[i] == mn + i - in.length + 1) {
				in[i - 1]++;
				for (int k = i; k < in.length; k++)
					in[k] = in[k - 1] + 1;
			}
	}

	@Override
	public boolean solve(ModelField f) {
		Dimensions dim = f.getDimensions();
		boolean b = false;
		for (int j = 2; j <= 4; j++) {
			int[] index = new int[j];
			for (int i = 0; i < j; i++)
				index[i] = i;
			while (index[0] <= dim.mn - j) {
				for (Unit[] uarr : f.units)
					for (Unit u : uarr) {
						b |= solveUnit(u, index);
					}

				/*
				 * for (int i = 0; i < dim.mn; i++) { b |=
				 * solveUnit(f.columns[i], index); b |= solveUnit(f.rows[i],
				 * index); b |= solveUnit(f.superBlocks[i], index); }
				 */
				next(index, dim.mn);
			}
		}
		return b;
	}

	private boolean solveUnit(Unit U, int[] index) {
		Dimensions dim = U.getDimensions();
		boolean success = false;
		Block[] blocks = new Block[index.length];
		int[] optionsBlocks = new int[dim.mn];
		for (int i = 0; i < index.length; i++) {
			blocks[i] = U.get(index[i]);
			for (int j = 0; j < dim.mn; j++)
				if (blocks[i].isOption(j))
					optionsBlocks[j]++;
		}
		int[] optionsUnit = new int[dim.mn];
		for (int i = 0; i < dim.mn; i++)
			for (int j = 0; j < dim.mn; j++)
				if (U.get(i).isOption(j))
					optionsUnit[j]++;

		int equalIn = 0;
		int reduceable = 0;
		for (int i = 0; i < dim.mn; i++)
			if (optionsBlocks[i] == optionsUnit[i] && optionsBlocks[i] != 0)
				equalIn++;
		for (int i = 0; i < dim.mn; i++)
			// R[i]:=in[i]>0 && IN[i]!=ALL[i];
			if (optionsBlocks[i] > 0 && optionsBlocks[i] != optionsUnit[i])
				reduceable++;

		if (equalIn == index.length && reduceable > 0) {
			success = true;
			for (int o = 0; o < dim.mn; o++)
				if (optionsBlocks[o] != optionsUnit[o])
					for (Block b : blocks)
						b.makeFalse(o);
		}
		return success;
	}

}
