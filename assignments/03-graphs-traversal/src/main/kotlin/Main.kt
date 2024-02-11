package org.example

import java.io.File

/**
 * Find the shortest path between two vertices in a graph using Dijkstra's algorithm.
 *
 * This algorithm uses a priority queue to keep track of the vertices to
 * visit next. The priority queue is implemented as a min heap.
 *
 * The algorithm works as follows:
 *  - For each vertex, v that is not the source:
 *      - prev[v] ← UNDEFINED
 *      - dist[v] ← INFINITY
 *      - queue.addWithPriority(v, INFINITY)
 *  - dist[from] ← 0
 *  - queue.addWithPriority(source, 0)
 *  - while queue is not empty:
 *      - u ← vertex in queue with min priority
 *      - remove u from queue
 *      - for each neighbor v of u still in queue:
 *          - alt ← dist[u] + edgecost(u, v)
 *          - if alt < dist[v]:
 *              - dist[v] ← alt
 *              - queue.changePriority(v, alt)
 *              - prev[v] ← u
 *  - reconstruct the shortest path from prev
 *  @param graph The graph to search for the shortest path.
 *  @param from The vertex to start the search from.
 *  @param to The vertex to find the shortest path to.
 *  @return A pair containing the shortest path and the distance of the path.
 *  If no path is found, return null.
 */
fun <T> shortestDijkstra(
    graph: MyGraph<T>, from: T, to: T
): Pair<List<T>, Double>? {
    val prev = mutableMapOf<T, T?>()
    val dist = mutableMapOf<T, Double>()
    val vertices = graph.getVertices()
    val queue = MyMinPriorityQueue<T>()

    for (vertex in vertices) {
        if (vertex == from) {
            prev[vertex] = null
            dist[from] = 0.0
            queue.addWithPriority(from, 0.0)
        } else {
            prev[vertex] = null
            dist[vertex] = Double.POSITIVE_INFINITY
            queue.addWithPriority(vertex, Double.POSITIVE_INFINITY)
        }
    }

    while (!queue.isEmpty()) {
        // Get the highest priority vertex and dequeue it
        val u = queue.next() ?: break
        // Get all the neighbors of vertex u
        val neighbors = graph.getEdges(u)

        for (v in neighbors.keys) {
            val alt = dist[u]!! + neighbors[v]!!
            if (alt < dist[v]!!) {
                dist[v] = alt
                queue.adjustPriority(v, alt)
                prev[v] = u
            }
        }
    }
    // Reconstruct the shortest path from prev
    val path = mutableListOf<T>()
    var current = to
    while (current != from) {
        path.add(current)
        current = prev[current] ?: return null
    }
    path.add(from)
    return Pair(path.reversed(), dist[to]!!)
}

fun projEuler81(fileDir: String): Pair<List<String>, Double>? {
    val lines = File(fileDir).readLines()
    val matrix = Array(80) { DoubleArray(80) }
    lines.forEachIndexed { rowIndex, line ->
        line.split(",").mapIndexed { colIndex, value ->
            matrix[rowIndex][colIndex] = value.toDouble()
        }
    }
    // Check the matrix
//    matrix.forEach { row ->
//        println(row.joinToString(separator = " ", prefix = "[", postfix = "]"))
//    }
    val graph = MyGraph<String>()
    for (i in 0 .. 78) {
        for (j in 0 .. 78) {
            graph.addEdge("($i, $j)", "(${i + 1}, $j)", matrix[i][j])
            graph.addEdge("($i, $j)", "($i, ${j + 1})", matrix[i][j])
        }
    }
    for (i in 0 .. 78) {
        graph.addEdge("($i, 79)", "(${i+1}, 79)", matrix[i][79])
    }
    for (j in 0 .. 78) {
        graph.addEdge("(79, $j)", "(79, ${j + 1})", matrix[79][j])
    }

    val dijkstra = shortestDijkstra(graph, "(0, 0)", "(79, 79)")
    // Since the problem asks to include the cost of the end point, we add it
    return dijkstra?.let { Pair(it.first, it.second + matrix[79][79]) }
}

fun main() {
    // Solution to Project Euler problem 81
    // Confirmed by https://blog.dreamshire.com/project-euler-81-solution/
    val x = projEuler81("0081_matrix.txt") // 427337.0

    println(x)
}
