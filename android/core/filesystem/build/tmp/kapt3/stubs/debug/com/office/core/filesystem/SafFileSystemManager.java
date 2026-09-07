package com.office.core.filesystem;

/**
 * Default SAF-backed implementation of [FileSystemManager].
 *
 * All blocking I/O is dispatched onto [Dispatchers.IO] (Rule 5 / reja.txt 30-bo'lim).
 * Creation of new documents is delegated to ACTION_CREATE_DOCUMENT through a pending
 * intent held by the caller; this class returns the URI that the caller must then resolve.
 */
@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\b\b\u0007\u0018\u0000 #2\u00020\u0001:\u0001#B\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J$\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0096@\u00a2\u0006\u0002\u0010\u0010J$\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\n2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0096@\u00a2\u0006\u0002\u0010\u0016J\u001c\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\r0\n2\u0006\u0010\u0018\u001a\u00020\u0012H\u0096@\u00a2\u0006\u0002\u0010\u0019J\u001c\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000f0\n2\u0006\u0010\u0018\u001a\u00020\u0012H\u0096@\u00a2\u0006\u0002\u0010\u0019J\u001c\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\n2\u0006\u0010\u0018\u001a\u00020\u0012H\u0096@\u00a2\u0006\u0002\u0010\u0019J$\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00140\n2\u0006\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u001e\u001a\u00020\u0014H\u0082@\u00a2\u0006\u0002\u0010\u001fJ\u001e\u0010 \u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00140\n2\u0006\u0010\u0018\u001a\u00020\u0012H\u0096@\u00a2\u0006\u0002\u0010\u0019J\u001c\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00140\n2\u0006\u0010\u0018\u001a\u00020\u0012H\u0096@\u00a2\u0006\u0002\u0010\u0019J\u001c\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0018\u001a\u00020\u0012H\u0096@\u00a2\u0006\u0002\u0010\u0019R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00068BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\b\u00a8\u0006$"}, d2 = {"Lcom/office/core/filesystem/SafFileSystemManager;", "Lcom/office/core/filesystem/FileSystemManager;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "resolver", "Landroid/content/ContentResolver;", "getResolver", "()Landroid/content/ContentResolver;", "copy", "Lcom/office/core/common/AppResult;", "", "input", "Ljava/io/InputStream;", "output", "Ljava/io/OutputStream;", "(Ljava/io/InputStream;Ljava/io/OutputStream;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "create", "Landroid/net/Uri;", "name", "", "mimeType", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "open", "uri", "(Landroid/net/Uri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "openForWrite", "persistPermission", "", "queryColumn", "column", "(Landroid/net/Uri;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "queryMimeType", "queryName", "querySize", "Companion", "filesystem_debug"})
public final class SafFileSystemManager implements com.office.core.filesystem.FileSystemManager {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    private static final int DEFAULT_BUFFER_SIZE = 8192;
    @org.jetbrains.annotations.NotNull()
    public static final com.office.core.filesystem.SafFileSystemManager.Companion Companion = null;
    
    @javax.inject.Inject()
    public SafFileSystemManager(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    private final android.content.ContentResolver getResolver() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object open(@org.jetbrains.annotations.NotNull()
    android.net.Uri uri, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.office.core.common.AppResult<? extends java.io.InputStream>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object openForWrite(@org.jetbrains.annotations.NotNull()
    android.net.Uri uri, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.office.core.common.AppResult<? extends java.io.OutputStream>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object create(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String mimeType, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.office.core.common.AppResult<? extends android.net.Uri>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object persistPermission(@org.jetbrains.annotations.NotNull()
    android.net.Uri uri, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.office.core.common.AppResult<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object queryName(@org.jetbrains.annotations.NotNull()
    android.net.Uri uri, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.office.core.common.AppResult<java.lang.String>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object querySize(@org.jetbrains.annotations.NotNull()
    android.net.Uri uri, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.office.core.common.AppResult<java.lang.Long>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object queryMimeType(@org.jetbrains.annotations.NotNull()
    android.net.Uri uri, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.office.core.common.AppResult<java.lang.String>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object copy(@org.jetbrains.annotations.NotNull()
    java.io.InputStream input, @org.jetbrains.annotations.NotNull()
    java.io.OutputStream output, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.office.core.common.AppResult<java.lang.Long>> $completion) {
        return null;
    }
    
    private final java.lang.Object queryColumn(android.net.Uri uri, java.lang.String column, kotlin.coroutines.Continuation<? super com.office.core.common.AppResult<java.lang.String>> $completion) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/office/core/filesystem/SafFileSystemManager$Companion;", "", "()V", "DEFAULT_BUFFER_SIZE", "", "filesystem_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}