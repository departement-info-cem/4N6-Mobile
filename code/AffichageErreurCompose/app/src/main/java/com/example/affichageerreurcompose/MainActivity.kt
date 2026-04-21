package com.example.affichageerreurcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.affichageerreurcompose.ui.theme.AffichageErreurComposeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AffichageErreurComposeTheme {
                    MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier) {

    // Gestionnaire de Focus, permet de relacher le focus en cas de besoin
    val focusManager = LocalFocusManager.current
    //focusManager.clearFocus()

    // Gestion du Snackbar
    val snackbarHostState = remember{ SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var isSnackBarButtonClick by remember{mutableStateOf(false)}

    // Gestion du dialogue
    var showDialog by remember{mutableStateOf(false)}

    // Variables pour le champ prénom
    var prenom by remember {mutableStateOf("")} // Le texte
    var isFirstNameError by remember{mutableStateOf(false)} // Si il y a une erreur
    val firstNameFocusRequester = remember { FocusRequester() } // Pour réclamer le focus en cas d'erreur


    var password by remember {mutableStateOf("")}
    var nom by remember{mutableStateOf("")}

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.Errors))
                }
            )
        },
        snackbarHost = {SnackbarHost(snackbarHostState)}
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(innerPadding).fillMaxSize().verticalScroll(rememberScrollState())
        ){
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                OutlinedTextField(
                    modifier = Modifier.padding(10.dp).fillMaxWidth().focusRequester(firstNameFocusRequester),
                    value = prenom,
                    onValueChange = { newValue ->
                        prenom = newValue
                        isFirstNameError = false // L'utilisateur tape -> On enlève le signal d'une erreur
                    },
                    isError = isFirstNameError, // Signale si le champ a une erreur
                    supportingText = { // Texte qui s'affiche sous le champ en cas d'erreur
                        if (isFirstNameError) {
                            Text("Illustre l'effet d'une erreur")
                        }
                    },
                    trailingIcon = { // On ajoute une petite icone à la fin du champs en cas d'erreur
                        if (isFirstNameError) {
                            Icon(
                                imageVector = Icons.Default.Error,
                                contentDescription = "Erreur",
                                tint = MaterialTheme.colorScheme.error
                            )
                        }
                    },
                    label = { Text(stringResource(R.string.EnterFirstName)) }
                )
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(Color.Gray)
            )

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                OutlinedTextField(
                    modifier = Modifier.padding(10.dp).fillMaxWidth(),
                    value = password,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    onValueChange = { newValue ->
                        password = newValue
                    },
                    label = { Text(stringResource(R.string.PutPassword)) }
                )
            }

            Column(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(Color.Gray)
            ){
                Text(text = "Pour afficher les erreurs, cliquez sur les boutons en bas")
                // Si je clique sur le bouton "En savoir plus" du snackbar
                if(isSnackBarButtonClick)
                {
                    Text(text = "J'ai voulu en savoir plus")
                }
            }

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                OutlinedTextField(
                    modifier = Modifier.padding(10.dp).fillMaxWidth(),
                    value = nom,
                    onValueChange = { newValue ->
                        nom = newValue
                    },
                    label = { Text(stringResource(R.string.EnterName)) }

                )
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(Color.Gray)
            )

            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                Button(
                    onClick = {
                        // je lance le snackbar et si je clique sur le bouton, je lance une action supplémentaire
                        // Imaginez si vous mettez Undo, ou OK...
                        scope.launch{
                            val result = snackbarHostState.showSnackbar(
                                message = "On affiche la SnackBar",
                                actionLabel = "En savoir plus"
                            )

                            if(result == SnackbarResult.ActionPerformed){
                                isSnackBarButtonClick = true
                            }
                        }
                    }
                ){
                    Text(text = "ENVOYER")
                }
                Button(
                    onClick = { // On met le booléan signalant une erreur à True et on réclame le focus sur le champs prénom
                        isFirstNameError = true
                        firstNameFocusRequester.requestFocus()
                    }
                ){
                    Text(text = "E2")
                }
                Button(
                    onClick = {
                        showDialog = true
                    }
                ){
                    Text(text = "E3")
                }
            }

            if(showDialog){
                AlertDialog(
                    onDismissRequest = {showDialog = false},
                    title = {Text("Plus de réseau !")},
                    text = {Text("Voulez-vous reessayer ?")},
                    confirmButton = {
                        TextButton(onClick = {
                            showDialog = false
                        }) {
                            Text("OK")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = {
                            showDialog = false
                            scope.launch{
                                val result = snackbarHostState.showSnackbar(
                                    message = "On retente une connection"
                                )
                            }
                        }) {
                            Text("Retry")
                        }
                    }
                )
            }
        }
    }
}

