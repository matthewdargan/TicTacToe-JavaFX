package edu.uiowa.domain.game

import edu.uiowa.domain.common.extensions.diagonalLeft
import edu.uiowa.domain.common.extensions.diagonalLeftKeys
import edu.uiowa.domain.common.extensions.diagonalRight
import edu.uiowa.domain.common.extensions.diagonalRightKeys
import edu.uiowa.domain.game.models.Board
import edu.uiowa.domain.game.models.Coordinate
import edu.uiowa.domain.game.models.Field
import edu.uiowa.domain.game.models.GameBoard

/**
 * Class that overrides the TicTacToe interface and
 * combines the player with board status, coordinates, and winning status.
 *
 * @param board
 *              the current board status
 * @param playerOnMove
 *              the current player
 * @param winNumber
 *              the current win count
 * @param lastCoords
 *              the last two coordinates played
 */
class Game private constructor(private val board: Board, private val playerOnMove: Field, private val winNumber: Int,
                               private val lastCoords: Pair<Coordinate, Coordinate>
) : TicTacToe {

    /**
     * Gets the last two coordinates played.
     *
     * @return the last two coordinates played
     */
    override fun getLastCoordinates(): Pair<Coordinate, Coordinate> = lastCoords

    /**
     * Determines whether a move is valid.
     *
     * @param x
     *              the horizontal dimension of the move
     * @param y
     *              the vertical dimension of the move
     * @return a boolean that says if a move is valid or not
     */
    private fun canPlay(x: Int, y: Int): Boolean = isMoveAvailable(x, y) && !isGameOver()

    /**
     * Makes a move on the board if it is valid.
     *
     * @param x
     *              the horizontal dimension of the move
     * @param y
     *              the vertical dimension of the move
     * @return a new Game object after the desired move has been played
     *         or just the current Game object if the move was not valid
     */
    override fun makeMove(x: Int, y: Int): TicTacToe {
        if (canPlay(x, y))
            return createGame(getBoard().setField(x, y, playerOnMove), playerOnMove.toggle(), winNumber, Pair(Coordinate(x, y), lastCoords.first))

        return this
    }

    /**
     * Gets the winner of the game.
     *
     * @return the winner and the winner's played moves
     */
    override fun getWinner(): Pair<Field, List<Coordinate>> {
        val fields = board.getFields()

        // iterate through the rows in the board
        for (i in 0..board.getRows() - winNumber) {

            // iterate through the columns in the board
            for (j in 0..board.getColumns() - winNumber) {
                val rowsCoordinates = i.rangeTo(i + winNumber - 1).map { index -> Coordinate(index, j) }
                val rows = i.rangeTo(i + winNumber - 1).map { index -> fields[index][j] }

                if (rows.all { item -> item == Field.CROSS })
                    return Pair(Field.CROSS, rowsCoordinates.toList())
                else if (rows.all { item -> item == Field.CIRCLE })
                    return Pair(Field.CIRCLE, rowsCoordinates.toList())

                val columnsCoordinates = j.rangeTo(j + winNumber - 1).map { index -> Coordinate(i, index) }
                val columns = j.rangeTo(j + winNumber - 1).map { index -> fields[i][index] }

                if (columns.all { item -> item == Field.CROSS })
                    return Pair(Field.CROSS, columnsCoordinates)
                else if (columns.all { item -> item == Field.CIRCLE })
                    return Pair(Field.CIRCLE, columnsCoordinates)

                val diagonalRightCoordinates = fields.diagonalRightKeys(i, j, winNumber)
                val diagonalRight = fields.diagonalRight(i, j, winNumber)

                if (diagonalRight.all { item -> item == Field.CROSS })
                    return Pair(Field.CROSS, diagonalRightCoordinates)
                else if (diagonalRight.all { item -> item == Field.CIRCLE })
                    return Pair(Field.CIRCLE, diagonalRightCoordinates)

                val diagonalLeftCoordinate = fields.diagonalLeftKeys(i, j + winNumber - 1, winNumber)
                val diagonalLeft = fields.diagonalLeft(i, j + winNumber - 1, winNumber)

                if (diagonalLeft.all { item -> item == Field.CROSS })
                    return Pair(Field.CROSS, diagonalLeftCoordinate)
                else if (diagonalLeft.all { item -> item == Field.CIRCLE })
                    return Pair(Field.CIRCLE, diagonalLeftCoordinate)
            }
        }

        return Pair(Field.ANON, listOf())
    }

    /**
     * Checks if the game is over or not.
     *
     * @return a boolean that says if the game is over or not
     */
    override fun isGameOver(): Boolean = getWinner().first != Field.ANON

    /**
     * Checks if a move is available at a certain position.
     *
     * @param x
     *              the horizontal dimension of the move
     * @param y
     *              the vertical dimension of the move
     * @return a boolean that says if a square is open
     */
    override fun isMoveAvailable(x: Int, y: Int): Boolean = board.getField(x, y) == Field.ANON

    /**
     * Get the current player that is able to make a move.
     *
     * @return the current player
     */
    override fun getPlayerOnMove(): Field = playerOnMove

    /**
     * Gets the current board status.
     *
     * @return the current board status
     */
    override fun getBoard(): Board = board

    /**
     * A static reference that contains functions
     * that need to be static.
     */
    companion object {

        /**
         * Creates a new game with an empty GameBoard object.
         *
         * @param rows
         *              the desired number of rows
         * @param columns
         *              the desired number of columns
         * @param winNumber
         *              the current win count
         * @return a new Game object from the given GameBoard object
         */
        fun createNewGame(rows: Int, columns: Int, winNumber: Int = 5): TicTacToe = Game(GameBoard.createNewBoard(rows, columns),
                Field.CROSS, winNumber, Pair(Coordinate(0 - 2, -2), Coordinate(-2, -2)))

        /**
         * Creates a game with the preset played moves.
         *
         * @param board
         *              the current board status
         * @param playerOnMove
         *              the current player
         * @param winNumber
         *              the current win count
         * @param lastCoords
         *              the last two coordinates played
         * @return a Game object based on the already played moves
         */
        fun createGame(board: Board, playerOnMove: Field, winNumber: Int, lastCoords: Pair<Coordinate, Coordinate>) =
                Game(board, playerOnMove, winNumber, lastCoords)
    }
}