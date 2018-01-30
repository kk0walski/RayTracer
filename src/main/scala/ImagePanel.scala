import java.awt.Graphics
import java.awt.image.BufferedImage
import javax.swing.JPanel

class ImagePanel(val image: BufferedImage) extends JPanel {

  override def paintComponent(g: Graphics) = {
    super.paintComponent(g)
    g.drawImage(image, 0, 0, this)
  }
}
