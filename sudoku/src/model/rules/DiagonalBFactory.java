package model.rules;

import model.dimensions.Dimensions;

public class DiagonalBFactory extends RuleFactory {

	public class DiagonalB extends Rule {
		public DiagonalB(Dimensions dim) {
			super(dim, 1);
		}

		@Override
		public int value(int i) {
			if (i % dim.mn + i / dim.mn + 1 == dim.mn)
				return 0;
			return -1;
		}
	}

	@Override
	public Rule getNewRule(Dimensions dim) {
		return new DiagonalB(dim);
	}

}
