package GUI.raster;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import GUI.raster.path.Axes;
import GUI.raster.path.Background;
import GUI.raster.path.Component;
import GUI.raster.path.PotLine;
import entities.Matrix;
import entities.VektorFeld;

@SuppressWarnings("serial")
public class Raster extends JPanel implements Observer {

	// fest, bis zum Ändern der Konfiguration
	private transient AffineTransform toDrawPlane;

	// Konfiguration
	private final Configuration config;
	private static Color colorBg = new Color(255, 255, 255);
	private static int startWidth = 750, startHeight = 750;
	private double sizeFactor = 0.2;
	private boolean displayArrows = false;

	// momentaner Zustand
	private VektorFeld feld = new Matrix(1, 0, 0, 1);
	private final Component axes, background;

	public Raster() {
		setPreferredSize(new Dimension(startWidth, startHeight));
		config = new Configuration();
		config.addObserver(this);
		axes = new Axes(config);
		background = new Background(config);
		update();
	}

	private void draw(Graphics2D g, Component path) {
		g.setColor(path.getColor());
		g.draw(path.createTransformedShape(toDrawPlane));
	}

	@Override
	public void paint(Graphics g_) {
		Graphics2D graphics = (Graphics2D) g_;
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		graphics.setBackground(colorBg);
		graphics.clearRect(0, 0, getWidth(), getHeight());

		boolean fraktal = false;
		if(fraktal){
			AffineTransform inv = new AffineTransform(toDrawPlane);
			try {
				inv.invert();
			} catch (NoninvertibleTransformException e) {
				e.printStackTrace();
			}
			
			for(int x=0; x<this.getWidth(); x++)
				for(int y=0; y<this.getHeight(); y++){
					Point2D.Double pos_pixel = new Point2D.Double(x, y);
					Point2D.Double pos_logical = new Point2D.Double(x, y);
					inv.transform(pos_pixel, pos_logical);
					PotLine p = new PotLine(feld, pos_logical.x, pos_logical.y);
					
					float v = p.segments / (float)PotLine.steps;

					graphics.setColor(new Color(1f,1f,v));
					graphics.drawRect(x, y, 1, 1);
					
				}
		}
		
		draw(graphics, background);
		draw(graphics, axes);

		if (feld != null && displayArrows) {
			Path2D.Double path = new Path2D.Double();

			for (double y = config.getMaxY() - (config.getMaxY() % config.getRasterY()); y >= config.getMinY(); y -= config.getRasterY()) {
				for (double x = config.getMaxX() - (config.getMaxX() % config.getRasterX()); x >= config.getMinX(); x -= config.getRasterX()) {
					double tx = feld.transformX(x, y) * sizeFactor;
					double ty = feld.transformY(x, y) * sizeFactor;
					path.moveTo(x, y);
					path.lineTo(x + tx, y + ty);
				}
			}
			graphics.setColor(Color.red);
			graphics.draw(path.createTransformedShape(toDrawPlane));
		}

		if (feld != null && !displayArrows) {
			graphics.setColor(Color.blue);
			for (double y = config.getMaxY() - (config.getMaxY() % config.getRasterY()); y >= config.getMinY(); y -= config.getRasterY()) {
				for (double x = config.getMaxX() - (config.getMaxX() % config.getRasterX()); x >= config.getMinX(); x -= config.getRasterX()) {
					PotLine p = new PotLine(feld, x, y);
					PathIterator s = p.createTransformedShape(toDrawPlane).getPathIterator(new AffineTransform());

					double[] seg = new double[7];
					double tx = 0, ty = 0;
					long i = 0;
					while (!s.isDone()) {
						if (s.currentSegment(seg) == PathIterator.SEG_MOVETO) {
							tx = seg[0];
							ty = seg[1];
						}
						if (s.currentSegment(seg) == PathIterator.SEG_LINETO) {
							float alpha = 1f - ((i - 1) / (float) p.segments);
							graphics.setColor(new Color(1 - alpha, 1 - alpha, 1));
							//graphics.setColor(new Color(0f, 0f, 1f, alpha));
							graphics.drawLine((int) tx, (int) ty, (int) seg[0], (int) seg[1]);
							tx = seg[0];
							ty = seg[1];
						}
						s.next();
						i++;
					}
				}
			}
		}
		

		
		

		if (feld instanceof Matrix) {
			Matrix matrix = (Matrix) feld;
			if (Double.isFinite(matrix.lambda1())) {
				double x = matrix.ev1x(), y = matrix.ev1y();
				double n = Math.sqrt(x * x + y * y);
				x *= 5 / n;
				y *= 5 / n;

				if (Double.isFinite(x) && Double.isFinite(y)) {
					Path2D.Double path = new Path2D.Double();

					path.moveTo(0, 0);
					path.lineTo(x, y);
					graphics.setColor(Color.magenta);
					graphics.draw(path.createTransformedShape(toDrawPlane));
				}
			}

			if (Double.isFinite(matrix.lambda2())) {
				double x = matrix.ev2x(), y = matrix.ev2y();
				double n = Math.sqrt(x * x + y * y);
				x *= 5 / n;
				y *= 5 / n;

				if (Double.isFinite(x) && Double.isFinite(y)) {
					Path2D.Double path = new Path2D.Double();

					path.moveTo(0, 0);
					path.lineTo(x, y);
					graphics.setColor(Color.magenta);
					graphics.draw(path.createTransformedShape(toDrawPlane));
				}
			}
		}
	}

	public void update() {
		double conX = super.getWidth() / config.getDeltaX();
		double conY = super.getHeight() / config.getDeltaY();

		// die affine Transformation um von den mathematischen Koordinaten in
		// die zeichen-Koordinaten zu transformieren.
		toDrawPlane = new AffineTransform();
		toDrawPlane.translate(-config.getMinX() * conX, config.getMaxY() * conY);
		toDrawPlane.scale(conX, -conY);
	}

	public Configuration getConfig() {
		return config;
	}

	@Override
	public void update(Observable o, Object arg) {
		update();
		repaint();
	}

	public void setFeld(VektorFeld feld) {
		this.feld = feld;
		repaint();
	}

	public void displayArrows(boolean b) {
		displayArrows = b;
	}
}
