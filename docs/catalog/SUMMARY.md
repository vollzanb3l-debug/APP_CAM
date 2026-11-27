# Ringkasan Katalog 100 Kamera (`docs/catalog/SUMMARY.md`)

Ringkasan terverifikasi dari seluruh **5 batch katalog** ([batch_01.md](batch_01.md) hingga [batch_05.md](batch_05.md)) beserta pendalaman tampilan visual di [looks_batch_01.md](looks/looks_batch_01.md)–[looks_batch_05.md](looks/looks_batch_05.md) dan hasil audit ketat pra-kalibrasi di [AUDIT.md](looks/AUDIT.md), mengacu pada 15 arketipe di [archetypes.md](archetypes.md) dan catatan lisensi sumber daya di [resources.md](resources.md).

## 1. Ringkasan Umum

| Metrik | Jumlah | Keterangan |
|---|---|---|
| **Total kamera terverifikasi** | **100** | 5 batch × 20 kamera (`batch_01`–`batch_05` & `looks_batch_01`–`looks_batch_05`), 100 ID unik |
| **Jumlah arketipe tercakup** | **15 / 15** | Seluruh arketipe di `archetypes.md` memiliki perwakilan |
| **Foto/frame contoh diperiksa visual** | **309** | 250 foto CC/PD lokal di `docs/references/manifest.md` (di-`.gitignore`) + 59 foto ulasan diperiksa sementara lalu dihapus |
| **Tautan redirect belum di-resolve** | **0** | Seluruh URL adalah tautan kanonis langsung terverifikasi |
| **Parameter angka shader dikarang** | **0** | Murni deskripsi kualitatif & `lookTags` (penyetelan angka saat kalibrasi) |

---

## 2. Jumlah Entri per Arketipe

| No | ID Arketipe | Jumlah Entri | Persentase | Catatan Cakupan |
|---|---|---:|---:|---|
| 1 | `ccd_dreamy_pastel` | 14 | 14% | Perwakilan tinggi (Casio, Nikon Coolpix, Panasonic, Samsung, Pentax, Olympus, Fujifilm, Sony, Canon IXY) |
| 2 | `premium_point_shoot_35mm` | 14 | 14% | Kompak film premium (Contax, Olympus, Yashica, Minolta, Pentax, Konica, Nikon, Canon, Ricoh) |
| 3 | `ccd_compact_2000s` | 13 | 13% | Perwakilan tinggi (Canon PowerShot/IXY, Nikon Coolpix, Kodak EasyShare, Samsung, Pentax, Olympus, Sony) |
| 4 | `classic_35mm_slr` | 9 | 9% | SLR & rangefinder klasik 35mm |
| 5 | `pro_ccd_filmic` | 8 | 8% | CCD sensor besar / enthusiast (Leica M9, F31fd, F10, S95, LX3, XZ-1, GRD III, Sigma DP1 Merrill) |
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
| | **Total** | **100** | **100%** | *(Gabungan `ccd_dreamy_pastel` + `ccd_compact_2000s` = 27 kamera; + `pro_ccd_filmic` = 35 kamera)* |

*(Catatan teknis: `Nikon Coolpix S6900` #27 memakai sensor BSI CMOS 1/2,3" rilisan 2014 namun dipetakan ke `ccd_dreamy_pastel` karena karakter visual lembut-pastelnya; `Fujifilm FinePix F10` #86 dipindahkan ke `pro_ccd_filmic` pada tahap pendalaman tampilan agar selaras dengan saudara Super CCD HR 1/1,7"-nya `FinePix F31fd`.)*

---

## 3. Distribusi Confidence Setelah Audit Ketat (`AUDIT.md`)

### 3.1 `confidenceLook` (Deskripsi Tampilan Foto — Audit Ketat Pra-Kalibrasi)

Aturan audit ketat di [AUDIT.md](looks/AUDIT.md):
- **`high`:** `>= 2` domain sumber independen DAN `>= 3` foto contoh diperiksa visual DAN deskripsi konsisten DAN tidak berupa contoh campur film/lensa/olahan maupun 0 CC/PD lokal.
- **`medium`:** Sumber ada dan konsisten, tetapi hanya 1 domain visual, atau `< 3` foto contoh, atau contoh campur (film/lensa berbeda-beda, bodi berlensa tukar, kimia instan lintas era, olahan campur), atau 0 foto tunggal CC/PD lokal, atau mode khas (potret *Glamour/Beauty*, video *interlaced/24p*) belum diperiksa visual langsung.
- **`low`:** Bukti visual sangat terbatas (`<= 1` foto diperiksa, atau 0 CC/PD dengan `<= 2` foto sementara terbatas dari 1 galeri tunggal).
- **`tidak ditemukan`:** Tidak ada bukti memadai.

| Tingkat | Jumlah | Persentase | Rincian per Batch (B1 / B2 / B3 / B4 / B5) | Perubahan dari Pra-Audit |
|---|---:|---:|---|---|
| **`high`** | **46** | 46% | 8 / 17 / 0 / 10 / 11 | Turun 38 (dari 84) setelah pemisahan ketat contoh campur film/lensa & syarat CC/PD |
| **`medium`** | **49** | 49% | 12 / 3 / 19 / 7 / 8 | Naik 33 (dari 16) |
| **`low`** | **5** | 5% | 0 / 0 / 1 / 3 / 1 | Naik 5 (dari 0) |
| **`tidak ditemukan`** | **0** | 0% | 0 / 0 / 0 / 0 / 0 | Tetap 0 |
| **Total** | **100** | **100%** | **Seluruh 100 kamera diaudit 1-per-1 di [AUDIT.md](looks/AUDIT.md)** | **43 entri diturunkan secara jujur** |

### 3.2 `confidenceSpecs` (Verifikasi Spesifikasi Teknis)

| Tingkat | Jumlah | Persentase | Rincian per Batch |
|---|---:|---:|---|
| **`high`** | **64** | 64% | Batch 01: 13 · Batch 02: 15 · Batch 03: 10 · Batch 04: 11 · Batch 05: 15 |
| **`medium`** | **35** | 35% | Batch 01: 6 · Batch 02: 5 · Batch 03: 10 · Batch 04: 9 · Batch 05: 5 |
| **`low`** | **1** | 1% | Batch 01: 1 (`Polaroid SX-70`) · Batch 02–05: 0 |
| **Total** | **100** | **100%** | |

---

## 4. Daftar Entri yang Kurang Sumber / Butuh Perhatian Saat Kalibrasi

### 4.1 Prioritas Tinggi — `confidenceLook = low` (5 entri)

| No | Kamera (`id`) | Batch | Foto Diperiksa (CC/PD) | Alasan Status `low` |
|---:|---|---|---:|---|
| 54 | `canon_sure_shot_max` | `looks_batch_03.md` | 2 (0 CC) | 0 foto CC/PD; bukti visual hanya 2 foto B&W sementara dari 1 galeri ulasan (`35mmc.com`), tanpa sampel warna. |
| 68 | `fujifilm_instax_wide_300` | `looks_batch_04.md` | 1 (1 CC) | Hanya 1 pindaian cetak fisik (CC0 Wikimedia) yang diperiksa langsung; perlu tambahan sampel siang terik & *flash*. |
| 69 | `fujifilm_instax_square_sq6` | `looks_batch_04.md` | 1 (0 CC) | 0 foto CC/PD; bukti visual hanya 1 foto cetak sementara dari 1 galeri ulasan (`fstoppers.com`). |
| 70 | `fujifilm_instax_mini_11` | `looks_batch_04.md` | 1 (0 CC) | 0 foto CC/PD; bukti visual hanya 1 foto dinding kolase cetak dari 1 galeri ulasan (`popphoto.com`). |
| 99 | `sony_handycam_dcr_vx1000` | `looks_batch_05.md` | 2 (0 CC) | 0 foto CC/PD; bukti visual hanya 2 *still frame* sementara dari 1 arsip (`archive.org`) tanpa inspeksi gerak *interlaced*. |

### 4.2 Prioritas Menengah — `confidenceLook = medium` (49 entri)

Dikelompokkan berdasarkan akar penyebab agar mudah ditangani saat kalibrasi (rincian per kamera ada di [AUDIT.md](looks/AUDIT.md)):
- **Kamera film isi ulang berlensa tetap dengan contoh film campur/tidak tercatat (25):** `#10 contax_t2`, `#11 olympus_mju_ii`, `#12 olympus_pen_ee_3`, `#14 holga_120n`, `#15 lomo_lc_a`, `#41 yashica_t4`, `#42 yashica_electro_35_gsn`, `#43 minolta_tc_1`, `#44 minolta_hi_matic_7sii`, `#46 pentax_espio_mini`, `#47 pentax_pc35af`, `#49 konica_big_mini_bm_201`, `#50 konica_hexar_af`, `#51 nikon_l35af`, `#52 nikon_35ti`, `#53 canon_canonet_ql17_giii`, `#55 olympus_xa`, `#56 ricoh_gr1v`, `#57 rollei_35`, `#58 kodak_ektar_h35`, `#60 canon_demi_ee17`, `#64 lomography_simple_use_reloadable_film_camera`, `#71 lomography_diana_f_plus`, `#72 lomography_la_sardina`, `#96 rollei_rolleiflex_2_8f`. *(Karakter optik lensa tetapnya konsisten, tetapi kalibrasi warna harus memisahkan lapisan film.)*
- **Bodi film berlensa tukar (9):** `#7 canon_ae_1`, `#8 nikon_fm2`, `#9 leica_m6`, `#18 hasselblad_500c_m`, `#59 olympus_pen_ft`, `#94 pentax_k1000`, `#95 olympus_om_1`, `#97 mamiya_7`, `#98 pentax_67`.
- **0 foto tunggal CC/PD lokal — bukti visual hanya dari inspeksi sementara galeri ulasan/arsip (7 dari 11 total; 4 lainnya masuk `low` di atas):**
  - *1 galeri ulasan (< 3 foto):* `#63 ilford_xp2_super_single_use_camera` (2 foto *The Darkroom*), `#66 polaroid_spectra` (2 pindaian *Blue Moon Camera Codex*), `#73 camp_snap_camera` (2 foto *35mmc*).
  - *2 galeri/arsip sementara (film campur atau tanpa video/foto tunggal CC):* `#45 minolta_freedom_escort`, `#48 pentax_17`, `#83 sony_mavica_mvc_fd7` *(hanya 1 kolase grid CC + 5 berkas tunggal sementara)*, `#100 panasonic_ag_dvx100`.
- **Kamera digital / instan dengan catatan sampel khusus (8):** `#4 leica_m9` & `#92 fujifilm_x_pro1` *(lensa tukar)*, `#16 polaroid_sx_70` *(campuran kimia 1985 vs modern)*, `#19 sony_handycam_dcr_trv900` *(tanpa inspeksi gerak video)*, `#27 nikon_coolpix_s6900` & `#35 samsung_st550` *(mode potret Glamour/Beauty belum diverifikasi visual demi privasi)*, `#28 nikon_coolpix_p7000` *(sampel campur SOOC/ViewNX/Photoshop bawah air)*, `#67 polaroid_now_gen_2` *(baru 2 foto diperiksa < 3)*.

### 4.3 Tanpa Halaman Spesifikasi Resmi Produsen Aktif — `confidenceSpecs = medium` (35 entri)

Spesifikasi tetap diverifikasi dari 2+ situs/arsip fotografi independen, namun halaman resmi produsen tidak ditemukan di indeks web aktif (tercatat juga per kamera di baris `Tidak ditemukan` masing-masing entri `looks_batch_01`–`05.md`):
- **Batch 01 (6 + 1 `low`):** `#2 FinePix F31fd`, `#10 Contax T2`, `#11 Olympus µ[mju:]-II`, `#12 Olympus Pen EE-3`, `#14 Holga 120N`, `#16 Polaroid SX-70` (`low`), `#18 Hasselblad 500C/M`.
- **Batch 02 (5):** `#31 Panasonic Lumix DMC-FX01`, `#32 Kodak EasyShare V570`, `#33 Kodak EasyShare DX6490`, `#39 Olympus Camedia C-5050 Zoom`, `#40 Fujifilm FinePix Z5fd`.
- **Batch 03 (10):** `#41 Yashica T4 / T4 Super`, `#42 Yashica Electro 35 GSN`, `#43 Minolta TC-1`, `#44 Minolta Hi-Matic 7sII`, `#45 Minolta Freedom Escort`, `#46 Pentax Espio Mini`, `#47 Pentax PC35AF`, `#49 Konica Big Mini BM-201`, `#50 Konica Hexar AF`, `#57 Rollei 35`.
- **Batch 04 (9):** `#62 Ilford HP5 Plus Single Use`, `#63 Ilford XP2 Super Single Use`, `#65 Polaroid Sun 660 Autofocus`, `#66 Polaroid Spectra`, `#74 Nintendo Game Boy Camera`, `#75 Sony Ericsson K800i`, `#76 Nokia N95`, `#77 Nokia N8`, `#78 Nokia 808 PureView`.
- **Batch 05 (5):** `#86 Fujifilm FinePix F10`, `#94 Pentax K1000`, `#96 Rolleiflex 2.8F`, `#97 Mamiya 7`, `#98 Pentax 67`.

*(Catatan tahun rilis: tahun rilis pasti varian saat ini tidak ditemukan di sumber resmi untuk `#13 QuickSnap Flash 400`, `#14 Holga 120N`, dan `#63 Ilford XP2 Super Single Use Camera`.)*

---

## 5. `lookTags` Paling Sering Muncul (Bahan Pemetaan Parameter Kalibrasi)

Tag teratas lintas 100 kamera yang menjadi acuan pemetaan ke pipeline shader di `docs/ARCHITECTURE.md`:
`soft-edges`, `high-contrast`, `sharp`, `clipped-highlights`, `harsh-flash`, `direct-flash`, `micro-contrast`, `soft-corners`, `heavy-vignette`, `film-like-grain`, `punchy-contrast`, `sharp-center`, `creamy-bokeh`, `vivid`, `smooth-bokeh`, `punchy-color`, `3d-pop`, `vertical-frame`, `classic-film`, `razor-sharp`, `noise-smoothing`, `edge-to-edge-sharp`, `dreamy`, `shallow-dof`, `deep-dof`.
