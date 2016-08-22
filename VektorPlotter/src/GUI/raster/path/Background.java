package GUI.raster.path;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.Observable;

import GUI.raster.Configuration;

public class Background implements Component {

	private final Configuration config;
	private final Path2D.Double path = new Path2D.Double();
	public final static Color color = new Color(217, 217, 217);

	public Background(Configuration config) {
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
		// das Raster berechnen
		for (double i = config.getMaxY() - (config.getMaxY() % config.getRasterY()); i >= config.getMinY(); i -= config.getRasterY()) {
			path.moveTo(config.getMinX(), i);
			path.lineTo(config.getMaxX(), i);
		}
		for (double i = config.getMaxX() - (config.getMaxX() % config.getRasterX()); i >= config.getMinX(); i -= config.getRasterX()) {
			path.moveTo(i, config.getMinY());
			path.lineTo(i, config.getMaxY());
		}
	}

	@Override
	public Shape createTransformedShape(AffineTransform toDrawPlane) {
		return path.createTransformedShape(toDrawPlane);
	}
}
