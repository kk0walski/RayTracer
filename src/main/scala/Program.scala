import java.awt.{BorderLayout, Color, Dimension}
import javax.swing.{JFrame, WindowConstants}

import cameras.Orthogonal
import shapes.Sphere
import tracing.{Raytracer, World}
import vecmath.{Vector, Vector2}

object Program {
  def main(args: Array[String]): Unit = {
    val world = new World(Color.CYAN)

    world.objects = new Sphere(new Vector(-2.5, 0, 0), 2, Color.RED) :: new Sphere(new Vector(2.5, 0, 0), 2, Color.GREEN) :: new Sphere(new Vector(0, 0, 2.5), 2, Color.BLUE) :: world.objects

    val camera = new Orthogonal(new Vector(0, 0, -5), new Vector(0, 0, 0), new Vector2(5, 5), new Vector(0, 1, 0.29))

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
