package GUI.raster.path;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

public class Axes{

	// @Override
	public Shape createTransformedShape(AffineTransform toDrawPlane, modell.Viewport config) {
		Path2D.Double path = new Path2D.Double();

		double x_start = config.x_start();
		double x_end = config.x_end();
		double x_delta = (x_end - x_start)/10d;
		
		double y_start = config.y_start();
		double y_end = config.y_end();
		double y_delta = (y_end - y_start)/10d;
		
		// Y-Achse
		path.moveTo(0, y_start);
		path.lineTo(0, y_end);
		// Skala Y-Achse
		double srasterx = 0.3 * x_delta;
		for (double y = y_start; y < y_end; y += y_delta) {
			path.moveTo(srasterx, y);
			path.lineTo(-srasterx, y);
		}

		// X-Achse
		path.moveTo(x_start, 0);
		path.lineTo(x_end, 0);
		// Skala X-Achse
		double srastery = 0.3 * y_delta;
		for (double x = x_start; x < x_end; x += x_delta) {
			path.moveTo(x, srastery);
			path.lineTo(x, -srastery);
		}

		return path.createTransformedShape(toDrawPlane);
	}
}
