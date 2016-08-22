package GUI.animation;



@SuppressWarnings("serial")
public class AnimationFrame extends javax.swing.JFrame {

	private final AnimationThread thread;
	private final AnimationPanel panel;

	public AnimationFrame(AnimationPanel panel) {
		super("hallo echo");
		this.panel=panel;
		add(panel);

		thread = new AnimationThread(panel, 1000/30);
		thread.start();

		this.setBounds(0, 0, 500, 500);
		
//		pack();
		setVisible(true);
		addWindowListener(new AnimationWindowEventListener());
	}
}
