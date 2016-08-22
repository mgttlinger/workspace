import static java.lang.Math.sin;
import static java.lang.Math.cos;

public class A {
	public final double l, m1, m2, dt;
	private double xv = 0, yv = 0, pv = 0, rv = 0;
	public double x = 0, y = 0, p = 0, r = 0;

	public static final double g = 9.81;

	public A(double l, double m1, double m2, double dt) {
		this.l = l;
		this.m1 = m1;
		this.m2 = m2;
		this.dt = dt;
	}

	public void setState(double xv, double yv, double pv, double rv, double x,
			double y, double p, double r) {
		this.xv = xv;
		this.yv = yv;
		this.pv = pv;
		this.rv = rv;
		this.x = x;
		this.y = y;
		this.p = p;
		this.r = r;
	}

	private static final double q(Double a) {
		return a * a;
	}

	public void next() {

		double ya =0;
		double ra =0;
		double pa = (pv*sin(p)*m2*l*cos(p)/(m1+m2) + g*sin(p))/(q(m2*l*cos(p))/(m1+m2) + q(m2*l));
		double xa = -(m2*l*(pa*cos(p)-q(pv)*sin(p)))/(m1+m2);
		//double xa = -(m2*g*sin(p)*cos(p)+m2*l*q(pv)*sin(p))/(m1+m2+m2*q(cos(p)));
		
		xv += dt * xa;
		yv += dt * ya;
		pv += dt * pa;
		rv += dt * ra;

		x += dt * xv;
		y += dt * yv;
		p += dt * pv;
		r += dt * rv;
	}
}
