package GUI.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import GUI.explainTree.ExplainTreeFrame;
import GUI.raster.RasterFrame;
import explainTree.ExplainTree;

public class FunctionInput extends JPanel {
	private static final long serialVersionUID = 1L;
	private final JTextField term, variable;
	private final JButton draw, explain;
	private final RasterFrame modell;

	public FunctionInput(RasterFrame modell) {
		 this(modell, "sin(x*x)+exp(sin(x+x))", "x");
	}
	
	
	public FunctionInput(RasterFrame modell, String start_term, String var_name) {
		this.modell = modell;
		term = new JTextField(50);
		term.setText(start_term);
		variable = new JTextField(1);
		variable.setText(var_name);
		draw = new MyJButton("zeichnen");
		draw.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				draw();
			}
		});

		explain = new MyJButton("explain-Tree");
		explain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				explain();
			}
		});
		add(term);
		add(variable);
		add(draw);
		add(explain);
	}

	private void draw() {
		//modell.drawFunction(f, x);
		modell.raster.parseAndDrawFunction(term.getText(), variable.getText());
	}

	private void explain() {
//		Function f = getFunction();
//		if (f == null)
//			return;
//		new ExplainTreeFrame(new ExplainTree(getFunction()));
//		//tree.output();
	}
}
