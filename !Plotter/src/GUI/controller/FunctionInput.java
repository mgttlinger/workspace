package GUI.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import parser.exceptions.ParserException;
import term.functions.Function;
import term.variable.Variable;
import GUI.explainTree.ExplainTreeFrame;
import explainTree.ExplainTree;

public class FunctionInput extends JPanel {
	private static final long serialVersionUID = -7467986844206744924L;
	private final JTextField term, variable;
	private final JButton draw, explain;
	private final Controller controller;

	public FunctionInput(Controller controller) {
		this.controller = controller;
		term = new JTextField(50);
		term.setText("abl(sin(x*x)+exp(sin(x+x)),x)");
		variable = new JTextField(1);
		variable.setText("x");
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

	private Function getFunction() {
		try {
			return new Function(term.getText(), variable.getText());
		} catch (ParserException e) {
			System.err.printf("Der input %s konnte nicht geparst werden.\n", term.getText());
			e.printStackTrace();
			return null;
		}
	}

	private void draw() {
		Function f = getFunction();
		if (f == null)
			return;
		Variable x = f.getEnvironment().getVariable(variable.getText());
		controller.drawFunction(f, x, Color.red);
	}

	private void explain() {
		Function f = getFunction();
		if (f == null)
			return;
		new ExplainTreeFrame(new ExplainTree(getFunction()));
		//tree.output();
	}
}
