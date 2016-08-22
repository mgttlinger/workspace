package GUI.raster.path;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.Observable;
import java.util.Observer;

public interface Component extends Observer {
	public Color getColor();

	@Override
	public void update(Observable o, Object arg);

	public Shape createTransformedShape(AffineTransform toDrawPlane);
}
