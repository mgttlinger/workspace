package GUI.raster.path;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.Observable;

public class Background {

	public Shape createTransformedShape(AffineTransform toDrawPlane, modell.Viewport config) {
		Path2D.Double path = new Path2D.Double();

		double x_start = config.U().x();
		double x_end = x_start + config.A().x();
		double x_delta = config.A().x() / 10d;
		
		double y_start = config.U().y();
		double y_end = y_start + config.B().y();
		double y_delta = config.B().y() / 10d;

		// das Raster berechnen
		for (double y = y_start; y < y_end; y += y_delta) {
			path.moveTo(x_start, y);
			path.lineTo(x_end, y);
		}
		for (double x = x_start; x < x_end; x += x_delta) {
			path.moveTo(x, y_start);
			path.lineTo(x, y_end);
		}

		return path.createTransformedShape(toDrawPlane);
	}
}
