package cameras

import vecmath.{Ray, Vector, Vector2}

class Orthogonal(var eyePosition: Vector, var angle: Double, var cameraSize: Vector2) extends ICamera {
  override def GetRayTo(relativeLocation: Vector2): Ray = ???
}
