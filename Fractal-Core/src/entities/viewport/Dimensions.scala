package entities.viewport

case class Dimensions(width:Int, height:Int) {
  def scale(factor:Double)= Dimensions((width*factor).toInt, (height*factor).toInt)
}

object Dimensions {
  val screenHD = Dimensions(1920, 1200)
}


trait HasDimensions{
  def dimensions: Dimensions
  def width = dimensions.width
  def height = dimensions.height
}