# TEST_CHECKLIST.md

Checklist pengujian manual yang dijalankan setiap kali satu tahap (`M0`–`M6`) selesai, sesuai `docs/ARCHITECTURE.md` bagian 12 dan 13.

## Daftar Uji Manual

- [ ] Preview tampil di kamera belakang dan depan *(uji di M0–M3)*
- [ ] Kamera depan tidak terbalik pada preview maupun foto *(uji di M1–M3)*
- [ ] Rotasi layar (portrait/landscape) aman, framing foto sama dengan preview *(uji di M0–M3)*
- [ ] Foto tersimpan di galeri, dapat dibuka aplikasi lain *(uji di M1–M3)*
- [ ] Efek pada preview dan foto cocok secara karakter (bandingkan berdampingan — mis. film *Monokrom 400* B&W, *Surya 200* hangat keemasan, dan *Linen 400* lembut pastel) *(uji di M2/M3)*
- [ ] Ganti Camera/Lens/Film berkali-kali cepat lewat PickerSheet: tidak crash, tidak berkedip aneh *(uji di M3)*
- [ ] Aplikasi ke background lalu kembali: preview pulih; pilihan terakhir Camera/Lens/Film tetap tersimpan *(uji di M3)*
- [ ] 5 menit preview terus-menerus: tidak terlalu panas, fps wajar *(uji di M1–M3)*
- [ ] Foto resolusi penuh tidak OOM *(uji di M1–M3)*
- [ ] File JSON rusak sengaja: aplikasi tetap jalan, item dilewati, dan error tampil di Layar Debug & Kalibrasi *(uji di M3 — tercover juga oleh unit test `RecipeRepositoryTest`)*
- [ ] Layar Debug & Kalibrasi: tampilkan metadata `archetype`, `confidenceLook`, `confidenceSpecs`, `calibrationStatus`, dan `sources` untuk kamera & film aktif; pilih foto referensi dari galeri, geser slider parameter, bandingkan berdampingan, lalu terapkan/reset ke preview *(uji di M3)*
