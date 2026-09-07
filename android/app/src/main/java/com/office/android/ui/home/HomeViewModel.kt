package com.office.android.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.office.domain.model.AppSettings
import com.office.domain.model.RecentFile
import com.office.domain.usecase.ObserveRecentFilesUseCase
import com.office.domain.usecase.ObserveSettingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    observeRecentFiles: ObserveRecentFilesUseCase,
    observeSettings: ObserveSettingsUseCase
) : ViewModel() {

    val recentFiles: StateFlow<List<RecentFile>> = observeRecentFiles(limit = 50)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    val settings: StateFlow<AppSettings> = observeSettings()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), AppSettings())
}
