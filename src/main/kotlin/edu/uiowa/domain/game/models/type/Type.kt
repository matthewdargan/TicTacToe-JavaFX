package edu.uiowa.domain.game.models.type

import tornadofx.ViewModel

/**
 * The class used to incorporate each GameType enum with
 * its corresponding ViewModel object in the GUI.
 *
 * @param gameType
 *              the GameType enum
 * @return the corresponding ViewModel for each GameType enum
 */
data class Type(val gameType: GameType) : ViewModel()