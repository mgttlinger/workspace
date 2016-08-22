package GUI.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import term.functions.Function;
import term.variable.Variable;
import GUI.raster.RasterFrame;

public class Controller extends JFrame {
	private static final long serialVersionUID = 6643474457020563983L;

	private int numberFunctions;
	private final RasterFrame frame;

	private JButton clear, addFunction, output;
	private Container functions;

	public Controller(RasterFrame frame) {
		this(1, frame);
	}
	
	public Controller(int numberFunctions, RasterFrame frame) {
		super("Controller");
		this.numberFunctions = numberFunctions;
		this.frame = frame;

		BorderLayout layout = new BorderLayout();
		setLayout(layout);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		add(setupButtons(), BorderLayout.PAGE_START);
		functions = setupInputs();
		add(functions, BorderLayout.CENTER);

		pack();
		setVisible(true);
		setResizable(false);
	}

	private void addFunction() {
		numberFunctions++;
		functions.setLayout(new GridLayout(numberFunctions, 1));
		functions.add(new FunctionInput(this));
		pack();
		repaint();
	}

	private void clear() {
		frame.clear();
	}

	private void output() {
		frame.output();
	}

	public void drawFunction(Function f, Variable variable, Color color) {
		frame.drawFunction(f, variable, color);
	}

	private Container setupButtons() {
		JPanel panel = new JPanel();
		clear = new MyJButton("lösche Raster");
		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				clear();
			}
		});
		panel.add(clear);

		addFunction = new MyJButton("Funktion hinzufügen");
		addFunction.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addFunction();
			}
		});
		panel.add(addFunction);

		output = new MyJButton("Raster speichern");
		output.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				output();
			}
		});
		panel.add(output);

		return panel;
	}

	private Container setupInputs() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(numberFunctions, 1));
		for (int i = 0; i < numberFunctions; i++) {
			panel.add(new FunctionInput(this));
		}
		return panel;
	}
}
