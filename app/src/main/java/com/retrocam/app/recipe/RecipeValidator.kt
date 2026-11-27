package com.retrocam.app.recipe

/**
 * Pure-Kotlin validator for Camera, Lens, Film, and Archetype JSON specs.
 */
object RecipeValidator {

    const val CURRENT_CAMERA_SCHEMA_VERSION = CameraSchemaMigrator.CURRENT_SCHEMA_VERSION
    const val CURRENT_ARCHETYPE_SCHEMA_VERSION = 2

    val allowedIconTypes = setOf(
        "compact_digital",
        "compact_film",
        "slr_film",
        "half_frame",
        "disposable",
        "instant",
        "medium_format",
        "toy_lens",
        "camcorder",
        "camera_phone",
    )

    private val allowedConfidence = setOf("high", "medium", "low")
    private val allowedCalibrationStatus = setOf("draft", "calibrated")
    private val allowedAspectRatios = setOf("4:3", "3:4", "3:2", "2:3", "16:9", "1:1")

    fun validateCamera(spec: CameraSpec): List<String> = buildList {
        if (spec.schemaVersion != CURRENT_CAMERA_SCHEMA_VERSION) {
            add("camera.schemaVersion must be $CURRENT_CAMERA_SCHEMA_VERSION")
        }
        if (spec.id.isBlank()) add("camera.id must not be blank")
        if (spec.brand.isBlank()) add("camera.brand must not be blank")
        if (spec.model.isBlank()) add("camera.model must not be blank")
        if (spec.aliases.any { it.isBlank() }) {
            add("camera.aliases must not contain blank entries")
        }
        if (spec.lookTags.any { it.isBlank() }) {
            add("camera.lookTags must not contain blank entries")
        }
        if (spec.displayName.isBlank()) add("camera.displayName must not be blank")
        if (spec.iconType !in allowedIconTypes) {
            add("camera.iconType unsupported: ${spec.iconType}")
        }
        if (spec.era.isBlank()) add("camera.era must not be blank")
        if (spec.category.isBlank()) add("camera.category must not be blank")
        if (spec.archetype.isBlank()) add("camera.archetype must not be blank")
        if (spec.confidenceLook !in allowedConfidence) {
            add("camera.confidenceLook must be high/medium/low")
        }
        if (spec.confidenceSpecs !in allowedConfidence) {
            add("camera.confidenceSpecs must be high/medium/low")
        }
        if (spec.calibrationStatus !in allowedCalibrationStatus) {
            add("camera.calibrationStatus must be draft/calibrated")
        }
        if (spec.sources.isEmpty() || spec.sources.any { it.isBlank() }) {
            add("camera.sources must contain at least one non-blank entry")
        }
        spec.aspectRatio?.let { ratio ->
            if (ratio !in allowedAspectRatios) {
                add("camera.aspectRatio unsupported: $ratio")
            }
        }
        spec.whiteBalance?.let { wb ->
            if (wb.temp !in -1f..1f) add("camera.whiteBalance.temp out of -1..1")
            if (wb.tint !in -1f..1f) add("camera.whiteBalance.tint out of -1..1")
        }
        spec.tone?.let { tone ->
            if (tone.contrast !in 0.5f..2.0f) add("camera.tone.contrast out of 0.5..2.0")
            if (tone.highlightRolloff !in 0f..1f) add("camera.tone.highlightRolloff out of 0..1")
            if (tone.shadowLift !in 0f..0.4f) add("camera.tone.shadowLift out of 0..0.4")
        }
        spec.saturation?.let { sat ->
            if (sat !in 0f..2.5f) add("camera.saturation out of 0..2.5")
        }
        spec.sensorNoise?.let { noise ->
            if (noise.amount !in 0f..1f) add("camera.sensorNoise.amount out of 0..1")
            if (noise.chroma !in 0f..1f) add("camera.sensorNoise.chroma out of 0..1")
        }
        spec.sharpen?.let { sharpen ->
            if (sharpen !in 0f..1f) add("camera.sharpen out of 0..1")
        }
        spec.dateStamp?.let { ds ->
            if (!HexColorParser.isValidHex(ds.color)) {
                add("camera.dateStamp.color invalid hex: ${ds.color}")
            }
        }
        spec.bodyOptics?.let { optics ->
            addAll(validateBodyOptics("camera.bodyOptics", optics))
        }
    }

    private fun validateBodyOptics(prefix: String, optics: CameraBodyOpticsSpec): List<String> = buildList {
        if (optics.softness !in 0f..1f) add("$prefix.softness out of 0..1")
        if (optics.vignetteStrength !in 0f..1f) add("$prefix.vignetteStrength out of 0..1")
        if (optics.bloomIntensity !in 0f..1f) add("$prefix.bloomIntensity out of 0..1")
        if (optics.chromaticAberration !in 0f..1f) add("$prefix.chromaticAberration out of 0..1")
        if (optics.lightLeakIntensity !in 0f..1f) add("$prefix.lightLeakIntensity out of 0..1")
    }

    fun validateLens(spec: LensSpec): List<String> = buildList {
        if (spec.schemaVersion != 1) add("lens.schemaVersion must be 1")
        if (spec.id.isBlank()) add("lens.id must not be blank")
        if (spec.displayName.isBlank()) add("lens.displayName must not be blank")
        if (spec.focalLengthEquiv !in 8..400) add("lens.focalLengthEquiv out of 8..400")
        if (spec.softness !in 0f..1f) add("lens.softness out of 0..1")
        if (spec.bloom.intensity !in 0f..1f) add("lens.bloom.intensity out of 0..1")
        if (spec.bloom.threshold !in 0f..1f) add("lens.bloom.threshold out of 0..1")
        if (spec.bloom.radius !in 0f..0.1f) add("lens.bloom.radius out of 0..0.1")
        if (spec.vignette.strength !in 0f..1f) add("lens.vignette.strength out of 0..1")
        if (spec.vignette.radius !in 0.1f..1.5f) add("lens.vignette.radius out of 0.1..1.5")
        if (spec.distortion.k1 !in -0.5f..0.5f) add("lens.distortion.k1 out of -0.5..0.5")
        if (spec.chromaticAberration !in 0f..1f) add("lens.chromaticAberration out of 0..1")
        if (spec.flare !in 0f..1f) add("lens.flare out of 0..1")
    }

    fun validateFilm(spec: FilmSpec): List<String> = buildList {
        if (spec.schemaVersion != 2) add("film.schemaVersion must be 2")
        if (spec.id.isBlank()) add("film.id must not be blank")
        if (spec.displayName.isBlank()) add("film.displayName must not be blank")
        if (spec.archetype.isBlank()) add("film.archetype must not be blank")
        if (spec.confidenceLook !in allowedConfidence) {
            add("film.confidenceLook must be high/medium/low")
        }
        if (spec.confidenceSpecs !in allowedConfidence) {
            add("film.confidenceSpecs must be high/medium/low")
        }
        if (spec.calibrationStatus !in allowedCalibrationStatus) {
            add("film.calibrationStatus must be draft/calibrated")
        }
        if (spec.sources.isEmpty() || spec.sources.any { it.isBlank() }) {
            add("film.sources must contain at least one non-blank entry")
        }
        if (spec.grade.temp !in -1f..1f) add("film.grade.temp out of -1..1")
        if (spec.grade.tint !in -1f..1f) add("film.grade.tint out of -1..1")
        if (spec.grade.saturation !in 0f..2.5f) add("film.grade.saturation out of 0..2.5")
        if (spec.grade.contrast !in 0.5f..2.0f) add("film.grade.contrast out of 0.5..2.0")
        if (spec.grade.blackLift !in 0f..0.3f) add("film.grade.blackLift out of 0..0.3")
        if (spec.grade.tintStrength !in 0f..1f) add("film.grade.tintStrength out of 0..1")
        if (!HexColorParser.isValidHex(spec.grade.highlightTint)) {
            add("film.grade.highlightTint invalid hex: ${spec.grade.highlightTint}")
        }
        if (!HexColorParser.isValidHex(spec.grade.shadowTint)) {
            add("film.grade.shadowTint invalid hex: ${spec.grade.shadowTint}")
        }
        if (spec.grain.amount !in 0f..1f) add("film.grain.amount out of 0..1")
        if (spec.grain.size !in 0.0001f..0.02f) add("film.grain.size out of 0.0001..0.02")
        if (spec.grain.roughness !in 0f..1f) add("film.grain.roughness out of 0..1")
        if (spec.halation.intensity !in 0f..1f) add("film.halation.intensity out of 0..1")
        if (!HexColorParser.isValidHex(spec.halation.color)) {
            add("film.halation.color invalid hex: ${spec.halation.color}")
        }
    }

    fun validateArchetype(spec: ArchetypeSpec): List<String> = buildList {
        if (spec.schemaVersion != CURRENT_ARCHETYPE_SCHEMA_VERSION) {
            add("archetype.schemaVersion must be $CURRENT_ARCHETYPE_SCHEMA_VERSION")
        }
        if (spec.id.isBlank()) add("archetype.id must not be blank")
        if (spec.displayName.isBlank()) add("archetype.displayName must not be blank")
        if (spec.era.isBlank()) add("archetype.era must not be blank")
        if (spec.summary.isBlank()) add("archetype.summary must not be blank")
        if (spec.typicalTraits.isEmpty() || spec.typicalTraits.any { it.isBlank() }) {
            add("archetype.typicalTraits must not be empty")
        }
        if (spec.calibrationStatus !in allowedCalibrationStatus) {
            add("archetype.calibrationStatus must be draft/calibrated")
        }
        spec.cameraDefaults?.let { defaults ->
            if (defaults.aspectRatio !in allowedAspectRatios) {
                add("archetype.cameraDefaults.aspectRatio unsupported: ${defaults.aspectRatio}")
            }
            if (defaults.whiteBalance.temp !in -1f..1f) add("archetype.cameraDefaults.whiteBalance.temp out of -1..1")
            if (defaults.whiteBalance.tint !in -1f..1f) add("archetype.cameraDefaults.whiteBalance.tint out of -1..1")
            if (defaults.tone.contrast !in 0.5f..2.0f) add("archetype.cameraDefaults.tone.contrast out of 0.5..2.0")
            if (defaults.tone.highlightRolloff !in 0f..1f) add("archetype.cameraDefaults.tone.highlightRolloff out of 0..1")
            if (defaults.tone.shadowLift !in 0f..0.4f) add("archetype.cameraDefaults.tone.shadowLift out of 0..0.4")
            if (defaults.saturation !in 0f..2.5f) add("archetype.cameraDefaults.saturation out of 0..2.5")
            if (defaults.sensorNoise.amount !in 0f..1f) add("archetype.cameraDefaults.sensorNoise.amount out of 0..1")
            if (defaults.sensorNoise.chroma !in 0f..1f) add("archetype.cameraDefaults.sensorNoise.chroma out of 0..1")
            if (defaults.sharpen !in 0f..1f) add("archetype.cameraDefaults.sharpen out of 0..1")
            if (!HexColorParser.isValidHex(defaults.dateStamp.color)) {
                add("archetype.cameraDefaults.dateStamp.color invalid hex: ${defaults.dateStamp.color}")
            }
            addAll(validateBodyOptics("archetype.cameraDefaults.bodyOptics", defaults.bodyOptics))
        }
    }

    /**
     * Cross-entry catalog validator checking unique IDs, valid archetype references,
     * and absence of blank required fields across the entire loaded catalog.
     */
    fun validateCatalog(
        catalog: RecipeCatalog,
        expectedCameraCount: Int? = null,
    ): List<String> = buildList {
        if (expectedCameraCount != null && catalog.cameras.size != expectedCameraCount) {
            add("catalog.cameras count expected $expectedCameraCount but was ${catalog.cameras.size}")
        }

        val archetypeById = linkedMapOf<String, ArchetypeSpec>()
        for (arch in catalog.archetypes) {
            val archErrors = validateArchetype(arch)
            for (err in archErrors) {
                add("archetype[${arch.id}]: $err")
            }
            if (arch.id in archetypeById) {
                add("duplicate archetype id: ${arch.id}")
            } else {
                archetypeById[arch.id] = arch
            }
        }

        val seenCameraIds = hashSetOf<String>()
        for (camera in catalog.cameras) {
            val camErrors = validateCamera(camera)
            for (err in camErrors) {
                add("camera[${camera.id}]: $err")
            }
            if (!seenCameraIds.add(camera.id)) {
                add("duplicate camera id: ${camera.id}")
            }
            val linkedArchetype = archetypeById[camera.archetype]
            if (linkedArchetype == null) {
                add("camera[${camera.id}] references unknown archetype: ${camera.archetype}")
            } else if (linkedArchetype.cameraDefaults == null) {
                add("camera[${camera.id}] references non-camera archetype without cameraDefaults: ${camera.archetype}")
            }
        }

        val seenLensIds = hashSetOf<String>()
        for (lens in catalog.lenses) {
            val lensErrors = validateLens(lens)
            for (err in lensErrors) {
                add("lens[${lens.id}]: $err")
            }
            if (!seenLensIds.add(lens.id)) {
                add("duplicate lens id: ${lens.id}")
            }
        }

        val seenFilmIds = hashSetOf<String>()
        for (film in catalog.films) {
            val filmErrors = validateFilm(film)
            for (err in filmErrors) {
                add("film[${film.id}]: $err")
            }
            if (!seenFilmIds.add(film.id)) {
                add("duplicate film id: ${film.id}")
            }
            if (film.archetype !in archetypeById) {
                add("film[${film.id}] references unknown archetype: ${film.archetype}")
            }
        }
    }
}
