package entities.color

trait HSV extends Color {
  def H(lambda: Double): Double
  def S(lambda: Double): Double
  def V(lambda: Double): Double

  def apply(lambda: Double): Int = {
    require(lambda >= 0)
    require(lambda <= 1)

    HSV2RGB(H(lambda), S(lambda), V(lambda))
  }

  def RGB(R: Double, G: Double, B: Double): Int = {
    assert(R >= 0 && R <= 1)
    assert(G >= 0 && G <= 1)
    assert(B >= 0 && B <= 1)
    (R * 255).toInt * (256 * 256) + (G * 255).toInt * (256) + (B * 255).toInt
  }

  def HSV2RGB(H: Double, S: Double, V: Double): Int = {
    // H \in [0 : 360]
    // S \in [0 : 1]
    // V \in [0 : 1]
    val h = (H / 60).toInt
    val f = H / 60.0 - h
    val q = V * (1 - S * f)
    val p = V * (1 - S)
    val t = V * (1 - S * (1 - f))

    h % 6 match {
      case 6 | 0 => RGB(V, t, p);
      case 1 => RGB(q, V, p);
      case 2 => RGB(p, V, t);
      case 3 => RGB(p, q, V);
      case 4 => RGB(t, p, V);
      case 5 => RGB(V, p, q);
      case _ => RGB(0, 0, 0);
    }
  }
}

object HSV {
  case object TestColor extends HSV {
    def H(lambda: Double) = lambda * 360 + 60
    def S(lambda: Double) = if (lambda < 0.5) 1 else 2 * (1 - lambda)
    def V(lambda: Double) = if (lambda < 0.5) 2 * lambda else 1
  }

  case object Rainbow extends HSV {
    def H(lambda: Double) = lambda * 360 + 240
    def S(lambda: Double) = 1
    def V(lambda: Double) = 1
  }

  object MonoColor {
    abstract class HSVMonoColor(val H: Double) extends HSV {
      def H(lambd: Double) = H
      def S(lambda: Double) = if (lambda < 0.5) 1 else 2 * (1 - lambda)
      def V(lambda: Double) = if (lambda < 0.5) 2 * lambda else 1
    }

    case object Red extends HSVMonoColor(0)
    case object Yello extends HSVMonoColor(60)
    case object Green extends HSVMonoColor(120)
    case object Cyan extends HSVMonoColor(180)
    case object Blue extends HSVMonoColor(240)
    case object Magenta extends HSVMonoColor(300)
  }

  object MonoColor2 {
    abstract class HSVMonoColor(val H: Double) extends HSV {
      def H(lambd: Double) = H
      def S(lambda: Double) = 1 - math.sqrt(1 - math.pow(1 - lambda, 2))
      def V(lambda: Double) = math.sqrt(1 - math.pow(1 - lambda, 2))
    }

    case object Red extends HSVMonoColor(0)
    case object Yello extends HSVMonoColor(60)
    case object Green extends HSVMonoColor(120)
    case object Cyan extends HSVMonoColor(180)
    case object Blue extends HSVMonoColor(240)
    case object Magenta extends HSVMonoColor(300)
  }
}

