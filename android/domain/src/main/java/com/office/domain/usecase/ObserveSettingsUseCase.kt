package com.office.domain.usecase

import com.office.domain.model.AppSettings
import com.office.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Observes user settings (theme, language, autosave) for the UI (reja.txt 3, 46-bo'lim).
 */
class ObserveSettingsUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    operator fun invoke(): Flow<AppSettings> = repository.settings
}
