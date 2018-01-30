package shapes

import java.awt.Color

import vecmath.Ray

abstract class Shape {
  val color: Color

  def HitTest(ray: Ray, minDistance: Double): (Boolean, Double)
}
