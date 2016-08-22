package model;

import java.util.ArrayList;

import model.dimensions.Dimensions;
import model.dataSet.IDataSet;
import model.dimensions.Dimensioned;
import model.rules.RuleFactory.Rule;

public class LogicDecoration extends Dimensioned {
	private Block[] blocks;
	private Unit[][] units;

	public LogicDecoration(Dimensions dim, Rule[] rules, IDataSet data) {
		super(dim);

		units = new Unit[rules.length][];
		for (int i = 0; i < rules.length; i++) {
			units[i] = new Unit[rules[i].getAmount()];
			for (int j = 0; j < rules[i].getAmount(); j++)
				units[i][j] = new Unit(dim);
		}

		blocks = new Block[dim.numberBlocks];		
		for (int i = 0; i < dim.mnq; i++) {
			ArrayList<Unit> enviro = new ArrayList<Unit>(rules.length);
			for (int j = 0; j < rules.length; j++) {
				int e = rules[j].value(i);
				if (e >= 0)
					enviro.add(units[j][e]);
			}
			Block b = new Block(dim, enviro.toArray(new Unit[0]));
			for (Unit u : enviro)
				u.append(b);
			blocks[i] = b;
		}

		for (Block b : blocks)
			b.updateOptions();
	}

	public Block getBlock(int n) {
		return blocks[n];
	}

	public Block[] getBlocks() {
		return blocks;
	}

	public Unit getUnit(int r, int n) {
		return units[r][n];
	}

	public Unit[][] getUnits() {
		return units;
	}
}
