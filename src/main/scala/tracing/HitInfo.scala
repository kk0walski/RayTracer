package tracing

import shapes.Shape
import vecmath.{Ray, Vector}

class HitInfo(var hitObject: Shape, var world: World, var normal:Vector, var hitPoint:Vector, var ray:Ray, var depht:Int) {
  def this() = this(null, null, Vector.Zero, Vector.Zero, null, 0)
}
