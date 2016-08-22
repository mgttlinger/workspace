package solver.logicalMethods;

import model.Block;
import model.Options;
import model.Unit;
import model.dimensions.Dimensions;
import model.field.ModelField;

// irgendwas hab ich verbuggt. ich haben den zähler eine schleife vor gezogen um performace zu sparen. eine genauere untersuchung fehlt

public class NackedAll extends SolvingMethod {
	private boolean action(Unit U, int[] in, Options merge) {
		Dimensions dim = U.getDimensions();
		boolean b = false;
		for (int i = 0; i < dim.mn; i++) {
			Block c = U.get(i);
			boolean condition = true;
			for (int j : in)
				condition &= i != j;
			if (condition)
				for (int j = 0; j < dim.mn; j++) {
					if (merge.isOption(j) && c.isOption(j)) {
						b = true;
						// stateReduction(c);
						// for (int k = 0; k < in.length; k++)
						// System.out.println(k + ": "
						// + U.blocks[in[k]].stringPos() + " "
						// + U.blocks[in[k]].getOptions().toString());
						// System.out.print("-> " + c.stringPos() + " "
						// + o.toString());
						c.makeFalse(j);
						// System.out.println("-> " + o.toString());
					}
				}
		}
		return b;
	}

	private Options mergeOptionsOr(Unit U, int[] in) {
		Dimensions dim = U.getDimensions();
		Options r = new Options(dim);
		for (int i = 0; i < dim.mn; i++) {
			boolean condition = true;
			for (int j : in)
				condition &= !U.get(j).isOption(i);
			if (condition)
				r.makeFalse(i);
		}
		return r;
	}

	private void next(int[] in, int mn) {
		in[in.length - 1]++;
		for (int i = in.length - 1; i > 0; i--)
			if (in[i] == mn + i - in.length + 1) {
				in[i - 1]++;
				for (int k = i; k < in.length; k++)
					in[k] = in[k - 1] + 1;
			}
	}

	private boolean search(Unit U, int[] in) {

		boolean b = false;
		Options merge = mergeOptionsOr(U, in);
		if (merge.getNumberOptions() == in.length) {
			boolean condition = true;
			for (int i : in)
				condition &= U.get(i).getNumberOptions() > 1;
			if (condition)
				b |= action(U, in, merge);
		}
		return b;
	}

	@Override
	public boolean solve(ModelField f) {
		Dimensions dim = f.getDimensions();
		boolean b = false;
		for (int j = 2; j <= dim.mn / 2; j++) {
			int[] index = new int[j];
			for (int i = 0; i < j; i++)
				index[i] = i;
			while (index[0] <= dim.mn - j) {
				for (Unit[] uarr : f.units)
					for (Unit u : uarr) {
						b |= search(u, index);
					}
				next(index, dim.mn);
			}
		}
		return b;
	}
}
