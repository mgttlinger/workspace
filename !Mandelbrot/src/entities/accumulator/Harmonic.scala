package entities.accumulator

@SerialVersionUID(1L)
class Harmonic extends Accumulator {

  private var v: Double = _

  override def reset() {
    synchronized {
      v = 0d
    }
  }

  override def next(d: Double) {
    synchronized {
      v += 1d / d
    }
  }

  override def result(n: Int): Double = n / v
  override def toString(): String = "Harm"
}
