package GUI.raster;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import term.IFunction;
import term.variable.Variable;
import GUI.raster.path.Axes;
import GUI.raster.path.Background;
import GUI.raster.path.Graph;
import GUI.raster.path.IPath;

@SuppressWarnings("serial")
public class Raster extends JPanel implements Observer {

	// fest, bis zum Ändern der Konfiguration
	private transient AffineTransform toDrawPlane;

	// Konfiguration
	private final Configuration config;
	private static Color colorBg = new Color(255, 255, 255);
	private static int startWidth = 750, startHeight = 750;

	// momentaner Zustand
	private final ArrayList<Graph> graphs = new ArrayList<Graph>();
	private final IPath axes, background;

	public Raster() {
		setPreferredSize(new Dimension(startWidth, startHeight));
		config = new Configuration();
		config.addObserver(this);
		axes = new Axes(config);
		background = new Background(config);
		update();
	}

	private void draw(Graphics2D g, IPath path) {
		g.setColor(path.getColor());
		g.draw(path.createTransformedShape(toDrawPlane));
	}

	@Override
	public void paint(Graphics g_) {
		Graphics2D graphics = (Graphics2D) g_;
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		graphics.setBackground(colorBg);
		graphics.clearRect(0, 0, getWidth(), getHeight());

		draw(graphics, background);
		draw(graphics, axes);

		for (Graph g : graphs)
			draw(graphics, g);

		// graphics.setColor(Color.black);
		// graphics.drawString(Double.toString(config.getMinX()), 50, 50);
	}

	public void addFunction(IFunction function, Variable variable, Color color) {
		graphs.add(new Graph(function, variable, color, config));
		repaint();
	}

	void update() {
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

	public void clear() {
		for (Graph g : graphs)
			config.deleteObserver(g);
		graphs.clear();
		repaint();
	}

}
