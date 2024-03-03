package org.example

import java.io.File
import kotlin.math.pow
import kotlin.random.Random
import kotlin.system.measureNanoTime

class BenchmarkMultiplication {
    fun start(limitExponent: Int, nTrials: Int): MutableList<String> {
        val dims = List(limitExponent + 1) { exponent ->
            2.0.pow(exponent).toInt()
        }
        val results = mutableListOf<String>()
        dims.forEach { dim ->

            repeat(nTrials) {
                val lhs = Matrix(dim, dim)
                val rhs = Matrix(dim, dim)
                for (i in 0 until dim) {
                    for (j in 0 until dim) {
                        lhs[i, j] = Random.nextInt(-10, 10)
                        rhs[i, j] = Random.nextInt(-10, 10)
                    }
                }
                val timeStandard = measureNanoTime { lhs * rhs }
                val timeStrassen = measureNanoTime {
                    Matrix.strassenMultiply(
                        lhs,
                        rhs
                    )
                }
                results.add("$dim, $timeStandard, $timeStrassen")
            }
        }
        return results
    }

    fun save(
        results: MutableList<String>, path:
        String = "multiplication_benchmark.csv"
    ) {
        val csvContent =
            "dim, standard, strassen\n" + results.joinToString(separator = "\n")
        File(path).writeText(csvContent)
    }
}
