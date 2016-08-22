package model.rules;

import java.io.Serializable;

import model.dimensions.Dimensioned;
import model.dimensions.Dimensions;

public abstract class RuleFactory implements Serializable {
	public abstract class Rule extends Dimensioned {
		private final int amount;

		protected Rule(Dimensions dim, int amount) {
			super(dim);
			this.amount = amount;
		}

		public int getAmount() {
			return amount;
		}


		public void stateRule() {
			for (int i = 0; i < dim.mn; i++) {
				for (int j = 0; j < dim.mn; j++)
					System.out.printf("%3d", value(i * dim.mn + j));
				System.out.println();
			}
		}

		public abstract int value(int i);
	}

	public abstract Rule getNewRule(Dimensions dim);

	@Override
	public String toString() {
		return getClass().getName();
	}
}