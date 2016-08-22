package entities.accumulator

@SerialVersionUID(1L)
class Min extends Accumulator {

  private var v: Double = java.lang.Double.POSITIVE_INFINITY

  override def reset() {
    synchronized {
      v = java.lang.Double.POSITIVE_INFINITY
    }
  }

  override def next(d: Double) {
    synchronized {
      v = Math.min(v, d)
    }
  }

  override def result(n: Int): Double = v
  override def toString(): String = "Min"
}
