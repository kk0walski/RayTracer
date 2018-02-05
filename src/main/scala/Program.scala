import java.awt.{BorderLayout, Color, Dimension}
import javax.swing.{JFrame, WindowConstants}

import cameras.Orthogonal
import color.ColorRgb
import lights.PointLight
import materials.Phong
import shapes.Sphere
import tracing.{Raytracer, World}
import vecmath.{Vector, Vector2}

object Program {
  def main(args: Array[String]): Unit = {
    val world = new World(Color.CYAN)

    val redMat = new Phong(new ColorRgb(new Color(240,128,128)), 0.8, 1, 30)
    val greenMat = new Phong(new ColorRgb(new Color(144,238,144)), 0.8, 1, 30)
    val blueMat = new Phong(new ColorRgb(new Color(173,216,230)), 0.8, 1, 30)
    val grayMat = new Phong(new ColorRgb(Color.GRAY), 0.8, 1, 30)

    world.objects = new Sphere(new Vector(-4, 0, 0), 2, redMat) :: new Sphere(new Vector(4, 0, 0), 2, greenMat) :: new Sphere(new Vector(0, 0, 3), 2, blueMat) :: world.objects
    //world.objects = new Plane(new Vector(0, -2, 0), new Vector(0, 1, 0), grayMat) :: world.objects
    world.lights = new PointLight(new Vector(0,5,-5), new ColorRgb(Color.WHITE)) :: world.lights

    val tracer = new Raytracer()
    val camera = new Orthogonal(new Vector(0, 1, -8), new Vector(0, 0, 0), new Vector(0, -1, 0), new Vector2(800, 600))
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
