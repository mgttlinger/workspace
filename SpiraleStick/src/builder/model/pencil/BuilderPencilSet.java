package builder.model.pencil;

import java.awt.Color;
import java.util.ArrayList;

import model.ICircle;
import model.pencils.PencilSet;
import builder.Builder;
import builder.model.circles.BuilderCircleSet;

public class BuilderPencilSet extends Builder<PencilSet> {

	private ArrayList<BuilderPencil> builder = new ArrayList<BuilderPencil>();
	private BuilderCircleSet circles;

	public BuilderPencilSet(BuilderCircleSet circles){
		this.circles=circles;
	}
	
	
	private void add(BuilderPencil p) {
		builder.add(p);
		changed();
	}

	public void addForAll(int type, Color color, Object... params) {
		for (Builder<ICircle> b : circles) {
			add(new BuilderPencil(type, b, color, params));
		}
	}

	public void addForLast(int type, Color color, Object... params){
		add(new BuilderPencil(type, circles.getLast(), color, params));
	}
	
	@Override
	public PencilSet createInstance() {
		PencilSet pens = new PencilSet();
		for (BuilderPencil b : builder)
			pens.add(b.getInstance());
		return pens;
	}

}
