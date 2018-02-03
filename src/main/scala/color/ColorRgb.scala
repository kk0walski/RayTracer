package color

class ColorRgb (var r:Double, var g:Double, var b:Double) {

  def normalize():Unit = {
    this.r /= 255.0
    this.g /= 255.0
    this.b /= 255.0
  }
  def +(color:ColorRgb) = new ColorRgb(r + color.r, g + color.g, b + color.b)

  def *(color:ColorRgb) = new ColorRgb(r*color.r, g*color.g, b*color.b)

  def /(value: Double) = this*(1/value)

  def *(value: Double) = new ColorRgb(r*value, g*value, b*value)
}
object ColorRgb{
  val White = new ColorRgb(1,1,1)
  val Black = new ColorRgb(0,0,0)
}
