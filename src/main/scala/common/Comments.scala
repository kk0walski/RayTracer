package common
import java.io.{File, PrintWriter}

import scala.io.Source
import scala.util.control.Breaks._

object Comments {
  def removeComments(fname:String, tmp_fname:String):Unit = {
    val writer = new PrintWriter(new File(tmp_fname))
    val source = Source.fromFile(fname)
    try {
      for (line <- source.getLines()) {
        var l = line.toString()
        val ind = l.indexOf("//")
        breakable {
          if (ind > 0) l = l.substring(0, ind - 1)
          else break
        }
        writer.println(l)
      }
    }
    finally
      {
        if(source != null)source.close()
        if(writer != null)writer.close()
      }
  }
}
