package model.szenario;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import solver.*;

import model.dataSet.DataSet;
import model.dataSet.EssenceDataSet;
import model.dataSet.IDataSet;
import model.dimensions.Dimensioned;
import model.dimensions.Dimensions;
import model.field.ModelField;
import model.ruleSet.RuleSet;

public class Szenario extends Dimensioned {
	private static final long serialVersionUID = 4982647190733181775L;
	private final DataSet input;
	private DataSet output_essence;
	private final Collection<DataSet> output;
	private final RuleSet ruleSet;
	final private int hashCode;

	public Szenario(Dimensions dim, RuleSet ruleSet, IDataSet input) {
		super(dim);
		this.ruleSet = ruleSet;
		this.input = new DataSet(dim, input);
		this.output = new ArrayList<DataSet>();

		int code = 0;
		for (int i = 0; i < dim.numberBlocks; i++) {
			code ^= input.getValue(i) << (i % 28);
		}
		hashCode = (code & 0x00ffffff) | dim.hashCode();
	}

	@Override
	public final int hashCode() {
		return hashCode;
	}

	public void print(PrintStream writer) {
		writer.printf("Szenario%n");
		writer.printf("   hash:       %8x%n", hashCode());
		writer.printf("   dimensions: %s%n", dim);
		writer.printf("   rules:      %s%n", ruleSet);
		writer.printf("   input:      %s%n", input);
		writer.printf("   essence:    %s%n", output_essence);
		for (IDataSet out : output) {
			writer.printf("   output:     %s%n", out);
		}
	}

	public void save() {
		if(output_essence==null)
			solve();
		FileManager.saveSzenario(this);
	}
	
	public Collection<DataSet> getSolutions(){
		return output;
	}
	
	public DataSet getEssence(){
		return output_essence;
	}

	public void solve() {
		ModelField temp = new ModelField(dim, ruleSet, input);
		TryingSolver.run(temp, output);
		
		output_essence=new EssenceDataSet(output);
	}
}
