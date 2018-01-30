package cameras

import vecmath.{Ray, Vector, Vector2}

class Orthogonal(var eyePosition: Vector, var lookAt: Vector, var cameraSize: Vector2, var up: Vector) extends ICamera {
  override def GetRayTo(relativeLocation: Vector2): Ray = {
    // Kierunek w którym skierowane są wszystkie promienie
    // wychodzące z kamery.
    // Otrzymany prostymi funkcjami trygonometrycznymi.
    var direction = lookAt - eyePosition
    var prostopadly = direction ** up

    // Kierunek promienia zawsze musi być znormalizowany
    direction = direction.normalize
    prostopadly = prostopadly.normalize

    // Jak bardzo początek promienia jest oddalony od
    // położenia kamery
    val position = eyePosition - (prostopadly * relativeLocation.x * cameraSize.x + up * relativeLocation.y * cameraSize.y)

    return new Ray(position, direction)
  }
}

object Orthogonal {
  def up(position: Vector, lookAt: Vector): Vector = {
    val direction = lookAt - position
    val wart = (0 - direction.x - direction.y) / direction.z
    return new Vector(1, 1, wart)
  }
}