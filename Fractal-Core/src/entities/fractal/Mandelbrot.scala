package entities
package fractal

object Mandelbrot {

  final class Iterator(val x0: Double, val y0: Double, val maxIteration: Int) { self =>
    type X = Double
    type Y = Double
    var x:X = 0
    var y:Y = 0
    private var xx = x * x
    private var yy = y * y
    private var iteration = 0

    @inline def hasNext: Boolean = (xx + yy < 4) && iteration < maxIteration

    @inline def next(): Boolean = {
      xx = x * x
      yy = y * y
      y = 2 * x * y + y0
      x = xx - yy + x0
      iteration += 1
      hasNext
    }

    @inline def size(): Int = {
      var i = 0
      while (next()) i = i + 1
      i
    }

    @inline def foreach(@inline f: (X, Y) => Unit): Unit =
      while (next()) f(x, y)

    @inline def foreachX(@inline f: X => Unit): Unit =
      while (next()) f(x)

    @inline def foreachY(@inline f: Y => Unit): Unit =
      while (next()) f(y)

    @inline def fold(start: Double)(@inline f: (Double, X, Y) => Double): Double = {
      var v = start
      while (next()) v = f(v, x, y)
      v
    }

    @inline def foldX(start: Double)(@inline f: (Double, X) => Double): Double = {
      var v = start
      while (next()) v = f(v, x)
      v
    }

    @inline def foldY(start: Double)(@inline f: (Double, Y) => Double): Double = {
      var v = start
      while (next()) v = f(v, y)
      v
    }

    def wrapped: scala.Iterator[(Double, Double)] =
      new scala.Iterator[(Double, Double)] {
        override def hasNext: Boolean = self.hasNext

        override def next(): (X, Y) = {
          self.next()
          (x, y)
        }
      }
  }

  val fractals = List(
    RoughColoring(150),
    RoughColoring(250),
    RoughColoring(500),
    RoughColoring(750),
    RoughColoring(1500),
    Contour(500),
    OrbitPoint(250, 0, 0),
    OrbitPoint(250, -1, 0),
    OrbitPoint(1250, 1, 1),
    OrbitPoint(250, 1, 0),
    OrbitPoint(250, 0, 1),
    OrbitRealAxis(250),
    OrbitImgAxis(250),
    Brot(1000))

  case class RoughColoring(maxIteration: Int) extends Fractal {
    override def apply(x0: Double, y0: Double): Double =
      new Iterator(x0, y0, maxIteration).size()
  }

  case class OrbitPoint(maxIteration: Int, trapx: Double, trapy: Double) extends Fractal {
    override def apply(x0: Double, y0: Double): Double =
      new Iterator(x0, y0, maxIteration).fold(1e20) {
        (d, x, y) =>
          val (dx, dy) = (x - trapx, y - trapy)
          math.min(d, math.sqrt(dx * dx + dy * dy))
      }
  }

  case class OrbitImgAxis(maxIteration: Int) extends Fractal {
    override def apply(x0: Double, y0: Double): Double =
      new Iterator(x0, y0, maxIteration).foldY(1e20) {
        (d, y) => math.min(d, y.abs)
      }
  }

  case class OrbitRealAxis(maxIteration: Int) extends Fractal {
    override def apply(x0: Double, y0: Double): Double =
      new Iterator(x0, y0, maxIteration).foldX(1e20) {
        (d, x) => math.min(d, x.abs)
      }
  }

  case class Contour(maxIteration: Int) extends Fractal {
    override def apply(x0: Double, y0: Double): Double =
      new Iterator(x0, y0, maxIteration).foldX(10d) {
        (d, x) => math.max(d, x.abs)
      }
  }

  case class Brot(maxIteration: Int) extends Fractal {
    override def apply(x0: Double, y0: Double): Double =
      if (new Iterator(x0, y0, maxIteration).size() == maxIteration) 0
      else 1
  }

}