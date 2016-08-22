package modell

class FixPoint(x0: Double, y0: Double) extends MassPoint(x0, y0, 0, 0, 1) {
  def reset() = {}
  def step(dt: Double) = {}
  def accelerate(inx: Double, iny: Double) = {}
}