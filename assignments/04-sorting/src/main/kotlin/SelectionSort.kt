import kotlin.Int.Companion.MAX_VALUE

fun selectionSort(intList: MutableList<Int>): MutableList<Int> {

    var minIndex = -1
    var pointer = 0
    while (pointer < intList.count()) {
        var min = MAX_VALUE
        for ((index, num) in intList.subList(pointer, intList.count())
            .withIndex()) {
            if (num < min) {
                min = num
                minIndex = index
            }
        }
        if (minIndex != -1) {
            intList.add(pointer, intList.removeAt(pointer + minIndex))
        }
        pointer++
    }
    return intList
}