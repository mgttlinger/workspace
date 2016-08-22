package term.operations.listed;

import java.util.Arrays;
import java.util.Collection;

import term.ITerm;
import term.operations.ListedOperation;
import term.operations.unitary.Abs;
import term.variable.Variable;

public class Sum extends ListedOperation {

	public Sum(Collection<ITerm> input) {
		this(input.toArray(new ITerm[0]));
	}

	public Sum(ITerm... input) {
		super(input);
	}

	@Override
	public ITerm extend(ITerm... input) {
		ITerm[] temp = Arrays.copyOf(terms, terms.length + input.length);
		for (int i = 0; i < input.length; i++)
			temp[terms.length + i] = input[i];
		return new Sum(temp);
	}

	@Override
	public ITerm derivation(Variable var) {
		ITerm[] der = new ITerm[terms.length];
		for (int i = 0; i < terms.length; i++) {
			der[i] = terms[i].derivation(var);
		}
		return new Sum(der);
	}

	@Override
	public double evaluate() {
		double rv = 0;
		for (ITerm t : terms)
			rv += t.evaluate();
		return rv;
	}
	

	@Override
	public ITerm substitude(ITerm del, ITerm ins) {
		ITerm[] sub = new ITerm[terms.length];
		for (int i = 0; i < terms.length; i++) {
			sub[i] = terms[i].substitude(del, ins);
		}
		return new Sum(sub);
	}
}
