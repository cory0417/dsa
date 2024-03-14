import kotlin.math.atan2
import kotlin.math.hypot

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