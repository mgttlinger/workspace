package benchmark

import entities.imageFactory._
import entities.fractal._
import entities.viewport.Transform
import entities.preImage._

object FractalBenchmark {
  val instance = new FractalBenchmark(
    new SimpleFactory(),
    new Transform(
      entities.viewport.ViewportUtil.createViewportByLongs(0x3fd6c3283cc3c79dL, 0x3fd60d5e21097162L, 0xbea464307e900000L, 0xbea37ed68c080000L, 0x3e9d6a190a600000L, 0xbe9a4331f1900000L),
      entities.viewport.Dimensions.screenHD))
}

class FractalBenchmark(val faktory: ImageFactory, val trans: Transform) {
  def benchmark(fractal: Fractal): Long = {
    val start = System.currentTimeMillis()
    new PreImage(fractal, faktory, trans)
    val ende = System.currentTimeMillis()
    ende - start
  }

  def benchmark(fraktals: List[Fractal]): List[Long] =
    fraktals.map(benchmark)

  def benchmarkAndDisplay(fraktals: List[Fractal]): Unit = {
    println("Benchmark")
    println("faktroy:    " + faktory)
    println("dimensions: " + trans.dim)
    println("viewport:   " + trans.view)
    println(" ----- ")
    for (f <- fraktals)
      println("%s\t\t%d".format(f, benchmark(f)))
  }
}