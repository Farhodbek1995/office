package com.office.android.ui.spreadsheet;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class XlsxViewModel_Factory implements Factory<XlsxViewModel> {
  @Override
  public XlsxViewModel get() {
    return newInstance();
  }

  public static XlsxViewModel_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static XlsxViewModel newInstance() {
    return new XlsxViewModel();
  }

  private static final class InstanceHolder {
    private static final XlsxViewModel_Factory INSTANCE = new XlsxViewModel_Factory();
  }
}
