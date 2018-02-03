package tracing

import shapes.Shape
import vecmath.{Ray, Vector}

class HitInfo(var hitObject: Shape, var world: World, var normal:Vector, var hitPoint:Vector, var ray:Ray) {
  def this() = this(null, null, Vector.Zero, Vector.Zero, null)
}
