package solver.logicalMethods;

import java.util.Arrays;
import java.util.Iterator;
import model.dimensions.Dimensions;

public class MethodList implements Iterable<SolvingMethod> {
	final public SolvingMethod[] list;

	public MethodList(Dimensions dim) {
		list = new SolvingMethod[]{new SingleOption(), new SingleInUnit(), new NackedAll(), new HiddenAll(), new PointingPairs()};
	}

	@Override
	public Iterator<SolvingMethod> iterator() {
		return Arrays.asList(list).iterator();
	}
}
