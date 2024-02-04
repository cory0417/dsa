import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DLLStackTest {

    @Test
    fun `push adds elements to the stack`() {
        val stack = DLLStack<Int>()
        stack.push(1)
        stack.push(2)
        assertEquals(2, stack.peek())
    }

    @Test
    fun `pop removes and returns the top element`() {
        val stack = DLLStack<Int>()
        stack.push(1)
        stack.push(2)
        assertEquals(2, stack.pop())
        assertEquals(1, stack.peek())
    }

    @Test
    fun `isEmpty returns true for an empty stack`() {
        val stack = DLLStack<Int>()
        assertTrue(stack.isEmpty())
    }

    @Test
    fun `isEmpty returns false when the stack has elements`() {
        val stack = DLLStack<Int>()
        stack.push(1)
        assertFalse(stack.isEmpty())
    }
}
