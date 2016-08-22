package model.circles;

import java.util.ArrayList;
import java.util.Iterator;

import model.ICircle;

public class CircleSet implements Iterable<ICircle> {
	private final ArrayList<ICircle> circles;
	
	public CircleSet(){
		circles=new ArrayList<ICircle>();
	}

	public void add(ICircle input) {
		circles.add(input);
	}
	
	public ArrayList<ICircle> getList() {
		return circles;
	}
	
	public ICircle getLast(){
		return circles.get(circles.size()-1);
	}

	@Override
	public Iterator<ICircle> iterator() {
		return circles.iterator();
	}
}
