package edu.uiowa.domain.game

import edu.uiowa.domain.game.models.Board
import edu.uiowa.domain.game.models.Coordinate
import edu.uiowa.domain.game.models.Field

/**
 * An interface that models the behaviors that an Ai
 * can take advantage of.
 */
interface AiTicTacToe {

    /**
     * Checks if the current position is available.
     *
     * @param x
     *          the horizontal dimension of the position
     * @param y
     *          the vertical dimension of the position
     * @return the current position if it is available,
     *          otherwise, false
     */
    fun isMoveAvailable(x: Int, y: Int): Boolean

    /**
     * Gets the player, who's move it is.
     *
     * @return the player
     */
    fun getPlayerOnMove(): Field

    /**
     * Gets the board.
     *
     * @return the board
     */
    fun getBoard(): Board

    /**
     * Creates a new game state with the modified values.
     *
     * @param x
     *          the horizontal dimension of the position
     * @param y
     *          the vertical dimension of the position
     * @return the new game state
     */
    fun makeMove(x: Int, y: Int): TicTacToe

    /**
     * Checks if the game has terminated or not.
     *
     * @return true if the game is over, false if it is not
     */
    fun isGameOver(): Boolean

    /**
     * Gets the winner and position of the game or will give a value of
     * Field.ANON if there is no winner.
     *
     * @return the winner of the game and the position of the end scene,
     *          if there is no winner Field.ANON is returned
     */
    fun getWinner(): Pair<Field, List<Coordinate>>

    /**
     * Gets the two previous positions which entails both
     * a cross and a circle.
     *
     * @return last two played positions (cross, circle)
     */
    fun getLastCoordinates(): Pair<Coordinate, Coordinate>
}