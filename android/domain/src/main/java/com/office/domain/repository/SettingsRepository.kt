package com.office.domain.repository

import com.office.domain.model.AppSettings
import kotlinx.coroutines.flow.Flow

/**
 * Settings repository backed by DataStore (reja.txt 3-bo'lim: Settings).
 */
interface SettingsRepository {
    val settings: Flow<AppSettings>
    suspend fun updateSettings(transform: (AppSettings) -> AppSettings): AppSettings
}
