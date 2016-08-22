import operators.{Operators, Variables}

object VariablesX extends Variables[Double] {
  override def variables(implicit operators: Operators[Double]): Seq[operators.Variable] = Seq(new operators.Variable("x"))
}

object Starter {
  def main(args: Array[String]): Unit = {
    {
      val p = parser.Instances.doubleParser
      val t = p.apply("2^-x", VariablesX)
      t.foreach { term =>
        val d = term.apply {
          case "x" => 25
        }
        println(d)
      }
      println(t)
    }

    {
      import parser.Instances.booleanParser
      val p = booleanParser.apply("!false", new Variables[Boolean] {})
      println(p)
    }
  }
}
