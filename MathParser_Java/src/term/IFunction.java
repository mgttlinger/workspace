package term;

import parser.environment.Environment;

public interface IFunction extends ITerm {
	public Environment getEnvironment();
}
