# Changelog

Semua perubahan penting pada proyek ini akan dicatat di file ini.

Format mengacu pada [Keep a Changelog](https://keepachangelog.com/id-ID/1.0.0/).

## [Unreleased]

### Added
- Menambahkan dokumen arsitektur utama (`docs/ARCHITECTURE.md`) dan prompt kerja agent (`docs/AGENT_PROMPT.md`).
- Menambahkan `TEST_CHECKLIST.md`, `CHANGELOG.md`, dan `README.md` sebagai struktur awal proyek.
- Menambahkan workflow GitHub Actions (`.github/workflows/build.yml`) untuk menjalankan unit test dan membangun `app-debug.apk` (JDK 17, Gradle 8.9, compileSdk/targetSdk 35).
- **M0 (Kerangka)**:
  - Menyiapkan konfigurasi Gradle Kotlin DSL + Version Catalog (`minSdk = 26`, `compileSdk = 35`, `targetSdk = 35`, `applicationId = "com.retrocam.app"`).
  - Menambahkan `AndroidManifest.xml`, tema gelap orisinal (`Theme.kt`), ikon peluncur orisinal, alur izin kamera, dan navigasi layar (`CameraScreen`, `GalleryScreen`, `SettingsScreen`).
- **M1 (Jalur GL tanpa efek / passthrough)**:
  - Menambahkan `FrameTransform` (matematika transformasi koordinat murni Kotlin) beserta unit test `FrameTransformTest` untuk memastikan kesamaan framing, orientasi, dan mirror kamera depan antara preview dan foto.
  - Menambahkan shader passthrough GLSL ES 3.0 bersama dan `ShaderLoader`.
  - Menambahkan `EffectEngine` (preview live via OpenGL ES 3.0 `SurfaceTexture` + `EGLWindowSurface`) dan `CaptureProcessor` (pemrosesan FBO offscreen resolusi penuh dengan dukungan *tiling* otomatis).
  - Menambahkan `PhotoSaver` untuk menyimpan foto JPEG ke `MediaStore` (`Pictures/RetroCam`) dan menghubungkan `CameraController` serta `CameraScreen` untuk tombol rana dan balik kamera.
- **M2 (Satu efek color grade & fondasi video-ready)**:
  - Menambahkan shader bersama `retro_pipeline.vert` / `retro_pipeline.frag` (white balance, shadow lift, highlight rolloff, contrast, saturation, split-tone tint, black lift) beserta `EffectUniforms`, `HexColorParser`, `ColorGradeMath`, dan `ColorGradeGoldenTest`.
  - Menjadikan `EffectEngine` dan `CaptureProcessor` **video-ready** sejak awal melalui `RenderQuality` (`PREVIEW` / `VIDEO` / `PHOTO`), `FrameRenderParams` (`uTimeSec`, `uFrameIndex`, `uGrainSeed` tetap per foto & berubah per frame untuk stream/video, `uQualityLevel`), pengelolaan output multi-surface (`attachOutputSurface` / `detachOutputSurface`), serta unit test `RenderContextTest`.
- **M3 (Resep dari JSON, Picker, dan Kalibrasi)**:
  - Memperbarui `docs/ARCHITECTURE.md` dengan bagian **15. Katalog dan Kalibrasi** (`archetype`, `confidenceLook`, `confidenceSpecs`, `sources`, `calibrationStatus` untuk kamera dan film) serta bagian **16. Rencana Masa Depan: Video** beserta temuan dokumentasi resmi CameraX 1.4.0 (`CameraEffect`, `SurfaceProcessor`, `UseCaseGroup`, `OverlayEffect`).
  - Menambahkan `kotlinx.serialization`, `RecipeModels` (`CameraSpec` v3, `FilmSpec` v2, `LensSpec` v1, `ArchetypeSpec` v1), `RecipeValidator`, `RecipeCompositor` (+ `RecipeCompositorTest` & `RecipeValidatorTest`), `RecipeCatalogLoader`, `RecipeRepository` (+ `RecipeRepositoryTest`), dan `SelectionStore`.
  - Menyelaraskan 7 arketipe (`assets/archetypes/`), 3 kamera (`Kompak CCD 2003`, `Saku Lembut 2008`, `Klasik 35mm`), 3 lensa (`28mm f2.8`, `35mm f2`, `50mm f1.4`), dan 3 film orisinal (`Surya 200`, `Linen 400`, `Monokrom 400`) berdasarkan panduan kualitatif `docs/catalog/archetypes.md`, `batch_01`–`batch_05`, dan `film_looks.md` (seluruhnya berstatus perkiraan awal `draft`).
  - Menambahkan `PickerSheet`, 3 slot `CAMERA | LENS | FILM` pada `CameraScreen`, ikon geometris orisinal (`RecipeIcons.kt`), pemulihan pilihan terakhir, dan menghapus resep sementara M2 dari kode.
  - Menambahkan `CalibrationDebugScreen` sederhana (metadata kalibrasi kamera & film termasuk `confidenceLook`/`confidenceSpecs`, log error JSON, pilih foto referensi dari galeri, perbandingan berdampingan via `CaptureProcessor`, serta slider parameter).
