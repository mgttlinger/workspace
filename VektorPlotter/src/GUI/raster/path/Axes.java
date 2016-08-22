package GUI.raster.path;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.Observable;

import GUI.raster.Configuration;

public class Axes implements Component {
	private final Configuration config;
	private final Path2D.Double path = new Path2D.Double();
	public final static Color color = new Color(0, 0, 0);
	public final static double scale = 0.3;

	public Axes(Configuration config) {
		this.config = config;
		config.addObserver(this);
		update(null, null);
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public void update(Observable o, Object arg) {
		path.reset();
		// Y-Achse
		path.moveTo(0, config.getMinY());
		path.lineTo(0, config.getMaxY());
		// Skala Y-Achse
		double srasterx = scale * config.getRasterX();
		for (double i = config.getMaxY() - (config.getMaxY() % config.getRasterY()); i >= config.getMinY(); i -= config.getRasterY()) {
			path.moveTo(srasterx, i);
			path.lineTo(-srasterx, i);
		}

		// X-Achse
		path.moveTo(config.getMinX(), 0);
		path.lineTo(config.getMaxX(), 0);
		// Skala X-Achse
		double srastery = scale * config.getRasterY();
		for (double i = config.getMaxX() - (config.getMaxX() % config.getRasterX()); i >= config.getMinX(); i -= config.getRasterX()) {
			path.moveTo(i, srastery);
			path.lineTo(i, -srastery);
		}
	}

	@Override
	public Shape createTransformedShape(AffineTransform toDrawPlane) {
		return path.createTransformedShape(toDrawPlane);
	}
}
