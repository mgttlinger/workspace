package gui.actions

import java.awt.event.ActionListener
import javax.swing.JMenu
import javax.swing.JMenuItem
import entities.color._
import MVC.modell.Modell

class ColorMenu(farben: List[Color], gui: Modell) extends JMenu("Farben") {

  private def menu_order(s: Array[String], a: Array[ActionListener]) {
    if (s.length > 10) {
      var sub = new JMenu("Sub")
      for (i <- 0 until s.length) {
        val menuItem = new JMenuItem(s(i))
        menuItem.addActionListener(a(i))
        sub.add(menuItem)
        if (i % 10 == 9) {
          add(sub)
          sub = new JMenu("Sub")
        }
      }
      add(sub)
    } else {
      for (i <- 0 until s.length) {
        val menuItem = new JMenuItem(s(i))
        menuItem.addActionListener(a(i))
        add(menuItem)
      }
    }
  }

  val reset = new JMenuItem("Default")
  reset.addActionListener(new ColorAction(gui, DefaultColors.default))
  add(reset)
  val names = Array.ofDim[String](farben.length)
  val actions = Array.ofDim[ActionListener](farben.length)

  for (i <- 0 until farben.length) {
    names(i) = farben(i).toString
    actions(i) = new ColorAction(gui, farben(i))
  }
  menu_order(names, actions)
}
