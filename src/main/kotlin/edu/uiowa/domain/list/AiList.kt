package edu.uiowa.domain.list

import edu.uiowa.domain.ai.Ai

/**
 * An interface that models how to manipulate the various Ai
 * implementations in a linear fashion.
 */
interface AiList {

    /**
     * Gets the names of the Ai implementations that
     * are available.
     *
     * @return a set of the available Ai implementations
     */
    fun getAvailableAis(): Set<Class<out Ai>>
}