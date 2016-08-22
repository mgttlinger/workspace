package term.operations;

import term.ITerm;

public abstract class ListedOperation extends Operation {

	public abstract ITerm extend(ITerm... input);
	
	private static ITerm[] combine(ITerm[] a, ITerm[] b){
		ITerm[] c=new ITerm[a.length+b.length];
		for (int i = 0; i < a.length; i++)
			c[i] = a[i];
		for (int i = 0; i < b.length; i++)
			c[a.length + i] = b[i];
		return c;
	}
	
	protected ListedOperation(ITerm[] inputA, ITerm... inputB) {
		super(combine(inputA, inputB));
	}

	protected ListedOperation(ITerm... input) {
		super(input);
	}
}
