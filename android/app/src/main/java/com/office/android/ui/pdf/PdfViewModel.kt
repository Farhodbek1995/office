package com.office.android.ui.pdf

import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.office.core.common.AppResult
import com.office.domain.repository.DocumentRepository
import com.office.engine.api.DocumentFormat
import com.office.engine.api.DocumentSource
import com.office.engine.pdf.PdfDocumentSession
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * PDF viewer state holder. Opens a document URI through the document repository,
 * renders the current page via the PDF engine, and tracks annotation state
 * (reja.txt 13, 18-bo'lim).
 */
@HiltViewModel
class PdfViewModel @Inject constructor(
    private val documentRepository: DocumentRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(PdfUiState())
    val uiState: StateFlow<PdfUiState> = _uiState.asStateFlow()

    private var session: PdfDocumentSession? = null

    fun openPdf(uri: Uri) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            val result = documentRepository.open(
                DocumentSource.UriSource(uri),
                DocumentFormat.PDF
            )
            when (result) {
                is AppResult.Success -> {
                    val s = result.data
                    if (s is PdfDocumentSession) {
                        session = s
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                pageCount = s.pageCount,
                                currentPage = 1,
                                documentName = s.name
                            )
                        }
                        renderCurrentPage()
                    } else {
                        _uiState.update {
                            it.copy(isLoading = false, errorMessage = "Not a PDF session")
                        }
                    }
                }
                is AppResult.Failure -> {
                    _uiState.update {
                        it.copy(isLoading = false, errorMessage = result.error.toString())
                    }
                }
            }
        }
    }

    fun nextPage() {
        val next = (_uiState.value.currentPage + 1).coerceAtMost(_uiState.value.pageCount)
        _uiState.update { it.copy(currentPage = next) }
        renderCurrentPage()
    }

    fun previousPage() {
        val prev = (_uiState.value.currentPage - 1).coerceAtLeast(1)
        _uiState.update { it.copy(currentPage = prev) }
        renderCurrentPage()
    }

    fun setTool(tool: String) = _uiState.update { it.copy(selectedTool = tool) }

    fun sign() = _uiState.update { it.copy(isSigned = true) }

    private fun renderCurrentPage() {
        val current = _uiState.value
        val active = session ?: return
        viewModelScope.launch {
            val bitmap = active.renderPage(
                pageIndex = current.currentPage,
                targetWidth = 1200,
                targetHeight = 1600
            )
            if (bitmap != null) {
                _uiState.update { it.copy(pageBitmap = bitmap) }
            }
        }
    }

    override fun onCleared() {
        session?.let { s -> viewModelScope.launch { documentRepository.close(s) } }
        super.onCleared()
    }
}

data class PdfUiState(
    val documentName: String = "Litsenziya_Shartnomasi.pdf",
    val pageCount: Int = 1,
    val currentPage: Int = 1,
    val pageBitmap: Bitmap? = null,
    val selectedTool: String = "select",
    val isSigned: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
