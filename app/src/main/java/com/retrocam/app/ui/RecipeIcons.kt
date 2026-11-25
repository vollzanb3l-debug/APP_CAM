package com.retrocam.app.ui

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke

@Composable
fun CameraGlyph(
    tint: Color,
    modifier: Modifier = Modifier,
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        val stroke = Stroke(width = w * 0.06f)

        // Top viewfinder bump
        drawRoundRect(
            color = tint,
            topLeft = Offset(w * 0.30f, h * 0.16f),
            size = Size(w * 0.28f, h * 0.14f),
            cornerRadius = CornerRadius(w * 0.05f, w * 0.05f),
            style = stroke,
        )
        // Camera body
        drawRoundRect(
            color = tint,
            topLeft = Offset(w * 0.12f, h * 0.28f),
            size = Size(w * 0.76f, h * 0.54f),
            cornerRadius = CornerRadius(w * 0.10f, w * 0.10f),
            style = stroke,
        )
        // Lens mount ring
        drawCircle(
            color = tint,
            radius = h * 0.17f,
            center = Offset(w * 0.50f, h * 0.55f),
            style = stroke,
        )
        drawCircle(
            color = tint,
            radius = h * 0.06f,
            center = Offset(w * 0.50f, h * 0.55f),
        )
    }
}

@Composable
fun LensGlyph(
    tint: Color,
    modifier: Modifier = Modifier,
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        val center = Offset(w * 0.50f, h * 0.50f)
        val radius = minOf(w, h) * 0.36f
        val stroke = Stroke(width = w * 0.06f)

        drawCircle(color = tint, radius = radius, center = center, style = stroke)
        drawCircle(color = tint, radius = radius * 0.64f, center = center, style = stroke)
        drawCircle(color = tint, radius = radius * 0.25f, center = center)
    }
}

@Composable
fun FilmGlyph(
    tint: Color,
    modifier: Modifier = Modifier,
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        val stroke = Stroke(width = w * 0.06f)

        // Top spool cap
        drawRoundRect(
            color = tint,
            topLeft = Offset(w * 0.36f, h * 0.12f),
            size = Size(w * 0.28f, h * 0.12f),
            cornerRadius = CornerRadius(w * 0.04f, w * 0.04f),
            style = stroke,
        )
        // Canister cylinder
        drawRoundRect(
            color = tint,
            topLeft = Offset(w * 0.24f, h * 0.24f),
            size = Size(w * 0.52f, h * 0.62f),
            cornerRadius = CornerRadius(w * 0.07f, w * 0.07f),
            style = stroke,
        )
        // Leader tongue on right
        drawRoundRect(
            color = tint,
            topLeft = Offset(w * 0.76f, h * 0.36f),
            size = Size(w * 0.14f, h * 0.30f),
            cornerRadius = CornerRadius(w * 0.03f, w * 0.03f),
            style = stroke,
        )
    }
}
