fun minFinder(intList: MutableList<Int>): MutableList<Int> {
    var minIndex = 0
    for (startIndex in 0..<intList.size -1) {
        var min = Int.MAX_VALUE
        intList.slice(startIndex ..<intList.size).forEachIndexed { i, v ->
            if (min > v) {
                min = v
                minIndex = i + startIndex
            }
        }
        intList.add(startIndex, intList.removeAt(minIndex))
    }

    return intList
}