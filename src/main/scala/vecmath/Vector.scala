package vecmath

class Vector(val x: Double, val y: Double, val z: Double) {
  // Add two vectors
  def +(v: Vector) = new Vector(x + v.x, y + v.y, z + v.z)

  // Subtract two vectors
  def -(v: Vector) = new Vector(x - v.x, y - v.y, z - v.z)

  // Scale vector
  def *(f: Double) = new Vector(x * f, y * f, z * f)

  // Unary minus
  def unary_- = new Vector(-x, -y, -z)

  // Cross product
  def **(v: Vector) = new Vector(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x)

  // Normalize
  def normalize = this / length

  def /(f: Double) = new Vector(x / f, y / f, z / f)

  // Length
  def length = math.sqrt(lengthSquared)

  def lengthSquared = this * this

  // Dot product
  def *(v: Vector) = x * v.x + y * v.y + z * v.z

  // Get an element by index
  def apply(index: Int): Double = index match {
    case 0 => x
    case 1 => y
    case 2 => z
    case _ => throw new IndexOutOfBoundsException(index.toString)
  }

  override def toString = "Vector(%g, %g, %g)".format(x, y, z)
}
