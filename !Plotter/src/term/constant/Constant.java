package term.constant;

import term.ITerm;
import term.variable.Variable;

public class Constant implements ITerm {
	private final double value;
	private final String name;

	public final static Constant zero = new Constant(0);
	public final static Constant one = new Constant(1);
	public final static Constant two = new Constant(2);
	public final static Constant e = new Constant(Math.E, "e");
	public final static Constant pi = new Constant(Math.PI, "pi");
	public final static Constant g = new Constant(9.80665, "g");
	public final static Constant NaN = new Constant(Double.NaN);

	public Constant(double value, String name) {
		this.value = value;
		this.name = name;
	}

	public Constant(double value) {
		this(value, null);
	}

	@Override
	public ITerm derivation(Variable var) {
		if (value == Double.NaN)
			return Constant.NaN;
		return Constant.zero;
	}

	@Override
	public boolean equals(Object o) {
		if (o.getClass().equals(this.getClass())) {
			return ((Constant) o).value == value;
		}
		return false;
	}

	@Override
	public double evaluate() {
		return value;
	}

	@Override
	public boolean isVariable() {
		return false;
	}

	@Override
	public ITerm[] getTerms() {
		return null;
	}

	@Override
	public String toString() {
		if (name != null)
			return name;
		else
			return Double.toString(value);
	}

	public String getName() {
		return name;
	}
}
