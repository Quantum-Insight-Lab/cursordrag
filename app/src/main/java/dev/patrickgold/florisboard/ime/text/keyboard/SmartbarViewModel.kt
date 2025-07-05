package dev.patrickgold.florisboard.ime.text.keyboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.patrickgold.florisboard.ime.core.GestureProcessor
import dev.patrickgold.florisboard.ime.preferences.GesturePrefs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SmartbarViewModel @Inject constructor(
    private val gestureProcessor: GestureProcessor,
    private val gesturePrefs: GesturePrefs
) : ViewModel() {
    
    private val _isDragEnabled = MutableStateFlow(false)
    val isDragEnabled: StateFlow<Boolean> = _isDragEnabled.asStateFlow()
    
    init {
        viewModelScope.launch {
            _isDragEnabled.value = gesturePrefs.smartbarDragEnabled
        }
    }
    
    fun processGesture(type: GestureProcessor.GestureType, dx: Float, dy: Float) {
        viewModelScope.launch {
            gestureProcessor.processGesture(type, dx, dy)
        }
    }
    
    fun toggleDragEnabled() {
        viewModelScope.launch {
            val newValue = !_isDragEnabled.value
            _isDragEnabled.value = newValue
            gesturePrefs.smartbarDragEnabled = newValue
        }
    }
} 