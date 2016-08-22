package meta;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import gui.*;
import modell.*;
import modell.System;

public class Starter {

	static Shape polygon() {
		return new Polygon(new int[] { 0, 5, 10, 15, 10, 5 }, new int[] { 5, 0,
				0, 5, 10, 10 }, 6);
	}

	public static Link[] linkAll(MassPoint[] goos) {
		Link[] links = new Link[goos.length * (goos.length - 1) / 2];
		int n = 0;
		for (int i = 0; i < goos.length; i++)
			for (int j = i + 1; j < goos.length; j++) {
				links[n] = new Link(goos[i], goos[j]);
				n++;
			}
		return links;
	}

	public static void fiber(System S, MassPoint oi, MassPoint ui, MassPoint ov, MassPoint uv, double dx,
			double dy, double k, int n) {
		MassPoint o = oi, u = ui;
		//MassPoint ov = null, uv = null;
		for (int i = 0; i < n; i++) {
			MassPoint on = new GooBall(o.x() + dx, o.y() + dy, 0.1);
			MassPoint un = new GooBall(u.x() + dx, u.y() + dy, 0.1);

			S.addLink(new Link(o, on, k));
			S.addLink(new Link(u, un, k));
			S.addLink(new Link(o, un, k * 2));
			S.addLink(new Link(u, on, k * 2));
			S.addLink(new Link(un, on, k));

			if (ov != null) {
				S.addLink(new Link(on, ov));
				S.addLink(new Link(un, uv));
				/*
				 * S.addLink(new Link(ov, un, k * 2)); S.addLink(new Link(uv,
				 * on, k * 2));
				 */
			}

			S.addMass(on);
			S.addMass(un);

			ov = o;
			uv = u;
			o = on;
			u = un;

		}
	}

	public static MassPoint[] circle(int n, double mx, double my, double r) {
		double alpha = 2 * Math.PI / n;
		MassPoint[] fix = new MassPoint[n];

		for (int i = 0; i < n; i++) {
			double x = mx + Math.sin(i * alpha) * r;
			double y = my + Math.cos(i * alpha) * r;
			//fix[i] = new RotationPoint(mx, my, r, Math.PI * 2 * i / n);
			 //fix[i] = new SwingPoint(x, y, 4, 0);
			 fix[i] = new FixPoint(x, y);
		}
		return fix;
	}

	public static void main(String[] args) {

		// Shape bounds = new Ellipse2D.Double(-10, -10, 30, 30);
		// Shape bounds = new Rectangle(0, 0, 10, 10);
		Shape bounds = polygon();
		final modell.System S = new modell.System(new Area(bounds));

		/*MassPoint[] outer = circle(45, 7.5, 5.0, 1);
		MassPoint[] inner = circle(45, 7.5, 5.0, 1-0.1);

		for (int i = 0; i < outer.length; i++) {
			S.addMass(outer[i]);
			S.addMass(inner[i]);
			MassPoint a = outer[i];
			MassPoint b = outer[(i + 1) % outer.length];

			MassPoint ai = inner[i];
			MassPoint bi = inner[(i + 1) % inner.length];
			
			S.addLink(new Link(a, b, 1));
			S.addLink(new Link(ai, bi, 1));
			double dx = (a.y() - b.y());
			double dy = -(a.x() - b.x());
			fiber(S, a, b, ai, bi, dx, dy, 2000, 6);
		}*/

		MassPoint[] goos = new MassPoint[5];
		goos[0] = new GooBall(3, 3);
		goos[1] = new GooBall(5, 1);
		goos[2] = new GooBall(7.5, 3);
		goos[3] = new GooBall(10, 1);
		goos[4] = new GooBall(12, 3);

		for (int i = 0; i < goos.length; i++)
			S.addMass(goos[i]);

		for (Link l : linkAll(goos))
			S.addLink(l);

		final GUIFrame gui = new gui.GUIFrame(S);

	}
}
