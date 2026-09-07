package com.office.android.ui.pdf;

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
public final class PdfViewModel_Factory implements Factory<PdfViewModel> {
  private final Provider<DocumentRepository> documentRepositoryProvider;

  public PdfViewModel_Factory(Provider<DocumentRepository> documentRepositoryProvider) {
    this.documentRepositoryProvider = documentRepositoryProvider;
  }

  @Override
  public PdfViewModel get() {
    return newInstance(documentRepositoryProvider.get());
  }

  public static PdfViewModel_Factory create(
      Provider<DocumentRepository> documentRepositoryProvider) {
    return new PdfViewModel_Factory(documentRepositoryProvider);
  }

  public static PdfViewModel newInstance(DocumentRepository documentRepository) {
    return new PdfViewModel(documentRepository);
  }
}
