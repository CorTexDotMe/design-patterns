package adapter

import java.awt.image.BufferedImage

class Bitmap(private val image: BufferedImage) {

    constructor(width: Int, height: Int, imageType: Int) : this(BufferedImage(width, height, imageType))

    val width: Int get() = image.width
    val height: Int get() = image.height

    fun getPixel(x: Int, y: Int): Int {
        require(x in 0 until width && y in 0 until height) { "Pixel coordinates out of bounds!" }
        return image.getRGB(x, y)
    }

    fun setPixel(x: Int, y: Int, color: Int) {
        require(x in 0 until width && y in 0 until height) { "Pixel coordinates out of bounds!" }
        image.setRGB(x, y, color)
    }
}