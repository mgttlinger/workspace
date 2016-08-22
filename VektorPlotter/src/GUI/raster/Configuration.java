package GUI.raster;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Observable;

public class Configuration extends Observable implements KeyListener, MouseListener, MouseWheelListener {
	// Konfiguration
	private double minX = -10, minY = -10, maxX = 10, maxY = 10, rasterX = 1, rasterY = 1;

	// abgeleitete Werte
	private double deltaX, deltaY, drawStep;

	public static final double zoomFactor = 0.1, moveFactor = 0.1;
	public static final int drawPoints = 10000;

	public Configuration() {
		update();
	}

	private void update() {
		deltaX = maxX - minX;
		deltaY = maxY - minY;
		rasterX = deltaX / 20;
		rasterY = deltaX / 20;
		drawStep = deltaX / drawPoints;
		super.setChanged();
		notifyObservers();
	}

	public double getMinX() {
		return minX;
	}

	public double getMinY() {
		return minY;
	}

	public double getMaxX() {
		return maxX;
	}

	public double getMaxY() {
		return maxY;
	}

	public double getRasterX() {
		return rasterX;
	}

	public double getRasterY() {
		return rasterY;
	}

	public double getDeltaX() {
		return deltaX;
	}

	public double getDeltaY() {
		return deltaY;
	}

	public double getDrawStep() {
		return drawStep;
	}

	public void zoomIn() {
		maxX -= deltaX * zoomFactor;
		minX += deltaX * zoomFactor;
		maxY -= deltaY * zoomFactor;
		minY += deltaY * zoomFactor;
		update();
	}

	public void zoomOut() {
		maxX += deltaX * zoomFactor;
		minX -= deltaX * zoomFactor;
		maxY += deltaY * zoomFactor;
		minY -= deltaY * zoomFactor;
		update();
	}

	public void up() {
		maxY += deltaY * moveFactor;
		minY += deltaY * moveFactor;
		update();
	}

	public void down() {
		maxY -= deltaY * moveFactor;
		minY -= deltaY * moveFactor;
		update();
	}

	public void right() {
		maxX += deltaX * moveFactor;
		minX += deltaX * moveFactor;
		update();
	}

	public void left() {
		maxX -= deltaX * moveFactor;
		minX -= deltaX * moveFactor;
		update();
	}

	@Override
	public String toString() {
		return String.format("X: %f %f %f %f, Y: %f %f %f %f", minX, maxX, deltaX, rasterX, minY, maxY, deltaY, rasterY);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		int key = e.getKeyChar();
		switch (key) {
		case '+':
			zoomIn();
			break;
		case '-':
			zoomOut();
			break;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case 38:
			up();
			break;
		case 37:
			left();
			break;
		case 40:
			down();
			break;
		case 39:
			right();
			break;
		}
	}

	@Override
	public boolean hasChanged() {
		return true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if (e.getPreciseWheelRotation() == -1) {
			zoomIn();
		} else if (e.getPreciseWheelRotation() == 1) {
			zoomOut();
		}
	}
}
