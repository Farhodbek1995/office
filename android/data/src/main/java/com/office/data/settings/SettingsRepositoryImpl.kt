package com.office.data.settings

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.office.domain.model.AppSettings
import com.office.domain.model.Language
import com.office.domain.model.ThemeMode
import com.office.domain.repository.SettingsRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.settingsDataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@Singleton
class SettingsRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : SettingsRepository {

    private object Keys {
        val THEME = stringPreferencesKey("theme")
        val LANGUAGE = stringPreferencesKey("language")
        val AUTOSAVE = booleanPreferencesKey("autosave_enabled")
        val AUTOSAVE_DELAY = longPreferencesKey("autosave_delay_ms")
    }

    override val settings: Flow<AppSettings> = context.settingsDataStore.data.map { prefs ->
        AppSettings(
            theme = prefs[Keys.THEME]?.let { ThemeMode.valueOf(it) } ?: ThemeMode.SYSTEM,
            language = prefs[Keys.LANGUAGE]?.let { Language.valueOf(it) } ?: Language.ENGLISH,
            autosaveEnabled = prefs[Keys.AUTOSAVE] ?: true,
            autosaveDelayMs = prefs[Keys.AUTOSAVE_DELAY] ?: 3000L
        )
    }

    override suspend fun updateSettings(transform: (AppSettings) -> AppSettings): AppSettings {
        val current = settings.first()
        val updated = transform(current)
        context.settingsDataStore.edit { prefs ->
            prefs[Keys.THEME] = updated.theme.name
            prefs[Keys.LANGUAGE] = updated.language.name
            prefs[Keys.AUTOSAVE] = updated.autosaveEnabled
            prefs[Keys.AUTOSAVE_DELAY] = updated.autosaveDelayMs
        }
        return updated
    }
}
