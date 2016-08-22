 package model;

import java.awt.Graphics2D;

public interface IPencil {
	public void addAngle(double angle);

	public void draw(Graphics2D g);

	public void drawFull(Graphics2D g);

	public double getTransferRatio();

	public boolean isVolatile();

	public void reset();

	public void setAngle(double angle);
}
