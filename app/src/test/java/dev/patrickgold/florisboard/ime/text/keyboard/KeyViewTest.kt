package dev.patrickgold.florisboard.ime.text.keyboard

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class KeyViewTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    
    @Test
    fun testSpaceKeyDragSlow() {
        var cursorMoved = 0
        composeTestRule.setContent {
            KeyView(
                key = Key(code = KeyCode.SPACE),
                onKeyPress = { },
                onCursorMove = { cursorMoved += it }
            )
        }
        
        // Simulate slow drag (30px)
        composeTestRule.onNodeWithTag("space_key")
            .performTouchInput { 
                swipe(start = center, end = center + offset(30f, 0f), durationMillis = 1000)
            }
        
        assert(cursorMoved in 2..4) // 30px / 10f ≈ 3 chars
    }
    
    @Test
    fun testSpaceKeyDragFast() {
        var cursorMoved = 0
        composeTestRule.setContent {
            KeyView(
                key = Key(code = KeyCode.SPACE),
                onKeyPress = { },
                onCursorMove = { cursorMoved += it }
            )
        }
        
        // Simulate fast drag (600px)
        composeTestRule.onNodeWithTag("space_key")
            .performTouchInput { 
                swipe(start = center, end = center + offset(600f, 0f), durationMillis = 100)
            }
        
        assert(cursorMoved in 58..62) // 600px / 10f ≈ 60 chars
    }
} 