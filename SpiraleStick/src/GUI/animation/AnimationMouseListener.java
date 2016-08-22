package GUI.animation;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.event.MouseInputAdapter;

public class AnimationMouseListener extends MouseInputAdapter implements MouseWheelListener {

	private Point last = null;
	private AnimationPanel panel;

	public AnimationMouseListener(AnimationPanel panel) {
		this.panel = panel;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (last == null)
			return;
//		System.out.printf("dragged %d %d%n", e.getX() - last.x, e.getY() - last.y);
		panel.my_move(e.getX() - last.x, e.getY() - last.y);
		last = e.getPoint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton()!=MouseEvent.BUTTON1)
			return;
//		System.out.println("klicked");
		last = e.getPoint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton()!=MouseEvent.BUTTON1)
			return;
//		System.out.println("released");
		last = null;
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e){
		System.out.printf("wheel %d %n",e.getWheelRotation());
		panel.scale(e.getWheelRotation());
	}
}
