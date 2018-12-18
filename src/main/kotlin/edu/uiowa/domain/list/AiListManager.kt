package edu.uiowa.domain.list

import edu.uiowa.domain.ai.Ai
import org.reflections.Reflections

/**
 * An implementation of the AiList interface that
 * determines how the handle providing the names
 * of the available Ai implementations.
 */
class AiListManager private constructor() : AiList {

    /**
     * An overridden function that gets the names of the
     * available Ai implementations.
     *
     * @return a set of the available Ai implementations
     */
    override fun getAvailableAis(): Set<Class<out Ai>> {
        val reflections = Reflections("edu.uiowa.domain.ai")

        return reflections.getSubTypesOf<Ai>(Ai::class.java)
    }

    /**
     * Creates a static reference of an object used to list the
     * names of the Ai implementations that are available.
     */
    companion object {
        val instance = AiListManager()
    }
}