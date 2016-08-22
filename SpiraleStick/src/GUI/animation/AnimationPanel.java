package GUI.animation;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import model.pencils.PencilSet;

@SuppressWarnings({ "serial" })
public class AnimationPanel extends JPanel {

	private final PencilSet pens;
//	private final int width, height;
	private final BufferedImage img, buffer;
	
	private double offsetx=0, offsety=0, scale=1;

	// private int tick = 0;

	public AnimationPanel(int width, int height, PencilSet pens, BufferedImage img) {
		this.pens = pens;
//		this.width = width;
//		this.height = height;

		this.img = img;
		buffer = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());

		//setPreferredSize(new Dimension(width, height));
		this.setSize(width, height);
		setVisible(true);
		
		AnimationMouseListener mouse=new AnimationMouseListener(this);
		addMouseMotionListener(mouse);
		addMouseListener(mouse);
		addMouseWheelListener(mouse);
	}

	public void next() {
//		pens.addAngle(0.5);
		pens.addAngle(0.01);
		pens.draw((Graphics2D) img.getGraphics(), false);
		// tick++;
		super.repaint();
	}
	
	@Override
	public void paint(Graphics g_) {
		Graphics2D g = (Graphics2D) g_;
		
		
		Graphics2D g_buffer = (Graphics2D) buffer.getGraphics();
		g_buffer.drawImage(img, 0, 0, null);
		pens.draw(g_buffer, true);
		g_buffer.dispose();

		g.clearRect(0, 0, getWidth(), getHeight());
		
		g.scale(scale, scale);
		g.translate(offsetx, offsety);
		
		g.drawImage(buffer, 0, 0, null);
	}

	public void my_move(int x, int y){
		offsetx+=x*scale;
		offsety+=y*scale;
	}
	
	public void scale(int s){
		scale+=s;
	}
}
