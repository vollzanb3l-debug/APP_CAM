# Aplikasi Kamera Retro (Camera + Lens + Film)

Aplikasi kamera Android untuk pemakaian pribadi dengan kombinasi modular **Camera + Lens + Film**. Preview live dan foto hasil diproses melalui satu pipeline shader OpenGL ES 3.0 yang sama dan digerakkan oleh data JSON.

## Struktur Dokumen Proyek

| File | Keterangan |
|---|---|
| [docs/ARCHITECTURE.md](docs/ARCHITECTURE.md) | Sumber kebenaran arsitektur, skema JSON, tahapan, dan aturan kerja |
| [docs/AGENT_PROMPT.md](docs/AGENT_PROMPT.md) | Prompt awal kerja agent dan ruang lingkup tahap M0–M1 |
| [CHANGELOG.md](CHANGELOG.md) | Catatan perubahan tiap tahap |
| [TEST_CHECKLIST.md](TEST_CHECKLIST.md) | Checklist uji manual di perangkat untuk setiap akhir tahap |
| [.github/workflows/build.yml](.github/workflows/build.yml) | Workflow GitHub Actions untuk uji unit + build `app-debug.apk` |

## Cara Build APK Lewat GitHub Actions (Tanpa Install SDK di Komputer)

1. **Buat repository private di GitHub**
   - Buka GitHub, klik **New repository** (mis. `retrocam-android`), pilih **Private**, jangan centang *Initialize with README*, lalu klik **Create repository**.
2. **Hubungkan repo lokal & push ke branch `main`**
   - Jalankan perintah berikut di folder `APP CAM`:
     ```powershell
     git remote add origin https://github.com/<username-github>/<nama-repo>.git
     git push -u origin main
     ```
3. **Unduh APK dari tab Actions**
   - Buka repository Anda di GitHub -> klik tab **Actions**.
   - Pilih workflow run terbaru (**Build Debug APK**) hingga berstatus hijau (selesai).
   - Gulir ke bagian **Artifacts**, lalu unduh **`retrocam-debug-apk`** (berisi `app-debug.apk` di dalam file `.zip`).
4. **Pasang APK di HP Android**
   - Ekstrak file `.zip` yang diunduh, lalu pindahkan `app-debug.apk` ke HP (kabel USB, Google Drive, atau kirim ke diri sendiri).
   - Ketuk `app-debug.apk` di HP, izinkan **Install unknown apps** bila diminta, lalu buka **RetroCam** dan jalankan checklist di [TEST_CHECKLIST.md](TEST_CHECKLIST.md).

## Roadmap Tahapan

- **M0 – Kerangka**: Proyek build, izin kamera, CameraX preview polos, navigasi layar kosong *(selesai)*
- **M1 – Jalur GL tanpa efek**: Preview lewat `EffectEngine` (passthrough), foto lewat `CaptureProcessor` (passthrough), simpan ke galeri *(selesai)*
- **M2 – Satu efek**: Color grade sementara untuk verifikasi kecocokan preview vs foto + golden-image test
- **M3 – Resep dari JSON**: `RecipeRepository`, `RecipeCompositor` (+ unit test), `PickerSheet`, simpan pilihan terakhir
- **M4 – Efek lengkap**: Grain, vignette, bloom/halation, chromatic aberration, distorsi, softness, crop rasio, date stamp
- **M5 – Pelengkap**: Exposure compensation, grid, galeri, label resep di EXIF, favorit
- **M6 – Kalibrasi dan konten**: Layar debug, optimasi performa/panas, perluasan konten JSON

**Status saat ini:** M0 & M1 selesai (siap build via GitHub Actions & uji perangkat; menunggu konfirmasi sebelum M2).
