package model.ruleSet;

import model.rules.*;

public class SodukuRules extends RuleSet {
	public SodukuRules() {
		super(new RowFactory(), new ColumnFactory(), new SuperBlockFactory());
	}
}
