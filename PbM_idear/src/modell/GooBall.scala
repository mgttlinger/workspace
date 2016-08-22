package modell

import term.ITerm
import term.constant._
import term.operations.binary._

class GooBall(x0: Double, y0: Double, vx0: Double, vy0: Double, mass: Double, distance: Double, spring: Double)
	extends modell.Mass(x0, y0, vx0, vy0, mass) {

  def k = new Constant(spring)
  def d = new Constant(distance)
  
  var connections = List[GooBall]()
  
  def addConnection(input:GooBall) =
    connections = connections.+:(input)
    
  def delConnection(input:GooBall) = 
    connections = connections.filter(_ != input)
    
  def V = {
    var sum : ITerm = Constant.zero
    for(c <- connections){
      //val pot = parser.Parser.parse("k*(sqrt((xi-xj)^2+(i-yj)^2)-d)", env);
      val a = new Power(new Subtraction(x, c.x), Constant.two)
      val b = new Power(new Subtraction(y, c.y), Constant.two)
      val inner = new Power(new Addition(a, b), new Constant(0.5))
      val pot = new Multiplication(k, new Subtraction(inner, d))
      sum = new Addition(sum, pot)
    }
    sum
  }
  
  def T = parser.Parser.parse("m*(vx*vx+vy+vy)/2", env)
}