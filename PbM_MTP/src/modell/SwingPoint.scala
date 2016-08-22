package modell

class SwingPoint(x0: Double, y0: Double, val dx: Double, val dy: Double) extends MassPoint(x0, y0, 0, 0, 1) {
  var t = 0.0
  def reset() = {}
  def step(dt: Double) = {
    t = t + dt
    x = x0 + Math.sin(t) * dx
    y = y0 + Math.sin(t) * dy
  }
  def accelerate(inx: Double, iny: Double) = {}
}