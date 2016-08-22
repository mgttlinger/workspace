package gui

import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.Dimension
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.RenderingHints
import java.awt.Color

import modell._

class GUI(val S: modell.System) extends JPanel {

  val b = S.area.getBounds()
  var xm, ym, xn, yn: Double = _
  val r = 10

  val selectAction = new SelectAction(S, this)
  val deleteAction = new DeleteAction(S, this)
  val addGooAction = new AddGooAction(S, this)
  val addFixAction = new AddFixAction(S, this)
  val addHGooAction = new AddHGooAction(S, this)
  def setActive(a: Action) = {
    this.removeMouseListener(selectAction)
    this.removeMouseListener(deleteAction)
    this.removeMouseListener(addGooAction)
    this.removeMouseListener(addFixAction)
    this.removeMouseListener(addHGooAction)
    this.addMouseListener(a)
  }

  def initTransform(width: Int, height: Int): Unit = {
    val o = 0
    xm = (width - 2 * o) / b.width.toDouble
    xn = -(width - 2 * o) * b.x / b.width + o

    ym = (height - 2 * o) / b.height.toDouble
    yn = -(height - 2 * o) * b.y / b.height + o
  }

  def transformX(x: Double): Int = (xm * x + xn) toInt
  def transformY(y: Double): Int = (ym * y + yn) toInt
  def detransformX(x: Int): Double = (x - xn) / xm
  def detransformY(y: Int): Double = (y - yn) / ym

  def linkSelected() = {
    val goos = selectAction.selected
    for (i <- 0 until goos.length)
      for (j <- i + 1 until goos.length)
        S.addLink(new Link(goos(i), goos(j)))
    selectAction.selected = List[MassPoint]()
    repaint()
  }

  override def paint(_g: Graphics) = {
    val g = _g.asInstanceOf[Graphics2D]
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)

    val width = getWidth
    val height = getHeight
    initTransform(width, height)
    g.clearRect(0, 0, width, height)

    import java.awt.geom.AffineTransform
    val at = new AffineTransform(xm, 0, 0, ym, xn, yn);

    g.setColor(Color.gray)
    g.fillRect(0, 0, width, height)
    g.setColor(Color.white)
    g.fill(at.createTransformedShape(S.area))

    g.setColor(Color.black)
    for (link <- S.links) {
      g.setColor({
        val t = link.tension()
        if (t > 4)
          Color.red
        else if (t < 0.25)
          Color.red
        else if (t > 1)
          new Color((85 * (t - 1)).toInt, 0, 0)
        else
          new Color(((1 - t) * 340).toInt, 0, 0)
      })
      g.drawLine(transformX(link.a.x), transformY(link.a.y),
        transformX(link.b.x), transformY(link.b.y))
    }

    for (goo <- S.masses) {
      g.setColor(goo match {
        case _: GooBall => Color.green
        case _: HeavyGooBall => new Color(0, 128, 0)
        case _: FixPoint => Color.red
        case _ => Color.black
      })

      g.fillArc(transformX(goo.x) - (r / 2), transformY(goo.y) - (r / 2), r, r, 0, 360)
      //g.drawLine(transformX(goo.x), transformY(goo.y), transformX(goo.x + goo.xv), transformY(goo.y + goo.yv))
      g.setColor(Color.black)
      //g.drawArc(transformX(goo.x) - (r / 2), transformY(goo.y) - (r / 2), r, r, 0, 360)
    }

    g.setColor(Color.red)
    for (goo <- selectAction.selected)
      g.drawArc(transformX(goo.x) - ((r + 10) / 2), transformY(goo.y) - ((r + 10) / 2), r + 10, r + 10, 0, 360)
  }
}