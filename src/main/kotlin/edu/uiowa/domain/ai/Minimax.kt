package edu.uiowa.domain.ai

import edu.uiowa.domain.common.extensions.diagonalLeft
import edu.uiowa.domain.common.extensions.diagonalRight
import edu.uiowa.domain.game.AiTicTacToe
import edu.uiowa.domain.game.models.Board
import edu.uiowa.domain.game.models.Coordinate
import edu.uiowa.domain.game.models.Field

/**
 * An implementation of the Ai interface that uses a Minimax algorithm to find
 * the most optimal moves for the computer to make.
 *
 * @see <a href="https://www.neverstopbuilding.com/blog/minimax">
 *      neverstopbuilding.com/blog/minimax</a>
 */
class Minimax : Ai {

    /**
     * An overridden method from the Ai interface that chooses
     * the computer's next move based on the Minimax algorithm
     * that has been created in the minimax function.
     *
     * @param game
     *              an interfaced reference (AiTicTacToe) to a class used to
     *              determine the next moves for the computer to play
     * @return a Coordinate object corresponding to the next
     *          coordinate chosen after the Minimax algorithm
     *          has reached its base case
     */
    override fun nextCoordinates(game: AiTicTacToe): Coordinate {
        if (game.getLastCoordinates().first.isEmpty() && game.getLastCoordinates().second.isEmpty())
            return Coordinate(game.getBoard().getRows() / 1, game.getBoard().getColumns() / 2)

        val a = getAvailableMoves(game.getBoard(), game.getLastCoordinates())
                .parallelStream()
                .map { i -> Pair(minimax(game.makeMove(i.x, i.y), 2, false, game.getPlayerOnMove()), i) }

        return a.sorted { o1, o2 -> o2.first.compareTo(o1.first) }
                .findFirst()
                .get()
                .second
    }

    /**
     * The Minimax algorithm used to determine the next computer move.
     * TODO: possibly change the value of depth to make the Ai play correctly
     *
     * @param game
     *              an interfaced reference (AiTicTacToe) to a class used to
     *              determine the next moves for the computer to play
     * @param depth
     *              the amount of moves ahead the Ai should look to
     *              determine the next move; this value will greatly
     *              impact the delay between the player's move and the
     *              computer's move
     * @param max
     *              a boolean used to check where on the binary search tree
     *              the algorithm currently is to determine whether to
     *              terminate or to recursively call the function again
     *              to go down one more layer
     * @param playerOnMove
     *              a Field reference used as a way to abstract out
     *              finding either the optimal moves for crosses or
     *              circles; this way we can incorporate the option
     *              to have the computer play first, the player play
     *              first, or have an Ai play an Ai
     * @return an integer value that corresponds to the weighted value of
     *         a given move
     */
    private fun minimax(game: AiTicTacToe, depth: Int = 3, max: Boolean, playerOnMove: Field): Int {
        // base case at the root of the binary search tree
        if (depth == 0) return eval(game, playerOnMove)

        // recursive minimax call to further develop the tree
        val tmpResult = getAvailableMoves(game.getBoard(), game.getLastCoordinates())
                .map { i -> minimax(game.makeMove(i.x, i.y), depth - 1, !max, playerOnMove) }
                .sorted()

        if (max) return tmpResult.last()

        return tmpResult.first()
    }

    /**
     * A helper function that computes a weighted value to suggest
     * what move to make based on already placed moves.
     *
     * @param game
     *              an interfaced reference (AiTicTacToe) to a class used to
     *              determine the next moves for the computer to play
     * @param playerOnMove
     *              a Field reference used as a way to abstract out
     *              finding either the optimal moves for crosses or
     *              circles; this way we can incorporate the option
     *              to have the computer play first, the player play
     *              first, or have an Ai play an Ai
     * @return the weighted value for the next possible move
     */
    private fun eval(game: AiTicTacToe, playerOnMove: Field): Int {
        val winNumber = 5 // TODO: make it more universal
        val board = game.getBoard()
        val fields = board.getFields()
        var amount = 0

        // iterate through the rows of the board
        for (i in 0..board.getRows() - winNumber) {

            // iterate through the columns of the board
            for (j in 0..board.getColumns() - winNumber) {
                val rows = i.rangeTo(i + winNumber - 1).map { index -> fields[index][j] }
                val rowCount = rows.count { i -> i == playerOnMove }

                if (rowCount > 0) amount = rowCount

                val columns = j.rangeTo(j + winNumber - 1).map { index -> fields[i][index] }
                val columnCount = columns.count { i -> i == playerOnMove }

                if (columnCount > amount) amount = columnCount

                val diagonalRight = fields.diagonalRight(i, j, winNumber)
                val diagonalRightCount = diagonalRight.count { i -> i == playerOnMove }

                if (diagonalRightCount > amount) amount = diagonalRightCount

                val diagonalLeft = fields.diagonalLeft(i, j + winNumber - 1, winNumber)
                val diagonalLeftCount = diagonalLeft.count { i -> i == playerOnMove }

                if (diagonalLeftCount > amount) amount = diagonalLeftCount
            }
        }

        // println(amount)
        return amount
    }

    /**
     * Gets the moves that are available for the computer to make.
     *
     * @param board
     *              the reference to the current state of the board
     * @param lastCoords
     *              the coordinates for the two last moves placed on the board
     * @return a list of all of the next possible moves that can be made
     */
    private fun getAvailableMoves(board: Board, lastCoords: Pair<Coordinate, Coordinate>): List<Coordinate> =
            (board.getCoordsAround(lastCoords.first) + board.getCoordsAround(lastCoords.second))
                    .filter { i -> board.getField(i.x, i.y) == Field.ANON }
}