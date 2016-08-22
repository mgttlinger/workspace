package explainTree;

import term.ITerm;

public class ExplainTree extends Node {
	public ExplainTree(ITerm wurzel) {
		super(wurzel, 0, 0);
	}

	public void explain() {
		explain("");
	}
}
