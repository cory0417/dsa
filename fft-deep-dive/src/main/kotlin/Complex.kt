package org.example

import kotlin.math.atan2
import kotlin.math.hypot

/**
 * A class to represent complex numbers.
 *
 * @property real The real part of the complex number.
 * @property imaginary The imaginary part of the complex number.
 * @constructor Creates a new complex number with the given [real] and
 * [imaginary] parts.
 */
data class Complex(val real: Double, val imaginary: Double) {
    operator fun plus(other: Complex) =
        Complex(real + other.real, imaginary + other.imaginary)

    operator fun minus(other: Complex) =
        Complex(real - other.real, imaginary - other.imaginary)

    operator fun times(other: Complex) =
        Complex(
            real * other.real - imaginary * other.imaginary,
            real * other.imaginary + imaginary * other.real
        )

    fun abs() = hypot(real, imaginary)

    fun phase() = atan2(imaginary, real)
}