import kotlin.random.Random

fun quickSort(intList: MutableList<Int>): MutableList<Int> {
    // Base case: when there's 1 or less element in the sublist
    if (intList.size <= 1) return intList

    val pivot = intList[Random.nextInt(intList.size)]
    val less = mutableListOf<Int>()
    val equal = mutableListOf<Int>()
    val greater = mutableListOf<Int>()

    intList.forEach {
        when {
            it < pivot -> less.add(it)
            it > pivot -> greater.add(it)
            else -> equal.add(it)
        }
    }

    // Recursively sort the less and greater lists, then combine
    return (quickSort(less) + equal + quickSort(greater)).toMutableList()
}
