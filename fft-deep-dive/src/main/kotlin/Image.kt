package org.example

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

/**
 * Import an image from a file and convert it to an array of complex numbers.
 *
 * The image is first read into a [BufferedImage] object, and then converted to
 * grayscale. The grayscale values are then used to create an array of complex
 * numbers, where the real part is the grayscale value and the imaginary part is
 * zero.
 *
 * @param path The path to the image file.
 * @return An array of complex numbers representing the grayscale image.
 */
fun importImage(path: String): Array<Array<Complex>> {
    val image: BufferedImage = ImageIO.read(File(path))
    val width = image.width
    val height = image.height

    // Create an array to store the image data
    val complexImage = Array(height) { Array(width) { Complex(0.0, 0.0) } }

    // Convert to grayscale and then to complex numbers
    for (y in 0 until height) {
        for (x in 0 until width) {
            val color = image.getRGB(x, y)
            // The return color is in the format 0xAARRGGBB, where the alpha
            // channel is not used here. We extract the red, green, and blue
            // channels and use them to compute the grayscale value.
            val r = (color shr 16) and 0xFF
            val g = (color shr 8) and 0xFF
            val b = color and 0xFF
            val grayLevel = (0.2126 * r + 0.7152 * g + 0.0722 * b)

            complexImage[y][x] = Complex(grayLevel, 0.0)
        }
    }

    return complexImage
}

/**
 * Save a 2D array of doubles as a grayscale image.
 *
 * The input array is first scaled to the 0-255 range, and then used to create a
 * grayscale image. The image is then saved to a file.
 *
 * @param data The 2D array of doubles representing the grayscale image.
 * @param fileName The name of the file to save the image to.
 */
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