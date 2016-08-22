package model;

import model.dimensions.Dimensions;

public class Block extends Options {
	private final Unit[] units;
	private int value;

	public Block(Dimensions dim, Unit... units) {
		super(dim);
		assert dim!=null;
		assert units!=null && units.length!=0;
		this.units = units;
		value = 0;
	}

	public int getIndex() {
		return getValue() - 1;
	}

	public int getValue() {
		return value;
	}

	public boolean isFilled() {
		return getValue() != 0;
	}

	public void setIndex(int i) {
		setValue(i + 1);
	}

	public void setValue(int input) {
		assert input > 0 && input <= dim.mn;
		assert isOption(input-1);
		value = input;
		updateOptions();
	}

	public String stringPos() {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		for (Unit u : units)
			sb.append(u.toString() + " ");
		sb.append(")");
		return sb.toString();
	}

	@Override
	public String toString() {
		return String.format("%d", getValue());
	}

	public void updateOptions() {
		if (isFilled()) {
			for (Unit u : units)
				u.updateOptions(getIndex());
			makeAllFalse();
		}
	}
}
