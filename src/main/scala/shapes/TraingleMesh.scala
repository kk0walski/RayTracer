package shapes

import color.ColorRgb
import materials.{IMaterial, PerfectDiffuse}
import vecmath.{Ray, Vector}

class TraingleMesh(var triangles:List[Traingle], override var material: IMaterial) extends Shape {

  def this(traingles:List[Traingle]) = this(traingles, new PerfectDiffuse(ColorRgb.Black))

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
