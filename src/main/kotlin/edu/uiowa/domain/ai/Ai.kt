package edu.uiowa.domain.ai

import edu.uiowa.domain.game.AiTicTacToe
import edu.uiowa.domain.game.models.Coordinate

/**
 * An interface used to conjoin a AiTicTacToe type class with a
 * useful method that will return the next coordinates to play for
 * that object.
 */
interface Ai {

    /**
     * A function used to determine the next coordinates
     * that the computer should play.
     *
     * @param game
     *              an interfaced reference (AiTicTacToe) to a class used to
     *              determine the next moves for the computer to play
     * @return a Coordinate object for the next corrdinate that the computer
     *          should play at
     */
    fun nextCoordinates(game: AiTicTacToe): Coordinate
}