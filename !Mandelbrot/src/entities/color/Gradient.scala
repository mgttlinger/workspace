package entities.color

class Gradient(val fa: ConstantColor, val fb: ConstantColor) extends Color {
  override def apply(p: Double): Int = ConstantColor.interpolate(fa, fb, p)
  override def toString(): String = "Gradient %s %s".format(fa, fb)
}
