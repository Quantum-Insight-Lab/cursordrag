package dev.patrickgold.florisboard.ime.text.gesture

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.patrickgold.florisboard.ime.core.GestureProcessor
import dev.patrickgold.florisboard.ime.core.InputManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BackspaceViewModel @Inject constructor(
    private val gestureProcessor: GestureProcessor,
    private val inputManager: InputManager
) : ViewModel() {
    
    private val _threshold = MutableStateFlow(0f)
    val threshold: StateFlow<Float> = _threshold.asStateFlow()
    
    fun setKeyWidth(width: Float) {
        _threshold.value = width * 0.3f
    }
    
    fun processGesture(type: GestureProcessor.GestureType, dx: Float, dy: Float) {
        viewModelScope.launch {
            if (dx > _threshold.value) {
                inputManager.deleteWord()
            } else if (dx > 0f) {
                // Single character deletion
                inputManager.moveCursor(-1)
            }
        }
    }
} 