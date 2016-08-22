package term.operations.listed;

import java.util.Arrays;
import java.util.Collection;

import term.ITerm;
import term.operations.ListedOperation;
import term.variable.Variable;

public class Product extends ListedOperation {

	public Product(Collection<ITerm> input) {
		this(input.toArray(new ITerm[0]));
	}

	public Product(ITerm... input) {
		super(input);
	}

	@Override
	public ITerm extend(ITerm... input) {
		ITerm[] temp = Arrays.copyOf(terms, terms.length + input.length);
		for (int i = 0; i < input.length; i++)
			temp[terms.length + i] = input[i];
		return new Product(temp);
	}
	
	@Override
	public ITerm derivation(Variable var) {
		ITerm[] der = new ITerm[terms.length];

		for (int i = 0; i < terms.length; i++) {
			ITerm backup = terms[i];
			terms[i] = terms[i].derivation(var);

			der[i] = new Product(terms);

			terms[i] = backup;
		}
		return new Sum(der);
	}

	@Override
	public double evaluate() {
		double rv = 1;
		for (ITerm t : terms)
			rv *= t.evaluate();
		return rv;
	}

	@Override
	public ITerm substitude(ITerm del, ITerm ins) {
		ITerm[] sub = new ITerm[terms.length];
		for (int i = 0; i < terms.length; i++) {
			sub[i] = terms[i].substitude(del, ins);
		}
		return new Product(sub);
	}
}
