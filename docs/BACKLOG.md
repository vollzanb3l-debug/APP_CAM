# BACKLOG.md — Ide Fitur Lanjutan RetroCam

Dokumen ini mencatat ide fitur yang **belum dikerjakan** dan dijadwalkan di luar cakupan tahap inti (M0–M5 / M6), agar ruang lingkup tiap tahap tetap terfokus dan tidak membengkak.

---

## 1. Pengambilan Gambar & Kontrol Kamera

- **Perekaman Video dengan Efek Live**
  - Memanfaatkan fondasi *video-ready* yang sudah disiapkan sejak M2 (`EffectEngine` multi-surface, `FrameRenderParams` dengan `uTimeSec` / `uFrameIndex` / `uGrainSeed` per frame, dan `RenderQuality.VIDEO`), dibungkus ke `CameraEffect` + `SurfaceProcessor` CameraX 1.4.0 (`PREVIEW or VIDEO_CAPTURE`) sesuai `docs/ARCHITECTURE.md` bagian 16.
- **Tap-to-Focus & AE/AF Lock**
  - Ketuk viewfinder untuk memfokuskan dan mengukur eksposur titik tertentu (`FocusMeteringAction`), disertai tahan-lama untuk mengunci AE/AF dan indikator bingkai fokus minimalis.
- **Kontrol Flash (Auto / On / Off / Torch)**
  - Penting untuk mendekati karakter kamera kompak 90-an/2000-an dan kamera sekali pakai dengan flash langsung.
- **Pinch-to-Zoom & Ganti Lensa Fisik Perangkat**
  - Zoom optik/digital halus serta opsi memilih sensor ultra-wide / wide / tele fisik perangkat bila tersedia.
- **Timer Rana (3 dtk / 10 dtk) & Tombol Volume sebagai Rana**
  - Memudahkan pemotretan tripod/selfie dan sensasi rana fisik.
- **Histogram Live & Indikator Clipping Highlight**
  - Opsional untuk membantu kontrol eksposur saat memotret di kondisi kontras tinggi.

---

## 2. Pengalaman Pengguna (UI/UX)

- **Tampilan Ikon Bintang Favorit di `PickerSheet`**
  - Menambahkan tombol bintang pada kartu Camera, Lens, dan Film di `PickerSheet` yang langsung memanggil `FavoritesStore.toggle(...)` (lapisan data & penyimpanan berbasis ID sudah selesai di M5).
- **Aksi "Pakai Ulang Resep" dari `GalleryScreen`**
  - Membaca metadata EXIF `UserComment` (`cameraId`, `lensId`, `filmId`) dari foto di galeri dan menyediakan tombol untuk langsung mengaktifkan kombinasi resep tersebut di `CameraScreen`.
- **Mode Edit Foto dari Galeri (Post-Process)**
  - Memilih foto lama dari galeri HP lalu menerapkan kombinasi Camera + Lens + Film melalui `CaptureProcessor` yang sama dan menyimpan salinan baru dengan EXIF resep.
- **Filter & Kategori di `PickerSheet`**
  - Pengelompokan kamera berdasarkan era/kategori dan film berdasarkan karakter warna (hangat, vivid, pudar, hitam putih, tungsten), melengkapi pencarian M6.
- **Opsi Kualitas & Format Penyimpanan di `SettingsScreen`**
  - Pengaturan kualitas kompresi JPEG, opsi menyimpan salinan asli tanpa efek, atau dukungan RAW/DNG bila perangkat mendukung.

---

## 3. Mesin Efek, Kalibrasi & Katalog

- **Dukungan 3D LUT `.cube` Kustom**
  - Memuat file LUT dari `assets/luts/` maupun impor LUT pribadi untuk profil emulsi film yang lebih kompleks.
- **Efek *Lens Flare* Optik Lanjutan & Bingkai Film Orisinal**
  - Menambahkan *ghosting/veil flare* berbasis arah cahaya serta opsi bingkai foto instan/film orisinal di tahap Overlay.
- **Perluasan Katalog Lensa & Film**
  - Menambah koleksi lensa dan emulsi film berbasis riset & kalibrasi setelah katalog 100 kamera (M6) stabil.

---

## 4. Pengujian & Persiapan Publikasi

- **Automated Golden-Image Instrumented Tests (`androidTest`)**
  - Menjalankan pengujian render OpenGL ES nyata pada emulator/perangkat fisik untuk mencegah regresi visual shader.
- **Mode Nama Generik & Ikon Orisinal Penuh untuk Publikasi**
  - Sesuai `docs/ARCHITECTURE.md` bagian 14, bila aplikasi suatu saat dipublikasikan: mengganti `displayName` menjadi nama generik dan menggunakan aset ikon buatan sendiri tanpa mengubah mesin efek.
