package materials

import color.ColorRgb
import lights.PointLight
import tracing.HitInfo
import vecmath.Vector

class Phong (materialColor:ColorRgb, diffuseCoef: Double, specular:Double, specularExponent:Double) extends IMaterial {
  override def Radiance(light: PointLight, hit: HitInfo): ColorRgb = {
    val inDirection = (light.position - hit.hitPoint).normalize
    val diffuseFactor = inDirection*hit.normal
    var result = ColorRgb.Black

    if(diffuseFactor < 0) return result
    result = light.color * materialColor * diffuseFactor * diffuseCoef
    val phongFactor = PhongFactor(inDirection, hit.normal, -hit.ray.direction)

    if(phongFactor != 0)result += materialColor*specular*phongFactor
    return result
  }
  def PhongFactor(inDirection:Vector, normal:Vector, toCameraDirection:Vector):Double = {
    val reflected = Vector.reflect(inDirection, normal)
    val cosAngle = reflected*toCameraDirection

    if (cosAngle <= 0)return 0

    return Math.pow(cosAngle, specularExponent)
  }
}
