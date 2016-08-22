package term.operations;

import term.ITerm;

public abstract class BinaryOperation extends Operation {
	protected final ITerm one, two;

	protected BinaryOperation(ITerm one, ITerm two) {
		super(one, two);
		this.one = one;
		this.two = two;
	}

	public ITerm getOperandOne() {
		return one;
	}
	
	public ITerm getOperandTwo() {
		return two;
	}

	}
