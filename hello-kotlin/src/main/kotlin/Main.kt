// Here's the original Python class method that determines if the ant object is within the allowed bounds.
// self.position is a tuple of two integers that represent x, y coordinates.

//def in_grid(self) -> bool:
//    """
//    Evaluate if the ant is within the grid.
//
//    Args:
//        None.
//
//    Returns:
//        bool: A boolean that represents if the ant is in the grid or not.
//          It's `True` if the ant is in the grid and `False` otherwise.
//    """
//    # Check if the position is between 0 and 255
//    in_bound = (self.position >= 0) & (self.position <= 255)
//
//    # return `False` if any of them are out of bounds
//    return in_bound.all()

// Here's my Kotlin translation:
fun inGrid(x: Int, y: Int): Boolean {
    // Check if the position is between 0 and 255
    val inBound = (x in 0..255) && (y in 0..255)
    // return `false` if any of them are out of bounds
    return inBound
}

fun main() {
    val x = 50
    val y = 50
    println("Ant in bound: ${inGrid(x, y)}")
}

