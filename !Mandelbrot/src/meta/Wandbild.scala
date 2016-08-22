package meta

import entities._
import fractal._
import viewport._
import preImage._
import imageFactory._
import image._
import color._
import accumulator._

import MVC._
import controller._
import modell._
import view._

import java.awt.image.BufferedImage

class Wandbild {

  def pre(fraktal: Fractal, faktory: ImageFactory) = {
    new NormalizedDoubleRaster(new PreImage(fraktal, faktory, new Transform(view, dim)))
  }

  val view = ViewportUtil.createViewportByLongs(0x3fd68b79b675ea99L, 0x3fd6049ba09a9cbdL, 0x3f0213f1a2e7c000L, 0xbeb691b33b140000L, 0xbe945fe581d00000L, 0xbef66fd8ae430000L)
  val dim = Dimensions.screenHD
  //val color = DefaultColors.default
  val color = new ColorTest()

  //val arith = pre(new Mandelbrot.SmoothColoring(500), new AntiAliase(() => new Arithmetic(), 1))
  val arith = pre(new Mandelbrot.RoughColoring(500), new AntiAliase(() => new Arithmetic(), 1))
  //val max = pre(new Mandelbrot.SmoothColoring(500), new AntiAliase(() => new Max(), 5))
  //val min = pre(new Mandelbrot.SmoothColoring(500), new AntiAliase(() => new Min(), 5))
  //  val realA =  pre(new Mandelbrot.OrbitRealAxis(250), new AntiAliase(() => new Arithmetic(), 5), "realA")
  //  val vari = pre(new Mandelbrot.SmoothColoring(500), new AntiAliase(() => new Variance(), 5), "variance")
  val orbit = pre(new Mandelbrot.OrbitPoint(750, 1, 1), new AntiAliase(() => new Max(), 1))
  //  val contour = pre(new Mandelbrot.Contour(250), new AntiAliase(() => new Max(), 10), "contour")

  new Image(arith, color)
    .save(new java.io.File("wandbild\\erg_1.png"))
  new Image(orbit, color)
    .save(new java.io.File("wandbild\\erg_2.png"))
  
  new Image(new NormalizedDoubleRaster(arith + orbit), color)
    .save(new java.io.File("wandbild\\erg.png"))
  //    new Image(new NormalizedDoubleRaster(max + orbit), color)
  //      .save(new java.io.File("wandbild\\max+orbit.png"))
  //    new Image(new NormalizedDoubleRaster(min + orbit), color)
  //      .save(new java.io.File("wandbild\\min+orbit.png"))
}
