package com.office.android.ui.editor;

/**
 * Editor state holder. The undo/redo stack is centralized (reja.txt 19-bo'lim).
 * Autosave with debounce is planned via settings.autosaveDelayMs (reja.txt 20-bo'lim).
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0016\u001a\u00020\u0017J\u0006\u0010\u0018\u001a\u00020\u0017J\u0006\u0010\u0019\u001a\u00020\u0017J\u0006\u0010\u001a\u001a\u00020\u0017J\u0006\u0010\u001b\u001a\u00020\u0017J\u0006\u0010\u001c\u001a\u00020\u0017J\u000e\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u0010R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/office/android/ui/editor/DocxEditorViewModel;", "Landroidx/lifecycle/ViewModel;", "saveDocument", "Lcom/office/domain/usecase/SaveDocumentUseCase;", "(Lcom/office/domain/usecase/SaveDocumentUseCase;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/office/android/ui/editor/DocxEditorUiState;", "activeSession", "Lcom/office/engine/api/DocumentSession;", "getActiveSession", "()Lcom/office/engine/api/DocumentSession;", "setActiveSession", "(Lcom/office/engine/api/DocumentSession;)V", "redoStack", "Lkotlin/collections/ArrayDeque;", "", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "undoStack", "redo", "", "save", "toggleBold", "toggleItalic", "toggleUnderline", "undo", "updateContent", "newContent", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class DocxEditorViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.office.domain.usecase.SaveDocumentUseCase saveDocument = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.office.android.ui.editor.DocxEditorUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.office.android.ui.editor.DocxEditorUiState> uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.collections.ArrayDeque<java.lang.String> undoStack = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.collections.ArrayDeque<java.lang.String> redoStack = null;
    @org.jetbrains.annotations.Nullable()
    private com.office.engine.api.DocumentSession activeSession;
    
    @javax.inject.Inject()
    public DocxEditorViewModel(@org.jetbrains.annotations.NotNull()
    com.office.domain.usecase.SaveDocumentUseCase saveDocument) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.office.android.ui.editor.DocxEditorUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.office.engine.api.DocumentSession getActiveSession() {
        return null;
    }
    
    public final void setActiveSession(@org.jetbrains.annotations.Nullable()
    com.office.engine.api.DocumentSession p0) {
    }
    
    public final void updateContent(@org.jetbrains.annotations.NotNull()
    java.lang.String newContent) {
    }
    
    public final void toggleBold() {
    }
    
    public final void toggleItalic() {
    }
    
    public final void toggleUnderline() {
    }
    
    public final void undo() {
    }
    
    public final void redo() {
    }
    
    public final void save() {
    }
}