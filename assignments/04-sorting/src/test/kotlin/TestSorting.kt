import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class SortingTest {

    companion object {
        @JvmStatic
        fun sortingAlgorithms() = listOf(
            ::quickSort,
            ::mergeSort,
            ::insertionSort,
            ::selectionSort,
            ::minFinder
        )
    }

    @ParameterizedTest
    @MethodSource("sortingAlgorithms")
    fun `test sort`(sortFunction: (MutableList<Int>) -> MutableList<Int>) {
        val testCases = listOf(
            mutableListOf<Int>() to mutableListOf(),
            mutableListOf(1) to mutableListOf(1),
            mutableListOf(3, 1, 2) to mutableListOf(1, 2, 3),
            mutableListOf(5, 4, 3, 2, 1) to mutableListOf(1, 2, 3, 4, 5)
        )

        testCases.forEach { (input, expected) ->
            assertEquals(expected, sortFunction(input))
        }
    }
}
