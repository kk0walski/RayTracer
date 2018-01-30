import java.awt.Color

import cameras.{Orthogonal, Size}
import shapes.Sphere
import tracing.{Raytracer, World}
import vecmath.{Vector, Vector2}

object Program {
  def main(args: Array[String]): Unit = {
    val world = new World()

    world.objects = new Sphere(new Vector(-2.5, 0, 0), 2, Color.RED) :: new Sphere(new Vector(2.5, 0, 0), 2, Color.RED) :: new Sphere(new Vector(0, 0, 2.5), 2, Color.RED) :: world.objects

    val camera = new Orthogonal(new Vector(0, 0, -5), 0, new Vector2(5, 5))

    val tracer = new Raytracer()

    val image = tracer.raytrace(world, camera, new Size(256, 256))
  }
}
