package com.office.android.ui.home;

import com.office.domain.usecase.ObserveRecentFilesUseCase;
import com.office.domain.usecase.ObserveSettingsUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation"
})
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<ObserveRecentFilesUseCase> observeRecentFilesProvider;

  private final Provider<ObserveSettingsUseCase> observeSettingsProvider;

  public HomeViewModel_Factory(Provider<ObserveRecentFilesUseCase> observeRecentFilesProvider,
      Provider<ObserveSettingsUseCase> observeSettingsProvider) {
    this.observeRecentFilesProvider = observeRecentFilesProvider;
    this.observeSettingsProvider = observeSettingsProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(observeRecentFilesProvider.get(), observeSettingsProvider.get());
  }

  public static HomeViewModel_Factory create(
      Provider<ObserveRecentFilesUseCase> observeRecentFilesProvider,
      Provider<ObserveSettingsUseCase> observeSettingsProvider) {
    return new HomeViewModel_Factory(observeRecentFilesProvider, observeSettingsProvider);
  }

  public static HomeViewModel newInstance(ObserveRecentFilesUseCase observeRecentFiles,
      ObserveSettingsUseCase observeSettings) {
    return new HomeViewModel(observeRecentFiles, observeSettings);
  }
}
