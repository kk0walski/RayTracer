package shapes

import java.awt.Color

import vecmath.Ray

class TraingleMesh(triangles:List[Traingle], override val color:Color) extends Shape {
  override def HitTest(ray: Ray, minDistance: Double): (Boolean, Double) = {
    var result = false
    var minimalDistance = Ray.Huge
    var hitDistance = 0.0
    var test = (false, Double.MaxValue)

    for (obj <- triangles) {
      test = obj.HitTest(ray, hitDistance)
      hitDistance = test._2
      if (test._1 && test._2 < minimalDistance) {
        minimalDistance = hitDistance
        result = true
      }
    }
    return (result, minimalDistance)
  }
}
