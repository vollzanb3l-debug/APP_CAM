# ARCHITECTURE.md — Aplikasi Kamera Retro (Camera + Lens + Film)

Dokumen ini adalah sumber kebenaran proyek. Agent WAJIB membacanya di awal setiap sesi
dan tidak boleh menyimpang tanpa persetujuan pemilik proyek.

---

## 1. Tujuan

Aplikasi kamera Android untuk pemakaian pribadi. Pengguna memilih kombinasi
**Camera + Lens + Film**. Preview live langsung menampilkan efeknya, dan foto yang
disimpan memiliki karakter yang sama (boleh lebih bagus/resolusi penuh).

Prinsip utama:

1. **Satu mesin efek** dipakai oleh preview DAN foto. Tidak boleh ada dua implementasi efek.
2. **Data-driven**: kamera, lensa, dan film adalah file JSON, bukan kode. Menambah item baru
   tidak boleh mengubah kode mesin.
3. **Kombinasi, bukan daftar preset**: 12 kamera x 15 lensa x 120 film dibangun dari
   147 file, bukan 21.600 preset.
4. **Satu perubahan, satu uji, satu commit.**

## 2. Konsep inti

| Lapisan | Mewakili | Mengontrol (contoh) |
|---|---|---|
| Camera | badan kamera + sensor + pengolahan internal | rasio gambar, white balance, kurva tone, noise sensor, penajaman, date stamp |
| Lens | optik | kelembutan, bloom/glow, vignette, distorsi, chromatic aberration, flare |
| Film | emulsi/warna | color grade/LUT, grain, fade (hitam terangkat), halation |

Hasil akhir = penggabungan ketiganya dengan aturan di bagian 7.

## 3. Stack

- Bahasa: Kotlin
- UI: Jetpack Compose
- Kamera: CameraX (Preview + ImageCapture + exposure compensation)
- Pemrosesan gambar: OpenGL ES 3.0 (GLSL), satu shader pipeline
- Data: JSON di `assets/` (dibaca saat start, divalidasi)
- Penyimpanan foto: MediaStore (galeri), tulis metadata nama resep di EXIF UserComment
- Min SDK: tentukan saat M0 (usulan: 26)
- Dependensi baru di luar CameraX/Compose/kotlinx.serialization: DILARANG tanpa izin.

## 4. Arsitektur modul

```
UI (Compose)
  CameraScreen  PickerSheet  GalleryScreen  SettingsScreen
        |              |
        v              v
  CameraViewModel   RecipeRepository  (memuat & memvalidasi JSON)
        |              |
        |              v
        |        RecipeCompositor  (gabung Camera+Lens+Film -> FinalRecipe)   [Kotlin murni, bisa diuji unit]
        |              |
        v              v
  CameraController --> EffectEngine (GL)  <-- FinalRecipe jadi uniform shader
   (CameraX)            |        |
                        v        v
                  PreviewSurface  CaptureProcessor (offscreen, resolusi penuh)
                                        |
                                        v
                                  PhotoSaver (MediaStore)
```

Aturan batas modul:

- `RecipeCompositor` tidak boleh tahu soal GL, Android, atau UI.
- `EffectEngine` tidak boleh tahu soal JSON, nama kamera, atau UI. Ia hanya menerima `FinalRecipe`.
- UI tidak boleh memanggil GL langsung.
- `CameraController` tidak boleh menerapkan efek apa pun.

## 5. Pipeline efek (URUTAN TETAP)

Urutan ini tidak boleh diubah tanpa mengubah dokumen ini.

1. Geometri: crop rasio gambar (camera), distorsi lensa (lens)
2. Optik: softness, chromatic aberration, bloom/halation, flare, vignette (lens, film untuk halation)
3. Sensor/kamera: white balance, kurva tone, saturasi dasar, noise sensor, penajaman (camera)
4. Film: color grade / LUT, fade, grain (film)
5. Overlay: date stamp, frame/light leak (camera)

## 6. Aturan "preview = foto"

Penyebab utama hasil foto tidak mirip preview adalah parameter yang bergantung resolusi.
Maka:

- Semua parameter efek dinyatakan dalam **koordinat ternormalisasi** (0..1 terhadap lebar/tinggi
  gambar), BUKAN piksel. Contoh: radius blur = 0.004 x sisi pendek gambar.
- Ukuran grain relatif terhadap sisi pendek gambar. Seed grain tetap per foto.
- Preview berjalan di resolusi rendah (target tinggi sekitar 720–1080 piksel) untuk 30 fps.
- Foto memakai shader SAMA di FBO offscreen pada resolusi penuh. Bila memori kurang, proses
  per tile dengan overlap agar efek blur/bloom tidak bergaris di sambungan.
- Orientasi dan mirror (kamera depan) ditangani SATU kali di `CameraController`/transform
  matrix, bukan di shader efek.
- Efek berat (mis. noise reduction, bloom radius besar) boleh punya `quality` dua tingkat:
  `preview` dan `final`. Karakter harus sama, hanya halus/kasarnya yang beda.

## 7. Skema data dan aturan penggabungan

Semua file punya `schemaVersion`. Mengubah skema = naikkan versi + migrasi + perbarui dokumen ini.

### 7.1 Camera (`assets/cameras/<id>.json`)

```json
{
  "schemaVersion": 1,
  "id": "compact_2003_ccd",
  "displayName": "Kompak 2003",
  "inspiredBy": "(catatan pribadi, tidak ditampilkan di UI publik)",
  "era": "2000s",
  "category": "digital_compact",
  "icon": "icons/cameras/compact_generic.png",
  "aspectRatio": "4:3",
  "whiteBalance": { "temp": 0.08, "tint": 0.02 },
  "tone": { "contrast": 1.10, "highlightRolloff": 0.30, "shadowLift": 0.02 },
  "saturation": 1.05,
  "sensorNoise": { "amount": 0.18, "chroma": 0.35 },
  "sharpen": 0.35,
  "dateStamp": { "available": true, "defaultOn": false, "format": "yy MM dd", "color": "#FF8C1A" },
  "notes": "Hangat sedikit kekuningan, highlight keras, agak noise."
}
```

### 7.2 Lens (`assets/lenses/<id>.json`)

```json
{
  "schemaVersion": 1,
  "id": "lens_35_f2",
  "displayName": "35mm f2",
  "icon": "icons/lenses/prime_generic.png",
  "focalLengthEquiv": 35,
  "softness": 0.10,
  "bloom": { "intensity": 0.10, "threshold": 0.80, "radius": 0.006 },
  "vignette": { "strength": 0.20, "radius": 0.85 },
  "distortion": { "k1": 0.00 },
  "chromaticAberration": 0.05,
  "flare": 0.0,
  "notes": "Standar, sedikit lembut di tepi."
}
```

### 7.3 Film (`assets/films/<id>.json`)

```json
{
  "schemaVersion": 1,
  "id": "film_vivid_100",
  "displayName": "Vivid 100",
  "icon": "icons/films/canister_generic.png",
  "lut": null,
  "grade": {
    "temp": 0.00, "tint": 0.00,
    "saturation": 1.25, "contrast": 1.10,
    "blackLift": 0.01,
    "highlightTint": "#FFE9C8", "shadowTint": "#1B2A3A", "tintStrength": 0.08
  },
  "grain": { "amount": 0.10, "size": 0.0012, "roughness": 0.5 },
  "halation": { "intensity": 0.05, "color": "#FF6A3D" },
  "notes": "Warna kuat, grain halus."
}
```

### 7.4 Aturan penggabungan (`RecipeCompositor`)

| Parameter | Aturan |
|---|---|
| temp, tint, blackLift, shadowLift, bloom.intensity, vignette.strength, chromaticAberration | dijumlah, lalu di-clamp ke rentang valid |
| saturation, contrast | dikalikan |
| grain.amount (film) dan sensorNoise.amount (camera) | digabung sebagai akar jumlah kuadrat |
| aspectRatio, dateStamp | hanya dari Camera |
| softness, distortion, flare | hanya dari Lens |
| lut, halation, tint | hanya dari Film |

Compositor harus punya unit test yang mengunci aturan ini (input tetap -> output tetap).

### 7.5 Validasi

`RecipeRepository` memvalidasi setiap file (field wajib, rentang nilai). File rusak tidak boleh
membuat aplikasi crash: lewati, catat di log, tampilkan di layar debug.

## 8. Layar dan UI

Desain WAJIB orisinal (ikon, warna, tata letak buatan sendiri). Boleh terinspirasi konsep
"pilih kamera + lensa + film", tapi jangan menyalin aset, nama, atau tampilan aplikasi lain.

1. **CameraScreen**
   - Viewfinder live (efek aktif), grid on/off, skala exposure compensation (-2..+2),
     toggle date stamp, tombol rana, tombol galeri, tombol balik kamera depan/belakang.
   - Tiga slot di bawah: Camera | Lens | Film (ikon + nama pilihan saat ini).
   - Mengetuk slot membuka PickerSheet.
2. **PickerSheet** (bottom sheet)
   - Tiga baris carousel horizontal (Camera, Lens, Film), indikator "pilihan / total".
   - Memilih langsung memperbarui preview (tanpa menutup sheet).
   - Film: kelompokkan/filter per kategori bila jumlahnya banyak (pencarian + favorit).
3. **GalleryScreen**: foto hasil aplikasi, label resep (Camera — Lens — Film).
4. **SettingsScreen**: kualitas, format penyimpanan, layar debug (lihat bagian 11, M6).

Item terakhir yang dipilih disimpan dan dipulihkan saat aplikasi dibuka.

## 9. Struktur folder

```
app/src/main/
  java/.../
    ui/            (compose screens, theme)
    camera/        (CameraController)
    effects/       (EffectEngine, shaders loader, CaptureProcessor)
    recipe/        (models, RecipeRepository, RecipeCompositor, validator)
    storage/       (PhotoSaver)
  assets/
    cameras/ lenses/ films/ luts/
    shaders/       (*.glsl)
    icons/
  res/
app/src/test/      (unit test compositor, validator)
app/src/androidTest/ (golden-image test shader)
docs/ARCHITECTURE.md  CHANGELOG.md  TEST_CHECKLIST.md
```

## 10. Konten awal (MVP)

Mulai kecil, mesin harus stabil dulu.

- 5 Camera: kompak digital 2000-an, kompak digital CCD gaya dreamy/lembut, film sekali pakai,
  camcorder, kamera lensa mainan.
- 5 Lens: 28mm f2.8, 35mm f2, 50mm f1.4, lensa lembut (soft), lensa vintage berflare.
- 10 Film: campuran warna hangat, vivid, pudar, hitam putih, tungsten.
- Perluasan ke puluhan/ratusan dilakukan SETELAH M6 dan hanya dengan menambah file JSON.

## 11. Tahapan dan kriteria selesai

Kerjakan berurutan. Jangan mulai tahap berikutnya sebelum pemilik proyek menyetujui.

- **M0 – Kerangka**: proyek build, izin kamera, CameraX preview polos, navigasi layar kosong.
  Selesai bila: aplikasi jalan di perangkat, preview tampil, tidak crash saat rotasi/balik kamera.
- **M1 – Jalur GL tanpa efek**: preview lewat EffectEngine (shader passthrough), foto lewat
  CaptureProcessor (shader passthrough), tersimpan ke galeri.
  Selesai bila: foto sama persis dengan preview dari sisi framing, orientasi, mirror, kamera depan/belakang.
- **M2 – Satu efek**: color grade dari resep yang ditulis tangan di kode (sementara).
  Selesai bila: warna preview dan foto jelas cocok; golden-image test lulus.
- **M3 – Resep dari JSON**: RecipeRepository, RecipeCompositor (+unit test), 3 camera/3 lens/3 film,
  PickerSheet, simpan pilihan terakhir.
- **M4 – Efek lengkap**: grain, vignette, bloom/halation, chromatic aberration, distorsi, softness,
  crop rasio, date stamp. Satu efek per commit, masing-masing dengan uji.
- **M5 – Pelengkap**: exposure compensation, grid, galeri, label resep di EXIF, favorit.
- **M6 – Kalibrasi dan konten**: layar debug (slider parameter + simpan kembali ke JSON,
  tampilan berdampingan dengan foto referensi), optimasi performa/panas, lalu perluasan konten.

## 12. Aturan kerja untuk agent (WAJIB)

1. Baca dokumen ini sebelum mengerjakan apa pun. Kerjakan HANYA tahap yang diminta.
2. Sebelum mengubah kode, sebutkan file yang akan diubah dan dampaknya. Jangan menulis ulang
   file yang tidak berkaitan.
3. Satu perubahan = satu commit dengan pesan jelas. Jangan campur perbaikan bug dan fitur baru.
4. Jangan menyimpan nilai resep di dalam kode. Semua nilai estetika ada di JSON.
5. Jangan menambah dependensi tanpa izin. Jangan mengubah skema JSON tanpa menaikkan `schemaVersion`.
6. Jangan menduplikasi logika efek untuk preview dan foto.
7. Setiap bug yang diperbaiki harus disertai uji (unit/golden/atau item baru di TEST_CHECKLIST.md)
   agar tidak muncul lagi.
8. Setelah selesai satu tahap: jalankan build + uji, perbarui CHANGELOG.md, tampilkan
   TEST_CHECKLIST untuk pemilik proyek, lalu BERHENTI dan tunggu konfirmasi.
9. Jika ada hal yang ambigu atau berpotensi memutus aturan di atas, tanyakan, jangan menebak.

## 13. TEST_CHECKLIST (dijalankan manual tiap tahap selesai)

- Preview tampil di kamera belakang dan depan
- Kamera depan tidak terbalik pada preview maupun foto
- Rotasi layar (portrait/landscape) aman, framing foto sama dengan preview
- Foto tersimpan di galeri, dapat dibuka aplikasi lain
- Efek pada preview dan foto cocok secara karakter (bandingkan berdampingan)
- Ganti Camera/Lens/Film berkali-kali cepat: tidak crash, tidak berkedip aneh
- Aplikasi ke background lalu kembali: preview pulih
- 5 menit preview terus-menerus: tidak terlalu panas, fps wajar
- Foto resolusi penuh tidak OOM
- File JSON rusak sengaja: aplikasi tetap jalan, item dilewati

## 14. Catatan hak dan merek

- Aplikasi saat ini untuk pemakaian pribadi. Field `inspiredBy` boleh berisi nama kamera asli
  sebagai catatan pribadi.
- Jika suatu hari dipublikasikan: ganti nama tampilan menjadi generik, jangan pakai logo atau
  foto produk resmi, buat ikon sendiri, dan jangan menyebut merek di judul/deskripsi toko.
  Hanya `displayName` dan `icon` yang perlu diganti; mesin efek tidak tersentuh.
