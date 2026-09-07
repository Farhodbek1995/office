package com.office.android

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.office.android.ui.editor.DocxEditorScreen
import com.office.android.ui.home.HomeScreen
import com.office.android.ui.home.HomeViewModel
import com.office.android.ui.pdf.PdfViewerScreen
import com.office.android.ui.spreadsheet.XlsxSpreadsheetScreen
import com.office.core.common.MimeTypes
import com.office.core.ui.theme.DarkColorScheme
import com.office.core.ui.theme.LightColorScheme
import com.office.domain.model.ThemeMode
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val homeViewModel: HomeViewModel = hiltViewModel()
            val settings by homeViewModel.settings.collectAsState()
            val colorScheme = when (settings.theme) {
                ThemeMode.LIGHT -> LightColorScheme
                ThemeMode.DARK -> DarkColorScheme
                ThemeMode.SYSTEM -> if (isSystemInDarkTheme()) DarkColorScheme else LightColorScheme
            }
            MaterialTheme(colorScheme = colorScheme) {
                OfficeAppRoot()
            }
        }
    }
}

@Composable
fun OfficeAppRoot() {
    val navController = rememberNavController()
    var currentRoute by remember { mutableStateOf("home") }

    // SAF launcher: ACTION_OPEN_DOCUMENT (reja.txt 7-bo'lim)
    val openDocumentLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        uri?.let { opened ->
            val mime = MimeTypes.fromFileName(opened.lastPathSegment)
            val destination = routeForMime(mime)
            if (destination != null) {
                navController.navigate("$destination?uri=${Uri.encode(opened.toString())}")
            }
        }
    }

    // SAF launcher: ACTION_CREATE_DOCUMENT (reja.txt 7-bo'lim)
    val createDocumentLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.CreateDocument()
    ) { uri: Uri? ->
        uri?.let { created ->
            val mime = MimeTypes.fromFileName(created.lastPathSegment)
            val destination = routeForMime(mime)
            if (destination != null) {
                navController.navigate("$destination?uri=${Uri.encode(created.toString())}")
            }
        }
    }

    Scaffold(
        bottomBar = {
            if (currentRoute == "home") {
                NavigationBar {
                    NavigationBarItem(
                        selected = true,
                        onClick = { },
                        icon = { Icon(Icons.Default.Home, contentDescription = null) },
                        label = { Text(stringResource(R.string.nav_home)) }
                    )
                    NavigationBarItem(
                        selected = false,
                        onClick = { navController.navigate("docx") },
                        icon = { Icon(Icons.Default.Description, contentDescription = null) },
                        label = { Text(stringResource(R.string.nav_docx)) }
                    )
                    NavigationBarItem(
                        selected = false,
                        onClick = { navController.navigate("xlsx") },
                        icon = { Icon(Icons.Default.TableChart, contentDescription = null) },
                        label = { Text(stringResource(R.string.nav_xlsx)) }
                    )
                    NavigationBarItem(
                        selected = false,
                        onClick = { navController.navigate("pdf") },
                        icon = { Icon(Icons.Default.PictureAsPdf, contentDescription = null) },
                        label = { Text(stringResource(R.string.nav_pdf)) }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("home") {
                currentRoute = "home"
                HomeScreen(
                    onNavigateToDocx = { navController.navigate("docx") },
                    onNavigateToXlsx = { navController.navigate("xlsx") },
                    onNavigateToPdf = { navController.navigate("pdf") },
                    onOpenSaf = {
                        openDocumentLauncher.launch(
                            arrayOf(MimeTypes.DOCX, MimeTypes.DOC, MimeTypes.XLSX, MimeTypes.XLS, MimeTypes.PDF)
                        )
                    },
                    onCreateDocument = { mime -> createDocumentLauncher.launch(defaultNameForMime(mime)) }
                )
            }
            composable(
                route = "docx?uri={uri}",
                arguments = listOf(navArgument("uri") { type = NavType.StringType; nullable = true; defaultValue = null })
            ) { entry ->
                currentRoute = "docx"
                DocxEditorScreen(
                    onNavigateBack = { navController.popBackStack() },
                    uri = entry.arguments?.getString("uri")
                )
            }
            composable(
                route = "xlsx?uri={uri}",
                arguments = listOf(navArgument("uri") { type = NavType.StringType; nullable = true; defaultValue = null })
            ) { entry ->
                currentRoute = "xlsx"
                XlsxSpreadsheetScreen(
                    onNavigateBack = { navController.popBackStack() },
                    uri = entry.arguments?.getString("uri")
                )
            }
            composable(
                route = "pdf?uri={uri}",
                arguments = listOf(navArgument("uri") { type = NavType.StringType; nullable = true; defaultValue = null })
            ) { entry ->
                currentRoute = "pdf"
                PdfViewerScreen(
                    onNavigateBack = { navController.popBackStack() },
                    uri = entry.arguments?.getString("uri")
                )
            }
        }
    }
}

private fun routeForMime(mime: String?): String? = when (mime) {
    MimeTypes.DOCX, MimeTypes.DOC -> "docx"
    MimeTypes.XLSX, MimeTypes.XLS -> "xlsx"
    MimeTypes.PDF -> "pdf"
    else -> null
}

private fun defaultNameForMime(mime: String): String = when (mime) {
    MimeTypes.XLSX -> "Yangi_Jadval.xlsx"
    MimeTypes.PDF -> "Yangi_Hujjat.pdf"
    else -> "Yangi_Hujjat.docx"
}
