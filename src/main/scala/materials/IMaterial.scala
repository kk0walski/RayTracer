package materials

import color.ColorRgb
import tracing.{HitInfo, Raytracer}

trait IMaterial {
  def Shade(tracer:Raytracer, hit:HitInfo):ColorRgb
}
