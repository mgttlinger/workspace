package model;

import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

public final class Transform {
	public static Point round(Point2D.Double in) {
		return new Point((int) Math.round(in.getX()), (int) Math.round(in.getY()));
	}

	public static Point2D.Double transform(AffineTransform af, double x, double y) {
		double[] data = new double[6];
		af.getMatrix(data);
		double out_x = data[0] * x + data[2] * y + data[4];
		double out_y = data[1] * x + data[3] * y + data[5];
		// [ x']   [ m00 m01 m02 ] [ x ]   [ m00x + m01y + m02 ]
		// [ y'] = [ m10 m11 m12 ] [ y ] = [ m10x + m11y + m12 ]
		// [ 1 ]   [  0   0   1  ] [ 1 ]   [         1         ]
		return new Point2D.Double(out_x, out_y);
	}

	public static Point2D.Double transform(AffineTransform af, Point2D.Double point) {
		return transform(af, point.x, point.y);
	}

	public static Point transformRound(AffineTransform af, double x, double y) {
		return round(transform(af, x, y));
	}
}
