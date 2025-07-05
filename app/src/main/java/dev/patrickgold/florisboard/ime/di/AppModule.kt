package dev.patrickgold.florisboard.ime.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.patrickgold.florisboard.ime.core.GestureProcessor
import dev.patrickgold.florisboard.ime.core.InputManager
import dev.patrickgold.florisboard.ime.metrics.AeonMetrics
import dev.patrickgold.florisboard.ime.preferences.GesturePrefs
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    
    @Provides
    @Singleton
    fun provideGesturePrefs(@ApplicationContext context: Context): GesturePrefs {
        return GesturePrefs(context)
    }
    
    @Provides
    @Singleton
    fun provideAeonMetrics(@ApplicationContext context: Context): AeonMetrics {
        return AeonMetrics(context)
    }
    
    @Provides
    @Singleton
    fun provideInputManager(): InputManager {
        return InputManager()
    }
    
    @Provides
    @Singleton
    fun provideGestureProcessor(
        inputManager: InputManager,
        gesturePrefs: GesturePrefs,
        metrics: AeonMetrics
    ): GestureProcessor {
        return GestureProcessor(inputManager, gesturePrefs, metrics)
    }
} 