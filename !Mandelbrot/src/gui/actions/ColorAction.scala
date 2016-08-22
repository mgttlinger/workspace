package gui.actions

import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import entities.color.Color
import MVC.modell.Modell

class ColorAction(private val gui: Modell, private val farbe: Color) extends ActionListener {

  override def actionPerformed(e: ActionEvent) {
    gui.setColor(farbe)
  }
}
