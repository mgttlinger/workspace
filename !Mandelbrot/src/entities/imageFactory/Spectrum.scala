package entities.imageFactory

import entities.viewport.Transform
import entities.fractal.Fractal

class Spectrum extends ImageFactory {

  override def apply(fractal: Fractal, trans: Transform, x_i: Int, y_i: Int): Double = x_i.toDouble
  override def toString(): String = "Spektrum"
}
