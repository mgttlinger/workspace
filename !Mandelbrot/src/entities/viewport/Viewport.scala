package entities.viewport

import java.io.Serializable

object ViewportUtil {

  def createViewportByLongs(x0: Long, y0: Long, ax: Long, ay: Long, bx: Long, by: Long) = {
    new Viewport(PointUtil.createPointByLongs(x0, y0),
      PointUtil.createPointByLongs(ax, ay),
      PointUtil.createPointByLongs(bx, by))
  }

  def createViewportCentered(a: Point, b: Point): Viewport = {
    val diff = b - a
    val orth = diff.orth()
    val U = a - diff - orth
    val B = orth * 2
    val A = diff * 3
    return new Viewport(U, A, B)
  }

  val start = new Viewport(new Point(-2.5, -1), new Point(3.5, 0), new Point(0, 2)) // HIER!

}

object ViewportConstants {
  val factorMove: Double = 0.10
  val factorZoom: Double = 0.20
  val invFactorZoom: Double = 1.0 / (1.0 - factorZoom)

  import ViewportUtil.createViewportByLongs
  val auswahl = List(createViewportByLongs(0x3fb919dc41fc0c98L, 0x3fe43ce9cd70672cL, 0x3ee1adf398f80000L, 0x0000000000000000L, 0x0000000000000000L, 0x3ed427536f2c0000L),
    createViewportByLongs(0x3fb919dc41fc0c98L, 0x3fe43ce9cd70672cL, 0x3ee1adf398f80000L, 0x0000000000000000L, 0x0000000000000000L, 0x3ed427536f2c0000L),
    createViewportByLongs(0x3fb951bcba0089d2L, 0x3fe4246546543ed9L, 0x3f6c9011b05ec440L, 0x0000000000000000L, 0x0000000000000000L, 0x3f6047ab97bd0c00L),
    createViewportByLongs(0x3fb9b5400e5b31cbL, 0x3fe43c3c5248660cL, 0xbdca5afa00000000L, 0x3d8d750000000000L, 0xbd8f01a800000000L, 0xbdbc2a6800000000L),
    createViewportByLongs(0x3fb9b54030375a4eL, 0x3fe43c3c544acb5cL, 0xbe51082219800000L, 0x3e1308d8c0000000L, 0xbe14099184000000L, 0xbe4233a898000000L),
    createViewportByLongs(0x3fd0473452b553faL, 0xbf419e2591221dd3L, 0x3f2f67b47a99a8dfL, 0x0000000000000000L, 0x0000000000000000L, 0x3f21e666e568795dL),
    createViewportByLongs(0x3fd2b2b15dc97007L, 0x3f8dbda2e4106624L, 0x3ec7097d036c0000L, 0x0000000000000000L, 0x0000000000000000L, 0xbeba53fc9631c000L),
    createViewportByLongs(0x3fd2b2b6cc9f4815L, 0x3f8dbd40f5c12a1eL, 0x3e79effb48000000L, 0x0000000000000000L, 0x0000000000000000L, 0xbe6da48ce4700000L),
    createViewportByLongs(0x3fd3a91862994662L, 0x3f9d2258e9caf8d7L, 0x3f6247b966a2efb2L, 0x0000000000000000L, 0x0000000000000000L, 0x3f54d69e32db5d24L),
    createViewportByLongs(0x3fd4e6446c003b60L, 0x3fa28167f4288367L, 0x3f695a5efea6b200L, 0x3c70000000000000L, 0x0000000000000000L, 0x3f695a5efea6b390L),
    createViewportByLongs(0x3fd4fb01ee73c238L, 0x3fa31928408d92feL, 0x3f16d601ad376000L, 0x0000000000000000L, 0x0000000000000000L, 0x3f16d601ad376c00L),
    createViewportByLongs(0x3fd4fbb672aa5711L, 0x3fa31dead3897147L, 0x3ee39dae6f770000L, 0x0000000000000000L, 0x0000000000000000L, 0x3ee39dae6f76e000L),
    createViewportByLongs(0x3fd4fbc5d186203bL, 0x3fa31e94b76e533fL, 0x3eb0d9976a580000L, 0x0000000000000000L, 0x0000000000000000L, 0x3eb0d9976a5e0000L),
    createViewportByLongs(0x3fd4fbca07ebfacfL, 0x3fa31e9816263552L, 0x3eb0d9976a580000L, 0x0000000000000000L, 0x0000000000000000L, 0x3eb0d9976a5e0000L),
    createViewportByLongs(0x3fd4fbcb4e51a4acL, 0x3fa31e9847f17159L, 0x3e7286d80f000000L, 0x0000000000000000L, 0x0000000000000000L, 0x3e7286d80ed00000L),
    createViewportByLongs(0x3fd62fa91bd76e52L, 0x3fd70c8d42476c50L, 0x3f5eef77a65c7c00L, 0x0000000000000000L, 0x0000000000000000L, 0x3f51a22b045eac00L),
    createViewportByLongs(0x3fd677bb376bfa88L, 0x3fd7abb3ccea013dL, 0x3f1e57cffd5dc000L, 0x0000000000000000L, 0x0000000000000000L, 0x3f114bb92dfda000L),
    createViewportByLongs(0x3fd689412a587594L, 0x3fd601849e62b476L, 0x3f4729c9ad7ed400L, 0x0000000000000000L, 0x0000000000000000L, 0x3f3a6810bb6d8000L),
    createViewportByLongs(0x3fd6897626b5d069L, 0x3fd5f304d0fe04b2L, 0x3f725f7e8cbb7280L, 0x0000000000000000L, 0x0000000000000000L, 0x3f64f211914aaf00L),
    createViewportByLongs(0x3fd68b79b675ea99L, 0x3fd6049ba09a9cbdL, 0x3f0213f1a2e7c000L, 0xbeb691b33b140000L, 0xbe945fe581d00000L, 0xbef66fd8ae430000L),
    createViewportByLongs(0x3fd6b80e8eb0905fL, 0x3fd60c97d60faf07L, 0x3ed34c1f039c0000L, 0x0000000000000000L, 0x0000000000000000L, 0x3ec5ffd37a480000L),
    createViewportByLongs(0x3fd6c3283cc3c79dL, 0x3fd60d5e21097162L, 0xbea464307e900000L, 0xbea37ed68c080000L, 0x3e9d6a190a600000L, 0xbe9a4331f1900000L),
    createViewportByLongs(0x3fd6c328c99ba770L, 0x3fd60d5da12b9a9fL, 0xbe8561c3ee200000L, 0xbe847145e5000000L, 0x3e7ed7e13d800000L, 0xbe7b89c7f0c00000L),
    createViewportByLongs(0x3fd78029adf9fc0eL, 0x3fe2f388010a0334L, 0x3ec26dfd9c580000L, 0x0000000000000000L, 0x0000000000000000L, 0xbeb50ffd44f00000L),
    createViewportByLongs(0x3fd7803923c43ac9L, 0x3fe2f3912cf3d67aL, 0x3e84437c50400000L, 0x0000000000000000L, 0x0000000000000000L, 0xbe77288e12000000L),
    createViewportByLongs(0x3fd9f128e8c30c47L, 0x3fc2e6ae8300a115L, 0x3ee5e25afd260000L, 0x0000000000000000L, 0x0000000000000000L, 0x3ed8f2c8f2aa0000L),
    createViewportByLongs(0xbfabae2659a182b3L, 0x3fef875ab34440d5L, 0x3e51680425400000L, 0x3ca0000000000000L, 0x0000000000000000L, 0xbe43e49708000000L),
    createViewportByLongs(0xbfb89539b888722aL, 0xbfe4e7be553ac4f8L, 0x3f12dfd694ccac00L, 0x0000000000000000L, 0x0000000000000000L, 0x3f0a36e2eb1c4000L),
    createViewportByLongs(0xbfb8a936ed338e24L, 0xbfe4cadb8e66f9c0L, 0x3f01bdb148ef0000L, 0x0000000000000000L, 0x0000000000000000L, 0x3ef1515f6d920000L),
    createViewportByLongs(0xbfb8b25ed2746a46L, 0xbfe4cbf79a914a9eL, 0x3f39d1084ed9c800L, 0x0000000000000000L, 0x0000000000000000L, 0x3f2933680e2b4000L),
    createViewportByLongs(0xbfb8e8427418d691L, 0xbfe4ce3150dae3e7L, 0x3f3aef6f8f041500L, 0x0000000000000000L, 0x0000000000000000L, 0x3f2669ced0b31000L),
    createViewportByLongs(0xbfb92135daad6020L, 0xbfe4d0c5eb313be2L, 0x3f633c1ce6c093e0L, 0x0000000000000000L, 0x0000000000000000L, 0x3f52c6ac215b9a00L),
    createViewportByLongs(0xbfba968e4d30ff27L, 0x3feeab94cfa18787L, 0x3e87b88a7e600000L, 0x0000000000000000L, 0x0000000000000000L, 0x3e7b1c0bfe000000L),
    createViewportByLongs(0xbfba9abf8f1a5f36L, 0xbfed8ed2a120a1a7L, 0x3e55c2052ef58a75L, 0x0000000000000000L, 0x0000000000000000L, 0x3e48ddbcc7f40bebL),
    createViewportByLongs(0xbfbaa8aee34d4c6eL, 0x3feeaa2130a573d1L, 0x3f41d878da754100L, 0x0000000000000000L, 0x0000000000000000L, 0x3f34651c67617000L),
    createViewportByLongs(0xbfbc34316a864cfdL, 0xbfecbb22a52def6cL, 0x3e57e409b1000000L, 0x0000000000000000L, 0x0000000000000000L, 0x3e4b3c6460000000L),
    createViewportByLongs(0xbfbc8457f95d096aL, 0xbfef292298d1f6dfL, 0x3e1323710e3d15c3L, 0x0000000000000000L, 0x0000000000000000L, 0x3e05df5ca28ef460L),
    createViewportByLongs(0xbfc076182e63d1ddL, 0x3fefa1b7184fc147L, 0x3e17ec4d50000000L, 0x0000000000000000L, 0x0000000000000000L, 0xbe0b573400000000L),
    createViewportByLongs(0xbfc0761a3f54d949L, 0x3fefa1b7578344ecL, 0x3e9eeb798fd00000L, 0x0000000000000000L, 0x0000000000000000L, 0xbe91ab20e4800000L),
    createViewportByLongs(0xbfc076e9fde9c979L, 0x3fefa1c6a3eccdacL, 0x3ea8280366c00000L, 0x3dc3042000000000L, 0x3d37a80000000000L, 0xbe9b9b7122800000L),
    createViewportByLongs(0xbfd5269057a1c2c6L, 0x3fe401b928207615L, 0x3f73ce9a36f23c00L, 0x0000000000000000L, 0x0000000000000000L, 0x3f73ce9a36f23c00L),
    createViewportByLongs(0xbfddcaf4a769ef19L, 0x3fe2847022c23a8aL, 0x3f2f38964ad1c000L, 0x0000000000000000L, 0x0000000000000000L, 0xbf21d7314f536000L),
    createViewportByLongs(0xbfe1333673651d0eL, 0xbfe395c986d77835L, 0x3e653f790fdbc90fL, 0x0000000000000000L, 0x0000000000000000L, 0x3e58488a5b445380L),
    createViewportByLongs(0xbfe28f09091b631dL, 0x3fdf465948b06135L, 0xbf1afcdcf9c3c000L, 0xbf19cd51b69e4000L, 0x3f13770030664000L, 0xbf1161098cfc9000L),
    createViewportByLongs(0xbfe28f1c4e38fdb9L, 0x3fdf45025a1f0439L, 0xbee28bbc10b50000L, 0xbee1bb240a8d0000L, 0x3edac09585620000L, 0xbed7e2b5d7360000L),
    createViewportByLongs(0xbfe28f1f0ea0371cL, 0x3fdf44e7c7cb8172L, 0xbeafdc8bc5d00000L, 0xbeae762f3ad80000L, 0x3ea6fae390100000L, 0xbea4847f04b80000L),
    createViewportByLongs(0xbfe652d705d0a096L, 0xbfd64390774c35c1L, 0x3d42980000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x3d429c0000000000L),
    createViewportByLongs(0xbfe652d705d1176cL, 0xbfd64390774d3e9bL, 0x3d99d28000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x3d99d2a000000000L),
    createViewportByLongs(0xbfe652d70b9c95f0L, 0xbfd643907c4ed5caL, 0x3e48ddbcc0000000L, 0x0000000000000000L, 0x0000000000000000L, 0x3e48ddbcc8000000L),
    createViewportByLongs(0xbfe7f1d17614d9a4L, 0x3fc168031f363015L, 0x3f60b3c336c30a00L, 0x0000000000000000L, 0x0000000000000000L, 0x3f531695f5713000L),
    createViewportByLongs(0xbfe7f41f41f2576bL, 0x3fc11528862de3d7L, 0x3f7fdb5eaf4b3200L, 0x0000000000000000L, 0x0000000000000000L, 0x3f7234361b066600L),
    createViewportByLongs(0xbfe9f64d2c5c481eL, 0xbfc796a41e176136L, 0x3eba53fc96300000L, 0x0000000000000000L, 0x0000000000000000L, 0x3eba53fc96300000L),
    createViewportByLongs(0xbfe9f659d9309724L, 0xbfc796d6d1689d51L, 0x3eeea6608e290000L, 0x0000000000000000L, 0x0000000000000000L, 0x3eeea6608e294000L),
    createViewportByLongs(0xbfefd1b929d5a6afL, 0x3fd32fde173ea150L, 0x3d9cebd3ac789d03L, 0x0000000000000000L, 0x0000000000000000L, 0x3d9086c219697e50L),
    createViewportByLongs(0xbff267b5f1bef49dL, 0x3fcb5dcc63f14120L, 0x3f22599ed7c70000L, 0x0000000000000000L, 0x0000000000000000L, 0x3f06052502eed000L),
    createViewportByLongs(0xbff2e30269ecab80L, 0xbfd34a3986719cb1L, 0x3dd0d5982c6a6f13L, 0x0000000000000000L, 0x0000000000000000L, 0x3dc33d4032c2c818L),
    createViewportByLongs(0xbff5855be739164aL, 0x3fa89801839c6f74L, 0x3eaa71e6e8000000L, 0x0000000000000000L, 0x0000000000000000L, 0x3e9e254d123c0000L),
    createViewportByLongs(0xbff5855eed9d6a39L, 0x3fa897c2a6249d8aL, 0x3ee80d30a2780000L, 0x0000000000000000L, 0x0000000000000000L, 0x3edb6ad82c480000L),
    createViewportByLongs(0xbff59437e1780367L, 0x3fa6c879dd085eb6L, 0x3e93a5d5b4000000L, 0x0000000000000000L, 0x0000000000000000L, 0x3e8665b955780000L),
    createViewportByLongs(0xbff59767a1b93008L, 0x3fa6a16a91690e17L, 0x3f16a751bf8c0000L, 0x0000000000000000L, 0x0000000000000000L, 0x3f09d2e470428800L),
    createViewportByLongs(0xbff653868f9862dfL, 0x3f9160271b3edd19L, 0x3ee5f8487cd80000L, 0x0000000000000000L, 0x0000000000000000L, 0x3ed91bc08eaca000L),
    createViewportByLongs(0xbff66e955a6e6062L, 0xbefd359f15f5f433L, 0xbdc2309000000000L, 0xbdc163f9f0b00000L, 0x3dba3d1000000000L, 0xbdb76d4a59600000L),
    createViewportByLongs(0xbff8e29b4e8ebf12L, 0x3f12893bf19b7adcL, 0x3ea139f3a3800000L, 0x0000000000000000L, 0x0000000000000000L, 0x3e93aff1df6f7c00L),
    createViewportByLongs(0xbfff19a6ea2a944eL, 0x3e51f7cbbf111ee1L, 0x3dda4dbdc5664d8eL, 0x0000000000000000L, 0x0000000000000000L, 0x3dce0fb44f5058a6L),
    createViewportByLongs(0xbffff12880163b21L, 0x3ecb6329faccb6e0L, 0x3e70999697000000L, 0x3b90000000000000L, 0x0000000000000000L, 0xbe62f8ac174d6080L),
    createViewportByLongs(0xbffff1289a2f9024L, 0x3ecc1658de9600a1L, 0x3e89545b65000000L, 0x0000000000000000L, 0x0000000000000000L, 0xbe7cf2b1970e7280L))
}

@SerialVersionUID(1L)
class Viewport(val U: Point, val A: Point, val B: Point) extends Serializable {

  require(PointUtil.linearIndependant(A, B))

  /*def this(x0: Double, y0: Double, ax: Double, ay: Double, bx: Double, by: Double) {
    this(new Point(x0, y0), new Point(ax, ay), new Point(bx, by))
  }*/

  override def hashCode(): Int = U.hashCode ^ A.hashCode ^ B.hashCode

  override def equals(o: Any): Boolean = {
    if (o.isInstanceOf[Viewport]) {
      val c = o.asInstanceOf[Viewport]
      return U == c.U && A == c.A && B == c.B
    }
    false
  }

  private def translate(t: Point): Viewport = new Viewport(U + t, A, B)

  import ViewportConstants._

  def right(): Viewport = translate(A.*(factorMove))
  def left(): Viewport = translate(A.*(-factorMove))
  def up(): Viewport = translate(B.*(-factorMove))
  def down(): Viewport = translate(B.*(factorMove))

  def focus(zielx: Double, ziely: Double): Viewport =
    translate(A * (zielx - 0.5) + (B * (ziely - 0.5)))

  def zoomOut(zx: Double, zy: Double): Viewport = {
    val new_a = A * invFactorZoom
    val new_b = B * invFactorZoom
    val new_basis = U + (new_a * (-factorZoom * zx)) + (new_b * (-factorZoom * zy))
    new Viewport(new_basis, new_a, new_b)
  }

  def zoomOut(): Viewport = zoomOut(0.5, 0.5)

  def zoomIn(zx: Double, zy: Double): Viewport = {
    val new_a = A * (1 - factorZoom)
    val new_b = B * (1 - factorZoom)
    val new_basis = U + (A * (factorZoom * zx)) + (B * (factorZoom * zy))
    new Viewport(new_basis, new_a, new_b)
  }

  def zoomIn(): Viewport = zoomIn(0.5, 0.5)

  def zoom(zx: Double, zy: Double, steps: Int): Viewport = {
    var view = this
    if (steps > 0)
      for (i <- 0 until steps) view = view.zoomOut(zx, zy)
    else
      for (i <- 0 until -steps) view = view.zoomIn(zx, zy)
    view
  }

  override def toString(): String =
    "%s, %s, %s".format(U, A, B)
}

object Fokus {
  val FA = new Point(0.3, 0.1);
  val FB = new Point(0.7, 0.3);

  //  val FA = new Point(800.0 / 1920.0, 300.0 / 1080);
  //  val FB = new Point(1200.0 / 1920.0, 1000.0 / 1080);

  //      val FA = new Point(0.49, 0.25);
  //    val FB = new Point(0.51, 0.71);
  val Fdelta = FB - FA
  val angle = Math.acos(Fdelta.y / Fdelta.norm())

  def rotAngle(p: Point): Point = {
    val c = Math.cos(angle)
    val s = Math.sin(angle)
    new Point(c * p.x - s * p.y, s * p.x + c * p.y)
  }

  def extractU(a: Point, b: Point) = {
    val diff = b - a
    val norm = diff norm
    val rot = Fokus.rotAngle(diff)
    val rotOrth = rot.orth()

    val A = rot * ((rot * diff) / (norm * norm))
    val B = rotOrth * ((rotOrth * diff) / (norm * norm))

    val TA = A * (1.0 / Fokus.Fdelta.y)
    val TB = B * (1.0 / Fokus.Fdelta.x)
    val U = a - TA * Fokus.FA.y - TB * Fokus.FA.x
    U
  }

  def extractA(a: Point, b: Point) = {
    val diff = b - a
    val norm = diff norm
    val rot = Fokus.rotAngle(diff)
    val rotOrth = rot.orth()

    val A = rot * ((rot * diff) / (norm * norm))
    val B = rotOrth * ((rotOrth * diff) / (norm * norm))

    val TA = A * (1.0 / Fokus.Fdelta.y)
    val TB = B * (1.0 / Fokus.Fdelta.x)
    val U = a - TA * Fokus.FA.y - TB * Fokus.FA.x
    TA
  }

  def extractB(a: Point, b: Point) = {
    val diff = b - a
    val norm = diff norm
    val rot = Fokus.rotAngle(diff)
    val rotOrth = rot.orth()

    val A = rot * ((rot * diff) / (norm * norm))
    val B = rotOrth * ((rotOrth * diff) / (norm * norm))

    val TA = A * (1.0 / Fokus.Fdelta.y)
    val TB = B * (1.0 / Fokus.Fdelta.x)
    val U = a - TA * Fokus.FA.y - TB * Fokus.FA.x  
    TB
    
    
  }

  /*def extractU(a: Point, b: Point) = {
    val diff = b - a
    val orth = diff.orth()
    val U = a - diff - orth
    U
  }

  def extractA(a: Point, b: Point) = {
    val diff = b - a
    val orth = diff.orth()
    val A = diff * 3
    A
  }

  def extractB(a: Point, b: Point) = {
    val diff = b - a
    val orth = diff.orth()
    val B = orth * 2
    B
  }*/

  val iteration1 = List[Fokus](
    new Fokus(0x3fb919f85c17a25dL, 0x3fe43ceed342be2cL, 0x3fb91a1d55cbf54eL, 0x3fe43cefed8da95bL),
    new Fokus(0x3fb9f710313ec9c3L, 0x3fe441be56afcd03L, 0x3fba0911ad1876e8L, 0x3fe440caecdd729aL),
    new Fokus(0x3fd049577dc1be5fL, 0xbf3f254345edcc98L, 0x3fd0498314ebe8f9L, 0xbf3f50641c3f13f4L),
    new Fokus(0x3fd3c3389d92a50bL, 0x3f9d75280f154e17L, 0x3fd3c317accb81f1L, 0x3f9d6f8920ea964fL),
    new Fokus(0x3fd506fe3deaca9dL, 0x3fa3de2b2255a21dL, 0x3fd5060bc0ca7f0eL, 0x3fa3f18bf7828279L),
    new Fokus(0x3fd4fbcd340eb991L, 0x3fa31e8544a92ff7L, 0x3fd4fbc9a392eaefL, 0x3fa31e9d763ea416L),
    new Fokus(0x3fd63939fa13efb5L, 0x3fd70d545f28ab29L, 0x3fd639b37dd0582fL, 0x3fd70e2262598521L),
    new Fokus(0x3fd676e5e2de0fa4L, 0x3fd7ae13a04bb5caL, 0x3fd676bf17f4da42L, 0x3fd7ae63eaf498c2L),
    new Fokus(0x3fd6c7af45093d1cL, 0x3fd60352263f35b4L, 0x3fd6c790273c70f3L, 0x3fd6043c952b5aefL),
    new Fokus(0x3fd68bc5347ac753L, 0x3fd6046afa3cff03L, 0x3fd68bbdb7aadf37L, 0x3fd6046afc3238f5L),
    new Fokus(0x3fd6c66ca7d15188L, 0x3fd60c21d7dd75c7L, 0x3fd6c65e301e02c6L, 0x3fd60c22f396cc19L),
    new Fokus(0x3fd7802809606b6aL, 0x3fe2f389e9e22f76L, 0x3fd78035569277daL, 0x3fe2f38f51e51dcaL),
    new Fokus(0x3fd78037eb1f6e73L, 0x3fe2f39071328f77L, 0x3fd78038408ba94dL, 0x3fe2f390948a3b3aL),
    new Fokus(0x3fd9f15f025d8f04L, 0x3fc2e6c665624830L, 0x3fd9f153d27553b7L, 0x3fc2e6c1c3dff904L),
    new Fokus(0xbfb8a850dc07ad5aL, 0xbfe4cad2cb3c761eL, 0xbfb8a7f9542927a1L, 0xbfe4cabf804325afL),
    new Fokus(0xbfb8e14614bf1bc8L, 0xbfe4ce220cd959f1L, 0xbfb8e0ee5969bd69L, 0xbfe4ce2acf5075bcL),
    new Fokus(0xbfb8cd7c660fcdb6L, 0xbfe4c8a151bc3338L, 0xbfb8ce35ac9d3e58L, 0xbfe4c8d25e798976L),
    new Fokus(0xbfba968a45754216L, 0x3feeab93ba85338dL, 0xbfba968a720f3e2cL, 0x3feeab93de718db5L),
    new Fokus(0xbfba9af8507adf0bL, 0xbfed8ecfa12bd3eaL, 0xbfba9af3e34e714bL, 0xbfed8ecf5f9cb47fL),
    new Fokus(0xbfbd261b8840cff8L, 0x3fef034d2b100beaL, 0xbfbd541c45c112f2L, 0x3fef03da8e5400afL),
    new Fokus(0xbfbc343138612c9aL, 0xbfecbb22a1d84321L, 0xbfbc343138144c44L, 0xbfecbb22a1b7adc2L),
    new Fokus(0xbfc076182ce9130dL, 0x3fefa1b7181d757aL, 0xbfc076182ce8ff3dL, 0x3fefa1b7181cfedcL),
    new Fokus(0xbfd4ca0553f5b072L, 0x3fe3fed5c89ca47aL, 0xbfd4c58da5e62dc3L, 0x3fe3fc4c9c059c40L),
    new Fokus(0xbfddccae5614a7a8L, 0x3fe2837fb8779751L, 0xbfddcca208422143L, 0x3fe283800624d024L),
    new Fokus(0xbfe1357904014463L, 0xbfe3972404fcc676L, 0xbfe1356f5afd9faeL, 0xbfe39742e8f78962L),
    new Fokus(0xbfe28f1f472a916bL, 0x3fdf44e4561c1b36L, 0xbfe28f1facbb4067L, 0x3fdf44e51cb6abfbL),
    new Fokus(0xbfe7ec3a53079688L, 0x3fc16f9c3aab7e52L, 0xbfe7ec14347a7a1eL, 0x3fc16f27219fd967L),
    new Fokus(0xbfe7c02a3f7be78eL, 0x3fc15f9ec2775c29L, 0xbfe7c1843cfab51eL, 0x3fc15e115c107967L),
    new Fokus(0xbfe9f648710f51f3L, 0xbfc796a0b5cd8194L, 0xbfe9f64aa7a54ca8L, 0xbfc79698ea47ed35L),
    new Fokus(0xbff2e30269e83ddeL, 0xbfd34a39866cd44aL, 0xbff2e30269e606a5L, 0xbfd34a39866e335aL),
    new Fokus(0xbff58539e292b17dL, 0x3fa898caa9bfac8eL, 0xbff58539494fc4cfL, 0x3fa898c664dac810L),
    new Fokus(0xbff594cb4f0f1e22L, 0x3fa6d64f327e56efL, 0xbff594cb606fe378L, 0x3fa6d64e6db7e95bL),
    new Fokus(0xbff597356fc03615L, 0x3fa6907633eda69aL, 0xbff5973df11cd306L, 0x3fa6909220592cb2L),
    new Fokus(0xbff653918d6b4849L, 0x3f91660fb2225999L, 0xbff653914f271365L, 0x3f9165fc59fb6d0eL),
    new Fokus(0xbff66e03c541b9baL, 0x3e916deab2201000L, 0xbff66e715be50ca4L, 0xbe937f1faab39800L),
    new Fokus(0xbff8e29b2312a0b8L, 0x3f1288de67060e8fL, 0xbff8e29b25d12815L, 0x3f1288ee1901fb61L),
    new Fokus(0xbffff1288031fa08L, 0x3ecb9484a52d86aaL, 0xbffff1287f8ee5b2L, 0x3ecbbb84a91c5b38L),
    new Fokus(0xbfe8c3f51bb1c2bbL, 0x3fbe73ce4c2e096bL, 0xbfe8c51694e923bcL, 0x3fbe77a480778fb9L)
    )
    
    
}

class Fokus(val a: Point, val b: Point) extends Viewport(Fokus.extractU(a, b), Fokus.extractA(a, b), Fokus.extractB(a, b)) {
  override def toString(): String = "Fokus: %s, %s".format(a, b)

  def this(a: Long, b: Long, c: Long, d: Long) = this(PointUtil.createPointByLongs(a, b), PointUtil.createPointByLongs(c, d))
}
