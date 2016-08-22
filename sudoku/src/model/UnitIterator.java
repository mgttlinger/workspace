package model;

import java.util.Iterator;

public class UnitIterator implements Iterator<Block> {

	private int pos = 0;
	private Unit unit;
	
	UnitIterator(Unit unit){
		this.unit=unit;
	}
	
	@Override
	public boolean hasNext() {
		return unit.getSize() > pos;
	}

	@Override
	public Block next() {
		Block result=unit.get(pos);
		pos++;
		return result;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
