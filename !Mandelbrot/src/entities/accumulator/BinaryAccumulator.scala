package entities.accumulator


@SerialVersionUID(1L)
abstract class BinaryAccumulator(protected val a: Accumulator, protected val b: Accumulator) extends Accumulator {

  override def reset() {
    a.reset()
    b.reset()
  }

  override def next(d: Double) {
    a.next(d)
    b.next(d)
  }

  override def result(n: Int): Double
  override def toString(): String = {
    String.format("%s(%s, %s)", getClass.getSimpleName, a, b)
  }
}

@SerialVersionUID(1L)
class Norm(a: Accumulator, b: Accumulator) extends BinaryAccumulator(a, b) {
  override def result(n: Int): Double = Math.sqrt(b.result(n) * a.result(n))
}

@SerialVersionUID(1L)
class Add(a: Accumulator, b: Accumulator) extends BinaryAccumulator(a, b) {
  override def result(n: Int): Double = b.result(n) + a.result(n)
}

@SerialVersionUID(1L)
class Sub(a: Accumulator, b: Accumulator) extends BinaryAccumulator(a, b) {
  override def result(n: Int): Double = b.result(n) - a.result(n)
}
