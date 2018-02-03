import java.awt.{BorderLayout, Color, Dimension}
import javax.swing.{JFrame, WindowConstants}

import cameras.Pinhole
import shapes.Traingle
import tracing.{Raytracer, World}
import vecmath.Vector

object Program {
  def main(args: Array[String]): Unit = {
    val world = new World(Color.CYAN)

    world.objects = new Traingle(new Vector(0.09541985, 0.4580153, 0), new Vector(0.09541985, 1.458015, 0), new Vector(1.09542, 0.4580153, 0), Color.YELLOW) :: world.objects


    val camera = new Pinhole(new Vector(1.221375, 0.7061068, 1.545801), new Vector(1.164122, 0.5343511, 0.4198475))

    val tracer = new Raytracer()

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
