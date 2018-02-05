package materials

import color.ColorRgb
import tracing.{HitInfo, Raytracer}
import vecmath.Vector

import scala.util.control.Breaks.{break, breakable}

class Phong (materialColor:ColorRgb, diffuseCoef: Double, specular:Double, specularExponent:Double) extends IMaterial {
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
        var result = light.color * materialColor * diffuseFactor * diffuseCoef
        val phongFactor = PhongFactor(inDirection, hit.normal, -hit.ray.direction)

        if(phongFactor != 0)result += materialColor*specular*phongFactor

        totalColor += result
      }
    }
    return totalColor;
  }
  def PhongFactor(inDirection:Vector, normal:Vector, toCameraDirection:Vector):Double = {
    val reflected = Vector.reflect(inDirection, normal)
    val cosAngle = reflected*toCameraDirection

    if (cosAngle <= 0)return 0

    return Math.pow(cosAngle, specularExponent)
  }
}
