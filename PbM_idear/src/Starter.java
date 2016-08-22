import java.awt.Graphics;

import javax.swing.JFrame;

import modell.Mass;

import term.ITerm;
import term.constant.*;
import term.operations.binary.*;
import term.variable.*;

import modell.*;

public class Starter {
	static double dt = 0.001;
	static GooBall a, b;
	static modell.System S;

	static public void step() {
		Variable aax = S.solve(a.x(), a.vx());
		Variable aay = S.solve(a.y(), a.vy());

		Variable bax = S.solve(b.x(), b.vx());
		Variable bay = S.solve(b.y(), b.vy());

		a.vx().assign(a.vx().evaluate() + dt * aax.evaluate());
		a.vy().assign(a.vy().evaluate() + dt * aay.evaluate());
		a.x().assign(a.x().evaluate() + dt * a.vx().evaluate());
		a.y().assign(a.y().evaluate() + dt * a.vy().evaluate());

		b.vx().assign(b.vx().evaluate() + dt * bax.evaluate());
		b.vy().assign(b.vy().evaluate() + dt * bay.evaluate());
		b.x().assign(b.x().evaluate() + dt * b.vx().evaluate());
		b.y().assign(b.y().evaluate() + dt * b.vy().evaluate());

	}

	public static void main(String... args) {
		a = new GooBall(2, 2, 0, 0, 1, 2, 3.5);
		b = new GooBall(5, 2, 0, 0, 1, 2, 3.5);

		a.addConnection(b);
		b.addConnection(a);

		S = new modell.System();
		S.addGoo(a);
		S.addGoo(b);

		final int faktor = 50;

		final JFrame f = new JFrame() {
			@Override
			public void paint(Graphics g) {
				java.lang.System.out.printf("a.x: %f, a.y: %f, b.x: %f, b.y: %f\n", a.x().evaluate(), a.y().evaluate(), b.x().evaluate(), b.y().evaluate());
				g.clearRect(0, 0, getWidth(), getHeight());
				g.fillArc((int) (a.x().evaluate() * faktor), (int) (a.y()
						.evaluate() * faktor), faktor, faktor, 0, 360);
				g.fillArc((int) (b.x().evaluate() * faktor), (int) (b.y()
						.evaluate() * faktor), faktor, faktor, 0, 360);
			}
		};

		f.setSize(1000, 800);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
		
		new Thread() {
			@Override
			public void run() {
				while (true) {
					step();
					f.repaint();
					try {
						sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
}
