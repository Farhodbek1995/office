package com.office.android.ui.spreadsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.office.android.R
import com.office.core.ui.theme.XlsxGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun XlsxSpreadsheetScreen(
    onNavigateBack: () -> Unit,
    uri: String? = null,
    viewModel: XlsxViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(uiState.documentName, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                        Text("XLSX Engine • Virtual Grid", fontSize = 11.sp, color = XlsxGreen)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    IconButton(onClick = { /* Undo */ }) { Icon(Icons.Default.Undo, contentDescription = null) }
                    IconButton(onClick = { /* Save */ }) { Icon(Icons.Default.Save, contentDescription = null) }
                }
            )
        },
        bottomBar = {
            // Formula & Tab status footer
            Surface(
                modifier = Modifier.fillMaxWidth(),
                tonalElevation = 6.dp,
                color = MaterialTheme.colorScheme.surface
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(uiState.activeSheet, fontWeight = FontWeight.Bold, color = XlsxGreen, fontSize = 12.sp)
                    Text("${stringResource(R.string.sum_label)} ${uiState.sumValue}", fontWeight = FontWeight.SemiBold, fontSize = 12.sp)
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Formula Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp, 32.dp)
                        .background(XlsxGreen.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(uiState.selectedCell, fontWeight = FontWeight.Bold, color = XlsxGreen, fontSize = 12.sp)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text("ƒx", fontWeight = FontWeight.Bold, color = Color.Gray)
                Spacer(modifier = Modifier.width(8.dp))
                TextField(
                    value = uiState.formulaText,
                    onValueChange = { viewModel.updateFormula(it) },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent
                    ),
                    placeholder = { Text(stringResource(R.string.formula_bar_hint), fontSize = 12.sp) }
                )
            }

            Divider(color = MaterialTheme.colorScheme.outlineVariant)

            // Virtualized Table Grid
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .horizontalScroll(rememberScrollState())
            ) {
                LazyColumn(modifier = Modifier.fillMaxHeight()) {
                    // Header Row
                    item {
                        Row {
                            Box(
                                modifier = Modifier
                                    .size(40.dp, 30.dp)
                                    .background(MaterialTheme.colorScheme.surfaceVariant)
                                    .border(0.5.dp, Color.Gray)
                            )
                            viewModel.columns.forEach { col ->
                                Box(
                                    modifier = Modifier
                                        .size(110.dp, 30.dp)
                                        .background(MaterialTheme.colorScheme.surfaceVariant)
                                        .border(0.5.dp, Color.Gray),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(col, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                }
                            }
                        }
                    }

                    // 100 Virtual Rows Demo
                    items(25) { rowIndex ->
                        val rowNum = rowIndex + 1
                        Row {
                            Box(
                                modifier = Modifier
                                    .size(40.dp, 34.dp)
                                    .background(MaterialTheme.colorScheme.surfaceVariant)
                                    .border(0.5.dp, Color.Gray),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(rowNum.toString(), fontSize = 11.sp, color = Color.Gray)
                            }

                            viewModel.columns.forEach { col ->
                                val cellKey = "$col$rowNum"
                                val isSelected = uiState.selectedCell == cellKey
                                Box(
                                    modifier = Modifier
                                        .size(110.dp, 34.dp)
                                        .clickable {
                                            viewModel.selectCell(cellKey)
                                            viewModel.commitCellValue()
                                        }
                                        .background(if (isSelected) XlsxGreen.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surface)
                                        .border(if (isSelected) 1.5.dp else 0.5.dp, if (isSelected) XlsxGreen else Color.Gray.copy(alpha = 0.4f)),
                                    contentAlignment = Alignment.CenterStart
                                ) {
                                    Text(
                                        text = viewModel.cellValue(cellKey),
                                        fontSize = 12.sp,
                                        modifier = Modifier.padding(horizontal = 6.dp),
                                        fontFamily = FontFamily.Monospace
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
