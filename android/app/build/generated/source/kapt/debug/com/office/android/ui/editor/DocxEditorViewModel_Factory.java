package com.office.android.ui.editor;

import com.office.domain.usecase.SaveDocumentUseCase;
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
public final class DocxEditorViewModel_Factory implements Factory<DocxEditorViewModel> {
  private final Provider<SaveDocumentUseCase> saveDocumentProvider;

  public DocxEditorViewModel_Factory(Provider<SaveDocumentUseCase> saveDocumentProvider) {
    this.saveDocumentProvider = saveDocumentProvider;
  }

  @Override
  public DocxEditorViewModel get() {
    return newInstance(saveDocumentProvider.get());
  }

  public static DocxEditorViewModel_Factory create(
      Provider<SaveDocumentUseCase> saveDocumentProvider) {
    return new DocxEditorViewModel_Factory(saveDocumentProvider);
  }

  public static DocxEditorViewModel newInstance(SaveDocumentUseCase saveDocument) {
    return new DocxEditorViewModel(saveDocument);
  }
}
