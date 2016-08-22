package entities.imageFactory

import entities.accumulator._
import entities.viewport.Transform
import entities.fractal.Fractal
import AntiAliaseConstants._

object AntiAliaseConstants {
  val standardAccu = () => new Arithmetic()
  val standardMulti: Int = 5

  val g = (1 + Math.sqrt(5)) / 2
  val gg = Math.pow(2, g)
  val standardLimit: Double = 0.05
}

class AntiAliase(val accus: () => Accumulator, val multi: Int) extends ImageFactory {

  private val multiq = multi * multi
  private val faktor = 1.0 / multi

  def this() {
    this(standardAccu, standardMulti)
  }

  override def apply(fractal:Fractal, trans: Transform, x_i: Int, y_i: Int): Double = {
    val accu = accus()
    for (x_s <- 0 until multi; y_s <- 0 until multi) {
      val x = trans.transformX(x_i, y_i, x_s, y_s, faktor)
      val y = trans.transformY(x_i, y_i, x_s, y_s, faktor)
      accu.next(fractal(x, y))
    }
    accu.result(multiq)
  }

  override def toString(): String = {
    "AA " + accus() + " " + multi
  }
}
