package model.ruleSet;

import java.io.Serializable;
import java.util.Arrays;

import model.dimensions.Dimensions;
import model.rules.RuleFactory;
import model.rules.RuleFactory.Rule;

public class RuleSet implements Serializable {
	final private RuleFactory[] factory;

	public RuleSet(RuleFactory... factory) {
		this.factory = factory;
	}

	public Rule[] getRules(Dimensions dim) {
		Rule[] rules = new Rule[factory.length];
		for (int i = 0; i < factory.length; i++)
			rules[i] = factory[i].getNewRule(dim);
		return rules;
	}

	@Override
	public String toString() {
		return Arrays.toString(factory);
	}
}
