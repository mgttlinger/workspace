package model.dataSet;

import java.util.Collection;
import java.util.Iterator;

import model.dimensions.Dimensions;

public class EssenceDataSet extends DataSet {

	public EssenceDataSet(Collection<DataSet> input) {
		super(input.iterator().next().getDimensions());
		
		Iterator<DataSet> it = input.iterator();
		DataSet ds = it.next();
		
		values = new int[dim.mnq];
		
		for (int i = 0; i < dim.mnq; i++)
			values[i] = ds.getValue(i);
		
		for (; it.hasNext(); ds = it.next()) {
			assert dim.equals(ds.getDimensions()); 
			for (int i = 0; i < dim.mnq; i++) {
				if (values[i] != ds.getValue(i))
					values[i] = 0;
			}
		}
	}

}
