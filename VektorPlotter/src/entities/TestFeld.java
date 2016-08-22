package entities;

public class TestFeld implements VektorFeld {
	public double transformX(double x, double y) {
		return Math.sin(1 / x);
	}

	public double transformY(double x, double y) {
		return Math.sin(1 / y);
	}

}
