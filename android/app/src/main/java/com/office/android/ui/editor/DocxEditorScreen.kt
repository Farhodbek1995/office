package com.office.android.ui.editor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.office.android.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DocxEditorScreen(
    onNavigateBack: () -> Unit,
    uri: String? = null,
    viewModel: DocxEditorViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(uiState.documentName, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                        Text(
                            text = when (uiState.savedStatus) {
                                SavedStatus.SAVED -> stringResource(R.string.saved_status)
                                SavedStatus.SAVING -> stringResource(R.string.saving_status)
                                SavedStatus.DIRTY -> stringResource(R.string.saved_status)
                                SavedStatus.ERROR -> "Error"
                            },
                            fontSize = 11.sp,
                            color = Color(0xFF10B981)
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.undo() }) { Icon(Icons.Default.Undo, contentDescription = null) }
                    IconButton(onClick = { viewModel.redo() }) { Icon(Icons.Default.Redo, contentDescription = null) }
                    IconButton(onClick = { viewModel.save() }) { Icon(Icons.Default.Save, contentDescription = null) }
                }
            )
        },
        bottomBar = {
            // Mobile formatting bottom bar (WPS Style)
            Surface(
                modifier = Modifier.fillMaxWidth(),
                tonalElevation = 4.dp,
                color = MaterialTheme.colorScheme.surface
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FilledIconToggleButton(checked = uiState.isBold, onCheckedChange = { viewModel.toggleBold() }) {
                        Icon(Icons.Default.FormatBold, contentDescription = "Bold")
                    }
                    FilledIconToggleButton(checked = uiState.isItalic, onCheckedChange = { viewModel.toggleItalic() }) {
                        Icon(Icons.Default.FormatItalic, contentDescription = "Italic")
                    }
                    FilledIconToggleButton(checked = uiState.isUnderline, onCheckedChange = { viewModel.toggleUnderline() }) {
                        Icon(Icons.Default.FormatUnderlined, contentDescription = "Underline")
                    }
                    IconButton(onClick = { /* Align Left */ }) {
                        Icon(Icons.Default.FormatAlignLeft, contentDescription = "Align Left")
                    }
                    IconButton(onClick = { /* Insert Table */ }) {
                        Icon(Icons.Default.TableChart, contentDescription = "Table")
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Simulated A4 Canvas Page
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 650.dp),
                shape = RoundedCornerShape(4.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                OutlinedTextField(
                    value = uiState.content,
                    onValueChange = { viewModel.updateContent(it) },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color(0xFF1E293B),
                        unfocusedTextColor = Color(0xFF1E293B),
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    )
                )
            }
        }
    }
}
