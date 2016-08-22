package gui;

import java.awt.Button;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import model.dataSet.DataSet;
import model.dataSet.IDataSet;
import model.dimensions.Dimensions;
import model.field.ModelField;
import model.ruleSet.RuleSet;
import model.ruleSet.SodukuRules;
import model.szenario.Szenario;
import solver.LogicalSolver;

public class Window extends JFrame {
	private static final long serialVersionUID = 1L;
	private final Dimensions dim;
	SudokuPanel input, output;

	public Window(Dimensions dim) {
		super("HALLI HALLO");
		this.dim = dim;

		input = new SudokuPanel(dim, true);
		output = new SudokuPanel(dim, false);
		add(input);
		add(output);

		// Button-Panel
		JButton solveButton = new JButton("solve()");
		solveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				buttonSolveClicked();
			}
		});
		JButton saveButton = new JButton("save()");
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				buttonSaveClicked();
			}
		});
		add(solveButton);
		add(saveButton);
		
		JPanel buttons=new JPanel();
		buttons.add(solveButton);
		buttons.add(saveButton);
		buttons.setLayout(new GridLayout(1, 2));
		add(buttons);
		
		JLabel input_label=new JLabel("input") ;
		JLabel output_label=new JLabel("output");
		add(input_label);
		add(output_label);

		
		int space = 10;
		Container contentPane = this.getContentPane();
		SpringLayout layout = new SpringLayout();


		// sudokus
		layout.putConstraint(SpringLayout.NORTH, output, 0, SpringLayout.NORTH, input);
		layout.putConstraint(SpringLayout.SOUTH, output, 0, SpringLayout.SOUTH, input);
		layout.putConstraint(SpringLayout.WEST, output, space, SpringLayout.EAST, input);
		layout.putConstraint(SpringLayout.WEST, input, space, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.EAST, contentPane, space, SpringLayout.EAST, output);
		layout.putConstraint(SpringLayout.NORTH, input, (int)input_label.getPreferredSize().getHeight()+space, SpringLayout.NORTH,contentPane );
		layout.putConstraint(SpringLayout.SOUTH, contentPane, (int)buttons.getPreferredSize().getHeight()+2*space, SpringLayout.SOUTH, input);
		
		// buttons
		layout.putConstraint(SpringLayout.NORTH, buttons, space, SpringLayout.SOUTH, input);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, buttons, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
		
		// labels
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, input_label, 0, SpringLayout.HORIZONTAL_CENTER, input);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, output_label, 0, SpringLayout.HORIZONTAL_CENTER, output);
		layout.putConstraint(SpringLayout.SOUTH, input_label, 0, SpringLayout.NORTH, input);
		layout.putConstraint(SpringLayout.SOUTH, output_label, 0, SpringLayout.NORTH, output);
		
//		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, labelInput, 0, SpringLayout.HORIZONTAL_CENTER, input);
//		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, labelOutput, 0, SpringLayout.HORIZONTAL_CENTER, output);		
		
		setLayout(layout);
		pack();
//		setResizable(false);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		setVisible(true);
	}

	public void buttonSolveClicked() {
		Szenario s=new Szenario(dim, new SodukuRules(), input);
		s.solve();
		setOutput(s.getEssence());
	}

	public void buttonSaveClicked() {
		Szenario s=new Szenario(dim, new SodukuRules(), input);
		s.solve();
		s.save();
	}
	
	public void setOutput(IDataSet f) {
		for (int i = 0; i < dim.numberBlocks; i++) {
			int v = f.getValue(i);
			if (v == 0)
				output.getTextField(i).setText("");
			else
				output.getTextField(i).setText(Integer.toString(v));
		}
	}

	public void setInput(IDataSet f) {
		for (int i = 0; i < dim.numberBlocks; i++) {
			int v = f.getValue(i);
			if (v == 0)
				input.getTextField(i).setText("");
			else
				input.getTextField(i).setText(Integer.toString(v));
		}
	}
	
	
	public static void main(String[] arg) {
		new Window(new Dimensions(3, 3));
	}
}