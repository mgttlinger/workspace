package model.dataSet;

import java.io.Serializable;
import java.util.Arrays;

import model.dimensions.Dimensioned;
import model.dimensions.Dimensions;

public class DataSet extends Dimensioned implements IDataSet, Serializable {
	protected int[] values;

	protected DataSet(Dimensions dim) {
		super(dim);
		assert dim!=null;
	}

	public DataSet(Dimensions dim, IDataSet input) {
		this(dim);
		assert input!=null;
		assert input.getDimensions().equals(dim);
		values = new int[dim.numberBlocks];
		for (int i = 0; i < dim.numberBlocks; i++)
			values[i] = input.getValue(i);
	}

	public DataSet(IDataSet ds) {
		this(ds.getDimensions());
		assert ds!=null;
		values = new int[dim.numberBlocks];
		for (int i = 0; i < dim.numberBlocks; i++)
			values[i] = ds.getValue(i);
	}

	@Override
	public int getValue(int n) {
		assert n>=0 && n<dim.numberBlocks;
		return values[n];
	}

	@Override
	public boolean isFilled(int n) {
		assert n>=0 && n<dim.numberBlocks;
		return values[n] != 0;
	}

	@Override
	public void setValue(int index, int value) {
		assert index>=0 && index<dim.numberBlocks;
		assert value>=0 && value<=dim.mn;
		values[index] = value;
	}

	@Override
	public String toString() {
		return Arrays.toString(values);
	}
}
