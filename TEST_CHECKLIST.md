# TEST_CHECKLIST.md

Checklist pengujian manual yang dijalankan setiap kali satu tahap (`M0`–`M6`) selesai, sesuai `docs/ARCHITECTURE.md` bagian 12 dan 13.

## Daftar Uji Manual

- [ ] Preview tampil di kamera belakang dan depan *(uji di M0–M3)*
- [ ] Kamera depan tidak terbalik (mirror alami) pada preview maupun foto *(uji di M1–M3)*
- [ ] Orientasi preview vs foto cocok (tidak miring, tidak melar, area crop sama) di semua posisi:
  - [ ] Portrait (tegak, `ROTATION_0`) — kamera belakang & kamera depan
  - [ ] Landscape kiri (`ROTATION_90`) — kamera belakang & kamera depan
  - [ ] Landscape kanan (`ROTATION_270`) — kamera belakang & kamera depan
  - [ ] Terbalik (`ROTATION_180`, bila didukung perangkat) — kamera belakang & kamera depan
  - [ ] Transisi langsung landscape kiri ↔ landscape kanan tetap tegak dan tidak terbalik 180°
- [ ] Foto tersimpan di galeri, dapat dibuka aplikasi lain *(uji di M1–M3)*
- [ ] Efek pada preview dan foto cocok secara karakter (bandingkan berdampingan — mis. film *Monokrom 400* B&W, *Surya 200* hangat keemasan, dan *Linen 400* lembut pastel) *(uji di M2/M3)*
- [ ] Ganti Camera/Lens/Film berkali-kali cepat lewat PickerSheet: tidak crash, tidak berkedip aneh *(uji di M3)*
- [ ] Aplikasi ke background lalu kembali: preview pulih; pilihan terakhir Camera/Lens/Film tetap tersimpan *(uji di M3)*
- [ ] 5 menit preview terus-menerus: tidak terlalu panas, fps wajar *(uji di M1–M3)*
- [ ] Foto resolusi penuh tidak OOM *(uji di M1–M3)*
- [ ] File JSON rusak sengaja: aplikasi tetap jalan, item dilewati, dan error tampil di Layar Debug & Kalibrasi *(uji di M3 — tercover juga oleh unit test `RecipeRepositoryTest`)*
- [ ] Layar Debug & Kalibrasi: tampilkan metadata `archetype`, `confidenceLook`, `confidenceSpecs`, `calibrationStatus`, dan `sources` untuk kamera & film aktif; pilih foto referensi dari galeri, geser slider parameter (color grade, optik, grain, overlay), bandingkan berdampingan, lalu terapkan/reset ke preview *(uji di M3–M4)*
- [ ] Efek M4 cocok antara preview dan foto (bandingkan berdampingan):
  - [ ] **Vignette** & **Distorsi Lensa** (mis. lensa `28mm f2.8` dengan *barrel* & vignette lebih kuat dibanding `50mm f1.4`)
  - [ ] **Softness**, **Bloom & Halation** pada sumber cahaya terang (mis. lensa `50mm f1.4` + film `Surya 200` — foto resolusi penuh tampak lebih halus dari preview namun berkarakter sama)
  - [ ] **Chromatic Aberration** di tepi benda berkontras tinggi
  - [ ] **Grain** (mis. `Monokrom 400` ukuran butiran seragam antara preview dan foto; tidak melar/menghilang saat resolusi penuh)
  - [ ] **Crop Rasio Gambar**: ganti antara `Kompak CCD 2003` (`4:3`), `Saku Lembut 2008` (`3:4` vertikal), dan `Klasik 35mm` (`3:2`) — viewfinder & foto galeri memiliki rasio dan area crop yang sama
  - [ ] **Date Stamp Orisinal (`'YY.MM.DD`)**: tombol `DATE ON/OFF` langsung menghidupkan/mematikan stempel oranye di kanan bawah pada preview maupun foto hasil
  - [ ] **Light Leak Opsional**: uji slider *Light Leak Intensity* & *Seed* di Layar Debug & Kalibrasi lalu klik **Terapkan ke Preview**
- [ ] Performa & panas dengan semua efek M4 aktif (terutama `50mm f1.4` + `Surya 200` yang memakai softness + bloom/halation + grain): 5 menit preview tetap lancar dan tidak terlalu panas *(uji di M4)*
- [ ] **Exposure Compensation (`-2..+2 EV`)** *(uji di M5)*:
  - [ ] Slider EV tampil sesuai rentang perangkat (dibatasi maksimal `-2..+2 EV`); geser ke negatif/positif langsung menggelapkan/menerangkan preview
  - [ ] Ambil foto pada EV negatif (mis. `-1.5`) dan EV positif (mis. `+1.5`): kecerahan hasil foto cocok dengan preview
  - [ ] Ketuk label nilai EV di tengah bar untuk reset ke `0.0 EV`; balik kamera belakang ↔ depan menjaga rentang EV sesuai kamera aktif
- [ ] **Grid Viewfinder (Aturan Sepertiga)** *(uji di M5)*:
  - [ ] Tombol `GRID` menghidupkan/mematikan garis aturan sepertiga di viewfinder, dan garis grid tidak ikut tercetak pada hasil foto
  - [ ] Tutup lalu buka kembali aplikasi: pilihan `GRID` (ON/OFF) tetap tersimpan
- [ ] **Metadata EXIF `UserComment` & `GalleryScreen`** *(uji di M5)*:
  - [ ] Saat folder `Pictures/RetroCam` kosong: `GalleryScreen` menampilkan kondisi kosong dengan rapi tanpa crash
  - [ ] Ambil beberapa foto dengan kombinasi Camera/Lens/Film berbeda lalu buka `Galeri`: label `"Kamera - Lensa - Film"` tampil sesuai resep saat memotret
  - [ ] Ketuk foto untuk melihat ukuran penuh (label resep dan info versi EXIF tampil), uji tombol **Bagikan** ke aplikasi lain, lalu kembali ke daftar grid
  - [ ] Foto tanpa metadata EXIF RetroCam (mis. foto lama M1–M4 di `Pictures/RetroCam`): tetap tampil tanpa crash dengan label cadangan
  - [ ] Hapus satu foto dari luar aplikasi (galeri bawaan/file manager), lalu ketuk atau segarkan di `GalleryScreen`: aplikasi tidak crash, item yang hilang dibersihkan dari daftar, dan muncul pemberitahuan
  - [ ] Pengujian jumlah foto besar (> 24 foto): foto dimuat berkala saat digulir ke bawah / lewat tombol **Muat lebih banyak** tanpa lag berat atau OOM
- [ ] **Favorit (Lapisan Data & Pengurutan Picker)** *(uji di M5)*:
  - [ ] Buka `PickerSheet`: daftar Camera, Lens, dan Film tampil normal tanpa crash saat favorit masih kosong; pengurutan favorit di bagian awal daftar dan penyimpanan ID-only tervalidasi oleh unit test `FavoritesTest` (ikon bintang UI di picker masuk tahap tampilan berikutnya)
