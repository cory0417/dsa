package org.example

import kotlin.math.log2
import kotlin.math.pow

class Matrix(val nRow: Int, val nCol: Int) {
    var matrix: Array<IntArray> = Array(nRow) { IntArray(nCol) }

    operator fun get(row: Int, col: Int): Int {
        try {
            return matrix[row][col]
        } catch (e: Exception) {
            throw e
        }
    }

    operator fun set(row: Int, col: Int, value: Int) {
        try {
            matrix[row][col] = value
        } catch (e: Exception) {
            throw e
        }
    }

    private fun slice(rowRange: IntRange, colRange: IntRange):
            Matrix {
        val numRows = rowRange.last - rowRange.first + 1
        val numCols = colRange.last - colRange.first + 1
        val slicedMatrix = Matrix(numRows, numCols)

        for (i in rowRange) {
            for (j in colRange) {
                slicedMatrix[i - rowRange.first, j - colRange.first] =
                    this[i, j]
            }
        }
        return slicedMatrix
    }

    operator fun times(other: Matrix): Matrix {
        if (this.nCol != other.nRow) {
            throw IllegalArgumentException()
        }
        val product = Matrix(this.nRow, other.nCol)
        for (i in 0..<this.nRow) {
            for (j in 0..<other.nCol) {
                for (k in 0..<this.nCol) {
                    product[i, j] += this[i, k] * other[k, j]
                }
            }
        }
        return product
    }

    override fun toString(): String {
        val sb = StringBuilder()
        for (row in matrix) {
            sb.append(row.joinToString(prefix = "[", postfix = "]\n"))
        }
        return sb.toString().trimEnd() // Removes the trailing newline character
    }

    companion object {
        @JvmStatic
        fun strassenMultiply(lhs: Matrix, rhs: Matrix): Matrix {
            if (lhs.nCol != rhs.nRow) {
                throw IllegalArgumentException()
            }
            if (lhs.nRow == 1 && rhs.nCol == 1 && lhs.nCol == 1) {
                return lhs * rhs
            }
            val subLhs = lhs.subMatrices()
            val subRhs = rhs.subMatrices()
            val A11 = subLhs[0]
            val A12 = subLhs[1]
            val A21 = subLhs[2]
            val A22 = subLhs[3]
            val B11 = subRhs[0]
            val B12 = subRhs[1]
            val B21 = subRhs[2]
            val B22 = subRhs[3]
            if (A11.nCol == 1) {
                val M1 = (A11 + A22) * (B11 + B22)
                val M2 = (A21 + A22) * B11
                val M3 = A11 * (B12 - B22)
                val M4 = A22 * (B21 - B11)
                val M5 = (A11 + A12) * B22
                val M6 = (A21 - A11) * (B11 + B12)
                val M7 = (A12 - A22) * (B21 + B22)

                val subMatricesResult =
                    arrayOf(
                        M1 + M4 - M5 + M7,
                        M3 + M5,
                        M2 + M4,
                        M1 - M2 + M3 + M6
                    )

                return combine(subMatricesResult)
            }
            val subMatricesResult = arrayOf(
                strassenMultiply(A11, B11) + strassenMultiply(A12, B21),
                strassenMultiply(A11, B12) + strassenMultiply(A12, B22),
                strassenMultiply(A21, B11) + strassenMultiply(A22, B21),
                strassenMultiply(A21, B12) + strassenMultiply(A22, B22),
            )
            return trimPadding(
                combine(subMatricesResult),
                lhs.nRow,
                rhs.nCol
            )
        }

        private fun combine(subMatrices: Array<Matrix>): Matrix {
            val n = subMatrices[0].nRow
            val combined = Matrix(n * 2, n * 2)
            for (i in 0 until n) {
                for (j in 0 until n) {
                    // Top-left
                    combined[i, j] = subMatrices[0][i, j]
                    // Top-right
                    combined[i, j + n] = subMatrices[1][i, j]
                    // Bottom-left
                    combined[i + n, j] = subMatrices[2][i, j]
                    // Bottom-right
                    combined[i + n, j + n] = subMatrices[3][i, j]
                }
            }
            return combined
        }

        private fun trimPadding(m: Matrix, n1: Int, n2: Int): Matrix {
            return m.slice(0 until n1, 0 until n2)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Matrix) {
            return false
        }
        return this.matrix.contentDeepEquals(other.matrix)
    }

    private operator fun minus(other: Matrix): Matrix {
        if (this.nRow != other.nRow || this.nCol != other.nCol) {
            throw IllegalArgumentException()
        }
        val result = Matrix(nRow, nCol)
        for (i in 0 until nRow) {
            for (j in 0 until nCol) {
                result[i, j] = this[i, j] - other[i, j]
            }
        }
        return result
    }

    private operator fun plus(other: Matrix): Matrix {
        if (this.nRow != other.nRow || this.nCol != other.nCol) {
            throw IllegalArgumentException()
        }
        val result = Matrix(nRow, nCol)
        for (i in 0 until nRow) {
            for (j in 0 until nCol) {
                result[i, j] = this[i, j] + other[i, j]
            }
        }
        return result
    }

    private fun subMatrices(): Array<Matrix> {
        val padded = if (this.nRow % 2 == 0 && this.nCol % 2 == 0) {
            this
        } else {
            padMatrix()
        }
        val midPoint = padded.nRow / 2
        val subMatrices = arrayOf(
            padded.slice(0..<midPoint, 0..<midPoint),
            padded.slice(0..<midPoint, midPoint..<padded.nRow),
            padded.slice(midPoint..<padded.nRow, 0..<midPoint),
            padded.slice(midPoint..<padded.nRow, midPoint..<padded.nRow)
        )
        return subMatrices
    }

    private fun padMatrix(): Matrix {
        var exponent: Int = log2(maxOf(nRow, nCol).toDouble()).toInt()
        if (2.0.pow(exponent.toDouble()) < maxOf(nRow, nCol).toDouble()) {
            exponent++
        }
        val paddedDim = 2.0.pow(exponent.toDouble()).toInt()
        val padded = Matrix(paddedDim, paddedDim)
        val colPad = paddedDim - nCol
        var iRow = 0
        while (iRow < nRow) {
            padded.matrix[iRow] = matrix[iRow] + IntArray(colPad) { 0 }
            iRow++
        }
        return padded
    }

    override fun hashCode(): Int {
        var result = nRow
        result = 31 * result + nCol
        result = 31 * result + matrix.contentDeepHashCode()
        return result
    }

    fun getMaxWithIndex(): Array<Triple<Int, Int, Int>> {
        var maxValue = Int.MIN_VALUE
        val maxPositions = mutableListOf<Triple<Int, Int, Int>>()

        for (i in matrix.indices) {
            for (j in matrix[i].indices) {
                if (this[i, j] > maxValue) {
                    maxValue = this[i, j]
                    maxPositions.clear()
                    maxPositions.add(Triple(maxValue, i, j))
                } else if (this[i, j] == maxValue) {
                    maxPositions.add(Triple(maxValue, i, j))
                }
            }
        }
        return maxPositions.toTypedArray()
    }
}