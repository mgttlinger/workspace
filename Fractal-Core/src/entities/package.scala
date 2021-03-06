package object entities {
  type Fractal = entities.fractal.Fractal
  type Dimensions = entities.viewport.Dimensions
  val Dimensions = entities.viewport.Dimensions
  type Accumulator = entities.accumulator.Accumulator
  type Viewport = entities.viewport.Viewport
  type Transform = entities.viewport.Transform
  type Content = entities.content.Content
  type FinishedContent = Content with entities.content.Normalized
}
