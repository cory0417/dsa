package org.example

import java.io.File

fun main() {
    val benchmark = BenchmarkMultiplication()
    val results = benchmark.start(limitExponent = 9, nTrials = 5)
    benchmark.save(results)

    val scoreMat = ScoreMatrix(targetGenome, testAgainst)
    scoreMat.fill()
    val (matchedSeqA, matchedSeqB) = scoreMat.traceback()
    File("genome_matched.txt").writeText("$matchedSeqA\n$matchedSeqB")
}