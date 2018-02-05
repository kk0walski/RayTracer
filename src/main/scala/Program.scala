import java.awt.{BorderLayout, Color, Dimension}
import javax.swing.{JFrame, WindowConstants}

import cameras.Pinhole
import color.ColorRgb
import lights.PointLight
import materials.Reflective
import shapes.{Plane, Sphere}
import tracing.{Raytracer, World}
import vecmath.Vector

object Program {
  def main(args: Array[String]): Unit = {
    val world = new World(Color.CYAN)

    val redMat = new Reflective(new ColorRgb(new Color(240,128,128)), 0.4, 1, 300, 1)
    val greenMat = new Reflective(new ColorRgb(new Color(144,238,144)), 0.4, 1, 300, 1)
    val blueMat = new Reflective(new ColorRgb(new Color(173,216,230)), 0.4, 1, 300, 1)
    val grayMat = new Reflective(new ColorRgb(Color.GRAY), 0.4, 1, 300, 0.6)

    world.objects = new Sphere(new Vector(-4, 0, 0), 2, redMat) :: new Sphere(new Vector(4, 0, 0), 2, greenMat) :: new Sphere(new Vector(0, 0, 3), 2, blueMat) :: world.objects
    world.objects = new Plane(new Vector(0, -2, 0), new Vector(0, 1, 0), grayMat) :: world.objects
    world.lights = new PointLight(new Vector(0,5,-10), new ColorRgb(Color.WHITE)) :: world.lights

    val tracer = new Raytracer(5)
    val camera = new Pinhole(new Vector(0, 1, -8), new Vector(0, 0, 0), new Vector(0, -1, 0), 1)
    val image = tracer.raytrace(world, camera, new Dimension(1920, 1200))

    val frame = new JFrame()
    frame.setLayout(new BorderLayout())
    frame.setSize(new Dimension(800, 600))
    val panel = new ImagePanel(image)
    frame.add(panel, BorderLayout.CENTER)
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
    frame.setVisible(true)
  }
}
