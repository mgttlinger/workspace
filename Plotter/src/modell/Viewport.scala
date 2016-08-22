package modell

import java.io.Serializable

object ViewportConstants {
  val factorMove: Double = 0.10
  val factorZoom: Double = 0.20
  val invFactorZoom: Double = 1.0 / (1.0 - factorZoom)
}

// Ein stark vereinfachtes Viewport als das aus dem Mandelbrot-Projekt. Die Vektoren A und B bestehen immer nur aus Teilen der X und Y-Achse.

@SerialVersionUID(1L)
class Viewport private (val U: Point, val A: Point, val B: Point) extends Serializable {

  def this(U: Point, dx: Double, dy: Double) = this(new Point(U.x, U.y+dy), new Point(dx, 0), new Point(0, -dy))

  private def translate(t: Point): Viewport = new Viewport(U + t, A, B)

  import ViewportConstants._

  def right(): Viewport = translate(A.*(factorMove))
  def left(): Viewport = translate(A.*(-factorMove))
  def down(): Viewport = translate(B.*(factorMove))
  def up(): Viewport = translate(B.*(-factorMove))

  def focus(zielx: Double, ziely: Double): Viewport =
    translate(A * (zielx - 0.5) + (B * (ziely - 0.5)))

  def zoomOut(zx: Double, zy: Double): Viewport = {
    val new_a = A * invFactorZoom
    val new_b = B * invFactorZoom
    val new_basis = U + (new_a * (-factorZoom * zx)) + (new_b * (-factorZoom * zy))
    new Viewport(new_basis, new_a, new_b)
  }

  def zoomOut(): Viewport = zoomOut(0.5, 0.5)

  def zoomIn(zx: Double, zy: Double): Viewport = {
    val new_a = A * (1 - factorZoom)
    val new_b = B * (1 - factorZoom)
    val new_basis = U + (A * (factorZoom * zx)) + (B * (factorZoom * zy))
    new Viewport(new_basis, new_a, new_b)
  }

  def zoomIn(): Viewport = zoomIn(0.5, 0.5)

  def zoom(zx: Double, zy: Double, steps: Int): Viewport = {
    var view = this
    if (steps > 0)
      for (i <- 0 until steps) view = view.zoomOut(zx, zy)
    else
      for (i <- 0 until -steps) view = view.zoomIn(zx, zy)
    view
  }

  override def toString(): String =
    "%s, %s, %s".format(U, A, B)
    
    
    // neu, nur fuer dieses Projekt.
    def x_start():Double = U.x
    def x_end ():Double = U.x + A.x
    def y_start():Double = U.y+B.y
    def y_end():Double = U.y
}