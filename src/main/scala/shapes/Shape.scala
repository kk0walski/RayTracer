package shapes

import java.awt.Color

import vecmath.Ray

trait Shape {
  val color: Color

  def HitTest(ray: Ray, minDistance: Double): (Boolean, Double)
}
