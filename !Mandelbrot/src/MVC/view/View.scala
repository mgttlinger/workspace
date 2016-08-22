package MVC.view

import gui.actions.ColorMenu
import java.awt.Frame
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JFrame
import javax.swing.JMenu
import javax.swing.JMenuBar
import javax.swing.JMenuItem
import entities.fractal._
import entities.color.DefaultColors
import MVC.modell.Modell
import entities.accumulator.Arithmetic
import entities.accumulator.Geometric
import entities.accumulator.Harmonic
import entities.accumulator.Max
import entities.accumulator.Min
import entities.accumulator.Variance
import entities.imageFactory.AntiAliase
import entities.imageFactory.SimpleFactory

import MVC._
import modell._
import view._
import controller._

object Collection {

  import entities.imageFactory._
  import entities.accumulator._
  val factories = Array(
    new SimpleFactory(),
    new AntiAliase(() => new Arithmetic(), 5),
    new AntiAliase(() => new Geometric(), 5),
    new AntiAliase(() => new Harmonic(), 5),
    new AntiAliase(() => new Min(), 5),
    new AntiAliase(() => new Max(), 5),
    new AntiAliase(() => new Variance(), 5),
    new AntiAliase(() => new Variance(), 2))

  import entities.fractal._
  val fractals = Mandelbrot.fractals ++ List(
     new Mandelbrot.Brot(3000),
     new Mandelbrot.Test(3000),
    new Collatz(150),

    new BurningShip(100),
    new BurningShip(500),
    new BurningShip(1000),

    new Tricorn(100),
    new Tricorn(500),
    new Tricorn(1000),

    new JuliaSet(100, -0.6, -0.6),
    new JuliaSet(500, -0.6, -0.6),
    new JuliaSet(1000, -0.6, -0.6),

    new JuliaSet(100, -0.4, 0.6),
    new JuliaSet(500, -0.4, 0.6),
    new JuliaSet(1000, -0.4, 0.6),

    new JuliaSet(100, -0.8, 0.156),
    new JuliaSet(500, -0.8, 0.156),
    new JuliaSet(1000, -0.8, 0.156),
    
    new Mandelbrot3(500),
    
    entities.fractal.Spektrum)

}

@SerialVersionUID(1L)
class View(val modell: Modell) extends JFrame {
  val imgPanel = new ImagePanel(modell)
  add(imgPanel)

  val controller = new GuiController(modell, this)

  val myMenuBar = new JMenuBar()

  { // Men� f�r die Auswahl des "simple" Algorithmus (Preview-Shot)
    val menu = new SimpleMenu(modell)
    for (factory <- Collection.factories) {
      menu.add(
        new MenuItem(
          factory.toString(),
          new SetSimpleAction(factory, modell)))
    }
    myMenuBar.add(menu)
  }

  { // Menü für die Auswahl des "Beauty" Algorithmus (Snap-Shot)
    val menu = new BeautyMenu(modell)
    for (factory <- Collection.factories) {
      menu.add(
        new MenuItem(
          factory.toString(),
          new SetBeautyAction(factory, modell)))
    }
    myMenuBar.add(menu)
  }

  { // Menü für die Auswahl des Fraktals
    val menu = new FractalMenu(modell)
    for (fractal <- Collection.fractals) {
      menu.add(
        new MenuItem(
          fractal.toString(),
          new SetFractalAction(fractal, modell)))
    }
    myMenuBar.add(menu)
  }

  { // Auswahl Menu
    val menu = new JMenu("Auswahl")
    var i = 0
    var sub:JMenu = null
    for (viewport <- entities.viewport.ViewportConstants.auswahl) {
      if(i%10 == 0){
        sub = new JMenu("sub %d".format(i/10))
        menu.add(sub.asInstanceOf[JMenuItem])
      }
      sub.add(
        new MenuItem(
          "%d".format(i),
          new SetViewportAction(viewport, modell)))
      i = i+1
    }
    myMenuBar.add(menu)
  }
  
    { // Auswahl Menu
    val menu = new JMenu("FokusAuswahl1")
    var i = 0
    var sub:JMenu = null
    for (viewport <- entities.viewport.Fokus.iteration1) {
      if(i%10 == 0){
        sub = new JMenu("sub %d".format(i/10))
        menu.add(sub.asInstanceOf[JMenuItem])
      }
      sub.add(
        new MenuItem(
          "%d".format(i),
          new SetViewportAction(viewport, modell)))
      i = i+1
    }
    myMenuBar.add(menu)
  }

  { // Save Menu
    val menu = new JMenu("Save")
    var menuItem: JMenuItem = null
    menuItem = new JMenuItem("Viewport")
    menuItem.addActionListener(new ActionListener() {
      override def actionPerformed(arg0: ActionEvent) {
        println(modell.view)
      }
    })
    menu.add(menuItem)
    menuItem = new JMenuItem("Image")
    menuItem.addActionListener(new ActionListener() {
      override def actionPerformed(arg0: ActionEvent) {
        modell.save()
      }
    })
    menu.add(menuItem)
    myMenuBar.add(menu)
  }

  { // fokus von points
    val menuItem = new JMenuItem("Fokus")
    menuItem.addActionListener(new ActionListener() {
      override def actionPerformed(arg0: ActionEvent) {
        if (modell.points.length >= 2) {
          val a = modell.points(0)
          val b = modell.points(1)
          val fokus = new entities.viewport.Fokus(a, b)
          modell.setViewport(fokus)
          modell.clearPoints()
        }
      }
    })
    myMenuBar.add(menuItem)
  }

  
  setJMenuBar(myMenuBar)
  //  myMenuBar.add(new ColorMenu(DefaultColors.instances, modell))
  setFocusable(false)
  pack()
  setExtendedState(Frame.MAXIMIZED_BOTH)
  setVisible(true)
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
}
