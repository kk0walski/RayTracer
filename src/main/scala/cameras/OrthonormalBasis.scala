package cameras

import vecmath.Vector

class OrthonormalBasis(eye: Vector, lookAt: Vector, up: Vector) {
  val v = w ** u
  w = w.normalize
  var w = eye - lookAt
  u = u.normalize
  var u = up ** w

  def *(v: Vector) = u * v.x + this.v * v.y + w * v.z
}
