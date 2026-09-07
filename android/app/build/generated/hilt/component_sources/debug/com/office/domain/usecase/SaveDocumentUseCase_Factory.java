package com.office.domain.usecase;

import com.office.domain.repository.DocumentRepository;
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
public final class SaveDocumentUseCase_Factory implements Factory<SaveDocumentUseCase> {
  private final Provider<DocumentRepository> repositoryProvider;

  public SaveDocumentUseCase_Factory(Provider<DocumentRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public SaveDocumentUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static SaveDocumentUseCase_Factory create(
      Provider<DocumentRepository> repositoryProvider) {
    return new SaveDocumentUseCase_Factory(repositoryProvider);
  }

  public static SaveDocumentUseCase newInstance(DocumentRepository repository) {
    return new SaveDocumentUseCase(repository);
  }
}
