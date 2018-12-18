package edu.uiowa.presentation.list

import edu.uiowa.domain.ai.models.AiItems
import edu.uiowa.domain.game.models.type.GameType
import edu.uiowa.domain.game.models.type.Type
import edu.uiowa.presentation.game.GameView
import edu.uiowa.presentation.mainMenu.MainMenuView
import javafx.collections.FXCollections
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.ListView
import javafx.scene.input.MouseEvent
import javafx.scene.layout.AnchorPane
import tornadofx.*

/**
 * View class implementation that displays the game mode options upon
 * starting the game.
 */
class ListView : View() {

    override val root: AnchorPane by fxml("/resources/fxml/list.fxml")
    private val controller: ListViewController by inject<ListViewController>()
    private val crossSplit: AnchorPane by fxid("cross")
    private val listCross: ListView<String> by fxid("listCross")
    private val listCircle: ListView<String> by fxid("listCircle")
    private val continueButton: Button by fxid("continueButton")
    private val type: Type by inject()

    /**
     * Initialization of the class that will display the View no matter
     * what after launching the game.
     */
    init {
        with(root) {
            val items = FXCollections.observableArrayList(controller.getAvailableAis())

            when {
                type.gameType == GameType.AI_VS_PLAYER -> {
                    crossSplit.children.clear()
                    crossSplit += Label("Player").apply {
                        this.styleClass.add("player_label")
                        this.alignment = Pos.CENTER
                        AnchorPane.setLeftAnchor(this, 0.0)
                        AnchorPane.setRightAnchor(this, 0.0)
                        AnchorPane.setTopAnchor(this, 0.0)
                        AnchorPane.setBottomAnchor(this, 0.0)
                    }

                    listCircle.items = items
                }

                else -> {
                    listCircle.items = items
                    listCross.items = items
                }
            }
        }
    }

    /**
     * Goes back to the list of game mode options
     *
     * @deprecated unused function in the current version
     */
    fun handleBack() = replaceWith(MainMenuView::class)

    /**
     * Passes control over to the GameView class to the game mode
     * that corresponds with what the user picks.
     */
    fun handleContinue() {
        val cross = if (type.gameType == GameType.AI_VS_AI) controller.getAiByName(listCross.selectionModel.selectedItem) else null
        val circle = controller.getAiByName(listCircle.selectionModel.selectedItem)
        val aiItems = AiItems(cross, circle)
        val scope = Scope()

        setInScope(aiItems, scope)
        this += find<GameView>(scope)
    }

    /**
     * Option used to allow the player to go on the second turn.
     *
     * @param event
     *              the event upon mouse click that chooses
     *              which turn the player will be
     * @deprecated unused function in the current version
     */
    fun handleCircle(event: MouseEvent) = toggleContinue()

    /**
     * Option used to allow the player to go on the first turn.
     *
     * @param event
     *              the event upon mouse click that chooses
     *              which turn the player will be
     * @deprecated unused function in the current version
     */
    fun handleCross(event: MouseEvent) = toggleContinue()

    /**
     * Handles whether the player can continue the game with a button
     * if he or she has already placed a move and they are ready for
     * the Ai to make its move.
     */
    private fun toggleContinue() {
        continueButton.isDisable = (type.gameType == GameType.AI_VS_AI && !(listCircle.selectionModel.selectedItem is String
                && listCross.selectionModel.selectedItem is String)
                || (type.gameType == GameType.AI_VS_PLAYER && !(listCircle.selectionModel.selectedItem is String)))
    }
}