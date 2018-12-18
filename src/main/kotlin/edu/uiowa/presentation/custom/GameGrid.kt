package edu.uiowa.presentation.custom

import edu.uiowa.domain.game.models.Board
import edu.uiowa.domain.game.models.Coordinate
import edu.uiowa.domain.game.models.Field
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.layout.HBox
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.shape.Line
import javafx.scene.text.Font
import tornadofx.add
import tornadofx.onHover
import tornadofx.plusAssign

/**
 * Class that handles the display of crosses or circles when prompted
 * on the GUI and handles creating the grid drawing on the playing board.
 */
class GameGrid : Pane() {

    var clickListener: ClickListener? = null
    var size = 0.0

    /**
     * Sets the playing board and handles the display of crosses
     * or circles on the board when called.
     *
     * @param board
     *              the current board state
     */
    fun setGameBoard(board: Board) {
        size = this.prefWidth / (board.getColumns() - 1)

        with(this) {
            this.children.clear()

            add(VBox().apply {
                for (i in 0..board.getRows() - 1) {
                    this += HBox().apply {
                        for (j in 0..board.getColumns() - 1) {
                            this += Label().apply {
                                alignment = Pos.CENTER
                                this.prefHeight = size
                                this.prefWidth = size
                                this.setOnMouseClicked { clickListener!!.onClicked(i, j) }
                                this.font = Font(size - 5)
                                this.onHover { this.font = Font(size - 5) }

                                val field = board.getFields()[i][j]

                                when (field) {
                                    Field.CROSS -> {
                                        this.text = "X"
                                        this.styleClass.add("x")
                                    }

                                    Field.CIRCLE -> {
                                        this.text = "O"
                                        this.styleClass.add("o")
                                    }
                                }
                            }
                        }
                    }
                }
            })
        }
    }

    /**
     * Creates the grid lines on the board to show
     * locations that can be played.
     *
     * @param coord
     *              a list of all of the coordinates
     *              on the playing board
     */
    fun drawLine(coord: List<Coordinate>) {
        with(this) {
            this += Line().apply {
                this.fill = Color.BLACK
                this.strokeWidth = 2.0
                this.startX = (coord[0].y + 1) * size - size / 4
                this.endX = (coord.last().y + 1) * size - size / 4
                this.startY = (coord[0].x + 1) * size - size / 4
                this.endY = (coord.last().x + 1) * size - size / 4
                this.toFront() //TODO: Does bring fully to front
            }
        }
    }

    /**
     * Initialization of the object to ensure that it uses
     * the proper styling file before the grid is drawn
     * on the screen.
     */
    init {
        stylesheets.add("/resources/styles/game-grid.css")
    }
}