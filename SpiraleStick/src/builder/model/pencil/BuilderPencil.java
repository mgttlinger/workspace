package builder.model.pencil;

import java.awt.Color;

import model.ICircle;
import model.IPencil;
import model.pencils.*;
import builder.Builder;

public class BuilderPencil extends Builder<IPencil>{

	public final static int ARC = 0, LINE = 1, MARKER = 2, RADIUS = 3, TEST = 4;

	private int type;
	private Builder<ICircle> parent;
	private Color color;
	private Object[] params;

	public BuilderPencil(int type, Builder<ICircle> parent, Color color, Object... params) {
		this.type = type;
		this.parent = parent;
		this.color = color;
		this.params = params;
	}

	public void setParent(Builder<ICircle> parent) {
		this.parent = parent;
		changed();
	}

	@Override
	protected IPencil createInstance() {
		switch (type) {
		case ARC:
			return new PencilArc(parent.getInstance(), color);
		case LINE:
			return new PencilLine(parent.getInstance(), color, (Double) params[0]);
		case MARKER:
			return new PencilMarker(parent.getInstance(), color, (Double) params[0]);
		case RADIUS:
			return new PencilRadius(parent.getInstance(), color);
		case TEST:
			return new PencilTest(parent.getInstance(), color, (Double) params[0]);
		}

		return null;
	}

}
