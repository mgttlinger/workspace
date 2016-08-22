package entities.preImage

import entities.imageFactory.ImageFactory
import entities.viewport.Transform
import entities.viewport.Dimensions
import entities.accumulator._
import entities.fractal.Fractal

class DoubleRaster(val dim: Dimensions) {
  val values = Array.ofDim[Double](dim.width, dim.height)

  def apply(x: Int, y: Int): Double = values(x)(y)

  def apply(accu: Accumulator): Accumulator = {
    for (w <- 0 until dim.width; h <- 0 until dim.height)
      accu.next(values(w)(h))
    accu
  }

  def apply(_accu: () => Accumulator): Accumulator = {
    apply(_accu())
  }

  def +(other: DoubleRaster): DoubleRaster = {
    val r = new DoubleRaster(dim)
    for (x <- 0 until dim.width; y <- 0 until dim.height)
      r.values(x)(y) = this.values(x)(y) + other.values(x)(y)
    r
  }

  def apply(f: (Double => Double)): DoubleRaster = {
    val r = new DoubleRaster(dim)
    for (x <- 0 until dim.width; y <- 0 until dim.height)
      r.values(x)(y) = f(values(x)(y))
    r
  }
}

class PreImage(fractal: Fractal, fak: ImageFactory, trans: Transform) extends DoubleRaster(trans.dim) {

  private var pixel = 0
  private val pixelSum = trans.dim.width * trans.dim.height

  class PreImageThread extends Thread {
    this.setPriority(Thread.MIN_PRIORITY)
    override def run(): Unit = {
      while (true) {

        val p = values.synchronized {
          val p = pixel
          pixel += 1
          p
        }

        if (p >= pixelSum)
          return

        val x = p % trans.dim.width
        val y = p / trans.dim.width
        values(x)(y) = fak(fractal, trans, x, y)
      }
    }
  }

  val threads = for (i <- 0 until Runtime.getRuntime().availableProcessors())
    yield new PreImageThread()
  for (t <- threads)
    t.start()
  for (t <- threads)
    t.join()
}

class NormalizedDoubleRaster(raster: DoubleRaster) extends DoubleRaster(raster.dim) {
  val max = raster.apply(new Max()).result(0)
  val min = raster.apply(new Min()).result(0)
  if (max != min) {
    val dy: Double = 1.0 / (max - min) // Steigung
    val y0: Double = -min / (max - min) // Start-Wert
    for (x <- 0 until dim.width; y <- 0 until dim.height)
      this.values(x)(y) = y0 + raster.values(x)(y) * dy
  }
}

class Normalized3DoubleRaster(raster: DoubleRaster) extends DoubleRaster(raster.dim) {
  val map = scala.collection.mutable.HashMap[Pair[Int, Int], Double]()

  println("setup")
  for (x <- 0 until dim.width; y <- 0 until dim.height)
    map += ((x, y) -> raster.values(x)(y))

  println("sort")
  val sorted = map.toList.sortBy(_._2)

  println("write")
  /*var i:Int = 0
  for(((x, y), v) <- sorted){
    println(x, y, v, i.toDouble/(dim.width * dim.height))
    this.values(x)(y) = i.toDouble/(dim.width * dim.height)
    i = i+1
  }*/

  var i = 0
  while (i < sorted.size) {

    def dummy(j: Int): Int = {
      if (j >= sorted.size)
        sorted.size - 1
      else if (sorted(i)._2 == sorted(j)._2)
        dummy(j + 1)
      else
        j
    }

    val j = dummy(i + 1)

    //println(i, j, sorted.size)

    val v = (i + j).toDouble / (2 * dim.width * dim.height)
    for (k <- i until j) {
      val x = sorted(k)._1._1
      val y = sorted(k)._1._2
      this.values(x)(y) = v
    }
    i = j + 1
  }
  println("ende")
}

class Normalized2DoubleRaster(raster: DoubleRaster) extends DoubleRaster(raster.dim) {
  import scala.collection.mutable.{ HashMap, HashSet }

  val map = HashMap[Double, HashSet[Pair[Int, Int]]]()
  for (x <- 0 until dim.width; y <- 0 until dim.height) {
    if (map.contains(raster.values(x)(y)))
      map(raster.values(x)(y)) += new Pair(x, y)
    else
      map += raster.values(x)(y) -> HashSet((x, y))
  }

  val sorted = map.toList.sortBy(_._1)

  var finished = 0
  for ((value, set) <- sorted) {
    finished += set.size
    val v = finished.toDouble / (dim.width * dim.height)
    for ((x, y) <- set)
      this.values(x)(y) = v
  }
}