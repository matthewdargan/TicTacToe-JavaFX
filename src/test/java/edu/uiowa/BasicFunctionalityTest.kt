package edu.uiowa

import edu.uiowa.presentation.Run
import edu.uiowa.presentation.list.ListViewController
import javafx.application.Platform
import org.junit.Test

class BasicFunctionalityTest {

    @Test
    fun checkAiNames() {
        val listViewController = ListViewController()
        val expected = listOf("Minimax", "RandomAi")
        val actualAis = listViewController.getAvailableAis()

        for (ai in actualAis) {
            assert(expected.contains(ai))
        }
    }

    @Test
    fun checkGameProcess() {
        // check that we can run the game and exit safely
        val game = Run()
        Platform.exit()
    }
}