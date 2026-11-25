# Ringkasan Katalog 100 Kamera (`docs/catalog/SUMMARY.md`)

Ringkasan terverifikasi dari seluruh **5 batch katalog** ([batch_01.md](batch_01.md) hingga [batch_05.md](batch_05.md)), mengacu pada 15 arketipe di [archetypes.md](archetypes.md) dan catatan lisensi sumber daya di [resources.md](resources.md).

## 1. Ringkasan Umum

| Metrik | Jumlah | Keterangan |
|---|---|---|
| **Total kamera terverifikasi** | **100** | 5 batch × 20 kamera (`batch_01`–`batch_05`), 0 duplikat |
| **Jumlah arketipe tercakup** | **15 / 15** | Seluruh arketipe di `archetypes.md` memiliki perwakilan |
| **Tautan redirect belum di-resolve** | **0** | Seluruh URL adalah tautan kanonis langsung terverifikasi |
| **Parameter angka shader dikarang** | **0** | Murni deskripsi kualitatif & `lookTags` (penyetelan angka saat kalibrasi) |

---

## 2. Jumlah Entri per Arketipe

| No | ID Arketipe | Jumlah Entri | Persentase | Catatan Cakupan |
|---|---|---:|---:|---|
| 1 | `ccd_dreamy_pastel` | 15 | 15% | Perwakilan tinggi (Casio, Nikon Coolpix, Panasonic, Samsung, Pentax, Olympus, Fujifilm, Sony, Canon IXY) |
| 2 | `premium_point_shoot_35mm` | 14 | 14% | Kompak film premium (Contax, Olympus, Yashica, Minolta, Pentax, Konica, Nikon, Canon, Ricoh) |
| 3 | `ccd_compact_2000s` | 13 | 13% | Perwakilan tinggi (Canon PowerShot/IXY, Nikon Coolpix, Kodak EasyShare, Samsung, Pentax, Olympus, Sony) |
| 4 | `classic_35mm_slr` | 9 | 9% | SLR & rangefinder klasik 35mm |
| 5 | `pro_ccd_filmic` | 7 | 7% | CCD sensor besar / enthusiast (Leica M9, F31fd, S95, LX3, XZ-1, GRD III, Sigma DP1 Merrill) |
| 6 | `early_camera_phone` | 7 | 7% | Kamera ponsel awal (iPhone 4/3GS, Sony Ericsson K800i, Nokia N95/N8/808 PureView, Galaxy S II) |
| 7 | `toy_lomo_vignette` | 6 | 6% | Toy/lo-fi (Holga 120N, Lomo LC-A, Diana F+, La Sardina, Camp Snap, Game Boy Camera) |
| 8 | `half_frame_35mm` | 5 | 5% | Half-frame 35mm (Pen EE-3, Pen FT, Pentax 17, Ektar H35, Demi EE17) |
| 9 | `disposable_point_shoot` | 5 | 5% | Sekali pakai (QuickSnap, FunSaver, Ilford HP5+, Ilford XP2, Lomography Simple Use) |
| 10 | `instant_integral_vintage` | 4 | 4% | Polaroid integral klasik & modern (SX-70, Sun 660, Spectra, Now Gen 2) |
| 11 | `instant_modern_instax` | 4 | 4% | Fujifilm Instax (Mini 90, Wide 300, SQ6, Mini 11) |
| 12 | `medium_format_tlr_slr` | 4 | 4% | Format medium 120 (Hasselblad 500C/M, Rolleiflex 2.8F, Mamiya 7, Pentax 67) |
| 13 | `camcorder_mini_dv_vhs` | 3 | 3% | Camcorder MiniDV 3-CCD (TRV900, VX1000, AG-DVX100) |
| 14 | `early_cmos_gritty` | 2 | 2% | Street/pocket CMOS awal (Ricoh GR III, Sony RX100) |
| 15 | `filmic_digital_mirrorless` | 2 | 2% | Digital simulasi film (Fujifilm X100V, X-Pro1) |
| | **Total** | **100** | **100%** | *(Gabungan `ccd_dreamy_pastel` + `ccd_compact_2000s` = 28 kamera; + `pro_ccd_filmic` = 35 kamera)* |

*(Catatan teknis: Nikon Coolpix S6900 #27 memakai sensor BSI CMOS 1/2,3" rilisan 2014, bukan CCD, namun dipetakan ke `ccd_dreamy_pastel` karena karakter visual khas kulit lembut-berpendar dan palet pastelnya.)*

---

## 3. Distribusi Confidence (`confidenceLook` & `confidenceSpecs`)

### 3.1 `confidenceLook` (Deskripsi Tampilan Foto)

| Tingkat | Jumlah | Persentase | Rincian per Batch |
|---|---:|---:|---|
| **high** | **85** | 85% | Batch 01: 12 · Batch 02: 20 · Batch 03: 16 · Batch 04: 20 · Batch 05: 17 |
| **medium** | **11** | 11% | Batch 01: 4 · Batch 02: 0 · Batch 03: 4 · Batch 04: 0 · Batch 05: 3 |
| **low** | **4** | 4% | Batch 01: 4 · Batch 02: 0 · Batch 03: 0 · Batch 04: 0 · Batch 05: 0 |
| **Total** | **100** | **100%** | Seluruh status `high` telah diaudit memiliki minimal 2 domain sumber independen |

### 3.2 `confidenceSpecs` (Verifikasi Spesifikasi Teknis)

| Tingkat | Jumlah | Persentase | Rincian per Batch |
|---|---:|---:|---|
| **high** | **64** | 64% | Batch 01: 13 · Batch 02: 15 · Batch 03: 10 · Batch 04: 11 · Batch 05: 15 |
| **medium** | **35** | 35% | Batch 01: 6 · Batch 02: 5 · Batch 03: 10 · Batch 04: 9 · Batch 05: 5 |
| **low** | **1** | 1% | Batch 01: 1 · Batch 02: 0 · Batch 03: 0 · Batch 04: 0 · Batch 05: 0 |
| **Total** | **100** | **100%** | |

---

## 4. Daftar Entri yang Kurang Sumber

### 4.1 Prioritas Tinggi — `confidenceLook = low` (4 entri) dan/atau `confidenceSpecs = low` (1 entri)

| No | Kamera | Batch | `confidenceSpecs` | `confidenceLook` | Alasan Kekurangan Sumber |
|---:|---|---|---|---|---|
| 16 | **Polaroid SX-70** | `batch_01.md` | `low` | `low` | Hanya 1 URL ulasan aktif (Casual Photophile); tautan kedua 404 dan halaman spesifikasi produsen 1972 tidak ditemukan. |
| 7 | **Canon AE-1** | `batch_01.md` | `high` | `low` | Sumber sesi ini fokus pada arsip spesifikasi bodi/lensa; hasil foto SLR film sangat bergantung roll film dan butuh ulasan galeri tambahan. |
| 8 | **Nikon FM2** | `batch_01.md` | `high` | `low` | Hanya 1 sumber ulasan tampilan foto (`35mmc`) dan bodi film mekanis sangat bergantung kombinasi lensa/film. |
| 20 | **Apple iPhone 4** | `batch_01.md` | `high` | `low` | Hanya 1 ulasan tampilan foto yang terverifikasi penuh pada sesi ini (`GSMArena`). |

### 4.2 Prioritas Menengah — `confidenceLook = medium` (11 entri)

Perlu tambahan minimal 1 ulasan foto independen atau foto referensi langsung saat kalibrasi:
- **Batch 01 (4):** `#2 FinePix F31fd` (2 utas forum pada domain sama), `#3 Cyber-shot DSC-T70` (artikel rilis + forum domain sama), `#4 Leica M9` (artikel + forum domain sama), `#9 Leica M6` (tampilan sangat bergantung lensa M & film).
- **Batch 03 (4):** `#42 Yashica Electro 35 GSN`, `#54 Canon Sure Shot Max (Prima 5 / Autoboy Mini)`, `#57 Rollei 35`, `#59 Olympus Pen FT`.
- **Batch 05 (3):** `#87 Nikon Coolpix S3300`, `#94 Pentax K1000`, `#95 Olympus OM-1`.

### 4.3 Tanpa Halaman Spesifikasi Resmi Produsen Aktif — `confidenceSpecs = medium` (35 entri)

Spesifikasi tetap diverifikasi dari 2+ situs/arsip fotografi independen, namun halaman resmi produsen tidak ditemukan di indeks web aktif:
- **Batch 01 (6):** `#2 FinePix F31fd`, `#10 Contax T2`, `#11 Olympus µ[mju:]-II`, `#12 Olympus Pen EE-3`, `#14 Holga 120N` *(tahun rilis pasti varian 120N juga tidak ditemukan)*, `#18 Hasselblad 500C/M`.
- **Batch 02 (5):** `#31 Panasonic Lumix DMC-FX01`, `#32 Kodak EasyShare V570`, `#33 Kodak EasyShare DX6490`, `#39 Olympus Camedia C-5050 Zoom`, `#40 Fujifilm FinePix Z5fd`.
- **Batch 03 (10):** `#41 Yashica T4 / T4 Super`, `#42 Yashica Electro 35 GSN`, `#43 Minolta TC-1`, `#44 Minolta Hi-Matic 7sII`, `#45 Minolta Freedom Escort`, `#46 Pentax Espio Mini`, `#47 Pentax PC35AF`, `#49 Konica Big Mini BM-201`, `#50 Konica Hexar AF`, `#57 Rollei 35`.
- **Batch 04 (9):** `#62 Ilford HP5 Plus Single Use`, `#63 Ilford XP2 Super Single Use` *(tahun rilis pasti varian sekali pakai juga tidak ditemukan)*, `#65 Polaroid Sun 660 Autofocus`, `#66 Polaroid Spectra`, `#74 Nintendo Game Boy Camera`, `#75 Sony Ericsson K800i`, `#76 Nokia N95`, `#77 Nokia N8`, `#78 Nokia 808 PureView`.
- **Batch 05 (5):** `#86 Fujifilm FinePix F10`, `#94 Pentax K1000`, `#96 Rolleiflex 2.8F`, `#97 Mamiya 7`, `#98 Pentax 67`.

*(Tambahan catatan tahun rilis: `#13 Fujifilm QuickSnap Flash 400` di `batch_01.md` memiliki halaman spesifikasi produsen resmi aktif `confidenceSpecs = high`, namun tahun rilis persis varian Flash 400 saat ini tidak tercantum di sumber resmi.)*

---

## 5. `lookTags` Paling Sering Muncul (Bahan Pemetaan Parameter M4–M6)

Tag teratas lintas 100 kamera yang dapat dijadikan acuan pemetaan awal ke pipeline shader di `docs/ARCHITECTURE.md`:
`soft-edges` (10), `high-contrast` (10), `sharp` (8), `clipped-highlights` (7), `harsh-flash` (6), `direct-flash` (6), `micro-contrast` (6), `soft-corners` (5), `heavy-vignette` (5), `film-like-grain` (4), `punchy-contrast` (4), `sharp-center` (4), `creamy-bokeh` (4), `vivid` (4), `smooth-bokeh` (4), `punchy-color` (4), `3d-pop` (3), `vertical-frame` (3), `classic-film` (3), `razor-sharp` (3), `noise-smoothing` (3), `edge-to-edge-sharp` (3), `dreamy` (3), `shallow-dof` (3), `deep-dof` (3).
