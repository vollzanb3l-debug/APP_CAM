# TEST_CHECKLIST.md

Checklist pengujian manual yang dijalankan setiap kali satu tahap (`M0`–`M6`) selesai, sesuai `docs/ARCHITECTURE.md` bagian 12 dan 13.

## Daftar Uji Manual

- [ ] Preview tampil di kamera belakang dan depan
- [ ] Kamera depan tidak terbalik pada preview maupun foto
- [ ] Rotasi layar (portrait/landscape) aman, framing foto sama dengan preview
- [ ] Foto tersimpan di galeri, dapat dibuka aplikasi lain
- [ ] Efek pada preview dan foto cocok secara karakter (bandingkan berdampingan)
- [ ] Ganti Camera/Lens/Film berkali-kali cepat: tidak crash, tidak berkedip aneh
- [ ] Aplikasi ke background lalu kembali: preview pulih
- [ ] 5 menit preview terus-menerus: tidak terlalu panas, fps wajar
- [ ] Foto resolusi penuh tidak OOM
- [ ] File JSON rusak sengaja: aplikasi tetap jalan, item dilewati
