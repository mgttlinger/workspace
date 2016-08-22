package MVC.controller

import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import java.awt.event.MouseWheelEvent
import java.awt.event.MouseWheelListener
import MVC.modell.Modell
import MVC.view.View
import entities.viewport.Point

class GuiController(val modell: Modell, val view: View) extends KeyListener with MouseListener with MouseWheelListener {
  val master = view.imgPanel
  master.addMouseListener(this)
  master.addMouseWheelListener(this)
  master.addKeyListener(this)

  override def keyReleased(arg0: KeyEvent) {
  }

  override def keyTyped(arg0: KeyEvent) {
  }

  override def mouseClicked(e: MouseEvent) {
  }

  override def mouseEntered(e: MouseEvent) {
  }

  override def mouseExited(e: MouseEvent) {
  }

  override def mousePressed(e: MouseEvent) {
    /*val px = e.getX / master.getWidth.toDouble
    val py = e.getY / master.getHeight.toDouble
    modell.setViewport(modell.view.focus(px, py))*/
    val trans = new entities.viewport.Transform(modell.view, new entities.viewport.Dimensions(master.getWidth(), master.getHeight()))
    val x = trans.transformX(e.getX, e.getY)
    val y = trans.transformY(e.getX, e.getY)
    modell.addPoint(new Point(x, y))
  }

  override def mouseReleased(e: MouseEvent) {
  }

  override def keyPressed(arg0: KeyEvent) {
    arg0.getKeyCode match {
      case 40 =>
        modell.setViewport(modell.view.down())
        return

      case 38 =>
        modell.setViewport(modell.view.up())
        return

      case 39 =>
        modell.setViewport(modell.view.right())
        return

      case 37 =>
        modell.setViewport(modell.view.left())
        return

      //      case 107 =>
      //        gui.algoSimple.setMaxIteration(gui.algoSimple.getMaxIteration - 50)
      //        gui.setup()
      //        return
      //
      //      case 109 =>
      //        gui.algoSimple.setMaxIteration(gui.algoSimple.getMaxIteration + 50)
      //        gui.setup()
      //        return

      case 10 =>
        println(modell.view.toString)
        return

      case 83 =>
        modell.snap()
        return

      case 82 =>
        modell.setViewport(entities.viewport.ViewportUtil.start)
        return
      case _ =>
        return
    }
  }

  override def mouseWheelMoved(e: MouseWheelEvent) {
    val px = e.getX / master.getWidth.toDouble
    val py = e.getY / master.getHeight.toDouble
    modell.setViewport(modell.view.zoom(px, py, e.getWheelRotation))
  }
}
