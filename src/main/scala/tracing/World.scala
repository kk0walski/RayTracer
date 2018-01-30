package tracing

import java.awt.Color

import shapes.Shape
import vecmath.Ray

class World(private var backgroundColor: Color = Color.BLUE) {
  var objects: List[Shape]

  def hitInfo(ray: Ray): HitInfo = {
    val result = new HitInfo()
    var minimalDistance = Ray.Huge
    var hitDistance = 0.0
    var test: (Boolean, Double) = (false, Double.MaxValue)

    for (obj <- objects) {
      test = obj.HitTest(ray, hitDistance)
      hitDistance = test._2
      if (test._1 && test._2 < minimalDistance) {
        minimalDistance = hitDistance
        result.hitObject = true
        result.color = obj.color
      }
    }
    return result
  }
}
