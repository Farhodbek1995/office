package com.office.data.di;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\bH\u0007\u00a8\u0006\t"}, d2 = {"Lcom/office/data/di/EngineModule;", "", "()V", "provideLibreOfficeEngine", "Lcom/office/engine/api/DocumentEngine;", "engine", "Lcom/office/engine/libreoffice/LibreOfficeEngine;", "providePdfEngine", "Lcom/office/engine/pdf/PdfRendererEngine;", "data_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class EngineModule {
    @org.jetbrains.annotations.NotNull()
    public static final com.office.data.di.EngineModule INSTANCE = null;
    
    private EngineModule() {
        super();
    }
    
    @dagger.Provides()
    @dagger.multibindings.IntoSet()
    @org.jetbrains.annotations.NotNull()
    public final com.office.engine.api.DocumentEngine providePdfEngine(@org.jetbrains.annotations.NotNull()
    com.office.engine.pdf.PdfRendererEngine engine) {
        return null;
    }
    
    @dagger.Provides()
    @dagger.multibindings.IntoSet()
    @org.jetbrains.annotations.NotNull()
    public final com.office.engine.api.DocumentEngine provideLibreOfficeEngine(@org.jetbrains.annotations.NotNull()
    com.office.engine.libreoffice.LibreOfficeEngine engine) {
        return null;
    }
}