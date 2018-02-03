package tracing

import java.awt.Color
import java.io.{BufferedReader, FileReader}
import java.util.{ArrayList, Locale, Scanner}

import common.Comments
import shapes.Shape
import vecmath.{Ray, Vector}

class World(var backgroundColor: Color = Color.BLUE) {
  val path = "C:/Users/karol/IdeaProjects/RayTracer/Sceny/"
  val path_temp = "C:/Users/karol/IdeaProjects/RayTracer/temp/"
  var objects = List[Shape]()
  var vertices:Array[Vector]
  var adj_triangles:ArrayList[Integer]

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

    val s = new Scanner(new BufferedReader(new FileReader(tm_fname)))
    s.useLocale(Locale.US)
    try{
      // Read vertices
      // skip keyword
      s.next()
      val vert_count = s.nextInt()
      for(i <- 0 to vert_count){
        vertices(i) = Vector.Zero
        vertices(i).x = s.nextDouble()
        vertices(i).y = s.nextDouble()
        vertices(i).z = s.nextDouble()
      }
      // Read triangles
      // skip keyword
      s.next()
      val trg_count = s.nextInt()
      // Create data for computing averaged normals
      adj_triangles = new ArrayList[Integer](trg_count)
    }
  }
}
