package meta

import entities._
import fractal._
import viewport._
import preImage._
import imageFactory._
import image._
import color._

import MVC._
import controller._
import modell._
import view._

object Aufgaben {

  def mainGUI(args: Array[String]): Unit = {
    val gui = new Modell()
    //    val v = ViewportUtil.createViewportByLongs(0xbfb8a936ed338e24L, 0xbfe4cadb8e66f9c0L, 0x3f01bdb148ef0000L, 0x0000000000000000L, 0x0000000000000000L, 0x3ef1515f6d920000L)
    //    gui.setViewport(v)
    //gui.setViewport(new Fokus(0xbffff1288031fa08L, 0x3ecb9484a52d86aaL, 0xbffff1287f8ee5b2L, 0x3ecbbb84a91c5b38L))
    //gui.setColor(new ColorTest())
    //    gui.setColor(new GradientN(ConstantColor.black, new ConstantColor(0x0000ff), ConstantColor.white, new ConstantColor(0x0000ff), ConstantColor.black))
    new View(gui)
  }

  def mainBenchmark(args: Array[String]): Unit = {
    val b = new Mandelbrot.SmoothColoring(250)
    val l = List[Fractal](b)
    benchmark.FractalBenchmark.instance.benchmarkAndDisplay(l)
  }

  def mainParallelTest(args: Array[String]): Unit = {
    val v = ViewportUtil.createViewportByLongs(0x3fd69906d177c4f9L, 0x3fd609288b1cea79L, 0x3e8b518d137a00d4L, 0xbe410d81bc8d3cdaL, 0xbe1eca0a3aa41a5aL, 0xbe80f3ed79f82a5aL)
    val d = Dimensions.screenHD
    val img = new NormalizedDoubleRaster(new PreImage(new Mandelbrot.SmoothColoring(500), new SimpleFactory(), new Transform(v, d)))

    val max = new accumulator.Max()
    val min = new accumulator.Min()
    for {
      x <- 0 until d.width
      y <- 0 until d.height
    } {
      max.next(img.apply(x, y))
      min.next(img.apply(x, y))
    }

    new Image(img, DefaultColors.default).save(new java.io.File("test.png"))

    println("norm %f %f".format(min.result(0), max.result(0)))
  }

  def mainWandbild(args: Array[String]): Unit = new Wandbild()

  def main(args: Array[String]): Unit = {
    //mainWandbild(args)
//    mainGUI(args)
        mainTest(args)
  }

  def mainTest(args: Array[String]): Unit = {
    val gui = new Modell()
    new View(gui)
  }
}

