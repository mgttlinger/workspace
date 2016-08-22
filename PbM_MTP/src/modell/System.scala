package modell
import java.awt.geom.Area

object System {
  val g = 9.81
  def corners(area: Area):List[Pair[Double, Double]] = {
    var cs = List[Pair[Double, Double]]()
    val pi = area.getPathIterator(null)
    while (!pi.isDone()) {
      val coords = new Array[Double](6)
      pi.currentSegment(coords)
      cs = cs.+:(new Pair(coords(0), coords(1)))
      pi.next()
    }
    return cs
  }
}

class System(val area: Area) {
  var masses = List[MassPoint]()
  var links = List[Link]()
  val corners = System.corners(area)

  def addMass(input: MassPoint) =
    masses = masses.:+(input)

  def removeMass(input: MassPoint) = {
    masses = masses.filter((that) => that != input)
    links = links.filter((that) => that.a != input && that.b != input)
  }

  def addLink(input: Link) =
    links = links.:+(input)

  def removeLink(input: Link) =
    links = links.filter((that) => that != input)

  def step(dt:Double) = {
    for (goo <- masses)
      goo.reset()

    for (link <- links)
      link.step()

    for (goo <- masses)
      goo.step(dt)

    for (one <- masses)
      if (!area.contains(one.x, one.y)) {
        //Closest.mirror(corners, one)
        val better = Closest.closest(area, one.x, one.y)
        one.x = better._1
        one.y = better._2
        /*one.xv = 0
        one.yv = 0*/
      }
  }
}