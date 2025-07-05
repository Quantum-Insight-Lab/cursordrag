package dev.patrickgold.florisboard.ime.text.keyboard

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import dev.patrickgold.florisboard.ime.core.GestureProcessor
import dev.patrickgold.florisboard.ime.ui.composable.GestureDetector

@Composable
fun KeyView(
    modifier: Modifier = Modifier,
    key: Key,
    onKeyPress: (Key) -> Unit,
    viewModel: KeyViewModel = hiltViewModel()
) {
    val gestureEnabled = key.code == KeyCode.SPACE
    
    KeyViewContent(
        modifier = if (gestureEnabled) {
            GestureDetector(
                modifier = modifier,
                gestureType = GestureProcessor.GestureType.SPACE_DRAG,
                onGesture = viewModel::processGesture,
                enabled = true
            )
        } else modifier,
        key = key,
        onKeyPress = onKeyPress
    )
}

@Composable
private fun KeyViewContent(
    modifier: Modifier = Modifier,
    key: Key,
    onKeyPress: (Key) -> Unit
) {
    // Key rendering logic
} 