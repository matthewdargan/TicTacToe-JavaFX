package edu.uiowa.presentation.mainMenu

import edu.uiowa.domain.ai.models.AiItems
import edu.uiowa.domain.game.models.type.GameType
import edu.uiowa.domain.game.models.type.Type
import edu.uiowa.presentation.game.GameView
import edu.uiowa.presentation.list.ListView
import javafx.scene.layout.AnchorPane
import tornadofx.*

/**
 * A View class implementation that displays the main menu
 * of the game upon launch.
 *
 * @param title
 *              the title of the game
 */
class MainMenuView : View("Tic Tac Toe"){

    override val root: AnchorPane by fxml("/resources/fxml/main-menu.fxml")

    /**
     * Initialization of the class for convenience purposes.
     */
    init {
        with(root) {}
    }

    /**
     * Handles what to do if the user picks to play
     * the Human vs Human game mode.
     */
    fun handleHumanVsHuman() {
        val aiItems = AiItems(null, null)
        val scope = Scope()

        setInScope(aiItems, scope)
        this += find<GameView>(scope)
    }

    /**
     * Handles what to do if the user picks to play
     * the Human vs Ai game mode.
     */
    fun handleHumanVsAi() {
        val gameType = Type(GameType.AI_VS_PLAYER)
        val scope = Scope()

        setInScope(gameType, scope)
        this += find<ListView>(scope)
    }

    /**
     * Handles what to do if the user picks to play
     * the Ai vs Ai game mode.
     */
    fun handleAiVsAi() {
        val gameType = Type(GameType.AI_VS_AI)
        val scope = Scope()

        setInScope(gameType, scope)
        this += find<ListView>(scope)
    }
}