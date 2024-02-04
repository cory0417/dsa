import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DLLQueueTest {

    @Test
    fun `enqueue adds elements to the queue`() {
        val queue = DLLQueue<Int>()
        queue.enqueue(1)
        queue.enqueue(2)
        assertEquals(1, queue.peek())
    }

    @Test
    fun `dequeue removes and returns the front element`() {
        val queue = DLLQueue<Int>()
        queue.enqueue(1)
        queue.enqueue(2)
        assertEquals(1, queue.dequeue())
        assertEquals(2, queue.peek())
    }

    @Test
    fun `isEmpty returns true for an empty queue`() {
        val queue = DLLQueue<Int>()
        assertTrue(queue.isEmpty())
    }

    @Test
    fun `isEmpty returns false when the queue has elements`() {
        val queue = DLLQueue<Int>()
        queue.enqueue(1)
        assertFalse(queue.isEmpty())
    }
}
