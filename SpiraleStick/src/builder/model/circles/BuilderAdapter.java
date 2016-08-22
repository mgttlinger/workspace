package builder.model.circles;

import model.ICircle;
import model.circles.Adapter;
import builder.Builder;

public class BuilderAdapter extends Builder<ICircle> {

	private Builder<ICircle> parent;
	private int radius;

	public BuilderAdapter(Builder<ICircle> parent, int radius) {
		this.parent = parent;
		this.radius = radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
		changed();
	}

	public void setParent(Builder<ICircle> parent) {
		this.parent = parent;
		changed();
	}

	@Override
	protected ICircle createInstance() {
		return new Adapter(parent.getInstance(), radius);
	}

}
