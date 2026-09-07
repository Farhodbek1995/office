package com.office.domain.usecase

import com.office.domain.model.AppSettings
import com.office.domain.repository.SettingsRepository
import javax.inject.Inject

/**
 * Updates a subset of user settings (reja.txt 3-bo'lim).
 */
class UpdateSettingsUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke(
        transform: (AppSettings) -> AppSettings
    ): AppSettings = repository.updateSettings(transform)
}
