package cameras

import vecmath.{Ray, Vector, Vector2}

class Pinhole(origin: Vector, lookAt: Vector, distance: Double) extends ICamera {
  val up = -Pinhole.getUp(origin, lookAt)
  val onb = new OrthonormalBasis(origin, lookAt, up)

  override def GetRayTo(relativeLocation: Vector2): Ray = {
    return new Ray(origin, rayDirection(relativeLocation))
  }

  def rayDirection(v: Vector2): Vector = {
    return onb * (new Vector(v.x, v.y, -distance))
  }
}


object Pinhole {
  def getUp(origin: Vector, lookAt: Vector): Vector = {
    val direction = lookAt - origin
    if (direction.x != direction.y || direction.y != direction.z) {
      return direction ** (new Vector(direction.z, direction.x, direction.y))
    }
    else return direction ** Vector.XAxis
  }
}