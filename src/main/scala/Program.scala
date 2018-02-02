import java.awt.{BorderLayout, Color, Dimension}
import javax.swing.{JFrame, WindowConstants}

import cameras.Pinhole
import shapes.{Plane, Sphere}
import tracing.{Raytracer, World}
import vecmath.Vector

object Program {
  def main(args: Array[String]): Unit = {
    val world = new World(Color.CYAN)

    world.objects = new Sphere(new Vector(-4, 0, 0), 2, Color.RED) :: new Sphere(new Vector(4, 0, 0), 2, Color.GREEN) :: new Sphere(new Vector(0, 0, 3), 2, Color.BLUE) :: world.objects
    world.objects = new Plane(new Vector(0, -2, 0), new Vector(0, 1, 0), Color.GRAY) :: world.objects

    val camera = new Pinhole(new Vector(0, 1, -8), new Vector(0, 0, 0), 1)

    val tracer = new Raytracer()

    val image = tracer.raytrace(world, camera, new Dimension(256, 256))

    val frame = new JFrame()
    frame.setLayout(new BorderLayout())
    frame.setSize(new Dimension(800, 600))
    val panel = new ImagePanel(image)
    frame.add(panel, BorderLayout.CENTER)
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
    frame.setVisible(true)
  }
}
