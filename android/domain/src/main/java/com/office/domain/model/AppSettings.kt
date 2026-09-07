package com.office.domain.model

/**
 * User preferences stored via DataStore (reja.txt 3-bo'lim: Settings).
 */
data class AppSettings(
    val theme: ThemeMode = ThemeMode.SYSTEM,
    val language: Language = Language.ENGLISH,
    val autosaveEnabled: Boolean = true,
    val autosaveDelayMs: Long = 3000L
)

enum class ThemeMode { SYSTEM, LIGHT, DARK }

enum class Language(val code: String) {
    ENGLISH("en"),
    UZBEK("uz"),
    RUSSIAN("ru")
}
