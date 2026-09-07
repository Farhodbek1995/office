package com.office.data.di;

import com.office.engine.api.DocumentEngine;
import com.office.engine.libreoffice.LibreOfficeEngine;
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
public final class EngineModule_ProvideLibreOfficeEngineFactory implements Factory<DocumentEngine> {
  private final Provider<LibreOfficeEngine> engineProvider;

  public EngineModule_ProvideLibreOfficeEngineFactory(Provider<LibreOfficeEngine> engineProvider) {
    this.engineProvider = engineProvider;
  }

  @Override
  public DocumentEngine get() {
    return provideLibreOfficeEngine(engineProvider.get());
  }

  public static EngineModule_ProvideLibreOfficeEngineFactory create(
      Provider<LibreOfficeEngine> engineProvider) {
    return new EngineModule_ProvideLibreOfficeEngineFactory(engineProvider);
  }

  public static DocumentEngine provideLibreOfficeEngine(LibreOfficeEngine engine) {
    return Preconditions.checkNotNullFromProvides(EngineModule.INSTANCE.provideLibreOfficeEngine(engine));
  }
}
