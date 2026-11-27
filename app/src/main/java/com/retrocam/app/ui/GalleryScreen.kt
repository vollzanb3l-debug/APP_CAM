package com.retrocam.app.ui

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.util.LruCache
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.retrocam.app.recipe.RecipeCatalog
import com.retrocam.app.storage.GalleryPagination
import com.retrocam.app.storage.GalleryPhotoItem
import com.retrocam.app.storage.GalleryRepository
import com.retrocam.app.ui.theme.RetroAmber
import com.retrocam.app.ui.theme.RetroDarkBackground
import com.retrocam.app.ui.theme.RetroSurface
import com.retrocam.app.ui.theme.RetroSurfaceBorder
import com.retrocam.app.ui.theme.RetroTextPrimary
import com.retrocam.app.ui.theme.RetroTextSecondary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun GalleryScreen(
    catalog: RecipeCatalog,
    onBack: () -> Unit,
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val repository = remember(context) { GalleryRepository(context) }
    val thumbnailCache = remember { LruCache<Long, Bitmap>(48) }

    var photos by remember { mutableStateOf<List<GalleryPhotoItem>>(emptyList()) }
    var nextOffset by remember { mutableIntStateOf(0) }
    var hasMore by remember { mutableStateOf(false) }
    var totalCount by remember { mutableIntStateOf(0) }
    var isLoading by remember { mutableStateOf(false) }
    var noticeMessage by remember { mutableStateOf<String?>(null) }
    var selectedPhoto by remember { mutableStateOf<GalleryPhotoItem?>(null) }

    val gridState = rememberLazyGridState()

    fun reloadGallery() {
        if (isLoading) return
        scope.launch {
            isLoading = true
            noticeMessage = null
            thumbnailCache.evictAll()
            val page = withContext(Dispatchers.IO) {
                repository.loadPage(
                    offset = 0,
                    pageSize = GalleryPagination.DEFAULT_PAGE_SIZE,
                    catalog = catalog,
                )
            }
            photos = page.items
            nextOffset = page.nextOffset
            hasMore = page.hasMore
            totalCount = page.totalCount
            isLoading = false
        }
    }

    fun loadNextPage() {
        if (isLoading || !hasMore) return
        scope.launch {
            isLoading = true
            val page = withContext(Dispatchers.IO) {
                repository.loadPage(
                    offset = nextOffset,
                    pageSize = GalleryPagination.DEFAULT_PAGE_SIZE,
                    catalog = catalog,
                )
            }
            val existingIds = photos.map { it.mediaId }.toHashSet()
            val appended = page.items.filterNot { it.mediaId in existingIds }
            photos = photos + appended
            nextOffset = page.nextOffset
            hasMore = page.hasMore
            totalCount = page.totalCount
            isLoading = false
        }
    }

    fun pruneMissingPhoto(missingId: Long) {
        thumbnailCache.remove(missingId)
        photos = GalleryPagination.removeMissingById(photos, missingId) { it.mediaId }
        if (selectedPhoto?.mediaId == missingId) {
            selectedPhoto = null
        }
        noticeMessage = "Foto sudah dihapus dari penyimpanan perangkat."
    }

    LaunchedEffect(Unit) {
        reloadGallery()
    }

    val shouldTriggerPrefetch by remember {
        derivedStateOf {
            GalleryPagination.shouldLoadMore(
                lastVisibleIndex = gridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index,
                loadedCount = photos.size,
                hasMore = hasMore,
                isLoading = isLoading,
            )
        }
    }

    LaunchedEffect(shouldTriggerPrefetch) {
        if (shouldTriggerPrefetch) {
            loadNextPage()
        }
    }

    BackHandler(enabled = selectedPhoto != null) {
        selectedPhoto = null
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        color = RetroDarkBackground,
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    TextButton(onClick = onBack) {
                        Text(text = "← Kembali", color = RetroAmber)
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Galeri RetroCam",
                            style = MaterialTheme.typography.titleMedium,
                            color = RetroTextPrimary,
                        )
                        if (totalCount > 0) {
                            Text(
                                text = "${photos.size} / $totalCount foto",
                                style = MaterialTheme.typography.labelSmall,
                                color = RetroTextSecondary,
                            )
                        }
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    TextButton(
                        onClick = { reloadGallery() },
                        enabled = !isLoading,
                    ) {
                        Text(text = "Segarkan", color = RetroTextPrimary)
                    }
                }

                noticeMessage?.let { msg ->
                    Text(
                        text = msg,
                        style = MaterialTheme.typography.bodySmall,
                        color = RetroAmber,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 4.dp, vertical = 4.dp),
                    )
                }

                when {
                    isLoading && photos.isEmpty() -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center,
                        ) {
                            CircularProgressIndicator(color = RetroAmber)
                        }
                    }

                    photos.isEmpty() -> {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(24.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text(
                                text = "Belum ada foto",
                                style = MaterialTheme.typography.headlineSmall,
                                color = RetroTextPrimary,
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Folder Pictures/RetroCam masih kosong. Ambil foto dari layar kamera untuk melihatnya di sini.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = RetroTextSecondary,
                                textAlign = TextAlign.Center,
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(
                                onClick = { reloadGallery() },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = RetroAmber,
                                    contentColor = RetroDarkBackground,
                                ),
                            ) {
                                Text(text = "Muat Ulang")
                            }
                        }
                    }

                    else -> {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            state = gridState,
                            contentPadding = PaddingValues(vertical = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier.fillMaxSize(),
                        ) {
                            items(
                                items = photos,
                                key = { it.mediaId },
                            ) { item ->
                                GalleryPhotoCard(
                                    item = item,
                                    repository = repository,
                                    thumbnailCache = thumbnailCache,
                                    onMissing = { pruneMissingPhoto(item.mediaId) },
                                    onClick = {
                                        scope.launch {
                                            val stillExists = withContext(Dispatchers.IO) {
                                                repository.exists(item.uri)
                                            }
                                            if (stillExists) {
                                                noticeMessage = null
                                                selectedPhoto = item
                                            } else {
                                                pruneMissingPhoto(item.mediaId)
                                            }
                                        }
                                    },
                                )
                            }

                            if (hasMore || isLoading) {
                                item(span = { GridItemSpan(2) }) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 12.dp),
                                        contentAlignment = Alignment.Center,
                                    ) {
                                        if (isLoading) {
                                            CircularProgressIndicator(color = RetroAmber)
                                        } else {
                                            TextButton(onClick = { loadNextPage() }) {
                                                Text(text = "Muat lebih banyak", color = RetroAmber)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            selectedPhoto?.let { activeItem ->
                FullScreenPhotoViewer(
                    item = activeItem,
                    repository = repository,
                    onMissing = { pruneMissingPhoto(activeItem.mediaId) },
                    onShare = {
                        scope.launch {
                            val stillExists = withContext(Dispatchers.IO) {
                                repository.exists(activeItem.uri)
                            }
                            if (!stillExists) {
                                pruneMissingPhoto(activeItem.mediaId)
                            } else {
                                sharePhoto(context, activeItem)
                            }
                        }
                    },
                    onClose = { selectedPhoto = null },
                )
            }
        }
    }
}

@Composable
private fun GalleryPhotoCard(
    item: GalleryPhotoItem,
    repository: GalleryRepository,
    thumbnailCache: LruCache<Long, Bitmap>,
    onMissing: () -> Unit,
    onClick: () -> Unit,
) {
    var thumbnail by remember(item.mediaId) { mutableStateOf(thumbnailCache.get(item.mediaId)) }
    var failedLoad by remember(item.mediaId) { mutableStateOf(false) }

    LaunchedEffect(item.mediaId) {
        if (thumbnail != null) return@LaunchedEffect
        val loaded = withContext(Dispatchers.IO) {
            if (!repository.exists(item.uri)) {
                null
            } else {
                repository.loadThumbnail(item.uri)
            }
        }
        if (loaded != null) {
            thumbnailCache.put(item.mediaId, loaded)
            thumbnail = loaded
        } else {
            val stillExists = withContext(Dispatchers.IO) { repository.exists(item.uri) }
            if (!stillExists) {
                onMissing()
            } else {
                failedLoad = true
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(RetroSurface)
            .border(1.dp, RetroSurfaceBorder, RoundedCornerShape(10.dp))
            .clickable(onClick = onClick),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .background(Color.Black),
            contentAlignment = Alignment.Center,
        ) {
            val bmp = thumbnail
            when {
                bmp != null -> {
                    Image(
                        bitmap = bmp.asImageBitmap(),
                        contentDescription = item.recipeLabel,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize(),
                    )
                }

                failedLoad -> {
                    Text(
                        text = "Gagal memuat",
                        style = MaterialTheme.typography.labelSmall,
                        color = RetroTextSecondary,
                    )
                }

                else -> {
                    CircularProgressIndicator(color = RetroAmber)
                }
            }
        }

        Column(modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp)) {
            Text(
                text = item.recipeLabel,
                style = MaterialTheme.typography.labelSmall,
                color = if (item.metadata != null) RetroTextPrimary else RetroTextSecondary,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
private fun FullScreenPhotoViewer(
    item: GalleryPhotoItem,
    repository: GalleryRepository,
    onMissing: () -> Unit,
    onShare: () -> Unit,
    onClose: () -> Unit,
) {
    var previewBitmap by remember(item.mediaId) { mutableStateOf<Bitmap?>(null) }
    var isLoadingPreview by remember(item.mediaId) { mutableStateOf(true) }

    LaunchedEffect(item.mediaId) {
        isLoadingPreview = true
        val decoded = withContext(Dispatchers.IO) {
            if (!repository.exists(item.uri)) {
                null
            } else {
                repository.loadPreviewBitmap(item.uri)
            }
        }
        if (decoded == null) {
            val stillExists = withContext(Dispatchers.IO) { repository.exists(item.uri) }
            if (!stillExists) {
                onMissing()
                return@LaunchedEffect
            }
        }
        previewBitmap = decoded
        isLoadingPreview = false
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.96f))
            .padding(12.dp),
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                TextButton(onClick = onClose) {
                    Text(text = "← Tutup", color = RetroAmber)
                }
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = onShare,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = RetroAmber,
                        contentColor = RetroDarkBackground,
                    ),
                ) {
                    Text(text = "Bagikan")
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center,
            ) {
                val bmp = previewBitmap
                when {
                    isLoadingPreview -> {
                        CircularProgressIndicator(color = RetroAmber)
                    }

                    bmp != null -> {
                        Image(
                            bitmap = bmp.asImageBitmap(),
                            contentDescription = item.recipeLabel,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.fillMaxSize(),
                        )
                    }

                    else -> {
                        Text(
                            text = "Gagal membuka foto.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = RetroTextSecondary,
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(RetroSurface)
                    .border(1.dp, RetroSurfaceBorder, RoundedCornerShape(10.dp))
                    .padding(12.dp),
            ) {
                Text(
                    text = item.recipeLabel,
                    style = MaterialTheme.typography.titleSmall,
                    color = RetroTextPrimary,
                )
                Spacer(modifier = Modifier.height(4.dp))
                val subtitle = item.metadata?.let { meta ->
                    "${item.displayName} · EXIF v${meta.schemaVersion} (${meta.recipeVersion})"
                } ?: item.displayName
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.labelSmall,
                    color = RetroTextSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

private fun sharePhoto(context: Context, item: GalleryPhotoItem) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "image/jpeg"
        putExtra(Intent.EXTRA_STREAM, item.uri)
        putExtra(Intent.EXTRA_TEXT, item.recipeLabel)
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }
    runCatching {
        context.startActivity(Intent.createChooser(shareIntent, "Bagikan foto RetroCam"))
    }
}
