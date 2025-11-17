package ca.cem.composeerreurs

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import ca.cem.composeerreurs.ui.theme.ComposeErreursTheme
import kotlinx.coroutines.launch
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeErreursTheme {
                EcranPrincipal()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EcranPrincipal() {
    val etatSnackbar = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val contexte = LocalContext.current

    val etatDefilement = rememberScrollState()

    var premierTexte by remember { mutableStateOf("") }
    var premiereErreur by remember { mutableStateOf<String?>(null) }

    var texteNom by remember { mutableStateOf("") }
    var erreurNom by remember { mutableStateOf<String?>(null) }

    var texteMotDePasse by remember { mutableStateOf("") }
    var erreurMotDePasse by remember { mutableStateOf<String?>(null) }

    val demandeFocusPremier = remember { FocusRequester() }
    val demandeFocusMotDePasse = remember { FocusRequester() }

    var afficherDialogue by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = etatSnackbar) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                scope.launch {
                    val resultat = etatSnackbar.showSnackbar(
                        message = contexte.getString(R.string.no_network),
                        actionLabel = contexte.getString(R.string.retry),
                        duration = SnackbarDuration.Long
                    )
                    if (resultat == SnackbarResult.ActionPerformed) {
                        Toast.makeText(contexte, "Nouvel essai", Toast.LENGTH_SHORT).show()
                    }
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.Send,
                    contentDescription = "envoyer"
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(etatDefilement)
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = premierTexte,
                onValueChange = {
                    premierTexte = it
                    if (premiereErreur != null) premiereErreur = null
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(demandeFocusPremier),
                label = { Text(text = stringResource(id = R.string.enter_firstname)) },
                isError = premiereErreur != null,
                singleLine = true
            )
            if (premiereErreur != null) {
                Text(
                    text = premiereErreur!!,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .background(Color(0xFFaabbcc))
            )

            OutlinedTextField(
                value = texteMotDePasse,
                onValueChange = {
                    texteMotDePasse = it
                    if (erreurMotDePasse != null) erreurMotDePasse = null
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(demandeFocusMotDePasse),
                label = { Text(text = "Mot de passe") },
                visualTransformation = PasswordVisualTransformation(),
                isError = erreurMotDePasse != null,
                singleLine = true
            )
            if (erreurMotDePasse != null) {
                Text(
                    text = erreurMotDePasse!!,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .background(Color(0xFFaabbcc))
                    .padding(15.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.instructions),
                    modifier = Modifier.fillMaxSize(),
                    textAlign = TextAlign.Start
                )
            }

            OutlinedTextField(
                value = texteNom,
                onValueChange = {
                    texteNom = it
                    if (erreurNom != null) erreurNom = null
                },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = stringResource(id = R.string.type_your_name)) },
                isError = erreurNom != null,
                singleLine = true
            )
            if (erreurNom != null) {
                Text(
                    text = erreurNom!!,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .background(Color(0xFFaabbcc))
            )

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                Button(onClick = {
                    // Afficher message sur champ: définir erreur sur mot de passe et focus + défilement
                    erreurMotDePasse = contexte.getString(R.string.error_password)
                    scope.launch {
                        demandeFocusMotDePasse.requestFocus()
                        // Défilement vers la zone où se trouve le mot de passe
                        etatDefilement.animateScrollTo(etatDefilement.maxValue)
                    }
                }) {
                    Text(text = stringResource(id = R.string.send))
                }
                Button(onClick = {
                    // Afficher snackbar: définir erreur sur premier champ et afficher snackbar avec action
                    premiereErreur = contexte.getString(R.string.error_example)
                    scope.launch {
                        val resultat = etatSnackbar.showSnackbar(
                            message = contexte.getString(R.string.snack_message),
                            actionLabel = contexte.getString(R.string.go_there),
                            duration = SnackbarDuration.Long
                        )
                        if (resultat == SnackbarResult.ActionPerformed) {
                            demandeFocusPremier.requestFocus()
                            scope.launch { etatDefilement.animateScrollTo(0) }
                        }
                    }
                }) {
                    Text(text = "E 2")
                }
                Button(onClick = {
                    // Afficher dialogue
                    afficherDialogue = true
                }) {
                    Text(text = "E 3")
                }
            }

            Spacer(modifier = Modifier.height(40.dp))
        }

        if (afficherDialogue) {
            AlertDialog(
                onDismissRequest = { afficherDialogue = false },
                title = { Text(text = stringResource(id = R.string.no_network)) },
                confirmButton = {
                    TextButton(onClick = { afficherDialogue = false }) {
                        Text(text = stringResource(id = R.string.ok))
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        Toast.makeText(contexte, "Nouvel essai", Toast.LENGTH_SHORT).show()
                        afficherDialogue = false
                    }) {
                        Text(text = stringResource(id = R.string.retry))
                    }
                },
                properties = DialogProperties()
            )
        }
    }
}