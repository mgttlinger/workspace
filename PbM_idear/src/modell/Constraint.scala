package modell

import parser.Parser
import term.operations.binary.Subtraction
import term.constant.Constant

class Constraint(m: Mass) {
  val l: Double = 1;
  val term = new Subtraction(Parser.parse("sqrt(x*x+y*y)", m.env), new Constant(l))
}