package cameras

import vecmath.Vector

class OrthonormalBasis(eye: Vector, lookAt: Vector, up: Vector) {
  var w = eye - lookAt
  w = w.normalize
  var u = up ** w
  u = u.normalize
  val v = w ** u

  def *(v: Vector) = u * v.x + this.v * v.y + w * v.z
}
