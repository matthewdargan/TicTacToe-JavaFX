package edu.uiowa

import edu.uiowa.domain.game.models.Board
import edu.uiowa.domain.game.models.Coordinate
import edu.uiowa.domain.game.models.Field
import edu.uiowa.domain.game.models.GameBoard
import org.junit.Assert.*
import org.junit.Test

class AdjacencyTest {

    val b = GameBoard.createBoard(listOf(
            listOf(Field.ANON, Field.CROSS, Field.CROSS, Field.ANON),
            listOf(Field.ANON, Field.CROSS, Field.CROSS, Field.CROSS),
            listOf(Field.ANON, Field.ANON, Field.ANON, Field.ANON),
            listOf(Field.ANON, Field.ANON, Field.ANON, Field.ANON)
    ))

    /**
     * A recursive function that determines whether the adjacent coordinates
     * contain a cross, circle, or neither.
     *
     * @param board
     *              the reference to the current board state
     * @param pos
     *              the current coordinate
     * @param amount
     *              the value corresponding to its to its related
     *              Field enum
     * @param memory
     *              a set of the coordinates that have been played
     * @return the index of the adjacent Field enum of the same value
     */
    fun adjacent(board: Board, pos: Coordinate, amount: Int = 1, memory: Set<Coordinate> = setOf()): Int {
        val tmpList = board.getCoordsAround(pos)
                .filter { i -> board.getField(pos.x, pos.y) == board.getField(i.x, i.y) && !memory.contains(i) }

        tmpList.forEach { i ->
            println(pos.toString() + ":" + i)
            println(board.getField(pos.x, pos.y).toString() + ":" + board.getField(i.x, i.y))
        }

        if (tmpList.isEmpty()) return amount

        println(tmpList)

        return tmpList
                .map { i -> adjacent(board, i, amount + 1, memory + pos) }
                .sorted().last()
    }

    @Test
    fun checkAdjacency1() {
        assertEquals(adjacent(b, Coordinate(0, 0)), 10)
    }

    @Test
    fun checkAdjacency2() {
        assertEquals(adjacent(b, Coordinate(2, 2)), 10)
    }
}