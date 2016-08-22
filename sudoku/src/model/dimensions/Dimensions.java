package model.dimensions;

import java.io.Serializable;

public class Dimensions implements Serializable {
	private static final long serialVersionUID = -3928789082920597098L;

	
	/**
	 * m ist: 
	 * a) die Breite der Superbl�cke
	 * b) die Anzahl der Superbl�cke in der H�he
	 */
	public final int m;
	/**
	 * n ist: 
	 * a) die H�he der Superbl�cke
	 * b) die Anzahl der Superbl�cke in der Breite
	 */
	public final int n;
	public final int mn, mnq, maxmn, numberBlocks;

	// mn = m*n, also die gr��e einer normalen Unit
	// mnq = mn zum quadrat, also die Anzahl Bl�cke in einem kompletten feld,
	// maxmn der gr��ere Wert von m und n

	public Dimensions(int m, int n) {
		assert m>0;
		assert n>0;
		this.m = m;
		this.n = n;
		mn = m * n;
		mnq = mn * mn;
		numberBlocks = mnq;
		maxmn = Math.max(m, n);
	}

	@Override
	public boolean equals(Object input) {
		assert input!=null;
		if (input instanceof Dimensions) {
			Dimensions vgl = (Dimensions) input;
			return vgl.m == m && vgl.n == n;
		}
		return false;
	}

	@Override
	public final int hashCode() {
		// eine Hash-Funktion f�r allm�glichen Speicher Algorithmen.
		// die ersten 8 Bit stehen f�r die Gr��e des Feldes. 4 Bit f�r m, 4 Bit
		// f�r n.

		return m << 28 | n << 24;
	}

	@Override
	public String toString() {
		return String.format("[%d, %d]", m, n);
	}
}
