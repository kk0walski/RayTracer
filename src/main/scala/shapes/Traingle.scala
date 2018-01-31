package shapes

import java.awt.Color

import vecmath.{Ray, Vector}

class Traingle(x: Vector, y: Vector, z: Vector, override val color: Color) extends Shape {

  override def HitTest(ray: Ray, minDistance: Double): (Boolean, Double) = {
    val u = y - x;
    val v = z - x;
    val n = u ** v
    val t = ((x - ray.origin) * n) / (ray.direction * n)
    if (t > Ray.Epsilon) {
      val p = ray.origin + ray.direction * t
      return (isInTrainle(p, x, y, z), t)
    }
    return (false, Double.MaxValue)
  }

  def isInTrainle(p: Vector, x: Vector, y: Vector, z: Vector): Boolean = {
    var reasult = (((p - x) ** (z - x)).length + ((p - x) ** (y - x)).length + ((p - y) ** (z - y)).length) - ((z - x) ** (y - x)).length
    return reasult <= Ray.Epsilon
  }
}
