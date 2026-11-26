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
- [ ] Layar Debug & Kalibrasi: tampilkan metadata `archetype`, `confidenceLook`, `confidenceSpecs`, `calibrationStatus`, dan `sources` untuk kamera & film aktif; pilih foto referensi dari galeri, geser slider parameter, bandingkan berdampingan, lalu terapkan/reset ke preview *(uji di M3)*
