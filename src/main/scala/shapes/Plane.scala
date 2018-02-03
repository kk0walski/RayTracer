package shapes

import materials.IMaterial
import vecmath.{Ray, Vector}

class Plane(point: Vector, normal: Vector, override val material: IMaterial) extends Shape {
  override def HitTest(ray: Ray, distance: Double, outNormal:Vector): (Boolean, Double, Vector) = {
    val t = ((point - ray.origin) * normal) / (ray.direction * normal)
    if (t > Ray.Epsilon) {
      return (true, t, normal)
    }
    return (false, Double.MaxValue, Vector.Zero)
  }
}