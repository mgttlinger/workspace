import entities.FinishedContent
import entities.Syntax._
import entities.fractal.Mandelbrot
import entities.viewport._

case class Model() {
  val dimensions = Dimensions.screenHD

  val transform =
    new entities.viewport.Fokus(0x3fb9f74a2103c027L, 0x3fe441bdb7277199L, 0x3fba08bcceb6efe6L, 0x3fe440cf7c4a6d3aL)
      .withDimensions(dimensions)



  val content: Seq[FinishedContent] = Seq.empty
//    Seq(
//      transform
//        .withAntiAliasedFractal(Mandelbrot.RoughColoring(750))
//        .linearNormalized,
//      transform
//        .withAntiAliasedFractal(Mandelbrot.OrbitPoint(1500, -1.0, 0.0))
//        .linearNormalized
//    )
}
