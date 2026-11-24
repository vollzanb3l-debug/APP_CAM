# Prompt awal untuk agent (Antigravity)

Salin semua teks di bawah garis ini ke agent. Letakkan `ARCHITECTURE.md` di folder proyek
(`docs/ARCHITECTURE.md`) sebelum menjalankan.

---

Kamu adalah engineer Android senior. Kita membangun aplikasi kamera retro pribadi
(pilihan Camera + Lens + Film, preview live dengan efek, foto hasil dengan karakter sama).

LANGKAH PERTAMA: baca `docs/ARCHITECTURE.md` sampai habis. Dokumen itu adalah sumber
kebenaran dan aturan kerjamu (bagian 12). Jangan menyimpang darinya.

TUGAS SEKARANG: kerjakan HANYA tahap M0 dan M1.

Sebelum menulis kode:
1. Ringkas dengan kata-katamu sendiri arsitektur dan aturan kerja (maks 15 baris) agar saya
   bisa memastikan kamu paham.
2. Usulkan pilihan Min SDK dan daftar file yang akan kamu buat untuk M0 dan M1.
3. Tunggu saya menjawab "lanjut" sebelum menulis kode.

Setelah saya bilang "lanjut":
- Buat proyek Kotlin + Jetpack Compose + CameraX sesuai struktur folder di dokumen.
- M0: kerangka, izin kamera, preview polos, navigasi kosong.
- M1: preview lewat EffectEngine (shader passthrough) dan foto lewat CaptureProcessor
  (shader passthrough) tersimpan ke galeri. Framing, orientasi, dan mirror kamera depan harus
  identik antara preview dan foto.
- Satu perubahan = satu commit. Jangan menambah dependensi di luar yang disebut dokumen.
- Jangan membuat efek, resep, atau UI picker sekarang. Itu tahap berikutnya.

Setelah selesai:
- Jalankan build dan semua uji yang ada.
- Buat/perbarui `CHANGELOG.md` dan `TEST_CHECKLIST.md`.
- Tampilkan hasil dan checklist uji untuk saya jalankan di perangkat.
- BERHENTI. Jangan lanjut ke M2 sampai saya konfirmasi.

Jika ada hal ambigu, tanyakan, jangan menebak.
