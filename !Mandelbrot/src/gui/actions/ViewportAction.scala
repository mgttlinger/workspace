package gui.actions

import MVC.modell.Modell
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import entities.viewport.Viewport

class ViewportAction(private val gui: Modell, private val view: Viewport) extends ActionListener {

  override def actionPerformed(e: ActionEvent) {
    gui.setViewport(view)
  }
}
