package dev.patrickgold.florisboard.ime.core

import dev.patrickgold.florisboard.ime.metrics.AeonMetrics
import dev.patrickgold.florisboard.ime.preferences.GesturePrefs
import io.mockk.*
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GestureProcessorTest {
    
    private lateinit var inputManager: InputManager
    private lateinit var gesturePrefs: GesturePrefs
    private lateinit var metrics: AeonMetrics
    private lateinit var gestureProcessor: GestureProcessor
    
    @Before
    fun setup() {
        inputManager = mockk(relaxed = true)
        gesturePrefs = mockk(relaxed = true)
        metrics = mockk(relaxed = true)
        gestureProcessor = GestureProcessor(inputManager, gesturePrefs, metrics)
    }
    
    @Test
    fun `space drag should move cursor and track metrics`() = runTest {
        // Given
        every { gesturePrefs.cursorSensitivity } returns 10f
        
        // When
        gestureProcessor.processGesture(
            GestureProcessor.GestureType.SPACE_DRAG,
            dx = 30f,
            dy = 0f
        )
        
        // Then
        verify { 
            inputManager.moveCursor(3)
            metrics.trackCursorDrag(3, "space")
        }
    }
    
    @Test
    fun `smartbar drag should be disabled by default`() = runTest {
        // Given
        every { gesturePrefs.smartbarDragEnabled } returns false
        
        // When
        gestureProcessor.processGesture(
            GestureProcessor.GestureType.SMARTBAR_DRAG,
            dx = 100f,
            dy = 0f
        )
        
        // Then
        verify(exactly = 0) { inputManager.moveCursor(any()) }
        verify(exactly = 0) { metrics.trackCursorDrag(any(), any()) }
    }
    
    @Test
    fun `smartbar drag should work when enabled`() = runTest {
        // Given
        every { gesturePrefs.smartbarDragEnabled } returns true
        every { gesturePrefs.cursorSensitivity } returns 10f
        
        // When
        gestureProcessor.processGesture(
            GestureProcessor.GestureType.SMARTBAR_DRAG,
            dx = 100f,
            dy = 0f
        )
        
        // Then
        verify { 
            inputManager.moveCursor(10)
            metrics.trackCursorDrag(10, "smartbar")
        }
    }
    
    @Test
    fun `backspace drag should delete word`() = runTest {
        // When
        gestureProcessor.processGesture(
            GestureProcessor.GestureType.BACKSPACE_DRAG,
            dx = 50f,
            dy = 0f
        )
        
        // Then
        verify { 
            inputManager.deleteWord()
            metrics.trackCursorDrag(1, "backspace_word")
        }
    }
} 