package solver;

import java.util.ArrayList;

import model.dimensions.Dimensions;
import model.field.ModelField;

public abstract class Solver {
	protected final Dimensions dim;
	public boolean mute = false;
	private final ArrayList<String> log;

	public Solver(Dimensions dim) {
		this.dim = dim;
		log = new ArrayList<String>();
	}

	protected void log(String s) {
		log.add(s);
	}

	public void printLog() {
		for (String s : log)
			System.out.println(s);
	}

	abstract public void run(ModelField f);
}