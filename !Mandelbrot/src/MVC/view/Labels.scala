package MVC.view

import MVC.modell.Modell
import javax.swing.JLabel
import util._

class ModellLabel(val f: (Modell => String), val modell: Modell)
  extends JLabel(f(modell))
  with Observer {

  modell.addObserver(this)

  override def update(caller: Observable) = {
    this.setText(f(modell))
  }
}

class FractalLabel(modell: Modell) extends ModellLabel((modell) => modell.fractal.toString(), modell) {}
class ViewportLabel(modell: Modell) extends ModellLabel((modell) => modell.view.toString(), modell) {}
class ColorLabel(modell: Modell) extends ModellLabel((modell) => modell.farbe.toString(), modell) {}
class SimpleLabel(modell: Modell) extends ModellLabel((modell) => modell.simple.toString(), modell) {}
class BeautyLabel(modell: Modell) extends ModellLabel((modell) => modell.beauty.toString(), modell) {}