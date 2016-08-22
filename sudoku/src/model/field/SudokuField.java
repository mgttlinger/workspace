package model.field;

import model.dataSet.DataSet;
import model.dimensions.Dimensions;
import model.ruleSet.SodukuRules;

public class SudokuField extends ModelField {
	public SudokuField(Dimensions dim, DataSet data) {
		super(dim, new SodukuRules(), data);
	}
}
