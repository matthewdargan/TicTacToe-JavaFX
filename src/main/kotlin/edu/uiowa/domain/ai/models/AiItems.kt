package edu.uiowa.domain.ai.models

import edu.uiowa.domain.ai.Ai
import tornadofx.ViewModel

/**
 * Data class that gives the controller classes a convenient organization
 * in order the display and store the locations of the crosses and circles.
 *
 * @param cross
 *              a generalized (interfaced) version of an Ai type class
 *              that pertains to a reference to a cross, which will be
 *              used to display a X in the GUI
 * @param circle
 *              a generalized (interfaced) version of an Ai type class
 *              that pertains to a reference to a circle, which will be
 *              used to display an O in the GUI
 * @return the ViewModel object corresponding to both the cross and circle references
 */
data class AiItems(val cross: Ai?, val circle: Ai?) : ViewModel()