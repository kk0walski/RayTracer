package shapes

import materials.IMaterial
import vecmath.{Ray, Vector}

class Sphere(val center: Vector, val radius: Float, override var material: IMaterial) extends Shape {
  override def HitTest(ray: Ray, distance: Double, normal:Vector): (Boolean, Double, Vector) = {
    var t = 0.0
    val distance = ray.origin - center

    val a = ray.direction.lengthSquared
    val b = (distance * 2) * ray.direction
    val c = distance.lengthSquared - (radius * radius)
    val disc = b * b - 4 * a * c

    if (disc < 0) return (false, Double.MaxValue, Vector.Zero)

    val discSq = Math.sqrt(disc)
    val denom = 2 * a

    t = (-b - discSq) / denom
    if (t < Ray.Epsilon) t = (-b + discSq) / denom
    if (t < Ray.Epsilon) return (false, Double.MaxValue, Vector.Zero)

    val hitPoint = (ray.origin + ray.direction * t)
    val outNormal = (hitPoint - center).normalize
    return (true, t, outNormal)
  }
}
