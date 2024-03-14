package org.example

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

fun importImage(path: String, gamma: Double = 2.2): Array<Array<Complex>> {
    val image: BufferedImage = ImageIO.read(File(path))
    val width = image.width
    val height = image.height

    // Create an array to store the image data
    val complexImage = Array(height) { Array(width) { Complex(0.0, 0.0) } }

    // Convert to grayscale and then to complex numbers
    for (y in 0 until height) {
        for (x in 0 until width) {
            val color = image.getRGB(x, y)
            val r = (color shr 16) and 0xFF
            val g = (color shr 8) and 0xFF
            val b = color and 0xFF
            val grayLevel = (0.2126 * r + 0.7152 * g + 0.0722 * b)

            complexImage[y][x] = Complex(grayLevel, 0.0)
        }
    }

    return complexImage
}

fun saveGrayscaleImage(data: Array<Array<Double>>, fileName: String) {
    val height = data.size
    val width = data[0].size
    val image = BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY)

    // Determine the min and max values after the logarithmic transformation
    var maxLogValue = Double.MIN_VALUE
    var minLogValue = Double.MAX_VALUE
    for (row in data) {
        for (value in row) {
            if (value > maxLogValue) maxLogValue = value
            if (value < minLogValue) minLogValue = value
        }
    }

    // Scale the logarithmic values to the 0-255 range
    for (y in 0 until height) {
        for (x in 0 until width) {
            val logValue = data[y][x]
            val scaledValue = ((logValue - minLogValue) / (maxLogValue -
                    minLogValue) * 255).toInt().coerceIn(0, 255)
            val color =
                (scaledValue shl 16) or (scaledValue shl 8) or scaledValue
            image.setRGB(x, y, color)
        }
    }

    // Save the image
    ImageIO.write(image, "png", File(fileName))
}