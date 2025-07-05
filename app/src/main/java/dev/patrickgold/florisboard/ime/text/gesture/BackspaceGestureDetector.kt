package dev.patrickgold.florisboard.ime.text.gesture

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import dev.patrickgold.florisboard.ime.core.GestureProcessor
import dev.patrickgold.florisboard.ime.ui.composable.GestureDetector

@Composable
fun BackspaceGestureDetector(
    modifier: Modifier = Modifier,
    keyWidth: Float,
    viewModel: BackspaceViewModel = hiltViewModel()
) {
    BackspaceContent(
        modifier = GestureDetector(
            modifier = modifier,
            gestureType = GestureProcessor.GestureType.BACKSPACE_DRAG,
            onGesture = viewModel::processGesture,
            enabled = true
        )
    )
}

@Composable
private fun BackspaceContent(modifier: Modifier = Modifier) {
    // Backspace rendering logic
} 