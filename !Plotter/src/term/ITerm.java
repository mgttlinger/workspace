package term;

import term.variable.Variable;

public interface ITerm {
	public double evaluate();

	public boolean isVariable();

	public ITerm[] getTerms();

	public ITerm derivation(Variable var);
}
