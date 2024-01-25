import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class GridTest {

    @Test
    fun testInGridWithinBounds() {
        assertTrue(inGrid(50, 100))
        assertTrue(inGrid(0, 0))
        assertTrue(inGrid(255, 255))
    }

    @Test
    fun testInGridOutsideBounds() {
        assertFalse(inGrid(-1, 100))
        assertFalse(inGrid(256, 200))
        assertFalse(inGrid(50, -5))
        assertFalse(inGrid(100, 300))
    }

    @Test
    fun testInGridEdgeCases() {
        assertFalse(inGrid(-1, -1))
        assertFalse(inGrid(256, 256))
    }
}