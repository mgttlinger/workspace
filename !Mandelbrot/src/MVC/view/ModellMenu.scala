package MVC.view

import MVC.modell.Modell
import javax.swing.JMenu
import util._

class ModellMenu(val f: (Modell => String), val modell: Modell)
  extends JMenu(f(modell))
  with Observer {

  modell.addObserver(ModellMenu.this)

  override def update(caller: Observable) = {
    ModellMenu.this.setText(f(modell))
  }
}

class FractalMenu(modell: Modell) extends ModellMenu((modell) => modell.fractal.toString(), modell) {}
class ViewportMenu(modell: Modell) extends ModellMenu((modell) => modell.view.toString(), modell) {}
class ColorMenu(modell: Modell) extends ModellMenu((modell) => modell.farbe.toString(), modell) {}
class SimpleMenu(modell: Modell) extends ModellMenu((modell) => modell.simple.toString(), modell) {}
class BeautyMenu(modell: Modell) extends ModellMenu((modell) => modell.beauty.toString(), modell) {}