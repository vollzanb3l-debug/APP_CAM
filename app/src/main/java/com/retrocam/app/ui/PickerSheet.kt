package com.retrocam.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.retrocam.app.recipe.CameraSearchFilter
import com.retrocam.app.recipe.CameraSpec
import com.retrocam.app.recipe.FavoriteIds
import com.retrocam.app.recipe.FavoritesSorter
import com.retrocam.app.recipe.FilmSpec
import com.retrocam.app.recipe.LensSpec
import com.retrocam.app.recipe.RecipeCatalog
import com.retrocam.app.ui.theme.RetroAmber
import com.retrocam.app.ui.theme.RetroDarkBackground
import com.retrocam.app.ui.theme.RetroSurface
import com.retrocam.app.ui.theme.RetroSurfaceBorder
import com.retrocam.app.ui.theme.RetroTextPrimary
import com.retrocam.app.ui.theme.RetroTextSecondary

@Composable
fun PickerSheet(
    catalog: RecipeCatalog,
    selectedCamera: CameraSpec?,
    selectedLens: LensSpec?,
    selectedFilm: FilmSpec?,
    onSelectCamera: (CameraSpec) -> Unit,
    onSelectLens: (LensSpec) -> Unit,
    onSelectFilm: (FilmSpec) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    favoriteIds: FavoriteIds = FavoriteIds(),
) {
    var searchQuery by rememberSaveable { mutableStateOf("") }

    val orderedCameras = remember(catalog.cameras, favoriteIds.cameraIds) {
        FavoritesSorter.sortCameras(catalog.cameras, favoriteIds)
    }
    val filteredCameras = remember(orderedCameras, searchQuery) {
        CameraSearchFilter.filterCameras(orderedCameras, searchQuery)
    }
    val suggestedTags = remember(catalog.cameras) {
        CameraSearchFilter.topSuggestedTags(catalog.cameras, limit = 8)
    }
    val orderedLenses = remember(catalog.lenses, favoriteIds.lensIds) {
        FavoritesSorter.sortLenses(catalog.lenses, favoriteIds)
    }
    val orderedFilms = remember(catalog.films, favoriteIds.filmIds) {
        FavoritesSorter.sortFilms(catalog.films, favoriteIds)
    }

    Surface(
        modifier = modifier.fillMaxWidth(),
        color = RetroDarkBackground.copy(alpha = 0.96f),
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        tonalElevation = 6.dp,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = RetroSurfaceBorder,
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                )
                .navigationBarsPadding()
                .padding(top = 12.dp, bottom = 16.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Kombinasi Resep",
                    style = MaterialTheme.typography.titleMedium,
                    color = RetroTextPrimary,
                )
                Spacer(modifier = Modifier.weight(1f))
                TextButton(onClick = onDismiss) {
                    Text(text = "Selesai", color = RetroAmber)
                }
            }

            // Search bar for 100-camera catalog (name, brand, alias, lookTags)
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                singleLine = true,
                placeholder = {
                    Text(
                        text = "Cari kamera, brand, alias, atau lookTag...",
                        style = MaterialTheme.typography.bodySmall,
                        color = RetroTextSecondary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        Text(
                            text = "Hapus",
                            style = MaterialTheme.typography.labelSmall,
                            color = RetroAmber,
                            modifier = Modifier
                                .clickable { searchQuery = "" }
                                .padding(horizontal = 10.dp, vertical = 6.dp),
                        )
                    }
                },
                textStyle = MaterialTheme.typography.bodySmall.copy(color = RetroTextPrimary),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = RetroAmber,
                    unfocusedBorderColor = RetroSurfaceBorder,
                    focusedContainerColor = RetroSurface,
                    unfocusedContainerColor = RetroSurface,
                    cursorColor = RetroAmber,
                ),
            )

            if (suggestedTags.isNotEmpty()) {
                Spacer(modifier = Modifier.height(6.dp))
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    items(suggestedTags, key = { it }) { tag ->
                        val active = searchQuery.trim().equals(tag, ignoreCase = true)
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(if (active) RetroAmber else RetroSurface)
                                .border(
                                    width = 1.dp,
                                    color = if (active) RetroAmber else RetroSurfaceBorder,
                                    shape = RoundedCornerShape(12.dp),
                                )
                                .clickable {
                                    searchQuery = if (active) "" else tag
                                }
                                .padding(horizontal = 8.dp, vertical = 4.dp),
                        ) {
                            Text(
                                text = "#$tag",
                                style = MaterialTheme.typography.labelSmall,
                                color = if (active) RetroDarkBackground else RetroTextSecondary,
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            val camIndex = filteredCameras.indexOfFirst { it.id == selectedCamera?.id }.let {
                if (it >= 0) it + 1 else 0
            }
            val camTitle = if (searchQuery.isBlank()) {
                "CAMERA"
            } else {
                "CAMERA (${filteredCameras.size}/${orderedCameras.size})"
            }
            PickerSectionHeader(
                title = camTitle,
                current = camIndex,
                total = filteredCameras.size,
            )
            if (filteredCameras.isEmpty()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Tidak ada kamera cocok untuk \"$searchQuery\".",
                        style = MaterialTheme.typography.bodySmall,
                        color = RetroTextSecondary,
                        modifier = Modifier.weight(1f),
                    )
                    TextButton(onClick = { searchQuery = "" }) {
                        Text(text = "Reset", color = RetroAmber)
                    }
                }
            } else {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    itemsIndexed(filteredCameras, key = { _, item -> item.id }) { _, camera ->
                        val isSelected = camera.id == selectedCamera?.id
                        PickerCard(
                            label = camera.displayName,
                            subtitle = camera.brand,
                            selected = isSelected,
                            onClick = { onSelectCamera(camera) },
                        ) { tint ->
                            CameraGlyph(tint = tint, modifier = Modifier.size(28.dp))
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            val lensIndex = orderedLenses.indexOfFirst { it.id == selectedLens?.id }.let {
                if (it >= 0) it + 1 else 0
            }
            PickerSectionHeader(
                title = "LENS",
                current = lensIndex,
                total = orderedLenses.size,
            )
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                itemsIndexed(orderedLenses, key = { _, item -> item.id }) { _, lens ->
                    val isSelected = lens.id == selectedLens?.id
                    PickerCard(
                        label = lens.displayName,
                        selected = isSelected,
                        onClick = { onSelectLens(lens) },
                    ) { tint ->
                        LensGlyph(tint = tint, modifier = Modifier.size(30.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            val filmIndex = orderedFilms.indexOfFirst { it.id == selectedFilm?.id }.let {
                if (it >= 0) it + 1 else 0
            }
            PickerSectionHeader(
                title = "FILM",
                current = filmIndex,
                total = orderedFilms.size,
            )
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                itemsIndexed(orderedFilms, key = { _, item -> item.id }) { _, film ->
                    val isSelected = film.id == selectedFilm?.id
                    PickerCard(
                        label = film.displayName,
                        selected = isSelected,
                        onClick = { onSelectFilm(film) },
                    ) { tint ->
                        FilmGlyph(tint = tint, modifier = Modifier.size(30.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun PickerSectionHeader(
    title: String,
    current: Int,
    total: Int,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelMedium,
            color = RetroTextSecondary,
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "%02d / %02d".format(current, total),
            style = MaterialTheme.typography.labelMedium,
            color = RetroAmber,
        )
    }
}

@Composable
private fun PickerCard(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    subtitle: String? = null,
    icon: @Composable (tint: androidx.compose.ui.graphics.Color) -> Unit,
) {
    val borderColor = if (selected) RetroAmber else RetroSurfaceBorder
    val iconTint = if (selected) RetroAmber else RetroTextPrimary
    val textColor = if (selected) RetroAmber else RetroTextPrimary

    Column(
        modifier = Modifier
            .width(110.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(RetroSurface)
            .border(width = if (selected) 1.5.dp else 1.dp, color = borderColor, shape = RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 8.dp, vertical = 9.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        icon(iconTint)
        Spacer(modifier = Modifier.height(5.dp))
        if (subtitle != null) {
            Text(
                text = subtitle,
                style = MaterialTheme.typography.labelSmall,
                color = RetroTextSecondary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = textColor,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}
