package GUI.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import GUI.raster.RasterFrame;

public class Controller extends JFrame {
	private static final long serialVersionUID = 1;

	private int numberFunctions;
	private final RasterFrame modell;

	private JButton clear, addFunction, output;
	private Container functions;

	public Controller() {
		this(1);
	}
	
	public Controller(int numberFunctions) {
		super("Controller");
		this.numberFunctions = numberFunctions;
		this.modell = new RasterFrame();

		setLayout(new BorderLayout());
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		add(setupButtons(), BorderLayout.PAGE_START);
		functions = setupInputs();
		add(functions, BorderLayout.CENTER);

	
		pack();
		setVisible(true);
		setResizable(false);
	}


	private Container setupButtons() {
		JPanel panel = new JPanel();
		clear = new MyJButton("loesche Raster");
		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				modell.raster.clear();
			}
		});
		panel.add(clear);

		addFunction = new MyJButton("Funktion hinzufuegen");
		addFunction.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				numberFunctions++;
				functions.add(new FunctionInput(modell));
				pack();
				repaint();
			}
		});
		panel.add(addFunction);

		output = new MyJButton("Raster speichern");
		output.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				modell.output();
			}
		});
		panel.add(output);

		return panel;
	}

	private Container setupInputs() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 1));
		for (int i = 0; i < numberFunctions; i++) {
			panel.add(new FunctionInput(modell));
		}
		return panel;
	}
}
