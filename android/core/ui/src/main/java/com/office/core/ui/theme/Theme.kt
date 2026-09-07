package com.office.core.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val BrandPrimary = Color(0xFF4F46E5)
val BrandSecondary = Color(0xFF3B82F6)
val DocxBlue = Color(0xFF2B579A)
val XlsxGreen = Color(0xFF217346)
val PdfRed = Color(0xFFB30B00)

val DarkColorScheme = darkColorScheme(
    primary = BrandPrimary,
    secondary = BrandSecondary,
    background = Color(0xFF0B0F19),
    surface = Color(0xFF111827),
    onPrimary = Color.White,
    onBackground = Color(0xFFF9FAFB),
    onSurface = Color(0xFFF9FAFB)
)

val LightColorScheme = lightColorScheme(
    primary = BrandPrimary,
    secondary = BrandSecondary,
    background = Color(0xFFF3F4F6),
    surface = Color(0xFFFFFFFF),
    onPrimary = Color.White,
    onBackground = Color(0xFF111827),
    onSurface = Color(0xFF111827)
)
