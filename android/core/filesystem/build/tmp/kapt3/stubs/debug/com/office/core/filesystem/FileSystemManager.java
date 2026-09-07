package com.office.core.filesystem;

/**
 * Abstraction over the Android Storage Access Framework (reja.txt 7-bo'lim).
 * The app works with content:// URIs and prefers streaming I/O over ByteArray
 * so large files are never fully loaded into RAM (Rule 4).
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J$\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u00a6@\u00a2\u0006\u0002\u0010\tJ$\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00032\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u00a6@\u00a2\u0006\u0002\u0010\u000fJ\u001c\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\u0006\u0010\u0011\u001a\u00020\u000bH\u00a6@\u00a2\u0006\u0002\u0010\u0012J\u001c\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u00032\u0006\u0010\u0011\u001a\u00020\u000bH\u00a6@\u00a2\u0006\u0002\u0010\u0012J\u001c\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u00032\u0006\u0010\u0011\u001a\u00020\u000bH\u00a6@\u00a2\u0006\u0002\u0010\u0012J\u001e\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u00032\u0006\u0010\u0011\u001a\u00020\u000bH\u00a6@\u00a2\u0006\u0002\u0010\u0012J\u001c\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\r0\u00032\u0006\u0010\u0011\u001a\u00020\u000bH\u00a6@\u00a2\u0006\u0002\u0010\u0012J\u001c\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0011\u001a\u00020\u000bH\u00a6@\u00a2\u0006\u0002\u0010\u0012\u00a8\u0006\u0019"}, d2 = {"Lcom/office/core/filesystem/FileSystemManager;", "", "copy", "Lcom/office/core/common/AppResult;", "", "input", "Ljava/io/InputStream;", "output", "Ljava/io/OutputStream;", "(Ljava/io/InputStream;Ljava/io/OutputStream;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "create", "Landroid/net/Uri;", "name", "", "mimeType", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "open", "uri", "(Landroid/net/Uri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "openForWrite", "persistPermission", "", "queryMimeType", "queryName", "querySize", "filesystem_debug"})
public abstract interface FileSystemManager {
    
    /**
     * Opens a readable stream for the given document URI.
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object open(@org.jetbrains.annotations.NotNull()
    android.net.Uri uri, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.office.core.common.AppResult<? extends java.io.InputStream>> $completion);
    
    /**
     * Opens a writable output stream, truncating the target document.
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object openForWrite(@org.jetbrains.annotations.NotNull()
    android.net.Uri uri, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.office.core.common.AppResult<? extends java.io.OutputStream>> $completion);
    
    /**
     * Creates a new document and returns its persistent URI.
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object create(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String mimeType, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.office.core.common.AppResult<? extends android.net.Uri>> $completion);
    
    /**
     * Persists read/write permission for the URI across reboots.
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object persistPermission(@org.jetbrains.annotations.NotNull()
    android.net.Uri uri, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.office.core.common.AppResult<kotlin.Unit>> $completion);
    
    /**
     * Returns metadata (name, size, mime) for a content URI.
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object queryName(@org.jetbrains.annotations.NotNull()
    android.net.Uri uri, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.office.core.common.AppResult<java.lang.String>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object querySize(@org.jetbrains.annotations.NotNull()
    android.net.Uri uri, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.office.core.common.AppResult<java.lang.Long>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object queryMimeType(@org.jetbrains.annotations.NotNull()
    android.net.Uri uri, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.office.core.common.AppResult<java.lang.String>> $completion);
    
    /**
     * Streams all bytes from [input] into [output] without loading them in memory.
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object copy(@org.jetbrains.annotations.NotNull()
    java.io.InputStream input, @org.jetbrains.annotations.NotNull()
    java.io.OutputStream output, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.office.core.common.AppResult<java.lang.Long>> $completion);
}