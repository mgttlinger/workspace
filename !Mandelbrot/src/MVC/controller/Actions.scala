package MVC.view

import MVC.modell.Modell
import java.awt.event.ActionListener
import java.awt.event.ActionEvent
import util._

class Action(val action: (Modell => Unit),
  val modell: Modell)
  extends ActionListener{

  override def actionPerformed(arg0: ActionEvent) {
    action(modell)
  }
}

import entities.fractal.Fractal
class SetFractalAction(fractal: Fractal, modell: Modell) extends Action(
  (modell => modell.setFractal(fractal)),
  modell) {}

import entities.viewport.Viewport
class SetViewportAction(view: Viewport, modell: Modell) extends Action(
  (modell => modell.setViewport(view)),
  modell) {}

import entities.color.Color
class SetColorAction(farbe: Color, modell: Modell) extends Action(
  (modell => modell.setColor(farbe)),
  modell) {}

import entities.imageFactory.ImageFactory
class SetSimpleAction(simple: ImageFactory, modell: Modell) extends Action(
  (modell => modell.setPreviewFactory(simple)),
  modell) {}

class SetBeautyAction(beauty: ImageFactory, modell: Modell) extends Action(
  (modell => modell.setImageFactory(beauty)),
  modell) {}