# Katalog Arketipe Kamera & Film (`assets/archetypes/`)

Folder ini berisi **15 arketipe kamera** (sesuai `docs/catalog/archetypes.md` dan `docs/catalog/json/catalog_index.json`) serta **3 arketipe film** (`warm_golden_film`, `soft_portrait_pastel`, `monochrome_bw`) dengan `schemaVersion: 2`.

- Seluruh nilai awal `cameraDefaults` pada 15 arketipe kamera diturunkan secara deterministik dari [`assets/look_tag_map.json`](../look_tag_map.json) berdasarkan `lookTags` kualitatif masing-masing arketipe.
- **Status wajib `draft`:** Seluruh angka di tahap ini adalah **perkiraan awal**, bukan klaim akurasi. Penyetelan akhir dilakukan secara visual terhadap foto referensi nyata melalui `CalibrationDebugScreen` sebelum menaikkan status menjadi `calibrated`.
- Setiap kamera di `assets/cameras/` mewarisi `cameraDefaults` dari arketipenya melalui `RecipeCompositor` dan cukup menuliskan *override* pada field yang berbeda saja.
