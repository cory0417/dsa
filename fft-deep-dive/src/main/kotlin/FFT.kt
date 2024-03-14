package org.example

import kotlin.math.*
import kotlin.reflect.typeOf

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

fun fft2D(input: Array<Array<Complex>>): Array<Array<Complex>> {
    val rowFFT = input.map { fft(it) }.toTypedArray()
    val colFFT = Array(input[0].size) { col ->
        fft(rowFFT.map { it[col] }.toTypedArray())
    }
    return Array(input.size) { row -> Array(input[0].size) { col -> colFFT[col][row] } }
}

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

fun applyLogTransform(input: Array<Array<Complex>>): Array<Array<Double>> {
    return input.map { row ->
        row.map { value ->
            ln(1 + value.abs())
        }.toTypedArray()
    }.toTypedArray()
}

fun extractPhase(fftResult: Array<Array<Complex>>): Array<Array<Double>> {
    return fftResult.map { row ->
        row.map {ln(1 + it.phase())
        }.toTypedArray()
    }.toTypedArray()
}

fun zeroPad(input: Array<Complex>): Array<Complex> {
    val originalLength = input.size
    // Bitwise operation for finding power of two
    val newLength = Integer.highestOneBit(originalLength - 1) shl 1
    val paddedArray = Array(newLength) { Complex(0.0, 0.0) }
    System.arraycopy(input, 0, paddedArray, 0, originalLength)
    return paddedArray
}

fun isPowerOfTwo(n: Int): Boolean {
    return n > 0 && (n and (n - 1)) == 0
}