package entities.imageFactory

import java.io.Serializable
import entities.viewport.Transform
import entities.fractal.Fractal

trait ImageFactory {
  def apply(fractal:Fractal, trans: Transform, x_i: Int, y_i: Int): Double
}

class SimpleFactory extends ImageFactory {

  override def apply(fractal:Fractal, trans: Transform, x_i: Int, y_i: Int): Double = {
    val x = trans.transformX(x_i, y_i)
    val y = trans.transformY(x_i, y_i)
    fractal(x, y)
  }

  override def toString(): String = {
    "Simple"
  }
}
