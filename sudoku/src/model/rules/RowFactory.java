package model.rules;

import model.dimensions.Dimensions;

public class RowFactory extends RuleFactory {

	public class Row extends Rule {
		public Row(Dimensions dim) {
			super(dim, dim.mn);
		}

		@Override
		public int value(int i) {
			return i / dim.mn;
		}
	}

	@Override
	public Rule getNewRule(Dimensions dim) {
		return new Row(dim);
	}

}
