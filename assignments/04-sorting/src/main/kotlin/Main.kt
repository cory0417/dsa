import java.io.File
import kotlin.system.measureNanoTime

fun main() {
    val sizes = listOf(
        100, 200, 500, 1000, 2000, 5000, 10000, 20000, 50000, 100000
    )
    val results = mutableListOf<String>()
    val trials = 3
    sizes.forEach { size ->
        val times = MutableList(5) { mutableListOf<Long>() }

        repeat(trials) {
            val list = List(size) { (0..10000).random() }.toMutableList()
            times[0].add(measureNanoTime { quickSort(list.toMutableList()) })
            times[1].add(measureNanoTime { mergeSort(list.toMutableList()) })
            times[2].add(measureNanoTime { insertionSort(list.toMutableList()) })
            times[3].add(measureNanoTime { selectionSort(list.toMutableList()) })
            times[4].add(measureNanoTime { minFinder(list.toMutableList()) })
        }

        val averages = times.map { it.sum() / it.size }

        results.add("$size, ${averages.joinToString(", ")}")
    }

    File("sort_results.csv").writeText(
        "Size,QuickSort,MergeSort,InsertionSort,SelectionSort,MinFinderSort\n${results.joinToString("\n")}"
    )
//
//    // Bonus: consecutive max sum finding
//    val list = listOf(1, 2, -10, 20, -1, 5)
//    print(findMax(list)) // should print 24
//    val list = List(1000) { (0..10000).random() }.toMutableList()
//    val result = minFinder(list.toMutableList())
//    println(result)
}