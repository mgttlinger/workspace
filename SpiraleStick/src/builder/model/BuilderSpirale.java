package builder.model;

import builder.Builder;
import model.Spirale;
import model.circles.CircleSet;
import model.pencils.PencilSet;

public class BuilderSpirale extends Builder<Spirale> {

	private Builder<PencilSet> pens;
	private Builder<CircleSet> circles;
	private int width, height, numberRounds;

	public BuilderSpirale(int width, int height,Builder<PencilSet> pens,Builder<CircleSet> circles, int numberRounds) {
		this.width=width;
		this.height=height;
		this.pens=pens;
		this.circles=circles;
		this.numberRounds=numberRounds;
	}

	@Override
	protected Spirale createInstance() {
		return new Spirale(width, height, circles.getInstance(), pens.getInstance(), numberRounds);
	}

	

}
