package com.office.android.ui.spreadsheet;

/**
 * Spreadsheet state holder with a virtualized cell map (reja.txt 16, 17-bo'lim).
 * Only visible cells are stored; rendering is lazy via LazyColumn in the UI.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\bJ\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\bJ\u000e\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\bR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006\u0018"}, d2 = {"Lcom/office/android/ui/spreadsheet/XlsxViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/office/android/ui/spreadsheet/XlsxUiState;", "cells", "", "", "columns", "", "getColumns", "()Ljava/util/List;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "cellValue", "cell", "commitCellValue", "", "selectCell", "updateFormula", "formula", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class XlsxViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.office.android.ui.spreadsheet.XlsxUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.office.android.ui.spreadsheet.XlsxUiState> uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Map<java.lang.String, java.lang.String> cells = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> columns = null;
    
    @javax.inject.Inject()
    public XlsxViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.office.android.ui.spreadsheet.XlsxUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getColumns() {
        return null;
    }
    
    public final void selectCell(@org.jetbrains.annotations.NotNull()
    java.lang.String cell) {
    }
    
    public final void updateFormula(@org.jetbrains.annotations.NotNull()
    java.lang.String formula) {
    }
    
    public final void commitCellValue() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String cellValue(@org.jetbrains.annotations.NotNull()
    java.lang.String cell) {
        return null;
    }
}