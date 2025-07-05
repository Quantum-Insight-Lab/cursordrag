package dev.patrickgold.florisboard.ime.ui.composable

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import dev.patrickgold.florisboard.ime.core.GestureProcessor

@Composable
fun GestureDetector(
    modifier: Modifier = Modifier,
    gestureType: GestureProcessor.GestureType,
    onGesture: (GestureProcessor.GestureType, Float, Float) -> Unit,
    enabled: Boolean = true
) {
    var dragDistance by remember { mutableStateOf(0f to 0f) }
    
    LaunchedEffect(dragDistance) {
        if (dragDistance.first != 0f || dragDistance.second != 0f) {
            onGesture(gestureType, dragDistance.first, dragDistance.second)
            dragDistance = 0f to 0f
        }
    }
    
    GestureContent(
        modifier = if (enabled) {
            modifier.pointerInput(gestureType) {
                detectDragGestures(
                    onDragStart = { },
                    onDragEnd = { },
                    onDragCancel = { },
                    onDrag = { change, dragAmount ->
                        change.consume()
                        dragDistance = dragDistance.first + dragAmount.x to dragDistance.second + dragAmount.y
                    }
                )
            }
        } else modifier
    )
}

@Composable
private fun GestureContent(modifier: Modifier = Modifier) {
    // Content rendering
} 