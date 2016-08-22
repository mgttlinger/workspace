package model.circles;

import java.awt.geom.AffineTransform;

import model.ICircle;

public class Adapter implements ICircle {

	final protected ICircle parent;
	final protected int radius;
	final protected double transferRatio;

	public Adapter(ICircle parent, int radius) {
		this.parent = parent;
		this.radius = radius;
		transferRatio = parent.getRadius() / (double) radius;
		assert parent.getRadius() > radius;
	}

	@Override
	public int getRadius() {
		return radius;
	}
	
	@Override
	public double getTransferRatio() {
		return -parent.getTransferRatio() * transferRatio;
	}

	@Override
	public AffineTransform getTransform(double angle) {
		AffineTransform g = parent.getTransform(-angle / transferRatio);

		g.translate(0, parent.getRadius() - radius);
		g.rotate(angle);

		return g;
	}

	@Override
	public String toString() {
		return String.format("%s: [parent=%x, r=%d] ", this.getClass()
				.getName(), parent.hashCode(), radius);
	}
}
