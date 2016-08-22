package GUI.raster

import GUI.raster.path._

class Raster extends javax.swing.JPanel {
  case class Graph(val funk: mathParser.sprache.mathe.Real.Term, val variable: mathParser.sprache.mathe.Real.Variable) {
    def createTransformedShape(toDrawPlane: java.awt.geom.AffineTransform, config: modell.Viewport): java.awt.Shape = {
      val x_start = config.U.x
      val x_end = x_start + config.A.x
      val x_delta = config.A.x / 1000d;

      val path = new java.awt.geom.Path2D.Double();
      variable(x_start)
      path.moveTo(x_start, funk())
      for (x <- Range.Double(x_start, x_end, x_delta)) {
        variable(x);
        val y = funk();
        if (java.lang.Double.isNaN(y) || java.lang.Double.isInfinite(y)) {
          path.moveTo(x, y);
        } else {
          path.lineTo(x, y);
        }
      }
      return path.createTransformedShape(toDrawPlane);
    }
  }

  var view = new modell.Viewport(new modell.Point(-10, -10), 20, 20)
  var graphs = List[Graph]()

  super.setPreferredSize(new java.awt.Dimension(750, 750))
  super.addKeyListener(new java.awt.event.KeyAdapter {
    override def keyPressed(arg: java.awt.event.KeyEvent) = {
      arg.getKeyCode() match {
        case 38 => view = view.up()
        case 39 => view = view.right()
        case 40 => view = view.down()
        case 37 => view = view.left()
        case 107 => view = view.zoomIn()
        case 108 => view = view.zoomOut()
      }
      repaint()
    }
  })
  super.addMouseListener(new java.awt.event.MouseAdapter {
    override def mousePressed(e: java.awt.event.MouseEvent) = {
      val x = e.getX().asInstanceOf[Double] / getWidth()
      val y = e.getY().asInstanceOf[Double] / getHeight()
      view = view.focus(x, y)
      repaint()
    }
  })
  super.addMouseWheelListener(new java.awt.event.MouseAdapter() {
    override def mouseWheelMoved(e: java.awt.event.MouseWheelEvent) = {
      val x = e.getX().asInstanceOf[Double] / getWidth()
      val y = e.getY().asInstanceOf[Double] / getHeight()
      view = view.zoom(x, y, e.getWheelRotation());
      repaint();
    }
  });
  super.setFocusable(true);

  override def paint(g: java.awt.Graphics): Unit = {
    if (!g.isInstanceOf[java.awt.Graphics2D])
      return
    val graphics = g.asInstanceOf[java.awt.Graphics2D]
    graphics.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON)

    graphics.setBackground(java.awt.Color.white)
    graphics.clearRect(0, 0, getWidth(), getHeight())

    val trans = new modell.Transform(view, new modell.Dimensions(getWidth(), getHeight()))

    val Atrans = trans.asTransform()
    try {
      Atrans.invert();
    } catch {
      case e: java.awt.geom.NoninvertibleTransformException => return
    }

    // graphics.setColor(Color.lightGray);
    // graphics.draw(new Background().createTransformedShape(Atrans, view));
    graphics.setColor(java.awt.Color.black);
    graphics.draw(new Axes().createTransformedShape(Atrans, view));

    graphics.setColor(java.awt.Color.red);
    for (graph <- graphs) {
      graphics.draw(graph.createTransformedShape(Atrans, view));
    }
  }

  def addFunction(funk: mathParser.sprache.mathe.Real.Term, variable: mathParser.sprache.mathe.Real.Variable) = {
    graphs = new Graph(funk, variable) :: graphs
    repaint()
  }

  def clear() = {
    graphs = Nil
    repaint()
  }

    import mathParser.sprache.mathe._
    import mathParser.sprache.mathe.Real._
  
    def parseAndDrawFunction(term: String, variable: String):Unit = {
      val p = new mathParser.parser.Parser(Real)
      val x = p.registerVariable(variable)
      p(term) match {
        case Some(t) => {
          addFunction(t.asInstanceOf[Term], x.asInstanceOf[Variable])
        }
        case None => 
      }
    }

}