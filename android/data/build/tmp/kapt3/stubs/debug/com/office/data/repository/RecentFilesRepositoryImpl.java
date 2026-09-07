package com.office.data.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0096@\u00a2\u0006\u0002\u0010\nJ\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0096@\u00a2\u0006\u0002\u0010\fJ\"\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u000e0\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0096@\u00a2\u0006\u0002\u0010\u0011J\u001c\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u000e0\u00132\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u001c\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0015\u001a\u00020\u0016H\u0096@\u00a2\u0006\u0002\u0010\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/office/data/repository/RecentFilesRepositoryImpl;", "Lcom/office/domain/repository/RecentFilesRepository;", "dao", "Lcom/office/data/database/dao/RecentFileDao;", "(Lcom/office/data/database/dao/RecentFileDao;)V", "addOrUpdateRecentFile", "Lcom/office/core/common/AppResult;", "", "file", "Lcom/office/domain/model/RecentFile;", "(Lcom/office/domain/model/RecentFile;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clearAll", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRecentFiles", "", "limit", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeRecentFiles", "Lkotlinx/coroutines/flow/Flow;", "removeRecentFile", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "data_debug"})
public final class RecentFilesRepositoryImpl implements com.office.domain.repository.RecentFilesRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.office.data.database.dao.RecentFileDao dao = null;
    
    @javax.inject.Inject()
    public RecentFilesRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.office.data.database.dao.RecentFileDao dao) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.office.domain.model.RecentFile>> observeRecentFiles(int limit) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getRecentFiles(int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.office.core.common.AppResult<? extends java.util.List<com.office.domain.model.RecentFile>>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object addOrUpdateRecentFile(@org.jetbrains.annotations.NotNull()
    com.office.domain.model.RecentFile file, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.office.core.common.AppResult<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object removeRecentFile(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.office.core.common.AppResult<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object clearAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.office.core.common.AppResult<kotlin.Unit>> $completion) {
        return null;
    }
}