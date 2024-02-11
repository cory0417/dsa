
import org.example.MyGraph
import org.example.shortestDijkstra
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class DijkstraTest {
    private lateinit var g: MyGraph<String>

    @BeforeEach
    fun setUp() {
        g = MyGraph<String>().apply {
            addEdge("a", "b", 0.5)
            addEdge("a", "c", 2.5)
            addEdge("b", "c", 2.5)
            addEdge("b", "e", 5.0)
            addEdge("c", "d", 0.5)
            addEdge("c", "e", 3.0)
            addEdge("d", "e", 0.5)
        }
    }
    @Test
    fun testDijkstraCost() {
        assertEquals(shortestDijkstra(g, "b", "e")?.second,3.5)
        assertEquals(shortestDijkstra(g, "a", "e")?.second, 3.5)
        assertEquals(shortestDijkstra(g, "a", "d")?.second, 3.0)
        assertEquals(shortestDijkstra(g, "a", "b")?.second, 0.5)
        assertEquals(shortestDijkstra(g, "b", "a")?.second, null)
    }

    @Test
    fun testDijkstraPath() {
        assertEquals(shortestDijkstra(g, "b", "e")?.first, listOf("b", "c", "d", "e"))
        assertEquals(shortestDijkstra(g, "a", "e")?.first, listOf("a", "c", "d", "e"))
        assertEquals(shortestDijkstra(g, "a", "d")?.first, listOf("a", "c", "d"))
        assertEquals(shortestDijkstra(g, "a", "b")?.first, listOf("a", "b"))
        assertEquals(shortestDijkstra(g, "b", "a")?.first, null)
    }
}