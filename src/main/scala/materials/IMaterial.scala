package materials

import color.ColorRgb
import lights.PointLight
import tracing.HitInfo

trait IMaterial {
  def Radiance(light:PointLight, hit:HitInfo):ColorRgb
}
