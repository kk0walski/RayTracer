package shapes

import java.awt.Color

import vecmath.{Ray, Vector}

class Sphere(val center: Vector, val radius: Float, override val color: Color) extends Shape {
  override def HitTest(ray: Ray, minDistance: Double): (Boolean, Double) = {
    var t = 0.0
    val distance = ray.origin - center

    val a = ray.direction.lengthSquared
    val b = (distance * 2) * ray.direction
    val c = distance.lengthSquared - (radius * radius)
    val disc = b * b - 4 * a * c

    if (disc < 0) return (false, -1.0)

    val discSq = Math.sqrt(disc)
    val denom = 2 * a

    t = (-b - discSq) / denom
    if (t < Ray.Epsilon) t = (-b + discSq) / denom
    if (t < Ray.Epsilon) return (false, -1.0)

    return (true, t)
  }
}
