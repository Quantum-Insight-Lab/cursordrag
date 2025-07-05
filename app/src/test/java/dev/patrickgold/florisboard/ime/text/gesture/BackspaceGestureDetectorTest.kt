package dev.patrickgold.florisboard.ime.text.gesture

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class BackspaceGestureDetectorTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    
    @Test
    fun testWordDeleteThreshold() {
        var deleteCharCalled = false
        var deleteWordCalled = false
        
        composeTestRule.setContent {
            BackspaceGestureDetector(
                keyWidth = 100f,
                onDeleteChar = { deleteCharCalled = true },
                onDeleteWord = { deleteWordCalled = true }
            )
        }
        
        // Drag beyond threshold (0.3 * 100 = 30px)
        composeTestRule.onNodeWithTag("backspace_key")
            .performTouchInput { 
                swipe(start = center, end = center + offset(35f, 0f), durationMillis = 500)
            }
        
        assert(deleteWordCalled)
        assert(!deleteCharCalled)
    }
    
    @Test
    fun testCharDeleteBelowThreshold() {
        var deleteCharCalled = false
        var deleteWordCalled = false
        
        composeTestRule.setContent {
            BackspaceGestureDetector(
                keyWidth = 100f,
                onDeleteChar = { deleteCharCalled = true },
                onDeleteWord = { deleteWordCalled = true }
            )
        }
        
        // Drag below threshold
        composeTestRule.onNodeWithTag("backspace_key")
            .performTouchInput { 
                swipe(start = center, end = center + offset(20f, 0f), durationMillis = 500)
            }
        
        assert(deleteCharCalled)
        assert(!deleteWordCalled)
    }
} 