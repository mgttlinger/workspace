package model.dataSet;

import model.dimensions.Dimensions;

public class FormatStringDataSet extends DataSet {
	private static int subM(String s) {
		String[] splitString = s.split("\\.");
		String[] mn = splitString[0].split("x");
		return Integer.parseInt(mn[0]);
	}

	private static int subN(String s) {
		String[] splitString = s.split("\\.");
		String[] mn = splitString[0].split("x");
		return Integer.parseInt(mn[1]);
	}

	int[] values;

	private FormatStringDataSet(Dimensions dim) {
		super(dim);
	}

	public FormatStringDataSet(String s) {
		this(new Dimensions(subM(s), subN(s)));
		values = new int[dim.mnq];
		try {
			String[] splitString = s.split("\\.");
			String[] v = splitString[1].split(",");
			for (int i = 0; i < dim.mnq; i++)
				values[i] = Integer.parseInt(v[i]);
		} catch (Exception e) {
			System.err.println("Falscher Input für FormatStringDataSet");
			e.printStackTrace();
		}
	}

}
