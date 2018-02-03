package shapes

import materials.IMaterial
import vecmath.{Ray, Vector}

trait Shape {
  val material: IMaterial

  def HitTest(ray: Ray, distance: Double, normal:Vector): (Boolean, Double, Vector)
}
