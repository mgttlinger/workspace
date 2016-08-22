package solver.logicalMethods;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.dimensions.Dimensions;

public class MethodList implements Iterable<SolvingMethod> {
	final public List<SolvingMethod> list;

	public MethodList(Dimensions dim) {
		list = new ArrayList<SolvingMethod>();
		list.add(new SingleOption(dim));
		list.add(new SingleInUnit(dim));
		list.add(new NackedAll(dim));
		list.add(new HiddenAll(dim));
		list.add(new PointingPairs(dim));
	}

	@Override
	public Iterator<SolvingMethod> iterator() {
		return list.iterator();
	}
}
