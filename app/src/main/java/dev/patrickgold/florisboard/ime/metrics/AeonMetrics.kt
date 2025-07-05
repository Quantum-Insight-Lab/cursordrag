package dev.patrickgold.florisboard.ime.metrics

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AeonMetrics(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences(
        "aeon_metrics", Context.MODE_PRIVATE
    )
    private val scope = CoroutineScope(Dispatchers.IO)
    
    fun trackCursorDrag(dx: Int, source: String) {
        scope.launch {
            val event = Event.CursorDrag(dx, source)
            storeEvent(event)
        }
    }
    
    private fun storeEvent(event: Event) {
        val events = prefs.getString("events", "") ?: ""
        val newEvents = "$events${event.toJson()}\n"
        prefs.edit().putString("events", newEvents).apply()
    }
    
    sealed class Event {
        data class CursorDrag(val dx: Int, val source: String) : Event() {
            fun toJson(): String = """{"type":"cursor_drag","dx":$dx,"source":"$source"}"""
        }
    }
} 