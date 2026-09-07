package com.office.data.repository;

import com.office.data.database.dao.RecentFileDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
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
public final class RecentFilesRepositoryImpl_Factory implements Factory<RecentFilesRepositoryImpl> {
  private final Provider<RecentFileDao> daoProvider;

  public RecentFilesRepositoryImpl_Factory(Provider<RecentFileDao> daoProvider) {
    this.daoProvider = daoProvider;
  }

  @Override
  public RecentFilesRepositoryImpl get() {
    return newInstance(daoProvider.get());
  }

  public static RecentFilesRepositoryImpl_Factory create(Provider<RecentFileDao> daoProvider) {
    return new RecentFilesRepositoryImpl_Factory(daoProvider);
  }

  public static RecentFilesRepositoryImpl newInstance(RecentFileDao dao) {
    return new RecentFilesRepositoryImpl(dao);
  }
}
