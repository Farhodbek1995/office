package com.office.domain.usecase;

import com.office.domain.repository.RecentFilesRepository;
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
public final class ObserveRecentFilesUseCase_Factory implements Factory<ObserveRecentFilesUseCase> {
  private final Provider<RecentFilesRepository> repositoryProvider;

  public ObserveRecentFilesUseCase_Factory(Provider<RecentFilesRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public ObserveRecentFilesUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static ObserveRecentFilesUseCase_Factory create(
      Provider<RecentFilesRepository> repositoryProvider) {
    return new ObserveRecentFilesUseCase_Factory(repositoryProvider);
  }

  public static ObserveRecentFilesUseCase newInstance(RecentFilesRepository repository) {
    return new ObserveRecentFilesUseCase(repository);
  }
}
