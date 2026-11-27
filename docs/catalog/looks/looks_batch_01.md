# Analisis Mendalam Tampilan Foto — Batch 01 (`docs/catalog/looks/looks_batch_01.md`)

Pendalaman karakter visual kualitatif untuk **20 kamera Batch 01** dari [batch_01.md](../batch_01.md), mengacu pada daftar arketipe di [archetypes.md](../archetypes.md).

> **Metodologi & Kepatuhan Aturan Keras:**
> - **Tanpa angka parameter shader:** Seluruh deskripsi murni kualitatif (warna, kontras, perilaku sorotan/bayangan, *grain*/*noise*, kelembutan, *vignette*, *flare*, karakter *flash*). Angka yang muncul hanyalah spesifikasi perangkat keras terverifikasi (mis. ukuran sensor, panjang fokus, ISO film, kecepatan rana).
> - **Inspeksi visual langsung tanpa memasukkan foto ke repo/aplikasi:** Inspeksi visual dilakukan langsung terhadap **56 foto contoh berlisensi terbuka jelas (CC0, CC BY, CC BY-SA, Public Domain)** dari **Wikimedia Commons** (lintas pengunggah langsung, Flickr CC, Geograph, Panoramio) dan **Flickr CC (via Openverse)** yang salinan rujukan lokalnya dicatat lengkap lisensinya di `docs/references/manifest.md` (folder `docs/references/` diabaikan `.gitignore`), ditambah inspeksi visual sementara atas **foto contoh dari situs fotografi independen** (Kosmo Foto, The Darkroom, BealeCorner) untuk melengkapi kamera yang foto CC-nya terbatas (berkas sementara langsung dihapus seusai pengamatan; hanya URL halaman sumber yang disimpan). Tidak ada foto apa pun yang masuk ke repositori Git maupun `app/`.
> - **Privasi:** Seluruh foto sampel telah diperiksa visual satu per satu; foto yang menampilkan wajah yang dapat dikenali atau anak-anak telah dikeluarkan dan tidak digunakan.
> - **Pemisahan kamera film vs digital:** Untuk kamera film dan instan, sifat optik bodi/lensa/format dipisahkan secara tegas dari sifat emulsi film; jenis film pada tiap foto sampel dicatat bila diketahui. Untuk kamera digital, dicatat apakah tampilan khas berasal dari setelan bawaan (*default* JPEG) atau mode warna/simulasi film.
> - **Penamaan kanonik:** Nama merek dan tipe mengikuti `docs/catalog/batch_01.md` secara persis; alias regional/varian dicatat di kolom `aliases` dengan satu nama kanonik.

---

## 1. Canon — PowerShot SD1000 (Digital IXUS 70 / IXY Digital 10)

- **id:** `canon_powershot_sd1000`
- **brand:** `Canon`
- **model:** `PowerShot SD1000` *(nama kanonik)*
- **aliases:** `Digital IXUS 70` (Eropa/Asia), `IXY Digital 10` (Jepang)
- **displayName:** `PowerShot SD1000 (Digital IXUS 70 / IXY Digital 10)`
- **Tahun & Jenis:** 2007 · Digital compact
- **Spesifikasi Inti (Resmi):** Sensor CCD 1/2,5" 7,1 MP efektif; lensa zoom 5,8–17,4mm f/2.8–4.9 (ekuiv. 35–105mm), prosesor DIGIC III.
- **Ketergantungan Mode Warna (Digital):** Karakter nostalgia CCD muncul langsung pada **pengaturan bawaan (default JPEG / Auto)**; kamera juga menyediakan mode *My Colors* (Vivid, Positive Film, Color Accent, Neutral) yang dapat mendorong saturasi lebih tinggi, namun foto sampel yang diperiksa berasal dari JPEG bawaan tanpa olah lanjut.
- **Deskripsi Tampilan Hasil Foto (Inspeksi Visual + Sumber):**
  - **Warna & White Balance:** Di siang hari cerah, hijau dedaunan dan kuning tampil segar serta sedikit menyala; pada cahaya mendung, keseimbangan putih cenderung netral-sejuk yang rapi; di malam hari di bawah lampu jalan, rona mengarah hangat kekuningan (tungsten) dengan kuat.
  - **Kontras & Sorotan (*Highlight*):** Kontras bawaan terasa tegas dan rapat. Karena rentang dinamis sensor CCD mungil sempit, bidang terang (rumput diterpa matahari langsung, jendela bercahaya malam, lampu jalan) cepat penuh menjadi putih rata (*clipped*) dan memunculkan pendaran tipis di sekitar lampu malam.
  - **Ketajaman, Aberasi & Kelembutan:** Bagian tengah tajam dengan penajaman tepi digital khas kompak Canon akhir 2000-an, sementara tepi jauh sedikit melunak. Pada kontras tinggi ranting/pagar melawan langit terang terlihat pinggiran ungu (*purple fringing*) tipis.
  - **Noise & Cahaya Redup:** Sangat bersih pada ISO rendah di luar ruang, tetapi di malam hari cepat memunculkan bintik *noise* luminansi dan bercak warna kasar di aspal serta bayangan gelap.
  - **Vignette & Flash:** Vignette optik nyaris tidak kentara di siang hari; flash bawaan yang dekat sumbu lensa menghasilkan foto snapshot malam berkontras keras dengan subjek depan terang mencolok dan latar cepat jatuh gelap.
- **lookTags:** `high-contrast`, `punchy-daylight`, `clipped-highlights`, `digital-noise`, `harsh-flash`, `purple-fringing`
- **Arketipe:** `ccd_compact_2000s` *(dikonfirmasi)*
- **confidenceLook:** **`high`** *(terkonfirmasi pada audit ketat)* — Memenuhi syarat ketat (>= 2 domain independen [5], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa).
- **Tidak ditemukan:** Tidak ada kekurangan sumber utama (spesifikasi resmi, referensi ulasan/Camera Finder, dan >= 3 foto contoh CC/PD tersedia).
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Canon Camera Museum — PowerShot SD1000](https://global.canon/en/c-museum/product/dcc539.html)
  - *Situs Fotografi / Ulasan:* [Imaging Resource — Canon SD1000 Gallery](https://www.imaging-resource.com/cameras/canon-sd1000-review/gallery/), [DPReview — Canon SD750/SD1000](https://www.dpreview.com/articles/2520296423/canonsd750sd1000/)
  - *Flickr Camera Finder:* [Flickr — Canon PowerShot SD1000](https://www.flickr.com/cameras/canon/powershot_sd1000)
  - *Foto Sampel Diperiksa Visual (3, EXIF SOOC):*
    1. [Wikimedia Commons (Direct, CC BY-SA 3.0) — *Heiliger Sand* (EXIF: Canon DIGITAL IXUS 70, siang)](https://commons.wikimedia.org/wiki/File:%22Heiliger_Sand%22,_j%C3%BCdischer_Friedhof_in_Worms.jpg)
    2. [Wikimedia Commons (Panoramio, CC BY-SA 3.0) — *"L" Caisson at Night* (EXIF: Canon PowerShot SD1000, malam)](https://commons.wikimedia.org/wiki/File:%22L%22_Caisson_at_Night_-_panoramio.jpg)
    3. [Wikimedia Commons (Flickr, CC BY 2.0) — *"T" Ford from South Dakota* (EXIF: Canon PowerShot SD1000, mendung)](https://commons.wikimedia.org/wiki/File:%22T%22_Ford_from_South_Dakota.jpg)

---

## 2. Fujifilm — FinePix F31fd

- **id:** `fujifilm_finepix_f31fd`
- **brand:** `Fujifilm`
- **model:** `FinePix F31fd` *(nama kanonik)*
- **aliases:** `F31fd`
- **displayName:** `FinePix F31fd`
- **Tahun & Jenis:** 2006 · Digital compact
- **Spesifikasi Inti:** Sensor Super CCD HR 1/1,7" 6,1 MP efektif; lensa zoom Fujinon ekuiv. 36–108mm f/2.8–5.0. *(Halaman spesifikasi resmi produsen aktif tidak ditemukan.)*
- **Ketergantungan Mode Warna (Digital):** Tampilan alami dan butiran rapi berasal dari **mode bawaan (F-Standard JPEG)**; kamera juga memiliki mode *F-Chrome* (warna lebih jenuh ala slide) dan *B&W*, tetapi ciri khas pemisahan warna organik serta ISO tinggi bersih sudah terlihat langsung di setelan standar.
- **Deskripsi Tampilan Hasil Foto (Inspeksi Visual + Sumber):**
  - **Warna & White Balance:** Reproduksi warna tampak hidup namun tetap wajar — merah tua dan biru terang pada objek alam terpisah tegas tanpa terlihat buatan, sementara pemandangan laut mendung tampil bersih bernuansa sejuk-sian yang tenang.
  - **Kontras & Sorotan:** Kontras lebih luwes dan tidak sekeras digicam CCD saku 1/2,5" sezamannya; gradasi langit mendung dan bayangan permukaan air bertahan mulus, walau sorotan sinar matahari langsung pada batu terang atau lampu sorot malam tetap dapat penuh (*clip*) disertai pendaran lembut di sekeliling sumber lampu.
  - **Noise & Cahaya Redup:** Inilah keunggulan utamanya: pada foto lapangan malam diterangi lampu sorot, langit gelap dan rumput tetap sangat bersih dengan pola butiran halus menyerupai *grain* luminansi tipis, tanpa bercak warna digital mencolok maupun penghalusan cat air yang berlebihan.
  - **Ketajaman, Aberasi & Vignette:** Ketajaman merata dengan tekstur organik tanpa penajaman tepi yang kaku; pada tiang lampu sorot malam berkontras ekstrem muncul pinggiran ungu tipis di sekitar cahaya paling terang; vignette sangat minim.
- **lookTags:** `natural-color`, `film-like-grain`, `clean-high-iso`, `smooth-midtones`, `purple-fringing`
- **Arketipe:** `pro_ccd_filmic` *(dikonfirmasi)*
- **confidenceLook:** **`high`** *(terkonfirmasi pada audit ketat)* — Memenuhi syarat ketat (>= 2 domain independen [4], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa).
- **Tidak ditemukan:** halaman spesifikasi resmi produsen aktif.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Situs Fotografi / Ulasan:* [Imaging Resource — FinePix F31fd Gallery](https://www.imaging-resource.com/PRODS/F31FD/F31FDGALLERY.HTM), [DPReview — FinePix F31fd Specs](https://www.dpreview.com/products/fuji/compacts/fuji_finepixf31fd)
  - *Flickr Camera Finder:* [Flickr — Fujifilm FinePix F31fd](https://www.flickr.com/cameras/fujifilm/finepix_f31fd)
  - *Forum / Komunitas:* [DPReview Forum — F31fd Review](https://www.dpreview.com/forums/threads/fujifilm-finepix-f31fd-review.3408748/)
  - *Foto Sampel Diperiksa Visual (3):*
    1. [Wikimedia Commons (Direct, CC BY 3.0) — *Drosera intermedia-Enallagma* (EXIF: FinePix F31fd, firmware bawaan)](https://commons.wikimedia.org/wiki/File:-Drosera_intermedia-Enallagma.jpg)
    2. [Wikimedia Commons (Direct, CC BY-SA 3.0) — *Destroyer Jinan* (EXIF: FinePix F31fd, mendung)](https://commons.wikimedia.org/wiki/File:051_Guided_missile_destroyer_Jinan_20080711.jpg)
    3. [Flickr CC via Openverse (CC BY 2.0) — *Hockey Field By Night* oleh Piano Piano! (malam)](https://www.flickr.com/photos/11076844@N00/1878323080)

---

## 3. Sony — Cyber-shot DSC-T70

- **id:** `sony_cyber_shot_dsc_t70`
- **brand:** `Sony`
- **model:** `Cyber-shot DSC-T70` *(nama kanonik)*
- **aliases:** `DSC-T70`, `Sony T70`
- **displayName:** `Cyber-shot DSC-T70`
- **Tahun & Jenis:** 2007 · Digital compact (lensa periskop lipat)
- **Spesifikasi Inti (Resmi):** Sensor Super HAD CCD 1/2,5" 8,1 MP efektif; lensa periskop Carl Zeiss Vario-Tessar 3x zoom (ekuiv. 38–114mm f/3.5–4.3), Optical Super SteadyShot.
- **Ketergantungan Mode Warna (Digital):** Karakter lembut-hangat dalam ruang serta gaya snapshot flash pekat berasal dari **pengaturan bawaan (Auto/Normal JPEG)**; kamera menyediakan mode warna *Vivid/Natural/Sepia/B&W* dan *Scene Selection*, namun ciri optik periskop dan peredaman *noise* tampak langsung pada mode standar.
- **Deskripsi Tampilan Hasil Foto (Inspeksi Visual + Sumber):**
  - **Warna & White Balance:** Di bawah penerangan interior pijar, keseimbangan putih otomatis membiarkan nuansa kuning-amber yang sangat hangat dan akrab; saat memakai *flash* langsung pada pameran dalam ruang, warna putih bodi dan lampu merah tampil cerah menyala serta kontras.
  - **Kelembutan & Peredaman Noise:** Konstruksi optik lipat tipis ditambah peredaman *noise* bawaan di cahaya redup membuat tekstur halus (dinding plester, serat kayu) tampak sedikit lembut dan licin ketimbang tajam klinis, memberi kesan *dreamy* lembut pada interior.
  - **Sorotan & Bloom:** Lampu sorot plafon dan pantulan kilap di bodi mobil cepat penuh ke putih dengan pendaran (*bloom*) krem lembut di tepinya.
  - **Flash & Vignette:** Flash langsung bawaan memberi kontras depan-belakang yang tajam khas digicam pesta era 2007 (subjek dekat terang mengilap, latar belakang segera gelap pekat), disertai sedikit pelunakan di sudut bingkai.
- **lookTags:** `warm-indoor`, `soft-detail`, `noise-smoothing`, `vivid-flash`, `highlight-bloom`
- **Arketipe:** `ccd_dreamy_pastel` *(dikonfirmasi)*
- **confidenceLook:** **`high`** *(terkonfirmasi pada audit ketat)* — Memenuhi syarat ketat (>= 2 domain independen [6], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa).
- **Tidak ditemukan:** halaman Flickr Camera Finder (404/500 saat diperiksa).
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Sony Support — DSC-T70 Specifications](https://www.sony.co.id/en/electronics/support/compact-cameras-dsct-series/dsc-t70/specifications)
  - *Situs Fotografi / Ulasan:* [Imaging Resource — Sony DSC-T70 Gallery](https://www.imaging-resource.com/PRODS/T70/T70GALLERY.HTM), [DPReview — Sony T200/T70](https://www.dpreview.com/articles/6948762998/sonyt200t70/), [PBase — Sony DSC-T70](https://www.pbase.com/cameras/sony/cyber-shot_dsc_t70)
  - *Foto Sampel Diperiksa Visual (3):*
    1. [Wikimedia Commons (Direct, CC BY-SA 3.0) — *"SARCH"* (EXIF: DSC-T70, interior hangat)](https://commons.wikimedia.org/wiki/File:%22SARCH%22.JPG)
    2. [Wikimedia Commons (Flickr, CC BY 2.0) — *card catalog* (EXIF: DSC-T70, interior)](https://commons.wikimedia.org/wiki/File:2009_4041502095_card_catalog.jpg)
    3. [Flickr CC via Openverse (CC BY-SA 2.0) — *Lamborghini Murciélago LP640 Rear* oleh Michi1308 (flash langsung)](https://www.flickr.com/photos/22256255@N05/2234797788)

---

## 4. Leica — Leica M9

- **id:** `leica_m9`
- **brand:** `Leica`
- **model:** `Leica M9` *(nama kanonik)*
- **aliases:** `M9` *(catatan: Leica M9-P berbagi sensor Kodak KAF-18500 CCD dan jalur gambar identik)*
- **displayName:** `Leica M9`
- **Tahun & Jenis:** 2009 · Digital rangefinder (Full-Frame CCD)
- **Spesifikasi Inti (Resmi):** Sensor full-frame 24×36mm Kodak KAF-18500 CCD 18 MP tanpa filter *anti-aliasing* (low-pass); dudukan lensa Leica M (lensa dapat diganti).
- **Ketergantungan Mode Warna (Digital):** Karakter warna pekat organik merupakan sifat dasar **sensor Kodak CCD pada profil warna bawaan (Standard JPEG maupun DNG dasar)** tanpa memerlukan simulasi film khusus; karakter ketajaman tepi, vignette, dan bokeh turut dipengaruhi lensa M yang dipasang.
- **Deskripsi Tampilan Hasil Foto (Inspeksi Visual + Sumber):**
  - **Warna & Separasi Tonal:** Warna terasa padat, matang, dan bergaya lukisan/film — biru tua-toska pada cat logam, kuning oker dinding, merah muda berdebu, merah menyala, serta hijau daun dalam ruang tampil kaya dan dalam tanpa kesan neon digital berlebihan.
  - **Ketajaman & Kontras Mikro:** Ketiadaan filter AA membuat detail tekstur tembok, pipa, batu jalan, dan urat daun tampak sangat tajam per piksel dengan kontras mikro kuat, namun tetap organik tanpa lingkaran penajaman digital kasar.
  - **Sorotan, Bayangan & Bokeh:** Bayangan terasa berisi dan berbobot, sementara sorotan terang pada pantulan krom di siang hari tetap dapat penuh (*clip*) lebih cepat dibanding sensor CMOS modern. Luruh latar (*bokeh*) khas full-frame terasa sangat lembut dan mulus.
  - **Noise & Flash:** Sangat bersih di ISO dasar, namun menurut ulasan independen cepat memunculkan *chroma noise* dan pergeseran warna pada ISO tinggi; bodi tidak memiliki flash bawaan.
- **lookTags:** `crisp-micro-contrast`, `rich-ccd-color`, `filmic-midtones`, `deep-shadows`, `smooth-bokeh`
- **Arketipe:** `pro_ccd_filmic` *(dikonfirmasi)*
- **confidenceLook:** **`medium`** *(diaudit ketat dari `high` → `medium`)* — Kamera digital berlensa tukar (mount Leica M); karakter optik (vignette, bokeh, flare) bervariasi antar lensa sampel.
- **Tidak ditemukan:** Tidak ada kekurangan sumber utama (spesifikasi resmi, referensi ulasan/Camera Finder, dan >= 3 foto contoh CC/PD tersedia).
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Leica Technical Data PDF — Leica M9 / M9-P](https://leica-camera.com/sites/default/files/pm-29341-Leica-M-9--M-9-P_Technical-Data_en.pdf)
  - *Situs Fotografi / Ulasan:* [Steve Huff Photo — Leica M9 Review](https://www.stevehuffphoto.com/2010/08/04/my-updated-big-bad-ass-leica-m9-review/), [Imaging Resource — Leica M9-P Gallery (sensor sama)](https://www.imaging-resource.com/PRODS/M9P/M9PGALLERY.HTM), [DPReview — Leica M9](https://www.dpreview.com/articles/3013742516/leicam9/)
  - *Flickr Camera Finder:* [Flickr — Leica M9](https://www.flickr.com/cameras/leica/m9)
  - *Foto Sampel Diperiksa Visual (3):*
    1. [Wikimedia Commons (Flickr, CC BY 2.0) — *Alfa Romeo* (EXIF: M9 Digital Camera)](https://commons.wikimedia.org/wiki/File:Alfa_Romeo_(14502944080).jpg)
    2. [Wikimedia Commons (Flickr, CC BY 2.0) — *Alley off Tan Quee Lan Street Singapore* (EXIF: M9 Digital Camera, firmware 1.002 SOOC)](https://commons.wikimedia.org/wiki/File:Alley_off_Tan_Quee_Lan_Street_Singapore.jpg)
    3. [Flickr CC via Openverse (CC BY 2.0) — *Leica M9 Sample* oleh bfishadow (interior)](https://www.flickr.com/photos/61368956@N00/3909101094)

---

## 5. Ricoh — Ricoh GR III

- **id:** `ricoh_gr_iii`
- **brand:** `Ricoh`
- **model:** `Ricoh GR III` *(nama kanonik)*
- **aliases:** `GR III`, `GR3` *(catatan: jangan tertukar dengan Ricoh GR Digital III 2009 bersensor CCD kecil)*
- **displayName:** `Ricoh GR III`
- **Tahun & Jenis:** 2019 · Digital compact APS-C
- **Spesifikasi Inti (Resmi):** Sensor APS-C CMOS 24,24 MP tanpa filter AA dengan 3-axis SR; lensa tetap GR 18,3mm f/2.8 (ekuiv. 28mm, 6 elemen dalam 4 grup).
- **Ketergantungan Mode Warna (Digital):** **Sangat bergantung pada profil *Image Control* bawaan.** Pada setelan *Standard* bawaan (terlihat pada 3 foto SOOC yang diperiksa), foto sangat tajam, bersih, dan berentang dinamis lebar; sementara gaya jalanan paling populernya lahir dari profil *Positive Film* (warna pekat kontras ala slide dengan bayangan dalam) serta *Hi-Contrast B&W* (monokrom pekat berbutir kasar).
- **Deskripsi Tampilan Hasil Foto (Inspeksi Visual + Sumber):**
  - **Ketajaman & Geometri:** Ketajaman lensa 28mm ekuiv. terasa luar biasa rata dari tengah hingga sudut ekstrem (serat cat dinding, batu, ranting tipis, hingga ladang jauh), dengan garis vertikal lurus nyaris tanpa distorsi barel mencolok.
  - **Warna & Kontras:** Pada mode standar, warna tampil bersih dan berenergi (kuning dinding pekat, hijau ilalang segar, biru langit jernih) disertai kontras mikro yang menggigit namun tidak kasar.
  - **Sorotan, Bayangan & Noise:** Rentang dinamis sensor APS-C modern sangat lebar sehingga awan terang, dinding tersorot matahari, dan bayangan batu/air tetap menyimpan detail baik tanpa mudah terbakar; noise di ISO dasar nyaris tak terlihat.
  - **Vignette & Flash:** Vignette optik sangat ringan pada bukaan normal; kamera ini tidak memiliki flash bawaan.
- **lookTags:** `razor-sharp`, `micro-contrast`, `wide-dr`, `punchy-slide`, `gritty-bw`
- **Arketipe:** `early_cmos_gritty` *(dikonfirmasi — catatan: sensor modern APS-C, namun tetap cocok pada arketipe street berkontras tegas karena peran utama profil Positive Film & Hi-Contrast B&W)*
- **confidenceLook:** **`high`** *(terkonfirmasi pada audit ketat)* — Memenuhi syarat ketat (>= 2 domain independen [5], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa).
- **Tidak ditemukan:** Tidak ada kekurangan sumber utama (spesifikasi resmi, referensi ulasan/Camera Finder, dan >= 3 foto contoh CC/PD tersedia).
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Galeri & Resmi Produsen:* [Ricoh Imaging — GR III Features & Gallery](https://www.ricoh-imaging.co.jp/english/products/gr-3/)
  - *Situs Fotografi / Ulasan:* [Imaging Resource — Ricoh GR III Gallery](https://www.imaging-resource.com/cameras/ricoh-gr-iii-review/gallery/), [DPReview — Ricoh GR III Review](https://www.dpreview.com/reviews/8690273264/ricoh-gr-iii/)
  - *Flickr Camera Finder:* [Flickr — Ricoh GR III](https://www.flickr.com/cameras/ricoh/gr_iii)
  - *Forum / Komunitas:* [DPReview Forum — GR III SOOC Hi-Contrast B&W](https://www.dpreview.com/forums/threads/gr-iii-sooc-with-high-contrast-b-w.4379111/)
  - *Foto Sampel Diperiksa Visual (3, EXIF SOOC):*
    1. [Wikimedia Commons (Flickr, CC BY 2.0) — *Berkeley storefront* (EXIF: GRiii SOOC)](https://commons.wikimedia.org/wiki/File:2021-68_-_Flickr_-_biosfear.jpg)
    2. [Wikimedia Commons (Direct, CC BY-SA 4.0) — *Wagnersee bei Pusselsheim 7* (EXIF: RICOH GR III SOOC)](https://commons.wikimedia.org/wiki/File:Wagnersee_bei_Pusselsheim_7.jpg)
    3. [Wikimedia Commons (Direct, CC BY-SA 4.0) — *Blick auf Vorbach von Südosten* (EXIF: RICOH GR III SOOC)](https://commons.wikimedia.org/wiki/File:Blick_auf_Vorbach_von_S%C3%BCdosten.jpg)

---

## 6. Fujifilm — Fujifilm X100V

- **id:** `fujifilm_x100v`
- **brand:** `Fujifilm`
- **model:** `Fujifilm X100V` *(nama kanonik)*
- **aliases:** `X100V`
- **displayName:** `Fujifilm X100V`
- **Tahun & Jenis:** 2020 · Digital fixed-lens compact APS-C
- **Spesifikasi Inti (Resmi):** Sensor APS-C X-Trans CMOS 4 26,1 MP; lensa tetap Fujinon 23mm f/2 II (ekuiv. 35mm), flash bawaan dan filter ND internal.
- **Ketergantungan Mode Warna (Digital):** **Sangat bergantung pada *Film Simulation* dan resep JPEG kustom.** Pada *Provia/Standard* bawaan (sampel 1–2), gambar tampil bersih, tajam, dan bergradasi pastel-alami halus; sedangkan dengan simulasi *Classic Negative*, *Classic Chrome*, *Acros*, atau resep komunitas (terlihat jelas pada sampel 3 bertag `fujiweekly`), bayangan terangkat *matte*, sorotan menghangat krem, dan warna menjadi kalem-nostalgia.
- **Deskripsi Tampilan Hasil Foto (Inspeksi Visual + Sumber):**
  - **Warna & Gradasi:** Pada setelan standar, biru, hijau, dan gradasi langit senja merah muda-lavender tampil sangat halus tanpa pita warna (*banding*) dengan warna yang hidup namun sopan; pada resep simulasi film, warna bergeser hangat krem dengan rona papan dan cat yang lembut (*muted*).
  - **Kontras, Sorotan & Bayangan:** Rentang dinamis luas dengan pelunakan sorotan (*highlight roll-off*) yang mulus bahkan di cahaya senja atau bidang putih terang; kontras sangat lentur mengikuti simulasi film yang dipilih.
  - **Ketajaman & Grain:** Lensa 23mm f/2 generasi II sangat tajam hingga tepi tanpa kabut dekat (*close-up glow*) seberat X100 generasi awal; sensor X-Trans sangat bersih dan mampu menambahkan efek *Grain* halus organik secara bawaan.
  - **Vignette & Flash:** Vignette optik sangat tipis; flash bawaan kecil dapat dipadukan rana daun (*leaf shutter*) untuk *fill flash* siang hari yang rapi.
- **lookTags:** `sharp`, `film-sim`, `smooth-rolloff`, `muted-warm`, `wide-dr`, `fine-grain`
- **Arketipe:** `filmic_digital_mirrorless` *(dikonfirmasi)*
- **confidenceLook:** **`high`** *(terkonfirmasi pada audit ketat)* — Memenuhi syarat ketat (>= 2 domain independen [5], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa).
- **Tidak ditemukan:** Tidak ada kekurangan sumber utama (spesifikasi resmi, referensi ulasan/Camera Finder, dan >= 3 foto contoh CC/PD tersedia).
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Fujifilm X Official — X100V Product Page](https://www.fujifilm-x.com/global/products/cameras/x100v/), [Fujifilm X News — Introducing X100V](https://www.fujifilm-x.com/global/news/introducing-the-fujifilm-x100v/)
  - *Situs Fotografi / Ulasan:* [Imaging Resource — Fujifilm X100V Gallery](https://www.imaging-resource.com/cameras/fujifilm-x100v-review/gallery/), [DPReview — Fujifilm X100V Review](https://www.dpreview.com/reviews/7990558355/fujifilm-x100v-review/)
  - *Flickr Camera Finder:* [Flickr — Fujifilm X100V](https://www.flickr.com/cameras/fujifilm/x100v)
  - *Foto Sampel Diperiksa Visual (3):*
    1. [Wikimedia Commons (Flickr, CC BY 2.0) — *Abandoned pedestrian sidewalk sign in Finland* (EXIF: X100V Ver1.00 SOOC)](https://commons.wikimedia.org/wiki/File:Abandoned_pedestrian_sidewalk_sign_in_Finland,_2020.jpg)
    2. [Wikimedia Commons (Direct, CC0) — *Bennetts Beach Hawks Nest* (EXIF: X100V SOOC)](https://commons.wikimedia.org/wiki/File:Bennetts_Beach_Hawks_Nest_captured_by_Kate_Branch.jpg)
    3. [Flickr CC via Openverse (CC BY-SA 2.0) — *Travels w/ my Fujifilm X100V* oleh DPK_Photography (resep Film Sim / fujiweekly)](https://www.flickr.com/photos/55208341@N06/52425515845)

---

## 7. Canon — Canon AE-1

- **id:** `canon_ae_1`
- **brand:** `Canon`
- **model:** `Canon AE-1` *(nama kanonik)*
- **aliases:** `AE-1` *(bukan Canon AE-1 Program 1981, meski memakai sistem dudukan lensa Canon FD yang sama)*
- **displayName:** `Canon AE-1`
- **Tahun & Jenis:** 1976 · Film SLR 35mm
- **Spesifikasi Inti (Resmi):** Film 35mm (bingkai 24×36mm, rasio 3:2); dudukan bayonet Canon FD (umum dipasangkan lensa standar FD 50mm f/1.8 atau f/1.4 S.S.C.); rana kain horizontal 2s–1/1000s, prioritas rana (*shutter-priority AE*).
- **Pemisahan Sifat Kamera/Lensa vs Emulsi Film (WAJIB FILM):**
  - **Sifat Bodi & Lensa (Canon AE-1 + optik Canon FD):** Bodi kamera tidak memberi warna sendiri. Dari inspeksi visual 3 sampel lintas era, pasangan bodi AE-1 dan optik FD klasik menghasilkan bingkai 3:2 lurus (*rectilinear*) tanpa distorsi barel kentara, ketajaman tengah yang bersih dan merata pada bukaan sedang, kontras optik seimbang khas pelapisan kaca akhir 1970-an, luruh latar lembut pada bukaan lebar, serta vignette optik sangat minim. Tanpa flash bawaan (mengandalkan hot-shoe eksternal).
  - **Sifat Emulsi Film (pada sampel yang diperiksa):**
    - Sampel 1 (Toyota Celica Supra, ~1998 — *jenis film negatif warna tidak dicantumkan*): rona hangat kemerahan-magenta dan butiran sedang berasal dari emulsi/pemindaian cetak 1990-an.
    - Sampel 2 (Pelabuhan Boston 1980 — *jenis film negatif warna tidak dicantumkan*): nuansa sejuk-kelabu pada langit mendung serta butiran halus-sedang berasal dari emulsi dan pemindaian arsip.
    - Sampel 3 (Lokomotif Saltley 1987 — **Kodacolor Gold 100**, dipotret dengan Canon AE-1 Program): kuning cerah hangat, biru tua agak teduh, dan butiran halus ISO 100 sepenuhnya sifat Kodacolor Gold 100.
- **lookTags:** `classic-film`, `clean-geometry`, `balanced-contrast`, `sharp-center`, `shallow-dof`
- **Arketipe:** `classic_35mm_slr` *(dikonfirmasi)*
- **confidenceLook:** **`medium`** *(terkonfirmasi pada audit ketat)* — Contoh campur: bodi kamera film berlensa tukar dengan lensa dan emulsi film berbeda-beda/sebagian tidak tercatat.
- **Tidak ditemukan:** halaman Flickr Camera Finder khusus model ini tidak terverifikasi/tidak digunakan.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Canon Camera Museum — AE-1](https://global.canon/en/c-museum/product/film93.html)
  - *Situs Fotografi / Arsip:* [MIR Archive — Canon AE-1 Specs](http://www.mir.com.my/rb/photography/companies/canon/fdresources/SLRs/ae1/spec.htm), [Shoot It With Film — Canon AE-1 Program Review](https://shootitwithfilm.com/canon-ae-1-program-review/), [Lomography Gallery — Canon AE-1](https://www.lomography.com/cameras/3315294-canon-ae-1/photos)
  - *Foto Sampel Diperiksa Visual (3):*
    1. [Wikimedia Commons (Direct, Public Domain) — *1985 Toyota Celica Supra* (film: tidak dicantumkan)](https://commons.wikimedia.org/wiki/File:1985_Toyota_Celica_Supra.jpg)
    2. [Wikimedia Commons (Direct, CC BY-SA 4.0) — *Stone & Webster headquarters 1980* (EXIF: AE-1; film: tidak dicantumkan)](https://commons.wikimedia.org/wiki/File:Stone_%26_Webster_headquarters.jpg)
    3. [Wikimedia Commons (Flickr, CC BY 2.0) — *Saltley 1987* (EXIF: Canon AE1 Program; **film: Kodacolor Gold 100**)](https://commons.wikimedia.org/wiki/File:20177,_20045_%26_47587_at_Saltley_(5482915895).jpg)

---

## 8. Nikon — Nikon FM2

- **id:** `nikon_fm2`
- **brand:** `Nikon`
- **model:** `Nikon FM2` *(nama kanonik)*
- **aliases:** `FM2`, `Nikon FM2n` / `New FM2` *(revisi 1984 dengan sinkronisasi flash 1/250s)*
- **displayName:** `Nikon FM2`
- **Tahun & Jenis:** 1982 · Film SLR 35mm mekanis
- **Spesifikasi Inti (Resmi):** Film 35mm (24×36mm, rasio 3:2); dudukan Nikon F (umum dipasangkan Nikkor 50mm f/1.4 atau f/1.8 AI-S); rana mekanis vertikal hingga 1/4000 detik.
- **Pemisahan Sifat Kamera/Lensa vs Emulsi Film (WAJIB FILM):**
  - **Sifat Bodi & Lensa (Nikon FM2 + optik Nikkor F AI-S):** Bodi sepenuhnya mekanis dan netral. Dari inspeksi visual 3 sampel, kombinasi bodi FM2 dan kaca Nikkor menghasilkan ketajaman arsitektur sangat rapi dari tengah hingga tepi (bata menara, kubah gereja, huruf papan toko), garis bangunan lurus nyaris bebas distorsi, kontrol *flare* baik di sinar matahari langsung, serta vignette minimal. Pada bukaan lebar malam hari (Nikkor 50mm f/1.4S), terlihat pendaran sferis tipis di sekitar lentera terang dan pemisahan latar mulus. Tidak memiliki flash bawaan.
  - **Sifat Emulsi Film (pada sampel yang diperiksa):**
    - Sampel 1 (Gang malam Osaka — **Nikon AI Nikkor 50mm f/1.4S + Kodak UltraMax 400**): merah/kuning neon sangat pekat, bayangan gelap dalam, dan *grain* kasar terlihat jelas sebagai karakter Kodak UltraMax 400 di cahaya rendah.
    - Sampel 2 (Biara Grottaferrata, Italia — *jenis film tidak dicantumkan*): gradasi bata merah-hangat dan langit biru lembut dengan *grain* halus berasal dari film warna siang hari.
    - Sampel 3 (Katedral Novocherkassk — *jenis film tidak dicantumkan*): langit biru pekat dan bayangan tegas berasal dari film/pemindaian. *(Catatan: ulasan 35mmc juga menguji FM2 + 50mm f/1.8 AI-S pada Kodak Portra 400, Kodak Gold 200, dan CineStill 400D dengan catatan ketajaman optik serupa.)*
- **lookTags:** `neutral-body`, `sharp-glass`, `clean-contrast`, `minimal-distortion`, `smooth-bokeh`
- **Arketipe:** `classic_35mm_slr` *(dikonfirmasi)*
- **confidenceLook:** **`medium`** *(terkonfirmasi pada audit ketat)* — Contoh campur: bodi kamera film berlensa tukar dengan lensa dan emulsi film berbeda-beda/sebagian tidak tercatat.
- **Tidak ditemukan:** halaman Flickr Camera Finder khusus model ini tidak terverifikasi/tidak digunakan.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Nikon Museum — Nikon FM2](https://www.nikonmuseum.com/post/nikon-fm2)
  - *Situs Fotografi / Ulasan:* [35mmc — Nikon FM2 Camera Review](https://www.35mmc.com/28/12/2022/nikon-fm2-camera-review/)
  - *Foto Sampel Diperiksa Visual (3):*
    1. [Wikimedia Commons (Flickr, CC BY 2.0) — *Dotonbori night* (**lensa: AI Nikkor 50mm f/1.4S; film: Kodak UltraMax 400**)](https://commons.wikimedia.org/wiki/File:2%E7%95%AA%E7%9B%AE%E3%81%AB_(22035861012).jpg)
    2. [Wikimedia Commons (Direct, Public Domain) — *Abbazia Grottaferrata02* (film: tidak dicantumkan)](https://commons.wikimedia.org/wiki/File:Abbazia_Grottaferrata02.jpg)
    3. [Wikimedia Commons (Direct, Public Domain) — *Church of the Ascension of Christ, Novocherkassk* (film: tidak dicantumkan)](https://commons.wikimedia.org/wiki/File:Church_of_the_Ascension_of_Christ_(Novocherkassk)_001.jpg)

---

## 9. Leica — Leica M6

- **id:** `leica_m6`
- **brand:** `Leica`
- **model:** `Leica M6` *(nama kanonik)*
- **aliases:** `M6 Classic`, `Leica M6 TTL` *(1998)*, `Leica M6 (Reissue 2022)`
- **displayName:** `Leica M6`
- **Tahun & Jenis:** 1984 · Film rangefinder 35mm
- **Spesifikasi Inti (Resmi):** Film 35mm (24×36mm, rasio 3:2); dudukan bayonet Leica M (sering dipasangkan Summicron-M 35mm f/2, Summarit 35mm f/2.5, atau lensa M pihak ketiga); rana kain mekanis 1s–1/1000s.
- **Pemisahan Sifat Kamera/Lensa vs Emulsi Film (WAJIB FILM):**
  - **Sifat Bodi & Lensa (Leica M6 + lensa mount M):** Bodi mekanis tidak menambahkan warna. Bersama lensa rangefinder M (seperti Summarit 35mm f/2.5 pada sampel 2 dan Nokton 40mm pada sampel 3), terlihat ketajaman tinggi hingga ke pinggir bingkai pada bidang fokus (batu jalan, jeruji besi, tiang kapal), kontras mikro tegas, distorsi geometris nyaris nihil, *veiling flare* lembut saat berhadapan langsung dengan langit terang di sudut bingkai, serta vignette sudut halus pada bukaan lebar. Tidak memiliki flash bawaan.
  - **Sifat Emulsi Film (pada sampel yang diperiksa):**
    - Sampel 1 (Mural & skuter Taichung — *jenis film warna tidak dicantumkan*): hangat sore hari dan kontras warna jenuh berasal dari film warna + cahaya matahari rendah.
    - Sampel 2 (Gang batu berjeruji — **Summarit 35mm f/2.5 + Kodak Tri-X 400**): skala monokrom berkontras tegas dan butiran *grain* bergaya jurnalistik berasal dari Kodak Tri-X 400.
    - Sampel 3 (Pelabuhan senja — **Nokton 40mm + CineStill 800T**, M6 TTL): rona dingin biru-sian menyeluruh dan pendaran tipis pada lampu pelabuhan berasal dari film tungsten CineStill 800T, bukan bodi M6. *(Ulasan Shoot It With Film juga menguji M6 dengan Kodak Portra 400 & Portra 800.)*
- **lookTags:** `micro-contrast`, `edge-sharpness`, `clean-geometry`, `subtle-flare`, `smooth-midtones`
- **Arketipe:** `classic_35mm_slr` *(dikonfirmasi)*
- **confidenceLook:** **`medium`** *(terkonfirmasi pada audit ketat)* — Contoh campur: bodi kamera film berlensa tukar dengan lensa dan emulsi film berbeda-beda/sebagian tidak tercatat.
- **Tidak ditemukan:** halaman Flickr Camera Finder khusus model ini tidak terverifikasi/tidak digunakan.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Leica Camera — Technical Specs Leica M6](https://leica-camera.com/fr-CH/technical-specs-leica-m6)
  - *Situs Fotografi / Ulasan:* [Shoot It With Film — Leica M6 Review](https://shootitwithfilm.com/leica-m6-35mm-film-camera-review/), [Red Dot Camera — Leica M6](https://reddotcamera.net/leica/m6/)
  - *Foto Sampel Diperiksa Visual (3):*
    1. [Wikimedia Commons (Flickr, CC BY 2.0) — *A Graffiti in Taichung* (EXIF: M6; film: tidak dicantumkan)](https://commons.wikimedia.org/wiki/File:A_Graffiti_in_Taichung.jpg)
    2. [Wikimedia Commons (Flickr, CC BY 2.0) — *Abandoned* (**lensa: Summarit 35mm f/2.5; film: Kodak Tri-X 400**)](https://commons.wikimedia.org/wiki/File:Abandoned_(43450058494).jpg)
    3. [Flickr CC via Openverse (CC BY 2.0) — *Leica M6 TTL Cinestill 800 Nokton 40mm* oleh Shiptorch (**lensa: Nokton 40mm; film: CineStill 800T**)](https://www.flickr.com/photos/70121625@N00/16163564829)

---

## 10. Contax (Kyocera) — Contax T2

- **id:** `contax_t2`
- **brand:** `Contax (Kyocera)`
- **model:** `Contax T2` *(nama kanonik)*
- **aliases:** `T2`
- **displayName:** `Contax T2`
- **Tahun & Jenis:** 1990 · Premium point-and-shoot 35mm
- **Spesifikasi Inti:** Film 35mm (rasio 3:2); lensa tetap **Carl Zeiss Sonnar 38mm f/2.8 T*** (5 elemen dalam 4 grup), bodi titanium, flash langsung bawaan. *(Halaman spesifikasi resmi produsen aktif tidak ditemukan.)*
- **Pemisahan Sifat Kamera/Lensa vs Emulsi Film (WAJIB FILM):**
  - **Sifat Bodi & Lensa Tetap (Zeiss Sonnar 38mm f/2.8 T* + flash bawaan):** Karena lensanya tetap, karakter optik sangat konsisten di semua sampel: ketajaman dan kontras mikro luar biasa tinggi untuk ukuran kamera saku (urat karat gerbang besi, ukiran atap emas, paku keling jembatan baja terbaca sangat tegas hingga tepi), distorsi geometris nyaris tak terasa pada bingkai lurus, pengendalian *flare* sangat bersih pada pantulan logam berkat lapisan multi-coating Zeiss T*, serta sedikit penggelapan alami tipis di sudut paling ujung. Menurut ulasan fotografi independen, flash langsung bawaannya kuat dan tegas, memberi gaya snapshot 90-an dengan subjek depan cerah dan latar cepat menggelap.
  - **Sifat Emulsi Film (pada sampel yang diperiksa):** Ketiga sampel CC yang diperiksa (gerbang merah Taichung, kuil emas Kaohsiung, mural jembatan beton Taichung) dipotret pada film negatif warna 35mm (*jenis emulsi spesifik tidak dicantumkan pada deskripsi pengunggah*); karenanya rona jingga-merah pekat, emas hangat, dan butiran film moderat diatribusikan ke kombinasi emulsi negatif warna + pemindaian, bukan semata lensa Sonnar.
- **lookTags:** `sharp`, `micro-contrast`, `clean-flare`, `subtle-vignette`, `direct-flash`, `90s-snapshot`
- **Arketipe:** `premium_point_shoot_35mm` *(dikonfirmasi)*
- **confidenceLook:** **`medium`** *(diaudit ketat dari `high` → `medium`)* — Contoh campur emulsi film: lensa tetap konsisten secara optik, namun foto contoh memakai jenis film berbeda atau tidak dicantumkan.
- **Tidak ditemukan:** halaman spesifikasi resmi produsen aktif; halaman Flickr Camera Finder khusus model ini tidak terverifikasi/tidak digunakan.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Situs Fotografi / Ulasan:* [MrLeica — Contax T2 Review](https://mrleica.com/contax-t2-review/), [A Fist Full of Bolts — Contax T2 Review](https://afistfullofbolts.com/film/contax-t2-review)
  - *Komunitas / Galeri:* [Lomography Gallery — Contax T2](https://www.lomography.com/cameras/3333919-contax-t2/photos)
  - *Foto Sampel Diperiksa Visual (3):*
    1. [Wikimedia Commons (Flickr, CC BY 2.0) — *A Gate in Taichung* (EXIF: T2; film: negatif warna tidak dicantumkan)](https://commons.wikimedia.org/wiki/File:A_Gate_in_Taichung.jpg)
    2. [Wikimedia Commons (Flickr, CC BY 2.0) — *Tiantai Temple Kaohsiung Taiwan* (EXIF: T2; film: negatif warna tidak dicantumkan)](https://commons.wikimedia.org/wiki/File:Tiantai_Temple_Kaohsiung_Taiwan.jpeg)
    3. [Wikimedia Commons (Flickr, CC BY 2.0) — *Railway bridges over Guoguang Rd.* (EXIF: T2; film: negatif warna tidak dicantumkan)](https://commons.wikimedia.org/wiki/File:Railway_bridges_over_the_Guoguang_Rd.,_Taichung_20090517.jpg)

---

## 11. Olympus — Olympus µ[mju:]-II (Stylus Epic)

- **id:** `olympus_mju_ii`
- **brand:** `Olympus`
- **model:** `Olympus µ[mju:]-II` *(nama kanonik)*
- **aliases:** `Olympus Stylus Epic` (Amerika Utara), `Olympus mju II`, `Olympus µ-II`
- **displayName:** `Olympus µ[mju:]-II (Stylus Epic)`
- **Tahun & Jenis:** 1997 · Point-and-shoot 35mm
- **Spesifikasi Inti:** Film 35mm (rasio 3:2); lensa tetap **Zuiko 35mm f/2.8** (4 elemen dalam 4 grup), autofokus inframerah multi-spot, flash bawaan termasuk mode *Night Scene* (slow-sync). *(Halaman spesifikasi resmi produsen aktif tidak ditemukan.)*
- **Pemisahan Sifat Kamera/Lensa vs Emulsi Film (WAJIB FILM):**
  - **Sifat Bodi & Lensa Tetap (Zuiko 35mm f/2.8 + flash bawaan):** Ketajaman tengah sangat menggigit untuk lensa 4 elemen (sampel lokomotif museum dalam ruang), disertai pelunakan ringan menuju tepi jauh. **Vignette optik di keempat sudut** terlihat nyata pada langit/salju terang dan adegan bukaan lebar (f/2.8). Saat menghadap matahari rendah atau lampu neon malam di f/2.8, optik menghasilkan pendaran lembut (*glow/flare*) di sekitar sumber cahaya terang. **Flash langsung bawaan** (terlihat jelas pada sampel 1) menghasilkan pantulan kilap tajam pada subjek depan dengan kontras tegas.
  - **Sifat Emulsi Film (pada sampel yang diperiksa):**
    - Sampel 1 (Museum lokomotif, flash — *film negatif warna tidak dicantumkan*): rona krem hangat di dinding dan butiran halus-sedang berasal dari film cetak 2002.
    - Sampel 2 (Neon malam "JUBILAT", Kraków — *film negatif warna tidak dicantumkan*): pendaran oranye/merah neon dan bayangan malam pekat adalah paduan cahaya malam + film ISO tinggi.
    - Sampel 3 (Pohon musim dingin bersalju — **film: dm Paradies** sesuai tag pengunggah): pergeseran warna biru-toska dingin menyeluruh berasal dari emulsi/pemindaian dm Paradies, bukan lensa Zuiko. *(Ulasan Shoot It With Film menguji kamera ini dengan Kodak UltraMax 400, ColorPlus, dan Portra 400.)*
- **lookTags:** `sharp-center`, `corner-vignette`, `harsh-flash`, `high-contrast`, `night-glow`
- **Arketipe:** `premium_point_shoot_35mm` *(dikonfirmasi)*
- **confidenceLook:** **`medium`** *(diaudit ketat dari `high` → `medium`)* — Contoh campur emulsi film: lensa tetap konsisten secara optik, namun foto contoh memakai jenis film berbeda atau tidak dicantumkan.
- **Tidak ditemukan:** halaman spesifikasi resmi produsen aktif; halaman Flickr Camera Finder khusus model ini tidak terverifikasi/tidak digunakan.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Situs Fotografi / Ulasan:* [Shoot It With Film — Olympus Stylus Epic Review](https://shootitwithfilm.com/olympus-stylus-epic-review/), [Casual Photophile — Olympus mju-II Review](https://casualphotophile.com/2018/09/27/olympus-mju-ii-film-camera-review/)
  - *Komunitas / Galeri:* [Lomography Gallery — Olympus mju-II](https://www.lomography.com/cameras/3324333-olympus-mju-ii/photos)
  - *Foto Sampel Diperiksa Visual (3):*
    1. [Wikimedia Commons (Direct, Public Domain) — *Fell Engine Museum 2002* (flash interior; film: tidak dicantumkan)](https://commons.wikimedia.org/wiki/File:Fell_Engine_Museum_-_2002-03-20.jpg)
    2. [Wikimedia Commons (Direct, CC BY-SA 4.0) — *Jubilat, Kraków by night* (malam f/2.8; film: tidak dicantumkan)](https://commons.wikimedia.org/wiki/File:Jubilat,_Krak%C3%B3w_-_Olympus_mju_II_01.jpg)
    3. [Flickr CC via Openverse (CC BY 2.0) — *stillness* oleh hnt6581 (backlit salju; **film: dm Paradies**)](https://www.flickr.com/photos/42639139@N08/8744176246)

---

## 12. Olympus — Olympus Pen EE-3

- **id:** `olympus_pen_ee_3`
- **brand:** `Olympus`
- **model:** `Olympus Pen EE-3` *(nama kanonik)*
- **aliases:** `PEN EE-3`, `Pen EE3`
- **displayName:** `Olympus Pen EE-3`
- **Tahun & Jenis:** 1973 · Half-frame 35mm
- **Spesifikasi Inti:** Film 35mm format **half-frame (18×24mm, rasio bawaan 3:4 vertikal**, 72 bingkai per roll 36); lensa *fixed-focus* **D.Zuiko 28mm f/3.5** (4 elemen dalam 3 grup); pengukur cahaya sel selenium tanpa baterai, eksposur otomatis 1/40s atau 1/200s. *(Halaman spesifikasi resmi produsen aktif tidak ditemukan.)*
- **Pemisahan Sifat Kamera/Lensa vs Emulsi Film (WAJIB FILM):**
  - **Sifat Bodi, Format Half-Frame & Lensa (D.Zuiko 28mm f/3.5):** Karena luas negatif hanya separuh 35mm standar, **perbesaran cetak/pindai membuat butiran film tampak jauh lebih besar dan bertekstur kasar** dibanding kamera full-frame pada roll yang sama — sangat nyata terlihat pada tembok batu Oxford, langit mendung, papan logam, dan tembok plester di seluruh sampel. Orientasi bawaan bingkai adalah **vertikal 3:4** (yang sering dipindai berdampingan sebagai diptik/triptik bersekat garis hitam vertikal seperti tampak pada sampel Kosmo Foto). Ruang tajam (*depth of field*) sangat lebar untuk pemandangan jalanan maupun arsitektur sehingga tiang depan dan menara jauh tetap terbaca tajam, namun karena lensanya *fixed-focus* (tanpa cincin fokus), objek terlalu dekat di pinggir depan (daun kiri bawah sampel 2) melunak buram. Ketajaman tengah pada jarak normal cukup bersih dengan sedikit pelunakan dan vignette halus di sudut. Tanpa flash bawaan (hanya hot-shoe/PC sync).
  - **Sifat Emulsi Film (pada sampel yang diperiksa):**
    - Sampel 1–2 (Pompa angin & taman Jepang — *film negatif warna tidak dicantumkan*): warna oranye-biru papan serta hijau-sejuk dedaunan berasal dari emulsi film warna.
    - Sampel 3–4 (Triptik jalan batu *Magpie Lane* & fasad rumah batu Oxford di Kosmo Foto — *film negatif warna 35mm tidak dicantumkan secara spesifik pada keterangan gambar*): nuansa sejuk sedikit kebiruan di bayangan jalan batu dan langit cerah pucat berasal dari film negatif warna, sedangkan kekasaran *grain* dan garis vertikal rasio 3:4 adalah ciri langsung format half-frame Pen EE-3. *(Catatan: ulasan 35mmc juga menguji Pen EE-3 dengan Kodak ColorPlus 200 dengan tekstur grain half-frame yang sama.)*
- **lookTags:** `coarse-grain`, `vertical-frame`, `deep-dof`, `fixed-focus-soft-close`, `nostalgic`
- **Arketipe:** `half_frame_35mm` *(dikonfirmasi)*
- **confidenceLook:** **`medium`** *(diaudit ketat dari `high` → `medium`)* — Contoh campur emulsi film: lensa tetap konsisten secara optik, namun foto contoh memakai jenis film berbeda atau tidak dicantumkan.
- **Tidak ditemukan:** halaman spesifikasi resmi produsen aktif; halaman Flickr Camera Finder khusus model ini tidak terverifikasi/tidak digunakan.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Situs Fotografi / Ulasan (dengan inspeksi visual sampel):* [Kosmo Foto — Olympus Pen EE3 Review](https://kosmofoto.com/2023/10/olympus-pen-ee3-review/), [35mmc — Olympus Pen EE-3 Review](https://www.35mmc.com/08/05/2020/olympus-pen-ee-3-review-weekend-in-prague-john-hanson/)
  - *Komunitas / Galeri:* [Lomography Gallery — Olympus Pen EE-3](https://www.lomography.com/cameras/3323369-olympus-pen-ee-3/photos)
  - *Foto Sampel Diperiksa Visual (4 lintas 2 sumber):*
    1. [Wikimedia Commons (Flickr, CC BY 2.0) — *Air pump street shot* oleh Taro Misako (film: tidak dicantumkan)](https://commons.wikimedia.org/wiki/File:%E7%84%A1%E6%96%99%E3%81%A7%E7%A9%BA%E6%B0%97%E5%85%A5%E3%82%8C_%E3%81%88%E3%81%82%E3%81%9E%E3%81%86%E3%81%8F%E3%82%93_(3829746890).jpg)
    2. [Flickr CC via Openverse (CC BY 2.0) — *PEN EE-3 1st test* oleh Taro Misako (vertikal 3:4; film: tidak dicantumkan)](https://www.flickr.com/photos/32469353@N04/3828937921)
    3. [Kosmo Foto — Triptik *Magpie Lane* & fasad rumah batu Oxford (2 rangkaian half-frame vertikal diperiksa visual langsung, tidak disimpan lokal)](https://kosmofoto.com/2023/10/olympus-pen-ee3-review/)

---

## 13. Fujifilm — QuickSnap Flash 400

- **id:** `fujifilm_quicksnap_flash_400`
- **brand:** `Fujifilm`
- **model:** `QuickSnap Flash 400` *(nama kanonik)*
- **aliases:** `Utsurundesu` / `写ルンです` (lini sejenis di Jepang), `QuickSnap Smart Flash`, `QuickSnap 400`
- **displayName:** `QuickSnap Flash 400`
- **Tahun & Jenis:** Tahun rilis persis varian Flash 400 saat ini tidak ditemukan di sumber resmi *(lini QuickSnap beredar sejak akhir 1980-an)* · Disposable 35mm
- **Spesifikasi Inti (Resmi):** Film negatif warna terpasang permanen **Fujicolor ISO 400** (27 eksposur, rasio 3:2); lensa plastik tunggal **32mm f/10** *fixed-focus* (1m–tak terhingga); rana tetap 1/140 detik; flash bawaan jangkauan 1–3m.
- **Pemisahan Sifat Kamera/Lensa vs Emulsi Film (WAJIB FILM — terikat permanen):**
  - **Sifat Bodi & Lensa Plastik (32mm f/10 tetap + rana 1/140s + flash):** Lensa plastik elemen tunggal menghasilkan **kelembutan mencolok di pinggir dan sudut bingkai** (sangat jelas pada mobil depan sudut kanan bawah dan pepohonan samping di sampel salju The Darkroom), sedikit lengkungan distorsi barel pada bingkai jendela, **vignette gelap ringan-sedang di keempat sudut** yang tampak nyata pada langit senja maupun langit salju mendung, kontras mikro lebih rendah dibanding lensa kaca, serta pendaran (*bloom/halation*) kabut lembut ketika cahaya terang menembus tirai jendela (sampel 1). Karena bukaan kecil (f/10) dan rana tetap (1/140s), pemotretan dalam ruang tanpa flash langsung mengalami kurang eksposur berat (sampel 2). **Flash langsung 1–3m** (terlihat langsung pada sampel anjing di salju senja The Darkroom) menerangi subjek depan secara tegas dan datar dengan kontras tajam terhadap latar gelap yang cepat jatuh.
  - **Sifat Emulsi Film (bawaan tetap Fujicolor Superia/ISO 400):** Karena film terpasang dari pabrik, warna menjadi bagian tak terpisahkan dari produk ini: di bawah cahaya redup tanpa flash (sampel 2), bayangan kurang eksposur terangkat menjadi keruh **hijau-sian berbutir sangat kasar**; pada foto senja dan salju (sampel 3–5 The Darkroom), langit menampilkan nuansa hangat-persik/magenta tipis pada awan senja, hijau-biru sejuk pada bayangan bulu/kendaraan gelap, kontras lembut, serta butiran *grain* ISO 400 yang terasa jelas di area langit.
- **lookTags:** `soft-edges`, `light-vignette`, `highlight-bloom`, `grainy-green-shadows`, `harsh-flash`
- **Arketipe:** `disposable_point_shoot` *(dikonfirmasi)*
- **confidenceLook:** **`high`** *(terkonfirmasi pada audit ketat)* — Memenuhi syarat ketat (>= 2 domain independen [4], >= 3 foto diperiksa [5], konsisten, tanpa campuran film/lensa).
- **Tidak ditemukan:** tahun rilis persis varian saat ini di sumber resmi; halaman Flickr Camera Finder khusus model ini tidak terverifikasi/tidak digunakan.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Fujifilm QuickSnap Official Specifications](https://quicksnap.fujifilm.com/en/spec.html)
  - *Situs Fotografi / Lab Film (dengan inspeksi visual sampel):* [The Darkroom — Fujifilm QuickSnap Review](https://thedarkroom.com/film/fujifilm-quicksnap-single-use-disposable-camera/)
  - *Komunitas / Galeri:* [Lomography Gallery — Fujifilm QuickSnap Flash 400](https://www.lomography.com/cameras/3336027-fujifilm-quicksnap-flash-400-disposable-35mm-camera/photos)
  - *Foto Sampel Diperiksa Visual (5 lintas 3 sumber, seluruhnya memakai film bawaan Fujicolor 400):*
    1. [Flickr CC via Openverse (CC BY 2.0) — *QuickSnap Bathroom* oleh thart2009 (cahaya jendela)](https://www.flickr.com/photos/37089490@N06/6319845791)
    2. [Flickr CC via Openverse (CC BY-SA 2.0) — *Noise* oleh Miguel Pires da Rosa (dalam ruang tanpa flash)](https://www.flickr.com/photos/75144752@N00/5094339935)
    3. [The Darkroom — 3 foto sampel QuickSnap (anjing di salju dengan flash senja, langit senja, halaman bersalju siang — diperiksa visual langsung, tidak disimpan lokal)](https://thedarkroom.com/film/fujifilm-quicksnap-single-use-disposable-camera/)

---

## 14. Holga — Holga 120N

- **id:** `holga_120n`
- **brand:** `Holga`
- **model:** `Holga 120N` *(nama kanonik)*
- **aliases:** `120N` *(saudara sekeluarga: Holga 120S, 120GN berlensa kaca, 120CFN ber-flash warna)*
- **displayName:** `Holga 120N`
- **Tahun & Jenis:** Tahun rilis pasti varian 120N tidak ditemukan di sumber produsen resmi · Toy camera medium format 120
- **Spesifikasi Inti:** Film roll 120 (**format persegi 6×6 / rasio 1:1** atau masker 6×4,5; juga kadang dimodifikasi memakai film 35mm panorama); lensa meniskus plastik tunggal 60mm (bukaan nominal f/8 dan f/11), rana sekitar 1/100–1/125 detik + Bulb. *(Halaman spesifikasi resmi produsen aktif tidak ditemukan.)*
- **Pemisahan Sifat Kamera/Lensa vs Emulsi Film (WAJIB FILM):**
  - **Sifat Bodi & Lensa Plastik (Meniskus 60mm + bodi 6×6):** Sangat dominan dan langsung dikenali pada semua sampel lintas jenis film! **Vignette sudut sangat berat dan gelap pekat** membingkai keempat pojok (sangat dramatis pada format 6×6 sampel 1–2), ketajaman hanya terbatas di area tengah lalu **cepat melunak menjadi kabur radial dan bergeser warna tipis (*chromatic smear*) ke arah tepi** (sangat jelas di sampel 2 dan 3), disertai distorsi barel halus dan nuansa sorotan *dreamy* berkabut. Karena celah bodi plastik tidak kedap sempurna (berdasarkan ulasan MyFavouriteLens & EMULSIVE), rawan muncul guratan bocoran cahaya (*light leak*) acak. Tidak memiliki flash bawaan.
  - **Sifat Emulsi Film (pada sampel yang diperiksa):**
    - Sampel 1 (Atap paviliun Red Fort — *film hitam-putih 120 tidak dicantumkan*): nuansa monokrom kelabu lembut berasal dari film B&W 120.
    - Sampel 2 (Semak Gelatt Lake — **Fujifilm Fujicolor Pro 400H** 120): palet hijau-zaitun kalem dan langit biru-kelabu pastel adalah karakter Fuji Pro 400H.
    - Sampel 3 (Taman Curitiba — **film 35mm Fuji** dipasang di bodi 120N): hijau rumput hangat dan merah muda bunga berasal dari film negatif warna Fuji.
- **lookTags:** `heavy-vignette`, `soft-edges`, `radial-blur`, `dreamy`, `square-format`, `light-leak`
- **Arketipe:** `toy_lomo_vignette` *(dikonfirmasi)*
- **confidenceLook:** **`medium`** *(diaudit ketat dari `high` → `medium`)* — Contoh campur emulsi film: lensa tetap konsisten secara optik, namun foto contoh memakai jenis film berbeda atau tidak dicantumkan.
- **Tidak ditemukan:** halaman spesifikasi resmi produsen aktif; tahun rilis persis varian saat ini di sumber resmi; halaman Flickr Camera Finder khusus model ini tidak terverifikasi/tidak digunakan.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Situs Fotografi / Ulasan:* [MyFavouriteLens — Holga 120N Review](https://www.myfavouritelens.com/holga-120n-camera-review/), [EMULSIVE — Holga 120N Review](https://emulsive.org/reviews/camera-reviews/holga-camera-reviews/holga-120n-by-sandeep-sumal)
  - *Komunitas / Galeri:* [Lomography Gallery — Holga 120N](https://www.lomography.com/cameras/3331797-holga-120-n/photos)
  - *Foto Sampel Diperiksa Visual (3):*
    1. [Wikimedia Commons (Direct, CC0) — *Rang Mahal, Red Fort* (format 6×6; film: B&W 120 tidak dicantumkan)](https://commons.wikimedia.org/wiki/File:A_corner_of_the_Rang_Mahal_-_Red_Fort.jpg)
    2. [Wikimedia Commons (Flickr, CC BY 2.0) — *Gelatt Lake* (format 6×6; **film: Fujifilm Pro 400H 120**)](https://commons.wikimedia.org/wiki/File:Gelatt_Lake_(Holga,_Medium_Format_Film,_Fuji_400H)_(52998881978).jpg)
    3. [Flickr CC via Openverse (CC BY-SA 2.0) — *Holga 120N/35mm Fuji film* oleh Felipe Miguel (**film: 35mm Fuji**)](https://www.flickr.com/photos/82603448@N00/2662019429)

---

## 15. LOMO / Lomography — Lomo LC-A

- **id:** `lomo_lc_a`
- **brand:** `LOMO / Lomography`
- **model:** `Lomo LC-A` *(nama kanonik)*
- **aliases:** `LOMO Kompakt Automat`, `LC-A` *(penerus modern beroptik sama: Lomo LC-A+)*
- **displayName:** `Lomo LC-A`
- **Tahun & Jenis:** 1984 (rilis awal LOMO) · Compact lo-fi 35mm
- **Spesifikasi Inti (Resmi Penerus):** Film 35mm (rasio 3:2); lensa kaca **Minitar-1 32mm f/2.8** (5 elemen dalam 4 grup), fokus zona 4 langkah, eksposur otomatis program.
- **Pemisahan Sifat Kamera/Lensa vs Emulsi Film (WAJIB FILM):**
  - **Sifat Bodi & Lensa Tetap (Minitar-1 32mm f/2.8):** Pada ketiga sampel (pantai siang, jalan berkabut, dan jalan kota *backlight*), **vignette gelap di keempat sudut sangat tegas dan konsisten** bahkan di siang hari terang. Lensa ini memberikan kontras optik kuat dan saturasi mikro yang hidup, ketajaman cukup baik di tengah lalu melunak ke pinggir, serta **mudah timbul *flare* berpendar lebar** saat menghadapi lampu jalan berkabut maupun sinar matahari langsung di antara gedung (terlihat dramatis pada sampel 2 dan 3). Tanpa flash bawaan (hanya hot-shoe eksternal).
  - **Sifat Emulsi Film (pada sampel yang diperiksa):**
    - Sampel 1 (Pantai Skotlandia — *film negatif warna tidak dicantumkan*): nuansa laut biru-toska dan pasir krem cerah dari film negatif warna siang hari.
    - Sampel 2 (Jalan berkabut Taipei — **Kodak Portra 400** pada Lomo LC-A+): gradasi kabut biru-kelabu lembut dan cahaya lampu hangat berasal dari Kodak Portra 400.
    - Sampel 3 (Shinjuku *backlight* — **Kodak Vision3 500T 5219**): bayangan sejuk kebiruan dan merah mobil sangat dalam dipengaruhi emulsi film bioskop tungsten 500T. *(Ulasan Blue Moon Camera Codex juga menguji Minitar-1 dengan Agfa Color Mission & Kodak Portra 400.)*
- **lookTags:** `heavy-vignette`, `high-contrast`, `vivid`, `strong-flare`, `soft-edges`
- **Arketipe:** `toy_lomo_vignette` *(dikonfirmasi)*
- **confidenceLook:** **`medium`** *(diaudit ketat dari `high` → `medium`)* — Contoh campur emulsi film: lensa tetap konsisten secara optik, namun foto contoh memakai jenis film berbeda atau tidak dicantumkan.
- **Tidak ditemukan:** halaman Flickr Camera Finder khusus model ini tidak terverifikasi/tidak digunakan.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Penerus Merek:* [Lomography — Introducing the Lomo LC-A Minitar-1](https://www.lomography.com/magazine/311143-introducing-the-lomo-lca-minitar-1-art-lens)
  - *Situs Fotografi / Ulasan:* [Blue Moon Camera Codex — Lomo LC-A Minitar-1 32mm f/2.8 Review](https://bluemooncameracodex.com/technical-reviews/lomo-lc-a-minitar-1-32mm-28-review)
  - *Foto Sampel Diperiksa Visual (3):*
    1. [Wikimedia Commons (Flickr, CC BY 2.0) — *Ecosse* (film: negatif warna tidak dicantumkan)](https://commons.wikimedia.org/wiki/File:Ecosse_(34341841756).jpg)
    2. [Flickr CC via Openverse (CC BY 2.0) — *Taipei foggy road* oleh Toomore (**Lomo LC-A+; film: Kodak Portra 400**)](https://www.flickr.com/photos/92438116@N00/23400337432)
    3. [Flickr CC via Openverse (CC BY 2.0) — *Shinjuku crosswalk* oleh Toomore (**Lomo LC-A; film: Kodak Vision3 500T 5219**)](https://www.flickr.com/photos/92438116@N00/25300640833)

---

## 16. Polaroid — Polaroid SX-70

- **id:** `polaroid_sx_70`
- **brand:** `Polaroid`
- **model:** `Polaroid SX-70` *(nama kanonik)*
- **aliases:** `SX-70 Land Camera`, `SX-70 Alpha 1`
- **displayName:** `Polaroid SX-70`
- **Tahun & Jenis:** 1972 · Instant SLR integral
- **Spesifikasi Inti:** Film instan integral **Polaroid SX-70** (format cetak persegi ~79×79mm / rasio 1:1 dalam bingkai putih, ISO ~160); lensa kaca 4 elemen 116mm f/8 fokus manual melalui jendela bidik SLR lipat; tanpa flash bawaan (menggunakan Flashbar eksternal). *(Halaman spesifikasi resmi produsen asli 1972 tidak ditemukan.)*
- **Pemisahan Sifat Kamera/Lensa vs Emulsi Film Instan (WAJIB FILM):**
  - **Sifat Bodi & Lensa Kaca SLR (116mm f/8):** Jauh lebih tajam di bidang fokus dibanding kamera instan berlensa plastik — kisi baja menara radio (sampel 1), deretan pipa knalpot & paku keling (sampel 2), serta garis nat ubin (sampel 3) tampak tegas dan rapi, disertai pelunakan organik halus di luar bidang fokus serta tepi bingkai. Karena bukaan f/8 dan ISO film rendah (~160), kamera rentan blur gerak bila cahaya kurang terang.
  - **Sifat Emulsi Instan Integral SX-70 (pada sampel yang diperiksa):**
    - Sampel 1 (Menara Portland — *emulsi integral SX-70 modern/Impossible/Polaroid Originals*): pergeseran warna dingin biru-ungu kuat, tekstur kimia sedikit berbintik, serta jejak kimia tipis di tepi atas cetak.
    - Sampel 2 (Mobil klasik — *emulsi integral SX-70*): sorotan langit krem-kekuningan hangat, warna logam kalem (*muted*), kontras lembut dengan bayangan agak terangkat, dan semburat kimia tipis di tepi bawah bingkai putih.
    - Sampel 3 (Bayangan kursi pantai 1985 — *emulsi Polaroid SX-70 Time-Zero klasik*): warna oker-krem hangat, merah bata kaya, dan bayangan beludru lembut tanpa garis keras.
- **lookTags:** `muted-vintage`, `low-contrast`, `sharp-center`, `square-instant`, `organic-chemical-shift`
- **Arketipe:** `instant_integral_vintage` *(dikonfirmasi)*
- **confidenceLook:** **`medium`** *(diaudit ketat dari `high` → `medium`)* — Contoh campur kimia emulsi instan lintas era (Polaroid Time-Zero 1985 vs Polaroid Originals SX-70 modern).
- **Tidak ditemukan:** halaman spesifikasi resmi produsen aktif; halaman Flickr Camera Finder khusus model ini tidak terverifikasi/tidak digunakan.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Situs Fotografi / Ulasan:* [Shoot It With Film — Polaroid SX-70 Review](https://shootitwithfilm.com/polaroid-sx-70-land-camera-review/), [Casual Photophile — Polaroid SX-70 Review](https://casualphotophile.com/2017/06/02/polaroid-sx-70-instant-film-camera-review-the-pinnacle-of-polaroid/), [EMULSIVE — Polaroid Originals SX-70 Film Review](https://emulsive.org/reviews/film-reviews/polaroid-originals-sx70-film-review-and-user-guide)
  - *Komunitas / Galeri:* [Lomography Gallery — Polaroid SX-70](https://www.lomography.com/cameras/3315377-polaroid-sx-70/photos)
  - *Foto Sampel Diperiksa Visual (3):*
    1. [Wikimedia Commons (Direct, CC BY-SA 4.0) — *Portland Emergency Dispatching Center Tower* (film: SX-70 integral)](https://commons.wikimedia.org/wiki/File:City_Of_Portland_Emergency_Dispatching_Center_Tower.jpg)
    2. [Wikimedia Commons (Flickr, CC BY 2.0) — *1946 rocket ship* oleh thart2009 (film: SX-70 integral)](https://commons.wikimedia.org/wiki/File:1946_rocket_ship_-_Flickr_-_thart2009.jpg)
    3. [Wikimedia Commons (Flickr, CC BY-SA 2.0) — *Polaroid SX-70 (1985)* oleh Augusto De Luca (film: SX-70 integral era 1980-an)](https://commons.wikimedia.org/wiki/File:Polaroid_sx_70_-_foto_di_Augusto_De_Luca_(4).jpg)

---

## 17. Fujifilm — Instax Mini 90 Neo Classic

- **id:** `fujifilm_instax_mini_90_neo_classic`
- **brand:** `Fujifilm`
- **model:** `Instax Mini 90 Neo Classic` *(nama kanonik)*
- **aliases:** `instax mini 90`, `Cheki instax mini 90` (Jepang)
- **displayName:** `Instax Mini 90 Neo Classic`
- **Tahun & Jenis:** 2013 · Instant modern
- **Spesifikasi Inti (Resmi):** Film instan **Fujifilm Instax Mini** (ISO 800, bidang gambar vertikal 46×62mm / rasio ~3:4); lensa 60mm f/12.7 (2 elemen), fokus 3 rentang (Macro 0,3–0,6m, Normal, Landscape), rana 1,8–1/400s + Bulb, kompensasi terang/gelap (L/L+/D), flash pintar dengan mode *Party* dan opsi matikan flash.
- **Pemisahan Sifat Kamera/Lensa vs Emulsi Film Instan (WAJIB FILM):**
  - **Sifat Bodi & Lensa (60mm f/12.7 + kontrol eksposur/flash):** Untuk ukuran cetak mini, ketajaman fokus dekat (mode Macro pada sampel 2–3) dan jarak normal tampak sangat bersih serta rapi, hanya melunak lembut di luar rentang fokus atau di tepi paling ujung; vignette optik ringan. Keunggulan utama bodi Mini 90 (dikonfirmasi PCMag & Popular Photography) adalah pengukuran cahaya dan flash pintar yang bisa diatur (termasuk mode *Party* slow-sync dan pengatur kecerahan), mencegah latar menjadi gelap total atau kelopak bunga putih terbakar rata.
  - **Sifat Emulsi Instan Fujifilm Instax Mini (bawaan tetap pada ketiga sampel):** Jauh lebih cerah, jenuh, dan berkontras kuat dibanding Polaroid SX-70 — langit biru tampil kobalt pekat (sampel 1–2), kuning bunga menyala bersih, hijau semanggi gelap pekat berlawanan dengan putih kelopak yang tegas (sampel 3), dan sorotan matahari langsung cepat memutih bersih (*crisp highlight clipping*).
- **lookTags:** `bright-color`, `punchy-contrast`, `crisp-instant`, `vertical-mini`, `balanced-flash`
- **Arketipe:** `instant_modern_instax` *(dikonfirmasi)*
- **confidenceLook:** **`high`** *(terkonfirmasi pada audit ketat)* — Memenuhi syarat ketat (>= 2 domain independen [5], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa).
- **Tidak ditemukan:** halaman Flickr Camera Finder khusus model ini tidak terverifikasi/tidak digunakan.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Fujifilm Official Catalogue PDF — Instax Mini 90](https://asset.fujifilm.com/www/ca/files/2020-12/5e51c23b70d831f1adccc629b2ddb593/instax_mini_90_catalogue.pdf)
  - *Situs Fotografi / Ulasan:* [PCMag — Instax Mini 90 Review](https://au.pcmag.com/digital-cameras/59124/fujifilm-instax-mini-90-neo-classic), [Popular Photography — Instax Mini 90 Review](https://www.popphoto.com/gear/2013/12/review-fujifilm-instax-mini-90-neo-classic-instant-camera/), [Lomography Magazine — Instax Mini 90 Sample Snapshots](https://www.lomography.com/magazine/256704-sample-snapshots-taken-with-the-fuji-instax-mini-90-neo-classic)
  - *Foto Sampel Diperiksa Visual (3, semua memakai film Instax Mini):*
    1. [Flickr CC via Openverse (CC BY 2.0) — *Instax Mini 90 wild grass* oleh Dane Van](https://www.flickr.com/photos/11028609@N05/48020641076)
    2. [Flickr CC via Openverse (CC BY 2.0) — *Instax Mini 90 yellow flowers* oleh James Feller](https://www.flickr.com/photos/29411712@N06/14927221837)
    3. [Flickr CC via Openverse (CC BY 2.0) — *InstaxMini daisies* oleh James Feller](https://www.flickr.com/photos/29411712@N06/14454689752)

---

## 18. Hasselblad — Hasselblad 500C/M

- **id:** `hasselblad_500c_m`
- **brand:** `Hasselblad`
- **model:** `Hasselblad 500C/M` *(nama kanonik)*
- **aliases:** `500 C/M`, `500CM`
- **displayName:** `Hasselblad 500C/M`
- **Tahun & Jenis:** 1970 · Medium format SLR 120
- **Spesifikasi Inti:** Film 120/220 **format persegi 6×6cm (rasio 1:1)**; bodi modular mekanis (umum dipasangkan lensa **Carl Zeiss Planar 80mm f/2.8 T*** berana daun sinkron flash hingga 1/500s). *(Halaman spesifikasi resmi produsen klasik 1970 tidak ditemukan.)*
- **Pemisahan Sifat Kamera/Lensa vs Emulsi Film (WAJIB FILM):**
  - **Sifat Bodi, Format 6×6 & Lensa (Zeiss Planar 80mm f/2.8 T*):** Luas negatif medium format 6×6 membuat butiran film tampil **sangat halus** dengan gradasi tonal panjang yang mulus. Pada ketiga sampel terlihat ketajaman tinggi di bidang fokus (kelopak bunga, tekstur perunggu patung, setang sepeda) dengan distorsi geometris nyaris nol, transisi fokus ke latar yang luar biasa krem dan berdimensi (*3D pop*), serta sedikit pendaran lembut (*subtle glow*) pada sorotan jendela *backlight* di bukaan lebar (sampel 3). Vignette sangat tipis. Tanpa flash bawaan.
  - **Sifat Emulsi Film (pada sampel yang diperiksa):**
    - Sampel 1 (Bunga marigold 6×6 dengan bingkai film `FUJI 14 RTP` — **Fujichrome 64T RTP tungsten slide film**): latar biru-indigo dalam dan kuning sangat pekat berasal dari film slide tungsten Fujichrome 64T yang dipakai di cahaya siang.
    - Sampel 2 (Patung perunggu Gotha — *film hitam-putih 120 tidak dicantumkan*): gradasi abu-abu halus dan sorotan bersih berasal dari film B&W 120.
    - Sampel 3 (Lorong sepeda San Francisco — **Planar 80mm**, *film negatif warna 120 tidak dicantumkan*): nuansa dinding hijau-zaitun kalem berasal dari film negatif warna di bawah cahaya koridor.
- **lookTags:** `fine-grain`, `3d-pop`, `smooth-rolloff`, `square-format`, `subtle-glow`, `zero-distortion`
- **Arketipe:** `medium_format_tlr_slr` *(dikonfirmasi)*
- **confidenceLook:** **`medium`** *(diaudit ketat dari `high` → `medium`)* — Contoh campur: bodi kamera film berlensa tukar dengan lensa dan emulsi film berbeda-beda/sebagian tidak tercatat.
- **Tidak ditemukan:** halaman spesifikasi resmi produsen aktif; halaman Flickr Camera Finder khusus model ini tidak terverifikasi/tidak digunakan.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Situs Fotografi / Ulasan:* [EMULSIVE — Hasselblad 500C/M Review](https://emulsive.org/reviews/camera-reviews/hasselblad-camera-reviews/camera-review-a-love-letter-to-my-hasselblad-500cm), [Shoot It With Film — Hasselblad 500C/M Review](https://shootitwithfilm.com/hasselblad-500-cm-film-camera-review/)
  - *Komunitas / Galeri:* [Lomography Gallery — Hasselblad 500C/M](https://www.lomography.com/cameras/3352762-hasselblad-500c-m/photos)
  - *Foto Sampel Diperiksa Visual (3):*
    1. [Wikimedia Commons (Flickr, CC BY 2.0) — *14 Fuji RTP* (**film: Fujichrome 64T RTP 120**, 6×6)](https://commons.wikimedia.org/wiki/File:14_Fuji_RTP_-_Flickr_-_BobvdK.jpg)
    2. [Wikimedia Commons (Direct, CC BY-SA 4.0) — *Denkmal Gotha* (EXIF: 500 C/M; film: B&W 120 tidak dicantumkan)](https://commons.wikimedia.org/wiki/File:Bernd_G%C3%B6bel_Denkmal_f%C3%BCr_Herzogin_Luise_Dorothea_an_der_Wasserkunst_in_Gotha.jpg)
    3. [Flickr CC via Openverse (CC BY 2.0) — *Bike Parking in the Mission* oleh takasphoto.com (**lensa: Planar 80mm**; film: warna 120 tidak dicantumkan, 6×6)](https://www.flickr.com/photos/8534722@N04/9308777426)

---

## 19. Sony — Handycam DCR-TRV900

- **id:** `sony_handycam_dcr_trv900`
- **brand:** `Sony`
- **model:** `Handycam DCR-TRV900` *(nama kanonik)*
- **aliases:** `DCR-TRV900`, `DCR-TRV900E` (PAL), `TRV900`
- **displayName:** `Handycam DCR-TRV900`
- **Tahun & Jenis:** 1998 · Camcorder MiniDV 3-CCD
- **Spesifikasi Inti (Resmi):** Sistem **3-CCD 1/4 inci** (~380K piksel bruto per chip RGB terpisah via prisma dikroik), rekam pita MiniDV definisi standar (NTSC/PAL) dan tangkapan *still* ke disket/mode *Progressive Scan*; lensa zoom optik 12x dengan cincin fokus manual; tanpa NightShot inframerah.
- **Ketergantungan Mode Warna (Digital/Video):** Ciri utama berasal dari arsitektur **3-CCD standar bawaan** dalam mode video *interlaced* (60i/50i) atau *progressive still*; tidak mengandalkan filter warna kustom, namun pada cahaya redup penguatan otomatis (*AGC/gain*) cepat memunculkan *noise* video.
- **Deskripsi Tampilan Hasil Foto/Frame (4 Sampel Visual + Ulasan Teknis):**
  - **Warna 3-CCD:** Pada keempat *still frame* langsung dari kamera (pesawat bercorak magenta-kuning di Wikimedia, serta bunga liar kuning, laba-laba, dan burung kuntul putih di rawa dari BealeCorner), pemisahan warna 3-CCD tampak jauh lebih pekat, jernih, dan bebas rembesan warna (*color bleeding*) dibanding camcorder 1-chip era 1990-an — kuning bunga dan magenta badan pesawat terpisah bersih dari hijau dedaunan gelap.
  - **Resolusi, Sorotan & Ketajaman:** Seluruh bingkai menampilkan proporsi **4:3 definisi standar (640×480)** dengan langit-langit detail yang lunak khas video DV 1998 dan penajaman tepi elektronik tipis pada garis berkontras tinggi (kaki laba-laba, paruh burung). Rentang dinamis video cukup sempit: langit mendung pada sampel pesawat langsung terbakar putih rata, sementara bulu putih burung kuntul nyaris menyentuh batas putih penuh dan bayangan rimbun dedaunan gelap tampak sedikit terkompresi. Pada jarak fokus dekat/zoom (sampel laba-laba), optik 12x memberi pemisahan latar yang cukup mulus.
  - **Karakter Video:** Menurut pengujian Videomaker dan BealeCorner, ujung sudut lebar lensa sedikit melunak dan rekaman pita MiniDV bawaannya membawa karakter gerak *interlaced* (sedangkan mode *Progressive Scan* menurunkan separuh resolusi vertikal gerak tetapi sering dipakai untuk menangkap *still* bersih ke disket).
- **lookTags:** `rich-3ccd-color`, `low-res`, `clipped-highlights`, `dv-softness`, `interlaced-video`
- **Arketipe:** `camcorder_mini_dv_vhs` *(dikonfirmasi)*
- **confidenceLook:** **`medium`** *(diaudit ketat dari `high` → `medium`)* — 4 frame still diperiksa lintas 2 domain (Wikimedia + BealeCorner), namun karakter gerak video interlaced hanya dari ulasan teknis.
- **Tidak ditemukan:** halaman Flickr Camera Finder khusus model ini tidak terverifikasi/tidak digunakan; inspeksi pemutaran video bergerak (interlaced/24p hanya diverifikasi dari literatur teknis).
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Sony Press Release 1998 — DCR-TRV900](https://www.sony.com/en/SonyInfo/News/Press/199807/98-068/)
  - *Situs Videografi / Uji Teknis (dengan inspeksi visual sampel):* [BealeCorner — TRV900 Notes & Direct Still Frames](http://www.bealecorner.com/trv900/bnotes/brief.html), [BealeCorner — TRV900 Comparisons](http://www.bealecorner.com/trv900/trv-comp.html), [Videomaker — Sony DCR-TRV900 Review](https://www.videomaker.com/article/c5/7530-sony-three-ccd-camcorder-review-dcr-trv900-mini-dv-camcorder/)
  - *Foto Sampel Diperiksa Visual (4 lintas 2 sumber):*
    1. [Wikimedia Commons (CC BY-SA 2.5) — *Skyvan* (diambil Agustus 2001 dengan Sony DCR-TRV900)](https://commons.wikimedia.org/wiki/File:Skyvan.jpg)
    2. [BealeCorner — 3 *still frame* langsung dari TRV900 (*flower1m*, *spider-m*, *3birds-m* mode Superfine 640×480 — diperiksa visual langsung, tidak disimpan lokal)](http://www.bealecorner.com/trv900/bnotes/brief.html)

---

## 20. Apple — iPhone 4

- **id:** `apple_iphone_4`
- **brand:** `Apple`
- **model:** `iPhone 4` *(nama kanonik)*
- **aliases:** `Apple iPhone 4` *(bukan iPhone 4S)*
- **displayName:** `iPhone 4`
- **Tahun & Jenis:** 2010 · Camera phone awal
- **Spesifikasi Inti (Resmi):** Sensor BSI CMOS 1/3,2" 5 MP; lensa tetap f/2.8 dengan autofokus sentuh dan lampu kilat LED; video 720p 30fps.
- **Ketergantungan Mode Warna (Digital):** Karakter utama berasal dari **kamera bawaan iOS default (JPEG otomatis tanpa filter)**; iOS 4.1+ menambahkan opsi *HDR* untuk sedikit menahan langit terang, namun seluruh sampel yang diperiksa adalah bidikan standar bawaan.
- **Deskripsi Tampilan Hasil Foto (Inspeksi Visual + Sumber):**
  - **Warna & Kontras:** Di cahaya terang maupun teduh siang hari, warna tampil cerah dan agak jenuh (merah cat kayu, kuning mencolok, merah muda pastel, dan hijau rimbun) dengan kontras tegas khas ponsel kamera awal Apple. Di dalam ruang (sampel 2), keseimbangan putih cenderung netral-sedikit sejuk.
  - **Sorotan (*Highlight*) & Rentang Dinamis:** Rentang dinamis sempit — bidang putih terang (papan nama cat putih, jendela latar perpustakaan, kap fiberglass kena cahaya) cepat penuh terbakar putih rata tanpa gradasi halus.
  - **Ketajaman & Noise:** Di siang hari dan jarak sedang, ketajaman untuk kelas 5 MP cukup rapi tanpa halo penajaman berlebihan; pada bayangan kayu gelap dan karpet perpustakaan redup terlihat **pola butiran *noise* luminansi yang halus dan seragam** (alih-alih penghalusan cat air berat seperti banyak ponsel sezamannya).
  - **Vignette & Flash:** Vignette sudut hampir tak terlihat; lampu kilat LED kecil bawaan (menurut Engadget & GSMArena) cenderung keras pada jarak dekat dan mudah membuat subjek datar/terbakar.
- **lookTags:** `crisp-daylight`, `vivid`, `clipped-highlights`, `fine-noise`, `low-dr`
- **Arketipe:** `early_camera_phone` *(dikonfirmasi)*
- **confidenceLook:** **`high`** *(terkonfirmasi pada audit ketat)* — Memenuhi syarat ketat (>= 2 domain independen [5], >= 3 foto diperiksa [3], konsisten, tanpa campuran film/lensa).
- **Tidak ditemukan:** halaman Flickr Camera Finder (404/500 saat diperiksa).
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Apple Support — iPhone 4 Technical Specifications](https://support.apple.com/en-us/112562)
  - *Situs Fotografi / Ulasan:* [Imaging Resource — Apple iPhone 4 Gallery](https://www.imaging-resource.com/cameras/apple-iphone-4-review/gallery/), [GSMArena — Apple iPhone 4 Camera Review](https://www.gsmarena.com/apple_iphone_4-review-490p8.php), [Engadget — iPhone 4 Review](https://www.engadget.com/2010-06-22-iphone-4-review.html)
  - *Foto Sampel Diperiksa Visual (3, EXIF SOOC):*
    1. [Wikimedia Commons (Direct, CC0) — *Artisan carreleur ghost sign* (EXIF: iPhone 4, iOS 5.0.1 SOOC)](https://commons.wikimedia.org/wiki/File:%22Artisan_carreleur%22_-_ghost_sign_-_Miribel.jpg)
    2. [Wikimedia Commons (Flickr, CC BY 2.0) — *Camelot stone carving* (EXIF: iPhone 4, interior)](https://commons.wikimedia.org/wiki/File:%22Camelot%22_(5786377041).jpg)
    3. [Wikimedia Commons (Direct, CC BY-SA 3.0) — *Chapeau Claque Spreepark* (EXIF: iPhone 4, iOS 6.1.3 SOOC)](https://commons.wikimedia.org/wiki/File:%22Chapeau_Claque%22.JPG)

---

---

## Ringkasan Laporan Batch 01 *(Diperbarui Audit Ketat)*

### 1. Jumlah Kamera per `confidenceLook` (Setelah Audit Ketat — Lihat [AUDIT.md](AUDIT.md))

| Tingkat `confidenceLook` | Jumlah | Daftar Kamera |
|---|---:|---|
| **`high`** | **8** | `canon_powershot_sd1000`, `fujifilm_finepix_f31fd`, `sony_cyber_shot_dsc_t70`, `ricoh_gr_iii`, `fujifilm_x100v`, `fujifilm_quicksnap_flash_400`, `fujifilm_instax_mini_90_neo_classic`, `apple_iphone_4` |
| **`medium`** | **12** | `leica_m9`, `canon_ae_1`, `nikon_fm2`, `leica_m6`, `contax_t2`, `olympus_mju_ii`, `olympus_pen_ee_3`, `holga_120n`, `lomo_lc_a`, `polaroid_sx_70`, `hasselblad_500c_m`, `sony_handycam_dcr_trv900` |
| **`low`** | **0** | — |
| **`tidak ditemukan`** | **0** | — |
| **Total** | **20** | **64 foto/frame diperiksa visual** (56 berlisensi CC/PD lokal di `docs/references/manifest.md`) |

### 2. Kamera dengan Sumber Lemah / Perlu Perhatian Saat Kalibrasi

- **`leica_m9` (`medium`):** Kamera digital berlensa tukar (mount Leica M); karakter optik (vignette, bokeh, flare) bervariasi antar lensa sampel.
- **`canon_ae_1` (`medium`):** Contoh campur: bodi kamera film berlensa tukar dengan lensa dan emulsi film berbeda-beda/sebagian tidak tercatat.
- **`nikon_fm2` (`medium`):** Contoh campur: bodi kamera film berlensa tukar dengan lensa dan emulsi film berbeda-beda/sebagian tidak tercatat.
- **`leica_m6` (`medium`):** Contoh campur: bodi kamera film berlensa tukar dengan lensa dan emulsi film berbeda-beda/sebagian tidak tercatat.
- **`contax_t2` (`medium`):** Contoh campur emulsi film: lensa tetap konsisten secara optik, namun foto contoh memakai jenis film berbeda atau tidak dicantumkan.
- **`olympus_mju_ii` (`medium`):** Contoh campur emulsi film: lensa tetap konsisten secara optik, namun foto contoh memakai jenis film berbeda atau tidak dicantumkan.
- **`olympus_pen_ee_3` (`medium`):** Contoh campur emulsi film: lensa tetap konsisten secara optik, namun foto contoh memakai jenis film berbeda atau tidak dicantumkan.
- **`holga_120n` (`medium`):** Contoh campur emulsi film: lensa tetap konsisten secara optik, namun foto contoh memakai jenis film berbeda atau tidak dicantumkan.
- **`lomo_lc_a` (`medium`):** Contoh campur emulsi film: lensa tetap konsisten secara optik, namun foto contoh memakai jenis film berbeda atau tidak dicantumkan.
- **`polaroid_sx_70` (`medium`):** Contoh campur kimia emulsi instan lintas era (Polaroid Time-Zero 1985 vs Polaroid Originals SX-70 modern).
- **`hasselblad_500c_m` (`medium`):** Contoh campur: bodi kamera film berlensa tukar dengan lensa dan emulsi film berbeda-beda/sebagian tidak tercatat.
- **`sony_handycam_dcr_trv900` (`medium`):** 4 frame still diperiksa lintas 2 domain (Wikimedia + BealeCorner), namun karakter gerak video interlaced hanya dari ulasan teknis.

### 3. Ringkasan Hal yang Tidak Ditemukan (Detail Per Kamera Tercatat di Masing-Masing Entri)

- **Tanpa halaman spesifikasi resmi produsen aktif (7):** `fujifilm_finepix_f31fd`, `contax_t2`, `olympus_mju_ii`, `olympus_pen_ee_3`, `holga_120n`, `polaroid_sx_70`, `hasselblad_500c_m`
- **Tanpa foto contoh tunggal berlisensi CC/PD lokal (0):** —
- **Foto contoh diperiksa visual < 3 (0):** —
- Rincian lengkap kekurangan per kamera (termasuk status Flickr Camera Finder dan mode khusus) tercantum pada baris **`Tidak ditemukan`** di setiap entri kamera di atas serta tabel menyeluruh di [AUDIT.md](AUDIT.md).
