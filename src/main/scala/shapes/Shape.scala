package shapes

import materials.IMaterial
import vecmath.{Ray, Vector}

trait Shape {
  val material: IMaterial

  def HitTest(ray: Ray, distance: Double, outNormal:Vector): (Boolean, Double, Vector)
}
