package shapes

import java.awt.Color

import vecmath.{Ray, Vector}

class Plane(point: Vector, normal: Vector, override val color: Color) extends Shape {
  override def HitTest(ray: Ray, minDistance: Double): (Boolean, Double) = {
    val t = ((point - ray.origin) * normal) / (ray.direction * normal)
    if (t > Ray.Epsilon) {
      return (true, t)
    }
    return (false, Double.MaxValue)
  }
}