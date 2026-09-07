package com.office.android.ui.pdf

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.office.android.R
import com.office.core.ui.theme.PdfRed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PdfViewerScreen(
    onNavigateBack: () -> Unit,
    uri: String? = null,
    viewModel: PdfViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val lines = remember { mutableStateListOf<List<Offset>>() }
    var currentLine by remember { mutableStateOf(listOf<Offset>()) }

    // Open the document URI once it is available.
    LaunchedEffect(uri) {
        uri?.let { viewModel.openPdf(Uri.parse(it)) }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(uiState.documentName, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                        Text(
                            "PDFium Engine • Sahifa ${uiState.currentPage} / ${uiState.pageCount}",
                            fontSize = 11.sp,
                            color = PdfRed
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.previousPage() }) {
                        Icon(Icons.Default.ChevronLeft, contentDescription = null)
                    }
                    IconButton(onClick = { viewModel.nextPage() }) {
                        Icon(Icons.Default.ChevronRight, contentDescription = null)
                    }
                    IconButton(onClick = { /* Share */ }) { Icon(Icons.Default.Share, contentDescription = null) }
                }
            )
        },
        bottomBar = {
            // PDF Annotation toolbar
            Surface(
                modifier = Modifier.fillMaxWidth(),
                tonalElevation = 6.dp,
                color = MaterialTheme.colorScheme.surface
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FilledIconToggleButton(
                        checked = uiState.selectedTool == "select",
                        onCheckedChange = { viewModel.setTool("select") }
                    ) {
                        Icon(Icons.Default.NearMe, contentDescription = stringResource(R.string.pdf_select))
                    }
                    FilledIconToggleButton(
                        checked = uiState.selectedTool == "highlight",
                        onCheckedChange = { viewModel.setTool("highlight") }
                    ) {
                        Icon(Icons.Default.BorderColor, contentDescription = stringResource(R.string.pdf_highlight))
                    }
                    FilledIconToggleButton(
                        checked = uiState.selectedTool == "pen",
                        onCheckedChange = { viewModel.setTool("pen") }
                    ) {
                        Icon(Icons.Default.Edit, contentDescription = stringResource(R.string.pdf_pen))
                    }
                    Button(
                        onClick = { viewModel.sign() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (uiState.isSigned) Color(0xFF16A34A) else PdfRed
                        )
                    ) {
                        Icon(Icons.Default.CheckCircle, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(if (uiState.isSigned) "Imzolandi" else stringResource(R.string.pdf_sign), fontSize = 12.sp)
                    }
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFF1E293B))
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            when {
                uiState.isLoading -> {
                    CircularProgressIndicator(color = PdfRed)
                }
                uiState.errorMessage != null -> {
                    Text(uiState.errorMessage.orEmpty(), color = Color.White)
                }
                else -> {
                    // PDF Sheet Document Paper
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .defaultMinSize(minHeight = 560.dp),
                        shape = RoundedCornerShape(4.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            uiState.pageBitmap?.let { bitmap ->
                                Image(
                                    bitmap = bitmap.asImageBitmap(),
                                    contentDescription = "Page",
                                    modifier = Modifier.fillMaxSize()
                                )
                            }

                            // Touch Drawing Annotation Layer Canvas
                            Canvas(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .pointerInput(uiState.selectedTool) {
                                        if (uiState.selectedTool != "select") {
                                            detectDragGestures(
                                                onDragStart = { offset ->
                                                    currentLine = listOf(offset)
                                                },
                                                onDrag = { change, _ ->
                                                    currentLine = currentLine + change.position
                                                },
                                                onDragEnd = {
                                                    lines.add(currentLine)
                                                    currentLine = listOf()
                                                }
                                            )
                                        }
                                    }
                            ) {
                                val strokeColor =
                                    if (uiState.selectedTool == "highlight") Color(0x66FEF08A) else Color.Red
                                val strokeWidth =
                                    if (uiState.selectedTool == "highlight") 28f else 5f

                                lines.forEach { line ->
                                    for (i in 0 until line.size - 1) {
                                        drawLine(
                                            color = strokeColor,
                                            start = line[i],
                                            end = line[i + 1],
                                            strokeWidth = strokeWidth,
                                            cap = StrokeCap.Round
                                        )
                                    }
                                }

                                for (i in 0 until currentLine.size - 1) {
                                    drawLine(
                                        color = strokeColor,
                                        start = currentLine[i],
                                        end = currentLine[i + 1],
                                        strokeWidth = strokeWidth,
                                        cap = StrokeCap.Round
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
