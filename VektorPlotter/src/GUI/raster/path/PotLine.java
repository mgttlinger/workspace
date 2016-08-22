package GUI.raster.path;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.Observable;

import entities.VektorFeld;
import GUI.raster.Configuration;

public class PotLine implements Component {

	private final VektorFeld feld;
	private final double x0, y0;
	private final Path2D.Double path = new Path2D.Double();

	public static final double dt = 0.001;
	public static final long steps = 1000;

	public final long segments;
	
	public PotLine(VektorFeld feld, double x0, double y0) {
		//throw new RuntimeException();
		this.feld = feld;
		this.x0 = x0;
		this.y0 = y0;
		// update(null, null);

		path.moveTo(x0, y0);
		double x = x0;
		double y = y0;
		long i = 0;
		for(; i<steps;i++){
			double tx = feld.transformX(x, y);
			double ty = feld.transformY(x, y);
			
			x+= dt*tx;
			y+= dt*ty;
			
			//System.out.printf("%f, %f\n", x, y);
			if(Double.isInfinite(x) || Double.isInfinite(y) ||Double.isNaN(x) ||Double.isNaN(y)){
//				System.out.println("Integration vorzeitig abgebrauchen, wert ausserhalb des Spektrum.");
				break;
			}
			
			path.lineTo(x, y);
			
			if((x-x0)*(x-x0) + (y-y0)*(y-y0) > 5)
				break;
		}
		segments = i;

	}

	@Override
	public Color getColor() {
		return Color.blue;
	}
	
	@Override
	public Shape createTransformedShape(AffineTransform toDrawPlane) {
		return path.createTransformedShape(toDrawPlane);
	}

	@Override
	public void update(Observable o, Object arg) {
	}
}
