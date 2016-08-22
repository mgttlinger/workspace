package entities.image

import entities.preImage.DoubleRaster
import entities.viewport._
import entities.color.Color
import entities.imageFactory._
import entities.fractal.Fractal
import java.awt.image.BufferedImage

class Image(val pre: DoubleRaster, val farbe: Color) {
  val width = pre.dim.width
  val height = pre.dim.height

  val buff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
  for (w <- 0 until width; h <- 0 until height)
    buff.setRGB(w, h, farbe(pre(w, h)))

  def save(file: java.io.File) = {
    if(file.getParentFile() != null)
    	file.getParentFile().mkdirs()
    javax.imageio.ImageIO.write(buff, "png", file)
  }
}