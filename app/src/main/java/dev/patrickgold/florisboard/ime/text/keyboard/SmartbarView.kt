package dev.patrickgold.florisboard.ime.text.keyboard

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import dev.patrickgold.florisboard.ime.core.GestureProcessor
import dev.patrickgold.florisboard.ime.ui.composable.GestureDetector

@Composable
fun SmartbarView(
    modifier: Modifier = Modifier,
    viewModel: SmartbarViewModel = hiltViewModel()
) {
    val isDragEnabled by viewModel.isDragEnabled.collectAsState()
    
    SmartbarContent(
        modifier = if (isDragEnabled) {
            GestureDetector(
                modifier = modifier,
                gestureType = GestureProcessor.GestureType.SMARTBAR_DRAG,
                onGesture = viewModel::processGesture,
                enabled = true
            )
        } else modifier
    )
}

@Composable
private fun SmartbarContent(modifier: Modifier = Modifier) {
    // Smartbar rendering logic
} 