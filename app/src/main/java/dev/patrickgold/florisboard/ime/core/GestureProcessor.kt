package dev.patrickgold.florisboard.ime.core

import dev.patrickgold.florisboard.ime.preferences.GesturePrefs
import dev.patrickgold.florisboard.ime.metrics.AeonMetrics
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GestureProcessor @Inject constructor(
    private val inputManager: InputManager,
    private val gesturePrefs: GesturePrefs,
    private val metrics: AeonMetrics
) {
    private val gestureStrategies = mapOf(
        GestureType.SPACE_DRAG to SpaceDragStrategy(),
        GestureType.SMARTBAR_DRAG to SmartbarDragStrategy(),
        GestureType.BACKSPACE_DRAG to BackspaceDragStrategy()
    )
    
    fun processGesture(type: GestureType, dx: Float, dy: Float) {
        val strategy = gestureStrategies[type]
        strategy?.execute(dx, dy, inputManager, gesturePrefs, metrics)
    }
    
    sealed class GestureType {
        object SPACE_DRAG : GestureType()
        object SMARTBAR_DRAG : GestureType()
        object BACKSPACE_DRAG : GestureType()
    }
    
    private interface GestureStrategy {
        fun execute(dx: Float, dy: Float, inputManager: InputManager, prefs: GesturePrefs, metrics: AeonMetrics)
    }
    
    private class SpaceDragStrategy : GestureStrategy {
        override fun execute(dx: Float, dy: Float, inputManager: InputManager, prefs: GesturePrefs, metrics: AeonMetrics) {
            val sensitivity = prefs.cursorSensitivity
            val chars = (dx / sensitivity).toInt()
            inputManager.moveCursor(chars)
            metrics.trackCursorDrag(chars, "space")
        }
    }
    
    private class SmartbarDragStrategy : GestureStrategy {
        override fun execute(dx: Float, dy: Float, inputManager: InputManager, prefs: GesturePrefs, metrics: AeonMetrics) {
            if (prefs.smartbarDragEnabled) {
                val sensitivity = prefs.cursorSensitivity
                val chars = (dx / sensitivity).toInt()
                inputManager.moveCursor(chars)
                metrics.trackCursorDrag(chars, "smartbar")
            }
        }
    }
    
    private class BackspaceDragStrategy : GestureStrategy {
        override fun execute(dx: Float, dy: Float, inputManager: InputManager, prefs: GesturePrefs, metrics: AeonMetrics) {
            if (dx > 0) {
                inputManager.deleteWord()
                metrics.trackCursorDrag(1, "backspace_word")
            }
        }
    }
} 