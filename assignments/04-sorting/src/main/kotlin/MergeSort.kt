fun mergeSort(intList: MutableList<Int>): MutableList<Int> {
    // Use recursion to divide and conquer
    // Chop the list in two, until there is one or fewer elements in the list
    // Merge the adjacent lists while sorting
    //

    if (intList.count() <= 1) {
        return intList
    }
    val midPoint = intList.count() / 2
    var left = intList.subList(0, midPoint).toMutableList()
    var right = intList.subList(midPoint, intList.count()).toMutableList()

    left = mergeSort(left)
    right = mergeSort(right)

    return merge(left, right)
}


fun merge(left: MutableList<Int>, right: MutableList<Int>): MutableList<Int> {
    val result = mutableListOf<Int>()
    while (left.isNotEmpty() && right.isNotEmpty()) {
        if (left.first() <= right.first()) {
            result.add(left.removeAt(0))
        } else {
            result.add(right.removeAt(0))
        }
    }
    result.addAll(left) // Add all remaining elements from left
    result.addAll(right) // Add all remaining elements from right
    return result
}