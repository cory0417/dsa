interface Queue<T> {
    /**
     * Add [data] to the end of the queue.
     */
    fun enqueue(data: T)
    /**
     * Remove the element at the front of the queue.  If the queue is empty, it remains unchanged.
     * @return the value at the front of the queue or nil if none exists
     */
    fun dequeue(): T?
    /**
     * @return the value at the front of the queue or nil if none exists
     */
    fun peek(): T?
    /**
     * @return true if the queue is empty and false otherwise
     */
    fun isEmpty(): Boolean
}

/**
 * An implementation of a FIFO structure called Queue.
 *
 * @param T data stored in the Queue
 *
 * @property doublyLinkedList the list used to store the data
 * @constructor initializes the list
 */
class DLLQueue<T>: Queue<T> {


    private var doublyLinkedList: DoublyLinkedList<T> = DoublyLinkedList()
    override fun enqueue(data: T) {
        doublyLinkedList.pushBack(data)
    }

    override fun dequeue(): T? {
        return doublyLinkedList.popFront()
    }

    override fun peek(): T? {
        return doublyLinkedList.peekFront()
    }

    override fun isEmpty(): Boolean {
        return doublyLinkedList.isEmpty()
    }
}
