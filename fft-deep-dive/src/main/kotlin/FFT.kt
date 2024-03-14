package org.example

import kotlin.math.*

/**
 * Compute the 1D Fast Fourier Transform of an array of complex numbers.
 *
 * Uses the Cooley-Tukey algorithm to recursively divide the [input] array into
 * smaller arrays, and then combine the results to produce the final output. The
 * algorithm works best when the input array has a length that is a power of
 * two. Otherwise, the input array is zero-padded to the next power of two.
 *
 * @param input The input array of complex numbers.
 * @return The 1D Fast Fourier Transform of the [input] array.
 */
fun fft(input: Array<Complex>): Array<Complex> {
    var n = input.size
    var signal = input
    if (n == 1) return input
    else if (!isPowerOfTwo(n)) {
        val padded = zeroPad(input)
        n = padded.size
        signal = padded
    }

    val even =
        fft(signal.filterIndexed { index, _ -> index % 2 == 0 }.toTypedArray())
    val odd =
        fft(signal.filterIndexed { index, _ -> index % 2 != 0 }.toTypedArray())

    val twiddleFactors = Array(n / 2) { k ->
        val angle = -2 * PI * k / n
        Complex(cos(angle), sin(angle))
    }

    val result = Array(n) { Complex(0.0, 0.0) }
    for (k in 0 until n / 2) {
        val twiddleFactor = twiddleFactors[k]
        val evenComponent = even[k]
        val oddComponent = twiddleFactor * odd[k]

        result[k] = evenComponent + oddComponent
        result[k + n / 2] = evenComponent - oddComponent
    }
    return result
}

/**
 * Compute the 2D Fast Fourier Transform of an array of complex numbers.

 * The 2D FFT is computed by first computing the 1D FFT of each row of the input
 * array, and then computing the 1D FFT of each column of the resulting array.
 * The final output is a 2D array of complex numbers.

 * @param input The input 2D array of complex numbers.
 * @return The 2D Fast Fourier Transform of the [input] array.
 */
fun fft2D(input: Array<Array<Complex>>): Array<Array<Complex>> {
    val rowFFT = input.map { fft(it) }.toTypedArray()
    val colFFT = Array(input[0].size) { col ->
        fft(rowFFT.map { it[col] }.toTypedArray())
    }
    return Array(input.size) { row -> Array(input[0].size) { col -> colFFT[col][row] } }
}

/**
 * Shift the zero-frequency component of the 2D FFT to the center of the array.
 *
 * @param input The input 2D array of complex numbers.
 * @return The 2D array of complex numbers with the zero-frequency component
 * shifted to the center.
 */
fun fftShift(input: Array<Array<Complex>>): Array<Array<Complex>> {
    val rows = input.size
    val cols = input[0].size
    val output = Array(rows) { Array(cols) { Complex(0.0, 0.0) } }

    for (i in 0 until rows) {
        for (j in 0 until cols) {
            output[(i + rows / 2) % rows][(j + cols / 2) % cols] = input[i][j]
        }
    }
    return output
}

/**
 * Apply log transform to the magnitudes of the 2D FFT result.
 *
 * @param fftResult The 2D array of complex numbers representing the FFT result.
 * @return The 2D array of doubles representing the magnitude of the FFT result.
 */
fun applyLogTransform(fftResult: Array<Array<Complex>>): Array<Array<Double>> {
    return fftResult.map { row ->
        row.map { value ->
            ln(1 + value.abs())
        }.toTypedArray()
    }.toTypedArray()
}

/**
 * Extract the phase of the 2D FFT result.
 *
 * @param fftResult The 2D array of complex numbers representing the FFT result.
 * @return The 2D array of doubles representing the phase of the FFT result.
 */
fun extractPhase(fftResult: Array<Array<Complex>>): Array<Array<Double>> {
    return fftResult.map { row ->
        row.map {
            ln(1 + it.phase())
        }.toTypedArray()
    }.toTypedArray()
}

/**
 * Add zero pad to an array of complex numbers to make its size a power of two.
 *
 * @param input The input array of complex numbers.
 * @return The zero-padded array of complex numbers.
 */
fun zeroPad(input: Array<Complex>): Array<Complex> {
    val originalLength = input.size
    // Bitwise operation for finding power of two
    val newLength = Integer.highestOneBit(originalLength - 1) shl 1
    val paddedArray = Array(newLength) { Complex(0.0, 0.0) }
    System.arraycopy(input, 0, paddedArray, 0, originalLength)
    return paddedArray
}

/**
 * Check if a number is a power of two.
 *
 * @param n The input number.
 * @return `true` if the input number is a power of two, `false` otherwise.
 */
fun isPowerOfTwo(n: Int): Boolean {
    return n > 0 && (n and (n - 1)) == 0
}