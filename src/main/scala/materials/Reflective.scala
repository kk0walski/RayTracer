package materials

import color.ColorRgb
import tracing.{HitInfo, Raytracer}
import vecmath.{Ray, Vector}

class Reflective (materialColor:ColorRgb, diffuseCoef: Double, specular:Double, specularExponent:Double, reflectivity:Double) extends IMaterial {

  val direct = new Phong(materialColor, diffuseCoef, specular, specularExponent)
  val reflectiveColor = materialColor
  override def Shade(tracer: Raytracer, hit: HitInfo): ColorRgb = {
    val toCameraDirection = -hit.ray.direction

    var radiance = direct.Shade(tracer, hit)
    val reflectionDirection = Vector.reflect(toCameraDirection, hit.normal)
    val reflectedRay = new Ray(hit.hitPoint, reflectionDirection)

    radiance += tracer.ShadeRay(hit.world, reflectedRay, hit.depht)* materialColor * reflectivity

    return radiance
  }
}
