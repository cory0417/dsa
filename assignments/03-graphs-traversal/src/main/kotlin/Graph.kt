package org.example

/**
 * A graph data structure.
 *
 * @param VertexType The type of the vertices in the graph.
 * @property vertices The set of vertices in the graph.
 * @property edges The map of vertices to their adjacent vertices and edge weights.
 */
class MyGraph<VertexType> : Graph<VertexType> {
    private var vertices: MutableSet<VertexType> = mutableSetOf()
    private var edges: MutableMap<VertexType, MutableMap<VertexType, Double>> =
        mutableMapOf()

    override fun getVertices(): Set<VertexType> {
        return vertices
    }

    override fun clear() {
        vertices = mutableSetOf()
        edges = mutableMapOf()
    }

    override fun getEdges(from: VertexType): Map<VertexType, Double> {
        return edges[from] ?: mutableMapOf()
    }

    override fun addEdge(from: VertexType, to: VertexType, cost: Double) {
        if (!vertices.contains(from)) {
            vertices.add(from)
        }
        if (!vertices.contains(to)) {
            vertices.add(to)
        }
        edges[from]?.also { currentAdjacent ->
            currentAdjacent[to] = cost
        } ?: run {
            edges[from] = mutableMapOf(to to cost)
        }
    }
}