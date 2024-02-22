fun findMax(list: List<Int>): Int {
    if (list.size <=1) {
        return list[0]
    }
    val midPoint = list.size / 2
    val left = list.subList(0, midPoint)
    val right = list.subList(midPoint, list.size)

    var leftMax = Int.MIN_VALUE
    var rightMax = Int.MIN_VALUE

    IntArray(left.size).also {
        left.forEachIndexed { i, v ->
            it[i] = it.getOrElse(i - 1) { 0 } + v
            if (it[i] > leftMax) leftMax = it[i]
        }
    }
    IntArray(right.size).also {
        right.forEachIndexed { i, v ->
            it[i] = it.getOrElse(i - 1) { 0 } + v
            if (it[i] > rightMax) rightMax = it[i]
        }
    }

    return maxOf(
        leftMax,
        rightMax,
        findMax(left),
        findMax(right)
    )
}