package term.operations;

import term.ITerm;

public abstract class UnitaryOperation extends Operation{
	protected final ITerm one;

	protected UnitaryOperation(ITerm one) {
		super(one);
		this.one=getTerms()[0];
	}

	public ITerm getOperandOne() {
		return one;
	}
	
	public String toString() {
		return String.format("%s", this.getClass().getSimpleName());
	}
}
