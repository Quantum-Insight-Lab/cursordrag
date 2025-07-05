package dev.patrickgold.florisboard.ime.preferences

import android.content.Context
import android.content.SharedPreferences

class GesturePrefs(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences(
        "gesture_prefs", Context.MODE_PRIVATE
    )
    
    var spaceSwipeAction: SwipeAction
        get() = SwipeAction.valueOf(
            prefs.getString("space_swipe_action", SwipeAction.CURSOR_MOVE.name) ?: SwipeAction.CURSOR_MOVE.name
        )
        set(value) = prefs.edit().putString("space_swipe_action", value.name).apply()
    
    var smartbarDragEnabled: Boolean
        get() = prefs.getBoolean("smartbar_cursor_drag", false)
        set(value) = prefs.edit().putBoolean("smartbar_cursor_drag", value).apply()
    
    var cursorSensitivity: Float
        get() = prefs.getFloat("cursor_sensitivity", 10f)
        set(value) = prefs.edit().putFloat("cursor_sensitivity", value).apply()
} 