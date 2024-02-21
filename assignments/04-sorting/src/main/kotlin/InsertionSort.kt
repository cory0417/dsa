fun insertionSort(intList: MutableList<Int>): MutableList<Int> {
    for (i in 1 until intList.count()) {
        val target = intList[i]
        var j = i - 1
        // Shift elements to the right to make room for the target
        while (j >= 0 && target < intList[j]) {
            intList[j + 1] = intList[j]
            j--
        }
        // Place target at its correct position
        // j + 1 since j currently points to the immediately smaller than target
        intList[j + 1] = target
    }
    return intList
}
