package com.retrocam.app.recipe

import kotlinx.serialization.Serializable

@Serializable
data class WhiteBalanceSpec(
    val temp: Float,
    val tint: Float,
)

@Serializable
data class ToneSpec(
    val contrast: Float,
    val highlightRolloff: Float,
    val shadowLift: Float,
)

@Serializable
data class SensorNoiseSpec(
    val amount: Float,
    val chroma: Float,
)

@Serializable
data class DateStampSpec(
    val available: Boolean,
    val defaultOn: Boolean,
    val format: String,
    val color: String,
)

@Serializable
data class CameraSpec(
    val schemaVersion: Int,
    val id: String,
    val displayName: String,
    val inspiredBy: String,
    val era: String,
    val category: String,
    val archetype: String,
    val confidenceLook: String,
    val confidenceSpecs: String,
    val sources: List<String>,
    val calibrationStatus: String,
    val icon: String,
    val aspectRatio: String,
    val whiteBalance: WhiteBalanceSpec,
    val tone: ToneSpec,
    val saturation: Float,
    val sensorNoise: SensorNoiseSpec,
    val sharpen: Float,
    val dateStamp: DateStampSpec,
    val notes: String,
)

@Serializable
data class BloomSpec(
    val intensity: Float,
    val threshold: Float,
    val radius: Float,
)

@Serializable
data class VignetteSpec(
    val strength: Float,
    val radius: Float,
)

@Serializable
data class DistortionSpec(
    val k1: Float,
)

@Serializable
data class LensSpec(
    val schemaVersion: Int,
    val id: String,
    val displayName: String,
    val icon: String,
    val focalLengthEquiv: Int,
    val softness: Float,
    val bloom: BloomSpec,
    val vignette: VignetteSpec,
    val distortion: DistortionSpec,
    val chromaticAberration: Float,
    val flare: Float,
    val notes: String,
)

@Serializable
data class FilmGradeSpec(
    val temp: Float,
    val tint: Float,
    val saturation: Float,
    val contrast: Float,
    val blackLift: Float,
    val highlightTint: String,
    val shadowTint: String,
    val tintStrength: Float,
)

@Serializable
data class GrainSpec(
    val amount: Float,
    val size: Float,
    val roughness: Float,
)

@Serializable
data class HalationSpec(
    val intensity: Float,
    val color: String,
)

@Serializable
data class FilmSpec(
    val schemaVersion: Int,
    val id: String,
    val displayName: String,
    val archetype: String,
    val confidenceLook: String,
    val confidenceSpecs: String,
    val sources: List<String>,
    val calibrationStatus: String,
    val icon: String,
    val lut: String? = null,
    val grade: FilmGradeSpec,
    val grain: GrainSpec,
    val halation: HalationSpec,
    val notes: String,
)

@Serializable
data class ArchetypeSpec(
    val schemaVersion: Int,
    val id: String,
    val displayName: String,
    val era: String,
    val summary: String,
    val typicalTraits: List<String>,
)

data class RecipeLoadError(
    val assetPath: String,
    val message: String,
)

data class RecipeCatalog(
    val cameras: List<CameraSpec>,
    val lenses: List<LensSpec>,
    val films: List<FilmSpec>,
    val archetypes: List<ArchetypeSpec>,
    val loadErrors: List<RecipeLoadError>,
)
