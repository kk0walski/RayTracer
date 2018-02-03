package tracing

import java.awt.Color
import java.io.{BufferedReader, FileReader}
import java.util.{Locale, Scanner}

import common.Comments
import lights.PointLight
import shapes.{Shape, Traingle}
import vecmath.{Ray, Vector}

class World(var backgroundColor: Color = Color.BLUE) {
  val path = "C:/Users/karol/IdeaProjects/RayTracer/Sceny/"
  val path_temp = "C:/Users/karol/IdeaProjects/RayTracer/temp/"
  var objects = List[Shape]()
  var lights = List[PointLight]()
  var vertices:Array[Vector] = null
  var traingles:Array[Traingle] = null

  def traceRay(ray: Ray): HitInfo = {
    val result:HitInfo = null
    var normal = Vector.Zero
    var minimalDistance = Ray.Huge
    var lastDistance = 0.0
    var test = (false, Double.MaxValue, Vector.Zero)

    for (obj <- objects) {
      test = obj.HitTest(ray, lastDistance, normal)
      lastDistance = test._2
      normal = test._3
      if (test._1 && lastDistance < minimalDistance) {
        minimalDistance = lastDistance
        result.hitObject = obj
        result.normal = normal
      }
      if (result.hitObject != null)
      {
        result.hitPoint = ray.origin + ray.direction*minimalDistance
        result.ray = ray
        result.world = this
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
      vertices = new Array[Vector](vert_count)
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
      traingles = new Array[Traingle](trg_count)
      for(i <- 0 to trg_count)
        {
          traingles(i) = Traingle.readFromFile(s,vertices)
        }
    }
    finally {
      s.close()
    }
  }
}
