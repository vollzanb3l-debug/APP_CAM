# Analisis Mendalam Tampilan Foto — Batch 02 (`docs/catalog/looks/looks_batch_02.md`)

Pendalaman karakter visual kualitatif untuk **20 kamera Batch 02** (No. `21`–`40`) dari [batch_02.md](../batch_02.md), mengacu pada daftar 15 arketipe di [archetypes.md](../archetypes.md).

> **Metodologi & Kepatuhan Aturan Keras:**
> - **Tanpa angka parameter shader:** Seluruh deskripsi tampilan murni kualitatif (warna, *white balance*, kontras, perilaku sorotan/bayangan, *noise*/*grain*, kelembutan optik, *vignette*, aberasi, dan karakter *flash*). Angka yang tercantum semata spesifikasi perangkat keras terverifikasi atau metadata EXIF foto sampel (ukuran sensor, panjang fokus, bukaan, ISO, kecepatan rana).
> - **Inspeksi visual langsung tanpa memasukkan foto ke repo/aplikasi:** Inspeksi visual dilakukan langsung terhadap **60 foto contoh berlisensi terbuka jelas (CC BY, CC BY-SA, Public Domain)** — tepat **3 foto terverifikasi EXIF per kamera** — dari **Wikimedia Commons** (lintas pengunggah langsung, Flickr CC, Geograph, dan Panoramio). Daftar salinan rujukan lokal dan lisensinya dicatat lengkap di `docs/references/manifest_batch_02.json` (folder `docs/references/` diabaikan `.gitignore`). Tidak ada foto apa pun yang masuk ke repositori Git maupun `app/`.
> - **Privasi & Keakuratan Model:** Seluruh foto sampel diperiksa visual satu per satu beserta metadata EXIF `Model`. Setiap foto yang menampilkan wajah manusia yang dapat dikenali, anak-anak, *watermark* berat, suntingan latar, bidikan produk kamera, atau model kamera berbeda (mis. ponsel yang berbagi nomor model) langsung dihapus dan diganti sebelum analisis ditulis.
> - **Mode warna kamera digital:** Seluruh 20 kamera pada Batch 02 merupakan kamera digital (19 sensor CCD dan 1 sensor BSI CMOS). Untuk setiap entri dicatat secara tegas apakah tampilan khas berasal dari JPEG bawaan langsung dari kamera (*straight out of camera* / SOOC) atau bergantung pada mode warna/kecantikan/adegan tertentu.
> - **Penamaan kanonik:** Nama merek dan tipe mengikuti `docs/catalog/batch_02.md` secara persis; alias regional dan varian dicatat pada bidang `aliases` dengan satu nama kanonik.

---

## 21. Canon — PowerShot G9

- **id:** `canon_powershot_g9`
- **brand:** `Canon`
- **model:** `PowerShot G9` *(nama kanonik)*
- **aliases:** `Canon G9`
- **displayName:** `Canon PowerShot G9`
- **Tahun & Jenis:** 2007 · Digital kompak antusias (CCD)
- **Spesifikasi Inti (Resmi):** Sensor CCD 1/1,7" 12,1 MP efektif; lensa zoom optik 6x ekuiv. 35–210mm f/2.8–4.8 dengan *Optical IS*; ISO 80–1600; rana 15–1/2500 dtk; RAW (.CR2) & JPEG.
- **Ketergantungan Mode Warna (Digital):** Karakter warna alami yang hidup dan kontras tegas muncul langsung pada **setelan bawaan (default SOOC JPEG / *My Colors Off*)**. Kamera menyediakan *My Colors* (*Vivid*, *Neutral*, *Sepia*, *B&W*, *Positive Film*, *Lighter/Darker Skin Tone*, *Custom Color*) untuk dorongan saturasi tambahan, tetapi seluruh sampel yang diperiksa berasal dari JPEG standar tanpa efek warna khusus.
- **Deskripsi Tampilan Hasil Foto (Inspeksi Visual + Sumber):**
  - **Warna & White Balance:** Pada sampel luar ruang dedaunan musim gugur dan aliran air, merah-jingga daun serta hijau basah tampil kaya, alami, dan pekat khas sains warna Canon akhir 2000-an tanpa terasa berlebihan. Di bawah lampu pameran dalam ruang (sampel mobil remuk kuning), *white balance* otomatis mempertahankan nuansa agak hangat yang masih terkendali dengan warna kuning cat yang kuat dan bersih.
  - **Kontras & Sorotan (*Highlight*):** Kontras bawaan terasa tegas dan memberi kesan "pop" klasik CCD besar. Bidang sangat terang seperti langit mendung pucat atau bukaan pintu bercahaya di dalam ruang cepat penuh ke putih rata (*clipped*), namun transisi di nada tengah batu dan logam tetap padat dan berdimensi.
  - **Ketajaman, Aberasi & Kelembutan:** Pada ISO dasar (100) hingga menengah (400), mikro-detail tekstur batu bata, ukiran balkon, serta karat logam terlihat sangat tajam dan renyah. Pada tepi sulur ranting melawan langit terang tampak pinggiran ungu (*purple fringing*) tipis yang wajar untuk zoom 6x kompak.
  - **Noise & Cahaya Redup:** Sampel ISO 800 dalam ruang memperlihatkan butiran *noise* luminansi ringan dan sedikit perataan detail halus pada bidang gelap palet kayu, sementara sampel ISO 400 luar ruang masih menjaga detail dedaunan cukup utuh dengan butiran halus di bayangan dalam; di atas ISO 400 degradasi tekstur mulai cepat terasa sesuai konsensus ulasan lab.
  - **Vignette & Flash:** Vignette optik sangat tipis dan hampir tak terasa pada bukaan menengah; flash internal yang dekat lensa menghasilkan cahaya langsung berkontras kuat untuk *snapshot*, sedangkan dudukan *hot shoe* memungkinkan kilat pantul lebih lembut.
- **lookTags:** `crisp-ccd-detail`, `natural-canon-color`, `punchy-daylight-contrast`, `gritty-high-iso`, `subtle-purple-fringing`
- **Arketipe:** `ccd_compact_2000s` *(dikonfirmasi)*
- **confidenceLook:** **`high`** — Didukung inspeksi visual 3 foto EXIF SOOC lintas pengunggah independen (ISO 100, 400, 800; luar dan dalam ruang) serta konsisten dengan ulasan DPReview, Imaging Resource, CNET, dan Luminous Landscape.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Canon Camera Museum — PowerShot G9](https://global.canon/en/c-museum/product/dcc548.html), [Canon USA Support — PowerShot G9](https://support.usa.canon.com/kb/s/article/ART123834)
  - *Situs Fotografi / Ulasan:* [DPReview — Canon PowerShot G9 Review](https://www.dpreview.com/reviews/3881329841/canong9/), [Imaging Resource — Canon G9 Test](https://www.imaging-resource.com/cameras/canon-g9-review/test/), [CNET — Canon PowerShot G9 Review](https://www.cnet.com/reviews/canon-powershot-g9-review/), [Luminous Landscape — Canon PowerShot G9 Review](https://luminous-landscape.com/canon-powershot-g9-review/)
  - *Foto Sampel Diperiksa Visual (3, EXIF SOOC):*
    1. [Wikimedia Commons (Flickr, CC BY 2.0) — *"A compact car"* (EXIF: Canon PowerShot G9, ISO 800, dalam ruang)](https://commons.wikimedia.org/wiki/File:%22A_compact_car%22.jpg)
    2. [Wikimedia Commons (Direct, Public Domain) — *"Balcone di Giulietta" - Verona* (EXIF: Canon PowerShot G9, ISO 400, luar ruang)](https://commons.wikimedia.org/wiki/File:%22Balcone_di_Giulietta%22_-_Verona_(2009).jpg)
    3. [Wikimedia Commons (Flickr, CC BY-SA 2.0) — *"Janezove stopnice"* (EXIF: Canon PowerShot G9, ISO 100, aliran air & dedaunan)](https://commons.wikimedia.org/wiki/File:%22Janezove_stopnice%22_zalite_do_vrha,_pasti_po_teh_stopnicah,_%C4%8Deprav_*nalit*,_ne_bi_bilo_mo%C5%BEno!_***R.I.P._Janez_!***_(5005775915).jpg)

---

## 22. Canon — PowerShot S95

- **id:** `canon_powershot_s95`
- **brand:** `Canon`
- **model:** `PowerShot S95` *(nama kanonik)*
- **aliases:** `Canon S95`
- **displayName:** `Canon PowerShot S95`
- **Tahun & Jenis:** 2010 · Digital kompak saku antusias (CCD)
- **Spesifikasi Inti (Resmi):** Sensor CCD 1/1,7" 10,0 MP efektif; prosesor DIGIC 4; lensa zoom 3,8x ekuiv. 28–105mm f/2.0–4.9 dengan *Hybrid IS*; ISO 80–3200; rana 15–1/1600 dtk; RAW (.CR2) & JPEG.
- **Ketergantungan Mode Warna (Digital):** Palet hangat-organik dan kedalaman mikro-kontras CCD hadir langsung pada **mode bawaan (default JPEG / *My Colors Off*)** maupun konversi RAW standar (Canon DPP). Kamera menyediakan *My Colors* (*Vivid*, *Positive Film*, *Nostalgic*, *Neutral*, *B&W*) serta *i-Contrast* untuk melunakkan bayangan; sampel malam murni SOOC JPEG sementara dua sampel siang melewati Canon DPP dan Picasa dengan karakter tonal yang tetap seragam.
- **Deskripsi Tampilan Hasil Foto (Inspeksi Visual + Sumber):**
  - **Warna & White Balance:** Reproduksi merah bata, papan hijau zamrud, dan biru bodi bus malam tampil kaya, padat, dan berkarakter hangat-emas yang sangat menyenangkan. Pada cahaya senja bersalju (sampel rumah klasik), pantulan cahaya matahari rendah memberi semburat persik-keemasan alami di atas pilar putih tanpa merusak keseimbangan biru dingin langit.
  - **Kontras & Sorotan (*Highlight*):** Dibanding kompak CCD 1/2,5", transisi sorotan S95 terasa lebih luwes dan bernapas seperti film negatif; pada foto jalanan malam Riga (ISO 800), lampu jalan kuning memunculkan pendaran melingkar lembut yang organik sementara detail rambu dan jendela toko tetap terbaca rapi.
  - **Ketajaman, Aberasi & Kelembutan:** Pada ISO 80 ketajaman pusat sangat solid dengan mikro-kontras halus di permukaan bata dan plester; pinggiran ungu pada ranting terhadap langit senja nyaris tidak mencolok. Bukaan lebar f/2.0 di 28mm memberi sedikit separasi kedalaman lembut pada objek dekat.
  - **Noise & Cahaya Redup:** Kombinasi sensor 1/1,7" 10 MP dan lensa f/2.0 membuat foto malam ISO 800 jauh lebih bersih dibanding kompak CCD biasa — area langit gelap dan batu jalan berpola butiran halus yang rapi tanpa bercak warna kasar, walau tekstur sangat halus mulai sedikit melunak.
  - **Vignette & Flash:** Vignette optik lembut dan hampir tidak terlihat pada jarak normal; *pop-up flash* internal menghasilkan kilat langsung yang dapat diturunkan kompensasinya untuk pengisi lembut.
- **lookTags:** `warm-filmic-ccd`, `rich-golden-tones`, `organic-micro-contrast`, `graceful-night-halation`, `clean-lowlight-ccd`
- **Arketipe:** `pro_ccd_filmic` *(dikonfirmasi)*
- **confidenceLook:** **`high`** — Didukung inspeksi visual 3 foto EXIF terverifikasi lintas kondisi cahaya (mendung siang, malam ISO 800, senja keemasan) serta selaras dengan ulasan Imaging Resource, CNET, dan Photo Thinking.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Canon Indonesia Support — PowerShot S95](https://id.canon/id/support/6200085300)
  - *Situs Fotografi / Ulasan:* [DPReview — Canon PowerShot S95](https://www.dpreview.com/articles/2660341179/canonpowershots95/), [Imaging Resource — Canon S95 Review](https://www.imaging-resource.com/cameras/canon-s95-review/), [CNET — Canon PowerShot S95 Review](https://www.cnet.com/reviews/canon-powershot-s95-review/), [Photo Thinking — Canon PowerShot S95](https://photothinking.com/2024-07-22-canon-powershot-s95-pocket-power/)
  - *Flickr Camera Finder:* [Flickr — Canon PowerShot S95](https://www.flickr.com/cameras/canon/powershot_s95)
  - *Foto Sampel Diperiksa Visual (3, EXIF terverifikasi):*
    1. [Wikimedia Commons (Flickr, CC BY 2.0) — *"Dogsbody", Claycross* (EXIF: Canon PowerShot S95, ISO 80, Canon DPP)](https://commons.wikimedia.org/wiki/File:%22_Dogsbody%22_,_The_Broadleys_,_Claycross_(6331958120).jpg)
    2. [Wikimedia Commons (Direct, CC BY-SA 3.0) — *1. trolejbuss Tērbatas ielā* (EXIF: Canon PowerShot S95, ISO 800, SOOC malam)](https://commons.wikimedia.org/wiki/File:1._trolejbuss_T%C4%93rbatas_iel%C4%81_2011.jpg)
    3. [Wikimedia Commons (Direct, CC BY-SA 3.0) — *"Белый дом"* (EXIF: Canon PowerShot S95, ISO 80, senja, lewat Picasa)](https://commons.wikimedia.org/wiki/File:%22%D0%91%D0%B5%D0%BB%D1%8B%D0%B9_%D0%B4%D0%BE%D0%BC%22.JPG)

---

## 23. Canon — IXY Digital 900 IS / PowerShot SD800 IS / Digital IXUS 850 IS

- **id:** `canon_ixy_digital_900_is`
- **brand:** `Canon`
- **model:** `IXY Digital 900 IS` *(nama kanonik)*
- **aliases:** `PowerShot SD800 IS` (Amerika Utara), `Digital IXUS 850 IS` (Eropa/Asia)
- **displayName:** `Canon IXY Digital 900 IS / PowerShot SD800 IS / Digital IXUS 850 IS`
- **Tahun & Jenis:** 2006 · Digital ultrakompak saku (CCD)
- **Spesifikasi Inti (Resmi):** Sensor CCD 1/2,5" 7,1 MP efektif; prosesor DIGIC III; lensa zoom 3,8x sudut lebar ekuiv. 28–105mm f/2.8–5.8 dengan *Optical IS*; ISO 80–1600; rana 15–1/1600 dtk; JPEG.
- **Ketergantungan Mode Warna (Digital):** Tampilan warna segar khas Canon era DIGIC III muncul langsung pada **JPEG bawaan (default Auto / *My Colors Off*)**. Mode *My Colors* (*Vivid*, *Positive Film*, *Color Accent*, *Neutral*) tersedia sebagai pilihan opsional, tetapi ketiga sampel yang diperiksa memperlihatkan rendering standar bawaan kamera.
- **Deskripsi Tampilan Hasil Foto (Inspeksi Visual + Sumber):**
  - **Warna & White Balance:** Di bawah matahari cerah (sampel gudang logam & stadion bata), langit biru tampil jenuh dan bersih khas biru kobalt Canon 2000-an, dengan hijau rumput dan pintu limau mencolok serta rona bata yang hangat keemasan. Pada suasana *blue hour* pelabuhan, keseimbangan putih menjaga biru sejuk air dan langit dengan awan merah muda lembut serta tiang layar jingga hangat.
  - **Kontras & Sorotan (*Highlight*):** Kontras bawaan terasa mantap dan hidup untuk ukuran saku. Lampu stadion dan awan terang cepat memutih rata (*clipped*), namun pada pencahayaan matahari miring sore detail dinding bata dan bayangan miring terjaga tegas.
  - **Ketajaman, Aberasi & Kelembutan:** Bagian tengah bingkai tajam dan renyah pada ISO rendah, sedangkan di sudut lebar 28mm ujung-ujung bingkai memperlihatkan sedikit kelembutan optik dan distorsi barel ringan khas lensa ramping sudut lebar generasi awal.
  - **Noise & Cahaya Redup:** Sangat bersih pada cahaya terang; pada foto senja pelabuhan mulai terlihat bintik *noise* ringan dan pelunakan tekstur di lambung kapal gelap serta riak air bayangan, sejalan dengan ulasan yang mencatat penghalusan mirip sapuan cat pada ISO tinggi.
  - **Vignette & Flash:** Vignette sudut ringan kadang tampak tipis di langit biru sudut lebar 28mm; flash depan dekat lensa menghasilkan kilat langsung kontras tegas khas *party snapshot* 2006.
- **lookTags:** `vibrant-2000s-ccd`, `warm-canon-palette`, `soft-wide-corners`, `clean-base-iso`, `smeared-high-iso`
- **Arketipe:** `ccd_compact_2000s` *(dikonfirmasi)*
- **confidenceLook:** **`high`** — Didukung inspeksi visual 3 foto EXIF terverifikasi dari 3 fotografer lintas platform (Geograph & Flickr; mencakup varian SD800 IS dan IXUS 850 IS) serta konsisten dengan DPReview, CNET, dan Imaging Resource.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Canon Camera Museum — IXY Digital 900 IS / SD800 IS](https://global.canon/en/c-museum/product/dcc534.html)
  - *Situs Fotografi / Ulasan:* [DPReview — Canon PowerShot SD800 IS Review](https://www.dpreview.com/reviews/6039752007/canonsd800is/), [CNET — Canon PowerShot ELPH SD800 IS Review](https://www.cnet.com/reviews/canon-powershot-elph-sd800-is-review/), [Imaging Resource — Canon PowerShot SD800 IS](https://www.imaging-resource.com/news/full-review-update-posted-for-canon-powershot-sd800-is/)
  - *Foto Sampel Diperiksa Visual (3, EXIF terverifikasi):*
    1. [Wikimedia Commons (Geograph, CC BY-SA 2.0) — *"New" DIY Store, Emscote* (EXIF: Canon DIGITAL IXUS 850 IS, SOOC cerah 28mm)](https://commons.wikimedia.org/wiki/File:%22New%22_DIY_Store,_Emscote_-_geograph.org.uk_-_1210410.jpg)
    2. [Wikimedia Commons (Flickr, CC BY 2.0) — *"The Big Wall" B&O Warehouse* (EXIF: Canon PowerShot SD800 IS, sore hangat)](https://commons.wikimedia.org/wiki/File:%22The_Big_Wall%22_B%26O_Warehouse.jpg)
    3. [Wikimedia Commons (Geograph, CC BY-SA 2.0) — *'Greater Manchester Challenge'* (EXIF: Canon DIGITAL IXUS 850 IS, SOOC senja)](https://commons.wikimedia.org/wiki/File:%27Greater_Manchester_Challenge%27_-_geograph.org.uk_-_496649.jpg)

---

## 24. Canon — PowerShot A590 IS

- **id:** `canon_powershot_a590_is`
- **brand:** `Canon`
- **model:** `PowerShot A590 IS` *(nama kanonik)*
- **aliases:** `Canon A590 IS`
- **displayName:** `Canon PowerShot A590 IS`
- **Tahun & Jenis:** 2008 · Digital kompak entry-manual (CCD)
- **Spesifikasi Inti (Resmi):** Sensor CCD 1/2,5" 8,0 MP efektif; lensa zoom optik 4x ekuiv. 35–140mm f/2.6–5.5 dengan *Optical IS*; ISO 80–1600; rana 15–1/2000 dtk; mode P/Tv/Av/M; JPEG; daya 2x AA.
- **Ketergantungan Mode Warna (Digital):** Karakter warna hangat-nostalgik dan kontras langsung yang cerah merupakan ciri **JPEG bawaan standar (*My Colors Off*)**. Kamera juga menyediakan *My Colors* (*Vivid*, *Neutral*, *Positive Film*, *Sepia*, *B&W*) dan kontrol manual eksposur penuh, tetapi ketiga sampel yang diamati merepresentasikan profil warna standar Canon.
- **Deskripsi Tampilan Hasil Foto (Inspeksi Visual + Sumber):**
  - **Warna & White Balance:** Pada sampel sore keemasan (*The Corner Pin*, ISO 80) dan dinding bata merah (ISO 80), rona hangat kuning-amber, terakota bata, serta cokelat kayu tampil hangat, cerah, dan penuh rasa nostalgia. Pada sampel bayangan hutan cemara (ISO 320), hijau dedaunan tetap segar namun sedikit lebih terangkat dan ringan dibanding seri G.
  - **Kontras & Sorotan (*Highlight*):** Rentang dinamis sensor CCD 1/2,5" yang sempit terlihat nyata: spanduk dan jendela yang terkena sinar matahari rendah langsung memutih pekat, begitu pula papan informasi putih dan beton terang pada foto totem. Kontras keseluruhan tegas di cahaya terang dan cenderung agak terang (*lifted*) di area terang-teduh campuran.
  - **Ketajaman, Aberasi & Kelembutan:** Sangat tajam dan bersih di ISO 80 (tekstur bata, lis jendela, dan patina perunggu patung tampil terdefinisi baik), dengan sedikit *purple fringing* halus pada celah langit di balik dedaunan rapat.
  - **Noise & Cahaya Redup:** Perbedaan ISO 80 dan ISO 320 langsung terbaca secara visual: pada ISO 320 di bawah kanopi pohon, butiran *noise* halus dan pengurangan kontras bayangan mulai terasa di kulit kayu serta tanah teduh; sesuai ulasan lab, ISO 400 ke atas cepat melunakkan tekstur halus dan memunculkan kekeruhan bayangan.
  - **Vignette & Flash:** Vignette optik nyaris tidak terasa; flash internal langsung memberi sorotan depan tegas dengan jatuh gelap cepat di latar ruang dalam.
- **lookTags:** `punchy-snapshot-ccd`, `warm-nostalgic-color`, `crisp-low-iso`, `clipped-bright-highlights`, `muddy-high-iso`
- **Arketipe:** `ccd_compact_2000s` *(dikonfirmasi)*
- **confidenceLook:** **`high`** — Didukung inspeksi visual 3 foto EXIF terverifikasi dari 3 fotografer berbeda (ISO 80 sore hangat, ISO 80 siang teduh, ISO 320 bawah pohon) serta konsisten dengan Imaging Resource, CNET, dan PCMag AU.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Canon Indonesia Support — PowerShot A590 IS](https://id.canon/id/support/6200005700)
  - *Situs Fotografi / Ulasan:* [DPReview — Canon PowerShot A590 IS](https://www.dpreview.com/products/canon/compacts/canon_a590is/), [Imaging Resource — Canon A590 IS Test](https://www.imaging-resource.com/cameras/canon-a590-is-review/test/), [CNET — Canon PowerShot A590 IS Review](https://www.cnet.com/reviews/canon-powershot-a590-is-review/), [PCMag AU — Canon PowerShot A590 IS](https://au.pcmag.com/electronics/3137/canon-powershot-a590-is)
  - *Foto Sampel Diperiksa Visual (3, EXIF terverifikasi):*
    1. [Wikimedia Commons (Flickr, CC BY 2.0) — *"The Corner Pin" Claycross* (EXIF: Canon PowerShot A590 IS, ISO 80, Canon DPP)](https://commons.wikimedia.org/wiki/File:%22The_Corner_Pin%22_Claycross_(3636847510).jpg)
    2. [Wikimedia Commons (Direct, CC BY-SA 3.0) — *(UBC) MOA 'Imich Siiyem' figure* (EXIF: Canon PowerShot A590 IS, ISO 320, SOOC)](https://commons.wikimedia.org/wiki/File:(UBC)_MOA_%27Imich_Siiyem%27_figure.jpg)
    3. [Wikimedia Commons (Direct, CC BY-SA 3.0) — *"Ночной дозор 3D"* (EXIF: Canon PowerShot A590 IS, ISO 80, SOOC)](https://commons.wikimedia.org/wiki/File:%22%D0%9D%D0%BE%D1%87%D0%BD%D0%BE%D0%B9_%D0%B4%D0%BE%D0%B7%D0%BE%D1%80_3D%22.2.JPG)

---

## 25. Casio — Exilim EX-Z75

- **id:** `casio_exilim_ex_z75`
- **brand:** `Casio`
- **model:** `Exilim EX-Z75` *(nama kanonik)*
- **aliases:** `EX-Z75`, `Casio Z75`
- **displayName:** `Casio Exilim EX-Z75`
- **Tahun & Jenis:** 2007 · Digital ultrakompak saku (CCD)
- **Spesifikasi Inti (Resmi):** Sensor CCD 1/2,5" 7,2 MP efektif; lensa zoom optik 3x ekuiv. 38–114mm f/3.1–5.9; stabilisasi digital *Anti-Shake DSP*; ISO 50–800; rana 1/2–1/2000 dtk; 34 mode *Best Shot*; JPEG.
- **Ketergantungan Mode Warna (Digital):** Nuansa pastel teredam dan kontras lunak muncul langsung pada **setelan bawaan SOOC JPEG** (seluruh 3 sampel memakai firmware `1.01` langsung dari kamera). Casio juga menyediakan 34 preset *Best Shot* (termasuk *Soft*, *Retro*, *Sunset*, *Autumn Leaves*, *Color Filter*) yang dapat menggeser keseimbangan putih dan saturasi lebih jauh, namun karakter *dreamy* lembut sudah melekat pada profil standar.
- **Deskripsi Tampilan Hasil Foto (Inspeksi Visual + Sumber):**
  - **Warna & White Balance:** Berbeda dari Canon atau Samsung yang jenuh tegas, sampel EX-Z75 menunjukkan palet yang lebih tenang, sedikit teredam (*muted*), dan lapang. Pada foto bangunan krem dan dermaga Salvador (ISO 50), biru langit tampil pucat-pastel lembut, dinding krem dan kuning bangunan terasa hangat tipis tanpa saturasi agresif; pada foto dalam ruang (ISO 200), keseimbangan putih menghasilkan nuansa krem-kelabu hangat yang kalem.
  - **Kontras & Sorotan (*Highlight*):** Kontras bawaan relatif lembut dan tidak menekan bayangan menjadi hitam pekat. Di bawah terik matahari pelabuhan, permukaan putih kapal dan bangunan di bukit memunculkan pendaran terang (*airy highlight bloom*) yang memberi kesan melamun khas digicam pastel era 2007.
  - **Ketajaman, Aberasi & Kelembutan:** Bagian tengah cukup bersih di ISO 50, tetapi tepi bingkai dan objek tipis jauh (tiang layar, jendela gedung) tampak agak lunak dengan sedikit penyebaran warna halus dan tanpa penajaman tepi digital yang keras.
  - **Noise & Cahaya Redup:** Pada ISO 200 dalam ruang (sampel adaptor listrik) sudah tampak butiran *noise* luminansi dan warna halus di seluruh permukaan polos serta bayangan; di ISO 400–800 butiran dan pelunakan tekstur semakin terasa sesuai temuan ulasan.
  - **Vignette & Flash:** Sedikit gelap tipis di sudut langit biru cerah; kilat internal langsung cenderung datar dan terang pada jarak dekat, mempertegas gaya kasual lo-fi.
- **lookTags:** `soft-pastel-ccd`, `muted-desaturated-color`, `airy-highlight-bloom`, `gentle-contrast`, `soft-corner-optics`
- **Arketipe:** `ccd_dreamy_pastel` *(dikonfirmasi)*
- **confidenceLook:** **`high`** — Didukung inspeksi visual 3 foto EXIF murni SOOC firmware `1.01` lintas 3 fotografer & 2 platform (Direct & Flickr; luar ruang cerah, dermaga terik, dalam ruang ISO 200) serta konsisten dengan Imaging Resource, Reviewed, dan CNET.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Casio Archive — EX-Z75 Specifications](http://arch.casio-intl.com/asia-mea/en/dc/past/ex_z75/spec.html)
  - *Situs Fotografi / Ulasan:* [Imaging Resource — Casio EX-Z75 Review](http://old.imaging-resource.com/PRODS/Z75/Z75A.HTM), [Reviewed — Casio Exilim EX-Z75 Review](https://www.reviewed.com/cameras/content/casio-exilim-ex-z75-digital-camera-review-14929), [CNET — Casio Exilim EX-Z75 Review](https://www.cnet.com/reviews/casio-exilim-ex-z75-review/), [DPReview — Casio EX-Z75 Specs](https://www.dpreview.com/products/casio/compacts/casio_exz75/)
  - *Foto Sampel Diperiksa Visual (3, EXIF SOOC firmware 1.01):*
    1. [Wikimedia Commons (Direct, CC BY-SA 3.0) — *20091226ExMarconi02* (EXIF: EX-Z75, ISO 50, luar ruang)](https://commons.wikimedia.org/wiki/File:20091226ExMarconi02.JPG)
    2. [Wikimedia Commons (Direct, CC BY-SA 3.0) — *3 Outlet Extension* (EXIF: EX-Z75, ISO 200, dalam ruang)](https://commons.wikimedia.org/wiki/File:3_Outlet_Extension.JPG)
    3. [Wikimedia Commons (Flickr, CC BY-SA 2.0) — *A Marina de Salvador* (EXIF: EX-Z75, ISO 50, terik matahari)](https://commons.wikimedia.org/wiki/File:A_Marina_de_Salvador.jpg)

---

## 26. Casio — Exilim EX-S10

- **id:** `casio_exilim_ex_s10`
- **brand:** `Casio`
- **model:** `Exilim EX-S10` *(nama kanonik)*
- **aliases:** `EX-S10`, `Exilim Card EX-S10`
- **displayName:** `Casio Exilim EX-S10`
- **Tahun & Jenis:** 2008 · Digital ultrakompak kartu ramping (CCD)
- **Spesifikasi Inti (Resmi):** Sensor CCD 1/2,3" 10,1 MP efektif; bodi logam sangat tipis; lensa zoom optik 3x ekuiv. 36–108mm f/2.8–5.3; tanpa IS optik; ISO 50–1600; rana 1/2–1/2000 dtk; JPEG.
- **Ketergantungan Mode Warna (Digital):** Tampilan lembut-hangat dengan pendaran langit terang hadir langsung pada **JPEG bawaan standar (SOOC firmware `1.02S`)**. Kamera juga dilengkapi deretan preset *Best Shot* (termasuk *Soft*, *Retro*, *Twilight*, *Sunset*) serta filter warna internal yang dapat memperkuat efek melamun, namun karakter optik kartu tipisnya sudah jelas terlihat di mode otomatis standar.
- **Deskripsi Tampilan Hasil Foto (Inspeksi Visual + Sumber):**
  - **Warna & White Balance:** Di cahaya siang hari (sampel Edinburgh, Pau, dan menara jam Akko, seluruhnya ISO 50), warna tampil alami dengan kecenderungan hangat ringan pada batu pasir dan dinding krem, sementara aksen merah toko dan hijau papan tetap muncul tegas tanpa terlihat neon berlebihan.
  - **Kontras & Sorotan (*Highlight*):** Sangat khas kamera CCD kartu tipis: pada adegan kontras tinggi atau sedikit *backlight* (sampel Menara Jam Akko dan gedung batu Edinburgh), langit terang cepat penuh menjadi putih bersih dengan pendaran (*bloom*) lembut yang memeluk tepi atap, menara, dan dedaunan, menghasilkan estetika *dreamy* ringan tanpa harus menambahkan filter eksternal.
  - **Ketajaman, Aberasi & Kelembutan:** Desain lensa super-ramping menghasilkan pusat bingkai yang cukup bersih tetapi detail tekstur halus (daun palem, batu bata jauh, tepi bingkai) cenderung lebih lunak dan licin, bebas dari penajaman buatan yang tajam menusuk ala ponsel modern.
  - **Noise & Cahaya Redup:** Pada ISO 50 siang hari area rata tampak sangat bersih; sejalan dengan hasil uji Imaging Resource dan CNET, mulai ISO 400 ke atas peredaman *noise* internal Casio cepat meratakan tekstur halus menjadi bidang warna lembut dan agak buram.
  - **Vignette & Flash:** Vignette sangat ringan; flash langsung pada bodi sangat tipis memancarkan cahaya keras dari dekat lensa yang mudah menerangi subjek depan sambil menjatuhkan latar gelap pada suasana dalam ruang.
- **lookTags:** `soft-slim-optics`, `warm-y2k-casual`, `airy-sky-bloom`, `smoothed-fine-texture`, `dreamy-snapshot-tone`
- **Arketipe:** `ccd_dreamy_pastel` *(dikonfirmasi)*
- **confidenceLook:** **`high`** — Didukung inspeksi visual 3 foto EXIF terverifikasi dari 3 fotografer & 2 platform (Direct & Flickr; termasuk 2 SOOC firmware `1.02S`) serta selaras dengan ulasan Imaging Resource dan CNET.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Casio Archive — EX-S10 Specifications](http://arch.casio-intl.com/asia-mea/en/dc/ex_s10/spec/)
  - *Situs Fotografi / Ulasan:* [Imaging Resource — Casio EX-S10 Test](https://www.imaging-resource.com/cameras/casio-ex-s10-review/test/), [CNET — Casio Exilim EX-S10 Review](https://www.cnet.com/reviews/casio-exilim-ex-s10-review/), [DPReview — Casio EX-S10 Specs](https://www.dpreview.com/products/casio/compacts/casio_exs10/)
  - *Flickr Camera Finder:* [Flickr — Casio Exilim EX-S10](https://www.flickr.com/cameras/casio/ex-s10)
  - *Foto Sampel Diperiksa Visual (3, EXIF terverifikasi):*
    1. [Wikimedia Commons (Direct, CC BY-SA 4.0) — *1-5 Blenheim Place* (EXIF: EX-S10, ISO 50, SOOC firmware 1.02S)](https://commons.wikimedia.org/wiki/File:1-5_Blenheim_Place.JPG)
    2. [Wikimedia Commons (Direct, CC BY-SA 3.0) — *Agence IDElib et IDEcycles Pau* (EXIF: EX-S10, ISO 50, SOOC firmware 1.02S)](https://commons.wikimedia.org/wiki/File:Agence_IDElib_et_IDEcycles_Pau_Porte_des_Pyr%C3%A9n%C3%A9es.jpg)
    3. [Wikimedia Commons (Flickr, CC BY 2.0) — *Akko Clock Tower* (EXIF: EX-S10, ISO 50, backlit hangat)](https://commons.wikimedia.org/wiki/File:Akko_Clock_Tower-2833842770.jpg)

---

## 27. Nikon — Coolpix S6900

- **id:** `nikon_coolpix_s6900`
- **brand:** `Nikon`
- **model:** `Coolpix S6900` *(nama kanonik)*
- **aliases:** `COOLPIX S6900`, `Nikon S6900`
- **displayName:** `Nikon Coolpix S6900`
- **Tahun & Jenis:** 2014 · Digital ultrakompak swafoto (BSI CMOS)
- **Spesifikasi Inti (Resmi):** Sensor BSI CMOS 1/2,3" 16,0 MP efektif; prosesor EXPEED C2; lensa NIKKOR zoom 12x ekuiv. 25–300mm f/3.3–6.3 dengan *Lens-shift VR*; ISO 125–6400; layar lipat-putar & tombol rana depan; JPEG.
- **Ketergantungan Mode Warna (Digital):** **Sangat bergantung pada kombinasi mode untuk tampilan potret tren modern.** Pada **JPEG standar bawaan (SOOC)** sebagaimana terlihat di 3 foto sampel luar ruang, kamera sudah menunjukkan rentang dinamis sederhana dengan langit berpendar putih-susu, warna segar, dan penghalusan tekstur khas sensor 1/2,3" 16 MP. Namun ciri "kulit halus berpendar pastel" yang membuatnya viral secara khusus diaktifkan melalui ***Glamour Mode* / *Glamour Retouch*** (*Skin Softening*, *Powder*, *Soft*, *Vividness*) yang sering dipadukan dengan kilat internal langsung.
- **Deskripsi Tampilan Hasil Foto (Inspeksi Visual + Sumber):**
  - **Warna & White Balance:** Pada sampel luar ruang SOOC (gerbang kayu hijau Taipei ISO 800, danau berkabut pagi ISO 125, dan pegunungan Alpen ISO 125), warna dasar tampil cerah dan hidup langsung dari kamera — biru langit Alpen dan hijau pinus mencolok, sementara kabut pagi di danau dirender hangat-susu dengan saturasi lembut yang tenang.
  - **Kontras & Sorotan (*Highlight*):** Rentang dinamis sensor mungil 1/2,3" cepat meluap pada langit terang: pada sampel gerbang Taipei (ISO 800), langit mendung memutih rata disertai pendaran lembut (*bloom*) di sekeliling gedung apartemen putih dan dedaunan palem, memberi nuansa kabur-cerah (*milky glow*) yang ringan.
  - **Ketajaman, Aberasi & Kelembutan:** Pada sudut terlebar 25mm tampak sedikit distorsi perspektif dan kelembutan di pinggir bingkai; detail halus dedaunan jauh serta permukaan aspal pada ISO 800 tampak diratakan cukup agresif oleh peredaman *noise* EXPEED C2, memperkuat tekstur lembut tidak terlalu klinis.
  - **Noise & Cahaya Redup:** Meskipun memakai BSI CMOS (bukan CCD), pada ISO 800 tekstur beton dan daun sudah menunjukkan kombinasi butiran halus dan penghalusan bidang warna; pada malam hari/dalam ruang bersama kilat langsung, ulasan mencatat munculnya butiran retro dengan sorotan kulit cerah.
  - **Vignette & Flash:** Vignette sangat minim di cahaya terang; kilat langsung bawaan yang dipadukan *Glamour Mode* (berdasarkan konsensus ulasan Digital Camera World, TechRadar, dan PhotoWorkout; potret wajah dikecualikan dari sampel visual demi aturan privasi) meratakan bayangan wajah dan menghasilkan efek kulit halus berpendar.
- **lookTags:** `milky-highlight-glow`, `glamour-soft-skin`, `warm-pastel-haze`, `vibrant-sooc-color`, `smoothed-cmos-texture`
- **Arketipe:** `ccd_dreamy_pastel` *(dikonfirmasi — catatan: perangkat keras BSI CMOS, dipetakan berdasarkan keselarasan karakter visual)*
- **confidenceLook:** **`high`** — Didukung inspeksi visual 3 foto EXIF murni SOOC (`COOLPIX S6900V1.1`) lintas 3 fotografer independen (ISO 125 & 800) ditambah 3 sumber ulasan independen (Digital Camera World, TechRadar, PhotoWorkout) yang konsisten menjelaskan perilaku *Glamour Mode* & kilat.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Nikon Global News — Coolpix S6900](https://www.nikon.com/company/news/2014/0912_cpx_02/), [Nikon Reference Manual — Coolpix S6900 (PDF)](https://download.nikonimglib.com/archive2/R9bPp00WTnuX01xldjA83DQHes41/S6900RM_%28En%2904.pdf)
  - *Situs Fotografi / Ulasan:* [Imaging Resource — Nikon S6900 Review](https://www.imaging-resource.com/cameras/nikon-s6900-review/), [Digital Camera World — Nikon Coolpix S6900](https://www.digitalcameraworld.com/cameras/compact-cameras/this-unremarkable-nikon-coolpix-s6900-compact-camera-is-trending-heres-why-and-what-id-buy-instead), [TechRadar — Nikon Coolpix S6900 Review](https://www.techradar.com/reviews/cameras-and-camcorders/cameras/compact-cameras/nikon-coolpix-s6900-1265200/review/5), [PhotoWorkout — Nikon Coolpix S6900](https://www.photoworkout.com/camera/nikon-coolpix-s6900/)
  - *Foto Sampel Diperiksa Visual (3, EXIF SOOC COOLPIX S6900V1.1):*
    1. [Wikimedia Commons (Direct, CC BY-SA 4.0) — *牯嶺街高等官舍群* (EXIF: COOLPIX S6900, ISO 800, mendung)](https://commons.wikimedia.org/wiki/File:-%E7%89%AF%E5%B6%BA%E8%A1%97%E9%AB%98%E7%AD%89%E5%AE%98%E8%88%8D%E7%BE%A4.jpg)
    2. [Wikimedia Commons (Direct, CC BY-SA 4.0) — *Bliss of Denwa* (EXIF: COOLPIX S6900, ISO 125, kabut pagi)](https://commons.wikimedia.org/wiki/File:Bliss_of_Denwa.jpg)
    3. [Wikimedia Commons (Direct, CC BY-SA 4.0) — *Fontanalba - Chanvrairée* (EXIF: COOLPIX S6900, ISO 125, cerah pegunungan)](https://commons.wikimedia.org/wiki/File:Fontanalba_-_Chanvrair%C3%A9e_-_795.jpg)

---

## 28. Nikon — Coolpix P7000

- **id:** `nikon_coolpix_p7000`
- **brand:** `Nikon`
- **model:** `Coolpix P7000` *(nama kanonik)*
- **aliases:** `COOLPIX P7000`, `Nikon P7000`
- **displayName:** `Nikon Coolpix P7000`
- **Tahun & Jenis:** 2010 · Digital kompak antusias (CCD)
- **Spesifikasi Inti (Resmi):** Sensor RGB CCD 1/1,7" 10,1 MP efektif; prosesor EXPEED C2; lensa NIKKOR ED zoom 7,1x ekuiv. 28–200mm f/2.8–5.6 dengan *Optical VR*; ISO 100–3200 (Hi 1 6400); RAW (.NRW) & JPEG.
- **Ketergantungan Mode Warna (Digital):** Ketajaman bersih dan warna alami-bertenaga hadir langsung pada ***COOLPIX Picture Control* standar (default JPEG)**; pengguna dapat memilih profil *Standard*, *Neutral*, *Vivid*, dan *Monochrome* (serta pengaturan bertingkat ketajaman, kontras, dan saturasi) maupun *Active D-Lighting* untuk membuka bayangan.
- **Deskripsi Tampilan Hasil Foto (Inspeksi Visual + Sumber):**
  - **Warna & White Balance:** Pada ketiga sampel ISO 100 (mural jam matahari Rapperswil, plakat kuningan *Stolperstein* SOOC, dan foto bawah air ikan badut dengan kilat), warna tampak jernih, akurat, dan bertenaga tanpa pergeseran rona aneh — merah bata/terakota mural, emas kuningan kusam, serta jingga terang ikan badut melawan biru-hijau air laut terpisah dengan bersih. Di cahaya campuran dalam ruang, ulasan mencatat *white balance* otomatis sesekali cenderung agak sejuk.
  - **Kontras & Sorotan (*Highlight*):** Kontras tegas namun tetap terkendali dengan separasi bayangan dalam yang solid (terlihat pada butiran aspal hitam di sekitar plakat kuningan serta bayangan gelap bulu laut bawah air). Gradasi nada tengah pada plester mural dan logam tergores terjaga utuh tanpa terpotong kasar.
  - **Ketajaman, Aberasi & Kelembutan:** Keunggulan utama lensa NIKKOR ED dan sensor 1/1,7" CCD tampak mencolok di ISO 100: setiap guratan halus logam kuningan, pori plester dinding, dan tentakel anemon tergambar amat tajam dari tengah hingga tepi bingkai tanpa halo penajaman berlebihan.
  - **Noise & Cahaya Redup:** Sangat bersih pada ISO 100–200; mengacu pada pengujian CNET, Amateur Photographer, dan DPReview, mulai ISO 400–800 *noise* bayangan meningkat dan peredaman *noise* JPEG bawaan mulai menghaluskan detail mikro, sehingga berkas RAW (.NRW) banyak dipilih untuk mempertahankan tekstur.
  - **Vignette & Flash:** Vignette sangat rendah berkat koreksi optik; *pop-up flash* bawaan (dan dukungan *hot shoe* i-TTL) menghasilkan eksposur kilat seimbang dengan warna tetap tajam.
- **lookTags:** `crisp-nikon-ccd`, `clean-low-iso-detail`, `accurate-punchy-color`, `solid-shadow-contrast`, `softened-high-iso`
- **Arketipe:** `ccd_compact_2000s` *(dikonfirmasi)*
- **confidenceLook:** **`high`** — Didukung inspeksi visual 3 foto EXIF terverifikasi `COOLPIX P7000` (1 SOOC murni, 1 lewat ViewNX 2.3, 1 bawah air dengan flash lewat Photoshop CS4) ditambah konsistensi uji CNET, Amateur Photographer, DPReview, dan Imaging Resource.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Nikon Global News — Coolpix P7000](https://www.nikon.com/company/news/2010/0908_coolpixp7000_01/)
  - *Situs Fotografi / Ulasan:* [DPReview — Nikon Coolpix P7000](https://www.dpreview.com/articles/3148282345/nikonp7000/), [Imaging Resource — Nikon P7000 Specs](https://www.imaging-resource.com/cameras/nikon-p7000-review/specifications/), [CNET — Nikon Coolpix P7000 Review](https://www.cnet.com/reviews/nikon-coolpix-p7000-review/), [Amateur Photographer — Nikon Coolpix P7000 Review](https://amateurphotographer.com/review/nikon-coolpix-p7000/)
  - *Flickr Camera Finder:* [Flickr — Nikon Coolpix P7000](https://www.flickr.com/cameras/nikon/coolpix_p7000)
  - *Foto Sampel Diperiksa Visual (3, EXIF COOLPIX P7000):*
    1. [Wikimedia Commons (Direct, CC BY-SA 3.0) — *Sonnenuhr Rathaus Rapperswil* (EXIF: COOLPIX P7000, ISO 100, ViewNX 2.3)](https://commons.wikimedia.org/wiki/File:%22Mis_st%C3%BCndli_stoht_in_diner_hand%22_-_Inschrift_oberhalb_der_Sonnenuhr_an_der_S%C3%BCdseite_des_Rathauses_Rapperswil_2012-09-20_17-17-32_(P7000).jpg)
    2. [Wikimedia Commons (Direct, CC BY-SA 3.0) — *East Timor Clown Fish* (EXIF: COOLPIX P7000, ISO 100, bawah air + flash, lewat Photoshop CS4)](https://commons.wikimedia.org/wiki/File:02-EastTimor-Dive_Bobs-Rock_13_(Clown_Fish)-APiazza.JPG)
    3. [Wikimedia Commons (Direct, CC BY-SA 4.0) — *Stolperstein Lünen Eduard Petrat* (EXIF: COOLPIX P7000, ISO 100, SOOC COOLPIX P7000V1.1)](https://commons.wikimedia.org/wiki/File:Stolperstein_seit_2014_in_L%C3%BCnen_Eduard_Petrat.JPG)

---

## 29. Nikon — Coolpix 990

- **id:** `nikon_coolpix_990`
- **brand:** `Nikon`
- **model:** `Coolpix 990` *(nama kanonik)*
- **aliases:** `Nikon E990`, `COOLPIX 990`
- **displayName:** `Nikon Coolpix 990`
- **Tahun & Jenis:** 2000 · Digital kompak prosumer bodi putar (CCD)
- **Spesifikasi Inti (Resmi):** Sensor CCD 1/1,8" 3,2 MP efektif; lensa Zoom-Nikkor 3x ekuiv. 38–115mm f/2.5–4.0 dengan makro 2 cm; bodi magnesium putar-belah (*swivel*); ISO 100–400; rana 8–1/1000 dtk; JPEG & TIFF.
- **Ketergantungan Mode Warna (Digital):** Karakter digital jujur awal milenium berasal langsung dari **setelan standar bawaan (default JPEG, *Image Adjustment Auto/Normal*)** tanpa simulasi film modern. Kamera menyediakan pengaturan dasar kontras, ketajaman, dan mode monokrom di menu, tetapi estetika warna pekat dan *clipping* sorotan yang tegas tampil langsung dari firmware `E990v1.0/v1.1`.
- **Deskripsi Tampilan Hasil Foto (Inspeksi Visual + Sumber):**
  - **Warna & White Balance:** Pada sampel lokomotif listrik merah ÖBB di Selzthal (ISO 100 SOOC) dan bukit musim gugur Maine (ISO 100), terlihat palet awal 2000-an yang amat ikonik: merah-jingga menyala penuh tenaga berpadu biru-hijau pegunungan yang pekat dan sedikit dingin (*cyan-blue*). Pada sampel makro tembikar museum (ISO 100 SOOC), latar kelabu cenderung sedikit dingin kebiruan yang rapi, sementara di bawah lampu pijar dalam ruang ulasan mencatat AWB mudah bergeser hangat kekuningan.
  - **Kontras & Sorotan (*Highlight*):** Kontras terasa grafis dan lugas tanpa pemulihan HDR komputasional. Pantulan matahari pada kisi logam lokomotif, pelat beton terang, serta langit mendung di atas bukit langsung memutih padat (*stark highlight clip*).
  - **Ketajaman, Aberasi & Kelembutan:** Optik Zoom-Nikkor dengan kemampuan makro dekat menghasilkan ketajaman optis yang amat jujur dan organik pada resolusi 3,2 MP — detail retakan keramik, garis rel, dan kisi lokomotif tajam alami tanpa tepian plastik. Sedikit pinggiran ungu-sian tipis muncul pada siluet cemara puncak bukit melawan langit putih.
  - **Noise & Cahaya Redup:** Di ISO 100 permukaan rata cukup bersih namun sudah menunjukkan tekstur piksel awal milenium yang sedikit berbintik halus di bayangan; sesuai ulasan DPReview, PetaPixel, dan Camera Labs, naik ke ISO 200–400 langsung memunculkan butiran kasar tegas tanpa peredaman *noise* modern yang merusak ketajaman.
  - **Vignette & Flash:** Vignette optik nyaris tak terlihat; flash internal di dekat bagian putar lensa memberi kilat langsung tegas yang cocok untuk dokumentasi jarak dekat maupun gaya *snapshot* tahun 2000.
- **lookTags:** `early-2000s-ccd`, `deep-blues-and-reds`, `stark-highlight-clip`, `honest-optical-sharpness`, `gritty-early-grain`
- **Arketipe:** `ccd_compact_2000s` *(dikonfirmasi)*
- **confidenceLook:** **`high`** — Didukung inspeksi visual 3 foto EXIF `E990` dari 3 fotografer lintas platform (Flickr & Direct; lanskap musim gugur, makro museum SOOC, lokomotif siang SOOC) ditambah ulasan DPReview, PetaPixel, dan Camera Labs.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Nikon Field Test Guide — Coolpix 990 (PDF)](http://cdn-10.nikon-cdn.com/pdf/cp990ftg.pdf)
  - *Situs Fotografi / Ulasan:* [DPReview — Nikon Coolpix 990 Review](https://www.dpreview.com/reviews/9276144772/nikoncp990/), [PetaPixel — Revisiting Nikon Coolpix 990](https://petapixel.com/2022/02/08/revisiting-the-unique-nikon-coolpix-990-swivel-camera-22-years-later/), [Camera Labs — Nikon Coolpix 990 Retro Review](https://www.cameralabs.com/nikon-coolpix-990-retro-review/)
  - *Flickr Camera Finder:* [Flickr — Nikon Coolpix 990](https://www.flickr.com/cameras/nikon/coolpix_990)
  - *Foto Sampel Diperiksa Visual (3, EXIF E990):*
    1. [Wikimedia Commons (Flickr, CC BY 2.0) — *Maine autumn hillside* (EXIF: E990, ISO 100, lanskap musim gugur)](https://commons.wikimedia.org/wiki/File:-Users-ddb-Desktop-maine-Maine_-_11_-_Flickr_-_ddb_and_kdw.jpg)
    2. [Wikimedia Commons (Direct, CC BY-SA 4.0) — *01592 Bodegon Puntal 2* (EXIF: E990, ISO 100, SOOC E990v1.0 makro)](https://commons.wikimedia.org/wiki/File:01592_Bodegon_Puntal_2.JPG)
    3. [Wikimedia Commons (Direct, CC BY 3.0) — *1040 selzthal 1* (EXIF: E990, ISO 100, SOOC E990v1.1 siang cerah)](https://commons.wikimedia.org/wiki/File:1040_selzthal_1.jpg)

---

## 30. Panasonic — Lumix DMC-LX3

- **id:** `panasonic_lumix_dmc_lx3`
- **brand:** `Panasonic`
- **model:** `Lumix DMC-LX3` *(nama kanonik)*
- **aliases:** `DMC-LX3`, `Lumix LX3`, `Panasonic LX3`
- **displayName:** `Panasonic Lumix DMC-LX3`
- **Tahun & Jenis:** 2008 · Digital kompak antusias multi-aspek (CCD)
- **Spesifikasi Inti (Resmi):** Sensor RGB CCD 1/1,63" 10,1 MP efektif multi-aspek (4:3, 3:2, 16:9); prosesor Venus Engine IV; lensa Leica DC Vario-Summicron 2,5x ekuiv. 24–60mm f/2.0–2.8 dengan *MEGA O.I.S.*; ISO 80–3200 (ekspansi 6400); RAW (.RW2) & JPEG.
- **Ketergantungan Mode Warna (Digital):** Tampilan filmis berkarakter kuat sudah muncul pada **setelan JPEG standar (*Film Mode Standard*)** berkat sensor CCD besar dan lensa Leica cepat (ketiga sampel SOOC firmware `Ver.1.2`/`Ver.2.1` merepresentasikan profil warna standar-hidup siang hari). Selain itu, LX3 sangat masyhur karena menu ***Film Mode*** (*Standard*, *Dynamic*, *Nature*, *Smooth*, *Vibrant*, *Nostalgic*, serta *Dynamic B&W* berkontras hitam pekat) yang sering dipilih pengguna untuk nuansa analog langsung dari kamera.
- **Deskripsi Tampilan Hasil Foto (Inspeksi Visual + Sumber):**
  - **Warna & White Balance:** Ketiga sampel SOOC ISO 80 (tepi Sungai Seine Paris 16:9, mercusuar merah Westkapelle 16:9, dan trem kuning Dresden di bawah jembatan layang) memamerkan kedalaman warna yang sangat berkarakter: merah mercusuar pekat dan lembut seperti emulsi slide, biru laut dalam dan bergradasi mulus, kuning trem menyala padat tanpa pecah, serta hijau dedaunan zaitun yang organik.
  - **Kontras & Sorotan (*Highlight*):** Kontras terasa dramatis namun matang — bayangan di bawah jembatan layang Dresden jatuh dalam dan pekat (*deep inky shadows*) tanpa tampak kusam, sementara gradasi langit biru cerah dan awan putih meluncur mulus dengan transisi sorotan yang jauh lebih luwes dibanding kompak CCD kecil.
  - **Ketajaman, Aberasi & Kelembutan:** Optik Leica 24mm f/2.0 memberikan ketajaman luar biasa dari pusat hingga tepi bingkai lebar 16:9 maupun 3:2, nyaris bebas distorsi kasatmata maupun pinggiran ungu pada kontras tinggi kabel trem dan siluet pepohonan.
  - **Noise & Cahaya Redup:** Pada ISO 80–200 citra sangat bersih dengan tekstur organik; sebagaimana dilaporkan 35mmc, 35hunter, dan Luminous Landscape, di ISO lebih tinggi maupun mode *Dynamic B&W*, butiran *noise* luminansi membentuk tekstur padat menyerupai *grain* film hitam-putih klasik.
  - **Vignette & Flash:** Vignette optik alami sangat tipis di sudut bingkai 24mm; *pop-up flash* internal dan *hot shoe* menyediakan fleksibilitas kilat langsung maupun pantul.
- **lookTags:** `filmic-leica-ccd`, `deep-shadow-contrast`, `rich-slide-colors`, `smooth-highlight-rolloff`, `multi-aspect-wide`
- **Arketipe:** `pro_ccd_filmic` *(dikonfirmasi)*
- **confidenceLook:** **`high`** — Didukung inspeksi visual 3 foto EXIF murni SOOC lintas 3 fotografer & 2 platform (Flickr & Direct; 16:9 dan 3:2) serta selaras dengan ulasan DPReview, Imaging Resource, 35mmc, 35hunter, dan Luminous Landscape.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Panasonic Operating Manual — DMC-LX3 (PDF)](https://av.jpn.support.panasonic.com/share/eww/en/dsc/lx3/DMC-LX3_eng.pdf)
  - *Situs Fotografi / Ulasan:* [DPReview — Panasonic DMC-LX3 Review](https://www.dpreview.com/reviews/8089991700/panasonicdmclx3/), [Imaging Resource — Panasonic DMC-LX3 Review](https://www.imaging-resource.com/cameras/panasonic-dmc-lx3-review/), [35mmc — Panasonic LX3](https://www.35mmc.com/20/07/2023/panasonic-lx3-8-months-with-a-14-year-old-point-and-shoot/), [35hunter — Panasonic Lumix LX3](https://35hunter.blog/2019/12/02/one-month-one-camera-december-2019-i-panasonic-lumix-lx3/), [Luminous Landscape — Panasonic LX3 Review](https://luminous-landscape.com/panasonic-lx-3-review/)
  - *Flickr Camera Finder:* [Flickr — Panasonic Lumix DMC-LX3](https://www.flickr.com/cameras/panasonic/dmc-lx3)
  - *Foto Sampel Diperiksa Visual (3, EXIF SOOC):*
    1. [Wikimedia Commons (Flickr, CC BY 2.0) — *Paris Seine 16:9* (EXIF: DMC-LX3, ISO 80, Ver.1.2)](https://commons.wikimedia.org/wiki/File:(PARIS,FR75)_(3875987538).jpg)
    2. [Wikimedia Commons (Direct, CC BY-SA 3.0 nl) — *10-Westkapelle* (EXIF: DMC-LX3, ISO 80, Ver.1.2, 16:9)](https://commons.wikimedia.org/wiki/File:10-Westkapelle.jpg)
    3. [Wikimedia Commons (Direct, CC BY-SA 4.0) — *Drážďany tram* (EXIF: DMC-LX3, ISO 80, Ver.2.1)](https://commons.wikimedia.org/wiki/File:016_Dr%C3%A1%C5%BE%C4%8Fany_P%C5%99estupn%C3%AD_zast%C3%A1vka_bus_tram_vlak2.JPG)

---

## 31. Panasonic — Lumix DMC-FX01

- **id:** `panasonic_lumix_dmc_fx01`
- **brand:** `Panasonic`
- **model:** `Lumix DMC-FX01` *(nama kanonik)*
- **aliases:** `DMC-FX01`, `Lumix FX01`, `Panasonic FX01`
- **displayName:** `Panasonic Lumix DMC-FX01`
- **Tahun & Jenis:** 2006 · Digital ultrakompak sudut lebar (CCD)
- **Spesifikasi Inti:** Sensor CCD 1/2,5" 6,0 MP efektif; prosesor Venus Engine Plus; lensa Leica DC Vario-Elmarit 3,6x ekuiv. 28–102mm f/2.8–5.6 dengan *MEGA O.I.S.*; ISO 80–400 (Mode Sensitivitas Tinggi hingga 1600); JPEG. *(Halaman spesifikasi resmi produsen aktif tidak ditemukan.)*
- **Ketergantungan Mode Warna (Digital):** Karakter tekstur lembut mirip cat air (*painterly NR*) dan palet hangat-nostalgik berasal langsung dari **setelan JPEG bawaan (*Color Effect Off* / Normal, firmware `Ver.1.0`)**. Kamera menyediakan mode *Color Effect* (*Cool*, *Warm*, *B&W*, *Sepia*) untuk pergeseran nada cepat, tetapi ketiga sampel yang diperiksa berasal dari profil standar.
- **Deskripsi Tampilan Hasil Foto (Inspeksi Visual + Sumber):**
  - **Warna & White Balance:** Pada ketiga sampel ISO 80 (kastel bata Italia & ladang gandum, fasad Gotik Barcelona, dan gang bangunan oker Roma), rona hangat aprikot, krem batu, dan terakota tampil sangat enak dipandang dan nostalgik, dipadukan langit biru-sian cerah yang sedikit pucat. Pada ruang dalam, ulasan mencatat sesekali muncul semburat hangat-magenta tipis.
  - **Kontras & Sorotan (*Highlight*):** Kontras pada cahaya matahari langsung (sampel gang Roma) terasa tegas dan menyenangkan dengan bayangan dalam yang bersih, sementara di cahaya mendung/jendela terbuka sorotan langit cepat memutih penuh tanpa transisi panjang.
  - **Ketajaman, Aberasi & Kelembutan:** Ciri khas Venus Engine Plus terbaca jelas saat mengamati sampel kastel & ladang gandum: meskipun struktur utama bangunan tajam, detail mikro yang sangat rapat (batang gandum, dedaunan pepohonan jauh, tekstur plester halus) diratakan halus sehingga berkesan sedikit lembut menyerupai sapuan cat air. Sudut lebar 28mm juga menunjukkan sedikit pelunakan di ujung bingkai.
  - **Noise & Cahaya Redup:** Di ISO 80 siang hari tampak bersih namun sudah memakai penghalusan halus pada area bertekstur tipis; berdasarkan uji DPReview dan Popular Photography, di atas ISO 100–200 *noise* dan pelunakan detail meningkat drastis.
  - **Vignette & Flash:** Vignette sangat tipis di 28mm; flash depan langsung menghasilkan pencahayaan snapshot kasual berkontras tinggi pada jarak dekat.
- **lookTags:** `painterly-nr-texture`, `warm-ochre-daylight`, `soft-fine-foliage`, `nostalgic-28mm-snap`, `gentle-pastel-sky`
- **Arketipe:** `ccd_dreamy_pastel` *(dikonfirmasi)*
- **confidenceLook:** **`high`** — Didukung inspeksi visual 3 foto EXIF terverifikasi `DMC-FX01` dari 3 fotografer lintas platform (Flickr & Direct; 2 SOOC firmware `Ver.1.0`) serta konsisten dengan DPReview, Popular Photography, dan Imaging Resource.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Situs Fotografi / Ulasan:* [DPReview — Panasonic Lumix DMC-FX01 Review](https://www.dpreview.com/reviews/3985055873/panasonicfx01/), [Imaging Resource — Panasonic DMC-FX01 Review](https://www.imaging-resource.com/cameras/panasonic-dmc-fx01-review/), [Popular Photography — Panasonic Lumix DMC-FX01 Review](https://www.popphoto.com/gear/2008/12/camera-review-panasonic-lumix-dmc-fx01/), [Techeblog — Panasonic DMC-FX01 Reviewed](https://www.techeblog.com/panasonic-dmc-fx01-reviewed/)
  - *Flickr Camera Finder:* [Flickr — Panasonic Lumix DMC-FX01](https://www.flickr.com/cameras/panasonic/dmc-fx01)
  - *Foto Sampel Diperiksa Visual (3, EXIF DMC-FX01):*
    1. [Wikimedia Commons (Flickr, CC BY 2.0) — *Kastel & ladang gandum Italia* (EXIF: DMC-FX01, ISO 80, SOOC Ver.1.0)](https://commons.wikimedia.org/wiki/File:...ci_sono_cose_(dal_finestrino_del_loco),che_voi_umani...1.jpg)
    2. [Wikimedia Commons (Direct, CC BY-SA 3.0) — *Plaça de Sant Josep Oriol 10* (EXIF: DMC-FX01, ISO 80, teduh)](https://commons.wikimedia.org/wiki/File:0_Pla%C3%A7a_de_Sant_Josep_Oriol_num_10.JPG)
    3. [Wikimedia Commons (Direct, CC BY-SA 4.0) — *Via dei Falegnami, Roma* (EXIF: DMC-FX01, ISO 80, SOOC Ver.1.0 cerah)](https://commons.wikimedia.org/wiki/File:08-11_v_d_Falegnami_1230728.JPG)

---

## 32. Kodak — EasyShare V570

- **id:** `kodak_easyshare_v570`
- **brand:** `Kodak`
- **model:** `EasyShare V570` *(nama kanonik)*
- **aliases:** `Kodak V570`, `KODAK V570 DUAL LENS`
- **displayName:** `Kodak EasyShare V570`
- **Tahun & Jenis:** 2006 · Digital ultrakompak lensa ganda (CCD)
- **Spesifikasi Inti:** Sistem lensa ganda *Retina Dual Lens* (lensa tetap ultralebar ekuiv. 23mm f/2.8 + lensa zoom 3x ekuiv. 39–117mm f/3.9–4.4), masing-masing dengan sensor CCD 1/2,5" 5,0 MP efektif; ISO 64–400 (800 resolusi rendah); stabilisasi digital; JPEG. *(Halaman spesifikasi resmi produsen aktif tidak ditemukan.)*
- **Ketergantungan Mode Warna (Digital):** Palet hangat khas *Kodak Color Science* hadir langsung pada **mode warna bawaan (*Natural Color* / *High Color* default SOOC JPEG)**; kamera juga menyediakan pilihan *Color Mode* (*High Color*, *Natural Color*, *Low Color*, *B&W*, *Sepia*) serta 22 mode adegan, namun seluruh 3 sampel SOOC langsung mencerminkan karakter bawaan Kodak.
- **Deskripsi Tampilan Hasil Foto (Inspeksi Visual + Sumber):**
  - **Warna & White Balance:** Ketiga sampel murni SOOC (interior gereja 23mm ISO 200 dengan flash, halaman masjid Maroko ISO 64, dan bangunan salmon/terakota Italia saat senja ISO 160) menampilkan ciri *Kodak Color Science* yang sangat khas: warna hangat, bersahaja namun kaya pada merah karpet, emas kaca patri, hijau genteng zamrud, dan dinding terakota senja yang menyala akrab.
  - **Kontras & Sorotan (*Highlight*):** Kontras tergolong sedang-mantap dengan kecenderungan melunakkan area gelap interior secara halus, sementara jendela terang gereja dan lampu jendela senja memutih penuh disertai pendaran lembut; pada sampel interior ultralebar 23mm juga tampak pendaran *lens flare* bundar kecil di dekat altar.
  - **Ketajaman, Aberasi & Kelembutan:** Lensa tetap ultralebar 23mm f/2.8 menampilkan perspektif dramatis khas lensa ganda V570 dengan kelembutan yang cukup terasa di tepi luar bingkai, sedangkan lensa zoom pada ISO 64 menjaga detail tengah cukup rapi namun tetap sedikit lunak di tekstur mikro.
  - **Noise & Cahaya Redup:** Salah satu sifat visual paling menarik dari sampel ISO 160 senja dan ISO 200 interior: butiran *noise* muncul sebagai tekstur luminansi rapat yang halus dan seragam di dinding plester, lebih menyerupai butiran film kasual ketimbang bercak warna digital kasar.
  - **Vignette & Flash:** Vignette halus terasa di sudut lensa ultralebar 23mm; pada sampel interior dengan flash (`Flash=25`), kilat internal mengisi bagian depan altar dan karpet dengan warna hangat tanpa terlalu mematikan suasana cahaya jendela.
- **lookTags:** `warm-kodak-color`, `soft-ultrawide-optics`, `tight-luminance-grain`, `gentle-indoor-flare`, `vintage-dual-lens`
- **Arketipe:** `ccd_compact_2000s` *(dikonfirmasi)*
- **confidenceLook:** **`high`** — Didukung inspeksi visual 3 foto EXIF murni SOOC lintas 3 fotografer berbeda (ISO 64 siang mendung, ISO 160 senja, ISO 200 interior ultralebar + flash) serta selaras dengan Imaging Resource, Reviewed, DPReview, dan CNET.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Situs Fotografi / Ulasan:* [DPReview — Kodak EasyShare V570 Throwback](https://www.dpreview.com/articles/0532135808/throwback-thursday-the-dual-lens-kodak-easyshare-v570/), [Imaging Resource — Kodak V570 Review](https://www.imaging-resource.com/cameras/kodak-v570-review/), [Reviewed — Kodak EasyShare V570 Review](https://www.reviewed.com/cameras/content/kodak-easyshare-v570-digital-camera-review), [CNET — Kodak EasyShare V570 Review](https://www.cnet.com/reviews/kodak-easyshare-v570-review/)
  - *Foto Sampel Diperiksa Visual (3, EXIF SOOC KODAK V570 DUAL LENS):*
    1. [Wikimedia Commons (Direct, CC BY-SA 3.0) — *Bleidenberg church interior* (EXIF: V570, ISO 200, f/2.8 ultralebar + flash)](https://commons.wikimedia.org/wiki/File:1_3_Bleidenberg.JPG)
    2. [Wikimedia Commons (Direct, CC BY-SA 4.0) — *Moulay Idriss Mausoleum* (EXIF: V570, ISO 64, mendung)](https://commons.wikimedia.org/wiki/File:1003157moulayidrissmausole.jpg)
    3. [Wikimedia Commons (Direct, Public Domain) — *Villa Italia saat senja* (EXIF: V570, ISO 160, senja)](https://commons.wikimedia.org/wiki/File:1villalor00_2528.JPG)

---

## 33. Kodak — EasyShare DX6490

- **id:** `kodak_easyshare_dx6490`
- **brand:** `Kodak`
- **model:** `EasyShare DX6490` *(nama kanonik)*
- **aliases:** `Kodak DX6490`, `KODAK DX6490 ZOOM`
- **displayName:** `Kodak EasyShare DX6490`
- **Tahun & Jenis:** 2003 · Digital bridge / superzoom kompak (CCD)
- **Spesifikasi Inti:** Sensor CCD 4,0 MP efektif; lensa Schneider-Kreuznach Variogon zoom optik 10x ekuiv. 38–380mm f/2.8–3.7; ISO 80–400 (800 resolusi rendah); rana 16–1/1700 dtk; mode P/A/S/M; EVF; JPEG. *(Halaman spesifikasi resmi produsen aktif tidak ditemukan.)*
- **Ketergantungan Mode Warna (Digital):** Karakter warna organik agak *matte* dan hangat khas Kodak awal 2000-an muncul langsung pada **setelan JPEG bawaan standar (*Color* normal)**. Menu menyediakan pilihan warna (*Color*, *B&W*, *Sepia*) serta pengaturan ketajaman, tetapi ketiga sampel murni SOOC tanpa olah lanjut menunjukkan tampilan bawaan yang sangat konsisten.
- **Deskripsi Tampilan Hasil Foto (Inspeksi Visual + Sumber):**
  - **Warna & White Balance:** Pada sampel lembah Pyrenees cerah dan hutan pinus Skotlandia, warna hijau padang rumput, coklat semak, dan langit biru cerah tampil organik, seimbang, dan sedikit *matte* seperti cetak film konsumen Kodak awal 2000-an. Pada sampel interior pub kayu dengan kilat langsung, merah dinding menyala amat pekat dan kaya sementara lampu dinding tetap menyisakan pijar kuning hangat.
  - **Kontras & Sorotan (*Highlight*):** Pengukuran eksposur sesekali cenderung sedikit terang di cahaya keras: awan putih di lembah cerah dan papan menu putih di depan kilat langsung pub langsung penuh memutih (*clipped*). Meski demikian, gradasi hijau-coklat pada lanskap mendung tampil lembut dan enak dilihat.
  - **Ketajaman, Aberasi & Kelembutan:** Lensa Schneider-Kreuznach Variogon 10x menyajikan ketajaman siang hari yang solid dan jujur pada 4 MP tanpa efek penajaman digital yang kasar; pada batas puncak pohon gelap melawan langit mendung tampak pinggiran ungu tipis khas zoom super-panjang era 2003.
  - **Noise & Cahaya Redup:** Di cahaya terang sangat bersih; pada bagian gelap bata perapian dan kayu tua pub terlihat butiran *noise* luminansi yang halus dan merata tanpa bercak warna mengganggu, selaras dengan catatan ulasan lab mengenai pola butiran rapi DX6490.
  - **Vignette & Flash:** Vignette optik nyaris tidak terlihat; *pop-up flash* internal menghasilkan kilat langsung bertenaga yang membuat kayu gelap mengilap, dinding merah menyala, dan objek terang dekat kamera cepat terbakar putih.
- **lookTags:** `organic-kodak-matte`, `warm-early-ccd`, `bright-metering-tendency`, `crisp-10x-daylight`, `punchy-direct-flash`
- **Arketipe:** `ccd_compact_2000s` *(dikonfirmasi)*
- **confidenceLook:** **`high`** — Didukung inspeksi visual 3 foto EXIF murni SOOC dari 3 fotografer & 3 platform berbeda (Geograph, Panoramio, Direct; hutan mendung, interior pub ber-flash, lembah cerah) serta konsisten dengan Imaging Resource, PCMag UK, Reviewed, dan CNET.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Situs Fotografi / Ulasan:* [DPReview — Kodak EasyShare DX6490](https://www.dpreview.com/products/kodak/compacts/kodak_dx6490/), [Imaging Resource — Kodak EasyShare DX6490 Review](https://www.imaging-resource.com/news/review-posted-for-kodak-easyshare-dx6490/), [PCMag UK — Kodak EasyShare DX6490](https://uk.pcmag.com/cameras/80120/kodak-easyshare-dx6490), [Reviewed — Kodak EasyShare DX6490](https://www.reviewed.com/cameras/content/digital-camera-review-kodak-easyshare-dx6490), [CNET — Kodak EasyShare DX6490](https://www.cnet.com/reviews/kodak-easyshare-dx-review/)
  - *Arsip Manual:* [Internet Archive — Kodak EasyShare DX6490 Manual](https://archive.org/details/manualzilla-id-7018200)
  - *Foto Sampel Diperiksa Visual (3, EXIF SOOC KODAK DX6490):*
    1. [Wikimedia Commons (Geograph, CC BY-SA 2.0) — *"Glenkin" house* (EXIF: DX6490, hutan mendung)](https://commons.wikimedia.org/wiki/File:%22Glenkin%22_house_-_geograph.org.uk_-_31040.jpg)
    2. [Wikimedia Commons (Panoramio, CC BY-SA 3.0) — *"The Case Is Altered" Pub* (EXIF: DX6490, interior + flash)](https://commons.wikimedia.org/wiki/File:%22The_Case_Is_Altered%22_Pub_from_the_1400s_-_panoramio.jpg)
    3. [Wikimedia Commons (Direct, CC BY-SA 2.0 fr) — *Lembah & kastel Pyrenees* (EXIF: DX6490, siang cerah)](https://commons.wikimedia.org/wiki/File:100_1632.JPG)

---

## 34. Samsung — NV10

- **id:** `samsung_nv10`
- **brand:** `Samsung`
- **model:** `NV10` *(nama kanonik)*
- **aliases:** `Samsung VLUU NV10`, `Samsung Digimax NV10`
- **displayName:** `Samsung NV10`
- **Tahun & Jenis:** 2006 · Digital ultrakompak (CCD)
- **Spesifikasi Inti (Resmi):** Sensor CCD 1/1,8" 10,1 MP efektif; lensa Schneider-Kreuznach Varioplan zoom 3x ekuiv. 35–105mm f/2.8–5.1; stabilisasi digital ASR; ISO 100–1000; rana 15–1/1500 dtk; tombol *Smart Touch*; JPEG.
- **Ketergantungan Mode Warna (Digital):** Tampilan sangat cerah, jenuh, dan berkontras tegas muncul langsung pada **JPEG bawaan standar (SOOC firmware `701121`)**. Kamera juga memiliki efek warna (*Normal*, *B&W*, *Sepia*, *Red*, *Green*, *Blue*, *Negative*, *Custom*) serta penyesuaian kontras/saturasi di menu, tetapi karakter warna menyolok sudah merupakan setelan default NV10.
- **Deskripsi Tampilan Hasil Foto (Inspeksi Visual + Sumber):**
  - **Warna & White Balance:** Ketiga sampel SOOC (`VLUU NV10, NV10`, ISO 200 dalam hanggar dan ISO 400 luar ruang mendung) memperlihatkan dorongan warna khas Samsung 2006 yang sangat bertenaga: garis merah-putih sayap biplan, biru-ungu tua badan pesawat DH.60 Moth, dan kuning cerah pesawat di latar belakang tampil luar biasa jenuh dan mencolok di bawah cahaya campuran hanggar. Di luar ruang hujan (bandara El Alto), aksen kuning-biru trotoar dan taksi tetap menonjol melawan aspal basah kelabu.
  - **Kontras & Sorotan (*Highlight*):** Kontras bawaan keras dan tegas. Sorotan kuat — baik pantulan lampu sorot hanggar pada sayap pesawat maupun celah awan mendung terang di bandara — cepat terpotong menjadi putih penuh disertai pendaran hangat tipis di sekitar pantulan logam/lampu.
  - **Ketajaman, Aberasi & Kelembutan:** Meskipun bersensor 10,1 MP 1/1,8", ketajaman per-piksel terasa agak lunak dibanding Canon G9; garis halus kabel antena bandara dan rangka pesawat tampak sedikit lembut tanpa ketajaman klinis.
  - **Noise & Cahaya Redup:** Sangat konsisten dengan ulasan DPReview, CNET, dan TechRadar: pada ISO 200 dalam hanggar hingga ISO 400 luar ruang, butiran *noise* berpasir (*gritty*) dan sedikit kabut warna halus sudah tampak nyata di langit awan kelabu, lantai beton, serta bayangan gelap bawah sayap.
  - **Vignette & Flash:** Vignette optik minim; *pop-up flash* unik di sisi atas menghasilkan kilat langsung kontras tegas khas kompak gaya hidup 2006.
- **lookTags:** `punchy-high-contrast`, `vivid-consumer-ccd`, `clipped-bright-highlights`, `slightly-soft-10mp`, `gritty-iso400-noise`
- **Arketipe:** `ccd_compact_2000s` *(dikonfirmasi)*
- **confidenceLook:** **`high`** — Didukung inspeksi visual 3 foto EXIF murni SOOC firmware `701121` lintas 2 platform (Direct & Flickr; luar ruang hujan ISO 400 dan 2 interior hanggar ISO 200) serta selaras dengan DPReview, CNET, dan TechRadar.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Samsung UK Support — NV10](https://www.samsung.com/uk/support/model/EC-NV10ZTBA/GB/)
  - *Situs Fotografi / Ulasan:* [DPReview — Samsung NV10 Review](https://www.dpreview.com/reviews/3213703532/samsungnv10/), [Imaging Resource — Samsung NV10 Review](https://www.imaging-resource.com/cameras/samsung-nv10-review/), [CNET — Samsung NV10 Review](https://www.cnet.com/reviews/samsung-nv10-review/), [TechRadar — Samsung NV10 Review](https://www.techradar.com/reviews/cameras-and-camcorders/cameras/compact-cameras/samsung-nv10-68326/review)
  - *Foto Sampel Diperiksa Visual (3, EXIF SOOC VLUU NV10 firmware 701121):*
    1. [Wikimedia Commons (Direct, CC BY-SA 3.0) — *Aeropuerto Internacional de El Alto* (EXIF: NV10, ISO 400, hujan/mendung)](https://commons.wikimedia.org/wiki/File:Aeropuerto_Internacional_de_El_Alto_jdb01.JPG)
    2. [Wikimedia Commons (Flickr, CC BY-SA 2.0) — *Avro Tutor, Shuttleworth Collection* (EXIF: NV10, ISO 200, hanggar)](https://commons.wikimedia.org/wiki/File:Avro_Tutor,_The_Shuttleworth_Collection._(12020059705).jpg)
    3. [Wikimedia Commons (Flickr, CC BY-SA 2.0) — *DH.60 Cirrus Moth, Shuttleworth Collection* (EXIF: NV10, ISO 200, hanggar)](https://commons.wikimedia.org/wiki/File:DH.60_Cirrus_Moth,_The_Shuttleworth_Collection._(12059308165).jpg)

---

## 35. Samsung — ST550 / TL225

- **id:** `samsung_st550`
- **brand:** `Samsung`
- **model:** `ST550` *(nama kanonik)*
- **aliases:** `TL225` (Amerika Utara), `VLUU ST550`, `DualView ST550`
- **displayName:** `Samsung ST550 / TL225`
- **Tahun & Jenis:** 2009 · Digital ultrakompak layar ganda (CCD)
- **Spesifikasi Inti (Resmi):** Sensor CCD 1/2,33" 12,2 MP efektif; lensa Schneider-Kreuznach zoom 4,6x ekuiv. 27–124mm f/3.5–5.9 dengan *Dual IS* (OIS+DIS); ISO 80–3200; layar belakang sentuh 3,5" + layar depan 1,5"; JPEG.
- **Ketergantungan Mode Warna (Digital):** Pada **mode bawaan (default SOOC JPEG)** yang diperiksa di 3 sampel luar ruang, kamera sudah menonjolkan dorongan biru-merah cerah berpadu kontras agak lapang dan peredaman *noise* yang melembutkan tekstur halus. Sementara itu, tampilan potret kulit licin bak *airbrush* yang membuatnya populer secara khusus berasal dari mode ***Beauty Shot*** (mengandalkan deteksi wajah) dan pilihan ***Photo Style Selector*** (*Normal*, *Soft*, *Vivid*, *Forest*, *Retro*, *Cool*, *Calm*, *Classic*).
- **Deskripsi Tampilan Hasil Foto (Inspeksi Visual + Sumber):**
  - **Warna & White Balance:** Pada ketiga sampel murni SOOC ISO 80 (bus biru Incheon, halte kereta Dangé Prancis, dan tebing berkabut Laos), warna biru bodi bus dan aksen merah/jingga grafiti mendapat dorongan cerah yang segar, sementara bidang luas seperti langit mendung dan lautan awan berkabut tampil pastel-sejuk yang lembut dan lapang. Menurut pengujian Imaging Resource dan Reviewed, rona kulit manusia cenderung dibuat hangat sedikit kemerahan (*rosy*) yang menyanjung untuk swafoto.
  - **Kontras & Sorotan (*Highlight*):** Kontras bawaan di kondisi mendung atau berkabut terasa agak ringan dan tidak menekan bayangan terlalu keras (tampak pada aspal dan pepohonan jauh di Incheon yang agak berkabut halus), sementara langit mendung terang memutih rata dengan transisi halus.
  - **Ketajaman, Aberasi & Kelembutan:** Bahkan pada ISO dasar 80, peredaman *noise* sensor CCD 12,2 MP mungil sudah sedikit meratakan detail mikro yang rapat seperti dedaunan hutan jauh di Laos maupun kerikil rel di Dangé, menghasilkan kesan lembut yang tidak menusuk; pada sudut lebar 27mm tepi bingkai sedikit melunak.
  - **Noise & Cahaya Redup:** Sangat licin di ISO 80; sesuai laporan Imaging Resource, Reviewed, dan komunitas DPReview, naik ke ISO menengah-tinggi cepat memicu perataan tekstur agresif bergaya sapuan cat air.
  - **Vignette & Flash:** Sedikit gelap sudut tipis pada langit lapang 27mm (tampak lembut di sudut atas foto awan Laos); flash internal langsung mendukung swafoto layar depan dengan kulit cerah merata saat *Beauty Shot* aktif.
- **lookTags:** `vivid-blue-red-pop`, `soft-nr-smoothing`, `airy-pastel-overcast`, `airbrushed-beauty-mode`, `nostalgic-dual-screen`
- **Arketipe:** `ccd_dreamy_pastel` *(dikonfirmasi)*
- **confidenceLook:** **`high`** — Didukung inspeksi visual 3 foto EXIF murni SOOC dari 3 fotografer & 2 platform (Direct & Panoramio) ditambah ulasan Imaging Resource, Reviewed, dan forum DPReview (catatan: perilaku *Beauty Shot* pada wajah diverifikasi dari ulasan karena foto wajah dikecualikan demi privasi).
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Samsung CA Support — ST550](https://www.samsung.com/ca/support/model/EC-ST550ZBPGCA/)
  - *Situs Fotografi / Ulasan:* [DPReview — Samsung ST550 Specs](https://www.dpreview.com/products/samsung/compacts/samsung_st550/), [Imaging Resource — Samsung TL225 Review](https://www.imaging-resource.com/cameras/samsung-tl225-review/), [Reviewed — Samsung TL225 Review](https://www.reviewed.com/cameras/content/samsung-tl225-digital-camera-review-21286-2)
  - *Flickr Camera Finder:* [Flickr — Samsung ST550](https://www.flickr.com/cameras/samsung/st550)
  - *Forum / Komunitas:* [DPReview Forum — Samsung ST550](https://www.dpreview.com/forums/threads/samsung-st550.2648332/)
  - *Foto Sampel Diperiksa Visual (3, EXIF SOOC SAMSUNG ST550 / TL225):*
    1. [Wikimedia Commons (Direct, CC BY-SA 4.0) — *Incheon Bus 30* (EXIF: ST550/TL225, ISO 80, SOOC 910291)](https://commons.wikimedia.org/wiki/File:30%EB%B2%88_SAM_3121.JPG)
    2. [Wikimedia Commons (Direct, CC BY-SA 3.0) — *Abri de quai gare de Dangé* (EXIF: ST550/TL225, ISO 80, SOOC 910291)](https://commons.wikimedia.org/wiki/File:Abri_de_quai_%C3%A0_la_gare_de_Dang%C3%A9.jpg)
    3. [Wikimedia Commons (Panoramio, CC BY 3.0) — *Pa ngoy in Phoukaokuay NBCA* (EXIF: ST550/TL225, ISO 80, SOOC 909301)](https://commons.wikimedia.org/wiki/File:Pa_ngoy_in_Phoukaokuay_NBCA_-_panoramio.jpg)

---

## 36. Pentax — Optio S

- **id:** `pentax_optio_s`
- **brand:** `Pentax`
- **model:** `Optio S` *(nama kanonik)*
- **aliases:** `PENTAX Optio S`
- **displayName:** `Pentax Optio S`
- **Tahun & Jenis:** 2003 · Digital ultrakompak miniatur (CCD)
- **Spesifikasi Inti (Resmi):** Sensor CCD 1/2,5" 3,2 MP efektif; lensa geser (*sliding lens*) zoom optik 3x ekuiv. 35–105mm f/2.6–4.3; ISO 50–200; rana 4–1/2000 dtk; bodi aluminium super-mungil; JPEG.
- **Ketergantungan Mode Warna (Digital):** Estetika digicam awal 2000-an yang hangat dan melamun tampil langsung pada **setelan JPEG bawaan standar (SOOC firmware `Optio S V1.00/V1.01`)**. Meski mungil, kamera memberi menu penyetelan kontras, ketajaman, saturasi, dan filter warna optik digital (B&W, Sepia, filter warna merah/hijau/biru), tetapi ketiga sampel SOOC menunjukkan karakter bawaan tanpa filter.
- **Deskripsi Tampilan Hasil Foto (Inspeksi Visual + Sumber):**
  - **Warna & White Balance:** Ketiga sampel murni SOOC (kapel kecil di perbukitan mendung, gedung kayu setengah-kayu Gießen di bawah langit biru, serta panorama atap genteng merah Ravensburg) memperlihatkan warna nada tengah yang hangat, kaya, dan nostalgik: genteng terakota dan batu pasir tampil hangat, langit biru cerah muncul dalam dan pekat, sedangkan rumput musim dingin mendung bernuansa zaitun kalem yang organik.
  - **Kontras & Sorotan (*Highlight*):** Rentang dinamis sensor 3,2 MP tahun 2003 terbatas sehingga langit mendung dan dinding plester putih terpapar matahari cepat penuh ke putih terang, namun keseimbangan nada tengah tetap menjaga detail bayangan pintu kayu dan ranting pohon tanpa hancur total.
  - **Ketajaman, Aberasi & Kelembutan:** Lensa *sliding* miniatur menghasilkan pusat bingkai yang mengejutkan rapi namun memperlihatkan kelembutan yang cukup kentara di sudut-sudut tepi bingkai (terlihat pada rambu jalan kiri di Gießen dan pinggir atap Ravensburg) disertai *purple fringing* tipis di ranting tipis melawan langit mendung.
  - **Noise & Cahaya Redup:** Karena ISO maksimal hanya 200, di cahaya siang tekstur masih bersih dengan butiran halus sangat tipis di bayangan; sesuai laporan DPReview, Imaging Resource, dan Panorama Circle, pada ISO 200 di area redup butiran halus di bayangan muncul secara jujur tanpa penghalusan plastik.
  - **Vignette & Flash:** Pada foto langit biru cerah Gießen terlihat sedikit **vignette alami yang khas** di sudut atas bingkai, memperkuat nuansa lo-fi retro awal 2000-an; kilat mini depan memberi pencahayaan langsung jarak dekat.
- **lookTags:** `dreamy-early-ccd`, `soft-wide-edges`, `warm-midtone-color`, `subtle-sky-vignette`, `mild-purple-fringing`
- **Arketipe:** `ccd_dreamy_pastel` *(dikonfirmasi)*
- **confidenceLook:** **`high`** — Didukung inspeksi visual 3 foto EXIF murni SOOC (`Optio S V1.00/V1.01`) dari 2 fotografer independen (mendung, langit biru cerah, panorama kota) ditambah ulasan DPReview, Imaging Resource, dan Panorama Circle.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Ricoh Imaging / Pentax — Optio S Manual (PDF)](https://www.ricoh-imaging.co.jp/english/support/man-pdf/optio-s.pdf)
  - *Situs Fotografi / Ulasan:* [DPReview — Pentax Optio S Review](https://www.dpreview.com/reviews/5027066227/pentaxoptios/), [Imaging Resource — Pentax Optio S Review](https://old.imaging-resource.com/PRODS/OPTS/OSA.HTM), [Panorama Circle — Pentax Optio S 15-Year Review](https://panoramacircle.com/2018/08/10/pentax-optio-s-15-year-old-camera-review/)
  - *Flickr Camera Finder:* [Flickr — Pentax Optio S](https://www.flickr.com/cameras/pentax/optio_s)
  - *Foto Sampel Diperiksa Visual (3, EXIF SOOC PENTAX Optio S):*
    1. [Wikimedia Commons (Direct, CC BY-SA 3.0) — *14 nothelfer kapelle ursprung* (EXIF: Optio S, V1.00, mendung)](https://commons.wikimedia.org/wiki/File:14_nothelfer_kapelle_ursprung.jpg)
    2. [Wikimedia Commons (Direct, Public Domain) — *Gießen Neues Schloss* (EXIF: Optio S, V1.01, langit biru cerah)](https://commons.wikimedia.org/wiki/File:2003-10_Gie%C3%9Fen_Neues_Schloss.jpg)
    3. [Wikimedia Commons (Direct, Public Domain) — *Ravensburg rooftops* (EXIF: Optio S, V1.01, panorama siang)](https://commons.wikimedia.org/wiki/File:2004-04-Ravensburg-Marktstra%C3%9Fe-Kirchstra%C3%9Fe-von-oben.jpg)

---

## 37. Pentax — Optio W60

- **id:** `pentax_optio_w60`
- **brand:** `Pentax`
- **model:** `Optio W60` *(nama kanonik)*
- **aliases:** `PENTAX Optio W60`, `Optio W60`
- **displayName:** `Pentax Optio W60`
- **Tahun & Jenis:** 2008 · Digital kompak tahan air (CCD)
- **Spesifikasi Inti (Resmi):** Sensor CCD 1/2,3" 10,0 MP efektif; lensa internal periskop zoom optik 5x sudut lebar ekuiv. 28–140mm f/3.5–5.5 dengan makro 1 cm; stabilisasi digital *Digital SR*; ISO 50–6400; tahan air 4m; JPEG.
- **Ketergantungan Mode Warna (Digital):** Karakter warna luar ruang yang segar dan jernih berasal langsung dari **JPEG bawaan standar (SOOC firmware `Ver 1.00`)**. Kamera menyediakan mode adegan bawah air (*Underwater*) serta berbagai *Digital Filters* (B&W, Sepia, pergeseran warna, ekstraksi warna, *Soft*) yang utamanya diterapkan saat *playback*, sementara ketiga sampel yang diperiksa berasal dari pengambilan warna normal.
- **Deskripsi Tampilan Hasil Foto (Inspeksi Visual + Sumber):**
  - **Warna & White Balance:** Pada sampel kota tua Tbilisi (ISO 50 SOOC) dan makro bunga bakung/daffodil (ISO 200), warna tampil cerah, bersih, dan segar tanpa terasa buatan — terakota kubah pemandian, biru pucat fasad masjid, hijau bukit, serta kelopak putih dan koral-aprikot bunga tergambar menyenangkan dan seimbang.
  - **Kontras & Sorotan (*Highlight*):** Kontras di siang hari terasa mantap dan renyah; langit mendung Tbilisi cepat memutih polos (*clipped*), sementara pada sampel makro saringan air dengan kilat langsung (ISO 400 SOOC, `Flash=9`), tutup kuning dekat kamera langsung meluap menjadi kuning neon bercahaya terang akibat kilat jarak dekat.
  - **Ketajaman, Aberasi & Kelembutan:** Kemampuan makro 1 cm tampak sangat tajam di titik fokus (kisi jala baja tahan karat dan benang sari bunga terlihat jernih), namun pada pemandangan lebar 28mm optik periskop internalnya menunjukkan kelembutan sudut ringan serta pinggiran ungu halus pada siluet pohon di puncak bukit melawan langit putih.
  - **Noise & Cahaya Redup:** Sangat bersih di ISO 50 dan masih rapi di ISO 200; pada ISO 400 (sampel saringan air) butiran *noise* sudah terlihat di latar gelap pipa, dan sebagaimana dicatat Camera Labs serta Reviewed, Auto-ISO cenderung cepat menaikkan sensitivitas sehingga cahaya redup mudah berbutir dan melunakkan detail halus.
  - **Vignette & Flash:** Vignette optik sangat tipis; flash langsung bawaan cukup kuat untuk makro/jarak dekat sehingga mudah menghasilkan pantulan kilap tajam pada air dan logam.
- **lookTags:** `lively-outdoor-ccd`, `crisp-1cm-macro`, `soft-periscope-corners`, `intense-closeup-flash`, `noisy-low-light-ccd`
- **Arketipe:** `ccd_compact_2000s` *(dikonfirmasi)*
- **confidenceLook:** **`high`** — Didukung inspeksi visual 3 foto EXIF terverifikasi `PENTAX Optio W60` lintas 3 fotografer & 2 platform (Flickr & Direct; ISO 50 lanskap kota, ISO 200 makro bunga, ISO 400 makro + flash) serta konsisten dengan Camera Labs dan Reviewed.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Ricoh Imaging — Optio W60 Specifications](https://www.ricoh-imaging.co.jp/english/products/digital/optio-w60/spec.html), [Ricoh Imaging News — Optio W60](https://www.ricoh-imaging.co.jp/english/news/2008/200813.html)
  - *Situs Fotografi / Ulasan:* [DPReview — Pentax Optio W60 Specs](https://www.dpreview.com/products/pentax/compacts/pentax_optiow60/), [Camera Labs — Pentax Optio W60 Review](https://www.cameralabs.com/pentax_optio_w60/2/), [Reviewed — Pentax Optio W60 Review](https://www.reviewed.com/cameras/content/pentax-optio-w60-digital-camera-review-19041)
  - *Flickr Camera Finder:* [Flickr — Pentax Optio W60](https://www.flickr.com/cameras/pentax/optio_w60)
  - *Foto Sampel Diperiksa Visual (3, EXIF PENTAX Optio W60):*
    1. [Wikimedia Commons (Flickr, CC BY 2.0) — *HUBER Turny greywater screen* (EXIF: Optio W60, ISO 400, SOOC Ver 1.00, makro + flash)](https://commons.wikimedia.org/wiki/File:%22HUBER_Turny%22_preceding_screen_for_greywater_(5729651009).jpg)
    2. [Wikimedia Commons (Flickr, CC BY 2.0) — *Abanotubani and Tbilisi Mosque* (EXIF: Optio W60, ISO 50, SOOC Ver 1.00, kota)](https://commons.wikimedia.org/wiki/File:Abanotubani_and_Tbilisi_Mosque.jpg)
    3. [Wikimedia Commons (Direct, CC BY-SA 4.0) — *Accent 2 (daffodil)* (EXIF: Optio W60, ISO 200, makro bunga)](https://commons.wikimedia.org/wiki/File:Accent_2.jpg)

---

## 38. Olympus — XZ-1

- **id:** `olympus_xz_1`
- **brand:** `Olympus`
- **model:** `XZ-1` *(nama kanonik)*
- **aliases:** `Olympus Stylus XZ-1`
- **displayName:** `Olympus XZ-1`
- **Tahun & Jenis:** 2011 · Digital kompak antusias (CCD)
- **Spesifikasi Inti (Resmi):** Sensor CCD sensitivitas tinggi 1/1,63" 10,0 MP efektif; prosesor TruePic V; lensa i.Zuiko Digital 4x ekuiv. 28–112mm f/1.8–2.5; stabilisasi *CCD-shift IS*; filter ND internal; ISO 100–6400; RAW 12-bit & JPEG.
- **Ketergantungan Mode Warna (Digital):** Karakter warna hangat-kaya bak kartu pos, mikro-kontras tiga dimensi, dan transisi sorotan luwes tampil langsung pada ***Picture Mode* bawaan (*Natural* / *Vivid* default JPEG, firmware `Version 1.1`)**. Kamera juga dilengkapi tombol putar ***Art Filters*** (*Pop Art*, *Soft Focus*, *Grainy Film*, *Pin Hole*, *Diorama*, *Dramatic Tone*) untuk gaya ekspresif instan, namun seluruh 3 sampel yang diamati menggunakan rendering standar tanpa *Art Filter*.
- **Deskripsi Tampilan Hasil Foto (Inspeksi Visual + Sumber):**
  - **Warna & White Balance:** Ketiga sampel (terminal bus malam Shinagawa ISO 200 f/1.8 SOOC, plakat batu Jena ISO 100 f/2.0 SOOC, dan telepon putar hitam Bakelite ISO 100 f/2.5) menunjukkan keunggulan warna Olympus TruePic V: biru langit malam pekat seperti beludru, papan neon merah-jingga berpendar kaya tanpa pecah, batu travertin hangat alami, dan kertas krem netral-hangat yang harmonis.
  - **Kontras & Sorotan (*Highlight*):** Sangat filmis dan matang. Pada foto telepon Bakelite hitam mengilap, pantulan cahaya jendela melengkung lembut menuju hitam pekat tanpa transisi kasar (*graceful highlight roll-off*); di foto malam Shinagawa, lampu jalan jingga membentuk halo lembut sinematik sementara bodi putih bus tetap terjaga detail dan teksturnya.
  - **Ketajaman, Aberasi & Kelembutan:** Lensa cepat i.Zuiko f/1.8–2.5 menghadirkan ketajaman taktil tiga dimensi pada bidang fokus (pori batu dan huruf dial telepon sangat tajam), dipadukan pemisahan latar lembut (*creamy bokeh*) pada kabel telepon dan lampu jalan jauh. Hampir tidak terlihat aberasi warna yang mengganggu.
  - **Noise & Cahaya Redup:** Berkat bukaan f/1.8 dan sensor CCD besar 1/1,63", bidikan malam genggam cukup bertahan di ISO 200 dengan bayangan amat bersih dan gradasi mulus; sesuai ulasan DPReview, Imaging Resource, dan Digital Photography School, pada ISO 800–1600 pola *noise* tetap terasa halus mirip butiran film meski peredaman *noise* bawaan sedikit melembutkan detail kecil.
  - **Vignette & Flash:** Vignette optik sangat halus dan alami pada bukaan f/1.8; *pop-up flash* internal dan *hot shoe* mendukung kontrol kilat yang rapi untuk potret malam.
- **lookTags:** `filmic-olympus-ccd`, `warm-rich-jpegs`, `bright-f18-bokeh`, `graceful-highlight-curve`, `cinematic-night-glow`
- **Arketipe:** `pro_ccd_filmic` *(dikonfirmasi)*
- **confidenceLook:** **`high`** — Didukung inspeksi visual 3 foto EXIF terverifikasi `XZ-1` dari 3 fotografer independen (malam f/1.8 SOOC, arsitektur batu f/2.0 SOOC, *still life* mengilap f/2.5) serta selaras dengan DPReview, Imaging Resource, dan Digital Photography School.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Resmi Produsen:* [Olympus Official Manual — XZ-1 (PDF)](https://learnandsupport.getolympus.com/sites/default/files/media/files/2018/04/XZ-1_Instruction_Manual_EN.pdf)
  - *Situs Fotografi / Ulasan:* [DPReview — Olympus XZ-1 Review](https://www.dpreview.com/reviews/1683963493/olympusxz1/), [Imaging Resource — Olympus XZ-1 Review](https://www.imaging-resource.com/cameras/olympus-xz-1-review/), [Digital Photography School — Olympus XZ-1 Review](https://digital-photography-school.com/olympus-xz-1-review/)
  - *Flickr Camera Finder:* [Flickr — Olympus XZ-1](https://www.flickr.com/cameras/olympus/xz-1)
  - *Foto Sampel Diperiksa Visual (3, EXIF XZ-1):*
    1. [Wikimedia Commons (Direct, CC BY 3.0) — *Keikyu Shinagawa bus terminal* (EXIF: XZ-1, ISO 200, f/1.8, SOOC Version 1.1 malam)](https://commons.wikimedia.org/wiki/File:110107Keikyu_Shinagawa_bus_terminal.JPG)
    2. [Wikimedia Commons (Direct, CC BY-SA 3.0) — *17Juni Jena Holzmarkt* (EXIF: XZ-1, ISO 100, f/2.0, SOOC Version 1.1)](https://commons.wikimedia.org/wiki/File:17Juni_Jena_Holzmarkt.JPG)
    3. [Wikimedia Commons (Direct, CC BY-SA 3.0) — *1960s GEC Black Rotary Telephone* (EXIF: XZ-1, ISO 100, f/2.5, still life)](https://commons.wikimedia.org/wiki/File:1960s_GEC_New_Gecophone_706_Black_Rotary_Dial_Telephone.JPG)

---

## 39. Olympus — Camedia C-5050 Zoom

- **id:** `olympus_camedia_c_5050_zoom`
- **brand:** `Olympus`
- **model:** `Camedia C-5050 Zoom` *(nama kanonik)*
- **aliases:** `Olympus C-5050Z`, `C5050Z`, `C-5050 Zoom`
- **displayName:** `Olympus Camedia C-5050 Zoom`
- **Tahun & Jenis:** 2002 · Digital kompak prosumer (CCD)
- **Spesifikasi Inti:** Sensor CCD 1/1,8" 5,0 MP efektif; prosesor TruePic; lensa zoom 3x ekuiv. 35–105mm f/1.8–2.6; bodi paduan magnesium; ISO 64–400; rana 16–1/1000 dtk; mode P/A/S/M; RAW (.ORF), TIFF & JPEG; daya 4x AA. *(Halaman spesifikasi resmi produsen aktif tidak ditemukan.)*
- **Ketergantungan Mode Warna (Digital):** Kedalaman warna pekat, kontras hitam dalam, dan ketajaman tegas merupakan ciri langsung **JPEG bawaan standar (SOOC firmware `v558-77/78`)**. Kamera menyediakan pengaturan bertingkat untuk kontras, ketajaman, dan saturasi serta kemampuan memproses ulang RAW di dalam kamera; banyak pengguna menurunkan ketajaman bawaan agar transisi tepi lebih halus, tetapi sampel SOOC memperlihatkan karakter default Olympus yang berenergi.
- **Deskripsi Tampilan Hasil Foto (Inspeksi Visual + Sumber):**
  - **Warna & White Balance:** Ketiga sampel (jalan berpohon Dortmund ISO 64, makro bunga ungu liar *Hepatica* ISO 64 SOOC, dan gereja bata Berlin ISO 100 SOOC) memperlihatkan sains warna Olympus CCD awal 2000-an yang legendaris: langit biru serulean dalam dan jenuh, hijau daun zaitun-zamrud sangat pekat, bata hangat, dan kelopak bunga ungu-biru kerajaan yang kaya serta berdimensi.
  - **Kontras & Sorotan (*Highlight*):** Kontras bawaan kuat dengan hitam bayangan yang dalam dan tegas (tampak pada latar gelap daun kering di balik bunga ungu serta bayangan rapat di bawah pohon cemara gereja). Pada paparan sinar matahari langsung, tepi kelopak bunga terang atau langit mendung cepat memutih berkilau (*clipped*).
  - **Ketajaman, Aberasi & Kelembutan:** Penajaman JPEG default terasa renyah dan tegas pada bata, atap sabak, dan kerikil rel trem; bukaan lensa cepat f/1.8–2.6 memudahkan pemisahan latar yang mulus dan dalam pada pemotretan jarak dekat/makro. Sedikit pinggiran ungu tipis kadang muncul di tepi daun terhadap langit sangat cerah.
  - **Noise & Cahaya Redup:** Pada ISO 64–100 tekstur tetap amat solid, namun pada langit biru polos gereja di ISO 100 sudah tampak butiran *noise* luminansi sangat halus khas CCD 2002; sesuai arsip uji Wrotniak/Mike Eckman dan DPReview, pada ISO 200–400 butiran di langit dan bayangan meningkat jelas menyerupai *grain* film tanpa penghalusan cat air.
  - **Vignette & Flash:** Vignette optik sangat ringan; *pop-up flash* langsung plus *hot shoe* memberi opsi kilat kuat untuk pemotretan malam.
- **lookTags:** `rich-early-olympus-ccd`, `punchy-default-sharpness`, `deep-shadow-contrast`, `fast-f18-separation`, `fine-sky-grain`
- **Arketipe:** `ccd_compact_2000s` *(dikonfirmasi)*
- **confidenceLook:** **`high`** — Didukung inspeksi visual 3 foto EXIF terverifikasi `C5050Z` dari 3 fotografer independen (ISO 64 jalanan, ISO 64 makro SOOC, ISO 100 langit cerah SOOC) serta selaras dengan DPReview, Shutterbug, Arsip Wrotniak/Mike Eckman, dan Olympus Passion.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Situs Fotografi / Ulasan:* [DPReview — Olympus C-5050 Zoom Review](https://www.dpreview.com/reviews/8418153953/olympusc5050z/), [Imaging Resource — Olympus C-5050 Zoom](https://www.imaging-resource.com/news/olympus-europe-announces-c-5050zoom/), [Shutterbug — Olympus Camedia C-5050 Zoom](https://www.shutterbug.com/content/olympus-camedia-c5050-zoom), [Mike Eckman / Wrotniak Archive — C-5050 Noise](https://mikeeckman.com/wrotniak/photo/c/c5050-noise.html), [Olympus Passion — Olympus C-5050](https://www.olympuspassion.com/2025/10/27/premium-olympus-throwback-c-5050-the-compact-premium-from-a-past-era-video/)
  - *Flickr Camera Finder:* [Flickr — Olympus Camedia C-5050 Zoom](https://www.flickr.com/cameras/olympus/c5050z)
  - *Foto Sampel Diperiksa Visual (3, EXIF C5050Z):*
    1. [Wikimedia Commons (Direct, CC BY-SA 3.0) — *Dortmund Gartenstadt* (EXIF: C5050Z, ISO 64, mendung hijau)](https://commons.wikimedia.org/wiki/File:060813_DO_Gartenstadt_010.JPG)
    2. [Wikimedia Commons (Direct, CC BY-SA 3.0) — *Hepatica wildflower macro* (EXIF: C5050Z, ISO 64, SOOC v558-77)](https://commons.wikimedia.org/wiki/File:071-v-p4181822.jpg)
    3. [Wikimedia Commons (Direct, CC BY 3.0) — *Christuskirche Dahlem* (EXIF: C5050Z, ISO 100, SOOC v558-78 cerah)](https://commons.wikimedia.org/wiki/File:08-07-01_Christuskirche_Dahlem_01.JPG)

---

## 40. Fujifilm — FinePix Z5fd

- **id:** `fujifilm_finepix_z5fd`
- **brand:** `Fujifilm`
- **model:** `FinePix Z5fd` *(nama kanonik)*
- **aliases:** `FinePix Z5fd`, `Fuji Z5fd`
- **displayName:** `Fujifilm FinePix Z5fd`
- **Tahun & Jenis:** 2007 · Digital ultrakompak saku (Super CCD HR)
- **Spesifikasi Inti:** Sensor Super CCD HR 1/2,5" 6,3 MP efektif; prosesor Real Photo Processor II; lensa internal periskop Fujinon zoom 3x ekuiv. 36–108mm f/3.5–4.2; *Face Detection* berbasis cip; ISO 100–1600; JPEG. *(Halaman spesifikasi resmi produsen aktif tidak ditemukan.)*
- **Ketergantungan Mode Warna (Digital):** Reproduksi warna hangat-menyenangkan dan penghalusan cahaya redup sudah muncul langsung pada **mode standar bawaan (*F-Standard* SOOC JPEG, firmware `Ver1.01`)**. Melalui tombol menu *F (FinePix Color)*, pengguna juga dapat memilih ***F-Chrome*** untuk saturasi dan kontras lebih hidup ala film slide maupun *B&W*, tetapi ketiga sampel yang diamati merepresentasikan keluaran warna standar yang akrab.
- **Deskripsi Tampilan Hasil Foto (Inspeksi Visual + Sumber):**
  - **Warna & White Balance:** Pada sampel *close-up* bagel salmon asap (ISO 100), sains warna Fujifilm Super CCD langsung bersinar: jingga-koral salmon tampil lezat dan hangat, keju krim putih bersih, serta rona jari tangan alami dan menyanjung. Pada sampel menara jam Turki di langit mendung (ISO 100 SOOC), keseimbangan putih menghasilkan nuansa sejuk-pastel yang rapi dengan aksen merah tipis yang manis; sementara pada foto malam banjir sungai Pittsburgh (ISO 1600 SOOC), pantulan lampu jalan menjadi keemasan hangat yang dramatis.
  - **Kontras & Sorotan (*Highlight*):** Kontras di ISO rendah terasa seimbang dan lembut tanpa menekan bayangan secara kasar. Di malam hari ISO 1600, lampu jembatan memutih terang disertai pendaran (*halation*) berpinggir biru-ungu lembut yang memperkuat nuansa nostalgia malam 2007.
  - **Ketajaman, Aberasi & Kelembutan:** Pada jarak dekat ISO 100, biji poppy bagel tajam dengan latar piring yang memudar halus; namun pada pemandangan arsitektur jauh (menara jam), lensa periskop internal tipis bersama pemrosesan Real Photo membuat dedaunan pohon dan detail kecil jauh tampak agak lembut dan licin.
  - **Noise & Cahaya Redup:** Sampel malam ISO 1600 SOOC membuktikan persis karakter yang dilaporkan Imaging Resource, CNET, dan PCMag UK: kamera mampu menangkap adegan malam tanpa kilat dengan warna lampu yang tetap hidup, tetapi peredaman *noise* agresif disertai keterbatasan kecepatan rana membuat tekstur gelap dan pantulan air menjadi sangat lembut, sedikit buram, dan berbutir halus.
  - **Vignette & Flash:** Vignette optik hampir tak terasa di siang hari; flash internal *Intelligent Flash* dirancang menyeimbangkan subjek dekat (terutama wajah) agar tidak terlalu putih terbakar dibanding kompak murah sezamannya.
- **lookTags:** `warm-fuji-superccd`, `flattering-natural-skin`, `soft-periscope-detail`, `dreamy-night-halation`, `soft-lowlight-smoothing`
- **Arketipe:** `ccd_dreamy_pastel` *(dikonfirmasi)*
- **confidenceLook:** **`high`** — Didukung inspeksi visual 3 foto EXIF terverifikasi `FinePix Z5fd` lintas 3 fotografer & 2 platform (Flickr & Direct; ISO 100 makanan/warna hangat, ISO 100 arsitektur mendung SOOC, ISO 1600 malam SOOC) serta selaras dengan Imaging Resource, PCMag UK, CNET, dan Reviewed.
- **Sumber & Foto Sampel yang Diperiksa:**
  - *Situs Fotografi / Ulasan:* [DPReview — Fujifilm FinePix Z5fd](https://www.dpreview.com/articles/5816595002/finepixz5fd/), [Imaging Resource — Fujifilm Z5fd Test](https://www.imaging-resource.com/cameras/fujifilm-z5fd-review/test/), [PCMag UK — Fuji FinePix Z5fd](https://uk.pcmag.com/cameras/57982/fuji-finepix-z5fd), [CNET — Fujifilm FinePix Z5fd Review](https://www.cnet.com/reviews/fujifilm-finepix-z5fd-review/), [Reviewed — Fujifilm FinePix Z5fd](https://www.reviewed.com/cameras/content/fujifilm-finepix-z5fd-first-impressions-review)
  - *Flickr Camera Finder:* [Flickr — Fujifilm FinePix Z5fd](https://www.flickr.com/cameras/fujifilm/finepix_z5fd)
  - *Foto Sampel Diperiksa Visual (3, EXIF FinePix Z5fd):*
    1. [Wikimedia Commons (Flickr, CC BY-SA 2.0) — *\*this\* is a bagel* (EXIF: FinePix Z5fd, ISO 100, close-up warna hangat)](https://commons.wikimedia.org/wiki/File:*this*_is_a_bagel.jpg)
    2. [Wikimedia Commons (Flickr, CC BY 2.0) — *10th Street Bypass flood* (EXIF: FinePix Z5fd, ISO 1600, SOOC Ver1.01 malam)](https://commons.wikimedia.org/wiki/File:10th_Street_Bypass_flood_(2112138746).jpg)
    3. [Wikimedia Commons (Direct, CC BY-SA 3.0) — *19 Mayıs Saat Kulesi* (EXIF: FinePix Z5fd, ISO 100, SOOC Ver1.01 mendung)](https://commons.wikimedia.org/wiki/File:19_May%C4%B1s_Saat_Kulesi.jpg)

---

## Ringkasan Laporan Batch 02

### 1. Jumlah Kamera per `confidenceLook`

| Tingkat `confidenceLook` | Jumlah | Daftar Kamera |
|---|---:|---|
| **`high`** | **20** | `canon_powershot_g9`, `canon_powershot_s95`, `canon_ixy_digital_900_is`, `canon_powershot_a590_is`, `casio_exilim_ex_z75`, `casio_exilim_ex_s10`, `nikon_coolpix_s6900`, `nikon_coolpix_p7000`, `nikon_coolpix_990`, `panasonic_lumix_dmc_lx3`, `panasonic_lumix_dmc_fx01`, `kodak_easyshare_v570`, `kodak_easyshare_dx6490`, `samsung_nv10`, `samsung_st550`, `pentax_optio_s`, `pentax_optio_w60`, `olympus_xz_1`, `olympus_camedia_c_5050_zoom`, `fujifilm_finepix_z5fd` |
| **`medium`** | **0** | — |
| **`low`** | **0** | — |
| **`tidak ditemukan`** | **0** | *(Tidak ada kamera tanpa sumber sama sekali)* |
| **Total** | **20** | **60 foto contoh CC/PD diperiksa visual langsung** (tepat 3 foto EXIF terverifikasi per kamera, bebas wajah & bebas anak, tercatat di `docs/references/manifest_batch_02.json`) |

*(Seluruh 20 kamera mempertahankan `confidenceLook = high` seperti pada `docs/catalog/batch_02.md`, kini diperkuat inspeksi visual langsung terhadap 60 foto sampel nyata terverifikasi EXIF.)*

### 2. Kamera dengan Catatan Khusus / Perlu Perhatian Saat Kalibrasi

1. **`nikon_coolpix_s6900` & `samsung_st550` (Mode Kecantikan / *Glamour* vs Privasi Sampel):** Ketiga foto sampel visual yang disimpan pada kedua kamera ini merupakan bidikan luar ruang bebas wajah dalam mode warna standar SOOC demi mematuhi aturan privasi ketat (tanpa wajah manusia yang dapat dikenali). Oleh karena itu, sifat khusus penghalusan kulit (*Glamour Mode* pada S6900 dan *Beauty Shot* pada ST550) didokumentasikan dari konsensus ulasan fotografi independen, bukan dari potret wajah. Selain itu, perlu dicatat bahwa secara perangkat keras `nikon_coolpix_s6900` bersensor **BSI CMOS** (2014), namun tetap cocok dipetakan ke `ccd_dreamy_pastel` karena estetika sorotan susu dan kelembutan kulitnya.
2. **`nikon_coolpix_p7000` (Variasi Perangkat Lunak Sampel):** Dari 3 foto EXIF asli `COOLPIX P7000` (setelah menyaring dan menghapus foto ponsel *Elephone P7000* yang keliru masuk kategori), 1 foto murni SOOC (`COOLPIX P7000V1.1`), 1 foto melewati perangkat lunak resmi Nikon (`ViewNX 2.3 W`), dan 1 foto bawah air dengan kilat melewati `Adobe Photoshop CS4`. Karakter utama warna dan ketajaman tetap konsisten dengan uji lab independen.
3. **`samsung_nv10` & `pentax_optio_s` (Keberagaman Pengunggah):** Pada masing-masing kamera ini, 2 dari 3 foto sampel CC yang lolos penyaringan privasi berasal dari fotografer yang sama (ditambah 1 fotografer independen lain pada kondisi cahaya berbeda). Keduanya tetap `high` karena telah dikroscek dengan 3–4 situs ulasan fotografi independen.

### 3. Hal yang Tidak Ditemukan

- **Flickr Camera Finder 404/500:** Saat dicek langsung, 8 URL Flickr Camera Finder mengembalikan galat HTTP 500 atau 404 sehingga tidak dicantumkan sebagai tautan aktif: `Canon PowerShot G9`, `Canon PowerShot SD800 IS`, `Canon PowerShot A590 IS`, `Casio Exilim EX-Z75`, `Nikon Coolpix S6900`, `Kodak EasyShare V570`, `Kodak EasyShare DX6490`, dan `Samsung NV10` (12 kamera lainnya merespons HTTP 200 dan dicantumkan lengkap).
- **Halaman spesifikasi resmi produsen yang tidak ditemukan aktif (5 model):** `Panasonic Lumix DMC-FX01`, `Kodak EasyShare V570`, `Kodak EasyShare DX6490`, `Olympus Camedia C-5050 Zoom`, dan `Fujifilm FinePix Z5fd`. (Pada `Samsung NV10` dan `Samsung ST550 / TL225`, tautan resmi yang masih aktif berupa portal dukungan model Samsung.)
