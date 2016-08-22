package parser.environment;

import java.util.Arrays;

import term.ITerm;
import term.constant.Constant;
import term.variable.Variable;

public class Environment {
	private final Variable[] variables;
	private final Constant[] constants;

	public Environment(Variable[] var, Constant[] con) {
		variables = var;
		constants = con;
	}

	public Variable[] getVariables() {
		return variables;
	}

	public Constant[] getConstants() {
		return constants;
	}

	public ITerm get(String s) {
		for (Variable v : variables)
			if (s.equals(v.getName()))
				return v;
		for (Constant c : constants)
			if (s.equals(c.getName()))
				return c;
		return null;
	}

	public Variable getVariable(String s) {
		for (Variable v : variables)
			if (s.equals(v.getName()))
				return v;
		return null;

	}
	
	@Override
	public String toString(){
		return Arrays.toString(variables) + Arrays.toString(constants);
	}
}
