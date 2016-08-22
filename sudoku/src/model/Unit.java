package model;

import java.util.Iterator;
import model.dimensions.Dimensions;

public class Unit extends Options implements Iterable<Block> {
	final private Block[] blocks;
	private int size = 0;

	/**
	 * @deprecated Use {@link #Unit(Dimensions)} instead
	 */
	public Unit(Dimensions dim, int ident, String type) {
		this(dim);
	}

	public Unit(Dimensions dim) {
		super(dim);
		assert dim!=null;
		blocks = new Block[dim.mn];
	}

	public void append(Block b) {
		assert b!=null;
		assert size<=blocks.length;
		assert !isInside(b);
		blocks[size] = b;
		size++;
		assert isInside(b);
	}

	public Block get(int n) {
		assert n>=0 && n<size;
		return blocks[n];
	}

	public int getSize() {
		return size;
	}

	public boolean isInside(Block b) {
		assert b!=null;
		for (int i = 0; i < size; i++)
			if (blocks[i] == b)
				return true;
		return false;
	}

	@Override
	public Iterator<Block> iterator() {
		return new UnitIterator(this);
	}

	void updateOptions(int i) {
		assert i>=0 && i<dim.mn;
		makeFalse(i);
		for (Block b : blocks)
			b.connectAnd(this, i);
	}
}
