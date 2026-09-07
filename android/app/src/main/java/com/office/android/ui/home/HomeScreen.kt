package com.office.android.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.office.android.R
import com.office.core.common.MimeTypes
import com.office.core.ui.theme.DocxBlue
import com.office.core.ui.theme.PdfRed
import com.office.core.ui.theme.XlsxGreen
import com.office.domain.model.RecentFile

data class RecentFileUi(
    val id: Long,
    val title: String,
    val size: String,
    val time: String,
    val type: String,
    val uri: String
)

@Composable
fun HomeScreen(
    onNavigateToDocx: () -> Unit,
    onNavigateToXlsx: () -> Unit,
    onNavigateToPdf: () -> Unit,
    onOpenSaf: () -> Unit,
    onCreateDocument: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val recentFiles by viewModel.recentFiles.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // Hero Card
        item {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "OFFLINE-FIRST ENGINE",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = stringResource(R.string.dashboard_hero_title),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = stringResource(R.string.dashboard_hero_sub),
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        Button(onClick = onNavigateToDocx) {
                            Icon(Icons.Default.Add, contentDescription = null)
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(stringResource(R.string.new_document))
                        }
                        OutlinedButton(onClick = onOpenSaf) {
                            Icon(Icons.Default.FolderOpen, contentDescription = null)
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(stringResource(R.string.open_saf))
                        }
                    }
                }
            }
        }

        // Quick Creation Action Cards (reja.txt 6-bo'lim)
        item {
            Text(
                text = stringResource(R.string.new_document),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                CreationCard(
                    title = stringResource(R.string.nav_docx),
                    subtitle = stringResource(R.string.card_docx_sub),
                    color = DocxBlue,
                    icon = Icons.Default.Description,
                    modifier = Modifier.weight(1f),
                    onClick = { onCreateDocument(MimeTypes.DOCX) }
                )
                CreationCard(
                    title = stringResource(R.string.nav_xlsx),
                    subtitle = stringResource(R.string.card_xlsx_sub),
                    color = XlsxGreen,
                    icon = Icons.Default.TableChart,
                    modifier = Modifier.weight(1f),
                    onClick = { onCreateDocument(MimeTypes.XLSX) }
                )
                CreationCard(
                    title = stringResource(R.string.nav_pdf),
                    subtitle = stringResource(R.string.card_pdf_sub),
                    color = PdfRed,
                    icon = Icons.Default.PictureAsPdf,
                    modifier = Modifier.weight(1f),
                    onClick = { onCreateDocument(MimeTypes.PDF) }
                )
            }
        }

        // Recent Files (reja.txt 22-bo'lim Room)
        item {
            Text(
                text = stringResource(R.string.recent_files),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        if (recentFiles.isEmpty()) {
            item {
                Text(
                    text = stringResource(R.string.recent_sub),
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }
        } else {
            items(recentFiles, key = { it.id }) { item ->
                RecentFileCard(item = item.toUi(), onClick = {
                    when (item.format) {
                        com.office.engine.api.DocumentFormat.DOC,
                        com.office.engine.api.DocumentFormat.DOCX -> onNavigateToDocx()
                        com.office.engine.api.DocumentFormat.XLS,
                        com.office.engine.api.DocumentFormat.XLSX -> onNavigateToXlsx()
                        com.office.engine.api.DocumentFormat.PDF -> onNavigateToPdf()
                        com.office.engine.api.DocumentFormat.TXT,
                        null -> Unit
                    }
                })
            }
        }
    }
}

private fun RecentFile.toUi(): RecentFileUi {
    val type = when (format) {
        com.office.engine.api.DocumentFormat.DOC,
        com.office.engine.api.DocumentFormat.DOCX -> "DOCX"
        com.office.engine.api.DocumentFormat.XLS,
        com.office.engine.api.DocumentFormat.XLSX -> "XLSX"
        com.office.engine.api.DocumentFormat.PDF -> "PDF"
        com.office.engine.api.DocumentFormat.TXT,
        null -> "FILE"
    }
    return RecentFileUi(
        id = id,
        title = name,
        size = "",
        time = java.text.DateFormat.getDateTimeInstance(
            java.text.DateFormat.SHORT, java.text.DateFormat.SHORT
        ).format(java.util.Date(lastOpened)),
        type = type,
        uri = uri
    )
}

@Composable
fun CreationCard(
    title: String,
    subtitle: String,
    color: Color,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.clickable { onClick() },
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(color.copy(alpha = 0.2f), RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = color)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(title, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text(subtitle, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
        }
    }
}

@Composable
fun RecentFileCard(item: RecentFileUi, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val badgeColor = when (item.type) {
                "DOCX" -> DocxBlue
                "XLSX" -> XlsxGreen
                else -> PdfRed
            }

            Box(
                modifier = Modifier
                    .size(44.dp)
                    .background(badgeColor, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(item.type, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 11.sp)
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(item.title, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(2.dp))
                Text(item.time, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
                Text(item.uri, fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f))
            }

            IconButton(onClick = { /* More options */ }) {
                Icon(Icons.Default.MoreVert, contentDescription = null)
            }
        }
    }
}
