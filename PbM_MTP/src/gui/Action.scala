package gui

import modell.System
import modell._
import java.awt.event.MouseListener
import java.awt.event.MouseEvent

abstract class Action(val S: System, val G: GUI) extends MouseListener {

  def dist(x1: Double, x2: Double, y1: Double, y2: Double) = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2))

  def mouseClicked(e: MouseEvent) = {}
  def mousePressed(e: MouseEvent)
  def mouseReleased(e: MouseEvent) = {}
  def mouseEntered(e: MouseEvent) = {}
  def mouseExited(e: MouseEvent) = {}
}

class SelectAction(S: System, G: GUI) extends Action(S, G) {

  var selected = List[MassPoint]()

  def unselect(input: MassPoint) =
    selected = selected.filter((that) => that != input)

  override def mousePressed(e: MouseEvent) = {
    for (goo <- S.masses)
      if (dist(e.getX(), G.transformX(goo.x), e.getY(), G.transformY(goo.y)) < 25) {
        if (!selected.contains(goo))
          selected = selected.:+(goo)
        else
          selected = selected.filter((that) => that != goo)
      }
    G.repaint()
  }
}

class DeleteAction(S: System, G: GUI) extends Action(S, G) {
  override def mousePressed(e: MouseEvent) = {
    for (goo <- S.masses)
      if (dist(e.getX(), G.transformX(goo.x), e.getY(), G.transformY(goo.y)) < 25) {
        S.removeMass(goo)
        G.selectAction.unselect(goo)
      }
    G.repaint()
  }
}

class AddGooAction(S: System, G: GUI) extends Action(S, G) {
  override def mousePressed(e: MouseEvent) = {
    val goo = new GooBall(G.detransformX(e.getX()), G.detransformY(e.getY()))
    S.addMass(goo)
    G.repaint()
  }
}

class AddHGooAction(S: System, G: GUI) extends Action(S, G) {
  override def mousePressed(e: MouseEvent) = {
    val goo = new HeavyGooBall(G.detransformX(e.getX()), G.detransformY(e.getY()))
    S.addMass(goo)
    G.repaint()
  }
}

class AddFixAction(S: System, G: GUI) extends Action(S, G) {
  import modell.FixPoint
  override def mousePressed(e: MouseEvent) = {
    val goo = new FixPoint(G.detransformX(e.getX()), G.detransformY(e.getY()))
    S.addMass(goo)
    G.repaint()
  }
}