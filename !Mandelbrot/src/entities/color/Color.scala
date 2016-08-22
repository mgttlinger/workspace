package entities.color

trait Color {
  def apply(p: Double): Int
}

object DefaultColors {

  import ConstantColor.starter
  val instances: List[Color] = null /* {
    var data = List[Color]()
    for (s <- 0 until starter.length; first <- 0 until 3; second <- 0 until 2) {
      data = data :+ (new GradientN(starter(s),
        starter(s ^ (1 << first)),
        starter(s ^ (1 << first) ^ (1 << (if (second >= first) second + 1 else second))),
        starter(0x7 & ~s)))
    }
    data
  }*/
  
  val default = new GradientN(
      new ConstantColor(0xffffff),
      new ConstantColor(0x00ffff),
      new ConstantColor(0x0000ff),
      new ConstantColor(0x000000))
}