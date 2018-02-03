package shapes

import java.awt.Color

import vecmath.{Ray, Vector}

trait Shape {
  val color: Color

  def HitTest(ray: Ray, distance: Double, normal:Vector): (Boolean, Double, Vector)
}
