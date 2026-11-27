# Analisis Mendalam Tampilan Foto — Batch 04 (`docs/catalog/looks/looks_batch_04.md`)

Pendalaman karakter visual kualitatif untuk **20 kamera Batch 04 (kamera #61–#80)** dari [batch_04.md](../batch_04.md) yang mencakup kamera film sekali pakai (*disposable*), kamera instan analog (Polaroid & Fujifilm Instax), kamera mainan/*lo-fi* analog & digital, serta ponsel kamera awal, mengacu pada 15 arketipe resmi di [archetypes.md](../archetypes.md).

> **Metodologi & Kepatuhan Aturan Keras:**
> - **Tanpa angka parameter shader:** Seluruh deskripsi murni kualitatif (warna, kontras, perilaku sorotan/bayangan, *grain*/*noise*, kelembutan tepi, *vignette*, *flare*, karakter *flash*, atau pola *dithering*). Angka yang tercantum semata-mata spesifikasi perangkat keras/film terverifikasi (panjang fokus, bukaan lensa, ISO film, ukuran cetak/sensor, kecepatan rana).
> - **Inspeksi visual langsung tanpa memasukkan foto ke repo/aplikasi:** Inspeksi visual dilakukan langsung terhadap **36 foto contoh berlisensi terbuka jelas (CC0, CC BY, CC BY-SA, Public Domain)** dari **Wikimedia Commons** dan **Flickr CC (via Openverse)** yang metadata lengkapnya dicatat di `docs/references/manifest_batch_04.json` (salinan rujukan lokal di `docs/references/batch_04_samples/` berada di luar repositori Git), ditambah inspeksi visual sementara atas **18 foto sampel dari ulasan fotografi independen** (The Darkroom, Shoot It With Film, Blue Moon Camera Codex, Fstoppers, Popular Photography, Austerity Photo, 35mmc, dan Myles Katherine) untuk melengkapi kamera sekali pakai, instan, dan *toy* yang arsip CC-nya terbatas (seluruh berkas sementara langsung dihapus seusai pengamatan; hanya URL halaman kanonik yang dicantumkan).
> - **Privasi:** Seluruh foto sampel telah diperiksa visual satu per satu; foto yang memuat wajah manusia yang dapat dikenali, anak-anak, maupun foto produk bodi kamera telah disingkirkan dan tidak disimpan dalam `batch_04_samples/` maupun `manifest_batch_04.json`.
> - **Pemisahan kamera film/instan vs digital:** Untuk kamera sekali pakai, instan analog, dan *toy* film (#61–#72), sifat optik bodi/lensa/format/flash dipisahkan secara tegas dari sifat emulsi film (termasuk catatan emulsi bawaan pabrik untuk *disposable* dan film spesifik untuk kamera isi ulang). Untuk kamera digital *lo-fi* dan ponsel kamera awal (#73–#80), dicatat apakah tampilan khas berasal dari setelan bawaan (*default* JPEG / palet tetap / filter bawaan) atau mode opsional.
> - **Penamaan kanonik:** Nama merek dan tampilan mengikuti `docs/catalog/batch_04.md` secara persis; alias/varian dicatat pada bidang `aliases`.

---

## 61. Kodak — Kodak FunSaver

- **id:** `kodak_funsaver`
- **brand:** `Kodak` *(Kodak Alaris)*
- **model:** `Kodak FunSaver` *(nama kanonik)*
- **aliases:** `Kodak Fun Saver 35`, `Kodak FunSaver Single Use Camera`, `FunSaver 800`
- **displayName:** `Kodak FunSaver`
- **Tahun & Jenis:** 1989 *(lini awal; varian ISO 800 masih dipasarkan)* · Disposable 35mm
- **Spesifikasi Inti (Resmi):** Film negatif warna terpasang permanen **Kodak ISO 800** (27 eksposur, rasio 3:2); lensa plastik fokus tetap sekitar 30–32mm f/10–f/11 (jarak fokus 1–1,2m hingga tak terhingga); rana tetap sekitar 1/100 detik; *flash* manual bawaan jangkauan 1,2–3,5m.
- **Pemisahan Sifat Kamera/Lensa vs Emulsi Film (WAJIB FILM — terikat permanen):**
  - **Sifat Bodi & Lensa (Optik Plastik 30–32mm f/10–f/11 + Rana Tetap + Flash):** Pada ketiga sampel CC luar ruang dan jembatan The Darkroom, ketajaman cukup terbaca di bagian tengah bingkai namun **cepat melunak ke arah pinggir dan keempat sudut** (tampak jelas pada batang pohon tepi kanan sampel danau, tepi bawah jalan, serta struktur baja tepi jembatan), disertai sedikit aberasi warna tipis pada tepi kontras tinggi dan **vignette gelap ringan-sedang di sudut atas** pada langit cerah. Bukaan kecil dan rana tetap membuat kamera sangat mengandalkan matahari terang atau **flash langsung manual**; di ruangan/senja, *flash* bawaan menghasilkan pencahayaan datar berkontras keras dengan subjek depan menyala tegas dan latar cepat jatuh gelap (dikonfirmasi The Darkroom, Casual Photophile, dan Shoot It With Film).
  - **Sifat Emulsi Film (bawaan tetap Kodak Max/Gold ISO 800):** Karena film terpasang dari pabrik, palet warna hangat nostalgia 90-an menjadi bagian menyatu dari kamera ini: dinding krem, rumput siang, dan baja kuning tampak **hangat keemasan serta cukup pekat**, langit biru sedikit bernuansa lembut dengan sedikit rona merah muda tipis saat mendung, bayangan dedaunan jatuh pekat, dan **butiran *grain* ISO 800 tampak nyata serta bertekstur mentah** di hamparan langit maupun permukaan air.
- **lookTags:** `warm-nostalgic`, `visible-grain`, `soft-edges`, `punchy-color`, `light-vignette`, `harsh-flash`
- **Arketipe:** `disposable_point_shoot` *(dikonfirmasi)*
- **confidenceLook:** **`high`** *(terkonfirmasi pada audit ketat)* — Memenuhi syarat ketat (>= 2 domain independen [7], >= 3 foto diperiksa [4], konsisten, tanpa campuran film/lensa).
- **Tidak ditemukan:** halaman Flickr Camera Finder khusus model ini tidak terverifikasi/tidak digunakan.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Kodak Moments / Kodak Alaris — Kodak FunSaver](https://business.kodakmoments.com/product/kodak-fun-saver-single-use-camera)
  - *Situs Fotografi / Lab Film (dengan inspeksi visual sampel):* [The Darkroom — Kodak FunSaver Review](https://thedarkroom.com/disposable-cameras-top-cameras-reviewed-compared/kodak-fun-saver-disposable-camera/), [Casual Photophile — Kodak FunSaver Review](https://casualphotophile.com/2018/10/08/kodak-funsaver-disposable-camera-review-cheap-is-good/), [Shoot It With Film — QuickSnap & FunSaver Comparison](https://shootitwithfilm.com/fujifilm-quicksnap-and-kodak-funsaver-disposable-cameras/), [Australian Photography — Kodak FunSaver Review](https://www.australianphotography.com/gear/review-kodak-funsaver)
  - *Foto Sampel Diperiksa Visual (4 lintas 4 sumber, seluruhnya memakai film bawaan Kodak ISO 800):*
    1. [Wikimedia Commons (CC BY 2.0) — *Neshoba County General Hospital* oleh CapCase (EXIF: Kodak Fun Saver, siang terik)](https://commons.wikimedia.org/wiki/File:Neshoba_County_General_Hospital_-_16635914310.jpg)
    2. [Wikimedia Commons (CC BY-SA 2.0) — *YLCC Pigeon Lake* oleh Dillan Payne (danau & pepohonan siang)](https://commons.wikimedia.org/wiki/File:YLCC_Pigeon_Lake_2015-06-04_001.jpg)
    3. [Flickr CC via Openverse (CC BY-SA 2.0) — *Blick auf die Leine* oleh Christoph Alexander Martsch (sungai & padang rumput)](https://www.flickr.com/photos/47717587@N07/4508379823)
    4. [The Darkroom — sampel jembatan baja kuning FunSaver (diperiksa visual sementara, tidak disimpan lokal)](https://thedarkroom.com/disposable-cameras-top-cameras-reviewed-compared/kodak-fun-saver-disposable-camera/)

---

## 62. Ilford — Ilford HP5 Plus Single Use Camera

- **id:** `ilford_hp5_plus_single_use_camera`
- **brand:** `Ilford` *(Harman Technology)*
- **model:** `Ilford HP5 Plus Single Use Camera` *(nama kanonik)*
- **aliases:** `Harman Ilford HP5 Plus Single Use`, `Ilford HP5+ 400 Disposable`
- **displayName:** `Ilford HP5 Plus Single Use Camera`
- **Tahun & Jenis:** 2012 · Disposable 35mm hitam-putih
- **Spesifikasi Inti:** Film negatif hitam-putih pankromatik terpasang permanen **Ilford HP5 Plus ISO 400** (27 eksposur, proses B&W standar); lensa meniskus plastik fokus tetap sekitar 30mm f/9.5 (fokus sekitar 1m–tak terhingga); rana tetap sekitar 1/100–1/120 detik; *flash* bawaan bertenaga baterai AA jangkauan hingga sekitar 3m. *(Halaman spesifikasi resmi produsen tidak ditemukan.)*
- **Pemisahan Sifat Kamera/Lensa vs Emulsi Film (WAJIB FILM — terikat permanen):**
  - **Sifat Bodi & Lensa (Optik Meniskus Plastik 30mm f/9.5 + Rana Tetap + Flash):** Ketiga sampel visual (gedung Art Deco *Tomorrowland*, belakang mobil klasik perak di terik matahari, dan wastafel berlilin redup di The Darkroom) memperlihatkan ketajaman tengah yang rapi untuk kamera sekali pakai, disertai **pelunakan sudut dan distorsi barel halus** di tepi bingkai serta **pendaran sorotan lembut (*glow*)** di sekitar nyala lilin dan pantulan krom terik. Karena ISO 400 dipadukan lensa f/9.5 dan rana tetap, ruang redup tanpa *flash* cepat jatuh gelap di area sudut dan bayangan; *flash* langsung membantu mengisi subjek dekat namun tetap menyisakan jatuh-gelap (*falloff*) pekat di belakangnya.
  - **Sifat Emulsi Film (bawaan tetap Ilford HP5 Plus ISO 400):** Menghadirkan **estetika hitam-putih dokumenter klasik yang organik** — gradasi *midtone* abu-abu pada aspal, plester dinding, dan badan mobil berlangsung mulus tanpa kontras digital kaku, sementara **butiran *grain* perak terasa nyata dan bertekstur hidup** pada langit mendung maupun bayangan ruang dalam.
- **lookTags:** `classic-bw`, `smooth-midtones`, `textured-grain`, `soft-edges`, `flash-dependent`
- **Arketipe:** `disposable_point_shoot` *(dikonfirmasi)*
- **confidenceLook:** **`high`** *(terkonfirmasi pada audit ketat)* — Memenuhi syarat ketat (>= 2 domain independen [4], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa).
- **Tidak ditemukan:** halaman spesifikasi resmi produsen aktif; halaman Flickr Camera Finder khusus model ini tidak terverifikasi/tidak digunakan.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Situs Fotografi / Lab Film (dengan inspeksi visual sampel):* [The Darkroom — Harman Ilford HP5 Plus 400 Single Use](https://thedarkroom.com/film/harman-ilford-hp5-plus-400-single-use-disposable-camera/), [Australian Photography — Ilford HP5 Plus Single Use Review](https://www.australianphotography.com/gear/review-harman-ilford-hp5-plus-single-use-camera), [Rediscover Analog — Ilford HP5 Plus Disposable Review](https://rediscoveranalog.com/camera-review-ilford-hp5-plus-disposable-camera/)
  - *Foto Sampel Diperiksa Visual (3 lintas 2 sumber, seluruhnya memakai film bawaan Ilford HP5 Plus 400):*
    1. [Flickr CC via Openverse (CC BY 2.0) — *Tomorrowland* oleh Phillip Pessar (eksterior bangunan siang)](https://www.flickr.com/photos/25955895@N03/9677377234)
    2. [The Darkroom — 2 foto sampel HP5 Plus Single Use (mobil klasik perak di terik matahari & wastafel berlilin interior redup — diperiksa visual sementara, tidak disimpan lokal)](https://thedarkroom.com/film/harman-ilford-hp5-plus-400-single-use-disposable-camera/)

---

## 63. Ilford — Ilford XP2 Super Single Use Camera

- **id:** `ilford_xp2_super_single_use_camera`
- **brand:** `Ilford` *(Harman Technology)*
- **model:** `Ilford XP2 Super Single Use Camera` *(nama kanonik)*
- **aliases:** `Ilford XP2 Super 400 Disposable`, `Ilford XP2 Single Use C-41`
- **displayName:** `Ilford XP2 Super Single Use Camera`
- **Tahun & Jenis:** Tahun rilis pasti varian sekali pakai tidak ditemukan · Disposable 35mm hitam-putih C-41
- **Spesifikasi Inti:** Film hitam-putih kromogenik terpasang permanen **Ilford XP2 Super ISO 400** (27 eksposur, diproses kimia warna C-41); lensa plastik fokus tetap 30mm f/9.5 (fokus 1m–tak terhingga); rana tetap 1/100 detik; *flash* bawaan jangkauan sekitar 3m. *(Halaman spesifikasi resmi produsen dan tahun rilis pasti varian sekali pakai tidak ditemukan.)*
- **Pemisahan Sifat Kamera/Lensa vs Emulsi Film (WAJIB FILM — terikat permanen):**
  - **Sifat Bodi & Lensa (Optik Plastik 30mm f/9.5 + Rana 1/100s + Flash):** Berbagi konstruksi optik sekali pakai yang serupa dengan HP5 Plus Single Use — bagian tengah bingkai terbaca cukup rapi pada sinar matahari penuh (dinding granit tebing dan rangka baja jembatan The Darkroom), namun **pinggir serta sudut bingkai melunak lembut dengan sedikit vignette tipis**. Karena bukaan kecil f/9.5 dan rana tetap 1/100 detik, pemotretan dalam cahaya minim tanpa *flash* segera mengalami kurang eksposur berat.
  - **Sifat Emulsi Film (bawaan tetap Ilford XP2 Super ISO 400 kromogenik C-41):** Perbedaannya dari HP5 Plus sangat kentara saat inspeksi visual langsung: awan langit dan permukaan granit terang pada sampel The Darkroom tampil **berbutiran jauh lebih halus, bersih, dan modern** dibanding film perak tradisional, dengan **rentang tonal panjang dan ketahanan sorotan yang sangat luwes** tanpa cepat terbakar rata. Sebaliknya, pada kondisi sangat kurang cahaya tanpa *flash* (dikonfirmasi sampel redup The Darkroom & ulasan Artizan Images), bayangan yang dipaksa terangkat cepat berubah menjadi **abu-abu keruh, berkontras rendah, dan berbutir kasar**.
- **lookTags:** `fine-grain-bw`, `smooth-tonality`, `clean-monochrome`, `wide-latitude`, `muddy-underexposure`
- **Arketipe:** `disposable_point_shoot` *(dikonfirmasi)*
- **confidenceLook:** **`medium`** *(terkonfirmasi pada audit ketat)* — 0 foto CC/PD lokal; bukti visual hanya 2 foto sementara (< 3) dari 1 galeri ulasan (The Darkroom), ditopang 3 ulasan konsisten.
- **Tidak ditemukan:** halaman spesifikasi resmi produsen aktif; tahun rilis persis varian saat ini di sumber resmi; halaman Flickr Camera Finder khusus model ini tidak terverifikasi/tidak digunakan; foto hasil bidikan berlisensi CC/PD di Wikimedia Commons/Openverse (bukti visual hanya dari inspeksi sementara galeri ulasan); foto contoh bebas-wajah ke-3 (baru 2 foto bersih diperiksa visual).
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Situs Fotografi / Lab Film (dengan inspeksi visual sampel):* [The Darkroom — Ilford XP2 Single Use Disposable Review](https://thedarkroom.com/disposable-cameras-top-cameras-reviewed-compared/ilford-xp2-single-use-disposable-camera/), [Artizan Images — Ilford XP2 Super Single Use Review](https://www.artizanimages.com/blog/2017/11/ilford-xp2-super-single-use-camera-review), [CameraDojo — Ilford XP2 Super Review](https://cameradojo.com/film/ilford-xp2-super/)
  - *Foto Sampel Diperiksa Visual (2 foto siang bersih + observasi kurang cahaya dari 1 sumber, seluruhnya memakai film bawaan Ilford XP2 Super 400; 0 sampel CC ditemukan):*
    1. [The Darkroom — sampel XP2 Single Use (tebing granit El Capitan Yosemite & rangka baja jembatan di langit terang — diperiksa visual sementara, tidak disimpan lokal)](https://thedarkroom.com/disposable-cameras-top-cameras-reviewed-compared/ilford-xp2-single-use-disposable-camera/)

---

## 64. Lomography — Lomography Simple Use Reloadable Film Camera

- **id:** `lomography_simple_use_reloadable_film_camera`
- **brand:** `Lomography`
- **model:** `Lomography Simple Use Reloadable Film Camera` *(nama kanonik)*
- **aliases:** `Lomography Simple Use`, `Simple Use Film Camera`, `Simple Use Color Negative 400`
- **displayName:** `Lomography Simple Use Reloadable Film Camera`
- **Tahun & Jenis:** 2017 · Kamera 35mm bergaya *disposable* yang dapat diisi ulang
- **Spesifikasi Inti (Resmi):** Film 35mm (terpasang awal varian Lomography Color Negative 400 / Lady Grey B&W / LomoChrome Purple / Metropolis, dapat diisi ulang dengan film 35mm standar); lensa plastik fokus tetap **31mm f/9** (fokus 1m–tak terhingga); rana mekanis tetap **1/120 detik**; *flash* bawaan bertenaga 1× AA dengan **3 filter gel warna lipat** (sian/biru, magenta, kuning).
- **Pemisahan Sifat Kamera/Lensa vs Emulsi Film (WAJIB FILM — dapat diisi ulang):**
  - **Sifat Bodi & Lensa (Optik Plastik 31mm f/9 + Rana 1/120s + Flash Gel Warna):** Pada ketiga sampel CC Flickr terlihat ciri optik lo-fi yang sangat konsisten: **bagian tengah terbaca cukup jelas namun sudut bingkai melunak tajam** (tampak nyata pada sudut papan plang parkir dan semak tepi kanan sampel 2), **distorsi barel melengkung kentara** pada garis lurus dekat bingkai (jelas pada garis horizontal bukaan kayu sampel 3), **vignette sudut ringan-sedang**, dan pinggiran aberasi kromatik halus di tepi kontras tinggi. Di dalam ruangan atau malam hari, *flash* bawaan bertenaga kecil memberi kontras keras dengan latar gelap, dan menurut ulasan PCMag, Digital Camera World, serta Tom's Guide, melipat **filter gel warna** di depan *flash* mewarnai subjek depan dengan semburat pesta sian, magenta, atau kuning secara analog.
  - **Sifat Emulsi Film (pada sampel yang diperiksa):**
    - Sampel 1 (*Elevated* — kolong rel Chicago, kurang cahaya kuat): bayangan gelap terangkat menjadi berbutir amat kasar bernuansa hijau-kelam dengan sorotan celah jalan yang hangat menyala akibat kombinasi kurang eksposur dan emulsi film warna.
    - Sampel 2 (*2019 Parking Lot* — **Lomography Color Negative 400**): warna merah mobil menyala pekat, langit biru segar, kontras tinggi dengan bayangan gedung jatuh hitam rapat, serta butiran *grain* tegas khas Lomo 400.
    - Sampel 3 (*First day of summer* — **Lomography 400**): laut biru-toska sejuk lembut dengan langit mendung cerah dan butiran merata.
- **lookTags:** `lofi-analog`, `soft-corners`, `barrel-distortion`, `gritty-grain`, `color-gel-flash`
- **Arketipe:** `disposable_point_shoot` *(dikonfirmasi)*
- **confidenceLook:** **`medium`** *(diaudit ketat dari `high` → `medium`)* — Contoh campur emulsi film: lensa tetap konsisten secara optik, namun foto contoh memakai jenis film berbeda atau tidak dicantumkan.
- **Tidak ditemukan:** halaman Flickr Camera Finder khusus model ini tidak terverifikasi/tidak digunakan.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Lomography — Simple and Reloadable: The Classic Picks](https://www.lomography.com/magazine/345086-simple-and-reloadable-the-classic-picks)
  - *Situs Fotografi / Ulasan:* [PCMag — Lomography Simple Use Color Negative 400 Review](https://www.pcmag.com/reviews/lomography-simple-use-film-camera-color-negative-400), [Digital Camera World — Lomography Simple Use LomoChrome Purple Review](https://www.digitalcameraworld.com/reviews/lomography-simple-use-reloadable-film-camera-lomochrome-purple-review), [Tom's Guide — Lomography Simple Use Classicolor Review](https://www.tomsguide.com/cameras-photography/lomography-simple-use-reloadable-film-camera-classicolor-review)
  - *Foto Sampel Diperiksa Visual (3 dari 3 pengunggah berbeda):*
    1. [Flickr CC via Openverse (CC BY-SA 2.0) — *Elevated* oleh Crawford Brian (siluet bawah rel kontras tinggi)](https://www.flickr.com/photos/78039748@N07/38219918926)
    2. [Flickr CC via Openverse (CC BY 2.0) — *2019 Parking Lot..* oleh Matthew Paul Argall (**film: Lomography Color Negative 400**, siang cerah)](https://www.flickr.com/photos/79157069@N03/46301836615)
    3. [Flickr CC via Openverse (CC BY 2.0) — *First day of summer* oleh analoguenative (**film: Lomography 400**, laut & bingkai kayu)](https://www.flickr.com/photos/187288315@N08/49601994546)

---

## 65. Polaroid — Polaroid Sun 660 Autofocus

- **id:** `polaroid_sun_660_autofocus`
- **brand:** `Polaroid`
- **model:** `Polaroid Sun 660 Autofocus` *(nama kanonik)*
- **aliases:** `Sun 660`, `Polaroid 660 AF`, `Polaroid Supercolor 670 AF` (varian internasional)
- **displayName:** `Polaroid Sun 660 Autofocus`
- **Tahun & Jenis:** 1981 · Kamera instan integral (seri 600)
- **Spesifikasi Inti:** Film instan integral **Polaroid 600** (ISO sekitar 640, bidang gambar persegi 79×79mm dalam bingkai putih, baterai terintegrasi dalam pak film); lensa meniskus plastik **116mm f/11** dengan sistem **autofokus akustik Sonar**; rana elektronik 1/4–1/200 detik; pengatur kompensasi terang/gelap; *flash* elektronik bawaan. *(Halaman spesifikasi resmi produsen asli 1981 tidak ditemukan.)*
- **Pemisahan Sifat Kamera/Lensa vs Emulsi Film Instan (WAJIB FILM):**
  - **Sifat Bodi & Lensa (116mm f/11 + Sonar AF + Flash):** Berkat sistem autofokus ultrasonik Sonar, subjek utama pada seluruh sampel yang diperiksa (menara SPBU Art Deco, jeruji jendela & mural dinding, serta batang pohon tepi jalan) tampil **jauh lebih terfokus tajam dan konsisten di area tengah** dibanding kamera kotak 600 fokus tetap, lalu perlahan melunak dengan **vignette tipis yang halus di keempat sudut**. Lensa plastik tunggal tanpa pelapisan canggih juga memunculkan **pendaran/flare keemasan hangat** saat berhadapan dengan cahaya matahari miring (terlihat pada sampel jalan berpohon Shoot It With Film). *Flash* bawaan menghasilkan sorotan langsung yang kuat dan tegas di dalam ruang.
  - **Sifat Emulsi Instan Integral Polaroid 600 (pada sampel yang diperiksa):**
    - Sampel 1 (*Art Deco Gas Station* — Polaroid 600): rona **kuning-amber hangat nostalgia**, awan bernuansa merah muda-pastel lembut, kontras analog yang empuk tidak sekeras digital, dengan detail rambu tetap terbaca bersih.
    - Sampel 2–3 (mural dinding & jalan berpohon Shoot It With Film — Polaroid 600 modern): langit krem-merah muda lembut, bayangan jalan mengarah hangat kemerahan/violet kalem, dan tampak sesekali **jejak kimia biru tipis (*opacification mark*)** di tepi bawah cetak khas film Polaroid modern.
- **lookTags:** `instant-vintage`, `sonar-sharp`, `pastel-shift`, `soft-vignette`, `direct-flash`, `square-instant`
- **Arketipe:** `instant_integral_vintage` *(dikonfirmasi)*
- **confidenceLook:** **`high`** *(terkonfirmasi pada audit ketat)* — Memenuhi syarat ketat (>= 2 domain independen [3], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa).
- **Tidak ditemukan:** halaman spesifikasi resmi produsen aktif; halaman Flickr Camera Finder khusus model ini tidak terverifikasi/tidak digunakan.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Situs Fotografi / Ulasan (dengan inspeksi visual sampel):* [Shoot It With Film — My Love for the Vintage Polaroid Sun 660](https://shootitwithfilm.com/my-love-for-the-vintage-polaroid-sun-660/), [Casual Photophile — Polaroid Sun 660 Review](https://casualphotophile.com/2018/08/22/polaroid-sun-660-review/)
  - *Foto Sampel Diperiksa Visual (3 lintas 2 sumber, seluruhnya cetak Polaroid 600):*
    1. [Flickr CC via Openverse (CC BY 2.0) — *Art Deco Gas Station* oleh Phillip Pessar (film Polaroid 600, siang)](https://www.flickr.com/photos/25955895@N03/6942679999)
    2. [Shoot It With Film — 2 pindaian cetak Polaroid Sun 660 (dinding mural bertanda jendela & jalan berpohon dengan flare hangat — diperiksa visual sementara, tidak disimpan lokal)](https://shootitwithfilm.com/my-love-for-the-vintage-polaroid-sun-660/)

---

## 66. Polaroid — Polaroid Spectra (Image System)

- **id:** `polaroid_spectra`
- **brand:** `Polaroid`
- **model:** `Polaroid Spectra` *(nama kanonik)*
- **aliases:** `Polaroid Image System` (Eropa), `Spectra System`, `Polaroid Spectra SE`
- **displayName:** `Polaroid Spectra (Image System)`
- **Tahun & Jenis:** 1986 · Kamera instan integral format lebar
- **Spesifikasi Inti:** Film instan **Polaroid Spectra / Image** (area gambar persegi panjang lebar **92×73mm** dalam bingkai putih; produksi film resmi dihentikan 2019); lensa **Quintic 3 elemen plastik 125mm f/10** (ekuiv. sekitar 46mm); autofokus Sonar dengan pengunci tak terhingga; rana elektronik sekitar 2,8–1/245 detik; *flash* otomatis yang dapat dimatikan, kompensasi terang/gelap, *self-timer*. *(Halaman spesifikasi resmi produsen asli 1986 tidak ditemukan.)*
- **Pemisahan Sifat Kamera/Lensa vs Emulsi Film Instan (WAJIB FILM):**
  - **Sifat Bodi, Format Lebar & Lensa (Quintic 3 Elemen 125mm f/10 + Sonar AF):** Pada kedua pindaian cetak bersih yang diperiksa (sepeda motor Triumph + pengendara berhelm penuh, serta papan nama *Sabatino Moped Scooter Vintage* dari Blue Moon Camera Codex), perbedaan kualitas optiknya langsung terlihat: **proporsi persegi panjang yang lebih lega** menghadirkan bingkai sinematik dibanding format persegi 600, **ketajaman tengah dari lensa Quintic 3 elemen dan Sonar AF terasa lebih bersih dan rapi** pada kisi mesin motor maupun tipografi papan nama, disertai **vignette lembut yang nyata di keempat sudut** serta pemisahan latar yang halus.
  - **Sifat Emulsi Instan Integral Polaroid Spectra (pada sampel yang diperiksa):**
    - Sampel 1 (sepeda motor — emulsi warna Polaroid Originals/Impossible Spectra): palet **pastel sendu sedikit sejuk-kehijauan di bayangan dan sorotan langit krem lembut**, kontras analog moderat, dengan sorotan pantulan krom dan helm sedikit berpendar lunak.
    - Sampel 2 (papan nama *Sabatino* — emulsi hitam-putih Polaroid Spectra B&W): gradasi monokrom lembut bernuansa sedikit hangat, bayangan dedaunan pekat seperti beludru, dan sorotan papan putih menyala lembut tanpa batas kasar.
- **lookTags:** `wide-instant`, `crisp-quintic`, `moody-pastel`, `soft-vignette`, `cinematic-frame`
- **Arketipe:** `instant_integral_vintage` *(dikonfirmasi)*
- **confidenceLook:** **`medium`** *(terkonfirmasi pada audit ketat)* — 0 foto CC/PD lokal; bukti visual hanya 2 pindaian sementara (< 3, warna & B&W) dari 1 galeri ulasan (Blue Moon Camera Codex).
- **Tidak ditemukan:** halaman spesifikasi resmi produsen aktif; halaman Flickr Camera Finder khusus model ini tidak terverifikasi/tidak digunakan; foto hasil bidikan berlisensi CC/PD di Wikimedia Commons/Openverse (bukti visual hanya dari inspeksi sementara galeri ulasan); foto contoh bebas-wajah ke-3 (baru 2 foto bersih diperiksa visual).
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Arsip / Situs Fotografi (dengan inspeksi visual sampel):* [Blue Moon Camera Codex — Film Fridays: Polaroid Spectra](https://bluemooncameracodex.com/film-fridays/ffpospectra), [PhotoThinking — Polaroid Spectra Almost Instant](https://photothinking.com/2018-05-11-polaroid-spectra-almost-instant/), [InstantPhoto.eu — Polaroid Spectra Archive](https://www.instantphoto.eu/pola/pola_spectra.htm)
  - *Komunitas:* [Lomography Magazine — Polaroid Spectra / Image System](https://www.lomography.com/magazine/8159-polaroid-spectra-image-system)
  - *Foto Sampel Diperiksa Visual (2 pindaian cetak Spectra warna & B&W dari 1 sumber; 0 sampel CC ditemukan):*
    1. [Blue Moon Camera Codex — 2 pindaian cetak Polaroid Spectra (sepeda motor warna & papan nama *Sabatino* hitam-putih — diperiksa visual sementara, tidak disimpan lokal)](https://bluemooncameracodex.com/film-fridays/ffpospectra)

---

## 67. Polaroid — Polaroid Now Gen 2

- **id:** `polaroid_now_gen_2`
- **brand:** `Polaroid`
- **model:** `Polaroid Now Gen 2` *(nama kanonik)*
- **aliases:** `Polaroid Now Generation 2`, `Polaroid Now (2nd Gen)` *(berbagi optik autofokus dua lensa yang sama dengan Polaroid Now Gen 1)*
- **displayName:** `Polaroid Now Gen 2`
- **Tahun & Jenis:** 2023 · Kamera instan integral modern
- **Spesifikasi Inti (Resmi):** Kompatibel film **Polaroid i-Type dan 600** (area gambar persegi 79×79mm); sistem **autofokus dua zona dengan dua lensa plastik otomatis** (jarak dekat 0,55–1,3m dan standar 0,6m–tak terhingga); *flash* strobo bawaan, mode eksposur ganda dan *self-timer*; bodi 40% plastik daur ulang dengan baterai isi ulang USB-C.
- **Pemisahan Sifat Kamera/Lensa vs Emulsi Film Instan (WAJIB FILM):**
  - **Sifat Bodi & Lensa (Dua Lensa Otomatis + Flash Strobo):** Pada pindaian cetak gurun Flickr CC (Polaroid Now 2023) dan pengamatan ulasan Polaroid Now Gen 2 (Digital Camera World, Chris Kendrick, Myles Katherine), ketajaman cukup rapi di bidang tengah subjek namun **tepi dan keempat sudut tetap terasa lembut berkabut dengan sedikit vignette halus**. Di dalam ruangan, **flash strobo bawaan terasa kuat dan langsung**, mudah menimbulkan titik pantulan terang menyolok pada permukaan mengilap/dinding dekat sembari menjatuhkan latar belakang ke gelap pekat.
  - **Sifat Emulsi Instan Integral Polaroid i-Type Modern:** Sangat mudah dikenali pada pindaian gurun (*Desert Southwest*) maupun ulasan Gen 2: langit dan area terang sering membawa **pergeseran rona merah muda-lavender atau hangat teredam (*muted warm*)**, kontras vintage yang empuk dengan bayangan berbatu jatuh gelap kecokelatan/violet, aksen warna pekat (seperti tenda merah) tetap menonjol lembut, serta karakter warna sangat peka terhadap suhu lingkungan saat berkembang (sesekali menyisakan nyala kimia biru kecil di tepi bawah bingkai).
- **lookTags:** `dreamy-instant`, `muted-warm`, `pastel-magenta-cast`, `soft-edges`, `strong-flash`
- **Arketipe:** `instant_integral_vintage` *(dikonfirmasi)*
- **confidenceLook:** **`medium`** *(diaudit ketat dari `high` → `medium`)* — Foto contoh diperiksa visual < 3 (hanya 2: 1 Flickr CC + 1 sampel ulasan Myles Katherine).
- **Tidak ditemukan:** halaman Flickr Camera Finder khusus model ini tidak terverifikasi/tidak digunakan; foto contoh bebas-wajah ke-3 (baru 2 foto bersih diperiksa visual).
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Polaroid Official — Polaroid Now Generation 2](https://www.polaroid.com/en_nl/products/polaroid-now-generation-2)
  - *Situs Fotografi / Ulasan (dengan inspeksi visual sampel):* [Digital Camera World — Polaroid Now Generation 2 Review](https://www.digitalcameraworld.com/reviews/polaroid-now-generation-2-review), [Chris Kendrick Photography — Polaroid Now Gen 2 Review](https://chriskendrickphoto.co.uk/blog/gear-review-polaroid-now-gen-2-camera), [Myles Katherine — Polaroid Now Generation 2 Review & Sample Photos](https://www.myleskatherine.com/blog/2026/3/1/polaroid-now-generation-2-review-and-how-to-with-sample-photos-the-best-polaroid-camera-for-beginners)
  - *Foto Sampel Diperiksa Visual (lintas 2 sumber, emulsi Polaroid i-Type):*
    1. [Flickr CC via Openverse (CC BY 2.0) — *Polaroid Week 2023: Desert Southwest* oleh kevin dooley (pindaian Polaroid Now i-Type, luar ruang)](https://www.flickr.com/photos/12836528@N00/52848397955)
    2. [Myles Katherine — sampel cetak dalam ruang *flash* Polaroid Now Gen 2 (diperiksa visual sementara untuk perilaku flash & rona i-Type, tidak disimpan lokal)](https://www.myleskatherine.com/blog/2026/3/1/polaroid-now-generation-2-review-and-how-to-with-sample-photos-the-best-polaroid-camera-for-beginners)

---

## 68. Fujifilm — Fujifilm Instax Wide 300

- **id:** `fujifilm_instax_wide_300`
- **brand:** `Fujifilm`
- **model:** `Instax Wide 300` *(nama kanonik)*
- **aliases:** `Fujifilm Instax Wide 300`, `Fuji Instax Wide 300`
- **displayName:** `Fujifilm Instax Wide 300`
- **Tahun & Jenis:** 2014 · Kamera instan modern format lebar
- **Spesifikasi Inti (Resmi):** Film instan **Fujifilm Instax Wide** (ukuran bidang gambar lanskap lebar **62×99mm**, ISO 800); lensa tarik 2 elemen **95mm f/14** dengan fokus bermotor 2 zona (0,9–3m dan 3m–tak terhingga) + adaptor *close-up* 0,4m; rana elektronik terprogram 1/64–1/200 detik; kompensasi eksposur ±2/3 EV (Lighten/Darken); *flash* otomatis/*fill-in* jangkauan 0,9–3m.
- **Pemisahan Sifat Kamera/Lensa vs Emulsi Film Instan (WAJIB FILM):**
  - **Sifat Bodi, Format Lebar & Lensa (95mm f/14 2 Zona + Flash):** Pada pindaian cetak asli CC0 (*Donald Cousens Parkway and Highway 7*) tampak jelas betapa **bingkai lanskap lebar 62×99mm memberi napas komposisi yang lapang dan sinematik**. Ketajaman di tengah bingkai (tiang lampu lalu lintas dan blok bangunan utama) cukup terbaca bersih bila zona fokus dipilih tepat, lalu melunak lembut menuju kedua ujung kiri-kanan disertai **vignette halus di keempat sudut**. Menurut PCMag, Digital Camera World, dan Dan Finnen, salah memilih zona fokus dua tahap cepat membuat seluruh foto melunak, sementara tombol *fill-in flash* membantu mengangkat subjek latar terang namun *flash* tidak dapat dimatikan sepenuhnya saat kamera menilai cahaya kurang.
  - **Sifat Emulsi Instan Fujifilm Instax Wide (bawaan tetap):** Berbeda dari Polaroid integral yang cenderung pastel-hangat pudar, emulsi Instax Wide menampilkan **warna yang lebih jernih, segar, sedikit sejuk-biru pada hari mendung**, serta **kontras lebih tegas dengan rentang dinamis sempit** — terlihat langsung pada pindaian jalan bersalju CC0: gundukan salju dan langit mendung tampak cerah bersih, sedangkan fasad rumah dan aspal bayangan langsung jatuh gelap pekat (sejalan dengan temuan PCMag dan Digital Camera World soal sorotan yang cepat terbakar di terik matahari dan bayangan cepat mengeras).
- **lookTags:** `wide-instax`, `crisp-contrast`, `cool-bright`, `narrow-dr`, `soft-edges`, `organic-print`
- **Arketipe:** `instant_modern_instax` *(dikonfirmasi)*
- **confidenceLook:** **`low`** *(diaudit ketat dari `medium` → `low`)* — Bukti visual sangat terbatas: hanya 1 pindaian cetak fisik (CC0 Wikimedia) yang diperiksa langsung.
- **Tidak ditemukan:** halaman Flickr Camera Finder khusus model ini tidak terverifikasi/tidak digunakan; foto contoh bebas-wajah ke-3 (baru 1 foto bersih diperiksa visual).
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Fujifilm Official — Instax Wide 300 Specifications](https://www.fujifilm.com/us/en/consumer/instax/cameras/wide300/specifications)
  - *Situs Fotografi / Ulasan:* [PCMag — Fujifilm Instax Wide 300 Review](https://www.pcmag.com/reviews/fujifilm-instax-wide-300), [Digital Camera World — Fujifilm Instax Wide 300 Review](https://www.digitalcameraworld.com/reviews/fujifilm-instax-wide-300), [Dan Finnen — Fuji Instax Wide 300 Review](https://danfinnen.com/2015/03/28/new-review-alert-fuji-instax-wide-300-instant-camera/)
  - *Foto Sampel Diperiksa Visual (1 pindaian cetak CC0, film Instax Wide):*
    1. [Wikimedia Commons (CC0) — *Donald Cousens Parkway and Highway 7* oleh ChadPerez49 (pindaian Instax Wide 300, mendung bersalju)](https://commons.wikimedia.org/wiki/File:DonaldCousensandHighway701282024.png)

---

## 69. Fujifilm — Fujifilm Instax Square SQ6

- **id:** `fujifilm_instax_square_sq6`
- **brand:** `Fujifilm`
- **model:** `Instax Square SQ6` *(nama kanonik)*
- **aliases:** `Fujifilm Instax Square SQ6`, `Instax SQ6`
- **displayName:** `Fujifilm Instax Square SQ6`
- **Tahun & Jenis:** 2018 · Kamera instan modern format persegi
- **Spesifikasi Inti (Resmi):** Film instan **Fujifilm Instax Square** (bidang gambar persegi **62×62mm**, ISO 800); lensa tarik 2 elemen **65,75mm f/12.6** dengan **3 zona fokus** (Makro 0,3–0,5m, Normal 0,5–2m, Lanskap 2m–tak terhingga); rana elektronik 1,6–1/400 detik (*slow synchro*); mode Selfie, Eksposur Ganda, Terang/Gelap (L/D), opsi matikan *flash*, plus **3 filter warna flash** (oranye, ungu, hijau).
- **Pemisahan Sifat Kamera/Lensa vs Emulsi Film Instan (WAJIB FILM):**
  - **Sifat Bodi, Format Persegi & Lensa (65,75mm f/12.6 3 Zona + Flash Kreatif):** Pada pindaian cetak etalase roti & gandum kering dari Fstoppers, format persegi 62×62mm memberi aura retro klasik Polaroid tetapi dengan ketajaman tengah yang lebih terjaga saat zona fokus sesuai (tangkai gandum dan serat peti kayu tampak jelas), sedikit pendaran/flare lembut pada pantulan kaca etalase di sudut kanan atas, serta pelunakan halus ke tepi bingkai. Menurut Fstoppers, TechRadar, dan PCMag, kemampuan mematikan *flash*, memakai eksposur ganda, dan memasang **filter gel warna di depan flash** memperluas efek kreatif analog secara langsung.
  - **Sifat Emulsi Instan Fujifilm Instax Square (bawaan tetap):** Menyajikan karakter warna khas Instax yang **lebih tegas, sedikit hangat keemasan pada pencahayaan alami, dan berkontras lebih pekat** dibanding film Polaroid modern — pada sampel etalase roti terlihat bagian bayangan interior jatuh hitam dalam sementara permukaan terkena matahari langsung (meja tepi bawah & pantulan jendela) segera penuh memutih bersih (*crisp highlight clipping*).
- **lookTags:** `square-instax`, `punchy-warm`, `deep-shadows`, `clipped-highlights`, `creative-flash`
- **Arketipe:** `instant_modern_instax` *(dikonfirmasi)*
- **confidenceLook:** **`low`** *(diaudit ketat dari `medium` → `low`)* — 0 foto CC/PD; bukti visual sangat terbatas: hanya 1 foto cetak sementara dari 1 galeri ulasan (Fstoppers).
- **Tidak ditemukan:** halaman Flickr Camera Finder khusus model ini tidak terverifikasi/tidak digunakan; foto hasil bidikan berlisensi CC/PD di Wikimedia Commons/Openverse (bukti visual hanya dari inspeksi sementara galeri ulasan); foto contoh bebas-wajah ke-3 (baru 1 foto bersih diperiksa visual).
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Fujifilm Official — Instax Square SQ6 Specifications](https://www.fujifilm.com/uy/en/consumer/instax/cameras/squaresq6/specifications)
  - *Situs Fotografi / Ulasan (dengan inspeksi visual sampel):* [Fstoppers — Fujifilm Instax Square SQ6 Review](https://fstoppers.com/film/fstoppers-reviews-fujifilm-instax-square-sq6-camera-medium-format-your-pocket-273236), [TechRadar — Fujifilm Instax SQ6 Review](https://www.techradar.com/reviews/fujifilm-instax-sq6-review), [PCMag — Fujifilm Instax Square SQ6 Review](https://www.pcmag.com/reviews/fujifilm-instax-square-sq6), [DPReview — Fujifilm Instax Square SQ6 Announcement](https://www.dpreview.com/news/0067965594/fujifilm-s-instax-square-sq6-is-a-square-format-analog-instant-camera/)
  - *Foto Sampel Diperiksa Visual (1 pindaian cetak non-wajah dari 1 sumber; 0 sampel CC ditemukan):*
    1. [Fstoppers — pindaian cetak Instax Square SQ6 (peti roti & gandum etalase toko — diperiksa visual sementara, tidak disimpan lokal)](https://fstoppers.com/film/fstoppers-reviews-fujifilm-instax-square-sq6-camera-medium-format-your-pocket-273236)

---

## 70. Fujifilm — Fujifilm Instax Mini 11

- **id:** `fujifilm_instax_mini_11`
- **brand:** `Fujifilm`
- **model:** `Instax Mini 11` *(nama kanonik)*
- **aliases:** `Fujifilm Instax Mini 11`, `Cheki instax mini 11` (Jepang)
- **displayName:** `Fujifilm Instax Mini 11`
- **Tahun & Jenis:** 2020 · Kamera instan modern format mini
- **Spesifikasi Inti (Resmi):** Film instan **Fujifilm Instax Mini** (bidang gambar vertikal **62×46mm**, ISO 800); lensa 2 elemen **60mm f/12.7** dengan barel tarik untuk mode *Selfie/Close-up* (0,3–0,5m); rana elektronik terprogram **1/2–1/250 detik dengan *slow synchro***; eksposur otomatis penuh (LV 5,0–14,5); *flash* menyala konstan otomatis jangkauan 0,3–2,7m.
- **Pemisahan Sifat Kamera/Lensa vs Emulsi Film Instan (WAJIB FILM):**
  - **Sifat Bodi & Lensa (60mm f/12.7 + Eksposur Otomatis Penuh + Flash Konstan):** Pengamatan langsung atas dinding kumpulan cetakan uji Popular Photography (sekitar 15 cetakan non-wajah meliputi dermaga kayu, pantai, langit berawan, rumah putih, tanaman hias pot, bunga putih, pohon salju, dan anjing hitam di beranda) langsung membuktikan keunggulan utama Mini 11 yang dipuji DPReview dan Camera Labs: **eksposur otomatis penuh jauh lebih konsisten** dibanding seri Mini lama sehingga cetakan luar ruang maupun interior jarang jatuh gelap total atau terbakar habis. Pada langit biru lebar tampak **vignette sudut gelap lembut yang khas**, ketajaman cukup rapi pada mode dekat (*close-up* tanaman dan bunga), sementara **flash yang selalu menyala** di ruang redup menerangi objek dekat secara rata sembari membuat latar belakang jauh jatuh gelap pekat.
  - **Sifat Emulsi Instan Fujifilm Instax Mini (bawaan tetap):** Menyajikan **biru langit kobalt yang cerah pekat**, hijau dedaunan dalam dan jenuh, nada hangat-bersih pada kayu dan bunga, serta **kontras retro tegas** di mana bidang putih cerah (fasad rumah di terik matahari dan salju) mudah menyentuh batas putih penuh (*clean highlight clipping*) dan bayangan gelap (siluet pohon melawan langit, bulu anjing hitam) cepat jatuh pekat.
- **lookTags:** `balanced-instax`, `vivid-sky`, `soft-vignette`, `constant-flash`, `dark-background`
- **Arketipe:** `instant_modern_instax` *(dikonfirmasi)*
- **confidenceLook:** **`low`** *(diaudit ketat dari `medium` → `low`)* — 0 foto CC/PD; bukti visual sangat terbatas: hanya 1 foto dinding kolase cetak dari 1 galeri ulasan (Popular Photography).
- **Tidak ditemukan:** halaman Flickr Camera Finder khusus model ini tidak terverifikasi/tidak digunakan; foto hasil bidikan berlisensi CC/PD di Wikimedia Commons/Openverse (bukti visual hanya dari inspeksi sementara galeri ulasan); foto contoh bebas-wajah ke-3 (baru 1 foto bersih diperiksa visual).
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Fujifilm Official — Instax Mini 11 Specifications](https://www.fujifilm.com/uy/en/consumer/instax/cameras/mini11/specifications)
  - *Situs Fotografi / Ulasan (dengan inspeksi visual sampel):* [Popular Photography — Instax Mini 11 Review](https://www.popphoto.com/gear-reviews/instax-mini-11-review/), [DPReview — Fujifilm Instax Mini 11 Review](https://www.dpreview.com/reviews/3860122294/fujifilm-instax-mini-11-review-the-best-easy-to-use-instax-mini-model/), [Camera Labs — Fujifilm Instax Mini 11 Review](https://www.cameralabs.com/fujifilm-instax-mini-11-review/)
  - *Foto Sampel Diperiksa Visual (1 foto dinding berisi ~15 cetakan non-wajah Mini 11 dari 1 sumber; 0 sampel CC ditemukan):*
    1. [Popular Photography — foto kumpulan cetak fisik uji lapangan Instax Mini 11 (dermaga, pantai, langit, tanaman hias, salju — diperiksa visual sementara, tidak disimpan lokal)](https://www.popphoto.com/gear-reviews/instax-mini-11-review/)

---

## 71. Lomography — Lomography Diana F+

- **id:** `lomography_diana_f_plus`
- **brand:** `Lomography`
- **model:** `Diana F+` *(nama kanonik)*
- **aliases:** `Lomography Diana F+`, `Diana F Plus`
- **displayName:** `Lomography Diana F+`
- **Tahun & Jenis:** 2007 · Toy camera medium format 120
- **Spesifikasi Inti (Resmi):** Film roll 120 (masker 12 bidikan persegi 5,2×5,2cm, 16 bidikan 4,2×4,2cm, atau panorama; kompatibel aksesori *back* Instax Mini); lensa plastik tunggal **75mm tanpa lapisan** yang dapat dilepas untuk fungsi *pinhole*; bukaan ikon f/8, f/11, f/16 + *pinhole*; rana Normal sekitar 1/60 detik dan Bulb; fokus zona 3 jarak (1m–tak terhingga); mendukung eksposur ganda mekanis; *flash* eksternal opsional dengan filter gel warna.
- **Pemisahan Sifat Kamera/Lensa vs Emulsi Film (WAJIB FILM):**
  - **Sifat Bodi & Lensa Plastik (75mm Tanpa Lapisan + Format Persegi 120):** Karakternya amat dominan dan konsisten lintas seluruh sampel yang diperiksa (hidran merah, hutan inframerah 6×6, meja makan dekat jendela, tembok kawat berduri, eksposur ganda atap rumah, serta sepeda biru di depan pintu garasi): **vignette gelap sangat pekat di keempat sudut** membingkai gambar seperti terowongan nostalgia, ketajaman hanya berada di area tengah lalu **melunak menjadi pendaran kabur radial lembut seperti lukisan** dengan lengkungan distorsi barel halus pada garis lurus (tampak jelas pada panel pintu garasi), serta **pendaran sorotan (*dreamy glow*)** yang kuat di sekitar jendela dan langit terang. Konstruksi bodi plastik ringan juga mudah memicu **bocoran cahaya hangat acak** dan eksposur ganda (dikonfirmasi Austerity Photo & Myles Katherine).
  - **Sifat Emulsi Film (pada sampel yang diperiksa):**
    - Sampel 1 (*Borne incendie* — dipasang aksesori **Instax back** pada Diana F+): warna merah hidran pekat dan rumput hijau kontras tinggi berasal dari emulsi Instax, namun pelunakan tepi dan gelap sudut tetap khas lensa plastik Diana.
    - Sampel 2 (*Diana F+ infrared (2)* — **Efke IR820** film inframerah 120 format 6×6): dedaunan putih berpendar pucat dan bayangan cokelat-sepia dalam berasal dari film inframerah Efke IR820 yang memperkuat efek *dreamy* lensa Diana.
    - Sampel 3–6 (Austerity Photo — film negatif warna 120 standar): biru langit lembut, nada hangat alami pada meja kayu dan batu bata, serta warna pastel nostalgia berasal dari film negatif warna 120 (sedangkan ulasan Myles Katherine menunjukkan rona sureal bila diisi film LomoChrome Purple/Turquoise).
- **lookTags:** `heavy-vignette`, `dreamy-soft`, `radial-blur`, `light-leak`, `plastic-distortion`, `square-format`
- **Arketipe:** `toy_lomo_vignette` *(dikonfirmasi)*
- **confidenceLook:** **`medium`** *(diaudit ketat dari `high` → `medium`)* — Contoh campur emulsi film: lensa tetap konsisten secara optik, namun foto contoh memakai jenis film berbeda atau tidak dicantumkan.
- **Tidak ditemukan:** halaman Flickr Camera Finder khusus model ini tidak terverifikasi/tidak digunakan.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Lomography Lomopedia — Diana F+](https://www.lomography.com/magazine/324365-lomopedia-diana-f)
  - *Situs Fotografi / Ulasan (dengan inspeksi visual sampel):* [Austerity Photo — Diana F+ Review](https://austerityphoto.co.uk/diana-f-review-or-how-i-learned-to-love-the-plastic-bomb/), [Myles Katherine — Diana F+ Review & Sample Photos](https://www.myleskatherine.com/blog/2024/2/12/review-of-the-diana-f-with-sample-photos-using-lomography-lomochrome-purple-and-lomochrome-turquoise-in-st-lucia-teaming-up-with-lomography-to-save-120-film)
  - *Foto Sampel Diperiksa Visual (6 lintas 3 sumber):*
    1. [Wikimedia Commons (CC BY 2.0) — *Borne incendie* oleh Nicolas Buffler (Diana F+ dengan *back* Instax)](https://commons.wikimedia.org/wiki/File:Borne_incendie_(15404264009).jpg)
    2. [Flickr CC via Openverse (CC BY 2.0) — *Diana F+ infrared (2)* oleh thart2009 (**film: Efke IR820 120**, 6×6)](https://www.flickr.com/photos/37089490@N06/4539752958)
    3. [Austerity Photo — 4 foto sampel film 120 Diana F+ (meja jendela, tembok kawat berduri, eksposur ganda atap, sepeda depan pintu garasi — diperiksa visual sementara, tidak disimpan lokal)](https://austerityphoto.co.uk/diana-f-review-or-how-i-learned-to-love-the-plastic-bomb/)

---

## 72. Lomography — Lomography La Sardina

- **id:** `lomography_la_sardina`
- **brand:** `Lomography`
- **model:** `La Sardina` *(nama kanonik)*
- **aliases:** `Lomography La Sardina`, `La Sardina 8Ball`, `La Sardina DIY`
- **displayName:** `Lomography La Sardina`
- **Tahun & Jenis:** 2011 · Toy camera 35mm sudut lebar
- **Spesifikasi Inti (Resmi):** Film 35mm (rasio 3:2); lensa plastik sudut sangat lebar **22mm** (sudut pandang sekitar 89°), bukaan tetap **f/8**; fokus zona 2 langkah (0,6–1m dan 1m–tak terhingga); rana Normal **1/100 detik** dan Bulb; sakelar eksposur ganda (MX); konektor mikro untuk *flash* eksternal opsional (*Fritz the Blitz*).
- **Pemisahan Sifat Kamera/Lensa vs Emulsi Film (WAJIB FILM):**
  - **Sifat Bodi & Lensa Plastik (22mm f/8 Sudut Sangat Lebar):** Ketiga sampel CC (tangga monumen, menara gedung mendongak, dan danau pepohonan musim gugur) secara gamblang menampilkan tanda tangan optik 22mm La Sardina: **perspektif sudut amat lebar yang mendramatisasi garis arsitektur dan kedalaman ruang**, **vignette sudut gelap yang sangat nyata di keempat pojok** (mencolok baik pada langit B&W maupun langit biru cerah sampel 3), ketajaman tengah cukup tegas namun **cepat luruh menjadi kabur lembut di pinggir kiri-kanan** (sangat jelas pada dedaunan tepi kanan sampel danau), serta sedikit distorsi tepi khas plastik. Ulasan PCMag dan Camera Go Camera juga menekankan bahwa optik plastik tanpa pelapisan ini mudah memicu *flare* saat menghadap matahari langsung.
  - **Sifat Emulsi Film (pada sampel yang diperiksa):**
    - Sampel 1–2 (*Nanjing Memorial* & *Nanjing on film* — **Lomography Lady Grey 400** B&W): gradasi monokrom sedang hingga tegas dan *grain* 400 yang halus-bertekstur berasal dari Lady Grey 400.
    - Sampel 3 (*La Sardina* danau & pepohonan — *film negatif warna 35mm tidak dicantumkan*): hijau-kuning keemasan dedaunan yang jenuh hangat serta langit biru pekat berasal dari emulsi film negatif warna siang hari, yang makin terasa vintage berkat penggelapan sudut optik La Sardina.
- **lookTags:** `ultra-wide-lofi`, `heavy-vignette`, `soft-corners`, `dramatic-perspective`, `plastic-flare`
- **Arketipe:** `toy_lomo_vignette` *(dikonfirmasi)*
- **confidenceLook:** **`medium`** *(diaudit ketat dari `high` → `medium`)* — Contoh campur emulsi film: lensa tetap konsisten secara optik, namun foto contoh memakai jenis film berbeda atau tidak dicantumkan.
- **Tidak ditemukan:** halaman Flickr Camera Finder khusus model ini tidak terverifikasi/tidak digunakan.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Lomography Lomopedia — Lomography La Sardina](https://www.lomography.com/magazine/243257-lomopedia-lomography-la-sardina)
  - *Situs Fotografi / Ulasan:* [PCMag — Lomography La Sardina Review](https://au.pcmag.com/digital-cameras/5951/lomography-la-sardina-camera-flash-western-edition), [Camera Go Camera — Lomography La Sardina Review](https://cameragocamera.com/2021/10/11/lomography-la-sardina/)
  - *Foto Sampel Diperiksa Visual (3 lintas 2 pengunggah & 2 emulsi):*
    1. [Wikimedia Commons (CC BY 2.0) — *Nanjing Memorial on film* oleh Kevin Dooley (EXIF: Sardina; **film: Lomography Lady Grey 400**)](https://commons.wikimedia.org/wiki/File:Nanjing_Memorial_on_film_(12125869446).jpg)
    2. [Flickr CC via Openverse (CC BY 2.0) — *Nanjing on film* oleh kevin dooley (**film: Lomography Lady Grey 400**, gedung sudut lebar)](https://www.flickr.com/photos/12836528@N00/12125485353)
    3. [Flickr CC via Openverse (CC BY 2.0) — *La Sardina* oleh Skakerman (Archive) (danau & pepohonan musim gugur, film warna 35mm)](https://www.flickr.com/photos/27482959@N08/22875620685)

---

## 73. Camp Snap — Camp Snap Camera

- **id:** `camp_snap_camera`
- **brand:** `Camp Snap`
- **model:** `Camp Snap Camera` *(nama kanonik)*
- **aliases:** `Camp Snap` (v101 / v103 / v105; generasi penerus: `Camp Snap 2`)
- **displayName:** `Camp Snap Camera`
- **Tahun & Jenis:** 2023 · Kamera digital *lo-fi* tanpa layar
- **Spesifikasi Inti (Resmi):** Sensor CMOS sekitar **8 MP** (ukuran ~1/3,2"–1/3"); lensa plastik fokus tetap sudut lebar sekitar ekuiv. 32–35mm (f/1.8–f/2.2 tergantung revisi); tanpa layar LCD peninjau; lampu kilat LED bawaan; penyimpanan kartu microSD 4 GB dan baterai isi ulang USB-C; mendukung penggantian profil filter warna via komputer.
- **Ketergantungan Mode Warna (Digital):** Karakter foto **berasal langsung dari profil JPEG bawaan pabrik** — revisi awal (v101/v103) terpasang *default* **filter Vintage** bernuansa hangat-kehijauan, sementara revisi selanjutnya memungkinkan pengguna mengunggah profil *Standard*, *Vintage*, *Black & White*, atau *custom LUT/filter* melalui berkas *firmware* komputer. Tanpa layar, seluruh hasil mengandalkan eksposur dan pemrosesan otomatis di dalam kamera.
- **Deskripsi Tampilan Hasil Foto (Inspeksi Visual + Sumber):**
  - **Warna & Profil Vintage:** Pada sampel kucing di dalam ruang dari 35mmc tampak jelas karakter filter *Vintage* bawaan Camp Snap: **rona hangat sedikit kuning-kehijauan (*muted vintage tint*)** pada kain terang dan bayangan, sementara pada bidikan malam pecinan warna lampu neon merah tampil **menyala pekat dengan kontras agresif**.
  - **Rentang Dinamis & Sorotan (*Highlight*):** Sensor CMOS mungilnya memiliki **rentang dinamis amat sempit** — pada sampel malam pecinan 35mmc, lampu etalase dan lampu mobil langsung **terbakar putih penuh disertai pendaran (*bloom*) lebar**, sementara bayangan atap dan sudut toko jatuh gelap rapat (selaras dengan ulasan DPReview, PetaPixel, dan PCMag).
  - **Ketajaman, Noise & Cahaya Redup:** Ketajaman lensa fokus tetap tampak **agak lunak di pinggir dan jarak dekat**, disertai pemrosesan JPEG sederhana (penajaman kontras kasar di siang hari menurut DPReview). Di dalam ruang dan malam hari, **bintik *noise* digital (luminansi sekaligus warna) segera muncul kuat** di bayangan kain maupun aspal, dan tanpa *flash* Xenon, rana lambat mudah menimbulkan jejak kabur gerak (*motion blur*) pada kendaraan melintas.
- **lookTags:** `lofi-digital`, `vintage-filter`, `narrow-dr`, `highlight-bloom`, `noisy-lowlight`, `soft-corners`
- **Arketipe:** `toy_lomo_vignette` *(dikonfirmasi)*
- **confidenceLook:** **`medium`** *(terkonfirmasi pada audit ketat)* — 0 foto CC/PD lokal; bukti visual hanya 2 foto sementara (< 3) dari 1 galeri ulasan (35mmc), ditopang 4 ulasan konsisten.
- **Tidak ditemukan:** halaman Flickr Camera Finder khusus model ini tidak terverifikasi/tidak digunakan; foto hasil bidikan berlisensi CC/PD di Wikimedia Commons/Openverse (bukti visual hanya dari inspeksi sementara galeri ulasan); foto contoh bebas-wajah ke-3 (baru 2 foto bersih diperiksa visual).
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Camp Snap Official — Specification](https://www.campsnapphoto.com/pages/specification)
  - *Situs Fotografi / Ulasan (dengan inspeksi visual sampel):* [35mmc — Camp Snap Camera Everyday Carry Review](https://www.35mmc.com/09/02/2026/camp-snap-camera-a-simple-and-fun-everyday-carry/), [DPReview — Camp Snap Camera Review](https://www.dpreview.com/reviews/2441177946/camp-snap-camera-review-perfect-for-camp-rules-but-what-about-the-photos/), [PetaPixel — Camp Snap Camera Review](https://petapixel.com/2025/03/31/camp-snap-camera-review-light-hearted-and-nostalgia-packed/), [PCMag — Camp Snap Review](https://www.pcmag.com/reviews/camp-snap-2)
  - *Foto Sampel Diperiksa Visual (2 foto asli dari 1 sumber; 0 sampel CC ditemukan):*
    1. [35mmc — 2 foto sampel asli Camp Snap (kucing Siam di seprai dalam ruang & jalan malam pecinan — diperiksa visual sementara, tidak disimpan lokal)](https://www.35mmc.com/09/02/2026/camp-snap-camera-a-simple-and-fun-everyday-carry/)

---

## 74. Nintendo — Nintendo Game Boy Camera

- **id:** `nintendo_game_boy_camera`
- **brand:** `Nintendo`
- **model:** `Game Boy Camera` *(nama kanonik)*
- **aliases:** `Pocket Camera` (Jepang), `Nintendo Game Boy Camera`, `GB Camera`
- **displayName:** `Nintendo Game Boy Camera`
- **Tahun & Jenis:** 1998 · Kamera digital aksesori konsol genggam
- **Spesifikasi Inti:** Sensor CMOS **Mitsubishi M64282FP** (128×128 piksel fisik, hasil tangkapan aktif **128×112 piksel** / ~0,014 MP); kedalaman warna **2-bit monokrom (4 tingkat abu-abu)**; lensa plastik fokus tetap sudut lebar (ekuiv. sekitar 34mm) dalam kepala putar 180°; penyimpanan hingga 30 foto di memori kartrid. *(Halaman spesifikasi resmi produsen asli 1998 tidak ditemukan.)*
- **Ketergantungan Mode Warna (Digital):** Tampilan ekstrem 128×112 berpalet **4 tingkat abu-abu** berasal **secara mutlak dari perangkat keras sensor 2-bit bawaan** (tidak dapat diubah ke warna penuh). Pengguna hanya dapat menyetel kecerahan, kontras, dan mengaktifkan/mematikan **pola titik *dithering* matriks teratur** (*ordered Bayer-like dithering*) — saat *dithering* aktif (mode baku), gradasi disimulasikan lewat titik-titik papan catur; saat dimatikan, foto berubah menjadi grafis hitam-putih kontras tajam terposterisasi.
- **Deskripsi Tampilan Hasil Foto (Inspeksi Visual + Sumber):**
  - **Resolusi Mikro & Piksel 8-Bit:** Pada ketiga tangkapan mentah 128×112 CC/CC0 (trem Dunedin, sedan parkir, dan pasangan stereo Subaru *3D Liberty*), seluruh citra tersusun atas **blok piksel persegi kasar bergaya 8-bit** tanpa peredaman atau *anti-aliasing* sehingga detail halus melebur menjadi siluet grafis ikonik.
  - **Palet 4-Shade & Pola Dithering:** Seluruh rentang nada dibatasi ketat pada **empat tingkat gelap-terang** (putih, abu-abu muda, abu-abu gelap, hitam — tampil abu-abu murni saat diekspor mentah atau bernuansa hijau-zaitun saat dilihat di layar LCD Game Boy asli). Transisi langit, kaca mobil, dan aspal sepenuhnya mengandalkan **pola titik/papan catur *dithering* yang rapat dan ritmis**.
  - **Kontras & Sorotan:** Rentang dinamis amat ekstrem — bidang cerah (kap mobil putih dan langit pada sampel 2–3) langsung jatuh menjadi **putih rata tanpa tekstur**, sedangkan ban, kolong mobil, dan sisi teduh langsung jatuh **hitam pekat**. Tanpa *flash* dan nyaris tanpa kepekaan cahaya redup, foto sangat bertumpu pada kontras bentuk yang kuat di bawah pencahayaan terang.
- **lookTags:** `pixelated-8bit`, `4-shade-mono`, `dither-pattern`, `extreme-lofi`, `stark-contrast`
- **Arketipe:** `toy_lomo_vignette` *(dikonfirmasi)*
- **confidenceLook:** **`high`** *(terkonfirmasi pada audit ketat)* — Memenuhi syarat ketat (>= 2 domain independen [5], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa).
- **Tidak ditemukan:** halaman spesifikasi resmi produsen aktif; halaman Flickr Camera Finder khusus model ini tidak terverifikasi/tidak digunakan.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Situs Fotografi / Ulasan:* [PetaPixel — Nintendo Game Boy Camera 24 Years Later](https://petapixel.com/2022/05/05/a-review-of-the-nintendo-game-boy-camera-24-years-later/), [Casual Photophile — Game Boy Camera Review](https://casualphotophile.com/2021/12/19/game-boy-camera-review/), [35mmc — Game Boy Camera by Adam Laws](https://www.35mmc.com/28/04/2018/game-boy-camera-by-adam-laws/)
  - *Foto Sampel Diperiksa Visual (3 tangkapan mentah 128×112 lintas 3 pengunggah):*
    1. [Wikimedia Commons (CC0) — *Dunedin tram in Otago Settlers Museum* oleh CauliflowerMoon (tangkapan mentah 4-shade *dithering*)](https://commons.wikimedia.org/wiki/File:Appreciably_lower_quality_dunedin_tram_in_otago_settlers_museum.png)
    2. [Wikimedia Commons (CC BY-SA 3.0) — *Car photo taken by gameboy camera* oleh Weakmassive (mobil di terik matahari, kontras tajam)](https://commons.wikimedia.org/wiki/File:Car_photo_taken_by_gameboy_camera.png)
    3. [Flickr CC via Openverse (CC BY 2.0) — *3D Liberty* oleh Jordan Bajc (pasangan stereo Subaru 1993, pola *dithering* penuh)](https://www.flickr.com/photos/68790385@N04/6279768354)

---

## 75. Sony Ericsson — Sony Ericsson K800i

- **id:** `sony_ericsson_k800i`
- **brand:** `Sony Ericsson`
- **model:** `Sony Ericsson K800i` *(nama kanonik)*
- **aliases:** `K800i`, `K800a` (varian Amerika dengan modul kamera identik), `K800c` (Tiongkok), `Cyber-shot K800i`
- **displayName:** `Sony Ericsson K800i`
- **Tahun & Jenis:** 2006 · Camera phone awal (Cyber-shot)
- **Spesifikasi Inti:** Sensor CMOS **3,15/3,2 MP** (2048×1536 piksel) dengan autofokus dan mode makro; **flash Xenon sejati** + penutup lensa geser mekanis + fitur *BestPic* 9 bidikan beruntun; video QCIF (176×144) 15fps. *(Halaman spesifikasi resmi produsen asli 2006 tidak ditemukan.)*
- **Ketergantungan Mode Warna (Digital):** Karakter utama muncul langsung pada **setelan bawaan otomatis (default JPEG Cyber-shot)** tanpa efek tambahan; ponsel menyediakan efek opsional (*Black & white*, *Negative*, *Sepia*, *Solarize*) dan pilihan adegan, namun ketiga sampel CC yang diperiksa adalah **SOOC langsung dari *firmware* bawaan Sony Ericsson**.
- **Deskripsi Tampilan Hasil Foto (Inspeksi Visual + Sumber):**
  - **Warna & White Balance:** Pada foto patung perunggu di siang hari (sampel 1) dan jalan berkabut/mendung London (sampel 3), warna tampil **wajar namun agak kalem dan sedikit condong dingin/keunguan tipis** di permukaan beton dan bayangan mendung, tidak sejenuh kamera saku digital sezamannya. Pada matahari terbit pantai (sampel 2), langit bergradasi kuning-persik hangat sementara pasir basah tetap condong zaitun-sejuk.
  - **Kontras, Sorotan & Flare:** Rentang dinamis sempit khas sensor ponsel 2006: langit mendung cerah dan matahari terbit langsung **penuh memutih rata (*clipped*)**, dan melawan matahari rendah muncul **berkas *flare* miring serta kabut kontras ringan** (jelas pada sampel 2).
  - **Ketajaman & Noise:** Untuk kelas 3,2 MP tahun 2006, ketajaman tengah pada tekstur perunggu, susunan bata, dan jendela gedung tergolong **rapi dan jelas dengan sedikit pelunakan di ujung bingkai** serta *grain* luminansi halus yang tetap terasa di area teduh dan langit mendung tanpa penghalusan berlebihan.
  - **Karakter Flash Xenon:** Dikonfirmasi GSMArena, MobileTechReview, dan diskusi DPReview, **flash Xenon sejati** mampu membekukan subjek di ruang gelap secara jauh lebih kuat dan tajam dibanding LED ponsel sezamannya, meski mudah membuat objek amat dekat tampak terlalu terang/datar.
- **lookTags:** `crisp-3mp`, `cool-muted-wb`, `clipped-highlights`, `xenon-flash`, `early-mobile`
- **Arketipe:** `early_camera_phone` *(dikonfirmasi)*
- **confidenceLook:** **`high`** *(terkonfirmasi pada audit ketat)* — Memenuhi syarat ketat (>= 2 domain independen [5], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa).
- **Tidak ditemukan:** halaman spesifikasi resmi produsen aktif; halaman Flickr Camera Finder khusus model ini tidak terverifikasi/tidak digunakan.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Situs Ulasan / Komunitas:* [GSMArena — Sony Ericsson K800 Review](https://www.gsmarena.com/sony_ericsson_k800-review-93.php), [GSMArena — K800 Camera Page](https://www.gsmarena.com/sony_ericsson_k800-review-93p2.php), [TechRadar — Sony Ericsson K800i Review](https://www.techradar.com/reviews/phones/mobile-phones/sony-ericsson-k800i-87823/review), [MobileTechReview — Sony Ericsson K800i](https://www.mobiletechreview.com/phones/sony-ericsson-K800i.htm), [DPReview Forum — K800i Discussion](https://www.dpreview.com/forums/threads/sony-k800i-phone-vs-canon-350d.1679406/)
  - *Foto Sampel Diperiksa Visual (3, seluruhnya EXIF SOOC firmware Sony Ericsson):*
    1. [Wikimedia Commons (Public domain) — *'s-Hertogenbosch the bullock* oleh Julo (EXIF: K800i, siang)](https://commons.wikimedia.org/wiki/File:%27s-Hertogenbosch_the_bullock.jpg)
    2. [Wikimedia Commons (CC BY-SA 3.0) — *Amazing sunrise at Kuakata Sea Beach* oleh Arman333 (EXIF: K800a, matahari terbit *backlight*)](https://commons.wikimedia.org/wiki/File:Amazing_sunrise_at_Kuakata_Sea_Beach.jpg)
    3. [Wikimedia Commons (CC BY-SA 2.0) — *"Checkerboard Flats", Page Street, SW1* oleh Danny Robinson (EXIF: K800i, mendung)](https://commons.wikimedia.org/wiki/File:%22Checkerboard_Flats%22,_Page_Street,_SW1_-_geograph.org.uk_-_573512.jpg)

---

## 76. Nokia — Nokia N95

- **id:** `nokia_n95`
- **brand:** `Nokia`
- **model:** `Nokia N95` *(nama kanonik)*
- **aliases:** `N95`, `Nokia N95-1`, `Nokia N95 8GB` (modul kamera 5 MP Carl Zeiss sama)
- **displayName:** `Nokia N95`
- **Tahun & Jenis:** 2007 · Camera phone awal (Symbian S60)
- **Spesifikasi Inti:** Sensor CMOS **5 MP** (2592×1944 piksel); optik **Carl Zeiss Tessar 5,6mm f/2.8** dengan autofokus (makro mulai 10cm); rana mekanis 1/3–1/1000 detik; lampu kilat LED bawaan; video VGA (640×480) hingga 30fps. *(Halaman spesifikasi resmi produsen asli 2007 tidak ditemukan.)*
- **Ketergantungan Mode Warna (Digital):** Karakter foto berasal dari **mode otomatis standar JPEG Symbian S60** (tanpa filter *Color Tone* Sepia/B&W/Vivid); ulasan GSMArena dan All About Symbian mencatat bahwa beberapa mode adegan (*Scene Mode*) dapat mendorong penajaman tepi lebih agresif, namun ketiga sampel CC yang diperiksa adalah **EXIF SOOC Nokia N95**.
- **Deskripsi Tampilan Hasil Foto (Inspeksi Visual + Sumber):**
  - **Ketajaman & Penajaman Tepi:** Pada papan petunjuk hutan siang hari (sampel 1) dan bidikan *close-up* kue *Bolo de Mel* (sampel 3), optik Carl Zeiss 5 MP menangkap detail daun, rumput, dan teks kemasan secara **sangat tajam dan penuh tekstur** untuk ponsel 2007 — namun pada helai rumput halus di sampel 1 tampak jelas **penajaman tepi digital agak keras (*oversharpened micro-contrast*)** khas pemrosesan Nokia era Symbian.
  - **Warna & White Balance:** Di siang hari cerah (sampel 1), hijau hutan tampil **hidup dan segar**; pada pencahayaan toko dalam ruang (sampel 3), keseimbangan putih otomatis tampak **sedikit dingin/klinis**, sedangkan pada siluet menara senja (sampel 2) bayangan gedung jatuh kelabu-dingin agak datar.
  - **Sorotan, Flare & Cahaya Redup:** Pada foto senja berlatar air (sampel 2), langit cerah di balik gedung langsung **terbakar putih lebar dengan kabut *flare* lembut** di sisi kiri, sementara sisi teduh bangunan memperlihatkan **bintik *noise* digital yang nyata dan penurunan kontras**. Di malam hari, *flash* LED lemah bawaan (dikonfirmasi GSMArena, All About Symbian, dan CNET) jangkauannya sangat pendek dan mudah memicu blur gerak serta *noise* kasar.
- **lookTags:** `detailed-daylight`, `oversharpened-edges`, `cool-tint`, `clipped-sky`, `lowlight-noise`, `weak-led-flash`
- **Arketipe:** `early_camera_phone` *(dikonfirmasi)*
- **confidenceLook:** **`high`** *(terkonfirmasi pada audit ketat)* — Memenuhi syarat ketat (>= 2 domain independen [4], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa).
- **Tidak ditemukan:** halaman spesifikasi resmi produsen aktif; halaman Flickr Camera Finder khusus model ini tidak terverifikasi/tidak digunakan.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Situs Ulasan:* [GSMArena — Nokia N95 Camera Review](https://www.gsmarena.com/nokia_n95_camera-review-139p2.php), [All About Symbian — Nokia N95 Part 3: The Camera](https://allaboutsymbian.com/reviews/item/Nokia_N95-part_3_the_Camera.php), [CNET — Living with the N95](https://www.cnet.com/tech/mobile/living-with-the-n95/)
  - *Foto Sampel Diperiksa Visual (3, seluruhnya EXIF SOOC Nokia N95):*
    1. [Wikimedia Commons (CC BY-SA 3.0) — *"Odietako xendetako" kartela* oleh Adrar (EXIF: N95, hutan siang)](https://commons.wikimedia.org/wiki/File:%22Odietako_xendetako%22_kartela.jpg)
    2. [Wikimedia Commons (CC BY-SA 3.0) — *Provinciehuis Noord-Brabant* oleh Cro-Cop (EXIF: N95, senja *backlight* & pantulan air)](https://commons.wikimedia.org/wiki/File:%27s-Hertogenbosch_-_Provinciehuis_Noord-Brabant.jpg)
    3. [Wikimedia Commons (CC BY 2.0) — *"Bolo de mel" cakes, Madeira* oleh Tim Walker (EXIF: N95, *close-up* dalam ruang)](https://commons.wikimedia.org/wiki/File:%22Bolo_de_mel%22_cakes,_Madeira.jpg)

---

## 77. Nokia — Nokia N8

- **id:** `nokia_n8`
- **brand:** `Nokia`
- **model:** `Nokia N8` *(nama kanonik)*
- **aliases:** `Nokia N8-00`, `N8`
- **displayName:** `Nokia N8`
- **Tahun & Jenis:** 2010 · Camera phone awal (Symbian^3)
- **Spesifikasi Inti:** Sensor CMOS besar **1/1,83" 12 MP**; optik **Carl Zeiss 28mm ekuiv. f/2.8** dengan autofokus; rana mekanis dan **filter ND (Neutral Density)** otomatis bawaan; **flash Xenon sejati**; video 720p (25/30fps). *(Halaman spesifikasi resmi produsen asli 2010 tidak ditemukan.)*
- **Ketergantungan Mode Warna (Digital):** Seluruh tampilan alami menyerupai kamera kompak saku muncul langsung pada **setelan bawaan otomatis (default JPEG Symbian^3)** tanpa filter *Color Tone* tambahan; ketiga sampel CC yang diperiksa adalah tangkapan standar tanpa olah lanjut.
- **Deskripsi Tampilan Hasil Foto (Inspeksi Visual + Sumber):**
  - **Tekstur Organik ala Kamera Kompak:** Pada ketiga sampel (bunga *Adenium* merah muda, rumah beranda beratap hijau di hari berkabut, dan ayam putih di kebun rimbun), perbedaan sensor fisik besar 1/1,83" langsung terasa dibanding ponsel kamera 1/3,2" sezamannya: **tekstur rumput, plester dinding, bulu ayam, dan dedaunan tampak halus alami tanpa halo penajaman buatan yang kasar maupun penghalusan cat air berat**, ditemani *grain* luminansi tipis yang organik di area teduh.
  - **Warna & Kontras:** Reproduksi warna terasa **jujur, tenang, dan seimbang** — hijau rumput dan dedaunan pada sampel 2–3 tidak berlebihan, kelopak merah muda pada sampel 1 tampak segar wajar, dan gradasi langit berkabut maupun dinding teduh mengalir lembut dengan kontras yang enak dipandang.
  - **Sorotan, Optik & Flash Xenon:** Optik Carl Zeiss 28mm menjaga ketajaman merata dengan distorsi minim; pada bukaan celah langit amat terang di belakang pohon (sampel 3) sorotan tetap dapat penuh ke putih, namun secara keseluruhan berkat sensor besar dan filter ND bawaan, sorotan siang hari jauh lebih terkendali. Menurut GSMArena dan komunitas DPReview, **flash Xenon sejati** menjadikan foto malam dan ruangan gelap jauh lebih bersih serta mampu membekukan gerak secara alami.
- **lookTags:** `natural-rendering`, `crisp-zeiss`, `balanced-dr`, `organic-texture`, `xenon-flash`, `compact-like`
- **Arketipe:** `early_camera_phone` *(dikonfirmasi)*
- **confidenceLook:** **`high`** *(terkonfirmasi pada audit ketat)* — Memenuhi syarat ketat (>= 2 domain independen [4], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa).
- **Tidak ditemukan:** halaman spesifikasi resmi produsen aktif; halaman Flickr Camera Finder khusus model ini tidak terverifikasi/tidak digunakan.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Situs Ulasan / Komunitas:* [GSMArena — Nokia N8 Camera Review](https://www.gsmarena.com/nokia_n8-review-523p6.php), [DPReview Forum — Nokia N8 Photo Thread](https://www.dpreview.com/forums/threads/nokia-n8-photo-thread.3153994/)
  - *Foto Sampel Diperiksa Visual (3 lintas 2 platform):*
    1. [Wikimedia Commons (CC BY-SA 3.0) — *'Adenium Obesum' flowers and buds* oleh Tarunpant (EXIF: N8-00 SOOC, *close-up*)](https://commons.wikimedia.org/wiki/File:%27Adenium_Obesum%27_flowers_and_buds,Gurgaon,India.jpg)
    2. [Wikimedia Commons (CC BY-SA 3.0) — *'BANOOL' Linden* oleh Drpamoss (EXIF: N8-00 SOOC, mendung berkabut)](https://commons.wikimedia.org/wiki/File:%27BANOOL%27_Linden.jpg)
    3. [Flickr CC via Openverse (CC BY-SA 2.0) — *Nokia N8 chickens* oleh Titanas (kebun teduh & langit terang)](https://www.flickr.com/photos/96437548@N00/5341147374)

---

## 78. Nokia — Nokia 808 PureView

- **id:** `nokia_808_pureview`
- **brand:** `Nokia`
- **model:** `Nokia 808 PureView` *(nama kanonik)*
- **aliases:** `808 PureView`, `Nokia 808`
- **displayName:** `Nokia 808 PureView`
- **Tahun & Jenis:** 2012 · Camera phone sensor besar (Symbian Belle)
- **Spesifikasi Inti:** Sensor CMOS masif **1/1,2" 41 MP total** (38 MP efektif 4:3, 34 MP 16:9) dengan teknologi *oversampling* PureView ke 3/5/8 MP; optik **Carl Zeiss f/2.4**; rana mekanis, **filter ND bawaan**, **flash Xenon** + lampu LED video; video 1080p 30fps. *(Halaman spesifikasi resmi produsen asli 2012 tidak ditemukan.)*
- **Ketergantungan Mode Warna (Digital):** Karakter paling ikonik berasal dari **mode bawaan PureView *oversampling* (5 MP / 8 MP default JPEG)** yang memadatkan beberapa piksel menjadi satu piksel bersih bertekstur organik; mode *Creative* menyediakan pilihan resolusi penuh 34–38 MP (detail maksimal tetapi butiran lebih terasa) serta setelan saturasi/kontras, namun ketiga sampel CC yang diperiksa adalah **EXIF SOOC `808 PureView`**.
- **Deskripsi Tampilan Hasil Foto (Inspeksi Visual + Sumber):**
  - **Tekstur Oversampling & Detail Mikro:** Ketiga sampel SOOC (jamur putih *Leucocoprinus* di serasah daun, sedan Talbot Tagora GLS 1981 biru-toska di tepi padang rumput, dan jamur kayu *Trametes* di batang lapuk) memamerkan kualitas yang nyaris mustahil disangka berasal dari ponsel 2012: **detail mikro luar biasa tajam namun sepenuhnya halus dan alami**, bebas dari halo penajaman digital maupun noda cat air peredaman *noise*.
  - **Kedalaman Bidang (*Depth of Field*) Alami:** Berkat ukuran sensor raksasa 1/1,2" dan bukaan f/2.4, bidikan dekat (sampel 1 & 3) langsung menghasilkan **separasi subjek-latar optik yang nyata dengan bokeh lembut krem**, bukan efek kabur buatan perangkat lunak.
  - **Warna, Kontras & Noise:** Warna cat biru-toska mobil, lampu belakang merah, dan hijau padang rumput (sampel 2) tampak **kaya, dalam, dan terukur alami**, gradasi kulit kayu serta bayangan lembap (sampel 1 & 3) sangat bersih dari bercak warna digital. Ulasan DPReview, GSMArena, TechRadar, dan All About Symbian juga mencatat bahwa pada resolusi penuh 38 MP butiran *grain* halus mirip film tampak lebih kentara dan sesekali *white balance* otomatis sedikit berubah di pencahayaan campur, sementara **flash Xenon** tetap kuat untuk malam hari.
- **lookTags:** `organic-oversampled`, `ultra-detailed`, `natural-bokeh`, `clean-texture`, `filmic-grain`, `xenon-flash`
- **Arketipe:** `early_camera_phone` *(dikonfirmasi)*
- **confidenceLook:** **`high`** *(terkonfirmasi pada audit ketat)* — Memenuhi syarat ketat (>= 2 domain independen [5], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa).
- **Tidak ditemukan:** halaman spesifikasi resmi produsen aktif; halaman Flickr Camera Finder khusus model ini tidak terverifikasi/tidak digunakan.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Situs Fotografi / Ulasan:* [DPReview — Nokia 808 PureView Review](https://www.dpreview.com/reviews/4388245494/nokia-808-pureview-review/), [GSMArena — Nokia 808 PureView Camera Review](https://www.gsmarena.com/nokia_808_pureview-review-776p6.php), [TechRadar — Nokia 808 PureView Review](https://www.techradar.com/reviews/phones/mobile-phones/nokia-808-pureview-1090241/review/12), [All About Symbian — Nokia 808 PureView Camera Part 2a](https://allaboutsymbian.com/reviews/item/15115_Nokia_808_PureView_part_2a_cam.php)
  - *Foto Sampel Diperiksa Visual (3, seluruhnya EXIF SOOC Nokia 808 PureView):*
    1. [Wikimedia Commons (CC BY-SA 3.0) — *Leucocoprinus cretaceus* oleh Nelly Chemaly (EXIF: 808 PureView, *close-up* bokeh alami)](https://commons.wikimedia.org/wiki/File:2012-07-19_Leucocoprinus_cretaceus_(Bull.-Fr.)_Locq_251432.jpg)
    2. [Wikimedia Commons (CC BY-SA 3.0) — *1981 Tagora GLS* oleh Tagoraman (EXIF: 808 PureView, siang cerah)](https://commons.wikimedia.org/wiki/File:1981_Tagora_GLS.jpg)
    3. [Wikimedia Commons (CC BY-SA 3.0) — *Trametes cingulata* oleh Glen van Niekerk / primordius (EXIF: 808 PureView, tekstur kayu & jamur)](https://commons.wikimedia.org/wiki/File:2013-04-02_Trametes_cingulata_Berk_319725.jpg)

---

## 79. Apple — Apple iPhone 3GS

- **id:** `apple_iphone_3gs`
- **brand:** `Apple`
- **model:** `iPhone 3GS` *(nama kanonik)*
- **aliases:** `Apple iPhone 3GS`, `iPhone 3G S`
- **displayName:** `Apple iPhone 3GS`
- **Tahun & Jenis:** 2009 · Camera phone awal
- **Spesifikasi Inti (Resmi):** Sensor CMOS **3 MP (3,15 MP)** f/2.8 dengan **autofokus sentuh (*tap-to-focus*)** serta pengukuran eksposur dan *white balance* otomatis; jarak makro hingga sekitar 10cm; **tanpa lampu kilat** dan tanpa kamera depan; video VGA (480p) 30fps.
- **Ketergantungan Mode Warna (Digital):** Aplikasi kamera bawaan iOS 3–4 sangat minimalis tanpa mode adegan, filter warna, maupun HDR (HDR baru hadir mulai iPhone 4 / iOS 4.1), sehingga seluruh ciri khas berasal **100% dari JPEG otomatis bawaan kamera**. *(Catatan sampel: sampel 1 adalah SOOC murni iOS 3.1.3; sampel 2–3 ber-EXIF `iPhone 3GS` dengan jejak simpan ulang perangkat lunak sehingga pengamatan tekstur diverifikasi silang dengan sampel 1 dan ulasan GSMArena/TechRadar.)*
- **Deskripsi Tampilan Hasil Foto (Inspeksi Visual + Sumber):**
  - **Butiran Noise Luminansi Khas Siang Hari:** Inilah tanda visual paling menonjol dari iPhone 3GS: pada aspal cerah sampel 1, cat pintu biru-abu sampel 2, hingga langit biru siang Mission Dolores sampel 3, tampak **lapisan *noise* luminansi berbutir halus yang seragam dan jujur** alih-alih penghalusan cat air — memberi nuansa sedikit bertekstur mirip *grain* digital awal.
  - **Warna & Rentang Dinamis:** Di bawah matahari terang (sampel 1 & 3), keseimbangan warna terasa **cukup netral-sejuk dan rapi** (bata merah, rangka biru, langit biru tua, dan ukiran batu krem tampil wajar tanpa saturasi berlebih). Namun **rentang dinamis sempit**: trotoar terkena matahari langsung pada sampel 1 langsung memutih terbakar (*clipped*), sedangkan kolong mobil dan bayangan pohon sampel 3 jatuh gelap rapat.
  - **Ketajaman & Ketiadaan Flash:** Resolusi 3 MP memberi detail memadai di bidang fokus tengah (ukiran fasad dan gagang kuningan) dengan **kelembutan halus pada detail mikro**. Karena **tidak memiliki lampu kilat**, pemotretan di ruang redup atau malam (dikonfirmasi GSMArena & TechRadar) segera berubah gelap, kasar ber-noise, dan sangat rentan kabur gerak.
- **lookTags:** `balanced-daylight`, `luminance-grain`, `modest-3mp`, `clipped-highlights`, `no-flash-lowlight`
- **Arketipe:** `early_camera_phone` *(dikonfirmasi)*
- **confidenceLook:** **`high`** *(terkonfirmasi pada audit ketat)* — Memenuhi syarat ketat (>= 2 domain independen [4], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa).
- **Tidak ditemukan:** halaman Flickr Camera Finder khusus model ini tidak terverifikasi/tidak digunakan.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Apple Support — iPhone 3GS Technical Specifications](https://support.apple.com/en-om/112307)
  - *Situs Ulasan:* [GSMArena — Apple iPhone 3GS Camera Review](https://www.gsmarena.com/apple_iphone_3gs-review-369p7.php), [TechRadar — iPhone 3GS Camera Review](https://www.techradar.com/reviews/phones/mobile-phones/iphone-3gs-610078/review/7)
  - *Foto Sampel Diperiksa Visual (3, seluruhnya EXIF iPhone 3GS):*
    1. [Wikimedia Commons (CC BY-SA 2.0) — *Una Honda S2000 nera con rollbar blu* oleh Phil Denton (EXIF: iPhone 3GS, iOS 3.1.3 SOOC)](https://commons.wikimedia.org/wiki/File:%27_98_-_Una_Honda_s2000_nera_con_rollbar_blu.jpg)
    2. [Wikimedia Commons (Public domain) — *02 NBA Station 04* oleh 23BowkerClose (EXIF: iPhone 3GS, pintu ruang tunggu teduh)](https://commons.wikimedia.org/wiki/File:02_NBA_Station_04.jpg)
    3. [Wikimedia Commons (CC BY-SA 2.0) — *00 Mission Dolores* oleh Al Abut (EXIF: iPhone 3GS, fasad batu & langit biru siang)](https://commons.wikimedia.org/wiki/File:00_Mission_Dolores.jpg)

---

## 80. Samsung — Samsung Galaxy S II (GT-I9100)

- **id:** `samsung_galaxy_s_ii`
- **brand:** `Samsung`
- **model:** `Galaxy S II` *(nama kanonik)*
- **aliases:** `GT-I9100`, `Samsung Galaxy S II`, `Galaxy S2`, `SC-02C` (Jepang)
- **displayName:** `Samsung Galaxy S II (GT-I9100)`
- **Tahun & Jenis:** 2011 · Camera phone awal (Android)
- **Spesifikasi Inti (Resmi):** Sensor CMOS **1/3,2" 8 MP** (3264×2448 piksel) f/2.6 dengan autofokus sentuh dan lampu kilat LED; perekaman video 1080p 30fps; kamera depan 2 MP.
- **Ketergantungan Mode Warna (Digital):** Karakter tajam, kontras tegas, dan saturasi warna hidup berasal langsung dari **pemrosesan JPEG otomatis bawaan aplikasi kamera TouchWiz Android** (tanpa perlu mengaktifkan filter *Negative/Sepia/Grayscale* maupun mode adegan khusus); sampel 1–2 merupakan SOOC *firmware* GT-I9100 murni *(sampel 3 membawa jejak simpan ulang Aviary, diverifikasi konsisten dengan sampel 1–2 dan ulasan).*
- **Deskripsi Tampilan Hasil Foto (Inspeksi Visual + Sumber):**
  - **Ketajaman & Saturasi Hidup:** Pada foto makro bunga iris kuning-marun (sampel 2) serta truk pikap putih dan fasad bata (sampel 1 & 3), sensor 8 MP menghasilkan **ketajaman siang hari yang sangat bersih dan kaya detail** pada serat kelopak bunga, susunan bata, hingga kisi krom, dipadukan **saturasi warna yang cerah dan menyala** (kuning keemasan, marun pekat, hijau rumput segar) khas pemrosesan Samsung awal 2010-an.
  - **Kontras & Sorotan (*Highlight*):** Kontras bawaan terasa tegas dan rapat; pada langit mendung terang (sampel 1 & 2) rentang dinamis segera menyempit sehingga **langit mudah penuh terbakar putih rata (*clipped sky*)**, sementara bayangan kolong kendaraan dan dedaunan gelap (sampel 3) jatuh cukup pekat.
  - **Cahaya Redup & Peredaman Noise:** Pada tepi jauh bangunan mendung tampak sedikit pelunakan sudut, sementara menurut pengujian GSMArena, Imaging Resource, dan PCMag, pada cahaya redup dan malam hari *noise* cepat naik disertai **peredaman *noise* agresif yang menghaluskan tekstur halus** (*smudged low-light*) dengan bantuan LED *flash* berjarak pendek.
- **lookTags:** `punchy-8mp`, `vivid-saturation`, `crisp-daylight`, `clipped-sky`, `smudged-lowlight`
- **Arketipe:** `early_camera_phone` *(dikonfirmasi)*
- **confidenceLook:** **`high`** *(terkonfirmasi pada audit ketat)* — Memenuhi syarat ketat (>= 2 domain independen [5], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa).
- **Tidak ditemukan:** halaman Flickr Camera Finder khusus model ini tidak terverifikasi/tidak digunakan.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Samsung Mobile Press — Galaxy S II Specifications](https://www.samsungmobilepress.com/media-assets/galaxy-s-ii?tab=specs)
  - *Situs Fotografi / Ulasan:* [GSMArena — Samsung Galaxy S II Camera Review](https://www.gsmarena.com/samsung_i9100_galaxy_s_ii-review-588p5.php), [Imaging Resource — Samsung Galaxy S II Review](https://www.imaging-resource.com/cameras/samsung-galaxy-s-ii-review/), [PCMag — Samsung Galaxy S II Review](https://www.pcmag.com/reviews/samsung-galaxy-s-ii-unlocked)
  - *Foto Sampel Diperiksa Visual (3, seluruhnya EXIF GT-I9100):*
    1. [Wikimedia Commons (CC BY-SA 3.0) — *"Brouwershuis" of "Waterhuis"* oleh ZedZoida (EXIF: GT-I9100 SOOC, arsitektur mendung)](https://commons.wikimedia.org/wiki/File:%22Brouwershuis%22_of_%22Waterhuis%22_2012-09-25_07-43-27.jpg)
    2. [Wikimedia Commons (CC BY-SA 3.0) — *Golden Iris* oleh FalsetaIL (EXIF: GT-I9100 SOOC, makro bunga warna pekat)](https://commons.wikimedia.org/wiki/File:%22%D7%90%D7%99%D7%A8%D7%95%D7%A1_%D7%96%D7%94%D7%95%D7%91%22.jpg)
    3. [Wikimedia Commons (Public domain) — *'17 Ford Super Duty Regular Cab* oleh Bull-Doser (EXIF: GT-I9100, siang teduh)](https://commons.wikimedia.org/wiki/File:%2717_Ford_Super_Duty_Regular_Cab.jpg)

---

---

## Ringkasan Laporan Batch 04 *(Diperbarui Audit Ketat)*

### 1. Jumlah Kamera per `confidenceLook` (Setelah Audit Ketat — Lihat [AUDIT.md](AUDIT.md))

| Tingkat `confidenceLook` | Jumlah | Daftar Kamera |
|---|---:|---|
| **`high`** | **10** | `kodak_funsaver`, `ilford_hp5_plus_single_use_camera`, `polaroid_sun_660_autofocus`, `nintendo_game_boy_camera`, `sony_ericsson_k800i`, `nokia_n95`, `nokia_n8`, `nokia_808_pureview`, `apple_iphone_3gs`, `samsung_galaxy_s_ii` |
| **`medium`** | **7** | `ilford_xp2_super_single_use_camera`, `lomography_simple_use_reloadable_film_camera`, `polaroid_spectra`, `polaroid_now_gen_2`, `lomography_diana_f_plus`, `lomography_la_sardina`, `camp_snap_camera` |
| **`low`** | **3** | `fujifilm_instax_wide_300`, `fujifilm_instax_square_sq6`, `fujifilm_instax_mini_11` |
| **`tidak ditemukan`** | **0** | — |
| **Total** | **20** | **54 foto/frame diperiksa visual** (36 berlisensi CC/PD lokal di `docs/references/manifest.md`) |

### 2. Kamera dengan Sumber Lemah / Perlu Perhatian Saat Kalibrasi

- **`ilford_xp2_super_single_use_camera` (`medium`):** 0 foto CC/PD lokal; bukti visual hanya 2 foto sementara (< 3) dari 1 galeri ulasan (The Darkroom), ditopang 3 ulasan konsisten.
- **`lomography_simple_use_reloadable_film_camera` (`medium`):** Contoh campur emulsi film: lensa tetap konsisten secara optik, namun foto contoh memakai jenis film berbeda atau tidak dicantumkan.
- **`polaroid_spectra` (`medium`):** 0 foto CC/PD lokal; bukti visual hanya 2 pindaian sementara (< 3, warna & B&W) dari 1 galeri ulasan (Blue Moon Camera Codex).
- **`polaroid_now_gen_2` (`medium`):** Foto contoh diperiksa visual < 3 (hanya 2: 1 Flickr CC + 1 sampel ulasan Myles Katherine).
- **`fujifilm_instax_wide_300` (`low`):** Bukti visual sangat terbatas: hanya 1 pindaian cetak fisik (CC0 Wikimedia) yang diperiksa langsung.
- **`fujifilm_instax_square_sq6` (`low`):** 0 foto CC/PD; bukti visual sangat terbatas: hanya 1 foto cetak sementara dari 1 galeri ulasan (Fstoppers).
- **`fujifilm_instax_mini_11` (`low`):** 0 foto CC/PD; bukti visual sangat terbatas: hanya 1 foto dinding kolase cetak dari 1 galeri ulasan (Popular Photography).
- **`lomography_diana_f_plus` (`medium`):** Contoh campur emulsi film: lensa tetap konsisten secara optik, namun foto contoh memakai jenis film berbeda atau tidak dicantumkan.
- **`lomography_la_sardina` (`medium`):** Contoh campur emulsi film: lensa tetap konsisten secara optik, namun foto contoh memakai jenis film berbeda atau tidak dicantumkan.
- **`camp_snap_camera` (`medium`):** 0 foto CC/PD lokal; bukti visual hanya 2 foto sementara (< 3) dari 1 galeri ulasan (35mmc), ditopang 4 ulasan konsisten.

### 3. Ringkasan Hal yang Tidak Ditemukan (Detail Per Kamera Tercatat di Masing-Masing Entri)

- **Tanpa halaman spesifikasi resmi produsen aktif (9):** `ilford_hp5_plus_single_use_camera`, `ilford_xp2_super_single_use_camera`, `polaroid_sun_660_autofocus`, `polaroid_spectra`, `nintendo_game_boy_camera`, `sony_ericsson_k800i`, `nokia_n95`, `nokia_n8`, `nokia_808_pureview`
- **Tanpa foto contoh tunggal berlisensi CC/PD lokal (5):** `ilford_xp2_super_single_use_camera`, `polaroid_spectra`, `fujifilm_instax_square_sq6`, `fujifilm_instax_mini_11`, `camp_snap_camera`
- **Foto contoh diperiksa visual < 3 (7):** `ilford_xp2_super_single_use_camera`, `polaroid_spectra`, `polaroid_now_gen_2`, `fujifilm_instax_wide_300`, `fujifilm_instax_square_sq6`, `fujifilm_instax_mini_11`, `camp_snap_camera`
- Rincian lengkap kekurangan per kamera (termasuk status Flickr Camera Finder dan mode khusus) tercantum pada baris **`Tidak ditemukan`** di setiap entri kamera di atas serta tabel menyeluruh di [AUDIT.md](AUDIT.md).
