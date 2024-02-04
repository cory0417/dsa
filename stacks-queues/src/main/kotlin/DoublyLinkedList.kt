class DoublyLinkedList<T> {
    var head: ListNode<T>? = null
    var tail: ListNode<T>? = null

    /**
     * Adds the element [data] to the front of the linked list.
     */
    fun pushFront(data: T) {
        val node = ListNode(data, head, null)
        if (head == null) {
            head = node
            tail = node
        } else {
            head!!.next = node
            head = node
        }
    }

    /**
     * Adds the element [data] to the back of the linked list.
     */
    fun pushBack(data: T){
        val node = ListNode<T>(data, null, tail)

        if (tail != null) {
            tail!!.prev = node
            tail = node
        }

        tail = tail ?: node
        head = head ?: node
    }
    /**
     * Removes an element from the front of the list. If the list is empty, it is unchanged.
     * @return the value at the front of the list or nil if none exists
     */
    fun popFront(): T?{
        val data = head?.data
        head = head?.prev
        return data
    }
    /**
     * Removes an element from the back of the list. If the list is empty, it is unchanged.
     * @return the value at the back of the list or nil if none exists
     */
    fun popBack(): T?{
        val data = tail?.data
        tail = tail?.next
        return data
    }
    /**
     * @return the value at the front of the list or nil if none exists
     */
    fun peekFront(): T?{
        return head?.data
    }

    /**
     * @return the value at the back of the list or nil if none exists
     */
    fun peekBack(): T?{
        return tail?.data
    }

    /**
     * @return true if the list is empty and false otherwise
     */
    fun isEmpty(): Boolean{
        return (head == null || tail == null)
    }
}

class ListNode<T> (val data: T, var prev: ListNode<T>?, var next: ListNode<T>?)