package term.variable;

import parser.exceptions.ParserException;
import term.ITerm;
import term.constant.Constant;

public class Variable implements ITerm {
	public static ITerm parse(String input) throws ParserException {
		throw new ParserException();
	}

	private double value;

	private final String name;

	public Variable(double value, String name) {
		this.value=value;
		this.name = name;
	}
	
	public Variable(String name) {
		this(0, name);
	}

	public void assign(double value) {
		this.value = value;
	}

	@Override
	public ITerm derivation(Variable var) {
		return var.equals(this) ? new Constant(1) : new Constant(0);
	}

	@Override
	public boolean equals(Object o) {
		return o == this;
	}

	@Override
	public double evaluate() {
		return value;
	}

	@Override
	public boolean isVariable() {
		return true;
	}

	@Override
	public ITerm[] getTerms() {
		return null;
	}
	
	public String getName(){
		return name;
	}

	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public ITerm substitude(ITerm del, ITerm ins) {
		if(this == del)
			return ins;
		return this;
	}
	
	public Variable clone(){
		return new Variable(value, name);
	}
}
