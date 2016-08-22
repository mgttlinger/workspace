package model.pencils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import model.ICircle;

public class PencilMarker extends Pencil {
	private final double distance;

	public PencilMarker(ICircle parent, Color color, double distance) {
		super(parent, color);
		this.distance = distance;
	}

	public static int size=15;
	
	@Override
	public void draw(Graphics2D g) {
		super.draw(g);

		Point point = model.Transform.transformRound(parent.getTransform(angle), 0, parent.getRadius() * distance);
//		g.fillArc(point.x-size, point.y-size, 2*size, 2*size, 0, 360);
		g.drawLine(point.x-size, point.y-size, point.x+size, point.y+size);
		g.drawLine(point.x+size, point.y-size, point.x-size, point.y+size);
	}

	@Override
	public boolean isVolatile() {
		return true;
	}
}
