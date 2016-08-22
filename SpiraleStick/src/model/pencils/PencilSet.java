package model.pencils;

import java.awt.Graphics2D;
import java.util.ArrayList;

import model.IPencil;

public class PencilSet {
	private final ArrayList<IPencil> vol, notvol;

	public PencilSet() {
		vol = new ArrayList<IPencil>();
		notvol = new ArrayList<IPencil>();
	}

	public void add(IPencil input) {
		if (input.isVolatile())
			vol.add(input);
		else
			notvol.add(input);
	}

	public void addAngle(double angle) {
		for (IPencil pen : vol)
			pen.addAngle(angle);
		for (IPencil pen : notvol)
			pen.addAngle(angle);
	}

	public void draw(Graphics2D g) {
		for (IPencil pen : vol)
			pen.draw(g);
		for (IPencil pen : notvol)
			pen.draw(g);
	}

	public void draw(Graphics2D g, boolean v) {
		if (v)
			for (IPencil pen : vol)
				pen.draw(g);
		else
			for (IPencil pen : notvol)
				pen.draw(g);
	}

	public ArrayList<IPencil> getNotVolatile() {
		return notvol;
	}

	public ArrayList<IPencil> getVolatile() {
		return vol;
	}

	public void reset() {
		for (IPencil pen : vol)
			pen.reset();
		for (IPencil pen : notvol)
			pen.reset();
	}

	public void setAngle(double angle) {
		for (IPencil pen : vol)
			pen.setAngle(angle);
		for (IPencil pen : notvol)
			pen.setAngle(angle);
	}
}
