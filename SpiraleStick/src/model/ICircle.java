package model;

import java.awt.geom.AffineTransform;

public interface ICircle {
	public int getRadius();
	
	public double getTransferRatio();

	public AffineTransform getTransform(double angle);
}
