package modell

import java.io.Serializable

object PointUtil {

  val doubleNull = java.lang.Double.doubleToLongBits(0d)

  def doubleToString(d: Double): String = {
    java.lang.Long.toHexString(java.lang.Double.doubleToLongBits(d))
  }

  def longToDouble(l: Long): Double = java.lang.Double.longBitsToDouble(l)

  def linearIndependant(a: Point, b: Point): Boolean = {
    val lambda1 = a.x / b.x
    val lambda2 = a.y / b.y
    lambda1 != lambda2
  }

  def createPointByLongs(x: Long, y: Long) =
    new Point(longToDouble(x), longToDouble(y))
}

@SerialVersionUID(1L)
class Point(val x: Double, val y: Double) extends Serializable {
  require(!java.lang.Double.isNaN(x))
  require(!java.lang.Double.isNaN(y))
  require(!java.lang.Double.isInfinite(x))
  require(!java.lang.Double.isInfinite(y))

  override def toString(): String = {
    String.format("0x%sL, 0x%sL", PointUtil.doubleToString(x), PointUtil.doubleToString(y))
  }
  
  /*override def toString(): String = {
    String.format("(%g, %g)", new java.lang.Double(x), new java.lang.Double(y))
  }*/

  def +(px: Double, py: Double): Point = new Point(x + px, y + py)
  def -(px: Double, py: Double): Point = new Point(x - px, y - py)

  def +(t: Point): Point = this + (t.x, t.y)
  def -(t: Point): Point = this - (t.x, t.y)

  def *(f: Double): Point = new Point(x * f, y * f)
  def *(p: Point): Double = x * p.x + y * p.y

  def orth(): Point = new Point(y, -x)
  def norm(): Double = Math.sqrt(x * x + y * y)
}
