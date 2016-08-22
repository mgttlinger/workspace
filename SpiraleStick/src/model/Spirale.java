package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import GUI.animation.AnimationFrame;
import GUI.animation.AnimationPanel;

import model.circles.*;
import model.pencils.*;

public class Spirale {
	final private int width, height;
	final private PencilSet pens;
	final private CircleSet circles;
	final private String name;
	final private BufferedImage img;
	final private int numberRounds;

	public Spirale(int width, int height, CircleSet circles, PencilSet pens, int numberRounds) {
		this.width = width;
		this.height = height;
		this.pens = pens;
		this.circles = circles;

		name = "fehlt_noch";
		this.numberRounds = numberRounds;

		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) img.getGraphics();
		g.setBackground(Color.white);
		g.clearRect(0, 0, width, height);

//		 numberRounds = sub_ComputeNumberRounds(radius);
	}

//	public Spirale(int width, int height, int... radius) {
//		// radius=new int[]{ 500, 300, 200, 60};
//
//		this.width = width;
//		this.height = height;
//		assert radius.length >= 2;
//
//		StringBuilder sb = new StringBuilder();
//		for (int i = 0; i < radius.length - 1; i++) {
//			sb.append(radius[i]);
//			sb.append(",");
//		}
//		sb.append(radius[radius.length - 1]);
//		name = sb.toString();
//
//		circles = new CircleSet();
//		ICircle last = new Frame(width / 2, height / 2, radius[0]);
//		circles.add(last);
//		for (int i = 1; i < radius.length; i++) {
//			last = new Adapter(last, radius[i]);
//			circles.add(last);
//		}
//
//		pens = new PencilSet();
//		pens.add(new PencilLine(last, Color.blue, 1));
//		pens.add(new PencilLine(last, Color.red, -1));
//		for (ICircle c : circles)
//			pens.add(new PencilArc(c, Color.black));
//
//		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//		Graphics2D g = (Graphics2D) img.getGraphics();
//		g.setBackground(Color.white);
//		g.clearRect(0, 0, width, height);
//
//	}

	private void createImage() {
		Graphics2D g = (Graphics2D) img.getGraphics();
		for (int i = 0; i < Math.PI * 2 * numberRounds * 100; i++) {
			// pens.addAngle(1);
			pens.addAngle(0.01);
			pens.draw(g, false);
		}
	}

	public void Animation() {
		// pens.reset();
		new AnimationFrame(new AnimationPanel(width, height, pens, img));
	}

	public void FileOutput() {
		createImage();
		File file = new File("img\\" + toString() + ".png");
		try {
			ImageIO.write(img, "png", file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(file.getAbsolutePath());
	}

	@Override
	public String toString() {
		return name;
	}
}
