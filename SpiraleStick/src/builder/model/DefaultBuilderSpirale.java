package builder.model;

import java.awt.Color;

import model.ICircle;
import builder.Builder;
import builder.model.circles.BuilderAdapter;
import builder.model.circles.BuilderCircleSet;
import builder.model.circles.BuilderFrame;
import builder.model.pencil.BuilderPencil;
import builder.model.pencil.BuilderPencilSet;

public class DefaultBuilderSpirale extends BuilderSpirale {

	private static int width = 1000, height = 1000;
	private static int[] radius = { 250, 150, 50, 30};
	// private static int[] radius = { 5 * 5 * 3 * 3 * 2, 5 * 3 * 3 * 3, 5 * 3 *
	// 3, 3 * 3 * 3 };
	private static BuilderCircleSet circles = new BuilderCircleSet();
	private static BuilderPencilSet pens = new BuilderPencilSet(circles);

	static {
		Builder<ICircle> last;

		last = new BuilderFrame(width / 2, height / 2, radius[0]);
		circles.addCircle(last);
		for (int i = 1; i < radius.length; i++) {
			last = new BuilderAdapter(last, radius[i]);
			circles.addCircle(last);
		}

		pens.addForLast(BuilderPencil.LINE, Color.blue, 1d);
		pens.addForLast(BuilderPencil.MARKER, Color.red, 1d);
		//
		// last = new BuilderFrame(width / 2, height / 2, radius[0]/5);
		// circles.addCircle(last);
		// for (int i = 1; i < radius.length; i++) {
		// last = new BuilderAdapter(last, radius[i]/5);
		// circles.addCircle(last);
		// }
		//
		// pens.addForLast(BuilderPencil.LINE, Color.blue, 1d);
		// pens.addForLast(BuilderPencil.MARKER, Color.red, 1d);
		//
		// last = new BuilderFrame(width / 2, height / 2, radius[0]/10);
		// circles.addCircle(last);
		// for (int i = 1; i < radius.length; i++) {
		// last = new BuilderAdapter(last, radius[i]/10);
		// circles.addCircle(last);
		// }
		//
		// pens.addForLast(BuilderPencil.LINE, Color.blue, 1d);
		// pens.addForLast(BuilderPencil.MARKER, Color.red, 1d);
		//

		pens.addForAll(BuilderPencil.ARC, Color.black);
		// pens.addForAll(BuilderPencil.RADIUS, Color.green);
	}

	public DefaultBuilderSpirale() {
		super(width, height, pens, circles, sub_ComputeNumberRounds(radius));
	}

	private static int sub_ggt(int a, int b) {
		if (b == 0)
			return a;
		return sub_ggt(b, a % b);
	}

	private static int sub_kgv(int a, int b) {
		return a * b / sub_ggt(a, b);
	}

	private static int sub_ComputeNumberRounds(int[] r) {
		int kgv = 1;
		for (int i : r)
			kgv = sub_kgv(kgv, i);
		return kgv / r[0];
	}
}
