package modell

class RotationPoint(val mx: Double, val my: Double, var r: Double, var t: Double) extends MassPoint(mx + Math.sin(t) * r, my + Math.cos(t) * r, 0, 0, 1) {
  def reset() = {}
  def step(dt: Double) = {
    t = t + dt*10
    x = mx + Math.sin(t) * r
    y = my + Math.cos(t) * r
  }
  def accelerate(inx: Double, iny: Double) = {}
}