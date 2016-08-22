package entities.accumulator

trait Accumulator[A] {
  def neutral: A
  def fold(left:A, right:Double) : A
  def lastOperation(result:A, count:Int) : Double

  def apply(collection:TraversableOnce[Double]): Double =
      lastOperation(collection.foldLeft(neutral)(fold), collection.size)
}