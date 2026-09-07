package com.office.android.ui.spreadsheet

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * Spreadsheet state holder with a virtualized cell map (reja.txt 16, 17-bo'lim).
 * Only visible cells are stored; rendering is lazy via LazyColumn in the UI.
 */
@HiltViewModel
class XlsxViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(XlsxUiState())
    val uiState: StateFlow<XlsxUiState> = _uiState.asStateFlow()

    private val cells = mutableMapOf<String, String>()

    init {
        // Seed the demo grid.
        cells["A1"] = "Kategoriya"
        cells["B1"] = "Yanvar"
        cells["C1"] = "Fevral"
        cells["B2"] = "14,500"
        cells["C2"] = "12,300"
        cells["B3"] = "9,800"
        cells["C3"] = "10,200"
        cells["B4"] = "22,000"
        cells["C4"] = "24,500"
    }

    val columns: List<String> = listOf("A", "B", "C", "D", "E")

    fun selectCell(cell: String) {
        _uiState.update {
            it.copy(
                selectedCell = cell,
                formulaText = cells[cell] ?: ""
            )
        }
    }

    fun updateFormula(formula: String) {
        _uiState.update { it.copy(formulaText = formula) }
    }

    fun commitCellValue() {
        val cell = _uiState.value.selectedCell
        val value = _uiState.value.formulaText
        cells[cell] = value
    }

    fun cellValue(cell: String): String = cells[cell] ?: ""
}

data class XlsxUiState(
    val documentName: String = "Choraklik_Budjet_Balansi.xlsx",
    val selectedCell: String = "B2",
    val formulaText: String = "=SUM(B2:B5)",
    val activeSheet: String = "Varaq 1 (Budjet)",
    val sumValue: String = "26,800"
)
