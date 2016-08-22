package entities.accumulator

trait BinaryAccumulator[A, B] extends Accumulator[(A, B)]{
  def left:Accumulator[A]
  def right:Accumulator[B]

  override def neutral: (A, B) = (left.neutral, right.neutral)
  override def fold(input: (A, B), next: Double): (A, B) =
    (left.fold(input._1, next), right.fold(input._2, next))
}

case class Norm(left: Accumulator[Double], right: Accumulator[Double]) extends BinaryAccumulator[Double, Double] {
  override def lastOperation(result: (Double, Double), count:Int): Double = {
    val l = left.lastOperation(result._1, count)
    val r = right.lastOperation(result._2, count)
    Math.sqrt(l*l + r*r)
  }
}

case class Add(left: Accumulator[Double], right: Accumulator[Double]) extends BinaryAccumulator[Double, Double] {
  override def lastOperation(result: (Double, Double), count:Int): Double = {
    val l = left.lastOperation(result._1, count)
    val r = right.lastOperation(result._2, count)
    l+r
  }
}

case class Sub(left: Accumulator[Double], right: Accumulator[Double]) extends BinaryAccumulator[Double, Double] {
  override def lastOperation(result: (Double, Double), count:Int): Double = {
    val l = left.lastOperation(result._1, count)
    val r = right.lastOperation(result._2, count)
    l-r
  }
}
