package GUI.explainTree;

import javax.swing.JFrame;

import explainTree.ExplainTree;

public class ExplainTreeFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public ExplainTreeFrame(ExplainTree et) {
		add(new ExplainTreeContainer(et));
		this.setVisible(true);
		this.pack();
	}
}
