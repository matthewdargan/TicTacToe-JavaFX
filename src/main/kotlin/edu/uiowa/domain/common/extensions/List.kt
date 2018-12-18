package edu.uiowa.domain.common.extensions

import edu.uiowa.domain.game.models.Coordinate

/**
 * Extension function of a List data type that creates
 * a set out of the indices and values from the list.
 *
 * @param index
 *              the index of the value from the list
 * @param value
 *              the value from the list
 * @return the set from the list used
 */
fun <E> List<E>.set(index: Int, value: E): List<E> {
    return mapIndexed { i, e ->
        if (i == index) value else e
    }
}

/**
 * Recursive extension function that is used to collect the elements mapped
 * in the right diagonal direction on the board.
 *
 * @param i
 *              the index of the row
 * @param j
 *              the index of the column
 * @param amount
 *              the size used to terminate the recursive calls
 * @param list
 *              the initial list of the elements used to add up
 *              to the total elements
 * @return the list of the elements mapped in the right diagonal direction on the board
 */
fun <E> List<List<E>>.diagonalRight(i: Int, j: Int, amount: Int, list: List<E> = listOf()): List<E> {
    if (amount <= 0) return list
    return diagonalRight(i + 1, j + 1, amount - 1, list + listOf(get(i)[j]))
}

/**
 * Recursive extension function that is used to collect the coordinates mapped
 * in the right diagonal direction on the board.
 *
 * @param i
 *              the index of the row
 * @param j
 *              the index of the column
 * @param amount
 *              the size used to terminate the recursive calls
 * @param list
 *              the initial list of the elements used to add up
 *              to the total coordinates
 * @return the list of the coordinates mapped in the right diagonal direction on the board
 */
fun <E> List<List<E>>.diagonalRightKeys(i: Int, j: Int, amount: Int, list: List<Coordinate> = listOf()): List<Coordinate> {
    if (amount <= 0) return list
    return diagonalRightKeys(i + 1, j + 1, amount - 1, list + listOf(Coordinate(i, j)))
}

/**
 * Recursive extension function that is used to collect the elements mapped
 * in the left diagonal direction on the board.
 *
 * @param i
 *              the index of the row
 * @param j
 *              the index of the column
 * @param amount
 *              the size used to terminate the recursive calls
 * @param list
 *              the initial list of the elements used to add up
 *              to the total elements
 * @return the list of the elements mapped in the left diagonal direction on the board
 */
fun <E> List<List<E>>.diagonalLeft(i: Int, j: Int, amount: Int, list: List<E> = listOf()): List<E> {
    if (amount <= 0) return list
    return diagonalLeft(i + 1, j - 1, amount - 1, list + listOf(get(i)[j]))
}

/**
 * Recursive extension function that is used to collect the coordinates mapped
 * in the left diagonal direction on the board.
 *
 * @param i
 *              the index of the row
 * @param j
 *              the index of the column
 * @param amount
 *              the size used to terminate the recursive calls
 * @param list
 *              the initial list of the elements used to add up
 *              to the total coordinates
 * @return the list of the coordinates mapped in the left diagonal direction on the board
 */
fun <E> List<List<E>>.diagonalLeftKeys(i: Int, j: Int, amount: Int, list: List<Coordinate> = listOf()): List<Coordinate> {
    if (amount <= 0) return list
    return diagonalLeftKeys(i + 1, j - 1, amount - 1, list + listOf(Coordinate(i, j)))
}

/**
 * Extension function that takes a two-dimensional list and maps it
 * to a different two-dimensional list based on a given function.
 *
 * @param f
 *              the passed in function used to manipulate the
 *              two-dimensional list
 * @param x
 *              the column index in the list
 * @param y
 *              the row index in the list
 * @return the two-dimensional list after the effect of the function
 */
fun <E> List<List<E>>.twoDMap(f: (x: Int, y: Int) -> E): List<List<E>> {
    return this.mapIndexed { i, list -> list.mapIndexed { j, e -> f(i, j) } }
}

/**
 * Extension function that takes a two-dimensional list and maps it
 * to a different two-dimensional list based on a given function
 * one element at a time.
 *
 * @param f
 *              the passed in function used to manipulate the
 *              two-dimensional list
 * @param x
 *              the column index in the list
 * @param y
 *              the row index in the list
 * @return the null value as the function is void and has already
 *         affected the amount of elements requested
 */
fun <E> List<List<E>>.twoDForeach(f: (x: Int, y: Int) -> Unit): Unit =
        forEachIndexed { i, list -> list.forEachIndexed { j, e -> f(i, j) } }

/**
 * Extension function that takes the two dimensional board and maps it to
 * a one-dimensional list of the coordinates played in the game.
 *
 * @return the list of coordinates corresponding to the
 *         two-dimensional board
 */
fun <E> List<List<E>>.toCoordinates(): List<Coordinate> =
        mapIndexed { i, list -> list.mapIndexed { j, item -> Coordinate(i, j) } }
                .flatMap { i -> i }