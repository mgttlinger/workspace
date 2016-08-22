package model.dataSet;

import model.dimensions.Dimensions;

public class ArrayDataSet extends DataSet {

	public ArrayDataSet(Dimensions dim, int[] v) {
		super(dim);
		assert dim.numberBlocks==v.length;
		values = v;
	}
}
