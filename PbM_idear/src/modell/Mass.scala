package modell

import parser.environment.Environment
import term.variable.Variable
import term.constant.Constant
import term._
import term.operations.binary.Subtraction
import parser.Parser

abstract class Mass(x0: Double, y0: Double, vx0: Double, vy0: Double, mass: Double) {
  val x = new Variable(x0, "x")
  val y = new Variable(y0, "y")
  val vx = new Variable(vx0, "vx")
  val vy = new Variable(vy0, "vy")
  val m = new Constant(mass, "m")
  val env: Environment = new Environment(
    Array[Variable](x, y, vx, vy),
    Array[Constant](Constant.g, m))

  def V: ITerm
  def T: ITerm
  def L: ITerm = new Subtraction(T, V)
}