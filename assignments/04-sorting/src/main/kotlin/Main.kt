import java.io.File
import kotlin.system.measureNanoTime

fun main() {
    val sizes = listOf(
        100, 200, 500, 1000, 2000, 5000, 10000, 20000,
        50000, 100000
    )
    val results = mutableListOf<String>()

    sizes.forEach { size ->
        val list = List(size) { (0..10000).random() }.toMutableList()
        val quickSortTime = measureNanoTime { quickSort(list.toMutableList()) }
        val mergeSortTime = measureNanoTime { mergeSort(list.toMutableList()) }
        val insertionSortTime =
            measureNanoTime { insertionSort(list.toMutableList()) }
        val selectionSortTime =
            measureNanoTime { selectionSort(list.toMutableList()) }

        results.add("$size, $quickSortTime, $mergeSortTime, $insertionSortTime, $selectionSortTime")
    }

    File("sort_results.csv").writeText(
        "Size,QuickSort,MergeSort,InsertionSort,SelectionSort\n${
            results.joinToString(
                "\n"
            )
        }"
    )
}