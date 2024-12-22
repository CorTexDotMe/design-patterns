package adapter

import java.io.File
import javax.imageio.ImageIO

class ImageProducer {
    fun getImage(filePath: String): Bitmap {
        val file = File(filePath)
        if (!file.exists()) throw IllegalArgumentException("File at $filePath does not exist!")
        val bufferedImage = ImageIO.read(file) ?: throw IllegalArgumentException("Failed to read image from file!")
        return Bitmap(bufferedImage)
    }
}