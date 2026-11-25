package com.retrocam.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.retrocam.app.ui.CameraScreen
import com.retrocam.app.ui.GalleryScreen
import com.retrocam.app.ui.SettingsScreen
import com.retrocam.app.ui.theme.RetroCamTheme

private enum class AppScreen {
    Camera,
    Gallery,
    Settings,
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RetroCamTheme {
                var currentScreen by rememberSaveable { mutableStateOf(AppScreen.Camera) }
                when (currentScreen) {
                    AppScreen.Camera -> CameraScreen(
                        lifecycleOwner = this@MainActivity,
                        onOpenGallery = { currentScreen = AppScreen.Gallery },
                        onOpenSettings = { currentScreen = AppScreen.Settings },
                    )
                    AppScreen.Gallery -> GalleryScreen(
                        onBack = { currentScreen = AppScreen.Camera },
                    )
                    AppScreen.Settings -> SettingsScreen(
                        onBack = { currentScreen = AppScreen.Camera },
                    )
                }
            }
        }
    }
}
