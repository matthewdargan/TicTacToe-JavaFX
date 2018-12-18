package edu.uiowa.presentation

import edu.uiowa.presentation.mainMenu.MainMenuView
import javafx.application.Application
import javafx.scene.text.Font
import javafx.stage.Stage
import tornadofx.App

/**
 * The main class used to start running the entire game.
 */
class Run: App(MainMenuView::class) {

    /**
     * Handles launch behaviors and what to do with
     * the JavaFX Stage object.
     *
     * @param stage
     *              the initial JavaFX Stage object
     */
    override fun start(stage: Stage) {
        super.start(stage)
        stage.isResizable = false
    }

    /**
     * Sets the font styles that should be used for
     * displaying text in the game.
     */
    init {
        Font.loadFont(Run::class.java.getResource("/resources/fonts/TicTacToe.ttf").toExternalForm(), 10.0)
        Font.loadFont(Run::class.java.getResource("/resources/fonts/super-webcomic-bros.regular.ttf").toExternalForm(), 10.0).name
    }

    /**
     * Calls the main method of the class instead of having
     * a top-level main method in order to deal with
     * awkward JavaFX/TornadoFX behaviors.
     */
    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            Application.launch(Run::class.java, *args)
        }
    }
}