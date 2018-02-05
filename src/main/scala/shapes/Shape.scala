package shapes

import materials.IMaterial
import vecmath.{Ray, Vector}

trait Shape {
  var material: IMaterial

  def HitTest(ray: Ray, distance: Double, outNormal:Vector): (Boolean, Double, Vector)
}
