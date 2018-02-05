package materials

import color.ColorRgb
import tracing.{HitInfo, Raytracer}

import scala.util.control.Breaks._

class PerfectDiffuse(val color:ColorRgb) extends IMaterial {
  override def Shade(tracer:Raytracer, hit: HitInfo): ColorRgb =
  {
    var totalColor = ColorRgb.Black;
    for (light <- hit.world.lights)
    {
      breakable{
        val inDirection = (light.position - hit.hitPoint).normalize;
        val diffuseFactor = inDirection*hit.normal
        if (diffuseFactor < 0) { break(); }
        if (hit.world.anyObstacleBetween(hit.hitPoint, light.position))
        { break(); }
        totalColor += light.color * color * diffuseFactor;
      }
    }
    return totalColor;
  }

}
