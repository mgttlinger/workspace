package model.pencils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import model.ICircle;
import model.IPencil;

abstract class Pencil implements IPencil {
	protected final Color color;
	protected final ICircle parent;
	protected double angle;
	private final double transferRatio;

	protected Pencil(ICircle parent, Color color) {
		this.parent = parent;
		this.color = color;
		transferRatio = parent.getTransferRatio();
	}

	@Override
	public void addAngle(double angle) {
		this.angle += angle * transferRatio;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(color);
	}

	@Override
	public void drawFull(Graphics2D g) {
		AffineTransform start = parent.getTransform(0);
		angle = Math.PI / 2600;
		while (!start.equals(parent.getTransform(angle))) {
			angle += Math.PI / 2600;
			draw(g);
		}
	}

	@Override
	public double getTransferRatio() {
		return parent.getTransferRatio();
	}

	@Override
	public void reset() {
		angle = 0;
	}

	@Override
	public void setAngle(double angle) {
		this.angle = angle * transferRatio;
	}

	@Override
	public String toString() {
		return String.format("%s: [parent=%x] ", this.getClass().getName(),
				parent.hashCode());
	}
}
