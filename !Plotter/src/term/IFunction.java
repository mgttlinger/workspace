package term;

import parser.environment.Environment;

import term.variable.Variable;

public interface IFunction extends ITerm {
	public Environment getEnvironment();
}
