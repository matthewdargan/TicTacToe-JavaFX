package edu.uiowa.domain.game.models

import edu.uiowa.domain.common.extensions.set

/**
 * An implementation of the board interface that is used to control basic elements
 * of the game between moves and is used to update the state of the game to give
 * vital information to the GUI controllers.
 *
 * @param fields
 *              a two-dimensional list of all of the squares on the board
 */
data class GameBoard private constructor(private val fields: List<List<Field>>) : Board {

    /**
     * Gets the Field enums that have been played.
     *
     * @return a two-dimensional list of Field enums
     */
    override fun getFields(): List<List<Field>> = fields

    /**
     * Gets a Field enum from its position.
     *
     * @param x
     *              the horizontal positional value
     * @param y
     *              the vertical positional value
     * @return a Field enum based on its position
     */
    override fun getField(x: Int, y: Int): Field = fields[x][y]

    /**
     * Gets a list of Field enums around a position chosen
     * in a random order.
     *
     * @param x
     *              the horizontal positional value
     * @param y
     *              the vertical positional value
     * @return a list of the Field enums around a given position
     */
    override fun getFieldsAround(x: Int, y: Int): List<Field> = (x - 1).rangeTo(x + 1)
            .map { i ->
                (y - 1).rangeTo(y + 1)
                        .filter { j -> (i != x || j != y) && isInBoard(i, j) }
                        .map { j -> getField(i, j) }
            }.flatMap { i -> i }

    /**
     * Gets a list of Field enums around a chosen coordinate
     * in a random order.
     *
     * @param coord
     *              the coordinate chosen to find the Field enums
     *              around
     * @return a list of the Field enums around a given coordinate
     */
    override fun getFieldsAround(coord: Coordinate): List<Field> = getFieldsAround(coord.x, coord.y)

    /**
     * Gets a list of coordinates around a given position
     * in a random order.
     *
     * @param x
     *              the horizontal positional value
     * @param y
     *              the vertical positional value
     * @return a list of coordinates around a chosen position
     */
    override fun getCoordsAround(x: Int, y: Int): List<Coordinate> = (x - 1).rangeTo(x + 1)
            .map { i ->
                (y - 1).rangeTo(y + 1)
                        .filter { j -> (i != x || j != y) && isInBoard(i, j) }
                        .map { j -> Coordinate(i, j) }
            }.flatMap { i -> i }

    /**
     * Gets a list of coordinates around a given coordinate
     * in a random order.
     *
     * @param coord
     *              the coordinate chosen to find the
     *              coordinates around
     * @return a list of coordinates around a chosen position
     */
    override fun getCoordsAround(coord: Coordinate): List<Coordinate> = getCoordsAround(coord.x, coord.y)

    /**
     * Sets the field on the position given and gives the new
     * board status afterwards.
     *
     * @param x
     *              the horizontal positional value
     * @param y
     *              the vertical positional value
     * @param field
     *              the desired Field enum to use
     * @return a new board status
     */
    override fun setField(x: Int, y: Int, field: Field): Board = GameBoard.Companion.createBoard(fields.set(x, fields[x].set(y, field)))

    /**
     * Gets the number of rows.
     *
     * @return the number of rows
     */
    override fun getRows(): Int = fields.size

    /**
     * Gets number of columns.
     *
     * @return the number of columns
     */
    override fun getColumns(): Int = fields[0].size

    /**
     * Checks if a given position is on the board or
     * if it is out of bounds.
     *
     * @param x
     *              the horizontal positional value
     * @param y
     *              the vertical positional value
     * @return a boolean that says whether or not the position
     *         given is out of bounds or not
     */
    private fun isInBoard(x: Int, y: Int) = x >= 0 && x < getFields().size && y >= 0 && y < getFields()[x].size

    /**
     * Used to ensure that the methods contained in the companion object
     * are static methods.
     */
    companion object {

        /**
         * Creates a new empty board.
         *
         * @param rows
         *              the number of rows that should be created
         * @param columns
         *              the number of columns that should be created
         * @return a Board object of the number of rows and columns
         *         asked for
         */
        fun createNewBoard(rows: Int, columns: Int): Board = GameBoard(
                0.rangeTo(rows - 1).map { i -> 0.rangeTo(columns - 1).map { j -> Field.ANON } }
        )

        /**
         * Creates a board with preset fields.
         *
         * @param fields
         *              a list of already played Field enums
         * @return a Board object based on already placed crosses
         *         and circles
         */
        fun createBoard(fields: List<List<Field>>): Board = GameBoard(fields)
    }
}