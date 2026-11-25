# TEST_CHECKLIST.md

Checklist pengujian manual yang dijalankan setiap kali satu tahap (`M0`–`M6`) selesai, sesuai `docs/ARCHITECTURE.md` bagian 12 dan 13.

## Daftar Uji Manual

- [ ] Preview tampil di kamera belakang dan depan *(uji di M0/M1)*
- [ ] Kamera depan tidak terbalik pada preview maupun foto *(uji di M1)*
- [ ] Rotasi layar (portrait/landscape) aman, framing foto sama dengan preview *(uji di M0/M1)*
- [ ] Foto tersimpan di galeri, dapat dibuka aplikasi lain *(uji di M1)*
- [ ] Efek pada preview dan foto cocok secara karakter (bandingkan berdampingan) *(mulai M2)*
- [ ] Ganti Camera/Lens/Film berkali-kali cepat: tidak crash, tidak berkedip aneh *(mulai M3)*
- [ ] Aplikasi ke background lalu kembali: preview pulih *(uji di M0/M1)*
- [ ] 5 menit preview terus-menerus: tidak terlalu panas, fps wajar *(uji di M1)*
- [ ] Foto resolusi penuh tidak OOM *(uji di M1)*
- [ ] File JSON rusak sengaja: aplikasi tetap jalan, item dilewati *(mulai M3)*
