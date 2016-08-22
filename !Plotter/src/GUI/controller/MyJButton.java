package GUI.controller;

import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class MyJButton extends JButton {
	private static final long serialVersionUID = 8869832634493686874L;

	private void ini() {
		registerKeyboardAction(getActionForKeyStroke(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)), KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
				JComponent.WHEN_FOCUSED);
		registerKeyboardAction(getActionForKeyStroke(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)), KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
				JComponent.WHEN_FOCUSED);
	}

	public MyJButton(String text) {
		super(text);
		ini();
	}
}
