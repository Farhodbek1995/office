package com.office.data.di;

import com.office.engine.api.DocumentEngine;
import com.office.engine.pdf.PdfRendererEngine;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class EngineModule_ProvidePdfEngineFactory implements Factory<DocumentEngine> {
  private final Provider<PdfRendererEngine> engineProvider;

  public EngineModule_ProvidePdfEngineFactory(Provider<PdfRendererEngine> engineProvider) {
    this.engineProvider = engineProvider;
  }

  @Override
  public DocumentEngine get() {
    return providePdfEngine(engineProvider.get());
  }

  public static EngineModule_ProvidePdfEngineFactory create(
      Provider<PdfRendererEngine> engineProvider) {
    return new EngineModule_ProvidePdfEngineFactory(engineProvider);
  }

  public static DocumentEngine providePdfEngine(PdfRendererEngine engine) {
    return Preconditions.checkNotNullFromProvides(EngineModule.INSTANCE.providePdfEngine(engine));
  }
}
