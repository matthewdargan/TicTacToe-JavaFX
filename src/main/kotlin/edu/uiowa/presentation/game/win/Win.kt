package edu.uiowa.presentation.game.win

import javafx.scene.control.Label
import javafx.scene.layout.StackPane
import tornadofx.View
import tornadofx.plusAssign

/**
 * Class used to package a View class together and display
 * the winner of the game on the GUI in a convenient way.
 *
 * @param player
 *              the winning player
 */
class Win(val player: String) : View() {

    override val root = StackPane()

    /**
     * Initialization of the object that will display the
     * winner of the game when created.
     */
    init {
        with(root) {
            root += Label("Winner: " + player)
        }
    }
}