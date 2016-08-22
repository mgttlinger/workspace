package term.functions;

import java.util.Hashtable;

import optimizer.IOptimizer;
import parser.Parser;
import parser.environment.Environment;
import parser.exceptions.ParserException;
import term.IFunction;
import term.ITerm;
import term.constant.Constant;
import term.variable.Variable;

public class Function implements IFunction {

	private final Environment env;
	private final ITerm term;

	public Function(ITerm term, Environment env) {
		this.term = term;
		this.env = env;
	}

	public Function(String input, String... vars) throws ParserException {
		Variable[] var = new Variable[vars.length];
		for (int i = 0; i < vars.length; i++)
			var[i] = new Variable(vars[i]);
		env = new Environment(var, new Constant[] { Constant.e, Constant.pi });
		term = IOptimizer.optimizer.optimize(Parser.parse(input, env));
	}

	public Function(ITerm term, Variable... vars) {
		env = new Environment(vars, new Constant[] { Constant.e, Constant.pi });
		this.term = term;
	}

	@Override
	public ITerm derivation(Variable var) {
		return term.derivation(var);
	}

	@Override
	public double evaluate() {
		return term.evaluate();
	}

	@Override
	public Environment getEnvironment() {
		return env;
	}

	@Override
	public boolean isVariable() {
		return term.isVariable();
	}

	@Override
	public ITerm[] getTerms() {
		return new ITerm[] { term };
	}

	@Override
	public String toString() {
		return "Function";
	}
}
