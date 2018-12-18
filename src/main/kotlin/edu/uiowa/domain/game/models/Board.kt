package edu.uiowa.domain.game.models

/**
 * An interface that is a model for how to represent
 * the current status of the moves played.
 */
interface Board {

    /**
     * Gets the Field enums that have been played.
     *
     * @return a two-dimensional list of Field enums
     */
    fun getFields(): List<List<Field>>

    /**
     * Gets a Field enum from its position.
     *
     * @param x
     *              the horizontal positional value
     * @param y
     *              the vertical positional value
     * @return a Field enum based on its position
     */
    fun getField(x: Int, y: Int): Field

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
    fun getFieldsAround(x: Int, y: Int): List<Field>

    /**
     * Gets a list of Field enums around a chosen coordinate
     * in a random order.
     *
     * @param coord
     *              the coordinate chosen to find the Field enums
     *              around
     * @return a list of the Field enums around a given coordinate
     */
    fun getFieldsAround(coord: Coordinate): List<Field>

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
    fun getCoordsAround(x: Int, y: Int): List<Coordinate>

    /**
     * Gets a list of coordinates around a given coordinate
     * in a random order.
     *
     * @param coord
     *              the coordinate chosen to find the
     *              coordinates around
     * @return a list of coordinates around a chosen position
     */
    fun getCoordsAround(coord: Coordinate): List<Coordinate>

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
    fun setField(x: Int, y: Int, field: Field): Board

    /**
     * Gets the number of rows.
     *
     * @return the number of rows
     */
    fun getRows(): Int

    /**
     * Gets number of columns.
     *
     * @return the number of columns
     */
    fun getColumns(): Int
}