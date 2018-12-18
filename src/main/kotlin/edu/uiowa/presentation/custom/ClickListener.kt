package edu.uiowa.presentation.custom

/**
 * An interface used to model how to handle
 * user click interactions with the GUI.
 */
interface ClickListener {

    /**
     * Handles what should be done when the user
     * clicks somewhere on the GUI.
     *
     * @param x
     *              the horizontal dimension of the click
     * @param y
     *              the vertical dimension of the click
     */
    fun onClicked(x: Int, y: Int)
}