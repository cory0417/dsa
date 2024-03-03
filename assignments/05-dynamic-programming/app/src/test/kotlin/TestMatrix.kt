import org.example.Matrix
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.random.Random

class TestMatrix {
    @Test
    fun testStrassen() {
        val matrix120x100 = Matrix(5, 7)
        val matrix100x50 = Matrix(7, 2)
        for (i in 0 until matrix120x100.nRow) {
            for (j in 0 until matrix120x100.nCol) {
                matrix120x100[i, j] = Random.nextInt(-10, 10)
            }
        }

        for (i in 0 until matrix100x50.nRow) {
            for (j in 0 until matrix100x50.nCol) {
                matrix100x50[i, j] = Random.nextInt(-10, 10)
            }
        }
        val expected = matrix120x100 * matrix100x50
        val actual = Matrix.strassenMultiply(matrix120x100, matrix100x50)
        assertEquals(expected == actual, true)
    }

}