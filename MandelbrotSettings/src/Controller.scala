import java.awt.event.{ActionEvent, ActionListener}

import entities.Syntax._
import entities.color.HSV
import entities.content.{Content, Spectrum}
import entities.viewport.Dimensions
import operators.{Operators, Variables}
import parser.Instances.{doubleParser => parser}


case class Controller(model: Model, view: View) extends Object with ActionListener {

  def state = s"ERG: ${view.erg.getText()}, H: ${view.H.getText()}, S: ${view.S.getText()}, V: ${view.V.getText()}"

  val contentVariables = new Variables[Double] {
    override def variables(implicit operators: Operators[Double]): Seq[operators.Variable] = model.content.zipWithIndex.map {
      case (content, index) =>
        operators.Variable((index + 'A').toChar.toString)
    }
  }

  val colorVariables = new Variables[Double] {
    override def variables(implicit operators: Operators[Double]): Seq[operators.Variable] =
      Seq(operators.Variable("l"))
  }

  view.addButtonListener(this)

  override def actionPerformed(e: ActionEvent): Unit = {

    lazy val color = new HSV {
      val funH = parser(view.H.getText(), colorVariables).getOrElse(throw new RuntimeException("parse Error H"))
      val funS = parser(view.S.getText(), colorVariables).getOrElse(throw new RuntimeException("parse Error S"))
      val funV = parser(view.V.getText(), colorVariables).getOrElse(throw new RuntimeException("parse Error V"))

      override def H(lambda: Double): Double = funH{
        case "l" => lambda
      }.max(0).min(360)
      override def S(lambda: Double): Double = funS{
        case "l" => lambda
      }.max(0).min(1)
      override def V(lambda: Double): Double = funV{
        case "l" => lambda
      }.max(0).min(1)
    }

    parser(view.erg.getText(), contentVariables) match {
      case Some(parsed) =>
//        val content = new Content {
//          override def apply(x: Int, y: Int): Double = {
//            parsed.apply {
//              case s if s.length == 1 && s.head >= 'A' && s.head <='Z' =>
//                model.content(s.head - 'A')(x, y)
//            }
//          }
//
//          override def dimensions: Dimensions = model.dimensions
//        }.linearNormalized

        val content = Spectrum


        new ImagePanel("DEFAUL COLOR VGL! "+state, content.withColor(HSV.MonoColor.Blue).buffer)
        new ImagePanel("DEFAUL2 COLOR VGL! "+state, content.withColor(HSV.MonoColor2.Blue).buffer)
        new ImagePanel(state, content.withColor(color).buffer)

      case None => println("parsing error ERG")
    }

  }
}
