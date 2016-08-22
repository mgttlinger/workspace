package builder.model.circles;

import model.ICircle;
import model.circles.Frame;
import builder.Builder;

public class BuilderFrame extends Builder<ICircle> {

	private int radius, x, y;

	public BuilderFrame(int x, int y,int radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
		changed();
	}

	public void setX(int x) {
		this.x = x;
		changed();
	}

	public void setY(int y) {
		this.x = y;
		changed();
	}

	@Override
	protected ICircle createInstance() {
		return new Frame(x, y, radius);
	}

}
