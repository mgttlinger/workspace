package GUI.raster.path;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.Observable;

import term.IFunction;
import term.variable.Variable;
import GUI.raster.Configuration;

public class Graph implements IPath {

	private final IFunction function;
	private final Variable variable;
	private final Color color;
	private final Configuration config;
	private final Path2D.Double path = new Path2D.Double();

	public Graph(IFunction function, Variable variable, Color color, Configuration config) {
		this.function = function;
		this.color = color;
		this.variable = variable;
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
		long start = System.currentTimeMillis();
		path.reset();
		variable.assign(config.getMinX());
		path.moveTo(config.getMinX(), function.evaluate());
		for (double x = config.getMinX() + config.getDrawStep(); x <= config.getMaxX(); x += config.getDrawStep()) {
			variable.assign(x);
			double y = function.evaluate();
			if (java.lang.Double.isNaN(y) || java.lang.Double.isInfinite(y)) {
				// || y > config.getMaxY() || y < config.getMinY()) {
				path.moveTo(x, y);
			} else {
				path.lineTo(x, y);
			}
		}
		System.out.printf("berechnung hat %o ms benötigt.%n", System.currentTimeMillis() - start);
	}

	@Override
	public Shape createTransformedShape(AffineTransform toDrawPlane) {
		return path.createTransformedShape(toDrawPlane);
	}
}
