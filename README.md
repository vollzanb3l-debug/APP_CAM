# Aplikasi Kamera Retro (Camera + Lens + Film)

Aplikasi kamera Android untuk pemakaian pribadi dengan kombinasi modular **Camera + Lens + Film**. Preview live dan foto hasil diproses melalui satu pipeline shader OpenGL ES 3.0 yang sama dan digerakkan oleh data JSON.

## Struktur Dokumen Proyek

| File | Keterangan |
|---|---|
| [docs/ARCHITECTURE.md](docs/ARCHITECTURE.md) | Sumber kebenaran arsitektur, skema JSON, tahapan, dan aturan kerja |
| [docs/AGENT_PROMPT.md](docs/AGENT_PROMPT.md) | Prompt awal kerja agent dan ruang lingkup tahap M0–M1 |
| [CHANGELOG.md](CHANGELOG.md) | Catatan perubahan tiap tahap |
| [TEST_CHECKLIST.md](TEST_CHECKLIST.md) | Checklist uji manual di perangkat untuk setiap akhir tahap |

## Roadmap Tahapan

- **M0 – Kerangka**: Proyek build, izin kamera, CameraX preview polos, navigasi layar kosong
- **M1 – Jalur GL tanpa efek**: Preview lewat `EffectEngine` (passthrough), foto lewat `CaptureProcessor` (passthrough), simpan ke galeri
- **M2 – Satu efek**: Color grade sementara untuk verifikasi kecocokan preview vs foto + golden-image test
- **M3 – Resep dari JSON**: `RecipeRepository`, `RecipeCompositor` (+ unit test), `PickerSheet`, simpan pilihan terakhir
- **M4 – Efek lengkap**: Grain, vignette, bloom/halation, chromatic aberration, distorsi, softness, crop rasio, date stamp
- **M5 – Pelengkap**: Exposure compensation, grid, galeri, label resep di EXIF, favorit
- **M6 – Kalibrasi dan konten**: Layar debug, optimasi performa/panas, perluasan konten JSON

**Status saat ini:** Persiapan (menunggu konfirmasi sebelum M0).
