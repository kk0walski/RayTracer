package tracing

import java.awt.image.BufferedImage
import java.awt.{Color, Dimension}

import cameras.ICamera
import color.ColorRgb
import vecmath.{Ray, Vector2}


class Raytracer(maxDeph:Int) {

  def raytrace(world: World, camera: ICamera, size: Dimension): BufferedImage = {
    val bmp = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB)

    for (x <- 0 until size.width)
      for (y <- 0 until size.height) {
        val pictureCoordinates = new Vector2(
          (x / size.width.toDouble) * 2 - 1,
          (y / size.height.toDouble) * 2 - 1
        )

        val ray = camera.GetRayTo(pictureCoordinates)

        bmp.setRGB(x, y, (StripColor(ShadeRay(world, ray, 0))).getRGB())
      }
    return bmp
  }

  def ShadeRay(world: World, ray: Ray, currentDepth:Int):ColorRgb = {
    if(currentDepth > maxDeph) return ColorRgb.Black
    val info = world.traceRay(ray)
    info.depht = currentDepth+1
    var finalColor = ColorRgb.Black

    if(info.hitObject == null){
      finalColor = new ColorRgb(world.backgroundColor)
      return finalColor
    }
    val material = info.hitObject.material

    return material.Shade(this, info)
  }
  def StripColor(rgb: ColorRgb): Color =
  {
    rgb.r = if(rgb.r < 0) 0 else if(rgb.r > 1) 1 else rgb.r;
    rgb.g = if(rgb.g < 0) 0 else if(rgb.g > 1) 1 else rgb.g;
    rgb.b = if(rgb.b < 0) 0 else if(rgb.b > 1) 1 else rgb.b;
    return new Color((rgb.r*255).toInt, (rgb.g*255).toInt, (rgb.b*255).toInt)
  }
}
