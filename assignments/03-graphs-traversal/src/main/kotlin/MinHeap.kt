package org.example

import java.util.*
import kotlin.math.ceil

/**
 * A class representing an element in the heap.
 *
 * @param T the type of the data in the element.
 * @property data the data in the element.
 * @property heapNumber the heap number of the element.
 */
class HeapElement<T>(var data: T, var heapNumber: Double) {
    override fun toString(): String {
        return "(${data}, ${heapNumber})"
    }
}

/**
 * A min heap data structure.
 *
 * The heap is represented as a list of elements, where the first element is
 * the root of the tree.
 *
 * @param T the type of the elements in the heap.
 * @property vertices the list of elements in the heap.
 */
class MinHeap<T> {
    var vertices: MutableList<HeapElement<T>> = mutableListOf()

    /**
     * Insert an element into the heap.
     *
     * @param data the element to insert.
     * @param heapNumber the heap number of the element.
     * @return true if the element was inserted, false if the element was
     * already in the heap.
     */
    fun insert(data: T, heapNumber: Double): Boolean {
        // Check if the data is unique
        if (vertices.any { it.data == data }) {
            return false
        }
        val node = HeapElement<T>(data, heapNumber)
        vertices.add(node)
        bubbleUp(vertices.size - 1)
        return true
    }

    /**
     * Get the element with the smallest [heapNumber] from the heap.
     */
    fun getMin(): T? {
        return dequeue()?.data
    }

    /**
     * Update the heap number of an element.
     *
     * @param data the element to update.
     * @param heapNumber the new heap number.
     * @return true if the element was updated, false if the element was not found.
     */
    fun adjustHeapNumber(data: T, heapNumber: Double): Boolean {
        val matchIndex = vertices.indexOfFirst { it.data == data }
        if (matchIndex == -1) {
            return false
        }
        if (vertices[matchIndex].heapNumber > heapNumber) {
            vertices[matchIndex].heapNumber = heapNumber
            bubbleUp(matchIndex)
            return true
        } else if (vertices[matchIndex].heapNumber < heapNumber) {
            vertices[matchIndex].heapNumber = heapNumber
            bubbleDown(matchIndex)
            return true
        } else {
            return true
        }
    }

    /**
     * Check if the heap is empty.
     *
     * @return true if the heap is empty, false otherwise.
     */
    fun isEmpty(): Boolean {
        return !vertices.any()
    }

    /**
     * Remove the first element from the heap.
     *
     * @return the element removed from the heap, null if the heap is empty.
     */
    private fun dequeue(): HeapElement<T>? {
        if (vertices.size == 0) {
            return null
        }
        Collections.swap(vertices, 0, vertices.size - 1)
        val element = vertices.removeLast()
        bubbleDown(0)
        return element
    }

    /**
     * Bubble up the element at the given index.
     *
     * @param startIndex the index to start the bubble up from.
     */
    private fun bubbleUp(startIndex: Int) {
        var i = startIndex
        val lastNonRootNode = 1
        // bubble up from the [startIndex]
        while (i >= lastNonRootNode) {
            val parent = findParent(i)
            if (compareElements(parent, i)) {
                Collections.swap(vertices, parent, i)
            }
            i = parent
        }
    }

    /**
     * Bubble down the element at the given index.
     *
     * @param startIndex the index to start the bubble down from.
     */
    private fun bubbleDown(startIndex: Int) {
        var i = startIndex
        val lastNonLeafNode =
            (ceil((vertices.size - 1).toDouble() / 2) - 1).toInt()
        // bubble down from the [startIndex]
        while (i <= lastNonLeafNode) {
            val children = findChild(i)
            // First evaluate which child has a bigger [heapNumber]
            if (compareElements(children.first, children.second)) {
                // Now check if the target node has a bigger [heapNumber]
                if (compareElements(i, children.second)) {
                    try {
                        Collections.swap(vertices, children.second, i)
                        i = children.second
                    } catch (e: IndexOutOfBoundsException) {
                        break
                    }
                } else {
                    break
                }
            } else if (compareElements(i, children.first)) {
                try {
                    Collections.swap(vertices, children.first, i)
                    i = children.first
                } catch (e: IndexOutOfBoundsException) {
                    break
                }
            } else {
                break
            }
        }
    }

    /**
     * Find the children of the given index.
     *
     * @param i the index of the parent.
     * @return a pair of the index of left and right children.
     */
    private fun findChild(i: Int): Pair<Int, Int> {
        val leftChild = (i + 1) * 2 - 1
        val rightChild = (i + 1) * 2
        return Pair(leftChild, rightChild)
    }

    /**
     * Find the parent of the given index.
     *
     * @param i the index of the child.
     * @return the index of the parent.
     */
    private fun findParent(i: Int): Int {
        return (ceil((i.toDouble() / 2)) - 1).toInt()
    }

    /**
     * Compare the elements of the heap by their indices.
     *
     * @param first index of the first element.
     * @param second index of the second element.
     * @return true if [first] is bigger than [second], false otherwise.
     */
    private fun compareElements(first: Int, second: Int): Boolean {
        if (vertices.getOrNull(first) == null) {
            return false
        }
        if (vertices.getOrNull(second) == null) {
            return true
        }
        return vertices[first].heapNumber > vertices[second].heapNumber
    }
}
fun main() {
    val heap = MinHeap<String>()
    heap.insert(data = "test", heapNumber = 3.2)
    heap.insert(data = "booh", heapNumber = 5.3)
    heap.insert(data = "claw", heapNumber = 1.0)
    heap.insert(data = "tester", heapNumber = 0.2)
    heap.insert(data = "qq", heapNumber = 0.5)
    heap.insert(data = "anotherone", heapNumber = 100.0)
    heap.adjustHeapNumber("claw", 500.0)
    println(heap)
}