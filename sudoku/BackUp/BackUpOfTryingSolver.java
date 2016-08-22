package solver;

import java.io.PrintStream;
import java.util.ArrayList;
import model.dimensions.Dimensions;
import model.field.ModelField;

public class BackUpOfTryingSolver extends LogicalSolver {
	ArrayList<ModelField> solutions;

	public BackUpOfTryingSolver(Dimensions dim) {
		super(dim);
		solutions = new ArrayList<ModelField>();
	}

	public ArrayList<ModelField> getSolutions() {
		return solutions;
	}

	public void printSolutions(PrintStream out) {
		out.println("Solution Count: " + solutions.size());
		for (ModelField f : solutions)
			out.println(f);
	}

	
	
	@Override
	public void run(ModelField f) {
		super.run(f);

		if (f.finished()) {
			solutions.add(f);
			return;
		}

		int minOptions = dim.mn + 1;
		int target = -1;
		for (int i = 0; i < f.blocks.length; i++) {
			int o = f.blocks[i].getOptions().getAmount();
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
			if (f.blocks[target].getOptions().isOption(i)) {
				output[k].blocks[target].setIndex(i);
				run(output[k]);
				k++;
			}
	}
}
