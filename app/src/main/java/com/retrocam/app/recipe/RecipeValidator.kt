package com.retrocam.app.recipe

/**
 * Pure-Kotlin validator for Camera, Lens, Film, and Archetype JSON specs.
 */
object RecipeValidator {

    private val allowedConfidence = setOf("high", "medium", "low")
    private val allowedCalibrationStatus = setOf("draft", "calibrated")
    private val allowedAspectRatios = setOf("4:3", "3:4", "3:2", "2:3", "16:9", "1:1")

    fun validateCamera(spec: CameraSpec): List<String> = buildList {
        if (spec.schemaVersion != 3) add("camera.schemaVersion must be 3")
        if (spec.id.isBlank()) add("camera.id must not be blank")
        if (spec.displayName.isBlank()) add("camera.displayName must not be blank")
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
        if (spec.aspectRatio !in allowedAspectRatios) {
            add("camera.aspectRatio unsupported: ${spec.aspectRatio}")
        }
        if (spec.whiteBalance.temp !in -1f..1f) add("camera.whiteBalance.temp out of -1..1")
        if (spec.whiteBalance.tint !in -1f..1f) add("camera.whiteBalance.tint out of -1..1")
        if (spec.tone.contrast !in 0.5f..2.0f) add("camera.tone.contrast out of 0.5..2.0")
        if (spec.tone.highlightRolloff !in 0f..1f) add("camera.tone.highlightRolloff out of 0..1")
        if (spec.tone.shadowLift !in 0f..0.4f) add("camera.tone.shadowLift out of 0..0.4")
        if (spec.saturation !in 0f..2.5f) add("camera.saturation out of 0..2.5")
        if (spec.sensorNoise.amount !in 0f..1f) add("camera.sensorNoise.amount out of 0..1")
        if (spec.sensorNoise.chroma !in 0f..1f) add("camera.sensorNoise.chroma out of 0..1")
        if (spec.sharpen !in 0f..1f) add("camera.sharpen out of 0..1")
        if (!HexColorParser.isValidHex(spec.dateStamp.color)) {
            add("camera.dateStamp.color invalid hex: ${spec.dateStamp.color}")
        }
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
        if (spec.schemaVersion != 1) add("archetype.schemaVersion must be 1")
        if (spec.id.isBlank()) add("archetype.id must not be blank")
        if (spec.displayName.isBlank()) add("archetype.displayName must not be blank")
        if (spec.era.isBlank()) add("archetype.era must not be blank")
        if (spec.summary.isBlank()) add("archetype.summary must not be blank")
        if (spec.typicalTraits.isEmpty() || spec.typicalTraits.any { it.isBlank() }) {
            add("archetype.typicalTraits must not be empty")
        }
    }
}
