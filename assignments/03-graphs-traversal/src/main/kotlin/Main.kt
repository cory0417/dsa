package org.example

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
fun <T> shortestDijkstra(graph: MyGraph<T>, from: T, to: T): Pair<List<T>, Double>? {
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

fun main() {
    val g = MyGraph<String>()
    g.addEdge("a", "b", 0.5)
    g.addEdge("a", "c", 2.5)
    g.addEdge("b", "c", 2.5)
    g.addEdge("b", "e", 5.0)
    g.addEdge("c", "d", 0.5)
    g.addEdge("c", "e", 3.0)
    g.addEdge("d", "e", 0.5)

    g.getVertices().forEach { println(it) }
    g.getEdges("a").forEach(::println)
    g.getEdges("b").forEach { println(it) }
    g.getEdges("c").forEach { println(it) }

    println(shortestDijkstra(g, "b", "e")) // ([b, c, d, e], 3.5)
    println(shortestDijkstra(g, "a", "e")) // ([a, c, d, e], 3.5)
    println(shortestDijkstra(g, "a", "d")) // ([a, c, d], 3.0)
    println(shortestDijkstra(g, "a", "b")) // ([a, b], 0.5)
    println(shortestDijkstra(g, "b", "a")) // null
}