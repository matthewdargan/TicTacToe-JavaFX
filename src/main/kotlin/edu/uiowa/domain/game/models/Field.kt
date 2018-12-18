package edu.uiowa.domain.game.models

/**
 * Enum class that creates values for crosses, circles,
 * and the absence of either of those values.
 */
enum class Field {
    ANON, CROSS, CIRCLE;

    /**
     * Toggles between the Field enums.
     *
     * @return a Field enum that is the opposite of the enum that has
     *         just been played
     */
    fun toggle(): Field {
        if (this == Field.CROSS) return CIRCLE else return CROSS
    }
}