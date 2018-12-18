package edu.uiowa.presentation.list

import edu.uiowa.domain.ai.Ai
import edu.uiowa.domain.list.AiListManager
import tornadofx.Controller

/**
 * Controller for the ListView class to handle what to display
 * at the start of every game.
 */
class ListViewController : Controller() {

    private val classes = AiListManager.instance.getAvailableAis()

    /**
     * Gets the available Ai implementations that can be
     * chosen to play against.
     *
     * @return a list of the names of the available Ais
     */
    fun getAvailableAis(): List<String> = classes.map { i -> i.simpleName }

    /**
     * Gets an Ai's name one by one instead of all of
     * the available ones all at once.
     *
     * @param name
     *              the name of one of the Ais
     * @return the instance of the Ai that corresponds with the name
     *         passed in as an argument
     */
    fun getAiByName(name: String): Ai = classes.find { i -> i.simpleName == name }?.newInstance()!!
}