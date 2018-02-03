import java.awt.{BorderLayout, Color, Dimension}
import javax.swing.{JFrame, WindowConstants}

import cameras.Pinhole
import color.ColorRgb
import lights.PointLight
import materials.PerfectDiffuse
import shapes.{Plane, Sphere}
import tracing.{Raytracer, World}
import vecmath.Vector

object Program {
  def main(args: Array[String]): Unit = {
    val world = new World(Color.CYAN)

    val redMat = new PerfectDiffuse(new ColorRgb(Color.RED))
    val greenMat = new PerfectDiffuse(new ColorRgb(Color.GREEN))
    val blueMat = new PerfectDiffuse(new ColorRgb(Color.BLUE))
    val grayMat = new PerfectDiffuse(new ColorRgb(Color.GRAY))

    world.objects = new Sphere(new Vector(-4, 0, 0), 2, redMat) :: new Sphere(new Vector(4, 0, 0), 2, greenMat) :: new Sphere(new Vector(0, 0, 3), 2, blueMat) :: world.objects
    world.objects = new Plane(new Vector(0, -2, 0), new Vector(0, 1, 0), grayMat) :: world.objects
    world.lights = new PointLight(new Vector(0,5,-5), new ColorRgb(Color.WHITE)) :: world.lights

    val tracer = new Raytracer()
    val camera = new Pinhole(new Vector(0, 1, -8), new Vector(0, 0, 0), new Vector(0, -1, 0), 1)
    val image = tracer.raytrace(world, camera, new Dimension(800, 600))

    val frame = new JFrame()
    frame.setLayout(new BorderLayout())
    frame.setSize(new Dimension(800, 600))
    val panel = new ImagePanel(image)
    frame.add(panel, BorderLayout.CENTER)
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
    frame.setVisible(true)
  }
}
