package MVC.modell

import java.awt.image.BufferedImage
import entities.viewport._
import entities.fractal.Fractal
import entities.imageFactory._
import entities.color._
import entities.image.Image
import entities.preImage._

import util.Observable

@SerialVersionUID(1L)
class Modell(
  var fractal: Fractal,
  var simple: ImageFactory,
  var beauty: ImageFactory,
  var farbe: Color,
  var view: Viewport) extends Observable {

  var quali: Double = 0.5
  var img: BufferedImage = _
  var points = List[Point]()

  preview()

  def this() =
    this(
      new entities.fractal.Mandelbrot.SmoothColoring(250),
      new SimpleFactory(),
      new AntiAliase(),
      DefaultColors.default,
      ViewportUtil.start)

  def preview() {
    val dim = Dimensions.screenHD.scale(quali)
    val p = new PreImage(fractal, simple, new Transform(view, dim))
    img = new Image(new NormalizedDoubleRaster(p), farbe).buff
    notifyObservers()
  }

  def snap() {
    val dim = Dimensions.screenHD
    val p = new PreImage(fractal, beauty, new Transform(view, dim))
    img = new Image(new NormalizedDoubleRaster(p), farbe).buff
    notifyObservers()
  }

  def save() {
    val dim = Dimensions.screenHD
    val p = new PreImage(fractal, beauty, new Transform(view, dim))
    val img = new Image(new NormalizedDoubleRaster(p), farbe)
      .save(new java.io.File("snapshots\\%s\\%s.png".format(fractal, view)))
  }

  def setViewport(v: Viewport) {
    require(v != null)
    view = v
    preview()
  }

  def setColor(f: Color) {
    require(f != null)
    farbe = f
    preview()
  }

  def setImageFactory(b: ImageFactory) = {
    require(b != null)
    beauty = b
    notifyObservers()
  }

  def setPreviewFactory(simple: ImageFactory) = {
    require(simple != null)
    this.simple = simple
    notifyObservers()
  }

  def setFractal(f: Fractal) = {
    require(f != null)
    fractal = f
    preview()
  }

  def setQuali(q: Double) = {
    require(q >= 0 && q <= 1)
    quali = q
    preview()
  }
  
  def addPoint(p: Point) = {
    points = p :: points
    notifyObservers()
  }
  
  def clearPoints() = {
    points = List()
    notifyObservers()    
  }
}

