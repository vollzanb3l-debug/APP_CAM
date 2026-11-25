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
  - Menambahkan shader passthrough GLSL ES 3.0 bersama (`passthrough.vert`, `passthrough.frag`) dan `ShaderLoader`.
  - Menambahkan `EffectEngine` (preview live via OpenGL ES 3.0 `SurfaceTexture` + `EGLWindowSurface`) dan `CaptureProcessor` (pemrosesan FBO offscreen resolusi penuh dengan dukungan *tiling* otomatis).
  - Menambahkan `PhotoSaver` untuk menyimpan foto JPEG ke `MediaStore` (`Pictures/RetroCam`) dan menghubungkan `CameraController` serta `CameraScreen` untuk tombol rana dan balik kamera.
