package model.dimensions;

import java.io.Serializable;

public abstract class Dimensioned implements Serializable {
	private static final long serialVersionUID = -168419579867669009L;
	final protected Dimensions dim;

	protected Dimensioned(Dimensions dim) {
		assert dim!=null;
		this.dim = dim;
	}

	final public Dimensions getDimensions() {
		return dim;
	}
}
