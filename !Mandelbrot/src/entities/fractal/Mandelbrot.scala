package entities.fractal
import Mandelbrot._
import scala.collection.JavaConversions._

object Mandelbrot {
  import Math.{ abs, min }
  val fractals = List(
    new SmoothColoring(150),
    new SmoothColoring(250),
    new SmoothColoring(500),
    new SmoothColoring(750),
    new SmoothColoring(1500),
    new RoughColoring(150),
    new RoughColoring(250),
    new RoughColoring(500),
    new RoughColoring(750),
    new RoughColoring(1500),
    new Contour(500),
    new OrbitPoint(250, 0, 0),
    new OrbitPoint(250, -1, 0),
    new OrbitPoint(1250, 1, 1),
    new OrbitPoint(250, 1, 0),
    new OrbitPoint(250, 0, 1),
    new OrbitRealAxis(250),
    new OrbitImgAxis(250),
    new OrbitTest(250))

  val log2 = Math.log(2.0)
  val log4 = Math.log(4.0)

  class SmoothColoring(val maxIteration: Int) extends Fractal {
    override def apply(x0: Double, y0: Double): Double = {
      var x = x0
      var y = y0
      for (i <- 0 until maxIteration) {
        val xx = x * x
        val yy = y * y
        y = 2 * x * y + y0
        x = xx - yy + x0
        if (xx + yy >= 4)
          return i - Math.log(Math.log(xx + yy) / log4) / log2
      }
      return maxIteration
    }

    override def toString(): String = "Mandelbrot.SmoothColoring " + maxIteration
  }

  class RoughColoring(val maxIteration: Int) extends Fractal {

    override def apply(x0: Double, y0: Double): Double = {
      var x = x0
      var y = y0
      for (i <- 0 until maxIteration) {
        val xx = x * x
        val yy = y * y
        y = 2 * x * y + y0
        x = xx - yy + x0
        if (xx + yy >= 4)
          return i
      }
      return maxIteration
    }

    override def toString(): String = "Mandelbrot.RoughColoring " + maxIteration
  }

  class OrbitPoint(val maxIteration: Int, val trapx: Double, val trapy: Double) extends Fractal {
    override def apply(x0: Double, y0: Double): Double = {
      var distance = 1e20
      var x = x0
      var y = y0

      for (i <- 0 until maxIteration) {
        //        println("i=%d, trapx=%f, trapy=%f, x=%f, y=%f, distance=%f".format(i, trapx, trapy, x, y, distance))

        //Perform Mandelbrot iteration
        val xx = x * x
        val yy = y * y
        y = 2 * x * y + y0
        x = xx - yy + x0

        //Set new distance dist = min( dist, |z-point| )
        val dx = x - trapx
        val dy = y - trapy
        val this_dist = Math.sqrt(dx * dx + dy * dy)
        if (this_dist < distance)
          distance = this_dist
        if (xx + yy >= 4)
          return distance
      }

      return distance;
    }

    override def toString(): String = "Mandelbrot.OrbitPoint " + trapx + " " + trapy + " " + maxIteration
  }

  class OrbitRealAxis(val maxIteration: Int) extends Fractal {

    override def apply(x0: Double, y0: Double): Double = {
      var distance = 1e20
      var x = x0
      var y = y0

      for (i <- 0 until maxIteration) {
        val xx = x * x
        val yy = y * y
        y = 2 * x * y + y0
        x = xx - yy + x0

        if (abs(x) < distance)
          distance = abs(x)
      }

      return distance;
    }

    override def toString(): String = "Mandelbrot.OrbitRealAxis " + maxIteration
  }

  class OrbitImgAxis(val maxIteration: Int) extends Fractal {

    override def apply(x0: Double, y0: Double): Double = {
      var distance = 1e20
      var x = x0
      var y = y0

      for (i <- 0 until maxIteration) {
        val xx = x * x
        val yy = y * y
        y = 2 * x * y + y0
        x = xx - yy + x0

        if (abs(y) < distance)
          distance = abs(y)
      }

      return distance;
    }

    override def toString(): String = "Mandelbrot.OrbitImgAxis " + maxIteration
  }

  class Contour(val maxIteration: Int) extends Fractal {

    override def apply(x0: Double, y0: Double): Double = {
      var distance = 10d
      var x = x0
      var y = y0

      for (i <- 0 until maxIteration) {
        val xx = x * x
        val yy = y * y
        y = 2 * x * y + y0
        x = xx - yy + x0

        if (abs(x) > distance)
          distance = abs(x)
      }

      return distance;
    }

    override def toString(): String = "Mandelbrot.Contour " + maxIteration
  }

  class OrbitTest(val maxIteration: Int) extends Fractal {

    override def apply(x0: Double, y0: Double): Double = {
      var distance = 1e20
      var x = x0
      var y = y0

      for (i <- 0 until maxIteration) {
        val xx = x * x
        val yy = y * y
        y = 2 * x * y + y0
        x = xx - yy + x0

        val this_dist = Math.sqrt(xx + yy) / 10
        if (this_dist < distance)
          distance = this_dist
        if (abs(x) < distance)
          distance = abs(x)
        if (abs(y) < distance)
          distance = abs(y)
      }

      return distance;
    }

    override def toString(): String = "Mandelbrot.OrbitTest " + maxIteration
  }

  class Brot(val maxIteration: Int) extends Fractal {

    override def apply(x0: Double, y0: Double): Double = {
      var x = x0
      var y = y0

      for (i <- 0 until maxIteration) {
        val xx = x * x
        val yy = y * y
        y = 2 * x * y + y0
        x = xx - yy + x0

        if (xx + yy >= 4)
          return 1
      }

      return 0;
    }

    override def toString(): String = "Mandelbrot.Brot " + maxIteration
  }

  class Test(val maxIteration: Int) extends Fractal {

    override def apply(x0: Double, y0: Double): Double = {
      var x = x0
      var y = y0
      for (i <- 0 until maxIteration) {
        val xx = x * x
        val yy = y * y
        y = 2 * x * y + y0
        x = xx - yy + x0
        if (xx + yy >= 4)
          return i
      }
      return (maxIteration)
    }


    override def toString(): String = "Mandelbrot.Test " + maxIteration
  }
}