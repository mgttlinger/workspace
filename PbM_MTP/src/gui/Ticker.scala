package gui

import java.awt.event.KeyListener

class Ticker(val g: GUI) extends Thread with KeyListener {
  var cont = true
  var dt = 0.005

  override def run() {
    while (true) {
      if (cont) {
//        for(i <- 1 to 10)
        	g.S.step(dt)
        g.repaint()
      }
      Thread.sleep(1000 / 30);
    }
  }

  def toggle() = cont = !cont

  import java.awt.event.KeyEvent
  def keyTyped(e: KeyEvent) {
    if (e.getKeyChar() == ' ')
      toggle()
    if (e.getKeyChar() == '+')
      dt *= 1.1
    if (e.getKeyChar() == '-')
      dt /= 1.1
  }
  def keyReleased(e: KeyEvent) {}
  def keyPressed(e: KeyEvent) {}
}