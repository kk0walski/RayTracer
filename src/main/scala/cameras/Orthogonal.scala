package cameras

import vecmath.{Ray, Vector, Vector2}

class Orthogonal(var eyePosition: Vector, var angle: Double, var cameraSize: Vector2) extends ICamera {
  override def GetRayTo(relativeLocation: Vector2): Ray = {
    // Kierunek w którym skierowane są wszystkie promienie
    // wychodzące z kamery.
    // Otrzymany prostymi funkcjami trygonometrycznymi.
    var direction = new Vector(Math.sin(angle), 0, Math.cos(angle))

    // Kierunek promienia zawsze musi być znormalizowany
    direction = direction.normalize

    // Jak bardzo początek promienia jest oddalony od
    // położenia kamery
    val offsetFromCenter = new Vector2(relativeLocation.x * cameraSize.x, relativeLocation.y * cameraSize.y)

    // Obliczenie finalnego położenia kamery,
    // rówież proste funkcje trygonometryczne.
    val position = new Vector(
      eyePosition.x + offsetFromCenter.x * Math.cos(angle),
      eyePosition.y + offsetFromCenter.y,
      eyePosition.z + offsetFromCenter.x * Math.sin(angle)
    )

    return new Ray(position, direction)
  }
}
