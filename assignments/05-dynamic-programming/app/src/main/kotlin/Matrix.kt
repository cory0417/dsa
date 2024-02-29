package org.example
class Matrix(n: Int) {
    var matrix = Array(n) {
        IntArray(n)
    }
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
}