package adapter

import java.awt.image.BufferedImage.TYPE_INT_RGB

class ImageProcessorAdapter(private val imageProcessor: ImageProcessor) {

    fun resizeImage(image: Bitmap, width: Int, height: Int): Bitmap {
        val byteArrayImage = image.toByteArray()
        val resizedByteArrayImage = imageProcessor.resizeImage(byteArrayImage, width, height)
        return resizedByteArrayImage.toBitmap(width, height)
    }

    private fun Bitmap.toByteArray(): ByteArray {
        val res = ByteArray(width * height)
        for (i in 0 until height) {
            for (j in 0 until width) {
                res[i * width + j] = getPixel(i, j).toByte()
            }
        }

        return res
    }

    private fun ByteArray.toBitmap(width: Int, height: Int): Bitmap {
        val res = Bitmap(width, height, TYPE_INT_RGB)
        for (i in 0 until height) {
            for (j in 0 until width) {
                res.setPixel(i, j, get(i * width + j).toInt())
            }
        }

        return res
    }

}