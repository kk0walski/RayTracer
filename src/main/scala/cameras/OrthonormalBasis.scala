package cameras

import vecmath.Vector

class OrthonormalBasis(eye: Vector, lookAt: Vector, up: Vector) {
  var w = eye - lookAt
  w = w.normalize
  val v = w ** u
  u = u.normalize
  var u = up ** w

  def *(v: Vector) = u * v.x + this.v * v.y + w * v.z
}
