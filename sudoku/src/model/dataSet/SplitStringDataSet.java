package model.dataSet;

import model.dimensions.Dimensions;

public class SplitStringDataSet extends DataSet {

	public SplitStringDataSet(Dimensions dim, String s, String splitter) {
		super(dim);
		String[] split = s.split(splitter);
		assert s!=null;
		assert splitter!=null;
		if (split[0].equals("")) {
			assert split.length==dim.numberBlocks+1;
			values = new int[split.length - 1];
			for (int i = 0; i < split.length - 1; i++)
				values[i] = Integer.parseInt(split[i + 1]);
		} else {
			assert split.length==dim.numberBlocks;
			values = new int[split.length];
			for (int i = 0; i < split.length; i++)
				values[i] = Integer.parseInt(split[i]);
		}
	}
}
