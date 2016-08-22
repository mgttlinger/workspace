package entities.accumulator

@SerialVersionUID(1L)
class Geometric extends Accumulator {

  private var v: Double = 1d

  override def reset() {
    synchronized {
      v = 1d
    }
  }

  override def next(d: Double) {
    synchronized {
      v *= d
    }
  }

  override def result(n: Int): Double = Math.pow(v, 1 / n.toDouble)
  override def toString(): String = "Geo"
}
