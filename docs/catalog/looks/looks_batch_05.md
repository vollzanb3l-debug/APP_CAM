# Analisis Mendalam Tampilan Foto — Batch 05 (`docs/catalog/looks/looks_batch_05.md`)

Pendalaman karakter visual kualitatif untuk **20 kamera Batch 05** (nomor `81`–`100`) dari [batch_05.md](../batch_05.md), mengacu pada daftar arketipe di [archetypes.md](../archetypes.md).

> **Metodologi & Kepatuhan Aturan Keras:**
> - **Tanpa angka parameter shader:** Seluruh deskripsi murni kualitatif (warna, kontras, perilaku sorotan/bayangan, *grain*/*noise*, kelembutan, *vignette*, *flare*, karakter *flash*). Angka yang muncul hanyalah spesifikasi perangkat keras terverifikasi (mis. ukuran sensor, panjang fokus, ISO, format film/video, kecepatan rana).
> - **Inspeksi visual langsung tanpa memasukkan foto ke repo/aplikasi:** Inspeksi visual dilakukan langsung terhadap **52 foto contoh berlisensi terbuka jelas (CC0, CC BY, CC BY-SA, Public Domain)** dari **Wikimedia Commons** dan **Flickr CC (via Openverse)** yang salinan rujukan lokalnya dicatat lengkap lisensinya di `docs/references/manifest_batch_05.json` (folder `docs/references/` tidak dimasukkan ke repo), ditambah inspeksi visual sementara atas **14 foto/*frame* contoh dari arsip dan ulasan teknis independen** (Mike Eckman / Wrotniak Archive, Stanford Mavica Notes, Adam Wilt 24p Guide, dan Internet Archive) untuk melengkapi kamera dengan sampel CC terbatas (`Mavica MVC-FD7`, `Handycam DCR-VX1000`, `AG-DVX100`); seluruh berkas sementara langsung dihapus seusai pengamatan dan hanya URL halaman sumber kanonik yang disimpan. Tidak ada foto apa pun yang masuk ke repositori Git maupun `app/`.
> - **Privasi:** Seluruh foto sampel telah diperiksa visual satu per satu; foto yang menampilkan wajah yang dapat dikenali, anak-anak, atau foto produk kamera telah dikeluarkan dan dihapus.
> - **Pemisahan kamera film vs digital:** Untuk kamera film 35mm dan medium format 120 (`Pentax K1000`, `Olympus OM-1`, `Rolleiflex 2.8F`, `Mamiya 7`, `Pentax 67`), sifat optik bodi/lensa/format dipisahkan secara tegas dari sifat emulsi film; jenis film pada tiap foto sampel dicatat bila diketahui atau dinyatakan *"jenis film tidak dicantumkan"*. Untuk kamera digital dan camcorder, dicatat apakah tampilan khas berasal dari setelan bawaan (*default* JPEG/RAW/video) atau mode warna/simulasi film/kurva gamma tertentu.
> - **Penamaan kanonik:** Nama merek dan tipe mengikuti `docs/catalog/batch_05.md` secara persis; alias regional/varian dicatat di kolom `aliases` dengan satu nama kanonik.

---

## 81. Sony — Cyber-shot DSC-W55

- **id:** `sony_cyber_shot_dsc_w55`
- **brand:** `Sony`
- **model:** `Cyber-shot DSC-W55` *(nama kanonik)*
- **aliases:** `DSC-W55`, `Sony W55`
- **displayName:** `Cyber-shot DSC-W55`
- **Tahun & Jenis:** 2007 · Digital compact CCD
- **Spesifikasi Inti (Resmi):** Sensor Super HAD CCD 1/2,5" 7,2 MP efektif; lensa Carl Zeiss Vario-Tessar 3x zoom optik (ekuiv. 38–114mm) f/2.8–5.2; tanpa stabilisasi optik; ISO Auto, 100–1000.
- **Ketergantungan Mode Warna (Digital):** Karakter hangat-pekat dan kontras khas CCD 2000-an muncul langsung pada **pengaturan bawaan (default SOOC JPEG / Normal)**; kamera menyediakan mode warna *Vivid*, *Natural*, *Sepia*, dan *B&W*, tetapi ketiga foto sampel EXIF yang diperiksa berasal dari keluaran JPEG standar kamera.
- **Deskripsi Tampilan Hasil Foto (Inspeksi Visual + Sumber):**
  - **Warna & White Balance:** Pada kondisi stasiun teduh (sampel 1), kuning dan merah bodi kereta tampil pekat dan hangat di tengah nuansa peron netral; saat matahari terbenam di pantai (sampel 2), gradasi jingga-keemasan tersaji kaya dan dramatis; pada bidikan dalam ruang dengan *flash* (sampel 3), rona bulu keemasan, kain merah marun, dan pola biru tampil hidup dengan sedikit dorongan hangat khas Sony era 2007.
  - **Kontras & Sorotan (*Highlight*):** Kontras bawaan tegas dengan rentang dinamis khas CCD saku yang sempit — langit mendung di luar kanopi stasiun dan piringan matahari senja langsung penuh terbakar putih rata, disertai pendaran lembut tipis di sekitar sumber cahaya terang dan bayangan gelap yang cepat jatuh pekat di area pantai serta atap peron.
  - **Ketajaman & Kelembutan:** Bidang tengah foto cukup renyah dan bersih di ISO rendah (tekstur hidung dan bulu anak anjing maupun bodi depan kereta terbaca jelas), sedangkan sudut tepi bingkai mulai melunak perlahan.
  - **Noise & Flash:** Pada bayangan atap stasiun dan pasir gelap pantai mulai tampak butiran *noise* luminansi khas sensor kecil, dan tanpa stabilisasi optik kenaikan ISO cepat memicu peredaman *noise* yang menghaluskan tekstur halus; lampu kilat langsung bawaan pada jarak dekat memberi pencahayaan depan yang terang rata, kontras tegas, dan jatuhan bayangan tajam khas snapshot digicam akhir 2000-an.
- **lookTags:** `warm`, `punchy-daylight`, `soft-corners`, `noise-smoothing`, `harsh-flash`
- **Arketipe:** `ccd_compact_2000s` *(dikonfirmasi)*
- **confidenceLook:** **`high`** — 3 foto sampel SOOC EXIF `DSC-W55` lintas 3 pencahayaan berbeda (teduh stasiun, senja pantai, dalam ruang dengan flash) konsisten sepenuhnya dengan pengujian Imaging Resource, PCMag, dan Bling Museum.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Sony Support — DSC-W55 Specifications](https://www.sony.co.id/en/electronics/support/compact-cameras-dscw-series/dsc-w55/specifications)
  - *Situs Fotografi / Ulasan:* [Imaging Resource — Sony DSC-W55 Test](https://www.imaging-resource.com/cameras/sony-dsc-w55-review/test/), [PCMag — Sony Cyber-shot DSC-W55](https://uk.pcmag.com/cameras/24301/sony-cyber-shot-dsc-w55), [Bling Museum — Sony Cyber-shot DSC-W55](https://bling-inc.com/blogs/museum/sony-cyber-shot-dsc-w55)
  - *Foto Sampel Diperiksa Visual (3, EXIF SOOC):*
    1. [Wikimedia Commons (CC BY-SA 2.0) — *159010 at Woking* oleh Au Morandarte (EXIF: DSC-W55, teduh stasiun)](https://commons.wikimedia.org/wiki/File:159010_at_Woking_(11894600086).jpg)
    2. [Wikimedia Commons (Public domain) — *''Taielet'' de Tel Aviv* oleh AWUY (EXIF: DSC-W55, senja pantai)](https://commons.wikimedia.org/wiki/File:%27%27Taielet%27%27_de_Tel_Aviv.jpg)
    3. [Wikimedia Commons (CC BY-SA 4.0) — *-babiesgolden* oleh DannaCaterina245 (EXIF: DSC-W55, dalam ruang flash langsung)](https://commons.wikimedia.org/wiki/File:-babiesgolden.JPG)

---

## 82. Sony — Cyber-shot DSC-T7

- **id:** `sony_cyber_shot_dsc_t7`
- **brand:** `Sony`
- **model:** `Cyber-shot DSC-T7` *(nama kanonik)*
- **aliases:** `DSC-T7`, `Sony T7`
- **displayName:** `Cyber-shot DSC-T7`
- **Tahun & Jenis:** 2005 · Ultra-slim digital compact CCD (lensa periskop lipat)
- **Spesifikasi Inti (Resmi):** Sensor Super HAD CCD 1/2,5" 5,1 MP efektif; lensa periskop internal Carl Zeiss Vario-Tessar 3x zoom (ekuiv. 38–114mm) f/3.5–4.4; ISO Auto, 64–400; bodi ultra-tipis ~9,8mm.
- **Ketergantungan Mode Warna (Digital):** Karakter warna yang relatif bersih-alami namun bertekstur lembut berasal dari **pengaturan bawaan (default SOOC JPEG)** pada optik lipat internal; tidak membutuhkan mode warna khusus, meski kamera menyediakan opsi *Natural*, *Vivid*, *Sepia*, dan *B&W*.
- **Deskripsi Tampilan Hasil Foto (Inspeksi Visual + Sumber):**
  - **Warna & White Balance:** Di cahaya siang terang bandara (sampel 1) dan kolam pantai mendung (sampel 3), warna tampil lebih bersih serta alami dibanding gaya super-jenuh digicam Sony awal — hijau zamrud bodi pesawat, biru laut teduh, dan rumput basah terlihat wajar dan sejuk, sedangkan papan reklame merah-hijau di cahaya keras (sampel 2) tetap tampil cerah mencolok.
  - **Kelembutan Optik & Pemrosesan:** Konstruksi optik periskop ultra-tipis membuat detail halus pada permukaan jauh (aspal apron, cakrawala, batu karang, dan hamparan rumput semanggi) tampak sedikit lunak serta agak terproses, berbeda dari ketajaman klinis lensa teleskopik biasa.
  - **Kontras, Sorotan & Aberasi:** Rentang dinamis sempit membuat langit di balik papan reklame (sampel 2) langsung terbakar putih rata, bayangan bawah sayap pesawat jatuh cukup gelap, dan pinggiran kontras tinggi menunjukkan sedikit aberasi kromatik halus.
  - **Noise:** Bahkan pada bayangan papan biru di siang hari (sampel 2), bintik *noise* luminansi digital halus sudah mulai terbaca; menurut uji lab DPInterface dan PCMag, *noise* naik cepat mulai ISO 200–400 dan melahirkan kesan saku pertengahan 2000-an yang sedikit membuai.
- **lookTags:** `natural-color`, `soft-detail`, `dreamy`, `high-iso-noise`, `ultra-compact-ccd`
- **Arketipe:** `ccd_dreamy_pastel` *(dikonfirmasi)*
- **confidenceLook:** **`high`** — 3 foto sampel EXIF `DSC-T7` lintas kondisi cerah, papan berkontras tinggi, dan pesisir mendung konsisten dengan ulasan DPInterface, TechRadar, PCMag, dan komunitas DPReview.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Sony UK Support — DSC-T7 Specifications](https://www.sony.co.uk/electronics/support/compact-cameras-dsc-t-series/dsc-t7/specifications)
  - *Situs Fotografi / Ulasan:* [DPInterface — Sony Cyber-shot T7 Review](https://dpinterface.com/reviews/sony-cybershot-t7/), [TechRadar — Sony Cyber-shot DSC-T7 Review](https://www.techradar.com/reviews/cameras-and-camcorders/cameras/compact-cameras/sony-cyber-shot-dsc-t7-64361/review), [PCMag — Sony Cyber-shot DSC-T7](https://uk.pcmag.com/cameras/26324/sony-cyber-shot-dsc-t7)
  - *Forum / Komunitas:* [DPReview Forum — DSC-T7 Review](https://www.dpreview.com/forums/threads/sony-cyber-shot-dsc-t7-review.3422505/)
  - *Foto Sampel Diperiksa Visual (3, EXIF DSC-T7):*
    1. [Wikimedia Commons (CC BY 2.0) — *01 EVA 77W rainbow detail* oleh moaksey (EXIF: DSC-T7, siang cerah)](https://commons.wikimedia.org/wiki/File:01_EVA_77W_rainbow_detail.jpg)
    2. [Wikimedia Commons (CC BY-SA 2.0) — *1-Eleven (E)* oleh Wm Jas (EXIF: DSC-T7, papan kontras tinggi)](https://commons.wikimedia.org/wiki/File:1-Eleven_(E)_(489461953).jpg)
    3. [Wikimedia Commons (CC BY-SA 3.0) — *100 Fairlight Beach* oleh Karl Bauer (EXIF: DSC-T7, pesisir mendung)](https://commons.wikimedia.org/wiki/File:100_Fairlight_Beach.jpg)

---

## 83. Sony — Mavica MVC-FD7

- **id:** `sony_mavica_mvc_fd7`
- **brand:** `Sony`
- **model:** `Mavica MVC-FD7` *(nama kanonik)*
- **aliases:** `MVC-FD7`, `Mavica FD7`, `Sony FD7`
- **displayName:** `Mavica MVC-FD7`
- **Tahun & Jenis:** 1997 · Early floppy-disk digital camera
- **Spesifikasi Inti (Resmi):** Sensor CCD 1/4" ~0,3 MP (resolusi maksimum VGA 640×480); lensa zoom optik 10x (ekuiv. ~40–400mm) f/1.8–2.9; media simpan disket 3,5" 1,44 MB (JPEG Standard/Fine).
- **Ketergantungan Mode Warna (Digital):** Karakter *lo-fi* 1997 muncul sepenuhnya dari **JPEG bawaan 640×480 langsung ke disket** (pilihan *Standard* vs *Fine* mengatur tingkat kompresi, serta opsi *Picture Effect* seperti Sepia/Monotone/Pastel jarang dipakai dibanding warna standar bawaan).
- **Deskripsi Tampilan Hasil Foto (Inspeksi Visual + Sumber):**
  - **Resolusi VGA & Artefak Kompresi:** Pada berkas asli 640×480 yang diperiksa dari arsip Wrotniak (bunga krisan merah dan perahu marina) serta Stanford (Menara Hoover, meja teras, ruang kerja), batas piksel rendah langsung tampak jelas: detail halus meluruh lunak bak lukisan digital awal, disertai kotak kompresi JPEG (*macro-blocking*) dan halo penajaman tepi pada kelopak merah terang serta garis tali perahu.
  - **Warna & White Balance:** Di luar ruang cerah, palet warna terasa datar-tegas namun cukup hidup — merah krisan tampil pekat menyala dan kolase 100 angka jalanan 1997 (sampel Wikimedia) menampilkan merah, kuning, biru, serta pirus yang khas kamera digital generasi pertama. Sebaliknya, pada ruang kantor berpendar fluoresen (sampel Stanford), keseimbangan putih mudah bergeser ke rona keruh kehijauan-kuning kecokelatan.
  - **Rentang Dinamis & Artefak Sensor Camcorder:** Rentang dinamis amat sempit — bagian atas lambung perahu putih, langit di balik menara, dan jendela teras langsung terbakar putih tanpa gradasi, sementara bayangan air jatuh gelap cepat. Karena pembacaan sensornya berakar dari camcorder era 90-an (*field/frame*), tepi miring dan permukaan air bergerak pada sampel perahu memperlihatkan gerigi tipis menyerupai garis *interlace*.
- **lookTags:** `lo-fi`, `low-res-vga`, `jpeg-artifacts`, `dreamy-soft`, `clipped-highlights`, `harsh-flash`
- **Arketipe:** `ccd_dreamy_pastel` *(dikonfirmasi)*
- **confidenceLook:** **`high`** — Didukung inspeksi visual langsung 1 kolase 100 bidikan 1997 berlisensi CC di Wikimedia Commons ditambah 5 berkas asli 640×480 lintas 2 arsip independen (Mike Eckman/Wrotniak Archive & Stanford Mavica Notes) yang selaras dengan Guy J. Brown Collection.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Sony Official Manual PDF — MVC-FD7](https://www.sony.com/electronics/support/res/manuals/W000/W0007206M.pdf)
  - *Lab / Arsip Fotografi (dengan inspeksi visual sampel):* [Mike Eckman / Wrotniak Archive — Sony Mavica MVC-FD7](https://mikeeckman.com/wrotniak/photo/snap/fd7.html), [Stanford Mavica Notes — Lucas Pereira](https://graphics.stanford.edu/~lucasp/Mavica/), [Digitalkamera Museum — Sony MVC-FD7](https://www.digitalkameramuseum.de/en/cameras/item/sony-mvc-fd7), [MavicaZine — FD7](https://www.mavicazine.com/fd7/)
  - *Situs Fotografi / Ulasan:* [Guy J. Brown Collection — Sony Mavica MVC-FD7](https://collection.guyjbrown.com/blog/sony-mavica-mvc-fd7-camera/)
  - *Foto Sampel Diperiksa Visual (6 lintas 3 sumber):*
    1. [Wikimedia Commons (CC BY-SA 2.0) — *Numbers in a City New Haven 1997 SML* oleh See-ming Lee (kolase 100 bidikan Mavica FD7 1997)](https://commons.wikimedia.org/wiki/File:Numbers_in_a_City_New_Haven_1997_SML_(7994149144).jpg)
    2. [Mike Eckman / Wrotniak Archive — 2 berkas asli 640×480 (*flowr-f.jpg*, *boats-f.jpg* — diperiksa visual sementara lalu dihapus)](https://mikeeckman.com/wrotniak/photo/snap/fd7.html)
    3. [Stanford Mavica Notes — 3 berkas asli 640×480 (*2MVC-terrtowerF.jpg*, *2MVC-terrtablesF.jpg*, *2MVC-officeF.jpg* — diperiksa visual sementara lalu dihapus)](https://graphics.stanford.edu/~lucasp/Mavica/)

---

## 84. Canon — PowerShot G10

- **id:** `canon_powershot_g10`
- **brand:** `Canon`
- **model:** `PowerShot G10` *(nama kanonik)*
- **aliases:** `Canon G10`, `G10`
- **displayName:** `PowerShot G10`
- **Tahun & Jenis:** 2008 · Enthusiast compact CCD
- **Spesifikasi Inti (Resmi):** Sensor CCD 1/1,7" 14,7 MP efektif; prosesor DIGIC 4; lensa zoom optik 5x (ekuiv. 28–140mm) f/2.8–4.5 dengan Optical IS; ISO Auto, 80–1600; dukungan RAW (CR2).
- **Ketergantungan Mode Warna (Digital):** Karakter tajam-kontras dengan warna kaya hadir sejak **JPEG standar bawaan (default DIGIC 4)**; mode *My Colors* (Vivid, Positive Film, Neutral, B&W) tersedia untuk memodifikasi saturasi, sedangkan pemotretan **RAW** dapat meredam pemotongan sorotan langit terang dibanding JPEG bawaan.
- **Deskripsi Tampilan Hasil Foto (Inspeksi Visual + Sumber):**
  - **Ketajaman ISO Rendah:** Pada sampel pelat lokomotif merah (sampel 2) dan fasad hotel bermandikan matahari terik (sampel 3), ketajaman pada ISO rendah amat mengesankan — tekstur cat timbul, paku keling logam, garis krom, hingga dedaunan palem tergambar renyah dan bersih.
  - **Warna & Kontras:** Warna khas Canon tampil pekat, hangat, dan berwibawa — merah pelat lokomotif menyala padat berpadu hitam mengilap yang dalam, sedangkan arsitektur krem di bawah langit siang tetap bersih dan natural; kontras bawaan terasa tegas dan rapat.
  - **Sorotan (*Highlight*):** Rentang dinamis sensor CCD padat piksel masih terbatas pada adegan terik: pantulan matahari pada kap mobil dan langit siang cerah (sampel 3) maupun pusat lampu gantung rotan senja (sampel 1) cepat menyentuh putih penuh.
  - **Noise Cahaya Redup:** Pada teras restoran senja (sampel 1), terlihat langsung karakter khas G10 melewati ISO rendah: butiran *noise* luminansi dan sedikit bintik warna muncul jelas di area bantal putih, kayu gelap, serta langit senja, selaras dengan catatan DPReview dan Camera Labs mengenai naiknya *noise* cepat di atas ISO 200–400.
- **lookTags:** `sharp-base-iso`, `punchy-color`, `high-contrast`, `clipped-highlights`, `noisy-high-iso`
- **Arketipe:** `ccd_compact_2000s` *(dikonfirmasi)*
- **confidenceLook:** **`high`** — 3 foto sampel EXIF `Canon PowerShot G10` lintas 3 kondisi pencahayaan (senja ISO tinggi, jarak dekat mengilap, siang terik) sangat selaras dengan DPReview, Imaging Resource, dan Camera Labs.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Canon USA Support — PowerShot G10 Specifications](https://support.usa.canon.com/kb/s/article/ART105000)
  - *Situs Fotografi / Ulasan:* [DPReview — Canon PowerShot G10 Review](https://www.dpreview.com/reviews/5619014799/canong10/), [Imaging Resource — Canon G10 Review](https://www.imaging-resource.com/cameras/canon-g10-review/), [Camera Labs — Canon PowerShot G10](https://www.cameralabs.com/canon_powershot_g10/5/)
  - *Foto Sampel Diperiksa Visual (3, EXIF Canon PowerShot G10):*
    1. [Wikimedia Commons (CC BY-SA 2.0) — *"A" restaurant in Hauz Khas Village* oleh tara hunt (EXIF: PowerShot G10, senja)](https://commons.wikimedia.org/wiki/File:%22A%22_restaurant_in_Hauz_Khas_Village,_New_Delhi_India_2013.jpg)
    2. [Wikimedia Commons (CC BY-SA 2.0) — *"Hardwicke"* oleh James Petts (EXIF: PowerShot G10, detail dekat)](https://commons.wikimedia.org/wiki/File:%22Hardwicke%22_-_Flickr_-_James_E._Petts.jpg)
    3. [Wikimedia Commons (CC BY-SA 2.0) — *"INTOURIST PALACE"* oleh Peretz Partensky (EXIF: PowerShot G10, siang terik)](https://commons.wikimedia.org/wiki/File:%22INTOURIST_PALACE%22_(3846213343).jpg)

---

## 85. Canon — IXY Digital 110 IS (PowerShot SD1200 IS / Digital IXUS 95 IS)

- **id:** `canon_ixy_digital_110_is`
- **brand:** `Canon`
- **model:** `IXY Digital 110 IS` *(nama kanonik)*
- **aliases:** `PowerShot SD1200 IS` (Amerika Utara), `Digital IXUS 95 IS` (Eropa/Asia)
- **displayName:** `IXY Digital 110 IS (PowerShot SD1200 IS / Digital IXUS 95 IS)`
- **Tahun & Jenis:** 2009 · Digital compact CCD
- **Spesifikasi Inti (Resmi):** Sensor CCD 1/2,3" 10,0 MP efektif; prosesor DIGIC 4; lensa zoom optik 3x (ekuiv. 35–105mm) f/2.8–4.9 dengan Optical IS; ISO 80–1600.
- **Ketergantungan Mode Warna (Digital):** Palet warna hangat-hidup yang ramah kulit dihasilkan langsung pada **mode Auto/Program JPEG bawaan** (dengan bantuan *i-Contrast* opsional untuk menahan bayangan); opsi *My Colors* tersedia, namun seluruh sampel yang diperiksa adalah keluaran JPEG standar kamera.
- **Deskripsi Tampilan Hasil Foto (Inspeksi Visual + Sumber):**
  - **Warna & White Balance:** Di cahaya sore cerah pada gereja bata terakota (sampel 2) dan rambu jalan berlatar langit biru (sampel 3), warna khas Canon tampil cerah, hangat, dan menyenangkan — bata merah-oranye, hijau cemara, serta biru langit terlihat segar tanpa terasa berlebihan; di cahaya musim dingin dekat jendela (sampel 1), keseimbangan putih tetap rapi bernuansa sejuk bersih.
  - **Ketajaman & Kelembutan Tepi:** Pusat bingkai cukup tajam untuk kelas saku 2009 (tekstur reflektif rambu jalan dan ornamen gereja tampak rapi), tetapi dedaunan jauh serta sudut bingkai menampilkan kelembutan halus yang memberi kesan ringan dan nostalgia.
  - **Sorotan, Noise & Flash:** Pada latar salju dekat jendela (sampel 1), sorotan terang langsung penuh putih lembut dan permukaan plastik dalam ruang menunjukkan butiran *noise* halus disertai sedikit penghalusan tekstur khas DIGIC 4 saat ISO meningkat; menurut Camera Labs dan Imaging Resource, ISO 800–1600 tampak kasar berbutir dan *flash* langsung menghasilkan potret malam berkontras kuat dengan latar cepat menggelap.
- **lookTags:** `warm-skin`, `vivid-daylight`, `gentle-softness`, `noise-smoothing`, `direct-flash`
- **Arketipe:** `ccd_dreamy_pastel` *(dikonfirmasi)*
- **confidenceLook:** **`high`** — 3 foto sampel EXIF lintas dua nama wilayah (`Canon DIGITAL IXUS 95 IS` dan `Canon PowerShot SD1200 IS`) selaras dengan ulasan Camera Labs, Imaging Resource, dan forum DPReview.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Canon Indonesia Support — IXY Digital 110 IS / Digital IXUS 95 IS](https://id.canon/id/support/6200040400)
  - *Situs Fotografi / Ulasan:* [Camera Labs — PowerShot SD1200 IS / IXUS 95 IS](https://www.cameralabs.com/canon_powershot_sd1200_is_ixus_95_is/3/), [Imaging Resource — Canon SD1200 IS Review](https://www.imaging-resource.com/cameras/canon-sd1200-is-review/)
  - *Forum / Komunitas:* [DPReview Forum — PowerShot SD1200 IS / IXUS 95 IS](https://www.dpreview.com/forums/threads/canon-powershot-sd1200-is-digital-ixus-95-is-review.3407472/)
  - *Foto Sampel Diperiksa Visual (3, EXIF SOOC):*
    1. [Wikimedia Commons (CC0) — *- 15°C im Winter 2012* oleh Florian Jesse (EXIF: Canon DIGITAL IXUS 95 IS, dekat jendela musim dingin)](https://commons.wikimedia.org/wiki/File:-_15%C2%B0C_im_Winter_2012.jpg)
    2. [Wikimedia Commons (CC BY-SA 3.0) — *Церковь Святой Троицы* oleh Elena sch (EXIF: Canon DIGITAL IXUS 95 IS, cahaya sore)](https://commons.wikimedia.org/wiki/File:.%D1%86%D0%B5%D1%80%D0%BA%D0%BE%D0%B2%D1%8C_%D0%A1%D0%B2%D1%8F%D1%82%D0%BE%D0%B9_%D0%A2%D1%80%D0%BE%D0%B8%D1%86%D1%8B.JPG)
    3. [Wikimedia Commons (CC BY 3.0) — *0 AR 38 St Francis County* oleh Thomas R Machnitzki (EXIF: Canon PowerShot SD1200 IS, siang cerah)](https://commons.wikimedia.org/wiki/File:0_AR_38_St_Francis_County_AR_2013-08-04_013.jpg)

---

## 86. Fujifilm — FinePix F10

- **id:** `fujifilm_finepix_f10`
- **brand:** `Fujifilm`
- **model:** `FinePix F10` *(nama kanonik)*
- **aliases:** `Fuji F10`, `F10`
- **displayName:** `FinePix F10`
- **Tahun & Jenis:** 2005 · Digital compact Super CCD HR
- **Spesifikasi Inti:** Sensor Super CCD HR generasi ke-5 1/1,7" 6,3 MP efektif; prosesor Real Photo; lensa Fujinon 3x zoom (ekuiv. 36–108mm) f/2.8–5.0; ISO Auto, 80–1600. *(Halaman spesifikasi resmi produsen aktif tidak ditemukan.)*
- **Ketergantungan Mode Warna (Digital):** Karakter warna hangat bernuansa merah kaya dan performa ISO tinggi bersih muncul langsung pada **mode bawaan (F-Standard SOOC JPEG)**; kamera juga menyediakan mode *F-Chrome* (lebih jenuh ala film slide) dan *B&W*, tetapi ketiga foto sampel EXIF menunjukkan langsung keluaran khas firmware bawaan F10.
- **Deskripsi Tampilan Hasil Foto (Inspeksi Visual + Sumber):**
  - **Warna & Rona Merah Khas Fuji:** Pada interior gereja kayu (sampel 2), warna kayu pinus keemasan dan **karpet merah menyala** tampil amat kaya, hangat, dan organik — ciri khas reproduksi merah Fujifilm Super CCD; di bawah kabut muara redup (sampel 1), derek merah dan lambung berkarat tetap terpisah jernih dari langit kelabu.
  - **Cahaya Redup & Karakter Noise Super CCD:** Pada foto kapal kargo di cahaya sangat buruk (sampel 1) dan interior gereja tanpa *flash* (sampel 2), terlihat mengapa F10 menjadi legenda 2005: bayangan tetap bersih dan terstruktur dengan pola butiran luminansi halus yang seragam menyerupai *grain* film tipis, jauh dari bercak warna kasar digicam 2005 lain.
  - **Eksposur & Sorotan (*Highlight*):** Pengamatan langsung pada benteng batu di bawah matahari terik (sampel 3) dan jendela gereja (sampel 2) mengonfirmasi temuan DPReview dan Imaging Resource: pengukuran cahaya bawaan F10 cenderung sedikit kelebihan eksposur sehingga langit terang, dinding batu diterpa matahari langsung, serta bingkai jendela mudah memudar penuh ke putih.
- **lookTags:** `clean-high-iso`, `warm-reds`, `film-like-grain`, `slight-overexposure`, `smooth-lowlight`
- **Arketipe:** `pro_ccd_filmic` *(diperbarui dari `ccd_dreamy_pastel` di `batch_05.md` agar selaras dengan definisi sensor Super CCD HR 1/1,7" berpola grain organik milik penerusnya FinePix F31fd di `archetypes.md`)*
- **confidenceLook:** **`high`** — 3 foto sampel EXIF `FinePix F10` lintas cahaya kabut sangat redup, interior tanpa flash, dan siang terik mengonfirmasi secara persis seluruh temuan DPReview, Imaging Resource, dan Reviewed.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Situs Fotografi / Ulasan:* [DPReview — Fujifilm FinePix F10 Review](https://www.dpreview.com/reviews/1581648897/fujifilmf10zoom/), [Imaging Resource — FinePix F10 Review](https://www.imaging-resource.com/news/fujifilm-finepix-f10-review-posted/), [Imaging Resource — FinePix F10 Data](https://old.imaging-resource.com/PRODS/F10/F10DAT.HTM), [Reviewed — Fujifilm FinePix F10 Review](https://www.reviewed.com/cameras/content/fujifilm-finepix-f10-digital-camera-review)
  - *Foto Sampel Diperiksa Visual (3, EXIF FinePix F10):*
    1. [Wikimedia Commons (CC BY 2.0) — *"OMSKIY-133" at Barrow Havenn* oleh David Wright (EXIF: FinePix F10, cahaya kabut redup)](https://commons.wikimedia.org/wiki/File:%22OMSKIY-133%22_at_Barrow_Havenn.jpg)
    2. [Wikimedia Commons (CC BY-SA 3.0) — *01paluknio baznycia2010* oleh Anaiptol (EXIF: FinePix F10 Ver1.02 SOOC, interior kayu & karpet merah)](https://commons.wikimedia.org/wiki/File:01paluknio_baznycia2010.jpg)
    3. [Wikimedia Commons (CC BY-SA 3.0) — *Vista de la muralla del Castell de Sant Ferran* oleh Francesc Vernet López (EXIF: FinePix F10 Ver1.00 SOOC, siang terik)](https://commons.wikimedia.org/wiki/File:02._Vista_de_la_muralla_i_el_fossat_del_Castell_de_Sant_Ferran_de_Figueres.JPG)

---

## 87. Nikon — Coolpix S3300

- **id:** `nikon_coolpix_s3300`
- **brand:** `Nikon`
- **model:** `Coolpix S3300` *(nama kanonik)*
- **aliases:** `COOLPIX S3300`, `Nikon S3300`
- **displayName:** `Coolpix S3300`
- **Tahun & Jenis:** 2012 · Ultra-compact CCD
- **Spesifikasi Inti (Resmi):** Sensor CCD 1/2,3" 16,0 MP efektif; lensa Zoom-NIKKOR 6x optik (ekuiv. 26–156mm) f/3.3–6.5 dengan Lens-shift VR; ISO 80–3200.
- **Ketergantungan Mode Warna (Digital):** Seluruh ciri visual yang diamati berasal dari **setelan bawaan (Auto/Standard SOOC JPEG)**; kamera memiliki efek *Filter Effects* (Soft, Nostalgic Sepia, High-Contrast Mono), namun ketiga foto sampel EXIF `COOLPIX S3300V1.0` merupakan keluaran standar langsung dari kamera.
- **Deskripsi Tampilan Hasil Foto (Inspeksi Visual + Sumber):**
  - **Warna & White Balance:** Di siang hari cerah maupun teduh, keseimbangan putih dan warna tergolong cukup akurat dan netral — dinding plester abu-abu serta salib hijau apotek (sampel 1), plester merah muda pucat (sampel 3), hingga hijau dedaunan hutan (sampel 2) tampak wajar tanpa penyimpangan warna ekstrem.
  - **Ketajaman Tidak Merata & Peredaman Noise:** Pada jalan setapak hutan (sampel 2) dan bangunan sudut lebar (sampel 1), area tengah terlihat dipertajam secara digital hingga dedaunan agak renyah keras, sedangkan tepi dan sudut bingkai cepat melunak disertai sedikit pendaran kromatik di tepi atap. Tekstur plester dinding (sampel 3) memperlihatkan efek peredaman *noise* yang membuat detail mikro agak licin khas sensor CCD 16 MP rapat piksel.
  - **Sorotan & Cahaya Redup:** Langit di balik bangunan (sampel 1) dan celah kanopi pepohonan (sampel 2) cepat penuh terbakar putih rata; menurut pengujian lab Reviewed, di atas ISO 400 kualitas gambar turun tajam karena lonjakan *noise* sehingga pemotretan dalam ruang bergantung pada *flash* langsung.
- **lookTags:** `accurate-daylight`, `soft-edges`, `oversharpened-center`, `aggressive-nr`, `clipped-highlights`
- **Arketipe:** `ccd_dreamy_pastel` *(dikonfirmasi)*
- **confidenceLook:** **`high`** *(naik dari `medium` di `batch_05.md`)* — Kini didukung inspeksi visual langsung 3 foto SOOC EXIF `COOLPIX S3300V1.0` lintas 3 pengunggah berbeda di Wikimedia Commons, konsisten dengan uji lab Reviewed dan ringkasan Imaging Resource.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Nikon USA — Coolpix S3300 Overview](https://www.nikonusa.com/p/coolpix-s3300/26309/overview)
  - *Situs Fotografi / Ulasan:* [Reviewed — Nikon Coolpix S3300 Review](https://www.reviewed.com/cameras/content/nikon-coolpix-s3300-review), [Imaging Resource — Nikon S3300 Review](https://www.imaging-resource.com/cameras/nikon-s3300-review/)
  - *Foto Sampel Diperiksa Visual (3, EXIF COOLPIX S3300V1.0 SOOC):*
    1. [Wikimedia Commons (CC BY-SA 3.0) — *"Zelený dom" na Hlavnom námestí v Bratislave* oleh Lukáš Perný (EXIF: COOLPIX S3300, sudut lebar kota)](https://commons.wikimedia.org/wiki/File:%22Zelen%C3%BD_dom%22_na_Hlavnom_n%C3%A1mest%C3%AD_v_Bratislave.JPG)
    2. [Wikimedia Commons (CC BY-SA 3.0) — *"Божа стежка" до джерела* oleh Oksana Huliar (EXIF: COOLPIX S3300, hutan cerah)](https://commons.wikimedia.org/wiki/File:%22%D0%91%D0%BE%D0%B6%D0%B0_%D1%81%D1%82%D0%B5%D0%B6%D0%BA%D0%B0%22_%D0%B4%D0%BE_%D0%B4%D0%B6%D0%B5%D1%80%D0%B5%D0%BB%D0%B0.JPG)
    3. [Wikimedia Commons (CC BY-SA 3.0) — *0.5 Atlantic* oleh ProfReader (EXIF: COOLPIX S3300, fasad siang)](https://commons.wikimedia.org/wiki/File:0.5_Atlantic.jpg)

---

## 88. Casio — Exilim EX-Z1080

- **id:** `casio_exilim_ex_z1080`
- **brand:** `Casio`
- **model:** `Exilim EX-Z1080` *(nama kanonik)*
- **aliases:** `EX-Z1080`, `Casio Z1080`
- **displayName:** `Exilim EX-Z1080`
- **Tahun & Jenis:** 2007 · Digital compact CCD
- **Spesifikasi Inti (Resmi):** Sensor CCD 1/1,75" 10,1 MP efektif; prosesor Exilim Engine 2.0; lensa zoom optik 3x (ekuiv. 38–114mm) f/2.8–5.1; tanpa stabilisasi optik; ISO 50/80–400 (ekspansi hingga 800–6400 pada mode Anti-Shake/Best Shot).
- **Ketergantungan Mode Warna (Digital):** Ciri hangat-pekat di siang hari dan kelembutan ber-*noise* pada malam hari muncul langsung pada **JPEG bawaan (Auto / Best Shot standar)**; kamera menawarkan puluhan adegan *Best Shot* (termasuk Anti-Shake berbasis ISO tinggi), namun foto sampel yang diperiksa adalah hasil asli sebelum olah efek tambahan.
- **Deskripsi Tampilan Hasil Foto (Inspeksi Visual + Sumber):**
  - **Ketajaman & Warna Siang Hari:** Pada jarak menengah-dekat mobil hitam (sampel 1) dan bentang perbukitan cerah (sampel 2), ketajaman pusat di ISO rendah tergolong sangat baik berkat sensor 1/1,75" yang sedikit lebih lega — emblem, jeruji velg, lampu rem merah pekat, hijau perbukitan, serta biru langit tampil hidup dan hangat, dengan sedikit pelunakan tipis ke arah sudut bingkai.
  - **Sorotan (*Highlight*):** Rentang dinamis masih sempit di bawah pantulan keras: kilau kaca dan atap mobil (sampel 1) serta papan reklame neon malam (sampel 3) cepat penuh menjadi putih rata disertai pendaran (*bloom*) terang.
  - **Cahaya Redup Tanpa OIS:** Sampel malam jalanan Nagoya (sampel 3) memperlihatkan kelemahan sekaligus ciri nostalgia utamanya: tanpa stabilisasi optik, pemotretan malam mendorong ISO tinggi dan rana lambat sehingga memicu rona kehijauan-sejuk di bawah lampu jalan, *noise* luminansi dan warna yang jelas, serta sedikit kabur gerak dan penghalusan detail halus.
- **lookTags:** `warm-vivid`, `sharp-center`, `clipped-highlights`, `soft-corners`, `noisy-lowlight`
- **Arketipe:** `ccd_dreamy_pastel` *(dikonfirmasi)*
- **confidenceLook:** **`high`** — 3 foto sampel lintas 2 platform independen (Wikimedia Commons EXIF `EX-Z1080` + 2 foto Flickr CC meliputi siang dan malam) konsisten dengan ulasan CNET dan komunitas DPReview.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Casio Archive — EX-Z1080 Specifications](http://arch.casio-intl.com/asia-mea/en/dc/past/ex_z1080/spec.html)
  - *Situs Fotografi / Ulasan:* [CNET — Casio Exilim Zoom EX-Z1080 Review](https://www.cnet.com/reviews/casio-exilim-zoom-ex-z1080-review/), [Imaging Resource — Casio EX-Z1080 Specs](https://www.imaging-resource.com/cameras/casio-ex-z1080-review/specifications/)
  - *Forum / Komunitas:* [DPReview Forum — Casio EX-Z1080 Review](https://www.dpreview.com/forums/threads/casio-exilim-ex-z1080-review.3410440/)
  - *Foto Sampel Diperiksa Visual (3 lintas 2 sumber):*
    1. [Wikimedia Commons (CC BY-SA 3.0) — *Alfa 147 rear* oleh M 93 (EXIF: EX-Z1080, teduh siang)](https://commons.wikimedia.org/wiki/File:Alfa_147_rear.jpg)
    2. [Flickr CC via Openverse (CC BY-SA 2.0) — *Original before Lomo Effect* oleh elsamuko (lanskap siang cerah)](https://www.flickr.com/photos/28653536@N07/2698587906)
    3. [Flickr CC via Openverse (CC BY-SA 2.0) — *Nagoya Casio Exilim z1080 noche* oleh chiclayonortea (malam perkotaan)](https://www.flickr.com/photos/11502309@N08/5455341289)

---

## 89. Olympus — µ [mju:] 700 (Stylus 710)

- **id:** `olympus_mju_700`
- **brand:** `Olympus`
- **model:** `µ [mju:] 700` *(nama kanonik)*
- **aliases:** `Stylus 710` (Amerika Utara), `µ 700`, `mju 700`, `u700D,S700D`, `u710,S710`
- **displayName:** `µ [mju:] 700 (Stylus 710)`
- **Tahun & Jenis:** 2006 · All-weather digital compact CCD
- **Spesifikasi Inti (Resmi):** Sensor CCD 1/2,3" 7,1 MP efektif; prosesor TruePic TURBO; lensa zoom optik 3x (ekuiv. 37–111mm) f/3.4–5.7; bodi tahan cuaca; ISO Auto, 64–1600 (Digital Image Stabilization berbasis kenaikan ISO).
- **Ketergantungan Mode Warna (Digital):** Karakter lembut-pastel dan sorotan mudah memudar berasal langsung dari **JPEG standar bawaan (TruePic TURBO default)** tanpa perlu mode efek khusus; mode *Digital Image Stabilization* mendorong ISO lebih tinggi di cahaya redup sehingga mempertebal butiran *noise*.
- **Deskripsi Tampilan Hasil Foto (Inspeksi Visual + Sumber):**
  - **Kelembutan & Kompresi JPEG:** Di ketiga foto sampel EXIF `u710,S710` (bus VW warna-warni di hari teduh, anak anjing di atas selimut merah muda, dan menara lonceng kayu kuil), keseluruhan gambar cenderung terasa **lembut** dengan detail halus yang cepat luruh akibat kombinasi optik berbukaan agak gelap, pemrosesan TruePic TURBO, dan kompresi JPEG cukup berat.
  - **Warna & Suasana Pastel:** Pada bus lukis dan kuil kayu (sampel 1 & 3), warna bayangan serta latar cenderung sejuk-kalem agak datar, sementara pada selimut merah muda dalam ruang (sampel 2) muncul nuansa pastel lembut yang sangat *dreamy* khas digicam kompak pertengahan 2000-an.
  - **Sorotan Memudar & Noise:** Pada menara lonceng kuil yang membelakangi pepohonan terang (sampel 3), cahaya latar langsung memutih memudar (*bleached highlights*) disertai pendaran halus di sela dedaunan; di bayangan beranda gelap (sampel 1) dan bulu gelap dalam ruang (sampel 2) tampak jelas butiran *noise* digital dan sedikit bercak warna halus.
- **lookTags:** `soft-focus`, `cool-muted`, `jpeg-grain`, `bleached-highlights`, `noisy-high-iso`
- **Arketipe:** `ccd_dreamy_pastel` *(dikonfirmasi)*
- **confidenceLook:** **`high`** — 3 foto sampel EXIF SOOC `u710,S710` lintas 3 kondisi pencahayaan (teduh penuh warna, dalam ruang pastel, cahaya latar kuat) sepenuhnya mengonfirmasi ulasan CNET, TechRadar, PCMag, dan DPReview Forum.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Olympus Official Manual PDF — Stylus 710 / µ 700](https://learnandsupport.getolympus.com/sites/default/files/media/files/2018/04/Stylus_710_Mju_710_Stylus_700_Mju_700_Basic_Manual_EN_FR_ES.pdf)
  - *Situs Fotografi / Ulasan:* [CNET — Olympus Stylus 710 Review](https://www.cnet.com/reviews/olympus-stylus-710-digital-camera-review/), [TechRadar — Olympus mju 700 Review](https://www.techradar.com/reviews/cameras-and-camcorders/cameras/compact-cameras/olympus-mju-700-63807/review), [PCMag — Olympus Stylus 710](https://uk.pcmag.com/cameras/25560/olympus-stylus-710)
  - *Forum / Komunitas:* [DPReview Forum — Stylus 700 / mju 700](https://www.dpreview.com/forums/threads/olympus-stylus-700-mju-700-digital-review.3418030/)
  - *Foto Sampel Diperiksa Visual (3, EXIF SOOC u710,S710):*
    1. [Wikimedia Commons (CC BY-SA 2.0) — *Hippie Bus Ferdinanda Beach* oleh Shibby777 (EXIF: u710,S710 Ver 1.0, teduh)](https://commons.wikimedia.org/wiki/File:Hippie_Bus_Ferdinanda_Beach_(2082525916).jpg)
    2. [Wikimedia Commons (Public domain) — *6-month-old Yorkshire Terrier on pink blanket* oleh Kadoda 03 (EXIF: u710,S710 Ver 1.1, dalam ruang)](https://commons.wikimedia.org/wiki/File:6-month-old_Yorkshire_Terrier_on_pink_blanket.jpg)
    3. [Wikimedia Commons (CC BY-SA 3.0) — *AMIDADERA-temple belfry* oleh user:KMR (EXIF: u710,S710 Ver 1.0, cahaya latar kuat)](https://commons.wikimedia.org/wiki/File:AMIDADERA-temple_belfry_(Nachi-Katsuura,_Wakayama).JPG)

---

## 90. Ricoh — GR Digital III

- **id:** `ricoh_gr_digital_iii`
- **brand:** `Ricoh`
- **model:** `GR Digital III` *(nama kanonik)*
- **aliases:** `GRD III`, `GRD3`, `GR DIGITAL 3`
- **displayName:** `GR Digital III`
- **Tahun & Jenis:** 2009 · Enthusiast fixed-lens compact CCD
- **Spesifikasi Inti (Resmi):** Sensor CCD 1/1,7" 10,0 MP efektif; prosesor GR Engine III; lensa tetap GR 6,0mm (ekuiv. 28mm) f/1.9; filter ND internal; ISO Auto, 64–1600; dukungan DNG RAW.
- **Ketergantungan Mode Warna (Digital):** Ketajaman optik luar biasa dan rona hangat-pekat bernuansa analog sudah tampak langsung pada **JPEG standar bawaan (default GR Engine III)** seperti pada ketiga sampel warna EXIF; namun sebagian besar reputasi ikonik kamera ini juga bertumpu pada **mode JPEG khusus dalam kamera**, terutama *High Contrast B&W* (hitam-putih pekat bertekstur *gritty* seperti film *pushed*) dan *Positive Film* (warna pekat berkontras kuat) — keduanya dapat pula diolah dari **DNG RAW**.
- **Deskripsi Tampilan Hasil Foto (Inspeksi Visual + Sumber):**
  - **Ketajaman & Optik 28mm f/1.9:** Pada ketiga sampel EXIF `GR DIGITAL 3` (gedung berlatar langit biru, hidangan soba jarak dekat, dan koridor kayu Kyoto Art Center), lensa tetap 28mm f/1.9 memperlihatkan ketajaman menggigit merata dari tengah hingga sudut dengan garis arsitektur amat lurus minim distorsi serta ruang tajam luas khas sensor 1/1,7".
  - **Warna, Kontras & Bayangan Pekat:** Warna JPEG bawaan tampil padat, berkarakter, dan tidak steril — biru langit kobalt dalam, merah tanda seru yang kaya, serta serat kayu dan nampan lak hitam yang pekat seperti tinta (sampel 2 & 3). Kontras bawaan tegas namun berkelas, menciptakan nuansa *moody* pada koridor berbayangan dalam.
  - **Sorotan & Pola Grain ISO Tinggi:** Pada koridor berkontras tinggi (sampel 3), kaca jendela yang diterpa cahaya luar cepat penuh putih disertai pendaran halus tipis pada kusen gelap. Selaras dengan ulasan Steve Huff Photo dan The Inspired Eye, saat dinaikkan ke ISO 800–1600 (terutama pada mode monokrom), *noise* luminansnya tampil rapat dan organik menyerupai butiran film *pushed* alih-alih bercak digital yang mengganggu.
- **lookTags:** `edge-to-edge-sharp`, `gritty-monochrome`, `film-like-grain`, `deep-blacks`, `moody-ccd`
- **Arketipe:** `pro_ccd_filmic` *(dikonfirmasi)*
- **confidenceLook:** **`high`** — 3 foto sampel SOOC EXIF `GR DIGITAL 3` lintas 3 pencahayaan berbeda (langit biru cerah, jarak dekat hangat, koridor berkontras tinggi) sangat selaras dengan Steve Huff Photo dan The Inspired Eye.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Ricoh Imaging — GR Digital III Specifications](https://www.ricoh-imaging.co.jp/english/r_dc/gr/gr_digital3/specs.html)
  - *Situs Fotografi / Ulasan:* [Steve Huff Photo — Ricoh GR Digital III Review](https://www.stevehuffphoto.com/2011/01/22/the-ricoh-gr-digital-iii-review/), [The Inspired Eye — Ricoh GRD III Review](https://www.theinspiredeye.net/ricoh/ricoh-grd-iii-review/index.html), [DPReview — Ricoh GR Digital III](https://www.dpreview.com/products/ricoh/compacts/ricoh_grdigital3/)
  - *Foto Sampel Diperiksa Visual (3, EXIF SOOC GR DIGITAL 3):*
    1. [Wikimedia Commons (CC BY 2.0) — *!? びっくり箱* oleh yy (EXIF: GR DIGITAL 3, siang cerah)](https://commons.wikimedia.org/wiki/File:!%3F_%E3%81%B3%E3%81%A3%E3%81%8F%E3%82%8A%E7%AE%B1_(6202724549).jpg)
    2. [Wikimedia Commons (CC BY-SA 2.0) — *Combo of soba noodle and tempura* oleh pelican (EXIF: GR DIGITAL 3, jarak dekat hangat)](https://commons.wikimedia.org/wiki/File:Combo_of_soba_noodle_and_tempura_(4808586338).jpg)
    3. [Wikimedia Commons (CC BY-SA 2.0) — *Corridor - Kyoto Art Center* oleh Lisa Pinehill (EXIF: GR DIGITAL 3, arsitektur interior)](https://commons.wikimedia.org/wiki/File:Corridor_-_Kyoto_Art_Center.jpg)

---

## 91. Sigma — DP1 Merrill

- **id:** `sigma_dp1_merrill`
- **brand:** `Sigma`
- **model:** `DP1 Merrill` *(nama kanonik)*
- **aliases:** `DP1M`, `Sigma DP1M`
- **displayName:** `DP1 Merrill`
- **Tahun & Jenis:** 2012 · Fixed-lens APS-C Foveon compact
- **Spesifikasi Inti (Resmi):** Sensor APS-C Foveon X3 Direct Image CMOS 3 lapis RGB (15,4 MP × 3, tanpa filter AA); lensa tetap 19mm f/2.8 (ekuiv. 28mm); ISO 100–6400; RAW X3F.
- **Ketergantungan Mode Warna (Digital):** Karakter ketajaman ekstrem dan kontras mikro paling optimal berasal dari berkas **Foveon X3 RAW (X3F) yang dikembangkan lewat *Sigma Photo Pro* (SPP)** (seperti pada sampel 1 & 3 yang tercatat EXIF `SIGMA Photo Pro 6.3.0`), meski mode warna bawaan (*Standard*, *Vivid*, *Landscape*, *Monochrome*) juga mewarisi perincian per-piksel tanpa interpolasi Bayer.
- **Deskripsi Tampilan Hasil Foto (Inspeksi Visual + Sumber):**
  - **Ketajaman Per-Piksel & Kontras Mikro:** Pada fasad rumah bersejarah Anklam (sampel 1 & 3) serta menara kaca Andromeda Vienna (sampel 2), tampilan Foveon X3 Merrill langsung membedakan diri dari kamera APS-C biasa: setiap butir plester kasar, genteng atap, cat terkelupas, batu jalanan, hingga kisi-kisi kaca gedung muncul dengan **ketajaman sehalus silet dan kontras mikro luar biasa** sehingga bidang datar pun terasa berdimensi tiga (*3D relief*) mendekati format lebih besar.
  - **Warna & Gradasi Tonal:** Pada ISO dasar di cahaya sore (sampel 1 & 3), warna tampil padat, dalam, dan organik menyerupai film slide — biru langit jernih, pintu hijau tua mengilap, serta dinding terakota dan abu-abu plester menampilkan separasi rona halus tanpa kesan plastik digital.
  - **Batas ISO Tinggi:** Selaras dengan pengujian Imaging Resource, PetaPixel, dan Vieri Bottazzini, kinerja sensor 3 lapis ini sangat menuntut cahaya cukup di ISO 100–200; melewati ISO 400 kualitas warna dan bayangan cepat menurun dengan munculnya bercak *noise* warna kasar (yang kerap diakali fotografer dengan konversi monokrom bergradasi dalam).
- **lookTags:** `razor-sharp`, `extreme-micro-contrast`, `3d-depth`, `rich-monochrome`, `harsh-high-iso`
- **Arketipe:** `pro_ccd_filmic` *(dikonfirmasi)*
- **confidenceLook:** **`high`** — 3 foto sampel terverifikasi `SIGMA DP1 Merrill` (termasuk *Quality Image* Wikimedia dan hasil olahan SPP langsung) sangat konsisten dengan Imaging Resource, PetaPixel, dan Vieri Bottazzini.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Sigma Global — DP1 Merrill](https://www.sigma-global.com/en/cameras/dp1-merrill/), [Sigma Catalog PDF](https://www.sigma-global.com/en/support/download/DP3Merrill_Catalog2013_EN_PrinterRes.pdf)
  - *Situs Fotografi / Ulasan:* [Imaging Resource — Sigma DP1 Merrill Review](https://www.imaging-resource.com/cameras/sigma-dp1-merrill-review/), [PetaPixel — Sigma DP1 Merrill Review](https://petapixel.com/2012/10/27/review-sigma-dp1-merrill-is-slow-and-clunky-but-its-photos-are-amazing/), [Vieri Bottazzini — DP1 Merrill & DP2 Merrill Review](https://www.vieribottazzini.com/2013/01/sigma-dp1-merrill-sigma-dp2-merrill-review.html)
  - *Foto Sampel Diperiksa Visual (3, terverifikasi DP1 Merrill):*
    1. [Wikimedia Commons (CC BY-SA 4.0) — *Baudenkmal Nr. 102 Anklam* oleh Elisauer (EXIF: SIGMA DP1 Merrill, SPP 6.3.0)](https://commons.wikimedia.org/wiki/File:Baudenkmal_Nr._102_Anklam_Heilige-Geist-Str._20.jpg)
    2. [Wikimedia Commons (CC BY-SA 4.0) — *Andromeda Tower Vienna* oleh Robert F. Tobler (Kategori: Taken with Sigma DP1 Merrill, Quality Image)](https://commons.wikimedia.org/wiki/File:Andromeda_Tower_Vienna_from_SW_on_2014-09-24.png)
    3. [Wikimedia Commons (CC BY-SA 4.0) — *Baudenkmal Nr. 103 Anklam* oleh Elisauer (EXIF: SIGMA DP1 Merrill, SPP 6.3.0)](https://commons.wikimedia.org/wiki/File:Baudenkmal_Nr._103_Anklam_Heilige-Geist-Stra%C3%9Fe_21.jpg)

---

## 92. Fujifilm — X-Pro1

- **id:** `fujifilm_x_pro1`
- **brand:** `Fujifilm`
- **model:** `X-Pro1` *(nama kanonik)*
- **aliases:** `Fuji X-Pro1`, `Fujifilm X-Pro 1`
- **displayName:** `X-Pro1`
- **Tahun & Jenis:** 2012 · APS-C X-Trans mirrorless
- **Spesifikasi Inti (Resmi):** Sensor APS-C X-Trans CMOS generasi pertama 16,3 MP tanpa filter low-pass/AA; mount Fujifilm X; Hybrid Multi Viewfinder (OVF/EVF); ISO 200–6400 (ekspansi 100, 12800–25600 JPEG).
- **Ketergantungan Mode Warna (Digital):** Karakter warna organik khas Fujifilm hadir kuat pada **JPEG bawaan lewat *Film Simulations*** (*Provia/Standard* sebagai setelan dasar, *Velvia* untuk hijau/warna lebih pekat, *Astia* untuk kulit lembut, serta *Pro Neg. Std/Hi* dan *Monochrome*); berkas RAW (RAF) juga dapat menerapkan profil simulasi serupa saat pengembangan (seperti sampel 1 & 2 di Lightroom).
- **Deskripsi Tampilan Hasil Foto (Inspeksi Visual + Sumber):**
  - **Warna & Simulasi Film:** Pada vila Mediterania (sampel 1), jalanan Osaka (sampel 2), dan sungai berhutan hijau (sampel 3), reproduksi warna X-Trans I terasa sangat sedap dipandang dan bernuansa film — plester krem-terakota hangat, merah marun truk yang dalam, serta hijau cemara dan rimba yang kaya namun tetap alami tanpa nada neon digital.
  - **Tekstur X-Trans & Kontras Mikro:** Ketiadaan filter AA menghasilkan ketajaman renyah pada daun cemara, bata dinding, papan Jepang, hingga bulu basah anjing di air (sampel 3), dengan kesan tekstur organik khas X-Trans generasi pertama yang tidak terasa kaku atau terlalu dipertajam.
  - **Transisi Sorotan & Grain ISO Tinggi:** Langit mendung di atas vila (sampel 1) dan pantulan kilau permukaan air (sampel 3) meluruh lembut tanpa batas patah kasar; konsisten dengan pengujian DPReview, Imaging Resource, Photography Life, dan Camera Labs, pada ISO 1600–6400 pola *noise*-nya tampil rapat, seragam, dan monokromatik menyerupai butiran film.
- **lookTags:** `x-trans-organic`, `film-sim`, `natural-skin`, `film-like-grain`, `crisp-micro-contrast`
- **Arketipe:** `filmic_digital_mirrorless` *(dikonfirmasi)*
- **confidenceLook:** **`high`** — 3 foto sampel lintas 2 platform (Wikimedia Commons EXIF `X-Pro1` + Flickr CC) konsisten sepenuhnya dengan DPReview, Imaging Resource, Photography Life, dan Camera Labs.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Fujifilm Hong Kong — X-Pro1 Specifications](https://www.fujifilm.com.hk/products/digital_cameras/x/fujifilm_x_pro1/specifications/), [Fujifilm X-Pro1 Manual PDF](https://dl.fujifilm-x.com/support/manual/x/fujifilm_xpro1_manual_en.pdf)
  - *Situs Fotografi / Ulasan:* [DPReview — Fujifilm X-Pro1 Review](https://www.dpreview.com/reviews/4019317027/fujifilm-x-pro1/), [Imaging Resource — Fujifilm X-Pro1 Review](https://www.imaging-resource.com/cameras/fujifilm-x-pro1-review/), [Photography Life — Fuji X-Pro1 Review](https://photographylife.com/reviews/fuji-x-pro1/2), [Camera Labs — Fujifilm X-Pro1](https://www.cameralabs.com/fujifilm_x-pro1/2/)
  - *Foto Sampel Diperiksa Visual (3 lintas 2 sumber):*
    1. [Wikimedia Commons (CC BY 2.0) — *2016 Grevillia* oleh thierrytutin (EXIF: X-Pro1, mendung arsitektur)](https://commons.wikimedia.org/wiki/File:2016_Grevillia_(30019924205).jpg)
    2. [Wikimedia Commons (CC BY 2.0) — *7-Eleven Ōsaka Sōemonchō-higashi* oleh bongs Lee (EXIF: X-Pro1, jalanan siang)](https://commons.wikimedia.org/wiki/File:7-Eleven_%C5%8Csaka_S%C5%8Demonch%C5%8D-higashi_in_Ch%C5%AB%C5%8D-ku,_gelegen_aan_de_Sakai-suji_straat,_-16_juli_2016_11%EF%BC%9A44.jpg)
    3. [Flickr CC via Openverse (CC BY 2.0) — *Australian Shepherd Mitch playing in the creek* oleh Ted Van Pelt (X-Pro1 + Touit 32mm)](https://www.flickr.com/photos/28914673@N02/18820286291)

---

## 93. Sony — Cyber-shot DSC-RX100 (Mark I)

- **id:** `sony_cyber_shot_dsc_rx100`
- **brand:** `Sony`
- **model:** `Cyber-shot DSC-RX100` *(nama kanonik)*
- **aliases:** `DSC-RX100`, `RX100 Mark I`, `RX100 I`, `RX100M1`
- **displayName:** `Cyber-shot DSC-RX100 (Mark I)`
- **Tahun & Jenis:** 2012 · 1-inch enthusiast compact CMOS
- **Spesifikasi Inti (Resmi):** Sensor 1.0-type Exmor CMOS 20,2 MP efektif; prosesor BIONZ; lensa Carl Zeiss Vario-Sonnar T* 3,6x zoom (ekuiv. 28–100mm) f/1.8–4.9 dengan Optical SteadyShot; ISO 125–6400; dukungan RAW (ARW).
- **Ketergantungan Mode Warna (Digital):** Karakter bersih, netral, dan berentang dinamis lega hadir langsung pada **JPEG standar bawaan (Creative Style: Standard + DRO)** (seperti sampel 2 & 3 EXIF SOOC `DSC-RX100 v1.00`), sementara pemotretan **RAW** membuka ruang pemulihan bayangan dan sorotan lebih jauh; kamera juga menyediakan *Creative Styles* (Vivid, Deep, B&W) dan *Picture Effects*.
- **Deskripsi Tampilan Hasil Foto (Inspeksi Visual + Sumber):**
  - **Rentang Dinamis & Warna:** Melompat jauh dari digicam 1/2,3": pada menara lonceng putih di bawah langit biru terik (sampel 2), sensor 1 inci menjaga tekstur plester putih terkena sinar matahari sekaligus menahan detail dedaunan di bayangan gelap; pada mobil sport merah di aspal basah mendung (sampel 1), warna merah rubi dan abu-abu basah tampil jernih serta natural tanpa dorongan saturasi berlebihan.
  - **Ketajaman & Optik Zeiss:** Bagian tengah hingga bidang menengah sangat tajam dan rapi (detail lengkung bodi mobil, tekstur menara, hingga ukiran ikonostasis dalam gereja), sementara menurut DPReview dan Ming Thein tepi ekstrem pada sudut lebar f/1.8 dan ujung tele 100mm sedikit melunak.
  - **Cahaya Redup & Noise:** Pada interior gereja redup tanpa *flash* (sampel 3, SOOC `DSC-RX100 v1.00`), lampu kristal menyala terang namun detail kain beludru merah marun, emas, dan marmer lantai tetap bersih dengan butiran *noise* halus yang terkendali rapi; baru pada ISO 3200–6400 peredaman *noise* JPEG BIONZ mulai menghaluskan detail mikro secara kentara.
- **lookTags:** `wide-dr`, `crisp-center`, `natural-color`, `clean-iso1600`, `soft-tele-edges`
- **Arketipe:** `early_cmos_gritty` *(dikonfirmasi)*
- **confidenceLook:** **`high`** — 3 foto sampel EXIF `DSC-RX100` (bukan varian M2–M7) lintas mendung basah, siang terik, dan interior sangat redup SOOC konsisten sepenuhnya dengan DPReview, Imaging Resource, Steve Huff Photo, dan Ming Thein.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Sony Support — DSC-RX100 Specifications](https://www.sony.co.id/en/electronics/support/compact-cameras-dsc-rx-series/dsc-rx100/specifications)
  - *Situs Fotografi / Ulasan:* [DPReview — Sony Cyber-shot DSC-RX100 Review](https://www.dpreview.com/reviews/2079759241/sony-cybershot-dsc-rx100/), [Imaging Resource — Sony RX100 Review](https://www.imaging-resource.com/cameras/sony-rx100-review/), [Steve Huff Photo — Sony RX100 Review](https://www.stevehuffphoto.com/2012/07/26/the-sony-rx100-digital-camera-review/), [Ming Thein — Sony RX100 Comparative Review](https://blog.mingthein.com/2012/08/06/the-sony-rx100-a-somewhat-comparative-review/)
  - *Foto Sampel Diperiksa Visual (3, EXIF DSC-RX100 Mark I):*
    1. [Wikimedia Commons (CC BY-SA 2.0) — *FERRARI F12Berlinetta* oleh Falcon 33 (EXIF: DSC-RX100, mendung basah)](https://commons.wikimedia.org/wiki/File:%2214_-_ITALIAN_Supercar_-_FERRARI_F12Berlinetta_-_red_coupes_facing_right.jpg)
    2. [Wikimedia Commons (CC BY-SA 3.0) — *$veti Dimitrija Skopje (1)* oleh Darko Nikolovski (EXIF: DSC-RX100 v1.00 SOOC, siang terik)](https://commons.wikimedia.org/wiki/File:$veti_Dimitrija_Skopje_(1).JPG)
    3. [Wikimedia Commons (CC BY-SA 3.0) — *$veti Dimitrija Skopje (100)* oleh Darko Nikolovski (EXIF: DSC-RX100 v1.00 SOOC, interior redup)](https://commons.wikimedia.org/wiki/File:$veti_Dimitrija_Skopje_(100).JPG)

---

## 94. Pentax (Asahi Optical) — K1000

- **id:** `pentax_k1000`
- **brand:** `Pentax (Asahi Optical)`
- **model:** `K1000` *(nama kanonik)*
- **aliases:** `Asahi Pentax K1000`, `Pentax K-1000`
- **displayName:** `K1000`
- **Tahun & Jenis:** 1976 · Mechanical 35mm film SLR
- **Spesifikasi Inti:** Film 35mm (24×36mm); mount bayonet Pentax K (pasangan paling umum: SMC Pentax-M 50mm f/2); rana kain mekanis penuh 1–1/1000 detik + Bulb; meter CdS TTL match-needle. *(Halaman spesifikasi resmi produsen aktif tidak ditemukan.)*
- **Pemisahan Sifat Kamera/Lensa vs Emulsi Film (WAJIB FILM):**
  - **Sifat Bodi, Format 35mm & Lensa (Mount K / SMC Pentax-M 50mm f/2):** Sebagai bodi SLR mekanis penuh, K1000 bersifat netral terhadap warna dan *grain* film; karakter optik hadir dari pasangan lensa Pentax K berpelapis *Super-Multi-Coating* (SMC). Pada ketiga sampel terlihat ketajaman bersih di bidang fokus (poster & vas tanaman di sampel 1, bilah kayu plafon lurus minim distorsi di sampel 2, papan kayu penunjuk arah di sampel 3), kontras klasik yang tegas dan tahan *flare* di bawah sinar matahari jendela, serta pemisahan latar lembut ber-bokeh bulat halus saat dipakai pada bukaan lebar di hutan pinus (sampel 3). Tanpa *flash* bawaan.
  - **Sifat Emulsi Film (pada sampel yang diperiksa):**
    - Sampel 1 (Meja jendela dengan poster sarden — **film: Kodak Gold 200**): rona kuning-amber hangat keemasan dan butiran halus-menengah berasal dari negatif warna Kodak Gold 200.
    - Sampel 2 (Plafon bilah kayu — *jenis film tidak dicantumkan*): transisi sorotan sinar matahari yang lembut berpendar dan rona kayu netral-hangat berasal dari negatif warna 35mm dan pemindaiannya.
    - Sampel 3 (Penanda Hutan Pinus Becici — pemindaian lab Noritsu EZ Controller, *jenis film tidak dicantumkan*): nuansa bayangan sedikit kehijauan-sejuk dan butiran 35mm yang cukup tegas berasal dari emulsi negatif warna serta setelan pemindai Noritsu.
- **lookTags:** `classic-film`, `rich-smc-contrast`, `soft-wide-open`, `crisp-stopped-down`, `neutral-body`
- **Arketipe:** `classic_35mm_slr` *(dikonfirmasi)*
- **confidenceLook:** **`medium`** — 3 foto film 35mm lintas pengunggah telah diperiksa visual dan konsisten dengan Casual Photophile, Lomography, dan Shoot It With Film, namun tetap `medium` karena bodi SLR lensa-tukar bersifat netral dan hasil akhir sangat bergantung pada lensa K-mount serta roll film yang dipasang.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Lab / Arsip Fotografi:* [Pentax K-Mount Page — K1000](http://kmp.pentaxians.eu/bodies/film_k/k1000/)
  - *Situs Fotografi / Ulasan:* [Casual Photophile — Pentax K1000 Review](https://casualphotophile.com/2016/11/07/pentax-k1000-camera-review/), [Shoot It With Film — 5 Great 35mm Film Cameras](https://shootitwithfilm.com/5-great-35mm-film-cameras-for-beginners/), [Ken Rockwell — Pentax K1000](https://www.kenrockwell.com/pentax/k1000.htm)
  - *Komunitas:* [Lomography — Pentax K1000](https://www.lomography.com/magazine/173357-pentax-k1000-my-first-analogue-love)
  - *Foto Sampel Diperiksa Visual (3, film 35mm):*
    1. [Wikimedia Commons (CC BY 2.0) — *Home, sweet home* oleh Mykyta Nikiforov (**film: Kodak Gold 200**, Pentax K1000)](https://commons.wikimedia.org/wiki/File:Home,_sweet_home_-_Flickr_-_nikiforovpizza.jpg)
    2. [Wikimedia Commons (CC BY-SA 3.0) — *Enduelado Princess* oleh Luis Aviles (Pentax K1000; *jenis film tidak dicantumkan*)](https://commons.wikimedia.org/wiki/File:Enduelado_Princess_(85624215).jpeg)
    3. [Wikimedia Commons (CC BY-SA 4.0) — *Penanda Pinus Becici* oleh OyirdniIjupRutac (Pentax K1000, Noritsu scan; *jenis film tidak dicantumkan*)](https://commons.wikimedia.org/wiki/File:Penanda_Pinus_Becici.jpg)

---

## 95. Olympus — OM-1

- **id:** `olympus_om_1`
- **brand:** `Olympus`
- **model:** `OM-1` *(nama kanonik)*
- **aliases:** `Olympus M-1` (produksi awal 1972), `OM-1MD`, `OM-1n` *(bukan OM System OM-1 digital 2022)*
- **displayName:** `OM-1`
- **Tahun & Jenis:** 1972 · Compact mechanical 35mm film SLR
- **Spesifikasi Inti (Resmi):** Film 35mm (24×36mm); mount bayonet Olympus OM (umum dipasangkan F.Zuiko Auto-S 50mm f/1.8); rana kain mekanis 1–1/1000 detik + Bulb; meter TTL CdS ganda; jendela bidik pentaprisma 0,92x.
- **Pemisahan Sifat Kamera/Lensa vs Emulsi Film (WAJIB FILM):**
  - **Sifat Bodi, Format 35mm & Lensa (Mount OM / Zuiko 50mm f/1.8):** Bodi mekanis OM-1 tidak memberi warna sendiri; seluruh kekuatan optiknya muncul dari lensa-lensa Zuiko yang ringkas namun tajam. Pada gerbang batu Verona (sampel 1), sedan klasik di bawah hujan (sampel 2), dan kubah tembaga di hutan musim gugur (sampel 3), optik OM menyajikan ketajaman arsitektural rapi dari tengah hingga tepi, kontras klasik tegas, ketahanan terhadap cahaya bocor (*veiling glare*) di lorong gelap-terang (sampel 1), dan geometri lurus bersih tanpa distorsi mengganggu. Tanpa *flash* bawaan.
  - **Sifat Emulsi Film (pada sampel yang diperiksa):**
    - Sampel 1 (Gerbang Palazzo Giusti, Verona 1993–1994 — **film slide: Kodak Ektachrome Elite 200**): kontras dalam di kolom gelap, sorotan langit bersih, dan rona batu netral-sejuk yang padat berasal dari film positif slide Ektachrome 200.
    - Sampel 2 (Alfa Romeo Arna di hari hujan, pindaian Plustek OpticFilm 7200i — *jenis film tidak dicantumkan*): nuansa merah muda-magenta tipis pada bodi putih dan butiran 35mm klasik berasal dari negatif warna lawas dan pemindaiannya.
    - Sampel 3 (Kubah tembaga di Tama Zoo — *jenis film tidak dicantumkan*): hijau pepohonan organik agak kalem dan butiran 35mm lembut berasal dari negatif warna 35mm di bawah langit mendung.
- **lookTags:** `classic-film`, `sharp-zuiko`, `crisp-contrast`, `smooth-bokeh`, `organic-tones`
- **Arketipe:** `classic_35mm_slr` *(dikonfirmasi)*
- **confidenceLook:** **`medium`** — 3 foto film 35mm autentik Olympus OM-1 (termasuk slide Ektachrome 1994) konsisten dengan Casual Photophile, 35mmc, dan Shoot It With Film, namun tetap `medium` karena bodi SLR film bersifat netral dan hasil akhir bergantung pasangan lensa OM serta emulsi film.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Olympus Technology Museum — OM-1](https://www.olympus-global.com/technology/museum/lecture/vol2_03/?page=technology_museum)
  - *Lab / Arsip Fotografi:* [MIR Archive — Olympus OM-1](https://www.mir.com.my/rb/photography/hardwares/classics/olympusom1n2/om1/index.htm)
  - *Situs Fotografi / Ulasan:* [Casual Photophile — Olympus OM-1 Review](https://casualphotophile.com/2016/04/25/olympus-om-1-camera-review/), [35mmc — 5 Frames Olympus OM-1 + Zuiko 50mm f/1.8](https://www.35mmc.com/21/09/2017/5-frames-09-olympus-om-1-zuiko-50mm1-8-toni-skokovic/), [Shoot It With Film — Olympus OM-1 Review](https://shootitwithfilm.com/olympus-om-1-camera-review/)
  - *Foto Sampel Diperiksa Visual (3, film 35mm Olympus OM-1):*
    1. [Wikimedia Commons (CC BY-SA 4.0) — *Giardino Giusti Verona 1993-1994* oleh Paolo Villa (**film: Kodak Ektachrome Elite 200**, EXIF: OLYMPUS OM-1)](https://commons.wikimedia.org/wiki/File:1993-1994-Giardino_Giusti_(Verona)-testo_e_photo_Paolo_Villa-nA01-tesi_Accademia_Belle_Arti_Bologna-portone_di_Palazzo_Giusti_Kodak_EKTACHROME_ELITE_200_5056.jpg)
    2. [Wikimedia Commons (Public domain) — *Alfa Arna 4d* oleh Charles01 (Kategori: Taken with Olympus OM-1; *jenis film tidak dicantumkan*)](https://commons.wikimedia.org/wiki/File:Alfa_Arna_4d.JPG)
    3. [Wikimedia Commons (CC BY 2.0) — *2009年秋ごろ* oleh yamauchi (Kategori: Taken with Olympus OM-1; *jenis film tidak dicantumkan*)](https://commons.wikimedia.org/wiki/File:2009%E5%B9%B4%E7%A7%8B%E3%81%94%E3%82%8D_(13669606424)_(2).jpg)

---

## 96. Rollei (Franke & Heidecke) — Rolleiflex 2.8F

- **id:** `rollei_rolleiflex_2_8f`
- **brand:** `Rollei (Franke & Heidecke)`
- **model:** `Rolleiflex 2.8F` *(nama kanonik)*
- **aliases:** `Rolleiflex 2.8 F`, `Rollei 2.8F`
- **displayName:** `Rolleiflex 2.8F`
- **Tahun & Jenis:** 1960 · Medium format TLR 120
- **Spesifikasi Inti:** Film roll 120 **format persegi 6×6cm (rasio 1:1)**; lensa tetap **Carl Zeiss Planar 80mm f/2.8** atau **Schneider-Kreuznach Xenotar 80mm f/2.8**; rana daun Synchro-Compur 1–1/500 detik + Bulb; meter selenium terkopel. *(Halaman spesifikasi resmi produsen aktif tidak ditemukan.)*
- **Pemisahan Sifat Kamera/Lensa vs Emulsi Film (WAJIB FILM):**
  - **Sifat Bodi, Format 6×6 & Lensa Tetap (Planar / Xenotar 80mm f/2.8):** Karena lensa 80mm f/2.8 terpasang permanen pada format persegi 6×6cm, karakter optiknya sangat konsisten lintas roll film. Pada kucing dekat jendela (sampel 1), ketajaman di bidang fokus (kumis dan motif karpet) menggigit bersih sementara tirai, jendela, dan kursi meluruh ke belakang secara amat krem tanpa pola ganda kasar, menciptakan dimensi ruang (*3D pop*) yang halus dan membuai. Pada bianglala (sampel 2) dan fajar berkabut Yosemite (sampel 3), kisi baja dan siluet pepohonan tampil jernih nyaris tanpa distorsi dengan gradasi tonal panjang yang sangat mulus dan butiran film yang terasa sangat halus berkat luas negatif 6×6. Vignette tipis alami; tanpa *flash* bawaan.
  - **Sifat Emulsi Film (pada sampel yang diperiksa):**
    - Sampel 1 (Kucing dekat jendela — **film B&W 120: Arista Ultra 400** dalam Rodinal 1+50): kontras monokrom pekat, bayangan dalam, dan butiran perak halus-klasik berasal dari emulsi Arista Ultra 400 dan pengembang Rodinal.
    - Sampel 2 (Bianglala Osaka — *jenis film warna 120 tidak dicantumkan*): palet langit biru-pastel lembut dan gondola merah-toska kalem berasal dari film negatif warna 120 yang diekspos sedikit terang.
    - Sampel 3 (Fajar Sungai Merced, Yosemite — **lensa: Zeiss Planar 80mm f/2.8; film: Kodak Portra 400**): rona langit persik-merah jambu lembut berpadu bayangan lembah biru-nila dalam berasal dari negatif warna Kodak Portra 400 saat senja/fajar berkabut asap.
- **lookTags:** `square-format`, `creamy-bokeh`, `3d-pop`, `smooth-tonality`, `biting-center`
- **Arketipe:** `medium_format_tlr_slr` *(dikonfirmasi)*
- **confidenceLook:** **`high`** — Lensa tetap Planar/Xenotar 80mm f/2.8 + 3 foto sampel 6×6 terverifikasi lintas 3 emulsi (B&W Arista 400, negatif warna pastel, Kodak Portra 400) selaras sepenuhnya dengan Shoot It With Film dan dua ulasan terpisah di 35mmc.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Situs Fotografi / Ulasan:* [Shoot It With Film — Rolleiflex Guide](https://shootitwithfilm.com/rolleiflex-cameras-get-to-know-these-iconic-film-cameras/), [35mmc — 5 Frames Rolleiflex 2.8F (Takeuchi)](https://www.35mmc.com/28/08/2021/5-frames-with-a-rolleiflex-2-8f-a-lifetime-partner-by-floyd-takeuchi/), [35mmc — Rolleiflex 2.8F Reflections (Foale)](https://www.35mmc.com/09/02/2023/rolleiflex-2-8f-reflections-on-a-brief-period-of-ownership-by-simon-foale/)
  - *Foto Sampel Diperiksa Visual (3, format persegi 6×6):*
    1. [Wikimedia Commons (CC BY 2.0) — *Pigeon* oleh Andrew Lambeth (**film: Arista Ultra 400**, Rolleiflex 2.8f)](https://commons.wikimedia.org/wiki/File:Pigeon_-_Flickr_-_walnerz.jpg)
    2. [Flickr CC via Openverse (CC BY-SA 2.0) — *Rolleiflex 2.8F @Osaka* oleh Amano42 (*jenis film tidak dicantumkan*)](https://www.flickr.com/photos/151965803@N07/35485885385)
    3. [Wikimedia Commons (CC BY 2.0) — *Smokey Sunrise* oleh Andrew Lambeth (**lensa: Zeiss Planar 80mm f/2.8; film: Kodak Portra 400**, Rolleiflex 2.8F)](https://commons.wikimedia.org/wiki/File:Smokey_Sunrise_-_Flickr_-_walnerz.jpg)

---

## 97. Mamiya — Mamiya 7

- **id:** `mamiya_7`
- **brand:** `Mamiya`
- **model:** `Mamiya 7` *(nama kanonik)*
- **aliases:** `Mamiya 7 (6×7)` *(pendahulu langsung Mamiya 7II dengan sistem lensa optik sama)*
- **displayName:** `Mamiya 7`
- **Tahun & Jenis:** 1995 · Medium format rangefinder 120
- **Spesifikasi Inti:** Film roll 120/220 **format 6×7cm (56×69,5mm)**; lensa dapat diganti berana daun elektronik di tiap lensa (standar Mamiya N 80mm f/4 L, juga populer 43mm f/4.5 & 65mm f/4); Aperture-Priority AE & Manual. *(Halaman spesifikasi resmi produsen aktif tidak ditemukan; diverifikasi via arsip PDF manual instruksi.)*
- **Pemisahan Sifat Kamera/Lensa vs Emulsi Film (WAJIB FILM):**
  - **Sifat Bodi, Format 6×7 & Lensa (Rangefinder Mamiya 80mm f/4 & 43mm f/4.5):** Tanpa hentakan cermin SLR dan dengan konstruksi optik modern berformat negatif besar 6×7cm, ketiga sampel memperlihatkan ketajaman luar biasa jernih dari tengah hingga tepi disertai kontras mikro bersih dan distorsi geometris amat rendah. Pada sampel tembok batu jalan pegunungan dengan **80mm @ f/4** (sampel 3), tekstur batu di bidang fokus terukir tajam sekali sementara jalan yang melengkung ke kejauhan melebur lembut secara netral dan alami; pada bentang kabut dan awan lebar (sampel 1 & 2), butiran film tampak amat halus dengan gradasi awan dan kabut yang sangat panjang serta modern. Tanpa *flash* bawaan.
  - **Sifat Emulsi Film (pada sampel yang diperiksa):**
    - Sampel 1 (Lembah Val de Oza berkabut — **lensa: 43mm; film: Kodak Portra 400** dikonversi monokrom): rentang awan dramatis dan perincian halus berasal dari negatif 6×7 Portra 400 yang diolah ke hitam-putih.
    - Sampel 2 (Dartmoor berkabut asap — **film: Kodak Portra 160NC**): palet abu-abu lembut, semak cokelat-hijau kalem, dan transisi asap yang amat halus berasal dari emulsi negatif warna berbutir sangat halus Portra 160NC.
    - Sampel 3 (Tembok batu pegunungan — **lensa: 80mm @ f/4.0; film: Kodak T-Max 400**): kontras hitam-putih pekat, ketajaman tinggi, dan *grain* amat rapi berasal dari emulsi T-grain Kodak T-Max 400.
- **lookTags:** `ultra-sharp`, `clean-modern-film`, `micro-contrast`, `fine-grain`, `natural-perspective`
- **Arketipe:** `medium_format_tlr_slr` *(dikonfirmasi)*
- **confidenceLook:** **`high`** — 3 foto sampel 6×7 terverifikasi dari 3 pengunggah dan 3 emulsi berbeda (Portra 400, Portra 160NC, T-Max 400) termasuk lensa standar 80mm f/4 sangat konsisten dengan Caleb Knueven, PhotoThinking, dan Ken Rockwell.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Lab / Arsip Fotografi:* [Manual Instruksi Mamiya 7 PDF via Focale22](https://www.focale22.com/pdf/mamiya_7_instructions_manual.pdf)
  - *Situs Fotografi / Ulasan:* [Caleb Knueven — 5 Years with the Mamiya 7](https://www.calebknueven.com/post/5-years-with-the-mamiya-7-why-this-medium-format-rangefinder-is-my-ride-or-die), [PhotoThinking — Mamiya 7 First Impressions](https://photothinking.com/2019-01-11-mamiya-7-first-impressions/), [Japan Camera Hunter — Mamiya 7](https://www.japancamerahunter.com/2021/09/camera-geekery-mamiya-7/), [Ken Rockwell — Mamiya 7](https://www.kenrockwell.com/mamiya/7.htm)
  - *Foto Sampel Diperiksa Visual (3, format 6×7 Flickr CC):*
    1. [Flickr CC via Openverse (CC BY 2.0) — *Val de Oza Mamiya 7* oleh Noel Feans (**lensa: 43mm; film: Kodak Portra 400** konversi B&W)](https://www.flickr.com/photos/37563893@N00/16243896675)
    2. [Flickr CC via Openverse (CC BY 2.0) — *dartmoor on fire* oleh michaelmelrose. (**film: Kodak Portra 160NC**, Mamiya 7)](https://www.flickr.com/photos/27360628@N08/5529302490)
    3. [Flickr CC via Openverse (CC BY 2.0) — *Mamiya 7 80mm @ F/4.0* oleh Gential LAMBERT (**lensa: 80mm f/4; film: Kodak T-Max 400**)](https://www.flickr.com/photos/25204075@N08/18814281841)

---

## 98. Pentax (Asahi Optical) — Pentax 67

- **id:** `pentax_67`
- **brand:** `Pentax (Asahi Optical)`
- **model:** `Pentax 67` *(nama kanonik)*
- **aliases:** `Pentax 6×7`, `Asahi Pentax 6x7`, `P67` *(bukan penerusnya Pentax 67II)*
- **displayName:** `Pentax 67`
- **Tahun & Jenis:** 1989 *(seri 6×7 sejak 1969)* · Medium format SLR 120
- **Spesifikasi Inti:** Film roll 120/220 **format 6×7cm (56×69mm)**; mount bayonet ganda Pentax 67 (ikonik dengan SMC Takumar / Pentax 67 105mm f/2.4 serta 55mm f/4); rana kain horizontal elektronik 1–1/1000 detik + Bulb; prisma dapat diganti & Mirror Lock-Up. *(Halaman spesifikasi resmi produsen untuk versi 67 asli tidak ditemukan; arsip Ricoh hanya memuat 67II.)*
- **Pemisahan Sifat Kamera/Lensa vs Emulsi Film (WAJIB FILM):**
  - **Sifat Bodi, Format 6×7 & Lensa (Mount Pentax 67 / 105mm f/2.4 & 55mm f/4):** Luas negatif 6×7cm berpadu optik Pentax 67 berbukaan lebar maupun sudut lebar menghasilkan perincian amat kaya dengan butiran film hampir tak terlihat. Pada salib batu patah di rerumputan (sampel 3), tampak langsung ciri khas **ruang tajam super dangkal dan efek pemisahan tiga-dimensi (*3D pop*)** Pentax 67 — ukiran lumut batu dan helai rumput di bidang fokus terpotong tajam menonjol, lalu latar batu nisan langsung meleleh krem lembut. Saat ditutup pada arsitektur gereja (sampel 1, lensa 55mm f/4) dan panorama atap kota Reykjavik (sampel 2), ketajaman merata hingga sudut dengan gradasi bayangan-ke-sorotan yang panjang dan kaya; pada bukaan penuh 105mm f/2.4 (menurut EMULSIVE dan Casual Photophile) muncul pula sedikit pendaran *vintage* lembut. Tanpa *flash* bawaan.
  - **Sifat Emulsi Film (pada sampel yang diperiksa):**
    - Sampel 1 (Gereja Willows, California — **lensa: SMC Pentax 67 55mm f/4; film: Kodak Portra 160**, pindaian The Darkroom): warna langit biru jernih, plester krem hangat, dan genteng terakota alami dengan butiran amat halus berasal dari Kodak Portra 160.
    - Sampel 2 (Panorama Skólavörðustígur, Reykjavik, pindaian Nikon Super Coolscan 9000 ED — *jenis film tidak dicantumkan*, berkarakter film slide 120): biru laut Nordik yang dalam, bayangan pekat, dan atap merah-kuning jenuh berasal dari emulsi positif/slide 120.
    - Sampel 3 (Salib batu Rock of Cashel — *jenis film warna 120 tidak dicantumkan*): hijau rumput cerah dan kontras siang hari segar berasal dari emulsi warna 120.
- **lookTags:** `3d-pop`, `shallow-dof`, `creamy-bokeh`, `vintage-glow`, `rich-tonality`
- **Arketipe:** `medium_format_tlr_slr` *(dikonfirmasi)*
- **confidenceLook:** **`high`** — 3 foto sampel 6×7 terverifikasi `Pentax 67` lintas 3 pengunggah dan 3 gaya pemotretan (arsitektur Portra 160, lanskap kota slide, ruang tajam dangkal *3D pop*) sangat konsisten dengan EMULSIVE, Casual Photophile, Kamerastore, dan Peter Jeffrey Photography.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Situs Fotografi / Ulasan:* [EMULSIVE — Pentax 6x7 Review](https://emulsive.org/reviews/camera-reviews/pentax-camera-reviews/camera-review-me-and-my-pentax-6x7-by-daniel-j-schneider), [Casual Photophile — Pentax 67 Review](https://casualphotophile.com/2020/10/05/pentax-67-review-its-a-big-camera/), [Kamerastore — Ultimate Pentax 67 Guide](https://kamerastore.com/en-int/blogs/posts/the-ultimate-pentax-67-guide-history-iconic-lenses-and-real-world-review), [Peter Jeffrey Photography — Pentax 67 Review](https://www.peterjeffrey.photography/film-photography-blog/pentax-67-review)
  - *Foto Sampel Diperiksa Visual (3, format 6×7 Wikimedia Commons):*
    1. [Wikimedia Commons (CC BY-SA 4.0) — *First United Methodist Church, Willows* oleh Frank Schulenburg (**lensa: SMC Pentax 67 55mm f/4; film: Kodak Portra 160**)](https://commons.wikimedia.org/wiki/File:First_United_Methodist_Church,_Willows_(2025)-P000327370004.jpg)
    2. [Wikimedia Commons (CC BY-SA 2.0 de) — *74Skolavördustigur* oleh Christian Bickel (**Pentax 67**, Coolscan 9000 ED; *jenis film tidak dicantumkan*)](https://commons.wikimedia.org/wiki/File:74Skolav%C3%B6rdustigur.jpg)
    3. [Wikimedia Commons (CC BY-SA 4.0) — *Broken cross, the Rock of Cashel* oleh Rob Hurson (EXIF: Pentax 67; *jenis film tidak dicantumkan*)](https://commons.wikimedia.org/wiki/File:Broken_cross,_the_Rock_of_Cashel.jpg)

---

## 99. Sony — Handycam DCR-VX1000

- **id:** `sony_handycam_dcr_vx1000`
- **brand:** `Sony`
- **model:** `Handycam DCR-VX1000` *(nama kanonik)*
- **aliases:** `DCR-VX1000`, `DCR-VX1000E` (PAL), `VX1000`, `VX1k`
- **displayName:** `Handycam DCR-VX1000`
- **Tahun & Jenis:** 1995 · Camcorder MiniDV 3-CCD
- **Spesifikasi Inti (Resmi):** Sistem **3-CCD 1/3 inci** (410K piksel bruto per chip RGB terpisah via prisma dikroik); pita MiniDV definisi standar **4:3** (*interlaced* 60i NTSC / 50i PAL, codec DV-25, i.LINK/FireWire); lensa zoom optik 10x dengan cincin fokus manual; rana 1/4–1/10.000 detik.
- **Ketergantungan Mode Warna (Digital/Video):** Tampilan ikoniknya berasal dari **mode video MiniDV 3-CCD *interlaced* 4:3 standar bawaan** (sering disertai sedikit setelan *Custom Preset* penajaman/warna atau adaptor lensa *fisheye* Century Optics pada budaya video *skate*, namun karakter warna hangat-pekat dan pendaran sorotannya melekat pada arsitektur 3-CCD + codec DV-25 bawaan).
- **Deskripsi Tampilan Hasil Foto/Frame (2 Frame Visual Bersih + Ulasan Teknis):**
  - **Warna 3-CCD & Kontras Siang:** Pada tangkapan *frame* siang hari (sampel arsip *Curved Universe*), pemisahan warna 3-CCD tampak jauh lebih pekat, hangat, dan bersih dari rembesan warna dibanding camcorder 1-chip era 90-an — mural pirus-hijau, langit biru cerah, rumput, dan beton diterpa matahari terpisah tegas dengan kontras kuat khas video jalanan akhir 1990-an.
  - **Sorotan (*Highlight*), Cahaya Senja & Resolusi SD 4:3:** Pada tangkapan *frame* senja (sampel arsip *Own Little World*), rentang dinamis video definisi standar yang sempit terlihat jelas: bidang langit terang langsung terbakar putih rata disertai **pendaran (*bloom*) lembut** di tepi bangunan dan lampu jalan keemasan, sementara detail tangga beton menampilkan kelembutan definisi standar 4:3 dengan sedikit penajaman tepi elektronik dan kabur gerak khas video DV.
  - **Karakter Gerak Video:** Selaras dengan ulasan Jenkem Magazine, EOSHD, dan arsip teknis DTV Group, perekaman *interlaced* 60i/50i memberi gerak video sangat luwes (berlawanan dengan irama sinematik 24p), ditambah tekstur kompresi pita MiniDV yang menjadi tolok ukur estetika video *skate* klasik.
- **lookTags:** `rich-3ccd-color`, `warm-contrast`, `interlaced-4x3`, `highlight-bloom`, `minidv-skate`
- **Arketipe:** `camcorder_mini_dv_vhs` *(dikonfirmasi)*
- **confidenceLook:** **`medium`** *(diturunkan jujur dari `high` di `batch_05.md`)* — Tidak ditemukan *still frame* berlisensi CC/PD langsung dari kamera di Wikimedia Commons maupun Openverse; penilaian visual bertumpu pada **2 *frame* video 4:3 bersih bebas-wajah** yang diperiksa sementara dari Internet Archive ditambah konsistensi 3 sumber teks/ulasan independen (Jenkem Magazine, EOSHD, DTV Group Archive), sementara karakter gerak *interlaced* diverifikasi dari ulasan teknis.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Sony Design Gallery — DCR-VX1000](https://www.sony.com/en/SonyInfo/design/gallery/DCR-VX1000/)
  - *Lab / Arsip Teknis:* [DTV Group Archive — Sony DCR-VX1000](https://www.dtvgroup.com/DigVideo/DVC/Sony/dcr_vx1000.html), [UrbanFox Manual Archive — Sony VX1000](http://www.urbanfox.tv/workbooks/sonyvx1000/vx1000manual.htm)
  - *Situs Ulasan / Komunitas:* [Jenkem Magazine — History of the VX1000](https://www.jenkemmag.com/home/2018/07/13/tracing-history-skateboardings-most-famous-camera/), [EOSHD — Sony VX1000 CCD Discussion](https://www.eoshd.com/comments/topic/28892-sony-vx1000-ccd-love-in-2019/)
  - *Frame Sampel Diperiksa Visual Sementara (2 frame bebas-wajah, tidak disimpan lokal):*
    1. [Internet Archive — *Curved Universe* (rekaman 4:3 VX1000 siang hari — 1 *frame* diperiksa sementara lalu dihapus)](https://archive.org/details/curved-universe-pt-remaster)
    2. [Internet Archive — *Own Little World* (rekaman 4:3 VX1000 senja — 1 *frame* diperiksa sementara lalu dihapus)](https://archive.org/details/own-little-world-remaster)

---

## 100. Panasonic — AG-DVX100

- **id:** `panasonic_ag_dvx100`
- **brand:** `Panasonic`
- **model:** `AG-DVX100` *(nama kanonik)*
- **aliases:** `DVX100`, `AG-DVX100E` (PAL), `AG-DVX100A`, `AG-DVX100B` *(revisi minor seri sama)*
- **displayName:** `AG-DVX100`
- **Tahun & Jenis:** 2002 · Prosumer MiniDV 3-CCD 24p camcorder
- **Spesifikasi Inti (Resmi):** Sistem **3-CCD 1/3 inci** interline transfer; rekam pita MiniDV definisi standar dengan **mode progresif sejati 24p/24pA**, 30p, dan 60i serta kurva **Cine-Like Gamma**; lensa tetap Leica Dicomar 10x zoom (ekuiv. ~32,5–325mm) f/1.6 dengan OIS; 2 input XLR + FireWire.
- **Ketergantungan Mode Warna (Digital/Video):** Perbedaan tampilan **sangat bergantung pada mode kurva gamma & *frame rate***: dalam mode **Normal 60i**, gambar tampak tegas dan kontras seperti video siaran standar; karakter sinematik film independen awal 2000-an baru muncul penuh ketika mengaktifkan **mode 24p + kurva *Cine-Like Gamma*** (pada varian lanjut DVX100A/B dibagi menjadi *Cine-Like_D* untuk rentang dinamis lembut dan *Cine-Like_V* untuk kontras lebih tegas) bersama matriks warna *Cine-Like*.
- **Deskripsi Tampilan Hasil Foto/Frame (7 Frame Visual Lintas 2 Arsip + Ulasan Teknis):**
  - **Perbandingan Langsung Normal vs Cine-Like Gamma:** Inspeksi visual langsung atas 5 *frame* uji sejajar pada arsip Adam Wilt (*DVX100-gCine*, *DVX100A-gNorm*, *DVX100A-gCine*, *DVX100A-gCineD*, *DVX100A-gCineV*) memperlihatkan perubahan tonal yang sangat nyata pada tirai jendela bermandikan cahaya di balik lampu meja biru: pada mode **Normal**, tirai terang lebih cepat menyentuh putih keras dan bayangan meja kayu tampak pekat berkontras video; pada **Cine-Like / Cine-Like_D**, transisi sorotan tirai **melunak jauh lebih halus** menjaga gradasi putih tanpa patah kasar sekaligus mengangkat detail bayangan tengah pada bodi komputer dan dinding, sedangkan **Cine-Like_V** sedikit mempertegas kontras sambil tetap menahan sorotan lebih luwes daripada video standar.
  - **Warna 3-CCD, Cahaya Redup & Tekstur MiniDV Indie:** Pada 2 *frame* bebas-wajah dari rekaman *Japan 05* di Internet Archive (lilin kuil hangat dan lampu malam biru-hijau), matriks warna sinematik 3-CCD meredam warna video yang mencolok menjadi nuansa hangat-kalem yang akrab, dengan pendaran lembut di sekeliling nyala lilin dan tekstur definisi standar 480p serta kompresi pita MiniDV yang lembut organik.
  - **Irama Gerak 24p:** Sesuai ulasan Videomaker, CNET, dan Adam Wilt, mode progresif 24p sejati menggantikan kelicinan video *interlaced* 60i dengan irama gerak dan *motion blur* khas film seluloid.
- **lookTags:** `24p-filmic-motion`, `cine-gamma`, `soft-highlights`, `warm-muted-3ccd`, `minidv-indie`
- **Arketipe:** `camcorder_mini_dv_vhs` *(dikonfirmasi)*
- **confidenceLook:** **`high`** — Didukung inspeksi visual langsung **7 *frame* asli lintas 2 sumber independen** (5 *frame* perbandingan gamma langsung Normal vs Cine-Like/Cine-D/Cine-V di Adam Wilt 24p Guide + 2 *frame* bebas-wajah dari rekaman *Japan 05* di Internet Archive) yang sepenuhnya selaras dengan ulasan teknis Videomaker, CNET, dan Adam Wilt *(catatan: irama gerak 24p diverifikasi dari ulasan teks teknis, bukan pemutaran video)*.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Panasonic Pro-AV News — Seri AG-DVX100](https://pro-av.panasonic.net/en/sales_o/news_info/ibc/132.html)
  - *Lab / Arsip Teknis (dengan inspeksi visual sampel):* [Adam Wilt — 24p: The AG-DVX100 and Digital Filmmaking](https://www.adamwilt.com/24p/)
  - *Situs Videografi / Ulasan:* [Videomaker — Panasonic AG-DVX100 Review](https://www.videomaker.com/article/c5/9410-panasonic-ag-dvx100-3ccd-24p-mini-dv-camcorder-review/), [CNET — Panasonic AG-DVX100 Review](https://www.cnet.com/reviews/panasonic-ag-dvx100-review/)
  - *Frame Sampel Diperiksa Visual Sementara (7 lintas 2 sumber, tidak disimpan lokal):*
    1. [Adam Wilt 24p Guide — 5 *frame* perbandingan gamma (*DVX100-gCine.jpg*, *DVX100A-gNorm.jpg*, *DVX100A-gCine.jpg*, *DVX100A-gCineD.jpg*, *DVX100A-gCineV.jpg* — diperiksa visual sementara lalu dihapus)](https://www.adamwilt.com/24p/)
    2. [Internet Archive — *Japan 05* oleh Kenny Ong (rekaman AG-DVX100 — 2 *frame* bebas-wajah lilin kuil & lampu malam diperiksa sementara lalu dihapus)](https://archive.org/details/KennyOngJapan05Japan05mov)

---

## Ringkasan Laporan Batch 05

### 1. Jumlah Kamera per `confidenceLook`

| Tingkat `confidenceLook` | Jumlah | Daftar Kamera |
|---|---:|---|
| **`high`** | **17** | `sony_cyber_shot_dsc_w55`, `sony_cyber_shot_dsc_t7`, `sony_mavica_mvc_fd7`, `canon_powershot_g10`, `canon_ixy_digital_110_is`, `fujifilm_finepix_f10`, `nikon_coolpix_s3300`, `casio_exilim_ex_z1080`, `olympus_mju_700`, `ricoh_gr_digital_iii`, `sigma_dp1_merrill`, `fujifilm_x_pro1`, `sony_cyber_shot_dsc_rx100`, `rollei_rolleiflex_2_8f`, `mamiya_7`, `pentax_67`, `panasonic_ag_dvx100` |
| **`medium`** | **3** | `pentax_k1000`, `olympus_om_1`, `sony_handycam_dcr_vx1000` |
| **`low`** | **0** | — |
| **`tidak ditemukan`** | **0** | *(Tidak ada kamera tanpa sumber sama sekali)* |
| **Total** | **20** | **66 foto/*frame* contoh diperiksa visual langsung** (52 berlisensi CC/PD di `docs/references/manifest_batch_05.json` + 14 foto/*frame* arsip & ulasan independen diperiksa sementara lalu dihapus) |

*(Perbandingan terhadap `docs/catalog/batch_05.md`: `nikon_coolpix_s3300` naik dari `medium` ke `high` berkat inspeksi visual langsung 3 foto SOOC EXIF `COOLPIX S3300V1.0`; `sony_handycam_dcr_vx1000` diturunkan jujur dari `high` ke `medium` karena ketiadaan sampel CC/PD dan keterbatasan 2 *frame* arsip bebas-wajah; serta arketipe `fujifilm_finepix_f10` diperbarui dari `ccd_dreamy_pastel` ke `pro_ccd_filmic` agar konsisten dengan arsitektur Super CCD HR 1/1,7" milik FinePix F31fd di `archetypes.md`.)*

### 2. Kamera dengan Sumber Lemah / Perlu Perhatian Saat Kalibrasi

1. **`pentax_k1000` dan `olympus_om_1` (`medium`):** Masing-masing telah diperiksa visual 3 foto film 35mm terverifikasi dan dipisahkan sifat optik lensa vs emulsi filmnya, namun tetap `medium` karena bodi SLR mekanis bersifat netral secara warna sehingga tampilan akhir sangat dipengaruhi lensa K-mount/OM dan jenis roll film (sebagian sampel arsip tidak mencantumkan nama emulsi spesifik).
2. **`sony_handycam_dcr_vx1000` (`medium`):** Tidak tersedia *still frame* berlisensi terbuka di Wikimedia Commons maupun Openverse; kalibrasi bertumpu pada 2 *frame* video 4:3 bebas-wajah dari Internet Archive serta ulasan teknis (gerak *interlaced* diverifikasi dari literatur teknis, bukan pemutaran klip).
3. **`panasonic_ag_dvx100` (`high` untuk kurva gamma & warna 3-CCD, catatan khusus untuk gerak 24p):** Tidak tersedia *still frame* CC/PD di Wikimedia/Openverse, tetapi telah diverifikasi langsung melalui 5 *frame* perbandingan gamma sejajar (Normal vs Cine-Like/Cine-D/Cine-V) di Adam Wilt 24p Guide plus 2 *frame* Internet Archive; irama gerak 24p tetap bersandar pada literatur teknis.
4. **`sony_mavica_mvc_fd7` (`high`, catatan jumlah sampel CC lokal):** Hanya 1 karya kolase 100 bidikan 1997 yang berlisensi CC di Wikimedia Commons (`manifest_batch_05.json`), sehingga karakter detail 640×480 asli, blok kompresi JPEG, dan gerigi *interlace* dilengkapi melalui inspeksi sementara 5 berkas 640×480 asli di Wrotniak Archive dan Stanford Mavica Notes.

### 3. Hal yang Tidak Ditemukan

- **Sampel foto/*frame* berlisensi CC/PD di Wikimedia Commons & Openverse Flickr CC:** Tidak ditemukan *still frame* hasil rekaman (non-foto-produk-kamera) berlisensi CC/PD untuk `Sony Handycam DCR-VX1000` dan `Panasonic AG-DVX100`, serta tidak ditemukan berkas tunggal 640×480 non-kolase yang pasti berasal dari `Sony Mavica MVC-FD7` di Wikimedia (kategori umum Sony Mavica mayoritas berisi foto beresolusi lebih tinggi dari seri CD Mavica / Mavica lanjut).
- **Halaman spesifikasi resmi produsen aktif:** Tidak ditemukan untuk `Fujifilm FinePix F10`, `Pentax K1000`, `Rolleiflex 2.8F`, `Mamiya 7` (hanya tersedia arsip PDF manual instruksi via pihak ketiga), dan `Pentax 67` versi asli 1989 (arsip resmi Ricoh hanya menampilkan penerusnya `Pentax 67II`).
