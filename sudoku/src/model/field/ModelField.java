package model.field;

import model.Block;
import model.LogicDecoration;
import model.Unit;
import model.dataSet.DataSet;
import model.dataSet.IDataSet;
import model.dimensions.Dimensioned;
import model.dimensions.Dimensions;
import model.ruleSet.RuleSet;

public class ModelField extends Dimensioned implements IDataSet {
	final public Block[] blocks;
	final public Unit[][] units;
	final private RuleSet ruleFactory;

	final private IDataSet dataset;

	public ModelField(Dimensions dim, RuleSet ruleFactory, IDataSet data) {
		super(dim);
		this.ruleFactory = ruleFactory;
		this.dataset = data;

		LogicDecoration logic = new LogicDecoration(dim, ruleFactory.getRules(dim), data);
		blocks = logic.getBlocks();
		units = logic.getUnits();

		for (int i = 0; i < dim.numberBlocks; i++) {
			if (data.isFilled(i))
				blocks[i].setValue(data.getValue(i));
		}
	}

	public ModelField(ModelField mf) {
		this(mf.dim, mf.ruleFactory, mf);
	}

	public boolean finished() {
		return getFilledValues() == dim.mnq;
	}

	public int getFilledValues() {
		int sum = 0;
		for (Block b : blocks)
			if (b.isFilled())
				sum++;
		return sum;
	}

	@Override
	public int getValue(int n) {
		return blocks[n].getValue();
	}

	@Override
	public boolean isFilled(int i) {
		return blocks[i].isFilled();
	}

	@Override
	public void setValue(int index, int value) {
		dataset.setValue(index, value);
	}

	@Override
	public String toString() {
		return new DataSet(this).toString();
	}
}
