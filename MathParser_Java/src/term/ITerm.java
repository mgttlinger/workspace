package term;

import parser.environment.Environment;
import term.variable.Variable;

public interface ITerm {
	public double evaluate();

	public boolean isVariable();

	public ITerm[] getTerms();
	
	public ITerm derivation(Variable var);
	
	public ITerm substitude(ITerm del, ITerm ins);
}
