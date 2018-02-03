package shapes

import java.awt.Color

import vecmath.{Ray, Vector}

class Plane(point: Vector, normal: Vector, override val color: Color) extends Shape {
  override def HitTest(ray: Ray, distance: Double, normal:Vector): (Boolean, Double, Vector) = {
    val t = ((point - ray.origin) * normal) / (ray.direction * normal)
    if (t > Ray.Epsilon) {
      return (true, t, normal)
    }
    return (false, Double.MaxValue, Vector.Zero)
  }
}