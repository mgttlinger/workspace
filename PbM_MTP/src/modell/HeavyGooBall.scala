package modell

class HeavyGooBall(x0: Double, y0: Double, m: Double) extends MassPoint(x0, y0, 0, 0, m) {
  def this(x0: Double, y0: Double) = this(x0, y0, 100d)

  val damping = 0.99
  var xa, ya = 0d

  def reset() = {
    xa = 0
    ya = System.g
  }

  def step(dt: Double) = {
	xp = x
	yp = y
    xv = xv * damping + xa * dt
    yv = yv * damping + ya * dt
    x += xv * dt
    y += yv * dt
  }

  def accelerate(inx: Double, iny: Double) = {
    xa += inx
    ya += iny
  }
}