package model.pencils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import model.ICircle;

public class PencilArc extends Pencil {
	public PencilArc(ICircle parent, Color color) {
		super(parent, color);
	}

	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
		Point2D.Double point = model.Transform.transform(
				parent.getTransform(angle), 0, 0);

		g.drawArc((int) (point.x - parent.getRadius()),
				(int) (point.y - parent.getRadius()), parent.getRadius() * 2,
				parent.getRadius() * 2, 0, 360);

	}

	@Override
	public boolean isVolatile() {
		return true;
	}

}
