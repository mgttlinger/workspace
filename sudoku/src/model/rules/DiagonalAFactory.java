package model.rules;

import model.dimensions.Dimensions;

public class DiagonalAFactory extends RuleFactory {

	public class DiagonalA extends Rule {
		public DiagonalA(Dimensions dim) {
			super(dim, 1);
		}

		@Override
		public int value(int i) {
			if (i % dim.mn == i / dim.mn)
				return 0;
			return -1;
		}
	}

	@Override
	public Rule getNewRule(Dimensions dim) {
		return new DiagonalA(dim);
	}

}
