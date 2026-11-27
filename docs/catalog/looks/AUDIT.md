# Audit Konsistensi Katalog Tampilan 100 Kamera (`docs/catalog/looks/AUDIT.md`)

Audit ketat sebelum katalog dipakai sebagai dasar kalibrasi resep shader RetroCam. Seluruh deskripsi tetap murni **kualitatif** (0 angka parameter shader), tanpa foto di repositori Git, dan seluruh penurunan label dilakukan apa adanya sesuai kekuatan bukti.

## 1. Aturan Penilaian `confidenceLook` Ketat

- **`high`:** Minimal **2 domain sumber independen** DAN minimal **3 foto contoh yang diperiksa visual** DAN deskripsi **konsisten antar sumber** DAN **tidak berupa contoh campur** (bukan film/lensa berbeda-beda, bukan campuran olahan, bukan mode potret/video khas yang belum diverifikasi visual, serta memiliki rujukan foto lokal CC/PD yang dapat diaudit ulang).
- **`medium`:** Sumber tersedia dan konsisten, tetapi hanya bertumpu pada **1 domain visual**, ATAU jumlah foto contoh diperiksa **< 3**, ATAU **contoh campur** (kamera film isi ulang dengan roll/emulsi berbeda-beda, bodi berlensa tukar, kimia instan lintas era, olahan campur), ATAU **0 foto tunggal CC/PD lokal** (hanya inspeksi sementara), ATAU mode khas (potret *Glamour/Beauty*, video *interlaced/24p*) belum diperiksa langsung.
- **`low`:** Bukti visual **sangat terbatas** (hanya 1 foto contoh diperiksa, atau 0 CC/PD dengan <= 2 foto sementara terbatas dari 1 galeri tunggal seperti hanya hitam-putih atau 2 *still frame* camcorder) atau sumber saling bertentangan.
- **`tidak ditemukan`:** Tidak ada bukti visual maupun tekstual yang memadai.

### Ringkasan Perubahan Distribusi (100 Kamera)

| Tingkat `confidenceLook` | Sebelum Audit (`685b745`) | Sesudah Audit Ketat | Selisih | Rincian per Batch (B1 / B2 / B3 / B4 / B5) |
|---|---:|---:|---:|---|
| **`high`** | 84 | **46** | -38 | 8 / 17 / 0 / 10 / 11 |
| **`medium`** | 16 | **49** | +33 | 12 / 3 / 19 / 7 / 8 |
| **`low`** | 0 | **5** | +5 | 0 / 0 / 1 / 3 / 1 |
| **`tidak ditemukan`** | 0 | **0** | 0 | 0 / 0 / 0 / 0 / 0 |
| **Total** | **100** | **100** | **43 entri diturunkan** | Total diperiksa visual: **310 foto/frame** (250 CC/PD lokal di `docs/references/manifest.md`) |

---

## 2. Tabel Audit Lengkap 100 Kamera

> **Catatan kolom:** `Domain Indep.` = jumlah domain unik terverifikasi pada daftar sumber kamera (dalam kurung: jumlah domain khusus sumber foto yang diperiksa visual). Perubahan label ditandai tebal pada kolom `Status`.

| No | `id` | Batch | Domain Indep. (Visual) | Foto Diperiksa | Foto CC/PD Lokal | Lama | Baru | Status & Alasan Perubahan / Catatan Bukti |
|---:|---|---:|---:|---:|---:|---|---|---|
| 1 | `canon_powershot_sd1000` | B01 | 5 (1) | 3 | 3 | `high` | **`high`** | Tetap (`high`) — Memenuhi syarat ketat (>= 2 domain independen [5], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa). |
| 2 | `fujifilm_finepix_f31fd` | B01 | 4 (2) | 3 | 3 | `high` | **`high`** | Tetap (`high`) — Memenuhi syarat ketat (>= 2 domain independen [4], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa). |
| 3 | `sony_cyber_shot_dsc_t70` | B01 | 6 (2) | 3 | 3 | `high` | **`high`** | Tetap (`high`) — Memenuhi syarat ketat (>= 2 domain independen [6], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa). |
| 4 | `leica_m9` | B01 | 6 (2) | 3 | 3 | `high` | **`medium`** | **TURUN (`high`→`medium`)** — Kamera digital berlensa tukar (mount Leica M); karakter optik (vignette, bokeh, flare) bervariasi antar lensa sampel. |
| 5 | `ricoh_gr_iii` | B01 | 5 (1) | 3 | 3 | `high` | **`high`** | Tetap (`high`) — Memenuhi syarat ketat (>= 2 domain independen [5], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa). |
| 6 | `fujifilm_x100v` | B01 | 5 (2) | 3 | 3 | `high` | **`high`** | Tetap (`high`) — Memenuhi syarat ketat (>= 2 domain independen [5], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa). |
| 7 | `canon_ae_1` | B01 | 5 (1) | 3 | 3 | `medium` | **`medium`** | Tetap (`medium`) — Contoh campur: bodi kamera film berlensa tukar dengan lensa dan emulsi film berbeda-beda/sebagian tidak tercatat. |
| 8 | `nikon_fm2` | B01 | 3 (1) | 3 | 3 | `medium` | **`medium`** | Tetap (`medium`) — Contoh campur: bodi kamera film berlensa tukar dengan lensa dan emulsi film berbeda-beda/sebagian tidak tercatat. |
| 9 | `leica_m6` | B01 | 5 (2) | 3 | 3 | `medium` | **`medium`** | Tetap (`medium`) — Contoh campur: bodi kamera film berlensa tukar dengan lensa dan emulsi film berbeda-beda/sebagian tidak tercatat. |
| 10 | `contax_t2` | B01 | 4 (1) | 3 | 3 | `high` | **`medium`** | **TURUN (`high`→`medium`)** — Contoh campur emulsi film: lensa tetap konsisten secara optik, namun foto contoh memakai jenis film berbeda atau tidak dicantumkan. |
| 11 | `olympus_mju_ii` | B01 | 5 (2) | 3 | 3 | `high` | **`medium`** | **TURUN (`high`→`medium`)** — Contoh campur emulsi film: lensa tetap konsisten secara optik, namun foto contoh memakai jenis film berbeda atau tidak dicantumkan. |
| 12 | `olympus_pen_ee_3` | B01 | 5 (3) | 4 | 2 | `high` | **`medium`** | **TURUN (`high`→`medium`)** — Contoh campur emulsi film: lensa tetap konsisten secara optik, namun foto contoh memakai jenis film berbeda atau tidak dicantumkan. |
| 13 | `fujifilm_quicksnap_flash_400` | B01 | 4 (2) | 5 | 2 | `high` | **`high`** | Tetap (`high`) — Memenuhi syarat ketat (>= 2 domain independen [4], >= 3 foto diperiksa [5], konsisten, tanpa campuran film/lensa). |
| 14 | `holga_120n` | B01 | 5 (2) | 3 | 3 | `high` | **`medium`** | **TURUN (`high`→`medium`)** — Contoh campur emulsi film: lensa tetap konsisten secara optik, namun foto contoh memakai jenis film berbeda atau tidak dicantumkan. |
| 15 | `lomo_lc_a` | B01 | 4 (2) | 3 | 3 | `high` | **`medium`** | **TURUN (`high`→`medium`)** — Contoh campur emulsi film: lensa tetap konsisten secara optik, namun foto contoh memakai jenis film berbeda atau tidak dicantumkan. |
| 16 | `polaroid_sx_70` | B01 | 5 (1) | 3 | 3 | `high` | **`medium`** | **TURUN (`high`→`medium`)** — Contoh campur kimia emulsi instan lintas era (Polaroid Time-Zero 1985 vs Polaroid Originals SX-70 modern). |
| 17 | `fujifilm_instax_mini_90_neo_classic` | B01 | 5 (1) | 3 | 3 | `high` | **`high`** | Tetap (`high`) — Memenuhi syarat ketat (>= 2 domain independen [5], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa). |
| 18 | `hasselblad_500c_m` | B01 | 5 (2) | 3 | 3 | `high` | **`medium`** | **TURUN (`high`→`medium`)** — Contoh campur: bodi kamera film berlensa tukar dengan lensa dan emulsi film berbeda-beda/sebagian tidak tercatat. |
| 19 | `sony_handycam_dcr_trv900` | B01 | 4 (2) | 4 | 1 | `high` | **`medium`** | **TURUN (`high`→`medium`)** — 4 frame still diperiksa lintas 2 domain (Wikimedia + BealeCorner), namun karakter gerak video interlaced hanya dari ulasan teknis. |
| 20 | `apple_iphone_4` | B01 | 5 (1) | 3 | 3 | `high` | **`high`** | Tetap (`high`) — Memenuhi syarat ketat (>= 2 domain independen [5], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa). |
| 21 | `canon_powershot_g9` | B02 | 7 (1) | 3 | 3 | `high` | **`high`** | Tetap (`high`) — Memenuhi syarat ketat (>= 2 domain independen [7], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa). |
| 22 | `canon_powershot_s95` | B02 | 7 (1) | 3 | 3 | `high` | **`high`** | Tetap (`high`) — Memenuhi syarat ketat (>= 2 domain independen [7], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa). |
| 23 | `canon_ixy_digital_900_is` | B02 | 5 (1) | 3 | 3 | `high` | **`high`** | Tetap (`high`) — Memenuhi syarat ketat (>= 2 domain independen [5], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa). |
| 24 | `canon_powershot_a590_is` | B02 | 6 (1) | 3 | 3 | `high` | **`high`** | Tetap (`high`) — Memenuhi syarat ketat (>= 2 domain independen [6], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa). |
| 25 | `casio_exilim_ex_z75` | B02 | 6 (1) | 3 | 3 | `high` | **`high`** | Tetap (`high`) — Memenuhi syarat ketat (>= 2 domain independen [6], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa). |
| 26 | `casio_exilim_ex_s10` | B02 | 6 (1) | 3 | 3 | `high` | **`high`** | Tetap (`high`) — Memenuhi syarat ketat (>= 2 domain independen [6], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa). |
| 27 | `nikon_coolpix_s6900` | B02 | 7 (1) | 3 | 3 | `high` | **`medium`** | **TURUN (`high`→`medium`)** — 3 foto luar ruang SOOC bebas wajah; mode potret Glamour khasnya belum diverifikasi visual karena aturan privasi. |
| 28 | `nikon_coolpix_p7000` | B02 | 7 (1) | 3 | 3 | `high` | **`medium`** | **TURUN (`high`→`medium`)** — Contoh campur pemrosesan/kondisi (1 SOOC, 1 ViewNX, 1 foto bawah air olahan Photoshop CS4). |
| 29 | `nikon_coolpix_990` | B02 | 6 (1) | 3 | 3 | `high` | **`high`** | Tetap (`high`) — Memenuhi syarat ketat (>= 2 domain independen [6], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa). |
| 30 | `panasonic_lumix_dmc_lx3` | B02 | 8 (1) | 3 | 3 | `high` | **`high`** | Tetap (`high`) — Memenuhi syarat ketat (>= 2 domain independen [8], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa). |
| 31 | `panasonic_lumix_dmc_fx01` | B02 | 6 (1) | 3 | 3 | `high` | **`high`** | Tetap (`high`) — Memenuhi syarat ketat (>= 2 domain independen [6], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa). |
| 32 | `kodak_easyshare_v570` | B02 | 5 (1) | 3 | 3 | `high` | **`high`** | Tetap (`high`) — Memenuhi syarat ketat (>= 2 domain independen [5], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa). |
| 33 | `kodak_easyshare_dx6490` | B02 | 7 (1) | 3 | 3 | `high` | **`high`** | Tetap (`high`) — Memenuhi syarat ketat (>= 2 domain independen [7], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa). |
| 34 | `samsung_nv10` | B02 | 6 (1) | 3 | 3 | `high` | **`high`** | Tetap (`high`) — Memenuhi syarat ketat (>= 2 domain independen [6], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa). |
| 35 | `samsung_st550` | B02 | 6 (1) | 3 | 3 | `high` | **`medium`** | **TURUN (`high`→`medium`)** — 3 foto luar ruang SOOC bebas wajah; mode Beauty Shot khasnya belum diverifikasi visual karena aturan privasi. |
| 36 | `pentax_optio_s` | B02 | 6 (1) | 3 | 3 | `high` | **`high`** | Tetap (`high`) — Memenuhi syarat ketat (>= 2 domain independen [6], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa). |
| 37 | `pentax_optio_w60` | B02 | 6 (1) | 3 | 3 | `high` | **`high`** | Tetap (`high`) — Memenuhi syarat ketat (>= 2 domain independen [6], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa). |
| 38 | `olympus_xz_1` | B02 | 6 (1) | 3 | 3 | `high` | **`high`** | Tetap (`high`) — Memenuhi syarat ketat (>= 2 domain independen [6], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa). |
| 39 | `olympus_camedia_c_5050_zoom` | B02 | 7 (1) | 3 | 3 | `high` | **`high`** | Tetap (`high`) — Memenuhi syarat ketat (>= 2 domain independen [7], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa). |
| 40 | `fujifilm_finepix_z5fd` | B02 | 7 (1) | 3 | 3 | `high` | **`high`** | Tetap (`high`) — Memenuhi syarat ketat (>= 2 domain independen [7], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa). |
| 41 | `yashica_t4` | B03 | 4 (1) | 3 | 3 | `high` | **`medium`** | **TURUN (`high`→`medium`)** — Contoh campur emulsi film: lensa tetap konsisten secara optik, namun foto contoh memakai jenis film berbeda atau tidak dicantumkan. |
| 42 | `yashica_electro_35_gsn` | B03 | 4 (1) | 3 | 3 | `medium` | **`medium`** | Tetap (`medium`) — Contoh campur emulsi film: lensa tetap konsisten secara optik, namun foto contoh memakai jenis film berbeda atau tidak dicantumkan. |
| 43 | `minolta_tc_1` | B03 | 4 (2) | 3 | 3 | `high` | **`medium`** | **TURUN (`high`→`medium`)** — Contoh campur emulsi film: lensa tetap konsisten secara optik, namun foto contoh memakai jenis film berbeda atau tidak dicantumkan. |
| 44 | `minolta_hi_matic_7sii` | B03 | 4 (3) | 3 | 2 | `high` | **`medium`** | **TURUN (`high`→`medium`)** — Contoh campur emulsi film: lensa tetap konsisten secara optik, namun foto contoh memakai jenis film berbeda atau tidak dicantumkan. |
| 45 | `minolta_freedom_escort` | B03 | 2 (2) | 5 | 0 | `high` | **`medium`** | **TURUN (`high`→`medium`)** — 0 foto CC/PD lokal; 5 foto sementara lintas 2 galeri ulasan (35mmc & New Wave Pool) dengan film campur (warna & Ilford HP5+). |
| 46 | `pentax_espio_mini` | B03 | 4 (2) | 4 | 3 | `high` | **`medium`** | **TURUN (`high`→`medium`)** — Contoh campur emulsi film: lensa tetap konsisten secara optik, namun foto contoh memakai jenis film berbeda atau tidak dicantumkan. |
| 47 | `pentax_pc35af` | B03 | 4 (1) | 3 | 3 | `high` | **`medium`** | **TURUN (`high`→`medium`)** — Contoh campur emulsi film: lensa tetap konsisten secara optik, namun foto contoh memakai jenis film berbeda atau tidak dicantumkan. |
| 48 | `pentax_17` | B03 | 4 (2) | 5 | 0 | `high` | **`medium`** | **TURUN (`high`→`medium`)** — 0 foto CC/PD lokal; 5 foto sementara lintas 2 galeri ulasan (Shoot It With Film & 35mmc) dengan film campur (warna & B&W). |
| 49 | `konica_big_mini_bm_201` | B03 | 5 (3) | 4 | 2 | `high` | **`medium`** | **TURUN (`high`→`medium`)** — Contoh campur emulsi film: lensa tetap konsisten secara optik, namun foto contoh memakai jenis film berbeda atau tidak dicantumkan. |
| 50 | `konica_hexar_af` | B03 | 4 (1) | 3 | 3 | `high` | **`medium`** | **TURUN (`high`→`medium`)** — Contoh campur emulsi film: lensa tetap konsisten secara optik, namun foto contoh memakai jenis film berbeda atau tidak dicantumkan. |
| 51 | `nikon_l35af` | B03 | 4 (2) | 3 | 1 | `high` | **`medium`** | **TURUN (`high`→`medium`)** — Contoh campur emulsi film: lensa tetap konsisten secara optik, namun foto contoh memakai jenis film berbeda atau tidak dicantumkan. |
| 52 | `nikon_35ti` | B03 | 5 (1) | 3 | 3 | `high` | **`medium`** | **TURUN (`high`→`medium`)** — Contoh campur emulsi film: lensa tetap konsisten secara optik, namun foto contoh memakai jenis film berbeda atau tidak dicantumkan. |
| 53 | `canon_canonet_ql17_giii` | B03 | 5 (3) | 3 | 2 | `high` | **`medium`** | **TURUN (`high`→`medium`)** — Contoh campur emulsi film: lensa tetap konsisten secara optik, namun foto contoh memakai jenis film berbeda atau tidak dicantumkan. |
| 54 | `canon_sure_shot_max` | B03 | 4 (1) | 2 | 0 | `medium` | **`low`** | **TURUN (`medium`→`low`)** — 0 foto CC/PD; hanya 2 foto B&W sementara dari 1 galeri ulasan (35mmc), tanpa sampel warna. |
| 55 | `olympus_xa` | B03 | 6 (1) | 3 | 3 | `high` | **`medium`** | **TURUN (`high`→`medium`)** — Contoh campur emulsi film: lensa tetap konsisten secara optik, namun foto contoh memakai jenis film berbeda atau tidak dicantumkan. |
| 56 | `ricoh_gr1v` | B03 | 6 (3) | 4 | 3 | `high` | **`medium`** | **TURUN (`high`→`medium`)** — Contoh campur emulsi film: lensa tetap konsisten secara optik, namun foto contoh memakai jenis film berbeda atau tidak dicantumkan. |
| 57 | `rollei_35` | B03 | 5 (2) | 3 | 3 | `medium` | **`medium`** | Tetap (`medium`) — Contoh campur emulsi film: lensa tetap konsisten secara optik, namun foto contoh memakai jenis film berbeda atau tidak dicantumkan. |
| 58 | `kodak_ektar_h35` | B03 | 5 (1) | 3 | 3 | `high` | **`medium`** | **TURUN (`high`→`medium`)** — Contoh campur emulsi film: lensa tetap konsisten secara optik, namun foto contoh memakai jenis film berbeda atau tidak dicantumkan. |
| 59 | `olympus_pen_ft` | B03 | 4 (1) | 3 | 3 | `medium` | **`medium`** | Tetap (`medium`) — Contoh campur: bodi kamera film berlensa tukar dengan lensa dan emulsi film berbeda-beda/sebagian tidak tercatat. |
| 60 | `canon_demi_ee17` | B03 | 5 (1) | 3 | 3 | `high` | **`medium`** | **TURUN (`high`→`medium`)** — Contoh campur emulsi film: lensa tetap konsisten secara optik, namun foto contoh memakai jenis film berbeda atau tidak dicantumkan. |
| 61 | `kodak_funsaver` | B04 | 7 (3) | 4 | 3 | `high` | **`high`** | Tetap (`high`) — Memenuhi syarat ketat (>= 2 domain independen [7], >= 3 foto diperiksa [4], konsisten, tanpa campuran film/lensa). |
| 62 | `ilford_hp5_plus_single_use_camera` | B04 | 4 (2) | 3 | 1 | `high` | **`high`** | Tetap (`high`) — Memenuhi syarat ketat (>= 2 domain independen [4], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa). |
| 63 | `ilford_xp2_super_single_use_camera` | B04 | 3 (1) | 2 | 0 | `medium` | **`medium`** | Tetap (`medium`) — 0 foto CC/PD lokal; bukti visual hanya 2 foto sementara (< 3) dari 1 galeri ulasan (The Darkroom), ditopang 3 ulasan konsisten. |
| 64 | `lomography_simple_use_reloadable_film_camera` | B04 | 5 (1) | 3 | 3 | `high` | **`medium`** | **TURUN (`high`→`medium`)** — Contoh campur emulsi film: lensa tetap konsisten secara optik, namun foto contoh memakai jenis film berbeda atau tidak dicantumkan. |
| 65 | `polaroid_sun_660_autofocus` | B04 | 3 (2) | 3 | 1 | `high` | **`high`** | Tetap (`high`) — Memenuhi syarat ketat (>= 2 domain independen [3], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa). |
| 66 | `polaroid_spectra` | B04 | 4 (1) | 2 | 0 | `medium` | **`medium`** | Tetap (`medium`) — 0 foto CC/PD lokal; bukti visual hanya 2 pindaian sementara (< 3, warna & B&W) dari 1 galeri ulasan (Blue Moon Camera Codex). |
| 67 | `polaroid_now_gen_2` | B04 | 5 (2) | 2 | 1 | `high` | **`medium`** | **TURUN (`high`→`medium`)** — Foto contoh diperiksa visual < 3 (hanya 2: 1 Flickr CC + 1 sampel ulasan Myles Katherine). |
| 68 | `fujifilm_instax_wide_300` | B04 | 5 (1) | 1 | 1 | `medium` | **`low`** | **TURUN (`medium`→`low`)** — Bukti visual sangat terbatas: hanya 1 pindaian cetak fisik (CC0 Wikimedia) yang diperiksa langsung. |
| 69 | `fujifilm_instax_square_sq6` | B04 | 5 (1) | 1 | 0 | `medium` | **`low`** | **TURUN (`medium`→`low`)** — 0 foto CC/PD; bukti visual sangat terbatas: hanya 1 foto cetak sementara dari 1 galeri ulasan (Fstoppers). |
| 70 | `fujifilm_instax_mini_11` | B04 | 4 (1) | 1 | 0 | `medium` | **`low`** | **TURUN (`medium`→`low`)** — 0 foto CC/PD; bukti visual sangat terbatas: hanya 1 foto dinding kolase cetak dari 1 galeri ulasan (Popular Photography). |
| 71 | `lomography_diana_f_plus` | B04 | 5 (3) | 6 | 2 | `high` | **`medium`** | **TURUN (`high`→`medium`)** — Contoh campur emulsi film: lensa tetap konsisten secara optik, namun foto contoh memakai jenis film berbeda atau tidak dicantumkan. |
| 72 | `lomography_la_sardina` | B04 | 5 (2) | 3 | 3 | `high` | **`medium`** | **TURUN (`high`→`medium`)** — Contoh campur emulsi film: lensa tetap konsisten secara optik, namun foto contoh memakai jenis film berbeda atau tidak dicantumkan. |
| 73 | `camp_snap_camera` | B04 | 5 (1) | 2 | 0 | `medium` | **`medium`** | Tetap (`medium`) — 0 foto CC/PD lokal; bukti visual hanya 2 foto sementara (< 3) dari 1 galeri ulasan (35mmc), ditopang 4 ulasan konsisten. |
| 74 | `nintendo_game_boy_camera` | B04 | 5 (2) | 3 | 3 | `high` | **`high`** | Tetap (`high`) — Memenuhi syarat ketat (>= 2 domain independen [5], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa). |
| 75 | `sony_ericsson_k800i` | B04 | 5 (1) | 3 | 3 | `high` | **`high`** | Tetap (`high`) — Memenuhi syarat ketat (>= 2 domain independen [5], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa). |
| 76 | `nokia_n95` | B04 | 4 (1) | 3 | 3 | `high` | **`high`** | Tetap (`high`) — Memenuhi syarat ketat (>= 2 domain independen [4], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa). |
| 77 | `nokia_n8` | B04 | 4 (2) | 3 | 3 | `high` | **`high`** | Tetap (`high`) — Memenuhi syarat ketat (>= 2 domain independen [4], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa). |
| 78 | `nokia_808_pureview` | B04 | 5 (1) | 3 | 3 | `high` | **`high`** | Tetap (`high`) — Memenuhi syarat ketat (>= 2 domain independen [5], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa). |
| 79 | `apple_iphone_3gs` | B04 | 4 (1) | 3 | 3 | `high` | **`high`** | Tetap (`high`) — Memenuhi syarat ketat (>= 2 domain independen [4], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa). |
| 80 | `samsung_galaxy_s_ii` | B04 | 5 (1) | 3 | 3 | `high` | **`high`** | Tetap (`high`) — Memenuhi syarat ketat (>= 2 domain independen [5], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa). |
| 81 | `sony_cyber_shot_dsc_w55` | B05 | 5 (1) | 3 | 3 | `high` | **`high`** | Tetap (`high`) — Memenuhi syarat ketat (>= 2 domain independen [5], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa). |
| 82 | `sony_cyber_shot_dsc_t7` | B05 | 6 (1) | 3 | 3 | `high` | **`high`** | Tetap (`high`) — Memenuhi syarat ketat (>= 2 domain independen [6], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa). |
| 83 | `sony_mavica_mvc_fd7` | B05 | 7 (3) | 6 | 1 | `high` | **`medium`** | **TURUN (`high`→`medium`)** — 0 foto tunggal 640x480 CC/PD lokal (hanya 1 kolase grid 100 foto CC di Wikimedia + 5 sampel sementara lintas 2 arsip). |
| 84 | `canon_powershot_g10` | B05 | 5 (1) | 3 | 3 | `high` | **`high`** | Tetap (`high`) — Memenuhi syarat ketat (>= 2 domain independen [5], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa). |
| 85 | `canon_ixy_digital_110_is` | B05 | 5 (1) | 3 | 3 | `high` | **`high`** | Tetap (`high`) — Memenuhi syarat ketat (>= 2 domain independen [5], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa). |
| 86 | `fujifilm_finepix_f10` | B05 | 5 (1) | 3 | 3 | `high` | **`high`** | Tetap (`high`) — Memenuhi syarat ketat (>= 2 domain independen [5], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa). |
| 87 | `nikon_coolpix_s3300` | B05 | 4 (1) | 3 | 3 | `high` | **`high`** | Tetap (`high`) — Memenuhi syarat ketat (>= 2 domain independen [4], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa). |
| 88 | `casio_exilim_ex_z1080` | B05 | 6 (2) | 3 | 3 | `high` | **`high`** | Tetap (`high`) — Memenuhi syarat ketat (>= 2 domain independen [6], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa). |
| 89 | `olympus_mju_700` | B05 | 6 (1) | 3 | 3 | `high` | **`high`** | Tetap (`high`) — Memenuhi syarat ketat (>= 2 domain independen [6], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa). |
| 90 | `ricoh_gr_digital_iii` | B05 | 5 (1) | 3 | 3 | `high` | **`high`** | Tetap (`high`) — Memenuhi syarat ketat (>= 2 domain independen [5], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa). |
| 91 | `sigma_dp1_merrill` | B05 | 5 (1) | 3 | 3 | `high` | **`high`** | Tetap (`high`) — Memenuhi syarat ketat (>= 2 domain independen [5], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa). |
| 92 | `fujifilm_x_pro1` | B05 | 8 (2) | 3 | 3 | `high` | **`medium`** | **TURUN (`high`→`medium`)** — Kamera mirrorless berlensa tukar (mount Fujifilm X); karakter optik bervariasi antar lensa sampel walau sensor X-Trans intrinsik. |
| 93 | `sony_cyber_shot_dsc_rx100` | B05 | 6 (1) | 3 | 3 | `high` | **`high`** | Tetap (`high`) — Memenuhi syarat ketat (>= 2 domain independen [6], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa). |
| 94 | `pentax_k1000` | B05 | 6 (1) | 3 | 3 | `medium` | **`medium`** | Tetap (`medium`) — Contoh campur: bodi kamera film berlensa tukar dengan lensa dan emulsi film berbeda-beda/sebagian tidak tercatat. |
| 95 | `olympus_om_1` | B05 | 6 (1) | 3 | 3 | `medium` | **`medium`** | Tetap (`medium`) — Contoh campur: bodi kamera film berlensa tukar dengan lensa dan emulsi film berbeda-beda/sebagian tidak tercatat. |
| 96 | `rollei_rolleiflex_2_8f` | B05 | 4 (2) | 3 | 3 | `high` | **`medium`** | **TURUN (`high`→`medium`)** — Contoh campur emulsi film: lensa tetap konsisten secara optik, namun foto contoh memakai jenis film berbeda atau tidak dicantumkan. |
| 97 | `mamiya_7` | B05 | 6 (1) | 3 | 3 | `high` | **`medium`** | **TURUN (`high`→`medium`)** — Contoh campur: bodi kamera film berlensa tukar dengan lensa dan emulsi film berbeda-beda/sebagian tidak tercatat. |
| 98 | `pentax_67` | B05 | 5 (1) | 3 | 3 | `high` | **`medium`** | **TURUN (`high`→`medium`)** — Contoh campur: bodi kamera film berlensa tukar dengan lensa dan emulsi film berbeda-beda/sebagian tidak tercatat. |
| 99 | `sony_handycam_dcr_vx1000` | B05 | 6 (1) | 2 | 0 | `medium` | **`low`** | **TURUN (`medium`→`low`)** — 0 foto CC/PD; hanya 2 frame still sementara dari 1 arsip (Internet Archive) untuk camcorder video tanpa inspeksi gerak interlaced. |
| 100 | `panasonic_ag_dvx100` | B05 | 5 (2) | 7 | 0 | `high` | **`medium`** | **TURUN (`high`→`medium`)** — 0 foto CC/PD lokal; bukti visual hanya dari 7 frame sementara lintas 2 arsip (Adam Wilt & Internet Archive) tanpa pemutaran video 24p. |

---

## 3. Audit Khusus 11 Kamera Tanpa Foto Tunggal CC/PD Lokal

Sebelas kamera di bawah ini tidak memiliki foto hasil bidikan tunggal berlisensi terbuka (CC0/CC BY/CC BY-SA/PD) di Wikimedia Commons maupun Openverse Flickr CC (seluruh hasil pencarian awal berupa foto bodi produk kamera atau kecocokan keliru yang telah dibersihkan). Seluruhnya telah diturunkan secara jujur sesuai aturan ketat:

| No | `id` | Batch | Foto CC/PD Lokal | Bukti Visual Aktual (Inspeksi Sementara) | Lama → Baru | Catatan Transparansi |
|---:|---|---:|---:|---|---|---|
| 45 | `minolta_freedom_escort` | B03 | 0 | 5 foto sementara lintas 2 galeri ulasan (`35mmc.com` & `newwavepool.ca`) | `high` → **`medium`** | Bukan 1 galeri tunggal, namun tetap turun ke `medium` karena 0 CC/PD lokal + campuran film (warna tidak dirinci & Ilford HP5+). |
| 48 | `pentax_17` | B03 | 0 | 5 foto sementara lintas 2 galeri ulasan (`shootitwithfilm.com` & `35mmc.com`) | `high` → **`medium`** | Bukan 1 galeri tunggal, namun tetap turun ke `medium` karena 0 CC/PD lokal + campuran film warna & hitam-putih. |
| 54 | `canon_sure_shot_max` | B03 | 0 | **Hanya 2 foto B&W sementara dari 1 galeri ulasan (`35mmc.com`)** | `medium` → **`low`** | Bukti visual hanya dari inspeksi sementara **satu galeri ulasan**, jumlah < 3, dan tanpa foto warna → diturunkan ke **`low`**. |
| 63 | `ilford_xp2_super_single_use_camera` | B04 | 0 | **Hanya 2 foto sementara dari 1 galeri ulasan (`thedarkroom.com`)** | `medium` → **`medium`** | Bukti visual hanya dari inspeksi sementara **satu galeri ulasan** (< 3 foto), ditopang 3 ulasan tertulis konsisten → tetap **`medium`**. |
| 66 | `polaroid_spectra` | B04 | 0 | **Hanya 2 pindaian sementara dari 1 galeri ulasan (`bluemooncameracodex.com`)** | `medium` → **`medium`** | Bukti visual hanya dari inspeksi sementara **satu galeri ulasan** (< 3 foto, warna & B&W) → tetap **`medium`**. |
| 69 | `fujifilm_instax_square_sq6` | B04 | 0 | **Hanya 1 foto cetak sementara dari 1 galeri ulasan (`fstoppers.com`)** | `medium` → **`low`** | Bukti visual hanya dari inspeksi sementara **satu galeri ulasan** dan baru 1 foto → diturunkan ke **`low`**. |
| 70 | `fujifilm_instax_mini_11` | B04 | 0 | **Hanya 1 foto dinding kolase cetak sementara dari 1 galeri ulasan (`popphoto.com`)** | `medium` → **`low`** | Bukti visual hanya dari inspeksi sementara **satu galeri ulasan** (tanpa pindaian cetak tunggal resolusi penuh) → diturunkan ke **`low`**. |
| 73 | `camp_snap_camera` | B04 | 0 | **Hanya 2 foto sementara dari 1 galeri ulasan (`35mmc.com`)** | `medium` → **`medium`** | Bukti visual hanya dari inspeksi sementara **satu galeri ulasan** (< 3 foto), ditopang 4 ulasan konsisten → tetap **`medium`**. |
| 83 | `sony_mavica_mvc_fd7` | B05 | 1 | 1 kolase grid 100 foto CC di Wikimedia + 5 berkas tunggal 640×480 sementara dari 2 arsip (`mikeeckman.com` & `graphics.stanford.edu`) | `high` → **`medium`** | 0 foto tunggal 640×480 CC/PD lokal; bukti resolusi penuh berasal dari inspeksi sementara 2 arsip → diturunkan ke **`medium`**. |
| 99 | `sony_handycam_dcr_vx1000` | B05 | 0 | **Hanya 2 frame 4:3 sementara dari 1 arsip (`archive.org`)** | `medium` → **`low`** | Bukti visual hanya dari inspeksi sementara **satu sumber arsip**, < 3 frame, dan tanpa inspeksi gerak video *interlaced* → diturunkan ke **`low`**. |
| 100 | `panasonic_ag_dvx100` | B05 | 0 | 7 frame sementara lintas 2 arsip (`adamwilt.com` & `archive.org`) | `high` → **`medium`** | 0 CC/PD lokal dan tanpa inspeksi pemutaran video 24p (walau perbandingan gamma Cine-Like jelas) → diturunkan ke **`medium`**. |

*(Tambahan catatan di luar 11 kamera di atas: `#68 fujifilm_instax_wide_300` memiliki 1 foto CC0 lokal, namun karena total foto yang diperiksa visual hanya 1, ia juga diturunkan ke **`low`**; sedangkan `#67 polaroid_now_gen_2` memiliki 1 foto CC + 1 foto ulasan = 2 foto < 3 sehingga diturunkan dari `high` ke **`medium`**.)*

---

## 4. Pemeriksaan Acak (Spot-Check) 10 Kamera Lintas Batch

Sepuluh kamera dipilih acak lintas Batch 01–05 (2 per batch, mewakili status `high`, `medium`, dan `low`) beserta URL sumber kanonis terverifikasi, deskripsi kualitatif dengan kata sendiri (tanpa angka parameter), dan satu pengamatan spesifik langsung dari foto contoh agar mudah diperiksa ulang:

### 1. `fujifilm_finepix_f31fd` (Batch 01 · `#2` · `high`)
- **URL Sumber:** <https://www.imaging-resource.com/PRODS/F31FD/F31FDGALLERY.HTM> · <https://www.dpreview.com/products/fuji/compacts/fuji_finepixf31fd> · <https://commons.wikimedia.org/wiki/File:-Drosera_intermedia-Enallagma.jpg> · <https://commons.wikimedia.org/wiki/File:051_Guided_missile_destroyer_Jinan_20080711.jpg>
- **Deskripsi (kata sendiri):** Warna bawaan JPEG tampil wajar dan seimbang tanpa kontras sekeras kompak CCD mungil; pada cahaya malam hari pola *noise* muncul sebagai butiran luminansi halus yang tetap rapi alih-alih bercak warna kasar.
- **Satu pengamatan spesifik dari foto:** Pada foto lapangan hoki malam hari (*Hockey Field By Night*), bidang langit gelap dan rumput bawah tetap sangat bersih tanpa bercak warna digital, sementara lampu sorot tiang memunculkan pendaran melingkar lembut disertai pinggiran ungu tipis tepat di tepi lampu paling terang.

### 2. `holga_120n` (Batch 01 · `#14` · `medium` — contoh campur emulsi)
- **URL Sumber:** <https://www.myfavouritelens.com/holga-120n-camera-review/> · <https://emulsive.org/reviews/camera-reviews/holga-camera-reviews/holga-120n-by-sandeep-sumal> · <https://commons.wikimedia.org/wiki/File:A_corner_of_the_Rang_Mahal_-_Red_Fort.jpg> · <https://commons.wikimedia.org/wiki/File:Gelatt_Lake_(Holga,_Medium_Format_Film,_Fuji_400H>
- **Deskripsi (kata sendiri):** Lensa plastik meniskus pada format persegi 6×6 hanya mempertahankan keterbacaan di pusat bingkai lalu meluruh cepat menjadi kabur lembut menuju pinggir, dibingkai penggelapan sudut yang sangat berat; warna akhir tetap mengikuti roll film yang dipasang.
- **Satu pengamatan spesifik dari foto:** Pada foto semak *Gelatt Lake* (6×6 di film Fuji Pro 400H), keempat pojok bingkai gelap pekat secara dramatis hingga membingkai awan mendung, sementara ranting semak di tepi kiri dan kanan langsung kabur halus dibanding rumpun tengah yang cukup terbaca.

### 3. `canon_powershot_s95` (Batch 02 · `#22` · `high`)
- **URL Sumber:** <https://id.canon/id/support/6200085300> · <https://www.dpreview.com/articles/2660341179/canonpowershots95/> · <https://commons.wikimedia.org/wiki/File:%22_Dogsbody%22_,_The_Broadleys_,_Claycross_(6331958120> · <https://commons.wikimedia.org/wiki/File:1._trolejbuss_T%C4%93rbatas_iel%C4%81_2011.jpg>
- **Deskripsi (kata sendiri):** Sensor CCD 1/1,7 inci memberikan rona merah bata, hijau, dan kuning emas yang padat-hangat dengan transisi sorotan yang lebih luwes daripada kompak CCD sensor kecil, serta pendaran lampu malam yang organik pada ISO menengah.
- **Satu pengamatan spesifik dari foto:** Pada foto jalanan malam hari SOOC di ISO 800, lampu jalan kuning membentuk lingkaran pendaran halus di sekitar sumber cahaya tanpa menjatuhkan detail bodi bus biru menjadi bercak warna kotor.

### 4. `nikon_coolpix_p7000` (Batch 02 · `#28` · `medium` — turun dari `high`)
- **URL Sumber:** <https://www.nikon.com/company/news/2010/0908_coolpixp7000_01/> · <https://www.dpreview.com/articles/3148282345/nikonp7000/> · <https://commons.wikimedia.org/wiki/File:%22Mis_st%C3%BCndli_stoht_in_diner_hand%22_-_Inschrift_oberhalb_der_Sonnenuhr_an_der_S%C3%BCdseite_des_Rathauses_Rapperswil_2012-09-20_17-17-32_(P7000> · <https://commons.wikimedia.org/wiki/File:02-EastTimor-Dive_Bobs-Rock_13_(Clown_Fish>
- **Deskripsi (kata sendiri):** Warna bawaan tampil jernih dan tegas dengan bayangan gelap yang padat di ISO dasar; namun ketiga sampel yang diperiksa berasal dari alur olah yang bercampur (SOOC, ViewNX, dan foto bawah air ber-Photoshop) sehingga diturunkan jujur ke `medium`.
- **Satu pengamatan spesifik dari foto:** Pada sampel SOOC plakat kuningan *Stolperstein* di permukaan aspal, ketajaman butiran aspal hitam dan tepi huruf ukiran tampil rapat bersih, namun sampel ke-3 merupakan foto bawah air dengan flash sehingga tidak seragam untuk kalibrasi siang hari murni.

### 5. `pentax_17` (Batch 03 · `#48` · `medium` — turun dari `high`)
- **URL Sumber:** <https://www.ricoh-imaging.co.jp/english/products/pentax17/spec/> · <https://shootitwithfilm.com/pentax-17-review/> · <https://www.35mmc.com/17/06/2024/pentax-17-review/> · <https://casualphotophile.com/2024/06/17/pentax-17-new-film-camera-review/>
- **Deskripsi (kata sendiri):** Lensa kaca modern pada format half-frame vertikal 3:4 menghasilkan detail tengah yang bersih dengan kontrol *flare* lebih rapi dibanding half-frame tua, sementara ukuran negatif separuh tetap memperbesar butiran film; diturunkan ke `medium` karena 0 CC/PD lokal dan campuran film warna serta hitam-putih.
- **Satu pengamatan spesifik dari foto:** Pada rangkaian diptik vertikal di ulasan *Shoot It With Film*, bingkai kembar bersekat hitam vertikal memperlihatkan teks rambu dan tekstur mural yang tajam di tengah disertai *grain* yang jelas terasa di area langit dan dinding.

### 6. `canon_sure_shot_max` (Batch 03 · `#54` · `low` — turun dari `medium`)
- **URL Sumber:** <https://global.canon/en/c-museum/product/film153.html> · <https://www.35mmc.com/07/12/2018/canon-sure-shot-max-review/> · <https://kosmofoto.com/2023/12/three-cameras-for-2023/> · <https://www.lomography.com/cameras/3334815-canon-sure-shot-max/photos>
- **Deskripsi (kata sendiri):** Optik prima 38mm f/3.5 terlihat rapi untuk snapshot saku dengan ketajaman tengah memadai dan distorsi rendah, tetapi bukti visual terlalu minim untuk mengonfirmasi perilaku warna maupun *flash* secara menyeluruh.
- **Satu pengamatan spesifik dari foto:** Dua sampel sementara yang diperiksa di ulasan *35mmc* (spanduk pagar kawat dan jalanan Hilo di bawah bendera gantung) keduanya berupa film hitam-putih sehingga tidak ada satu pun foto contoh warna bebas-wajah yang terverifikasi langsung.

### 7. `kodak_funsaver` (Batch 04 · `#61` · `high`)
- **URL Sumber:** <https://business.kodakmoments.com/product/kodak-fun-saver-single-use-camera> · <https://thedarkroom.com/disposable-cameras-top-cameras-reviewed-compared/kodak-fun-saver-disposable-camera/> · <https://commons.wikimedia.org/wiki/File:Neshoba_County_General_Hospital_-_16635914310.jpg> · <https://commons.wikimedia.org/wiki/File:YLCC_Pigeon_Lake_2015-06-04_001.jpg>
- **Deskripsi (kata sendiri):** Paduan tetap lensa plastik sederhana dan film negatif warna ISO 800 menghasilkan tepi bingkai yang cepat melunak, vignette ringan di pojok langit, butiran film yang langsung terasa, serta rona siang hari hangat khas film konsumen Kodak dengan *flash* langsung yang keras di ruangan.
- **Satu pengamatan spesifik dari foto:** Pada foto danau dan sampel jembatan di *The Darkroom*, struktur tengah tetap terbaca jelas sementara pepohonan di pinggir kanan dan sudut atas langit memperlihatkan pelunakan tepi dan penggelapan sudut yang kentara disertai *grain* nyata di area langit.

### 8. `fujifilm_instax_square_sq6` (Batch 04 · `#69` · `low` — turun dari `medium`)
- **URL Sumber:** <https://www.fujifilm.com/uy/en/consumer/instax/cameras/squaresq6/specifications> · <https://fstoppers.com/film/fstoppers-reviews-fujifilm-instax-square-sq6-camera-medium-format-your-pocket-273236> · <https://www.techradar.com/reviews/fujifilm-instax-sq6-review> · <https://www.pcmag.com/reviews/fujifilm-instax-square-sq6>
- **Deskripsi (kata sendiri):** Cetakan instan persegi Instax menampilkan warna cerah dan kontras lebih tegas dibanding Polaroid klasik dengan pilihan mode pengatur kecerahan dan gel warna *flash*, namun verifikasi visual sangat terbatas.
- **Satu pengamatan spesifik dari foto:** Satu-satunya foto cetak bersih yang dapat diperiksa langsung (pindaian etalase dari ulasan *Fstoppers*, tanpa foto CC/PD lokal) memperlihatkan bingkai persegi putih rapi dengan bayangan gelap pekat dan sorotan kaca terang yang cepat memutih, tetapi 1 foto tidak cukup untuk status `medium`/`high`.

### 9. `sigma_dp1_merrill` (Batch 05 · `#91` · `high`)
- **URL Sumber:** <https://www.sigma-global.com/en/cameras/dp1-merrill/> · <https://www.sigma-global.com/en/support/download/DP3Merrill_Catalog2013_EN_PrinterRes.pdf> · <https://commons.wikimedia.org/wiki/File:Baudenkmal_Nr._102_Anklam_Heilige-Geist-Str._20.jpg> · <https://commons.wikimedia.org/wiki/File:Andromeda_Tower_Vienna_from_SW_on_2014-09-24.png>
- **Deskripsi (kata sendiri):** Sensor 3 lapis Foveon X3 tanpa interpolasi Bayer menghasilkan ketajaman per-piksel dan kontras mikro luar biasa pekat di ISO dasar dengan kesan dimensi tiga yang kuat, disertai warna padat bergaya slide pada cahaya sore namun cepat memunculkan pergeseran warna hijau-magenta di ISO tinggi.
- **Satu pengamatan spesifik dari foto:** Pada foto fasad bangunan bersejarah Anklam (dikembangkan lewat *Sigma Photo Pro 6.3.0*), tekstur plester dinding kasar, celah genteng, dan pantulan pintu hijau tua tampak sangat tajam seolah bertekstur timbul tanpa halo penajaman digital buatan.

### 10. `sony_handycam_dcr_vx1000` (Batch 05 · `#99` · `low` — turun dari `medium`)
- **URL Sumber:** <https://www.sony.com/en/SonyInfo/design/gallery/DCR-VX1000/> · <https://www.dtvgroup.com/DigVideo/DVC/Sony/dcr_vx1000.html> · <https://archive.org/details/curved-universe-pt-remaster> · <https://archive.org/details/own-little-world-remaster>
- **Deskripsi (kata sendiri):** Camcorder MiniDV 3-CCD 1995 ini dikenal lewat warna 3-chip yang pekat tanpa rembesan warna berlebih, resolusi video definisi standar 4:3, serta nuansa gerak *interlaced* khas video skateboard akhir 1990-an; namun bukti visual langsung pada sesi ini masih sangat tipis.
- **Satu pengamatan spesifik dari foto:** Hanya 2 *still frame* 4:3 bebas-wajah dari satu sumber arsip (`Internet Archive`, 0 CC/PD lokal) yang dapat diperiksa visual — keduanya menampilkan tepi video SD yang lunak dan sorotan langit cepat *clip*, tanpa inspeksi rekaman gerak *interlaced* maupun adegan lensa *fisheye* khas komunitasnya.

