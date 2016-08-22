package meta

import MVC.model._
import MVC.view._

object GUIStarter extends App {
  val model  = new Model( )
  new View(model)
}


