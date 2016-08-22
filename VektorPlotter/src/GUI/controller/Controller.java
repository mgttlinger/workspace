package GUI.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import parser.Parser;
import parser.environment.Environment;
import parser.exceptions.ParserException;
import term.IFunction;
import term.ITerm;
import term.constant.Constant;
import term.functions.Function;
import term.variable.Variable;
import entities.Matrix;
import entities.ParsedFeld;
import entities.VektorFeld;
import GUI.raster.RasterFrame;

public class Controller extends JFrame {
	private static final long serialVersionUID = 6643474457020563983L;

	private int numberFunctions;
	private final RasterFrame frame;

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

		// add(setupButtons(), BorderLayout.PAGE_START);

		JTabbedPane tabbedPane = new JTabbedPane();

		tabbedPane.addTab("Matrix", null, matrixPanel(), "Die Eingabe des Vektorfeldes als 2x2-Matrix.");
		try {
			tabbedPane.addTab("Parse", null, parserPanel(), "Die Eingabe des Vektorfeldes als beliebige 2d-Funktionen.");
		} catch (Error e) {
			tabbedPane.addTab("Parse", null, new JPanel(), "Das Modul konnte nicht geladen werden. Skala fehlt.");
			//System.err.println("Kein Parse-Modul");
		}

		this.add(tabbedPane);

		pack();
		setVisible(true);
		setResizable(false);
	}

	private Container matrixPanel() {
		final JPanel panel = new JPanel();

		final JLabel la = new JLabel("a:");
		final JLabel lb = new JLabel("b:");
		final JLabel lc = new JLabel("c:");
		final JLabel ld = new JLabel("d:");

		final JTextField ia = new JTextField("1");
		final JTextField ib = new JTextField("0");
		final JTextField ic = new JTextField("0");
		final JTextField id = new JTextField("1");

		final JLabel lD = new JLabel("Determinante:");
		final JLabel lT = new JLabel("Trace:");
		final JLabel ll1 = new JLabel("Eigenwert 1:");
		final JLabel ll2 = new JLabel("Eigenwert 2:");
		final JLabel vD = new JLabel("");
		final JLabel vT = new JLabel("");
		final JLabel vl1 = new JLabel("");
		final JLabel vl2 = new JLabel("");

		MyJButton button = new MyJButton("Matrix anzeigen");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					double a = Double.parseDouble(ia.getText());
					double b = Double.parseDouble(ib.getText());
					double c = Double.parseDouble(ic.getText());
					double d = Double.parseDouble(id.getText());

					Matrix M = new Matrix(a, b, c, d);
					frame.setFeld(M);
					// frame.displayArrows(true);
					vD.setText(Double.toString(M.det()));
					vT.setText(Double.toString(M.trace()));
					vl1.setText(Double.toString(M.lambda1()) + ", ("+M.ev1x()+", "+M.ev1y()+")");
					vl2.setText(Double.toString(M.lambda2()) + ", ("+M.ev2x()+", "+M.ev2y()+")");
				} catch (NumberFormatException e) {
					System.err.println("Input konnte nicht geparset werden: a=" + ia.getText() + ", b=" + ib.getText() + ", c=" + ic.getText() + ", d=" + id.getText());
				}
			}
		});

		panel.setLayout(new GridLayout(0, 2));
		panel.add(la);
		panel.add(ia);
		panel.add(lb);
		panel.add(ib);
		panel.add(lc);
		panel.add(ic);
		panel.add(ld);
		panel.add(id);

		panel.add(button);
		panel.add(new JLabel(""));

		panel.add(lD);
		panel.add(vD);
		panel.add(lT);
		panel.add(vT);
		panel.add(ll1);
		panel.add(vl1);
		panel.add(ll2);
		panel.add(vl2);

		return panel;
	}

	protected JComponent parserPanel() {
		final JPanel panel = new JPanel();
		final JLabel lx = new JLabel("f1(x, y):");
		final JLabel ly = new JLabel("f2(x, y):");

		final JTextField ix = new JTextField("sin(1/x)");
		final JTextField iy = new JTextField("sin(1/y)");

		MyJButton button = new MyJButton("Matrix anzeigen");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					Variable[] vars = new Variable[] { new Variable("x"), new Variable("y") };
					Constant[] cons = new Constant[] { new Constant(Math.E, "e"), new Constant(Math.PI, "pi") };
					Environment env = new Environment(vars, cons);

					ITerm tx = Parser.parse(ix.getText(), env);
					ITerm ty = Parser.parse(iy.getText(), env);

					IFunction fx = new Function(tx, env);
					IFunction fy = new Function(ty, env);

					VektorFeld feld = new ParsedFeld(fx, fy);

					frame.setFeld(feld);
				} catch (ParserException e) {
					System.err.println("Input konnte nicht geparset werden: fx=" + ix.getText() + ", fy=" + iy.getText());
				}
			}
		});
		panel.setLayout(new GridLayout(0, 2));
		panel.add(lx);
		panel.add(ix);
		panel.add(ly);
		panel.add(iy);
		panel.add(button);
		return panel;
	}
}
