
import org.example.MyGraph
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MyGraphTest {
    @Test
    fun testAddGetVertices() {
        val g = MyGraph<String>()
        g.addEdge("a", "b", 1.0)
        g.addEdge("a", "c", 2.0)
        g.addEdge("b", "c", 3.0)

        assertEquals(g.getVertices(), setOf("a", "b", "c"))
    }

    @Test
    fun testAddEdges() {
        val g = MyGraph<String>()
        g.addEdge("a", "b", 1.0)
        g.addEdge("a", "c", 2.0)
        g.addEdge("b", "c", 3.0)

        assertEquals(g.getEdges("a").size, 2)
    }

    @Test
    fun testGetEdges() {
        val g = MyGraph<String>()
        g.addEdge("a", "b", 1.0)
        g.addEdge("a", "c", 2.0)

        assertEquals(g.getEdges("a").size, 2)
        assertEquals(g.getEdges("a"), mapOf("b" to 1.0, "c" to 2.0))
    }

    @Test
    fun testClear() {
        val g = MyGraph<String>()
        g.addEdge("a", "b", 1.0)
        g.clear()

        assertEquals(g.getVertices(), setOf<String>())
        assertEquals(g.getEdges("a"), mapOf<String, Double>())
    }

}