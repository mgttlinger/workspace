package entities.accumulator

import java.io.Serializable

trait Accumulator extends Serializable {
  def reset(): Unit
  def next(d: Double): Unit
  def result(n: Int): Double
}
