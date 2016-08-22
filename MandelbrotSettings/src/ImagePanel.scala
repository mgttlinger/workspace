import java.awt.Graphics
import java.awt.image.BufferedImage
import javax.swing.JFrame

class ImagePanel(val name:String, val image: BufferedImage) extends JFrame(name) {
  setPreferredSize(new java.awt.Dimension(1000, 800))
  pack()
  setVisible(true)

  override def paint(g: Graphics) {
    g.drawImage(image, 0, 0, getWidth, getHeight, null)
  }

}