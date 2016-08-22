package modell

object Dimensions {
  val screenHD = new Dimensions(1920, 1080)
  val screenHD4 = screenHD.scale(2)
  val screenBig = screenHD.scale(10.0)
}

class Dimensions(val width: Int, val height: Int) {
  require(width > 0)
  require(height > 0)
  
  def scale(faktor: Double) = {
    val w = (width * faktor).toInt
    val h = (height * faktor).toInt
    new Dimensions(w, h)
  }
}