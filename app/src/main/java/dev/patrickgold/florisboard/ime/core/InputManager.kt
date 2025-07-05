package dev.patrickgold.florisboard.ime.core

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InputManager @Inject constructor() {
    private val _cursorPosition = MutableStateFlow(0)
    val cursorPosition: StateFlow<Int> = _cursorPosition.asStateFlow()
    
    private val _selectionStart = MutableStateFlow(0)
    val selectionStart: StateFlow<Int> = _selectionStart.asStateFlow()
    
    private val _selectionEnd = MutableStateFlow(0)
    val selectionEnd: StateFlow<Int> = _selectionEnd.asStateFlow()
    
    fun moveCursor(dx: Int) {
        val newPosition = (_cursorPosition.value + dx).coerceAtLeast(0)
        _cursorPosition.value = newPosition
    }
    
    fun setSelection(start: Int, end: Int) {
        _selectionStart.value = start.coerceAtLeast(0)
        _selectionEnd.value = end.coerceAtLeast(start)
    }
    
    fun deleteSelection() {
        val start = _selectionStart.value
        val end = _selectionEnd.value
        if (start != end) {
            // Delete logic here
            _cursorPosition.value = start
            _selectionStart.value = start
            _selectionEnd.value = start
        }
    }
    
    fun deleteWord() {
        // Word deletion logic
        moveCursor(-1)
    }
} 