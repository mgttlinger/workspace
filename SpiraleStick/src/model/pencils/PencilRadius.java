package model.pencils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import model.ICircle;

public class PencilRadius extends Pencil {
	public PencilRadius(ICircle parent, Color color) {
		super(parent, color);
	}

	@Override
	public void draw(Graphics2D g) {
		super.draw(g);

		Point2D.Double point = model.Transform.transform(
				parent.getTransform(angle), 0, 0);
		Point2D.Double point2 = model.Transform.transform(
				parent.getTransform(angle), 0, parent.getRadius());
		g.drawLine((int) point.x, (int) point.y, (int) point2.x, (int) point2.y);
	}

	@Override
	public boolean isVolatile() {
		return true;
	}

}
