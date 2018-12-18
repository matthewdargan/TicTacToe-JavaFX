package edu.uiowa.presentation.game

import edu.uiowa.domain.ai.Ai
import edu.uiowa.domain.ai.models.AiItems
import edu.uiowa.domain.game.Game
import edu.uiowa.domain.game.TicTacToe
import edu.uiowa.domain.game.models.Board
import tornadofx.Controller

/**
 * Controller class that controls the behavior of the game
 * during play.
 */
class GameController : Controller() {

    private val aiItems: AiItems by inject()
    private var game: TicTacToe = Game.createNewGame(22, 35, 5)

    /**
     * Handles what to do once a move has been made by either
     * a player or an Ai.
     *
     * @param x
     *              the horizontal positional value
     * @param y
     *              the vertical positional value
     */
    fun onMoveMade(x: Int, y: Int) {
        val circle = aiItems.circle
        val cross = aiItems.cross

        when {
            circle is Ai && cross is Ai -> {
                val crossCoords = cross.nextCoordinates(game)
                game = game.makeMove(crossCoords.x, crossCoords.y)

                val circleCoords = circle.nextCoordinates(game)
                game = game.makeMove(circleCoords.x, circleCoords.y)
            }

            circle is Ai -> {
                game = game.makeMove(x, y)
                val circleCoords = circle.nextCoordinates(game)

                game = game.makeMove(circleCoords.x, circleCoords.y)
            }

            else -> game = game.makeMove(x, y)
        }
    }

    /**
     * Gets the current board state.
     *
     * @return the current board state
     */
    fun getBoard(): Board = game.getBoard()

    /**
     * Gets the winner of the game.
     *
     * @return the winner of the game
     */
    fun getWinner() = game.getWinner()

    /**
     * Resets the board state to what it should be initially
     * if the player chooses to start over.
     */
    fun resetBoard() {
        game = Game.createNewGame(22, 35, 5)
    }

    /**
     * Makes one move at a time when running
     * an Ai vs Ai game; this function helps
     * separate out complexity by making Ai vs Ai
     * games their own case entirely.
     */
    fun makeOneMove() {
        val circle = aiItems.circle
        val cross = aiItems.cross

        if (circle is Ai && cross is Ai) {
            val crossCoords = cross.nextCoordinates(game)
            game = game.makeMove(crossCoords.x, crossCoords.y)

            val circleCoords = circle.nextCoordinates(game)
            game = game.makeMove(circleCoords.x, circleCoords.y)
        }
    }

    /**
     * @deprecated unused function in this current version
     */
    fun play() {}

    /**
     * Checks if the game type chosen is the Ai vs Ai mode.
     *
     * @return a boolean that says whether or not the chosen
     *         game mode is Ai vs Ai
     */
    fun isAiVsAi(): Boolean = aiItems.cross is Ai && aiItems.circle is Ai
}