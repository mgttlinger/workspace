package MVC.view

import javax.swing.JPanel
import java.awt.image.BufferedImage
import java.awt.Graphics
import util._
import MVC.modell.Modell

import entities.viewport.Transform
import entities.viewport.Dimensions
import entities.viewport._

class ImagePanel(val modell: Modell) extends JPanel with Observer {
  modell.addObserver(this)
  setFocusable(true)
  setPreferredSize(new java.awt.Dimension(1000, 800))

  def invert(p: Point) = {
    val trans = new Transform(modell.view, new Dimensions(getWidth(), getHeight()))
    new Point(trans.invertX(p.x, p.y), trans.invertY(p.x, p.y))
  }

  def line(g:Graphics,a:Point, b:Point){
    val inva = invert(a)
    val invb = invert(b)
    g.drawLine(inva.x.toInt, inva.y.toInt, invb.x.toInt, invb.y.toInt)
  }
  
  override def paint(g: Graphics) {
    //g.clearRect(0, 0, getWidth(), getHeight())
    g.drawImage(modell.img, 0, 0, getWidth, getHeight, this)

    g.setColor(java.awt.Color.red)
    val trans = new Transform(modell.view, new Dimensions(getWidth(), getHeight()))
    
    

    for (p <- modell.points) {
      val posx = trans.invertX(p.x, p.y).toInt
      val posy = trans.invertY(p.x, p.y).toInt
      g.drawLine(posx - 25, posy - 25, posx + 25, posy + 25)
      g.drawLine(posx - 25, posy + 25, posx + 25, posy - 25)
    }

    /*
        // Kreuze für alle Fokus-Viewports
    for (f <- Fokus.iteration1) {
      val p = (f.a+f.b)*0.5
      val posx = trans.invertX(p.x, p.y).toInt
      val posy = trans.invertY(p.x, p.y).toInt
      g.drawLine(posx - 25, posy - 25, posx + 25, posy + 25)
      g.drawLine(posx - 25, posy + 25, posx + 25, posy - 25)
    }
    */
    
   /* if (modell.points.size >= 2) {
      val a = modell.points(0)
      val b = modell.points(1)

      line(g, a, b)
      
      val diff = b - a
      val norm = diff norm
      val rot = Fokus.rotAngle(diff)
      val rotOrth = rot.orth()
      
      val A = rot * ((rot * diff) / (norm*norm))
  val B = rotOrth * ((rotOrth * diff) / (norm*norm))

//  val A = rot
//      val B = rotOrth
  
      
      g.setColor(java.awt.Color.green)
      line(g, a, a+A)
      line(g, a, a+B)
      line(g, a+A, a+B+A)
      line(g, a+B, a+B+A)
      
      
      val TA = A * ( 1.0 / Fokus.Fdelta.y)
      val TB = B * ( 1.0 / Fokus.Fdelta.x)
      val U = a - TA * Fokus.FA.y - TB*Fokus.FA.x
      
      
      g.setColor(java.awt.Color.blue)
      line(g, U, U+TA)
      line(g, U, U+TB)
      line(g, U+TA, U+TB+TA)
      line(g, U+TB, U+TB+TA)
      
    }*/
  }

  override def update(caller: Observable) = repaint()
}