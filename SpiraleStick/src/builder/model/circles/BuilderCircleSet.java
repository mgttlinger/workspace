package builder.model.circles;

import java.util.ArrayList;
import java.util.Iterator;

import model.ICircle;
import model.circles.CircleSet;
import builder.Builder;

public class BuilderCircleSet extends Builder<CircleSet> implements Iterable<Builder<ICircle>> {

	private ArrayList<Builder<ICircle>> circles;
	
	public BuilderCircleSet() {
		circles=new ArrayList<Builder<ICircle>>();
	}
	
	public void addCircle(Builder<ICircle> circle){
		circles.add(circle);
		changed();
	}
	
	@Override
	protected CircleSet createInstance() {
		CircleSet set=new CircleSet();
		for(Builder<ICircle> b:circles)
			set.add(b.getInstance());
		return set;
	}

	@Override
	public Iterator<Builder<ICircle>> iterator() {
		return circles.iterator();
	}

	public Builder<ICircle> getLast() {
		return circles.get(circles.size()-1);
	}

}
