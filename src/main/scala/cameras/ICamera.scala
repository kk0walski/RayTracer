package cameras

import vecmath.{Ray, Vector2}

trait ICamera {
  def GetRayTo(relativeLocation: Vector2): Ray
}
