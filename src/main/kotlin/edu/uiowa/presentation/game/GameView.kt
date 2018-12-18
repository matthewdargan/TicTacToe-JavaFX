package edu.uiowa.presentation.game

import edu.uiowa.domain.game.models.Field
import edu.uiowa.presentation.custom.ClickListener
import edu.uiowa.presentation.custom.GameGrid
import edu.uiowa.presentation.mainMenu.MainMenuView
import javafx.scene.control.Button
import javafx.scene.layout.AnchorPane
import tornadofx.View
import tornadofx.hide

/**
 * Class used to control the Ai vs Ai mode by presenting the user
 * with some buttons to go between Ai moves.
 */
class GameView : View(), ClickListener {

    override val root: AnchorPane by fxml("/resources/fxml/game.fxml")
    val controller: GameController by inject()
    val gridPane: GameGrid by fxid()
    private val backwordsButton: Button by fxid("backwordsButton")
    private val playButton: Button by fxid("playButton")
    private val forwardsButton: Button by fxid("forwardsButton")

    /**
     * Initialization of the object which default to hiding
     * unless the Ai vs Ai game mode has been selected.
     */
    init {
        gridPane.clickListener = this
        render()

        if (!controller.isAiVsAi()) {
            backwordsButton.hide()
            playButton.hide()
            forwardsButton.hide()
        }
    }

    /**
     * Handles what to do if the user clicks on the screen
     * during Ai vs Ai game mode.
     *
     * @param x
     *              the horizontal dimension of the click
     * @param y
     *              the vertical dimension of the click
     */
    override fun onClicked(x: Int, y: Int) {
        controller.onMoveMade(x, y)
        render()
    }

    /**
     * Renders the game board along with the control buttons
     * for the Ai vs Ai mode.
     */
    fun render() {
        val winner = controller.getWinner()

        gridPane.setGameBoard(controller.getBoard())

        if (winner.first != Field.ANON) gridPane.drawLine(winner.second)
    }

    /**
     * Goes back in time one move if the user clicks
     * the back button.
     *
     * @return a boolean that says whether or not the game
     *         was able to go back in time one move or not
     */
    fun handleBack() = replaceWith(MainMenuView::class)

    /**
     * Resets the game if the user clicks the reset button.
     */
    fun handleReset() {
        controller.resetBoard()
        render()
    }

    /**
     * Starts game play if the user clicks the play button.
     */
    fun handlePlay() {
        controller.play()
        render()
    }

    /**
     * Plays one move or goes forward in time one move if
     * the user had decided to go back a few moves.
     */
    fun handleForward() {
        controller.makeOneMove()
        render()
    }

    /**
     * @deprecated unused function in the current version
     */
    fun handleBackwords() {
        // TODO: implement
    }
}