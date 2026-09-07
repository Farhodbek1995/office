package com.office.engine.pdf;

/**
 * Wraps [PdfRenderer] into a [DocumentSession]. Page rendering is exposed
 * through [renderPage] which the PDF viewer uses for page rendering.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\'\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\u000e\u0010\u0017\u001a\u00020\u0018H\u0096@\u00a2\u0006\u0002\u0010\u0019J(\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u00122\u0006\u0010\u001d\u001a\u00020\u00122\u0006\u0010\u001e\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010\u001fJ&\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00180!2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0096@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b$\u0010%R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0011\u001a\u00020\u00128F\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0005\u001a\u00020\u0006X\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006&"}, d2 = {"Lcom/office/engine/pdf/PdfDocumentSession;", "Lcom/office/engine/api/DocumentSession;", "id", "", "name", "renderer", "Landroid/graphics/pdf/PdfRenderer;", "descriptor", "Landroid/os/ParcelFileDescriptor;", "(Ljava/lang/String;Ljava/lang/String;Landroid/graphics/pdf/PdfRenderer;Landroid/os/ParcelFileDescriptor;)V", "format", "Lcom/office/engine/api/DocumentFormat;", "getFormat", "()Lcom/office/engine/api/DocumentFormat;", "getId", "()Ljava/lang/String;", "getName", "pageCount", "", "getPageCount", "()I", "getRenderer$pdf_engine_debug", "()Landroid/graphics/pdf/PdfRenderer;", "close", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "renderPage", "Landroid/graphics/Bitmap;", "pageIndex", "targetWidth", "targetHeight", "(IIILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "save", "Lkotlin/Result;", "destination", "Lcom/office/engine/api/DocumentDestination;", "save-gIAlu-s", "(Lcom/office/engine/api/DocumentDestination;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "pdf-engine_debug"})
public final class PdfDocumentSession implements com.office.engine.api.DocumentSession {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String id = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String name = null;
    @org.jetbrains.annotations.NotNull()
    private final android.graphics.pdf.PdfRenderer renderer = null;
    @org.jetbrains.annotations.NotNull()
    private final android.os.ParcelFileDescriptor descriptor = null;
    @org.jetbrains.annotations.NotNull()
    private final com.office.engine.api.DocumentFormat format = com.office.engine.api.DocumentFormat.PDF;
    
    public PdfDocumentSession(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    android.graphics.pdf.PdfRenderer renderer, @org.jetbrains.annotations.NotNull()
    android.os.ParcelFileDescriptor descriptor) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String getId() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String getName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.graphics.pdf.PdfRenderer getRenderer$pdf_engine_debug() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.office.engine.api.DocumentFormat getFormat() {
        return null;
    }
    
    public final int getPageCount() {
        return 0;
    }
    
    /**
     * Renders a single page (1-based [pageIndex]) into a [Bitmap].
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object renderPage(int pageIndex, int targetWidth, int targetHeight, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super android.graphics.Bitmap> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object close(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}