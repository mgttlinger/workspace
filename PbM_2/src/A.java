import static java.lang.Math.sin;
import static java.lang.Math.cos;

public class A {
	public double v1 = 0, v2 = 0, p1, p2;
	public final double l1, l2, m1, m2, dt;

	public static final double g = 9.81;

	public A(double l1, double l2, double m1, double m2, double dt, double p1_0, double p2_0) {
		this.l1 = l1;
		this.l2 = l2;
		this.m1 = m1;
		this.m2 = m2;
		this.dt = dt;
		this.p1 = p1_0;
		this.p2 = p2_0;
	}

	private static final double q(Double a) {
		return a * a;
	}

	public void next() {
		double a2 = (l1 * q(v1) * sin(p1 - p2) - g * sin(p2))
				/ (l2 - m2 * l2 * q(cos(p1 - p2)) / (m1 + m2));

		double a1 = (-m2 * (l1 * q(v1) * sin(p1 - p2) - g * sin(p2))
				* cos(p1 - p2) - m2 * l2 * q(v2) * sin(p1 - p2) - (m1 + m2) * g
				* sin(p1))
				/ ((m1 + m2) * l1 - m2 * l2 * q(cos(p1 - p2)));

		v1 += dt * a1;
		v2 += dt * a2;
		p1 += dt * v1;
		p2 += dt * v2;
	}
}
