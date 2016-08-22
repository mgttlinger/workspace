package model.ruleSet;

import model.rules.*;

public class XSodukuRules extends RuleSet {
	public XSodukuRules() {
		super(new RowFactory(), new ColumnFactory(), new SuperBlockFactory(), new DiagonalAFactory(), new DiagonalBFactory());
	}
}
