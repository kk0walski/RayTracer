package tracing

import java.awt.Color

import common.Comments
import shapes.Shape
import vecmath.Ray

class World(var backgroundColor: Color = Color.BLUE) {
  val path = "C:/Users/karol/IdeaProjects/RayTracer/Sceny/"
  val path_temp = "C:/Users/karol/IdeaProjects/RayTracer/temp/"
  var objects = List[Shape]()

  def traceRay(ray: Ray): HitInfo = {
    val result = new HitInfo()
    var minimalDistance = Ray.Huge
    var hitDistance = 0.0
    var test = (false, Double.MaxValue)

    for (obj <- objects) {
      test = obj.HitTest(ray, hitDistance)
      hitDistance = test._2
      if (test._1 && test._2 < minimalDistance) {
        minimalDistance = hitDistance
        result.hitObject = true
        result.color = obj.color
      }
    }
    return result
  }

  def loadFromFile(fname:String): Unit = {
    val path_file = path + fname
    val tm_fname = path_temp + fname + ".tmp"

    Comments.removeComments(path_file, tm_fname)
  }
}
