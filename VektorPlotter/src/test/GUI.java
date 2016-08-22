package test;

import GUI.controller.Controller;
import GUI.raster.RasterFrame;

public class GUI {
	public static void main(String[] args) {
		final RasterFrame r = new RasterFrame();
		final Controller c = new Controller(r);
	}
}
