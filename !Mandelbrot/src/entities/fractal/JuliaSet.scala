package entities.fractal

object JuliaSet {

  var phi: Double = (1 + Math.sqrt(5)) / 2
}

class JuliaSet(maxIteration: Int, val cr:Double, val ci:Double) extends Fractal {

  override def apply(_x: Double, _y: Double): Double = {
    var x = _x
    var y = _y
    for (i <- 0 until maxIteration) {
      val xx = x * x
      val yy = y * y
      y = (x + x) * y + ci
      x = xx - yy + cr
      if (xx + yy > 4) return i / maxIteration.toDouble
    }
    1d
  }
  
  override def toString() = "JuliaSet "+cr+" "+ci+" "+maxIteration
}
