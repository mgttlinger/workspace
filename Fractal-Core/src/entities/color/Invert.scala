package entities.color

class Invert(private val farbe: Color) extends Color {
  override def apply(d: Double): Int = farbe.apply(1 - d)
}
