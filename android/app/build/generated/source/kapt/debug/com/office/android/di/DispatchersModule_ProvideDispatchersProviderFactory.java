package com.office.android.di;

import com.office.core.common.DispatchersProvider;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class DispatchersModule_ProvideDispatchersProviderFactory implements Factory<DispatchersProvider> {
  @Override
  public DispatchersProvider get() {
    return provideDispatchersProvider();
  }

  public static DispatchersModule_ProvideDispatchersProviderFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static DispatchersProvider provideDispatchersProvider() {
    return Preconditions.checkNotNullFromProvides(DispatchersModule.INSTANCE.provideDispatchersProvider());
  }

  private static final class InstanceHolder {
    private static final DispatchersModule_ProvideDispatchersProviderFactory INSTANCE = new DispatchersModule_ProvideDispatchersProviderFactory();
  }
}
