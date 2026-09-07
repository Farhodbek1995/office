package com.office.data.database;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DatabaseModule_ProvideOfficeDatabaseFactory implements Factory<OfficeDatabase> {
  private final Provider<Context> contextProvider;

  public DatabaseModule_ProvideOfficeDatabaseFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public OfficeDatabase get() {
    return provideOfficeDatabase(contextProvider.get());
  }

  public static DatabaseModule_ProvideOfficeDatabaseFactory create(
      Provider<Context> contextProvider) {
    return new DatabaseModule_ProvideOfficeDatabaseFactory(contextProvider);
  }

  public static OfficeDatabase provideOfficeDatabase(Context context) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideOfficeDatabase(context));
  }
}
