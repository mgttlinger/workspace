package model.pencils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import model.ICircle;

public class PencilLine extends Pencil {
	private final double distance;
	private Point2D.Double lastDot;

	public PencilLine(ICircle parent, Color color, double distance) {
		super(parent, color);
		this.distance = distance;
	}

	@Override
	public void draw(Graphics2D g) {
		super.draw(g);

		Point2D.Double point = model.Transform.transform(
				parent.getTransform(angle), 0, parent.getRadius() * distance);

		if (lastDot != null) {
			g.drawLine((int) Math.round(point.x), (int) Math.round(point.y),
					(int) Math.round(lastDot.x), (int) Math.round(lastDot.y));
		}
		lastDot = point;
	}

	@Override
	public boolean isVolatile() {
		return false;
	}

	@Override
	public void reset() {
		super.reset();
		lastDot = null;
	}
}
