package com.retrocam.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.retrocam.app.recipe.FinalRecipe
import com.retrocam.app.recipe.RecipeCompositor
import com.retrocam.app.recipe.RecipeRepository
import com.retrocam.app.recipe.SelectionStore
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
                val catalog = remember { RecipeRepository.loadFromAssets(applicationContext.assets) }
                val selectionStore = remember { SelectionStore(applicationContext) }
                val initialSelection = remember { selectionStore.load() }

                var selectedCameraId by rememberSaveable {
                    mutableStateOf(
                        initialSelection.cameraId
                            ?.takeIf { id -> catalog.cameras.any { it.id == id } }
                            ?: catalog.cameras.firstOrNull()?.id,
                    )
                }
                var selectedLensId by rememberSaveable {
                    mutableStateOf(
                        initialSelection.lensId
                            ?.takeIf { id -> catalog.lenses.any { it.id == id } }
                            ?: catalog.lenses.firstOrNull()?.id,
                    )
                }
                var selectedFilmId by rememberSaveable {
                    mutableStateOf(
                        initialSelection.filmId
                            ?.takeIf { id -> catalog.films.any { it.id == id } }
                            ?: catalog.films.firstOrNull()?.id,
                    )
                }

                val selectedCamera = catalog.cameras.firstOrNull { it.id == selectedCameraId }
                    ?: catalog.cameras.firstOrNull()
                val selectedLens = catalog.lenses.firstOrNull { it.id == selectedLensId }
                    ?: catalog.lenses.firstOrNull()
                val selectedFilm = catalog.films.firstOrNull { it.id == selectedFilmId }
                    ?: catalog.films.firstOrNull()

                val activeRecipe = remember(selectedCamera, selectedLens, selectedFilm) {
                    if (selectedCamera != null && selectedLens != null && selectedFilm != null) {
                        RecipeCompositor.compose(selectedCamera, selectedLens, selectedFilm)
                    } else {
                        FinalRecipe.NEUTRAL
                    }
                }

                var currentScreen by rememberSaveable { mutableStateOf(AppScreen.Camera) }
                when (currentScreen) {
                    AppScreen.Camera -> CameraScreen(
                        lifecycleOwner = this@MainActivity,
                        catalog = catalog,
                        selectedCamera = selectedCamera,
                        selectedLens = selectedLens,
                        selectedFilm = selectedFilm,
                        activeRecipe = activeRecipe,
                        onSelectCamera = { camera ->
                            selectedCameraId = camera.id
                            selectionStore.save(camera.id, selectedLens?.id, selectedFilm?.id)
                        },
                        onSelectLens = { lens ->
                            selectedLensId = lens.id
                            selectionStore.save(selectedCamera?.id, lens.id, selectedFilm?.id)
                        },
                        onSelectFilm = { film ->
                            selectedFilmId = film.id
                            selectionStore.save(selectedCamera?.id, selectedLens?.id, film.id)
                        },
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
