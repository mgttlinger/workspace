	package test;

import java.io.FileNotFoundException;

import parser.exceptions.ParserException;
import GUI.controller.Controller;
import GUI.raster.RasterFrame;

@SuppressWarnings("unused")
public class test {
	public static void main(String[] args) throws FileNotFoundException, ParserException {
		// System.setOut(new PrintStream("output.txt") );

		// final Variable x = new Variable("x");
		// final ITerm term = new Integral((ITerm) (new Multiplication(x, x)),
		// x, (ITerm) Constant.zero, x);

		final RasterFrame r = new RasterFrame();
		final Controller c = new Controller(r);

		// new Thread() {
		// @Override
		// public void run() {
		// try {
		// sleep(500);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// r.drawFunction(new Function(term, x), x, Color.blue);
		//
		// }
		// }.start();

		// parser.Parser.

		// System.out.println(new TokenSet("-x").toString());

		// Function f=new Function("sin(x*x)+exp(sin(x+x))", "x");
		// new ExplainTree(f).output();

		// JFrame f=new JFrame();
		// // //JColorChooser c=new JColorChooser(Color.black);
		// // JFileChooser c=new JFileChooser();
		// // f.add(c);
		// // f.pack();
		// // f.setVisible(true);
		//
		// JFileChooser chooser = new JFileChooser();
		// FileNameExtensionFilter filter = new FileNameExtensionFilter(
		// "JPG & PNG Images", "jpg", "png");
		// chooser.setFileFilter(filter);
		// int returnVal = chooser.showOpenDialog(f);
		// if(returnVal == JFileChooser.APPROVE_OPTION) {
		// System.out.println("You chose to open this file: " +
		// chooser.getSelectedFile().getName());
		// }

		// Variable v = new Variable("x");
		// ITerm bla=new Sin(new Division(Constant.one, v));
		//
		//
		// Newton.solve(bla, v, 15.0);
		// System.out.printf("f( %s ) = %s%n",v.evaluate(),bla.evaluate());
	}
}
