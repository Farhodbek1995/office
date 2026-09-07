package com.office.android.ui.editor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.office.core.common.AppResult
import com.office.domain.usecase.SaveDocumentUseCase
import com.office.engine.api.DocumentSession
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Editor state holder. The undo/redo stack is centralized (reja.txt 19-bo'lim).
 * Autosave with debounce is planned via settings.autosaveDelayMs (reja.txt 20-bo'lim).
 */
@HiltViewModel
class DocxEditorViewModel @Inject constructor(
    private val saveDocument: SaveDocumentUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(DocxEditorUiState())
    val uiState: StateFlow<DocxEditorUiState> = _uiState.asStateFlow()

    private val undoStack = ArrayDeque<String>()
    private val redoStack = ArrayDeque<String>()

    var activeSession: DocumentSession? = null

    fun updateContent(newContent: String) {
        val current = _uiState.value.content
        if (current == newContent) return
        undoStack.addLast(current)
        redoStack.clear()
        // Cap the undo stack to avoid unbounded memory growth (Rule 4).
        while (undoStack.size > 100) undoStack.removeFirst()
        _uiState.update {
            it.copy(content = newContent, isDirty = true, savedStatus = SavedStatus.DIRTY)
        }
    }

    fun toggleBold() = _uiState.update { it.copy(isBold = !it.isBold) }

    fun toggleItalic() = _uiState.update { it.copy(isItalic = !it.isItalic) }

    fun toggleUnderline() = _uiState.update { it.copy(isUnderline = !it.isUnderline) }

    fun undo() {
        if (undoStack.isEmpty()) return
        val previous = undoStack.removeLast()
        redoStack.addLast(_uiState.value.content)
        _uiState.update { it.copy(content = previous, isDirty = true) }
    }

    fun redo() {
        if (redoStack.isEmpty()) return
        val next = redoStack.removeLast()
        undoStack.addLast(_uiState.value.content)
        _uiState.update { it.copy(content = next, isDirty = true) }
    }

    fun save() {
        viewModelScope.launch {
            _uiState.update { it.copy(savedStatus = SavedStatus.SAVING) }
            val result = activeSession?.let { saveDocument(it, null) } ?: AppResult.success(Unit)
            _uiState.update {
                when (result) {
                    is AppResult.Success -> it.copy(isDirty = false, savedStatus = SavedStatus.SAVED)
                    is AppResult.Failure -> it.copy(savedStatus = SavedStatus.ERROR)
                }
            }
        }
    }
}

data class DocxEditorUiState(
    val documentName: String = "Kompaniya_Hisoboti_2026.docx",
    val content: String = "O'zbekiston Respublikasi Innovatsion Loyiha Rejasi\n\n1. Arxitektura Asoslari va LibreOfficeKit\nMazkur hujjat reja.txt da ko'rsatilgan Clean Architecture va Engine mustaqilligi prinsiplari asosida render qilinmoqda. Kotlin JNI orqali LibreOfficeKit bilan bog'langan holatda 100% oflayn formatda ishlaydi.",
    val isBold: Boolean = false,
    val isItalic: Boolean = false,
    val isUnderline: Boolean = false,
    val isDirty: Boolean = false,
    val savedStatus: SavedStatus = SavedStatus.SAVED
)

enum class SavedStatus { SAVED, SAVING, DIRTY, ERROR }
