package meta;

import model.dataSet.DataSet;
import model.dataSet.SplitStringDataSet;
import model.dimensions.Dimensions;
import model.field.ModelField;
import model.ruleSet.SodukuRules;
import solver.logicalMethods.MethodList;
import solver.logicalMethods.SolvingMethod;

public class Benchmarking {
	private static long benchmark(ModelField f, SolvingMethod m) {
		long start = System.currentTimeMillis();
		m.solve(f);
		return System.currentTimeMillis() - start;
	}

	public static void main(String[] args) {
		Dimensions dim = new Dimensions(3, 3);
		DataSet ds = new SplitStringDataSet(null, "001070000000800400800000003103020080004503700050010302200000009006004000000090100", "");
		MethodList methods = new MethodList(dim);

		System.out.printf("%-30s # %5s%n", "Method", "Time");
		System.out.println("######################################");
		for (SolvingMethod method : methods) {
			long zeit = 0;
			// for (int i = 0; i < 100; i++) {
			ModelField f = new ModelField(dim, new SodukuRules(), ds);
			zeit += benchmark(f, method);
			// }
			System.out.printf("%-30s # %5d", method.getClass().getName(), zeit);
			System.out.println();
		}
	}
}
