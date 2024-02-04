interface Stack<T> {
    /**
     * Add [data] to the top of the stack
     */
    fun push(data: T)

    /**
     * Remove the element at the top of the stack.  If the stack is empty, it remains unchanged.
     * @return the value at the top of the stack or nil if none exists
     */
    fun pop(): T?

    /**
     * @return the value on the top of the stack or nil if none exists
     */
    fun peek(): T?

    /**
     * @return true if the stack is empty and false otherwise
     */
    fun isEmpty(): Boolean
}

/**
 * An implementation of a LIFO structure called Stack.
 *
 * @param T data stored in the Stack
 */
class DLLStack<T> : Stack<T> {
    private var doublyLinkedList: DoublyLinkedList<T> = DoublyLinkedList()
    override fun push(data: T) {
        doublyLinkedList.pushBack(data)
    }

    override fun pop(): T? {
        return doublyLinkedList.popBack()
    }

    override fun peek(): T? {
        return doublyLinkedList.peekBack()
    }

    override fun isEmpty(): Boolean {
        return doublyLinkedList.isEmpty()
    }
    override fun toString(): String {
        var current = doublyLinkedList.tail // Assuming head is accessible; adjust based on your implementation
        val items = mutableListOf<T>()
        while (current != null) {
            items.add(current.data)
            current = current.next
        }
        return items.joinToString(prefix = "[", postfix = "]")
    }
}