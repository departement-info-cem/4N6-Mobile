@file:OptIn(ExperimentalMaterial3Api::class)

package ca.cem.composescaffold

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ca.cem.composescaffold.ui.theme.ComposeScaffoldTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeScaffoldTheme {
                val drawerState = rememberDrawerState(DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                val snackbarHostState = remember { SnackbarHostState() }

                // états pour le menu déroulant
                var menuExpanded by remember { mutableStateOf(false) }

                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        MonTiroirDeNavigation(
                            onAccueil = { scope.launch { drawerState.close() } },
                            onParametres = { scope.launch { drawerState.close() } }
                        )
                    }
                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        topBar = {
                            MaBarreAuTop(
                                menuExpanded = menuExpanded,
                                setMenuExpanded = { menuExpanded = it },
                                onMenuClick = { scope.launch { drawerState.open() } },
                                snackbarHostState = snackbarHostState
                            )
                        },
                        floatingActionButton = {
                            FloatingActionButton(
                                onClick = {
                                    scope.launch {
                                        snackbarHostState.showSnackbar(
                                            message = "FAB cliqué",
                                            actionLabel = "Annuler"
                                        )
                                    }
                                }
                            ) {
                                Icon(Icons.Filled.Add, contentDescription = "Ajouter")
                            }
                        },
                        snackbarHost = { SnackbarHost(snackbarHostState) }
                    ) { innerPadding ->
                        MaZoneCentrale(modifier = Modifier.padding(innerPadding))
                    }
                }
            }
        }
    }
}

@Composable
fun ScrollableColorList(modifier: Modifier = Modifier) {
    val colors = listOf(
        Color(0xFFB71C1C), // red
        Color(0xFF880E4F), // pink
        Color(0xFF4A148C), // purple
        Color(0xFF311B92), // deep purple
        Color(0xFF1A237E), // indigo
        Color(0xFF0D47A1), // blue
        Color(0xFF1565C0), // blue lighten
        Color(0xFF0D47A1),
        Color(0xFF00695C), // teal
        Color(0xFF004D40), // teal dark
        Color(0xFF1B5E20), // green
        Color(0xFF33691E),
        Color(0xFF827717), // lime
        Color(0xFFF57F17), // yellow
        Color(0xFFFF6F00), // orange
        Color(0xFFE65100), // deep orange
        Color(0xFFBF360C), // brown-ish
        Color(0xFF3E2723),
        Color(0xFF263238), // blue grey
        Color(0xFF212121)  // grey/black
    )

    val itemsList = (1..20).toList()

    LazyColumn(modifier = modifier) {
        items(itemsList) { i ->
            if (i == 3) {
                // Item 3 remplacé par une LazyRow horizontale de 20 éléments 200x200
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                ) {
                    items((1..20).toList()) { j ->
                        Box(
                            modifier = Modifier
                                .width(200.dp)
                                .height(200.dp)
                                .background(colors[(j - 1) % colors.size]),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "Item 3 - $j", color = Color.White)
                        }
                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(colors[(i - 1) % colors.size]),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Item $i", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun MaBarreAuTop(
    menuExpanded: Boolean,
    setMenuExpanded: (Boolean) -> Unit,
    onMenuClick: () -> Unit,
    snackbarHostState: SnackbarHostState,
    title: String = "Mon titre"
) {
    val scope = rememberCoroutineScope()
    var showDeleteDialog by remember { mutableStateOf(false) }
    var showEditDialog by remember { mutableStateOf(false) }
    var showOtherDialog by remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = onMenuClick) {
                Icon(Icons.Filled.Menu, contentDescription = "Menu")
            }
        },
        actions = {
            IconButton(onClick = {
                scope.launch {
                    val result = snackbarHostState.showSnackbar(
                        message = "Action de partage",
                        actionLabel = "Annuler"
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        snackbarHostState.showSnackbar("Action annulée")
                    }
                }
            }) {
                Icon(Icons.Filled.Share, contentDescription = "Partager")
            }

            IconButton(onClick = { setMenuExpanded(true) }) {
                Icon(Icons.Filled.MoreVert, contentDescription = "Plus d'actions")
            }

            DropdownMenu(expanded = menuExpanded, onDismissRequest = { setMenuExpanded(false) }) {
                DropdownMenuItem(
                    text = { Text("Supprimer") },
                    leadingIcon = { Icon(Icons.Filled.Delete, contentDescription = "Supprimer") },
                    onClick = {
                        setMenuExpanded(false)
                        showDeleteDialog = true
                    }
                )

                DropdownMenuItem(
                    text = { Text("Modifier") },
                    leadingIcon = { Icon(Icons.Filled.Edit, contentDescription = "Modifier") },
                    onClick = {
                        setMenuExpanded(false)
                        showEditDialog = true
                    }
                )

                DropdownMenuItem(
                    text = { Text("Info") },
                    leadingIcon = { Icon(Icons.Filled.Info, contentDescription = "Info") },
                    onClick = {
                        setMenuExpanded(false)
                        showOtherDialog = true
                    }
                )
            }
        }
    )

    // Dialogues gérés localement
    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Supprimer") },
            text = { Text("Voulez-vous vraiment supprimer cet élément ?") },
            confirmButton = {
                TextButton(onClick = { showDeleteDialog = false }) { Text("Supprimer") }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) { Text("Annuler") }
            }
        )
    }

    if (showEditDialog) {
        AlertDialog(
            onDismissRequest = { showEditDialog = false },
            title = { Text("Modifier") },
            text = { Text("Ouvrir l'éditeur pour cet élément ?") },
            confirmButton = {
                TextButton(onClick = { showEditDialog = false }) { Text("OK") }
            },
            dismissButton = {
                TextButton(onClick = { showEditDialog = false }) { Text("Annuler") }
            }
        )
    }

    if (showOtherDialog) {
        AlertDialog(
            onDismissRequest = { showOtherDialog = false },
            title = { Text("Information") },
            text = { Text("Informations supplémentaires sur cet élément.") },
            confirmButton = {
                TextButton(onClick = { showOtherDialog = false }) { Text("OK") }
            }
        )
    }
}

@Composable
fun MonTiroirDeNavigation(onAccueil: () -> Unit, onParametres: () -> Unit) {
    ModalDrawerSheet {
        Text("Navigation", modifier = Modifier.padding(16.dp))

        NavigationDrawerItem(
            label = { Text("Accueil") },
            selected = true,
            onClick = onAccueil
        )

        NavigationDrawerItem(
            label = { Text("Paramètres") },
            selected = false,
            onClick = onParametres
        )
    }
}

@Composable
fun MaZoneCentrale(modifier: Modifier = Modifier) {
    ScrollableColorList(modifier = modifier)
}
