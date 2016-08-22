package modell

import term.ITerm
import term.constant._
import term.operations.binary._
import term.variable._

object System {
  def getAllVariables(tree: ITerm): List[ITerm] = {
    tree match {
      case v: Variable => return List(v)
      case _ => {
        var l = List[ITerm]()
        val t = tree.getTerms()
        if (t != null)
          for (i <- 0 until t.length) {
            l = l ++ getAllVariables(t(i))
          }
        return l
      }
    }
  }

  def copy(tree: ITerm, except: Variable): ITerm = {
    var t = tree
    for (v <- getAllVariables(tree)) {
      if (v != except)
        v match {
          case v2: Variable =>
            v.asInstanceOf[Variable]
            t = t substitude (v, v2.clone())
        }
    }
    t
  }

  def solveFor(tree: ITerm, x: Variable) {
    val summand = new Division(tree, tree.derivation(x))
    //println(summand.toString())
    x.assign(0)
    for (i <- 0 until 100) {
      /*println(x.evaluate())
      println("summand: "+summand.evaluate())*/
      x.assign(x.evaluate() - summand.evaluate())
    }
    return x
  }
}

class System {
  var goos = List[GooBall]()
  def addGoo(input: GooBall) =
    goos = input +: goos

  def delGoo(input: GooBall) =
    goos = goos filter (_ != input)

  def L = {
    var sum: ITerm = Constant.zero
    for (c <- goos) {
      sum = new Addition(sum, c.L)
    }
    sum
  }

  def solve(p: Variable, v: Variable): Variable = {
    val a = new Variable("a");

    val L = this L;
    val first = L derivation (v)
    val second = L derivation (p)
    val first_sub = first substitude (v, a) substitude (v, p)

    val EL = new Subtraction(first_sub, second)
    val cEL = System.copy(EL, a)
    System.solveFor(cEL, a);
    a
  }
}