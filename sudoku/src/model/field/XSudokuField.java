package model.field;

import model.dataSet.DataSet;
import model.dimensions.Dimensions;
import model.ruleSet.XSodukuRules;

public class XSudokuField extends ModelField {
	public XSudokuField(Dimensions dim, DataSet data) {
		super(dim, new XSodukuRules(), data);
	}
}
