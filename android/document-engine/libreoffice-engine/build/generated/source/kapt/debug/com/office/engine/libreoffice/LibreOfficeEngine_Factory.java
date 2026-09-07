package com.office.engine.libreoffice;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class LibreOfficeEngine_Factory implements Factory<LibreOfficeEngine> {
  private final Provider<Context> contextProvider;

  public LibreOfficeEngine_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public LibreOfficeEngine get() {
    return newInstance(contextProvider.get());
  }

  public static LibreOfficeEngine_Factory create(Provider<Context> contextProvider) {
    return new LibreOfficeEngine_Factory(contextProvider);
  }

  public static LibreOfficeEngine newInstance(Context context) {
    return new LibreOfficeEngine(context);
  }
}
