package adapter

fun main() {
    val filePath = "sea.png"
    val producer = ImageProducer()
    val processor = ImageProcessor()
    val processorAdapter = ImageProcessorAdapter(processor)

    val image = producer.getImage(filePath)

    // Cannot pass image to processor with adapter
    //processor.resizeImage(image, 1280, 720)

    processorAdapter.resizeImage(image, 1280, 720)
}