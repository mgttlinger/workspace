package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import model.dataSet.IDataSet;
import model.dimensions.Dimensions;
import model.rules.RuleFactory.Rule;
import model.rules.SuperBlockFactory;

public class SudokuPanel extends JPanel implements IDataSet {
	static final int space_normal = 1;
	static final int space_superBlock = 5;
	static final Dimension size_textField =new Dimension(25,25);

	private Dimensions dim;
	private JTextField[] fields;

	public SudokuPanel(Dimensions dim, boolean editable) {
		super();
		this.dim = dim;

		setLayout(new GridLayout(dim.n, dim.m, space_superBlock, space_superBlock));
		setBackground(Color.black);
		setBorder(new LineBorder(Color.black, space_superBlock));

		fields = new JTextField[dim.mnq];

		Container[] superBlocks=new Container[dim.mn];
		for(int i=0;i<dim.mn;i++){
			superBlocks[i]=new JPanel();
			superBlocks[i].setBackground(Color.BLACK);
			superBlocks[i].setLayout(new GridLayout(dim.m, dim.n,space_normal,space_normal));
			add(superBlocks[i]);
		}
		
		Rule r=new SuperBlockFactory().getNewRule(dim);
		for (int i = 0; i < dim.mnq; i++) {
			fields[i] = new JTextField();
			fields[i].setPreferredSize(size_textField);
			fields[i].setBorder(null);
			fields[i].setEditable(editable);
			fields[i].setBackground(Color.white);
			fields[i].setHorizontalAlignment(JTextField.CENTER);
			superBlocks[r.value(i)].add(fields[i]);
		}
	}

	public JTextField getTextField(int n) {
		return fields[n];
	}

	@Override
	public int getValue(int n) {
		if (fields[n].getText().equals(""))
			return 0;
		else
			return Integer.parseInt(fields[n].getText());
	}

	@Override
	public boolean isFilled(int n) {
		return !fields[n].getText().equals("");
	}

	@Override
	public void setValue(int index, int value) {
		getTextField(index).setText(Integer.toString(value));
	}

	@Override
	public Dimensions getDimensions() {
		return dim;
	}
}
