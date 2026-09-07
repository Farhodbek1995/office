package com.office.android.ui.pdf;

/**
 * PDF viewer state holder. Opens a document URI through the document repository,
 * renders the current page via the PDF engine, and tracks annotation state
 * (reja.txt 13, 18-bo'lim).
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u000e\u001a\u00020\u000fJ\b\u0010\u0010\u001a\u00020\u000fH\u0014J\u000e\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\u000fJ\b\u0010\u0015\u001a\u00020\u000fH\u0002J\u000e\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u0018J\u0006\u0010\u0019\u001a\u00020\u000fR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u001a"}, d2 = {"Lcom/office/android/ui/pdf/PdfViewModel;", "Landroidx/lifecycle/ViewModel;", "documentRepository", "Lcom/office/domain/repository/DocumentRepository;", "(Lcom/office/domain/repository/DocumentRepository;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/office/android/ui/pdf/PdfUiState;", "session", "Lcom/office/engine/pdf/PdfDocumentSession;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "nextPage", "", "onCleared", "openPdf", "uri", "Landroid/net/Uri;", "previousPage", "renderCurrentPage", "setTool", "tool", "", "sign", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class PdfViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.office.domain.repository.DocumentRepository documentRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.office.android.ui.pdf.PdfUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.office.android.ui.pdf.PdfUiState> uiState = null;
    @org.jetbrains.annotations.Nullable()
    private com.office.engine.pdf.PdfDocumentSession session;
    
    @javax.inject.Inject()
    public PdfViewModel(@org.jetbrains.annotations.NotNull()
    com.office.domain.repository.DocumentRepository documentRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.office.android.ui.pdf.PdfUiState> getUiState() {
        return null;
    }
    
    public final void openPdf(@org.jetbrains.annotations.NotNull()
    android.net.Uri uri) {
    }
    
    public final void nextPage() {
    }
    
    public final void previousPage() {
    }
    
    public final void setTool(@org.jetbrains.annotations.NotNull()
    java.lang.String tool) {
    }
    
    public final void sign() {
    }
    
    private final void renderCurrentPage() {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
}