package model.circles;

import java.awt.geom.AffineTransform;

import model.IFrame;

public class Frame implements IFrame {
	final private int radius;
	final private int x, y;

	// private AffineTransform lastTransform;

	public Frame(int x, int y, int radius) {
		this.radius = radius;
		this.x = x;
		this.y = y;

		// lastTransform = new AffineTransform();
		// lastTransform.translate(x, y);
	}

	@Override
	public int getRadius() {
		return radius;
	}

	@Override
	public double getTransferRatio() {
		return 1;
	}

	@Override
	public AffineTransform getTransform(double angle) {
		AffineTransform transform = new AffineTransform();
		transform.translate(x, y);
		transform.rotate(angle);
		return transform;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return String.format("%s: [x=%d, y=%d, r=%d] ", this.getClass()
				.getName(), x, y, radius);
	}
}
