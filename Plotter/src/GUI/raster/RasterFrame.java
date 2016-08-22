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

@SuppressWarnings({ "serial" })
public class RasterFrame extends JFrame  {

	public final Raster raster;

	public RasterFrame() {
		super("raster");
		raster = new Raster();
		add(raster);
		pack();
		/*this.addKeyListener(raster.getConfig());
		this.addMouseWheelListener(raster.getConfig());*/
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
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
}
