package tracing

import java.awt.image.BufferedImage
import java.awt.{Color, Dimension}

import cameras.ICamera
import vecmath.Vector2


class Raytracer {

  def raytrace(world: World, camera: ICamera, size: Dimension): BufferedImage = {
    val bmp = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB)

    for (x <- 0 until size.width)
      for (y <- 0 until size.height) {
        val pictureCoordinates = new Vector2(
          (x / size.width.toDouble) * 2 - 1,
          (y / size.height.toDouble) * 2 - 1
        )

        val ray = camera.GetRayTo(pictureCoordinates)
        val info = world.traceRay(ray)

        var color = Color.BLUE
        if (info.hitObject == null) {
          color = info.world.backgroundColor
        }
        else {
          color = world.backgroundColor
        }
        bmp.setRGB(x, y, color.getRGB)
      }
    return bmp
  }
}
