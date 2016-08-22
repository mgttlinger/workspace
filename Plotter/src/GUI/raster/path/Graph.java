package GUI.raster.path;
//package GUI.raster.path;
//
//import java.awt.Color;
//import java.awt.Shape;
//import java.awt.geom.AffineTransform;
//import java.awt.geom.Path2D;
//import java.util.Observable;
//
//import term.IFunction;
//import term.variable.Variable;
//
//
//
//public class Graph{
//	private final IFunction function;
//	private final Variable variable;
//
//	public Graph(IFunction function, Variable variable) {
//		this.function = function;
//		this.variable = variable;
//	}
//
//	public Shape createTransformedShape(AffineTransform toDrawPlane, modell.Viewport config) {
//
//		double x_start = config.U().x();
//		double x_end = x_start + config.A().x();
//		double x_delta = config.A().x() / 1000d;
//
//		Path2D.Double path = new Path2D.Double();
//		variable.assign(x_start);
//		path.moveTo(x_start, function.evaluate());
//		for (double x = x_start + x_delta; x <= x_end; x += x_delta) {
//			variable.assign(x);
//			double y = function.evaluate();
//			if (java.lang.Double.isNaN(y) || java.lang.Double.isInfinite(y)) {
//				// || y > config.getMaxY() || y < config.getMinY()) {
//				path.moveTo(x, y);
//			} else {
//				path.lineTo(x, y);
//			}
//		}
//		return path.createTransformedShape(toDrawPlane);
//	}
//}
