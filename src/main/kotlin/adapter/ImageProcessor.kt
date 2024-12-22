package adapter


class ImageProcessor() {

    // simulate image resize
    fun resizeImage(image: ByteArray, width: Int, height: Int): ByteArray {
        val resizedImage = ByteArray(width * height)
        for (i in image.indices) {
            resizedImage[i] = image[i]
        }
        return resizedImage
    }

}

