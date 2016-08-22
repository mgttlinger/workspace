package entities.accumulator

@SerialVersionUID(1L)
class Max extends Accumulator {

  private var v: Double = java.lang.Double.NEGATIVE_INFINITY

  override def reset() {
    synchronized {
      v = java.lang.Double.NEGATIVE_INFINITY
    }
  }

  override def next(d: Double) {
    synchronized {
      v = Math.max(v, d)
    }
  }

  override def result(n: Int): Double = v
  override def toString(): String = "Max"
}
