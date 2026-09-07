package com.office.engine.pdf;

/**
 * PDF rendering engine built on the platform [PdfRenderer] (reja.txt 13-bo'lim, MVP).
 * Pages are rendered to bitmaps on a background dispatcher; the UI layer owns
 * caching/bitmap-pooling strategy (reja.txt 29-bo'lim).
 */
@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0007H\u0016J4\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0013H\u0096@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0014\u0010\u0015J$\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00110\u000e2\u0006\u0010\u0017\u001a\u00020\u0018H\u0096@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0017\u001a\u00020\u0018H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u001d"}, d2 = {"Lcom/office/engine/pdf/PdfRendererEngine;", "Lcom/office/engine/api/DocumentEngine;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "supportedFormats", "", "Lcom/office/engine/api/DocumentFormat;", "getSupportedFormats", "()Ljava/util/Set;", "canHandle", "", "format", "export", "Lkotlin/Result;", "", "session", "Lcom/office/engine/api/DocumentSession;", "destination", "Lcom/office/engine/api/DocumentDestination;", "export-BWLJW6A", "(Lcom/office/engine/api/DocumentSession;Lcom/office/engine/api/DocumentFormat;Lcom/office/engine/api/DocumentDestination;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "open", "source", "Lcom/office/engine/api/DocumentSource;", "open-gIAlu-s", "(Lcom/office/engine/api/DocumentSource;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "openDescriptor", "Landroid/os/ParcelFileDescriptor;", "pdf-engine_debug"})
public final class PdfRendererEngine implements com.office.engine.api.DocumentEngine {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Set<com.office.engine.api.DocumentFormat> supportedFormats = null;
    
    @javax.inject.Inject()
    public PdfRendererEngine(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.util.Set<com.office.engine.api.DocumentFormat> getSupportedFormats() {
        return null;
    }
    
    @java.lang.Override()
    public boolean canHandle(@org.jetbrains.annotations.NotNull()
    com.office.engine.api.DocumentFormat format) {
        return false;
    }
    
    private final android.os.ParcelFileDescriptor openDescriptor(com.office.engine.api.DocumentSource source) {
        return null;
    }
}