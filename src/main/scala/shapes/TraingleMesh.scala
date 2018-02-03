package shapes

import materials.IMaterial
import vecmath.{Ray, Vector}

class TraingleMesh(triangles:List[Traingle], override val material: IMaterial) extends Shape {
  override def HitTest(ray: Ray, distance: Double, normal:Vector): (Boolean, Double, Vector) = {
    var result = false
    var minimalDistance = Ray.Huge
    var hitDistance = 0.0
    var normal = Vector.Zero
    var test = (false, Double.MaxValue, Vector.Zero)

    for (obj <- triangles) {
      test = obj.HitTest(ray, distance, normal)
      hitDistance = test._2
      normal = test._3
      if (test._1 && test._2 < minimalDistance) {
        minimalDistance = hitDistance
        result = true
      }
    }
    return (result, minimalDistance, normal)
  }
}
