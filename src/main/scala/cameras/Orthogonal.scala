package cameras

import vecmath.{Ray, Vector, Vector2}

class Orthogonal(var origin: Vector, var lookAt: Vector, var up: Vector,  var cameraSize: Vector2) extends ICamera {

  val onb = new OrthonormalBasis(origin, lookAt, up)

  override def GetRayTo(relativeLocation: Vector2): Ray = {
    return new Ray(rayDirection(relativeLocation),(lookAt - origin).normalize)
  }

  def rayDirection(v: Vector2): Vector = {
    return onb * (new Vector(v.x*10, v.y*10, 0))
  }
}

object Orthogonal {
  def up(position: Vector, lookAt: Vector): Vector = {
    val direction = lookAt - position
    val wart = (0 - direction.x - direction.y) / direction.z
    return new Vector(1, 1, wart)
  }
}