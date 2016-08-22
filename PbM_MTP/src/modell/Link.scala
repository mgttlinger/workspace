package modell

object Link {
  def distance(x1: Double, x2: Double, y1: Double, y2: Double): Double =
    Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2))
}

class Link(val a: MassPoint, val b: MassPoint, val k: Double, val d: Double) {
  def this(a: MassPoint, b: MassPoint) = this(a, b, 100, Link.distance(a.x, b.x, a.y, b.y))
  def this(a: MassPoint, b: MassPoint, k: Double) = this(a, b, k, Link.distance(a.x, b.x, a.y, b.y))

  def tension() = Link.distance(a.x, b.x, a.y, b.y) / d

  def step() = {
    val tx = a.x - b.x
    val ty = a.y - b.y
    val t = Math.pow(tx * tx + ty * ty, 0.5)
    if (t != 0) {
      val ya = k / 2 * ty * (t - d) / t
      val xa = k / 2 * tx * (t - d) / t
      a.accelerate(-xa / a.m, -ya / a.m)
      b.accelerate(+xa / b.m, +ya / b.m)
    } else {
      /* TODO: Heuristik.
       * Falls die beiden Punkte an der gleichen Stelle sind (Singularität), dann ist es nicht möglich die Federkraft zu bestimmen.
       * Deswegen werden die beiden Punkte mit einer willkürlichen Kraft auseinander gedrückt. Das soll die Singularität auflösen und so im nächsten Zeitschritt wieder ordentliche Ergbenisse liefern
       */
      a.accelerate(-0.001, 0)
      b.accelerate(+0.001, 0)
    }
  }
}