package com.office.data.repository;

/**
 * Routes document operations to the engine that supports the given format.
 * This is the single entry point the UI/ViewModel layer talks to (Rule 10).
 */
@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u001a\b\u0007\u0012\u0011\u0010\u0002\u001a\r\u0012\t\u0012\u00070\u0004\u00a2\u0006\u0002\b\u00050\u0003\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0096@\u00a2\u0006\u0002\u0010\fJ,\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0096@\u00a2\u0006\u0002\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J$\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000b0\b2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u000e\u001a\u00020\u000fH\u0096@\u00a2\u0006\u0002\u0010\u001aJ&\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0096@\u00a2\u0006\u0002\u0010\u001cR\u0019\u0010\u0002\u001a\r\u0012\t\u0012\u00070\u0004\u00a2\u0006\u0002\b\u00050\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lcom/office/data/repository/DocumentRepositoryImpl;", "Lcom/office/domain/repository/DocumentRepository;", "engines", "", "Lcom/office/engine/api/DocumentEngine;", "Lkotlin/jvm/JvmSuppressWildcards;", "(Ljava/util/Set;)V", "close", "Lcom/office/core/common/AppResult;", "", "session", "Lcom/office/engine/api/DocumentSession;", "(Lcom/office/engine/api/DocumentSession;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "export", "format", "Lcom/office/engine/api/DocumentFormat;", "destination", "Lcom/office/engine/api/DocumentDestination;", "(Lcom/office/engine/api/DocumentSession;Lcom/office/engine/api/DocumentFormat;Lcom/office/engine/api/DocumentDestination;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "mapEngineError", "Lcom/office/core/common/DocumentError;", "t", "", "open", "source", "Lcom/office/engine/api/DocumentSource;", "(Lcom/office/engine/api/DocumentSource;Lcom/office/engine/api/DocumentFormat;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "save", "(Lcom/office/engine/api/DocumentSession;Lcom/office/engine/api/DocumentDestination;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "data_debug"})
public final class DocumentRepositoryImpl implements com.office.domain.repository.DocumentRepository {
    @org.jetbrains.annotations.NotNull()
    private final java.util.Set<com.office.engine.api.DocumentEngine> engines = null;
    
    @javax.inject.Inject()
    public DocumentRepositoryImpl(@org.jetbrains.annotations.NotNull()
    java.util.Set<com.office.engine.api.DocumentEngine> engines) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object open(@org.jetbrains.annotations.NotNull()
    com.office.engine.api.DocumentSource source, @org.jetbrains.annotations.NotNull()
    com.office.engine.api.DocumentFormat format, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.office.core.common.AppResult<? extends com.office.engine.api.DocumentSession>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object save(@org.jetbrains.annotations.NotNull()
    com.office.engine.api.DocumentSession session, @org.jetbrains.annotations.Nullable()
    com.office.engine.api.DocumentDestination destination, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.office.core.common.AppResult<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object export(@org.jetbrains.annotations.NotNull()
    com.office.engine.api.DocumentSession session, @org.jetbrains.annotations.NotNull()
    com.office.engine.api.DocumentFormat format, @org.jetbrains.annotations.NotNull()
    com.office.engine.api.DocumentDestination destination, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.office.core.common.AppResult<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object close(@org.jetbrains.annotations.NotNull()
    com.office.engine.api.DocumentSession session, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.office.core.common.AppResult<kotlin.Unit>> $completion) {
        return null;
    }
    
    private final com.office.core.common.DocumentError mapEngineError(java.lang.Throwable t) {
        return null;
    }
}