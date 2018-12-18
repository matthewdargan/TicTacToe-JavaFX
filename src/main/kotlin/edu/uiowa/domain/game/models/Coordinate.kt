package edu.uiowa.domain.game.models

/**
 * Data class that packages a position's horizontal and
 * vertical aspects into one place.
 *
 * @param x
 *              the horizontal positional value
 * @param y
 *              the vertical positional value
 */
data class Coordinate(val x: Int, val y: Int) {

    /**
     * Takes the positional values of the class
     * and determines whether or not there is a
     * cross or circle at that location.
     *
     * @return a boolean that tells if the position
     *         has a cross or circle or not
     */
    fun isEmpty() = (x < 0 || y < 0)
}