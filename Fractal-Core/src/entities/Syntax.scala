package entities

import entities.accumulator.Arithmetic
import entities.color.Color
import entities.content._
import entities.image.Image

object Syntax{

  implicit class EnrichedViewport(val viewport:Viewport) extends AnyVal {
    def withDimensions(dim:Dimensions): Transform = new Transform(viewport, dim)
  }

  implicit class EnrichedTransform(val transform:Transform) extends AnyVal {
    def withFractal(fractal:Fractal):Content = FractalContent(fractal, transform)

    def withAntiAliasedFractal(fractal: Fractal, accu: Accumulator = Arithmetic, multi: Int = 5): Content =
      AntiAliasedFractalContent(fractal, transform, accu, multi)

    def withBuddhaBrot(sourceTransform: Transform = transform, maxIteration: Int = 250) =
      BuddahBrot(transform, sourceTransform, maxIteration)
  }

  implicit class EnrichedContent(val content:Content) extends AnyVal{
    def cached: CachedContent = content match{
      case cached:CachedContent => cached
      case _ => new CachedContent(content)
    }
    def linearNormalized: Content with Normalized = LinearNormalizedContent(content.cached)
    def strongNormalized: Content with Normalized = StrongNormalizedContent(content.cached)
  }

  implicit class EnrichedFinishedContent(val content:FinishedContent) extends AnyVal{
    def withColor(color:Color) = new Image(content, color)
  }

  implicit class EnrichedImage(val image:Image) extends AnyVal {
    def save(file: java.io.File):Unit = {
      if(file.getParentFile != null)
        file.getParentFile.mkdirs()
      javax.imageio.ImageIO.write(image.buffer, "png", file)
    }
    def save(fileName:String):Unit = save(new java.io.File(fileName))
  }
}