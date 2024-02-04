import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DoublyLinkedListTest {

    @Test
    fun `pushFront adds element to the front`() {
        val list = DoublyLinkedList<Int>()
        list.pushFront(1)
        assertEquals(1, list.peekFront())
    }

    @Test
    fun `pushBack adds element to the back`() {
        val list = DoublyLinkedList<Int>()
        list.pushBack(2)
        assertEquals(2, list.peekBack())
    }

    @Test
    fun `popFront removes and returns the front element`() {
        val list = DoublyLinkedList<Int>()
        list.pushFront(1)
        list.pushFront(2)
        assertEquals(2, list.popFront())
        assertEquals(1, list.peekFront())
    }

    @Test
    fun `popBack removes and returns the back element`() {
        val list = DoublyLinkedList<Int>()
        list.pushBack(1)
        list.pushBack(2)
        assertEquals(2, list.popBack())
        assertEquals(1, list.peekBack())
    }

    @Test
    fun `isEmpty returns true for a new list`() {
        val list = DoublyLinkedList<Int>()
        assertTrue(list.isEmpty())
    }

    @Test
    fun `isEmpty returns false for a list with elements`() {
        val list = DoublyLinkedList<Int>()
        list.pushBack(1)
        assertFalse(list.isEmpty())
    }
}
