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
	A[] a;

	class Ticker extends Thread {
		public void run() {
			while (true) {
				for (int i = 0; i < 2; i++)
					for(A _a:a)
						_a.next();
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

	public Animation(A[] a) {
		this.a = a;
		JFrame f = new JFrame();
		f.add(this);
		f.pack();
		f.setVisible(true);
		new Ticker().start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public Dimension getPreferredSize() {
		return new Dimension(1000, 800);
	}

	static int faktor = 250;
	
	@Override
	public void paint(Graphics _g) {
		Graphics2D g = (Graphics2D) _g;
		g.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
		g.clearRect(0, 0, getWidth(), getHeight());

		

		int x0 = getWidth() / 2;
		int y0 = getHeight() / 2;
		for(A _a:a)
		paint(_a, x0, y0, g);
	}
	
	void paint(A a, int x0, int y0, Graphics2D g){
		int x1 = (int) (x0 + a.x * faktor);
		int y1 = (int) (y0 + a.y);
		int x2 = (int) (x1 + a.r * sin(a.p) * faktor);
		int y2 = (int) (y1 - a.r * cos(a.p) * faktor);
		
		g.drawLine(0, y0, getWidth(), y0);
		g.drawLine(x0, 0, x0, getHeight());
		g.setColor(Color.red);
		g.drawLine(x1 - 20, y1 - 20, x1 + 20, y1 + 20);
		g.drawLine(x1 - 20, y1 + 20, x1 + 20, y1 - 20);

		g.setColor(Color.black);
		g.drawArc(x2 - 20, y2 - 20, 40, 40, 0, 360);
		g.drawLine(x1, y1, x2, y2);
	}

	public static void main(String[] args) {
		A a = new A(1, 1, 1, 0.005);
		a.setState(0, 0, 0, 0, -0.5, 0, Math.PI*+1.5 , a.l);

		A a2 = new A(1, 1, 1, 0.005);
		a2.setState(0, 0, 0, 0, 0.5, 0, Math.PI*-1.5 , a.l);
		Animation anim = new Animation(new A[]{a, a2});
	}
}
