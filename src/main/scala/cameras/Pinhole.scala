package cameras

import vecmath.{Ray, Vector, Vector2}

class Pinhole(origin: Vector, lookAt: Vector, up: Vector, distance: Double) extends ICamera {
  val onb = new OrthonormalBasis(origin, lookAt, up)

  override def GetRayTo(relativeLocation: Vector2): Ray = {
    return new Ray(origin, rayDirection(relativeLocation))
  }

  def rayDirection(v: Vector2): Vector = {
    return onb * (new Vector(v.x, v.y, -distance))
  }
}
