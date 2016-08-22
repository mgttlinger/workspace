package entities

import term.IFunction

class ParsedFeld(val tx: IFunction, val ty: IFunction) extends VektorFeld {

  def transformX(x: Double, y: Double): Double = {
    tx.getEnvironment().getVariable("x").assign(x)
    tx.getEnvironment().getVariable("y").assign(y)
    tx.evaluate()
  }

  def transformY(x: Double, y: Double): Double = {
    ty.getEnvironment().getVariable("x").assign(x)
    ty.getEnvironment().getVariable("y").assign(y)
    ty.evaluate()
  }

}