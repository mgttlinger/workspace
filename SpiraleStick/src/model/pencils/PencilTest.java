package model.pencils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;

import model.ICircle;

public class PencilTest extends Pencil {
	private final double distance;

	public PencilTest(ICircle parent, Color color, double distance) {
		super(parent, color);
		this.distance = distance;
	}

	@Override
	public void draw(Graphics2D g) {
		super.draw(g);

		int mx=512, my=512;
		
		Point point = model.Transform.transformRound(parent.getTransform(angle), 0, parent.getRadius() * distance);
		Point point2 = model.Transform.transformRound(parent.getTransform(angle), 0, parent.getRadius() * -distance);
		
		Point p;
		if((point.x-mx)*(point.x-mx) + (point.y-my)*(point.y-my) > (point2.x-mx)*(point2.x-mx) + (point2.y-my)*(point2.y-my))
			g.drawLine(point2.x, point2.y, mx, my);
			
		else
			g.drawLine(point.x, point.y, mx, my);
				
		

	}

	@Override
	public boolean isVolatile() {
		return false;
	}
}
