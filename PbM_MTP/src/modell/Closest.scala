package modell

import java.awt.geom.Area
import java.util.ArrayList
import java.awt.geom.PathIterator;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

object Closest {
  def closest(area: Area, x: Double, y: Double): Pair[Double, Double] = {
    //http://stackoverflow.com/questions/8103451/point-outside-of-area-which-is-closest-to-point-inside
    val areaPoints = new ArrayList[Array[Double]]();
    val coords = new Array[Double](6);
    val areaSegments = new ArrayList[Line2D.Double]();

    val pi = area.getPathIterator(null)
    while (!pi.isDone()) {
      val pathIteratorCoords = Array[Double](pi.currentSegment(coords), coords(0), coords(1))
      areaPoints.add(pathIteratorCoords)
      pi.next()
    }

    var start = Array[Double](3)
    for (i <- 0 until areaPoints.size()) {
      val currentElement = areaPoints.get(i);
      var nextElement = Array[Double](-1, -1, -1);
      if (i < areaPoints.size() - 1) {
        nextElement = areaPoints.get(i + 1);
      }
      if (currentElement(0) == PathIterator.SEG_MOVETO) {
        start = currentElement; // Record where the polygon started to close it later
      }

      if (nextElement(0) == PathIterator.SEG_LINETO) {
        areaSegments.add(
          new Line2D.Double(
            currentElement(1), currentElement(2),
            nextElement(1), nextElement(2)));
      } else if (nextElement(0) == PathIterator.SEG_CLOSE) {
        areaSegments.add(
          new Line2D.Double(
            currentElement(1), currentElement(2),
            start(1), start(2)));
      }

    }

    val closestPoint = new Point2D.Double(-1, -1);
    val bestPoint = new Point2D.Double(-1, -1);
    val closestPointList = new ArrayList[Point2D.Double]();
    // Calculate the nearest point on the edge
    for (i <- 0 until areaSegments.size()) {
      val line = areaSegments.get(i)
      val u: Double =
        ((x - line.x1) * (line.x2 - line.x1) + (y - line.y1) * (line.y2 - line.y1)) / ((line.x2 - line.x1) * (line.x2 - line.x1) + (line.y2 - line.y1) * (line.y2 - line.y1));

      val xu = line.x1 + u * (line.x2 - line.x1);
      val yu = line.y1 + u * (line.y2 - line.y1);

      if (u < 0) {
        closestPoint.setLocation(line.getP1());
      } else if (u > 1) {
        closestPoint.setLocation(line.getP2());
      } else {
        closestPoint.setLocation(xu, yu);
      }

      if (closestPoint.distance(x, y) < bestPoint.distance(x, y)) {
        bestPoint.setLocation(closestPoint);
      }
    }
    if (closestPoint.x == -1 && closestPoint.y == -1)
      return null
    else
      return Pair(bestPoint.x, bestPoint.y)
  }

  def distance(x1: Double, x2: Double, y1: Double, y2: Double): Double =
    Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2))

  def lambda(a: Pair[Double, Double], b: Pair[Double, Double], m: MassPoint): Double = {
    //http://www.wolframalpha.com/input/?i=solve+for+x%2C+l+%3A+x*d1%2Bl*v1%3Dp1-a1%3B+x*d2%2Bl*v2%3Dp2-a2
    val dx = b._1 - a._1
    val dy = b._2 - a._2
    //    if (dy * m.xv != dx * m.yv)
    return -((m.y - a._2) * dx - (m.x - a._1) * dy) / (dy * m.xv - dx * m.yv)
    //    else
    //      return -1

    /*if (dy * m.xv != dx * m.yv && m.xv != 0) {
      return (-a._1 * dy + a._2 * dx - dx * m.y + dy * m.x) / (dy * m.xv - dx * m.yv)
    } else
      return -1*/
    /*else if (m.xv == 0 && dx != 0) {
      return (a._1 * dy - a._2 * dx + dx * m.y - dy * m.x) / (dx * m.yv)
    } else if (m.yv == 0 && dy == 0 && a._2 == m.y) {
      return -(a._1 + dx - m.x) / m.xv
    } else if (dy == 0 && dx == 0 && m.xv != 0) {
      return (m.y - a._2) / m.xv
    } else {
      return -(a._2 - m.y) / m.yv
    }*/

  }

  def mirror(corners: List[Pair[Double, Double]], m: MassPoint): Unit = {
    for (i <- 0 until corners.length) {
      val c1 = corners(i)
      val c2 = corners((i + 1) % corners.length)
      val l = lambda(c1, c2, m)
      if (l >= 0 && l <= 1) {

        //        println("Wand: " + i)
        println("l: " + l)
        val gx = m.x - l * m.xv
        val gy = m.y - l * m.yv
        //println("c1: " + c1)
        //println("c2: " + c2)
        //println("l: " + l)
        println("g: (" + gx + ", " + gy + ")")
        m.x = gx
        m.y = gy

        val dx = c2._1 - c1._1
        val dy = c2._2 - c1._2

        val alpha = -(dx * m.xv + dy * m.yv) /
          (distance(dx, 0, dx, 0) * distance(m.xv, 0, m.yv, 0))

        val xv2 = Math.cos(2 * alpha) * m.xv + Math.sin(2 * alpha) * m.yv
        val yv2 = Math.sin(2 * alpha) * m.xv - Math.cos(2 * alpha) * m.yv

        m.xv = 0.6*xv2
        m.yv = 0.6*yv2

        //m.xv *= -1
        //m.yv *= -1
        return
      }

    }
  }
}