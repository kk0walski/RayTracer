package materials

import color.ColorRgb
import lights.PointLight
import tracing.HitInfo

class PerfectDiffuse(val color:ColorRgb) extends IMaterial {
  override def Radiance(light: PointLight, hit: HitInfo): ColorRgb = {
    val inDirectrion = (light.position - hit.hitPoint).normalize
    val diffuseFactor = inDirectrion*hit.normal
    if(diffuseFactor < 0) return ColorRgb.Black
    return light.color*this.color*diffuseFactor
  }
}
