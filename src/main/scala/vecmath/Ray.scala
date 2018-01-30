package vecmath

class Ray(val origin: Vector, val direction: Vector)


object Ray {
  val Epsilon = 0.00001
  val Huge = Double.MaxValue

  // Create a Ray
  def apply(origin: Vector, direction: Vector) = new Ray(origin, direction)

  // Extractor method
  def unapply(v: Vector) = Some(v.x, v.y, v.z)
}