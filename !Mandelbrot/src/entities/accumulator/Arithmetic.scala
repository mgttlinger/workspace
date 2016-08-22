package entities.accumulator

@SerialVersionUID(1L)
class Arithmetic extends Accumulator {

  private var v: Double = _

  override def reset() {
    synchronized {
      v = 0d
    }
  }

  override def next(d: Double) {
    synchronized {
      v += d
    }
  }

  override def result(n: Int): Double = v / n
  override def toString(): String = "Arith"
}
