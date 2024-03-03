package org.example

class ScoreMatrix(private val seqA: String, private val seqB: String) {
    private val lenA = seqA.length
    private val lenB = seqB.length
    private val m = Matrix(lenB + 1, lenA + 1)
    private val s = substitutionMatrix()

    private fun substitutionMatrix(): Matrix {
        val s = Matrix(lenB, lenA)
        seqB.forEachIndexed { i, baseB ->
            seqA.forEachIndexed { j, baseA ->
                s[i, j] = score(baseA, baseB)
            }
        }
        return s
    }

    fun fill() {
        for (i in 1 until m.nRow) {
            for (j in 1 until m.nCol) {
                m[i, j] = maxOf(
                    m[i - 1, j - 1] + s[i - 1, j - 1],
                    m[i - 1, j] - gapPenalty(),
                    m[i, j - 1] - gapPenalty(),
                    0
                )
            }
        }
    }

    private fun score(baseA: Char, baseB: Char): Int {
        return if (baseA == baseB) {
            3
        } else {
            -3
        }
    }

    private fun gapPenalty(): Int {
        return 2
    }

    fun traceback(): Pair<String, String> {
        val (_, startRow, startCol) = m.getMaxWithIndex().first()
        var i = startRow
        var j = startCol
        var alignedSeqA = ""
        var alignedSeqB = ""
        while (m[i, j] > 0) {
            // check if it was a diagonal move
            if (i > 0 && j > 0 && m[i, j] == m[i - 1, j - 1] + s[i - 1, j - 1]) {
                alignedSeqA = seqA[j - 1] + alignedSeqA
                alignedSeqB = seqB[i - 1] + alignedSeqB
                i -= 1
                j -= 1
            }
            // check if it was a gap in seqA
            else if (i > 0 && m[i, j] == m[i - 1, j] - gapPenalty()) {
                alignedSeqA = "-$alignedSeqA"
                alignedSeqB = seqB[i - 1] + alignedSeqB
                i -= 1
            }
            // check if it was a gap in seqB
            else if (j > 0 && m[i, j] == m[i, j - 1] - gapPenalty()) {
                alignedSeqA = seqA[j - 1] + alignedSeqA
                alignedSeqB = "-$alignedSeqB"
                j -= 1
            }
        }
        return Pair(alignedSeqA, alignedSeqB)
    }
}


