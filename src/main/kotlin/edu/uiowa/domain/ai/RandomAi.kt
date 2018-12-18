package edu.uiowa.domain.ai

import edu.uiowa.domain.common.extensions.random
import edu.uiowa.domain.game.AiTicTacToe
import edu.uiowa.domain.game.models.Coordinate
import edu.uiowa.domain.game.models.Field

/**
 * An implementation of the Ai interface that randomly chooses
 * the computer's next move out of the remaining valid options.
 */
class RandomAi : Ai {

    /**
     * An overridden method from the Ai interface that chooses
     * the computer's next move randomly.
     *
     * @param game
     *              an interfaced reference (AiTicTacToe) to a class used to
     *              determine the next moves for the computer to play
     * @return a Coordinate object corresponding to the next
     *          coordinate chosen randomly
     */
    override fun nextCoordinates(game: AiTicTacToe): Coordinate {
        val board = game.getBoard().getFields()

        while (true) {
            val x = Int.random(0, board.size - 1)
            val y = Int.random(0, board[x].size - 1)

            if (board[x][y] == Field.ANON) return Coordinate(x, y)
        }
    }
}