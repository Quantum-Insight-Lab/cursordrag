package dev.patrickgold.florisboard.ime.text.keyboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.patrickgold.florisboard.ime.core.GestureProcessor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KeyViewModel @Inject constructor(
    private val gestureProcessor: GestureProcessor
) : ViewModel() {
    
    private val _keyState = MutableStateFlow(KeyState())
    val keyState: StateFlow<KeyState> = _keyState.asStateFlow()
    
    fun processGesture(type: GestureProcessor.GestureType, dx: Float, dy: Float) {
        viewModelScope.launch {
            gestureProcessor.processGesture(type, dx, dy)
            _keyState.value = _keyState.value.copy(
                lastGesture = type,
                lastDragDistance = dx to dy
            )
        }
    }
    
    data class KeyState(
        val lastGesture: GestureProcessor.GestureType? = null,
        val lastDragDistance: Pair<Float, Float> = 0f to 0f
    )
} 