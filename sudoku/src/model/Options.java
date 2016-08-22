package model;

import java.util.BitSet;

import model.dimensions.Dimensioned;
import model.dimensions.Dimensions;

public class Options extends Dimensioned {
	private BitSet values;

	public Options(Dimensions dim) {
		super(dim);
		assert dim!=null;
		values = new BitSet(dim.mn);
		values.set(0, dim.mn); // setze alle möglichkeiten auf true
	}

	public void connectAnd(Options opt) {
		assert opt!=null;
		values.and(opt.values);
	}

	public void connectAnd(Options opt, int i) {
		assert opt!=null;
		assert i>=0 && i<dim.mn;
		values.set(i, values.get(i) && opt.values.get(i));
	}

	public boolean equals(Options o) {
		return values.equals(o.values);
	}

	public int getFirstOption() {
		return values.nextSetBit(0);
	}

	public int getNumberOptions() {
		return values.cardinality();
	}

	public boolean isOption(int i) {
		assert i>=0 && i<dim.mn;
		return values.get(i);
	}

	void makeAllFalse() {
		values.clear(0, dim.mn);
	}

	public void makeFalse(int i) {
		values.clear(i);
	}

	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < dim.mn; i++)
			s += isOption(i) ? i + 1 : " ";
		return s;
	}
}