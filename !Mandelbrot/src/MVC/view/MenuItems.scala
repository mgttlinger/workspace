package MVC.view

import MVC.modell.Modell
import MVC.controller._
import java.awt.event.ActionEvent
import javax.swing.JMenuItem
import util._

class MenuItem( val title: String,
  val action: Action)
  extends JMenuItem(title){

  this.addActionListener(action)
}
