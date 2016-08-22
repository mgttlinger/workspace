package meta

import entities.Syntax._
import entities.accumulator.Max
import entities.color.HSV
import entities.content.Content
import entities.fractal.Mandelbrot
import entities.viewport.{Dimensions, Viewport}

object WandbildStarter extends App {
  val view = Viewport.wandbild
  val color = HSV.MonoColor.Blue

  val transform = view
    .withDimensions(Dimensions.screenHD)

  val rough = transform
    .withAntiAliasedFractal(Mandelbrot.RoughColoring(500))

  val orbit = transform
    .withAntiAliasedFractal(Mandelbrot.OrbitPoint(750, 1,1), Max)

  val added = new Content{
    override def dimensions: Dimensions = rough.dimensions
    override def apply(x: Int, y: Int): Double = rough(x,y) + orbit(x,y)
  }

  rough.strongNormalized.withColor(color).save("wandbild\\rough.png")
  orbit.strongNormalized.withColor(color).save("wandbild\\orbit.png")
  added.strongNormalized.withColor(color).save("wandbild\\added.png")
}
