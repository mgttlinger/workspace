package term.operations;

import term.ITerm;

public abstract class Operation implements ITerm {
	protected final ITerm[] terms;
	private final boolean cachedVariable;

	private static boolean containsVariableTerm(ITerm[] terms) {
		for (ITerm t : terms)
			if (t.isVariable())
				return true;
		return false;
	}

	//this.two = IOptimizer.optimizer.optimize(two);
	
	protected Operation(ITerm... terms) {
		this.terms = terms;
		cachedVariable = containsVariableTerm(terms);
	}

	@Override
	public ITerm[] getTerms() {
		return terms;
	}

	// @Override
	// public ITerm derivation(Variable var) {
	// // TODO Auto-generated method stub
	// return null;
	// }

	@Override
	public boolean equals(Object o) {
		if (o.getClass().equals(this.getClass())) {
			Operation o2 = (Operation) o;
			for (int i = 0; i < terms.length; i++)
				if (!terms[i].equals(o2.terms[i]))
					return false;
			return true;
		}
		return false;
	}

	@Override
	public boolean isVariable() {
		return cachedVariable;
	}
}
