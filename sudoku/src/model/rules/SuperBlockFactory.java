package model.rules;

import model.dimensions.Dimensions;

public class SuperBlockFactory extends RuleFactory {

	public class SuperBlock extends Rule {
		public SuperBlock(Dimensions dim) {
			super(dim, dim.mn);
		}

		@Override
		public int value(int i) {
			return i % dim.mn / dim.m + i / dim.mn / dim.n * dim.n;
		}
	}

	@Override
	public Rule getNewRule(Dimensions dim) {
		return new SuperBlock(dim);
	}

}
