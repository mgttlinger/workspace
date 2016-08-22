package model.dataSet;

import model.dimensions.Dimensions;

public interface IDataSet {
	public Dimensions getDimensions();

	public int getValue(int i);

	public boolean isFilled(int i);

	public void setValue(int index, int value);
}
