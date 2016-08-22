import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import static java.lang.Math.sin;
import static java.lang.Math.cos;
import static java.awt.RenderingHints.*;

public class Animation extends JPanel {
	A a;

	private final Path2D.Double path = new Path2D.Double();

	class Ticker extends Thread {
		public void run() {
			while (true) {
				a.next();
				repaint();
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public Animation(A a) {
		this.a = a;
		JFrame f = new JFrame();
		f.add(this);
		f.pack();
		f.setVisible(true);
		new Ticker().start();
	}

	public Dimension getPreferredSize() {
		return new Dimension(1000, 800);
	}

	@Override
	public void paint(Graphics _g) {
		Graphics2D g = (Graphics2D) _g;
		g.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);

		g.clearRect(0, 0, getWidth(), getHeight());
		double faktor = Math.min(getHeight(), getWidth()) / 4;
		int x0 = getWidth() / 2;
		int y0 = getHeight() / 2;
		int x1 = x0 + (int) (faktor * a.l1 * sin(a.p1));
		int y1 = y0 + (int) (faktor * a.l1 * cos(a.p1));
		int x2 = x1 + (int) (faktor * a.l2 * sin(a.p2));
		int y2 = y1 + (int) (faktor * a.l2 * cos(a.p2));

		g.drawLine(x0, y0, x1, y1);
		g.drawLine(x1, y1, x2, y2);
		g.fillArc(x1 - 25, y1 - 25, 50, 50, 0, 360);
		g.fillArc(x2 - 25, y2 - 25, 50, 50, 0, 360);
		try {
			path.lineTo(x2, y2);
		} catch (java.awt.geom.IllegalPathStateException e) {
			path.moveTo(x2, y2);
		}
		g.setColor(Color.red);
		g.draw(path);
	}

	public static void main(String[] args) {
		A a = new A(1, 1, 1, 1, 0.01, 1, 3);
		Animation anim = new Animation(a);
	}
}
