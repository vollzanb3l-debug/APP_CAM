# Indeks Katalog JSON 100 Kamera (`docs/catalog/json/`)

Folder ini menyimpan representasi data terstruktur dari **100 kamera hasil riset katalog** sebelum masuk ke tahap kalibrasi dan impor ke `app/src/main/assets/`.

> **Penting:** Berkas di `docs/catalog/json/` adalah **indeks riset & perencanaan**, **bukan** aset runtime aplikasi. Folder `app/` belum disentuh dan berkas ini sengaja **tidak memuat angka parameter shader/efek**.

---

## 1. Berkas di Folder Ini

- [`catalog_index.json`](catalog_index.json): Indeks 100 kamera lengkap hasil riset [batch_01.md](../batch_01.md)–[batch_05.md](../batch_05.md), pendalaman visual [looks_batch_01.md](../looks/looks_batch_01.md)–[looks_batch_05.md](../looks/looks_batch_05.md), dan audit ketat [AUDIT.md](../looks/AUDIT.md).

---

## 2. Skema `catalog_index.json`

### Struktur Tingkat Atas

| Field | Tipe | Wajib | Keterangan |
|---|---|---|---|
| `schemaVersion` | integer | Ya | Versi skema indeks katalog (saat ini `1`). |
| `status` | string | Ya | Status dokumen (`"research_index_draft"`). |
| `description` | string | Ya | Ringkasan tujuan berkas. |
| `archetypesRef` | string | Ya | Rujukan dokumen 15 arketipe (`docs/catalog/archetypes.md`). |
| `auditRef` | string | Ya | Rujukan audit konsistensi (`docs/catalog/looks/AUDIT.md`). |
| `totalCameras` | integer | Ya | Harus sama dengan panjang array `cameras` (`100`). |
| `allowedArchetypes` | string[] | Ya | Daftar 15 ID arketipe sah. |
| `allowedIconTypes` | string[] | Ya | Daftar 10 jenis siluet ikon sah. |
| `cameras` | object[] | Ya | Array berisi tepat 100 objek kamera. |

### Struktur Per Kamera (`cameras[]`)

| Field | Tipe | Wajib | Aturan / Nilai Sah |
|---|---|---|---|
| `id` | string | Ya | Unik (`brand_model`, huruf kecil + garis bawah, mis. `canon_powershot_sd1000`). |
| `brand` | string | Ya | Merek persis mengikuti `batch_01.md`–`batch_05.md`. |
| `model` | string | Ya | Nama tipe kanonik (satu nama kanonik walau punya banyak alias). |
| `aliases` | string[] | Ya | Daftar nama lain di wilayah berbeda (mis. IXUS, IXY, Stylus Epic) atau `[]` bila tidak ada. |
| `displayName` | string | Ya | Nama tampilan (saat ini sama persis dengan nama asli di `batch_01`–`batch_05`). |
| `era` | string | Ya | Dekade rilis terverifikasi (`1960s`..`2020s`), `1980s-present` (QuickSnap), atau `unspecified` bila tahun rilis persis varian saat ini tidak ditemukan di sumber resmi. |
| `category` | string | Ya | Kategori teknis kamera (`digital_compact`, `digital_rangefinder`, `digital_mirrorless`, `film_slr_35mm`, `film_rangefinder_35mm`, `film_compact_35mm`, `half_frame_35mm`, `disposable_35mm`, `instant_film`, `medium_format_120`, `toy_camera_35mm`, `toy_camera_120`, `toy_camera_digital`, `camcorder`, `camera_phone`). |
| `archetype` | string | Ya | Salah satu dari **15 ID arketipe sah** di [`docs/catalog/archetypes.md`](../archetypes.md). |
| `lookTags` | string[] | Ya | 3 sampai 6 kata kunci pendek karakter visual kualitatif. |
| `confidenceSpecs` | string | Ya | `"high"` \| `"medium"` \| `"low"` (mengacu `batch_01`–`batch_05.md`: 64 / 35 / 1). |
| `confidenceLook` | string | Ya | `"high"` \| `"medium"` \| `"low"` \| `"tidak ditemukan"` (mengacu hasil audit ketat [`AUDIT.md`](../looks/AUDIT.md): 46 / 49 / 5 / 0). |
| `sources` | string[] | Ya | Daftar URL sumber kanonis terverifikasi (minimal 1 URL, tanpa tautan redirect). |
| `calibrationStatus` | string | Ya | Seluruh entri saat ini `"draft"`. Hanya berubah menjadi `"calibrated"` setelah penyetelan visual terhadap foto referensi nyata. |
| `iconType` | string | Ya | Salah satu dari **10 siluet ikon** di bagian 3. |
| `tuning_notes` | string | Ya | Catatan kualitatif dengan kata-kata saja (0 angka parameter efek) mengenai pembeda kamera ini terhadap arketipe induknya dan catatan audit saat kalibrasi. |

---

## 3. Taksonomi `iconType` (10 Jenis Siluet)

| No | `iconType` | Jumlah | Perwakilan Kamera |
|---:|---|---:|---|
| 1 | `compact_digital` | 39 | Kompak digital CCD/CMOS, pro-compact, Mavica, Leica M9, Fujifilm X100V/X-Pro1 |
| 2 | `compact_film` | 17 | Point-and-shoot 35mm & rangefinder ringkas (Contax T2, Olympus µ-II, Yashica T4, Ricoh GR1v, Canonet QL17) |
| 3 | `instant` | 8 | Seluruh kamera instan integral Polaroid & Fujifilm Instax |
| 4 | `camera_phone` | 7 | Kamera ponsel awal (iPhone 4/3GS, Sony Ericsson K800i, Nokia N95/N8/808, Galaxy S II) |
| 5 | `slr_film` | 6 | SLR & rangefinder bodi penuh 35mm (Canon AE-1, Nikon FM2, Leica M6, Pentax K1000, Olympus OM-1) |
| 6 | `toy_lens` | 6 | Kamera mainan / lo-fi (Holga 120N, Lomo LC-A, Diana F+, La Sardina, Camp Snap, Game Boy Camera) |
| 7 | `half_frame` | 5 | Kamera half-frame 35mm vertikal (Pen EE-3, Pen FT, Pentax 17, Ektar H35, Demi EE17) |
| 8 | `disposable` | 5 | Kamera sekali pakai / single-use (QuickSnap, FunSaver, Ilford HP5+/XP2, Lomography Simple Use) |
| 9 | `medium_format` | 4 | Format medium 120 (Hasselblad 500C/M, Rolleiflex 2.8F, Mamiya 7, Pentax 67) |
| 10 | `camcorder` | 3 | Camcorder MiniDV 3-CCD (Sony DCR-TRV900, DCR-VX1000, Panasonic AG-DVX100) |
| | **Total** | **100** | |

---

## 4. Aturan Pewarisan dari Arketipe (*Archetype Inheritance*)

Sesuai prinsip data-driven dan pemisahan **Camera + Lens + Film** pada [`docs/ARCHITECTURE.md`](../../ARCHITECTURE.md):

1. **Tidak ada angka parameter di `catalog_index.json`:** Seluruh 100 kamera mulai dalam status `calibrationStatus: "draft"` dan **mewarisi resep dasar** dari berkas arketipe induknya (`assets/archetypes/<archetype>.json`) yang dipetakan dari 15 arketipe di [`docs/catalog/archetypes.md`](../archetypes.md).
2. **Alur penggabungan saat masuk ke aplikasi nanti:**
   ```text
   archetype baseline (assets/archetypes/<archetype>.json)
     + override kamera spesifik (opsional, hanya field yang berbeda setelah kalibrasi visual)
     + lapisan Lens (optik: softness, vignette, CA, distortion, bloom)
     + lapisan Film (emulsi: color grade/LUT, fade, grain, halation)
     = FinalRecipe -> EffectEngine (preview & photo)
   ```
3. **Peran `tuning_notes`:** Field `tuning_notes` menjadi panduan kualitatif manusia saat menyetel slider di layar kalibrasi (`CalibrationDebugScreen`) berdampingan dengan foto referensi nyata di `docs/references/` (tidak ikut repo). Hanya perbedaan nyata yang terverifikasi secara visual yang ditulis sebagai *override* numerik di berkas resep kamera aplikasi nanti.
4. **Aturan khusus kamera film vs digital saat kalibrasi:**
   - **Kamera film isi ulang (`confidenceLook: "medium"` karena contoh campur):** Override kamera hanya menyetel aspek **bodi/format/optik tetap** (mis. rasio 3:4 untuk half-frame, rasio 1:1 untuk 6×6, vignette sudut Lomo LC-A/Holga, kelembutan tepi lensa plastik, atau karakter *flash*), **bukan** warna emulsi film; warna tetap dikendalikan lapisan **Film**.
   - **Kamera digital dengan mode warna/simulasi film (mis. Ricoh GR III, Fujifilm X100V/X-Pro1, Olympus XZ-1):** Kalibrasi dasar mengikuti profil *default* SOOC, sementara varian mode populernya (*Positive Film*, *Classic Chrome*, *Hi-Contrast B&W*) dapat dipasangkan lewat lapisan **Film**.
   - **Kamera berstatus `low` (5 entri di `AUDIT.md`):** Tidak boleh dinaikkan ke `calibrationStatus: "calibrated"` sebelum ditambah foto contoh nyata yang memadai.

---

## 5. Validasi Otomatis

Sebelum dipakai atau diperbarui, pastikan `catalog_index.json` lolos seluruh pemeriksaan berikut:
- Format JSON valid dan `len(cameras) == totalCameras == 100`.
- Seluruh 100 `id` unik dan berformat `snake_case`.
- Seluruh `archetype` termasuk dalam 15 ID sah di `docs/catalog/archetypes.md`.
- Seluruh `iconType` termasuk dalam 10 ID siluet sah.
- Tidak ada field wajib yang kosong atau `null` (`id`, `brand`, `model`, `displayName`, `era`, `category`, `archetype`, `lookTags`, `confidenceSpecs`, `confidenceLook`, `sources`, `calibrationStatus`, `iconType`, `tuning_notes`; `aliases` bertipe array string).
- `lookTags` berisi 3–6 tag per kamera; `sources` minimal 1 URL kanonis tanpa tautan redirect `vertexaisearch.cloud.google.com`.
- `calibrationStatus == "draft"` untuk semua entri dan **0 nilai parameter shader numerik** di dalam JSON.
- `confidenceLook` cocok 1:1 dengan `docs/catalog/looks/AUDIT.md` (`46 high`, `49 medium`, `5 low`, `0 tidak ditemukan`).
