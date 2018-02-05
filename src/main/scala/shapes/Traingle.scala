package shapes

import java.util.Scanner

import color.ColorRgb
import materials.{IMaterial, PerfectDiffuse}
import vecmath.{Ray, Vector}

class Traingle(var x: Vector, var y: Vector, var z: Vector, override var material: IMaterial) extends Shape {

  def this(x:Vector, y:Vector, z:Vector) = this(x,y,z, new PerfectDiffuse(ColorRgb.Black))

  override def HitTest(ray: Ray, distance: Double, normal:Vector): (Boolean, Double, Vector) = {
    val n = (y - x) ** (z - x)
    val t = ((x - ray.origin) * n) / (ray.direction * n)
    if (t > Ray.Epsilon) {
      val p = ray.origin + ray.direction * t
      return (isInTrainle(p, x, y, z), t, n)
    }
    return (false, Double.MaxValue, Vector.Zero)
  }

  def isInTrainle(p: Vector, x: Vector, y: Vector, z: Vector): Boolean = {
    //Sprawdzenie czy suma pól trójkątów xpz xpy oraz pyz jest równa polu trójkąta xyz
    var reasult = (((p - x) ** (z - x)).length + ((p - x) ** (y - x)).length + ((p - y) ** (z - y)).length) - ((z - x) ** (y - x)).length
    return reasult <= Ray.Epsilon
  }
}
object Traingle{
  val Zero = new Traingle(Vector.Zero, Vector.Zero, Vector.Zero, new PerfectDiffuse(color.ColorRgb.Black))

  def readFromFile(s:Scanner, vertices:Array[Vector]): Traingle ={
    val i1 = s.nextInt()
    val i2 = s.nextInt()
    val i3 = s.nextInt()
    return  new Traingle(vertices(i1), vertices(i2), vertices(i3),new PerfectDiffuse(color.ColorRgb.Black))
  }
}
