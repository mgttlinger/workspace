package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import entities.Matrix;
import GUI.controller.Controller;
import GUI.raster.Raster;
import GUI.raster.RasterFrame;

@SuppressWarnings("unused")
public class Print {

	static Matrix[] todo = new Matrix[] {
		
		 // A
		new Matrix(0,0,1,0)
		 /*new Matrix(-1, -5, 5, -1),
		new Matrix(+1, -5, 5, +1),
		new Matrix(+1, -5, 5, +1),
		new Matrix(10,  0,  0,  1),
		new Matrix(1,  0,  0,  10),
		new Matrix(-10,  0,  0,  -1),
		
		new Matrix(2, 6,  1,  3),
		new Matrix(5, 0,  0,  0),
		new Matrix(2, 1,  4,  -2),
		new Matrix(0, 0,  1,  0),
		new Matrix(0, 0,  0,  0)*/
		
		/*new Matrix(-1, -5,  5,  -4),
		new Matrix(1, -1,  1,  1),
		new Matrix(-1, -1,  2,  4),
		new Matrix(1, 1,  0,  1),
		new Matrix(-1, 1,  0,  -1),
		new Matrix(-1, 1,  0,  1)*/
	};

	public static void main(String[] args) throws IOException {

		Raster r = new Raster();
		r.setSize(1000, 1000);
		r.update();

		for (Matrix matrix : todo) {
			r.setFeld(matrix);

			BufferedImage img = new BufferedImage(1000, 1000, BufferedImage.TYPE_3BYTE_BGR);

			Graphics g = img.getGraphics();
			r.paint(g);

			g.setColor(Color.green);
			g.drawString("a = " + matrix.a, 5, 10);
			g.drawString("b = " + matrix.b, 5, 20);
			g.drawString("c = " + matrix.c, 5, 30);
			g.drawString("d = " + matrix.d, 5, 40);
			g.drawString("D = " + matrix.det(), 5, 60);
			g.drawString("T = " + matrix.trace(), 5, 70);
			g.drawString("L1 = " + matrix.lambda1(), 5, 80);
			g.drawString("L2 = " + matrix.lambda2(), 5, 90);

			File file = new File(String.format("img\\%.0f, %.0f, %.0f, %.0f.png", matrix.a, matrix.b, matrix.c, matrix.d));
			System.out.println(file.getAbsolutePath());
			if (file.getParentFile() != null)
				file.getParentFile().mkdirs();
			javax.imageio.ImageIO.write(img, "png", file);
		}
	}
}
