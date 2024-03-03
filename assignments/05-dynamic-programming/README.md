# Divide and Conquer
## Matrix
In the `app/src/main/kotlin/Matrix.kt`, you'll find an implementation for a 
`Matrix` class. The `Matrix` class has two ways to perform a matrix 
multiplication. The first is the 'naive' or a standard matrix multiplication 
method and the second is Strassen's algorithm, where it reduces the time 
complexity of the operation from $O(n^3)$ to around $O(n^{2.8})$. You can 
check out the benchmark results by running `Main.kt` of the same directory.
<figure>
  <img src="https://raw.githubusercontent.com/cory0417/dsa/assignment-05/assignments/05-dynamic-programming/multiplication_benchmark.svg?sanitize=true" alt="benchmark plot">
</figure>

Although Strassen should be faster in theory, standard method is faster in 
smaller matrix sizes. If you're curious how it performs when the dimensions 
are much larger, feel free to modify the `limitExponent` argument in `Main.
kt` to increase the maximum value of the exponent (has 2 as the base) for the 
dimension size. (It will probably take a while to compute.)

# Dynamic Programming
## Sequence Alignment
To perform local alignment on a pair of sequences, I implemented the 
Smith-Waterman algorithm that uses dynamic programming. Modify the constants 
in `score()` and `gapPenalty()` methods of the `ScoreMatrix` class in 
`SmithWaterman.kt` to change the heuristics for the scoring a match. 
Although nonlinear `gapPenalty()` function would result in a less segmented 
matches, a linear version has been implemented for simplicity. Running the 
`Main.kt` file would export the matched result in `genome_matched.txt` file 
at the root project directory. 

Here's a short example: 

Sequence A: `TGTTACGG`
Sequence B: `GGTTGACTA`
```kotlin
val scoreMat = ScoreMatrix("TGTTACGG","GGTTGACTA")
scoreMat.fill()
val (matchedSeqA, matchedSeqB) = scoreMat.traceback()
```
Result:
```text
GTT-AC
GTTGAC
```
