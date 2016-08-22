package model.rules;

import model.dimensions.Dimensions;

public class ColumnFactory extends RuleFactory {

	public class Column extends RuleFactory.Rule {
		public Column(Dimensions dim) {
			super(dim, dim.mn);
		}

		@Override
		public int value(int i) {
			return i % dim.mn;
		}
	}

	@Override
	public Rule getNewRule(Dimensions dim) {
		return new Column(dim);
	}

}
