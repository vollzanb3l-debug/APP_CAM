# Changelog

Semua perubahan penting pada proyek ini akan dicatat di file ini.

Format mengacu pada [Keep a Changelog](https://keepachangelog.com/id-ID/1.0.0/).

## [Unreleased]

### Fixed
- **Orientasi Preview M1 (Bug Prioritas Tertinggi)**:
  - Menyatukan perhitungan orientasi kamera dan mirror ke satu sumber kebenaran `FrameTransform.computeCameraOrientation` (mengacu ke aturan CameraX `CameraOrientationUtil`) yang dipakai oleh jalur preview (`EffectEngine` + `CameraController`) dan foto (`CaptureProcessor`).
  - Memperbaiki *double-rotation* dan *double-mirroring* pada preview OpenGL dengan mendekomposisi rotasi/mirror bawaan HAL dari `SurfaceTexture.getTransformMatrix` (`extractBakedSurfaceTransform` & `solvePreviewDelta`) sehingga preview dan foto cocok di semua kombinasi orientasi sensor (`90°`/`270°`), rotasi layar (`0°`/`90°`/`180°`/`270°`), dan kamera belakang/depan (+ unit test komprehensif di `FrameTransformTest`).
  - Menambahkan `DisplayManager.DisplayListener` pada `CameraScreen` agar perubahan rotasi layar terdeteksi di keempat posisi (termasuk perpindahan langsung landscape kiri ↔ landscape kanan).

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
- **M4 (Efek lengkap dalam koordinat ternormalisasi — satu efek per commit)**:
  - **Vignette**: falloff melingkar terkoreksi rasio aspek (`computeVignetteFactor`, `vImageUv`/`uImageUvRect`, `VignetteEffectTest`, slider *Vignette Strength/Radius*).
  - **Grain**: butiran film & noise kromatik relatif sisi pendek gambar dengan seed *video-ready* (tetap per foto, berubah per frame untuk video/preview) dan 2 tingkat kualitas (`preview` 1-oktaf vs `photo` 2-oktaf; `GrainMath`, `GrainEffectTest`, slider *Grain Amount/Size/Roughness/Chroma*).
  - **Softness / blur lembut**: kernel Gaussian relatif sisi pendek dengan 2 tingkat kualitas (`preview` 5-tap vs `photo` 9-tap; `OpticsMath`, `SoftnessEffectTest`, slider *Softness*).
  - **Bloom & Halation**: ekstraksi sorotan lembut + pendaran radial relatif sisi pendek dengan 2 tingkat kualitas (`preview` 8-tap vs `photo` 16-tap dua cincin; `BloomHalationEffectTest`, slider *Bloom Intensity/Threshold/Radius* & *Halation Intensity*).
  - **Chromatic Aberration**: pergeseran radial R/B nol di pusat dan meningkat menuju sudut (`ChromaticAberrationEffectTest`, slider *Chromatic Aberration*).
  - **Distorsi Lensa**: distorsi *barrel* (`k1 < 0`) dan *pincushion* (`k1 > 0`) di tahap Geometri tanpa tepi hitam (`DistortionEffectTest`, slider *Lens Distortion k1*).
  - **Crop Rasio Gambar**: dukungan penuh `4:3`, `3:4` (half-frame vertikal), `3:2`, `2:3`, `1:1`, `16:9` seragam antara viewfinder, `CaptureProcessor`, dan layar kalibrasi (`AspectCropEffectTest`).
  - **Date Stamp Orisinal**: stempel tanggal digital oranye 7-segmen prosedural orisinal (`'YY.MM.DD`) langsung di shader tahap Overlay (identik di preview dan foto), lengkap dengan toggle `DATE ON/OFF` di `CameraScreen` & layar kalibrasi (`OverlayMath`, `DateStampEffectTest`).
  - **Light Leak Opsional**: efek bocoran cahaya tepi film prosedural opsional (default mati) di tahap Overlay (`LightLeakEffectTest`, slider *Light Leak Intensity/Seed*).
  - Memperbarui resep awal 3 kamera / 3 lensa / 3 film agar memanfaatkan seluruh efek M4 sesuai deskripsi kualitatif katalog & `film_looks.md` (status tetap `draft`).
- **M5 (Pelengkap)**:
  - **Exposure Compensation**: skala `-2..+2 EV` di `CameraScreen` yang dibatasi rentang perangkat (`cameraInfo.exposureState`) dan berlaku seragam untuk preview maupun foto (`ExposureCompensationMath`, `CameraController`, `ExposureCompensationMathTest`).
  - **Grid Viewfinder (Aturan Sepertiga)**: overlay garis 3x3 on/off di viewfinder dengan penyimpanan status otomatis (`GridOverlayMath`, `SelectionStore`, `GridOverlayMathTest`).
  - **Metadata Resep di EXIF `UserComment`**: menulis `cameraId`, `lensId`, `filmId`, `schemaVersion`, dan versi skema resep pada setiap foto tersimpan (`RecipeExifMetadata`, `PhotoSaver`, `CameraController`, `RecipeExifMetadataTest` termasuk uji tahan-crash untuk foto tanpa metadata).
  - **`GalleryScreen`**: daftar foto aplikasi dari `Pictures/RetroCam` dengan muat berkala (*pagination* 24 item/halaman), label `"Kamera - Lensa - Film"` dari EXIF, layar penuh + aksi bagikan (`Intent.ACTION_SEND`), penanganan folder kosong, serta pembersihan aman saat foto dihapus dari luar aplikasi (`GalleryPagination`, `GalleryRepository`, `GalleryLogicTest`).
  - **Favorit (Lapisan Data & Penyimpanan)**: penyimpanan berbasis ID murni (`FavoritesStore`, `FavoritesRepository`, `FavoriteIds`) agar tetap valid setelah migrasi skema M6, serta pengurutan item favorit di bagian atas daftar `PickerSheet` (`FavoritesSorter`, `FavoritesTest`; tampilan ikon bintang di picker dijadwalkan di tahap UI berikutnya).
  - **Backlog Fitur**: menambahkan `docs/BACKLOG.md` untuk ide fitur lanjutan di luar tahap saat ini (video, tap-to-focus, ikon bintang picker, dll.).
- **M6 Gerbang 1 (Fondasi Katalog, 15 Arketipe, Kalibrasi, & Performa Preview)**:
  - **Migrasi Skema Kamera `v3 -> v4`**: menambah field `brand`, `model`, `aliases`, `displayName`, `iconType`, `archetype`, `lookTags`, `tuning_notes` dengan `CameraSchemaMigrator` tanpa mengubah `id` lama (`CameraSchemaMigratorTest`).
  - **Pewarisan Arketipe**: `ArchetypeSpec` `v2` (`cameraDefaults`), parameter opsional per kamera, dan resolusi pewarisan tunggal di `RecipeCompositor` tanpa membaca `tuning_notes` (`RecipeCompositorTest`).
  - **Peta Kata Kunci & 15 Arketipe (`draft`)**: menambahkan `assets/look_tag_map.json` (46 `lookTags` dengan penyesuaian kecil berstatus `draft`), `LookTagMapper`, dan seluruh 15 arketipe kamera `draft` di `assets/archetypes/` (`LookTagMapperTest`).
  - **Perluasan Layar Kalibrasi**: pemilih target 15 arketipe + resep aktif, perbandingan berdampingan foto referensi galeri, seluruh slider parameter (termasuk *Sharpen*, *Flare*, rasio `2:3`), tombol **Reset**, dan tombol **"Simpan sebagai calibrated"** yang mengekspor JSON valid ke `Documents/RetroCam/export/` (`CalibrationExporter`, `CalibrationExporterTest`).
  - **Optimasi Performa Preview & Penurunan Kualitas Otomatis**:
    - Menambahkan `PreviewPerformanceController` (`PreviewPerformanceMonitor.kt`), pengukuran waktu frame langsung di `EffectEngine` (`avg` / `p95` / `max` ms + pembacaan thermal `PowerManager.getCurrentThermalStatus()` pada API 29+), indikator ringan `ms` / `ECO` di `CameraScreen`, serta tier shader `RenderQuality.PREVIEW_LOW` (`uQualityLevel = -1`) yang hanya berlaku untuk preview (`PHOTO` & `VIDEO` tidak pernah diturunkan).
    - **Pengurangan beban shader pada mode adaptif `PREVIEW_LOW` (efek berat bloom + grain + blur aktif bersamaan, karakter warna/tampilan tetap sama)**:
      - Total *texture tap* per piksel: **15 tap (`PREVIEW`) -> 9 tap (`PREVIEW_LOW`, -40%)** — Softness `5 -> 3` tap, Bloom/Halation `8 -> 4` tap, Chromatic Aberration `2 -> 2` tap.
      - Operasi hash grain per piksel (dengan noise kromatik): **16 `hash12` (`PREVIEW`) -> 3 `hash12` (`PREVIEW_LOW`, -81%)**.
      - Ambang otomatis: turun ke `PREVIEW_LOW` bila frame preview `> 28 ms` selama 8 frame berturut-turut atau status thermal `>= MODERATE`; pulih kembali ke `PREVIEW` setelah 45 frame stabil `< 18 ms` pada thermal `<= LIGHT` (histeresis anti-kedip; diuji oleh `PreviewPerformanceTest`).
      - *Catatan pengukuran perangkat nyata*: karena lingkungan pengembangan tidak memiliki HP/GPU fisik, angka milidetik nyata (`avg`/`p95` ms) dibaca langsung dari indikator bar atas `CameraScreen` & `Logcat` (`EffectEngine: PreviewPerf ...`) saat uji Gerbang 1 di HP.
- **M6 Gerbang 2 (Katalog 100 Kamera, Validator, & Pencarian Picker)**:
  - **Impor 100 Kamera (`draft`)**: menambahkan bundel tunggal `assets/cameras/catalog_cameras.json` (`168 KB`, 1 kali `AssetManager.open` untuk menjaga waktu muat tetap cepat) berisi seluruh 100 kamera dari `docs/catalog/json/catalog_index.json` berstatus `draft` yang mewarisi parameter penuh dari 15 arketipe; `RecipeCatalogLoader` mendukung bundel tunggal sekaligus *drop-in override* berkas kamera per-ID hasil kalibrasi.
  - **Validator Katalog & Waktu Muat**: menambahkan `RecipeValidator.validateCatalog(...)` (cek 100 ID unik, semua arketipe valid dengan `cameraDefaults`, tidak ada field wajib kosong) serta pelacakan `RecipeCatalog.loadTimeMs` + uji unit `Catalog100ValidatorTest` (termasuk benchmark waktu muat median 100 kamera vs baseline).
  - **Pencarian 100 Kamera di `PickerSheet`**: menambahkan `CameraSearchFilter` (`CameraSearchFilterTest`) dan kolom pencarian + chip cepat `#lookTag` pada `PickerSheet` untuk menyaring 100 kamera berdasarkan nama, brand, alias regional, maupun `lookTags` (multi-kata, tetap menjaga urutan favorit di awal daftar).
