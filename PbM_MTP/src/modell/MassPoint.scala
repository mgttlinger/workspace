package modell

abstract class MassPoint(x0: Double, y0: Double, xv0:Double, yv0:Double, val m: Double) {
  var x = x0
  var y = y0
  var xv = xv0
  var yv = yv0
  var xp = x
  var yp = y
  
  def reset()
  def step(dt:Double)
  def accelerate(ax:Double, ay:Double)
}