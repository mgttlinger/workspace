package modell

class GooBall(x0: Double, y0: Double, m: Double) extends MassPoint(x0, y0, 0, 0, m) {
  def this(x0: Double, y0: Double) = this(x0, y0, 1)

  val damping = 0.99
  var xa, ya = 0d
  var xl = x0
  var yl = y0

  def reset() = {
    xa = 0
    ya = System.g
  }

  def step(dt: Double) = {
    val tempx = x
    val tempy = y
    x = 2 * x - xl + xa * dt * dt
    y = 2 * y - yl + ya * dt * dt
    yl = tempy
    xl = tempx

    /*x = x+dt*xv +dt*dt/2*xa
    y = y+dt*yv +dt*dt/2*ya
    
    xv = xv + xa*dt
    yv = yv + ya*dt*/

    /*xp = x
	yp = y
    xv = xv * damping + xa * dt
    yv = yv * damping + ya * dt
    x += xv * dt
    y += yv * dt*/
  }

  def accelerate(inx: Double, iny: Double) = {
    xa += inx
    ya += iny
  }
}