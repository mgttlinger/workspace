package solver;

import java.util.ArrayList;
import java.util.Collection;

import model.dataSet.DataSet;
import model.dimensions.Dimensions;
import model.field.ModelField;

public class TryingSolver {

	public static Collection<DataSet> run(ModelField input) {
		ArrayList<DataSet> output = new ArrayList<DataSet>();
		run(input, output);

		return output;
	}

	public static void run(ModelField f, Collection<DataSet> solutions) {
		LogicalSolver.run(f);
		if (f.finished()) {
			solutions.add(new DataSet(f));
			return;
		}

		Dimensions dim = f.getDimensions();
		int minOptions = dim.mn + 1;
		int target = -1;
		for (int i = 0; i < f.blocks.length; i++) {
			int o = f.blocks[i].getNumberOptions();
			if (o < minOptions && o > 0) {
				target = i;
				minOptions = o;
			}
		}

		if (target == -1)
			return;

		ModelField[] output = new ModelField[minOptions];
		for (int i = 0; i < output.length; i++)
			output[i] = new ModelField(f);

		int k = 0;
		for (int i = 0; i < dim.mn; i++)
			if (f.blocks[target].isOption(i)) {
				output[k].blocks[target].setIndex(i);
				run(output[k], solutions);
				k++;
			}
	}

}
