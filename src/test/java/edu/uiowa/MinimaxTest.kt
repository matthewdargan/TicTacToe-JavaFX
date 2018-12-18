package edu.uiowa

import edu.uiowa.domain.ai.Minimax
import edu.uiowa.domain.game.Game
import edu.uiowa.domain.game.TicTacToe
import edu.uiowa.domain.game.models.Coordinate
import org.junit.Assert.*
import org.junit.Test

class MinimaxTest {

    var game: TicTacToe = Game.createNewGame(22, 35, 5)
    val ai = Minimax()

    @Test
    fun checkMinimax1() {
        game.makeMove(0, 0)
        assertEquals(ai.nextCoordinates(game), Coordinate(22, 17))
    }

    @Test
    fun checkMinimax2() {
        game = Game.createNewGame(15, 15, 5)
        game.makeMove(0, 0)
        assertEquals(ai.nextCoordinates(game), Coordinate(15, 7))
    }

    @Test
    fun checkMinimax3() {
        game = Game.createNewGame(5, 5, 5)
        game.makeMove(4, 3)
        assertEquals(ai.nextCoordinates(game), Coordinate(5, 2))
    }
}