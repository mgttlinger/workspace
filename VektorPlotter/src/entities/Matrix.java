package entities;

import java.awt.geom.Point2D;

public class Matrix implements VektorFeld {
	public final double a, b, c, d;
	public final double det, trace;

	public Matrix(double a, double b, double c, double d) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		trace = a + d;
		det = a * d - b * c;
	}

	public double transformX(double x, double y) {
		return a * x + b * y;
	}

	public double transformY(double x, double y) {
		return c * x + d * y;
	}

	public double det() {
		return det;
	}

	public double trace() {
		return trace;
	}

	public double lambda1() {
		return 0.5 * (trace + Math.sqrt(trace * trace - 4 * det));
	}

	public double lambda2() {
		return 0.5 * (trace - Math.sqrt(trace * trace - 4 * det));
	}

	public double ev1x() {
		return b;
	}

	public double ev1y() {
		return lambda1() - a;
	}

	public double ev2x() {
		return b;
	}

	public double ev2y() {
		return lambda2() - a;
	}


}
