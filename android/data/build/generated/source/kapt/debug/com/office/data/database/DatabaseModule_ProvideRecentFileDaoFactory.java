package com.office.data.database;

import com.office.data.database.dao.RecentFileDao;
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
public final class DatabaseModule_ProvideRecentFileDaoFactory implements Factory<RecentFileDao> {
  private final Provider<OfficeDatabase> dbProvider;

  public DatabaseModule_ProvideRecentFileDaoFactory(Provider<OfficeDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public RecentFileDao get() {
    return provideRecentFileDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideRecentFileDaoFactory create(
      Provider<OfficeDatabase> dbProvider) {
    return new DatabaseModule_ProvideRecentFileDaoFactory(dbProvider);
  }

  public static RecentFileDao provideRecentFileDao(OfficeDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideRecentFileDao(db));
  }
}
