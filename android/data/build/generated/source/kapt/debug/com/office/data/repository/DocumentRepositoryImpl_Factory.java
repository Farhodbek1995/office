package com.office.data.repository;

import com.office.engine.api.DocumentEngine;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import java.util.Set;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class DocumentRepositoryImpl_Factory implements Factory<DocumentRepositoryImpl> {
  private final Provider<Set<DocumentEngine>> enginesProvider;

  public DocumentRepositoryImpl_Factory(Provider<Set<DocumentEngine>> enginesProvider) {
    this.enginesProvider = enginesProvider;
  }

  @Override
  public DocumentRepositoryImpl get() {
    return newInstance(enginesProvider.get());
  }

  public static DocumentRepositoryImpl_Factory create(
      Provider<Set<DocumentEngine>> enginesProvider) {
    return new DocumentRepositoryImpl_Factory(enginesProvider);
  }

  public static DocumentRepositoryImpl newInstance(Set<DocumentEngine> engines) {
    return new DocumentRepositoryImpl(engines);
  }
}
