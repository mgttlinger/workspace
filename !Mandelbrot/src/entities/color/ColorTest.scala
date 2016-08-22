package entities.color

case class HSV(val H: Int, val S: Int, val V: Int) {

}

object ColorTest {
  def RGB(R: Double, G: Double, B: Double): Int = {
    assert(R >= 0 && R <= 1)
    assert(G >= 0 && G <= 1)
    assert(B >= 0 && B <= 1)
    return (R * 255).toInt * (256 * 256) + (G * 255).toInt * (256) + (B * 255).toInt
  }

  def HSV2RGB(input: HSV): Int = {
    return HSV2RGB(input.H, input.S, input.V)
    val H = input.H;
    val V = input.V / 100.0;
    val S = input.S / 100.0;
    val h = H / 60;
    val f = H / 60.0 - h;
    val q = V * (1 - S * f);
    val p = V * (1 - S);
    val t = V * (1 - S * (1 - f));

    //std::cout << "in: H=" << H << ", S=" << S << ", V=" << V << " -> h=" << h << ", f=" << f << ", q=" << q << ", p=" << p << ", t=" << t << "\n";

    h match {
      case 6 | 0 => return RGB(V, t, p);
      case 1 => return RGB(q, V, p);
      case 2 => return RGB(p, V, t);
      case 3 => return RGB(p, q, V);
      case 4 => return RGB(t, p, V);
      case 5 => return RGB(V, p, q);
      case _ => return RGB(0, 0, 0);
    }
  }

  def HSV2RGB(H: Double, S: Double, V: Double): Int = {
    // H \in [0 : 360]
    // S \in [0 : 1]
    // V \in [0 : 1]     
    val h = (H / 60).toInt;
    val f = H / 60.0 - h;
    val q = V * (1 - S * f)
    val p = V * (1 - S)
    val t = V * (1 - S * (1 - f))

    h % 6 match {
      case 6 | 0 => return RGB(V, t, p);
      case 1 => return RGB(q, V, p);
      case 2 => return RGB(p, V, t);
      case 3 => return RGB(p, q, V);
      case 4 => return RGB(t, p, V);
      case 5 => return RGB(V, p, q);
      case _ => return RGB(0, 0, 0);
    }
  }
}

class ColorTest extends Color {


  def apply(_lambda: Double): Int = {
    val lambda = Math.max(0, Math.min(1, _lambda))

    val H = 0.5 * Math.sin(5 * Math.PI * lambda) * 60 + 240
    val S = if (lambda < 0.5) 1 else 2 * (1 - lambda)
    val V = if (lambda < 0.5) 2 * lambda else 1
    return ColorTest.HSV2RGB(H, S, V)
  }
  
//    def apply(_lambda: Double): Int = {
//    val lambda = Math.max(0, Math.min(1, _lambda))
//
//    val l2 = if(lambda > 0.5) 2*(1-lambda) else 2*lambda
//    
//    val H = 0.0 * Math.sin(5 * Math.PI * l2) * 60 + 240
//    val S = if (l2 < 0.5) 1 else 2 * (1 - l2)
//    val V = if (l2 < 0.5) 2 * l2 else 1
//    return ColorTest.HSV2RGB(H, S, V)
//  }
}