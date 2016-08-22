package entities.accumulator

@SerialVersionUID(1L)
class Variance extends Accumulator {

  private val x_i = new Arithmetic()

  private val x_i_2 = new Arithmetic()

  override def reset() {
    x_i.reset()
    x_i_2.reset()
  }

  override def next(d: Double) {
    x_i.next(d)
    x_i_2.next(d * d)
  }

  override def result(n: Int): Double = {
    val erw = x_i.result(n)
    Math.sqrt(x_i_2.result(n) - erw * erw)
  }
  override def toString(): String = "Variance"
}
