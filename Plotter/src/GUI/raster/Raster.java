package GUI.raster;
//package GUI.raster;
//
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.RenderingHints;
//import java.awt.event.KeyAdapter;
//import java.awt.event.KeyEvent;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseWheelEvent;
//import java.awt.geom.AffineTransform;
//import java.awt.geom.NoninvertibleTransformException;
//import java.util.ArrayList;
//
//import javax.swing.JPanel;
//
//import term.IFunction;
//import term.variable.Variable;
//import GUI.raster.path.Axes;
//import GUI.raster.path.Background;
//import GUI.raster.path.Graph;
//
//@SuppressWarnings("serial")
//public class Raster extends JPanel {
//	// Konfiguration
//	private modell.Viewport view = new modell.Viewport(new modell.Point(-10, -10), 20., 20.);
//	private static Color colorBg = new Color(255, 255, 255);
//	private static int startWidth = 750, startHeight = 750;
//	private final ArrayList<Graph> graphs = new ArrayList<Graph>();
//
//	public Raster() {
//		setPreferredSize(new Dimension(startWidth, startHeight));
//		addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyPressed(KeyEvent arg0) {
//				switch (arg0.getKeyCode()) {
//				case 38:
//					view = view.up();
//					break;
//				case 39:
//					view = view.right();
//					break;
//				case 40:
//					view = view.down();
//					break;
//				case 37:
//					view = view.left();
//					break;
//				case 107:
//					view = view.zoomIn();
//					break;
//				case 109:
//					view = view.zoomOut();
//					break;
//				default:
//					return;
//				}
//				repaint();
//			}
//		});
//
//		addMouseListener(new MouseAdapter() {
//			public void mousePressed(MouseEvent e) {
//				double x = e.getX() / (double) getWidth();
//				double y = e.getY() / (double) getHeight();
//				view = view.focus(x, y);
//				repaint();
//			}
//		});
//
//		addMouseWheelListener(new MouseAdapter() {
//			public void mouseWheelMoved(MouseWheelEvent e) {
//				double x = e.getX() / (double) getWidth();
//				double y = e.getY() / (double) getHeight();
//				view = view.zoom(x, y, e.getWheelRotation());
//				repaint();
//			}
//		});
//
//		setFocusable(true);
//	}
//
//	@Override
//	public void paint(Graphics g_) {
//		Graphics2D graphics = (Graphics2D) g_;
//		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
//		graphics.setBackground(colorBg);
//		graphics.clearRect(0, 0, getWidth(), getHeight());
//
//		modell.Transform trans = new modell.Transform(view, new modell.Dimensions(getWidth(), getHeight()));
//
//		AffineTransform Atrans = trans.asTransform();
//		try {
//			Atrans.invert();
//		} catch (NoninvertibleTransformException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		// graphics.setColor(Color.lightGray);
//		// graphics.draw(new Background().createTransformedShape(Atrans, view));
//		graphics.setColor(Color.black);
//		graphics.draw(new Axes().createTransformedShape(Atrans, view));
//
//		graphics.setColor(Color.red);
//		for (Graph g : graphs) {
//			graphics.draw(g.createTransformedShape(Atrans, view));
//		}
//	}
//
//	public void addFunction(IFunction function, Variable variable, Color color) {
//		graphs.add(new Graph(function, variable));
//		repaint();
//	}
//
//	public void clear() {
//		graphs.clear();
//		repaint();
//	}
//}
