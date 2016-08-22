package meta

import entities.Syntax._
import entities.content.CachedContent
import entities.fractal.{Fractal, Mandelbrot}
import entities.viewport.Dimensions

object BenchmarkStarter extends App {
  val fractals = Seq(
    Mandelbrot.RoughColoring(500),
    Mandelbrot.OrbitPoint(500, 1, 1),
    Mandelbrot.OrbitPoint(500, -1, 1),
    Mandelbrot.OrbitPoint(500, 1, -1),
    Mandelbrot.OrbitPoint(500, -1, -1)
  )

  def operation(fractal: Fractal): CachedContent =
    entities.viewport.Viewport.benchmark
      .withDimensions(Dimensions.screenHD.scale(0.1))
      .withAntiAliasedFractal(fractal)
      .cached


  private def ticToc(fractal: Fractal): Long = {
    val start = System.currentTimeMillis()
    operation(fractal)
    val ende = System.currentTimeMillis()
    ende - start
  }

  println("Benchmark")
  println(" ----- ")
  for (fractal <- fractals;
       time = ticToc(fractal))
    println(s"$fractal\t\t$time")
}