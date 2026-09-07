package com.office.engine.libreoffice;

/**
 * Lightweight session for the placeholder engine. Holds the original source so the
 * app can round-trip file bytes through SAF without a native parser.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\'\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\u000e\u0010\u0011\u001a\u00020\u0012H\u0096@\u00a2\u0006\u0002\u0010\u0013J&\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00120\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0096@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0018\u0010\u0019R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u001a"}, d2 = {"Lcom/office/engine/libreoffice/LibreOfficeSession;", "Lcom/office/engine/api/DocumentSession;", "id", "", "name", "format", "Lcom/office/engine/api/DocumentFormat;", "source", "Lcom/office/engine/api/DocumentSource;", "(Ljava/lang/String;Ljava/lang/String;Lcom/office/engine/api/DocumentFormat;Lcom/office/engine/api/DocumentSource;)V", "getFormat", "()Lcom/office/engine/api/DocumentFormat;", "getId", "()Ljava/lang/String;", "getName", "getSource", "()Lcom/office/engine/api/DocumentSource;", "close", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "save", "Lkotlin/Result;", "destination", "Lcom/office/engine/api/DocumentDestination;", "save-gIAlu-s", "(Lcom/office/engine/api/DocumentDestination;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "libreoffice-engine_debug"})
public final class LibreOfficeSession implements com.office.engine.api.DocumentSession {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String id = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String name = null;
    @org.jetbrains.annotations.NotNull()
    private final com.office.engine.api.DocumentFormat format = null;
    @org.jetbrains.annotations.NotNull()
    private final com.office.engine.api.DocumentSource source = null;
    
    public LibreOfficeSession(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    com.office.engine.api.DocumentFormat format, @org.jetbrains.annotations.NotNull()
    com.office.engine.api.DocumentSource source) {
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
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.office.engine.api.DocumentFormat getFormat() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.office.engine.api.DocumentSource getSource() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object close(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}