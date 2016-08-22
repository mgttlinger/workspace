package meta;

import java.util.HashSet;

import model.dataSet.*;
import model.dimensions.*;
import model.ruleSet.*;
import model.rules.*;
import model.szenario.*;

@SuppressWarnings("unused")
public class Console {
	public static void main(String[] args) {
		Dimensions dim = new Dimensions(3, 3);
		Szenario s = new Szenario(dim, new SodukuRules(), new SplitStringDataSet(dim,
				"001070000000800400800000003103020080004503700050010302200000009006004000000090100", ""));
		s.solve();
		s.save();

		s.print(System.out);

		// s = FileManager.readSzenario("33475422");
		// s.print(System.out);
	}
}
