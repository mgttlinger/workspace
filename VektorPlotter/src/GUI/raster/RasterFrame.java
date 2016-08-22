package GUI.raster;

import java.awt.Color;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import entities.Matrix;
import entities.VektorFeld;


@SuppressWarnings({ "serial" })
public class RasterFrame extends JFrame implements ComponentListener {

	private final Raster raster;

	public RasterFrame() {
		super("raster");
		raster = new Raster();
		add(raster);
		pack();
		this.addKeyListener(raster.getConfig());
		this.addMouseWheelListener(raster.getConfig());
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
		addComponentListener(this);

	}

	@Override
	public void componentResized(ComponentEvent e) {
		raster.update();
	}

	@Override
	public void componentMoved(ComponentEvent e) {
	}

	@Override
	public void componentShown(ComponentEvent e) {
	}

	@Override
	public void componentHidden(ComponentEvent e) {
	}

	public void output() {
		File file = new File("img\\test.png");
		try {
			BufferedImage img = new BufferedImage(raster.getWidth(), raster.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
			raster.paint(img.getGraphics());
			ImageIO.write(img, "png", file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(file.getAbsolutePath());
	}

	public void setFeld(VektorFeld feld) {
		raster.setFeld(feld);
	}

	public void displayArrows(boolean b) {
		raster.displayArrows(b);
	}
}
