package entities
package viewport

case class Transform(view: Viewport, dimensions: Dimensions) extends HasDimensions{
  val mx = view.A.x / width
  val my = view.B.y / height
  val sx = view.B.x / height
  val sy = view.A.y / width
  val tx = view.U.x
  val ty = view.U.y
  val dx = mx + sx
  val dy = my + sy

  // die tatsaechliche Transformation
  def transformX(x: Double, y: Double): Double = mx * x + sx * y + tx
  def transformY(x: Double, y: Double): Double = sy * x + my * y + ty

  // die diskreten Gitterpositionen
  def transformX(x: Int, y: Int): Double = transformX(x.toDouble, y.toDouble)
  def transformY(x: Int, y: Int): Double = transformY(x.toDouble, y.toDouble)
  def transform(x:Int, y:Int):(Double, Double) = (transformX(x, y), transformY(x,y))

  // fuer multi-Sampling. 
  def transformX(x0: Int, y0: Int, xi: Int, yi: Int, multi: Double): Double = transformX(x0 + xi * multi, y0 + yi * multi)
  def transformY(x0: Int, y0: Int, xi: Int, yi: Int, multi: Double): Double = transformY(x0 + xi * multi, y0 + yi * multi)

  // Fuer andere Arten von Anti-Aliase
  def transformX(x0: Int, y0: Int, xo: Double, yo: Double): Double = transformX(x0 + xo, y0 + yo)
  def transformY(x0: Int, y0: Int, xo: Double, yo: Double): Double = transformY(x0 + xo, y0 + yo)

  def invertX(x: Double, y: Double): Double = (my * (x - tx) - sx * (y - ty)) / (mx * my - sx * sy)
  def invertY(x: Double, y: Double): Double = (mx * (y - ty) - sy * (x - tx)) / (mx * my - sx * sy)
  def invert(x:Double, y:Double):(Double, Double) = (invertX(x, y), invertY(x,y))
}
