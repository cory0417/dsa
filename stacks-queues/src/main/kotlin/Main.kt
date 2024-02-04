/**
 * Reverse the order of elements in a specified [DLLStack].
 *
 * Iterate over the provided stack, popping each element and pushing it onto a
 * new stack, thereby reversing the order. The function empties the original
 * stack during the process.
 *
 * @param T The type of elements in the stack.
 * @param stack The stack to reverse.
 * @return A new [DLLStack] with elements in reversed order.
 */
fun <T> reverseStack(stack: DLLStack<T>): DLLStack<T> {
    val reversed = DLLStack<T>()
    while (!stack.isEmpty()) {
        val item = stack.pop()
        reversed.push(item!!)
    }
    return reversed
}

/**
 * Determine if a string has valid parentheses.
 *
 * Iterate through each character in [s]. Push opening parentheses onto a
 * [DLLStack] and pop them when a matching closing parenthesis is encountered.
 * The string is valid if the stack is empty at the end.
 *
 * @param s The string to check for valid parentheses.
 * @return True if the parentheses are valid, false otherwise.
 */
fun validParentheses(s: String): Boolean {
    val stack = DLLStack<Char>()
    for (c in s) {
        if (c == '(' || c == '[' || c == '{') {
            stack.push(c)
        } else {
            // If the stack is empty, there is no matching opening parenthesis
            if (stack.isEmpty()) {
                return false
            }
            // If the top of the stack does not match the current closing
            // parenthesis, return false
            val top = stack.pop()
            if (c == ')' && top != '(') {
                return false
            }
            if (c == ']' && top != '[') {
                return false
            }
            if (c == '}' && top != '{') {
                return false
            }
        }
    }
    return stack.isEmpty()
}

/**
 * Copy the contents of a stack to a new stack.
 *
 * Utilize a temporary [DLLQueue] to reverse the order twice, ensuring the copy
 * maintains the original stack's order. Pop elements from the original stack
 * into the queue, then enqueue them back to the original stack and push to the
 * copy simultaneously.
 *
 * @param T The type of elements in the stack.
 * @param stack The original stack to copy.
 * @return A new [DLLStack] containing all elements from the original stack.
 */
fun <T> copyStack(stack: DLLStack<T>): DLLStack<T> {
    val copy = DLLStack<T>()
    val temp = DLLQueue<T>()
    while (!stack.isEmpty()) {
        temp.enqueue(stack.pop()!!)
    }
    while (!temp.isEmpty()) {
        val item = temp.dequeue()!!
        copy.push(item)
        stack.push(item)
    }
    return copy
}

fun main() {
    // Reverse elements in a stack
    val stack = DLLStack<Int>()
    stack.push(1)
    stack.push(2)
    stack.push(3)
    println("Original: $stack")

    val reversed = reverseStack(stack)
    println("Reversed: $reversed")

    // Valid parentheses test
    val validCase = "([{}])"
    val valid = validParentheses(validCase)
    println("$validCase: $valid") // true

    val invalidCase = "([)]"
    val invalid = validParentheses(invalidCase)
    println("$invalidCase $invalid") // false

    // Copy a stack
    val original = DLLStack<Int>()
    original.push(1)
    original.push(2)
    original.push(3)
    val copy = copyStack(original)
    println("Original: $original")
    println("Copy: $copy")
}