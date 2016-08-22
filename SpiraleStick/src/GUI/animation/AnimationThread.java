package GUI.animation;

public class AnimationThread extends Thread {
	private final AnimationPanel panel;
	private final int sleepTime;

	AnimationThread(AnimationPanel panel, int sleepTime) {
		this.panel = panel;
		this.sleepTime = sleepTime;
	}

	@Override
	public void run() {
		while (true) {
			panel.next();
		
			try {
				sleep(sleepTime);
			} catch (Exception e) {

			}
		}
	}
	
}
