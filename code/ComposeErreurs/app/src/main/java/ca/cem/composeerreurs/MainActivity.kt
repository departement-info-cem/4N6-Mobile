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
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val scrollState = rememberScrollState()

    var firstText by remember { mutableStateOf("") }
    var firstError by remember { mutableStateOf<String?>(null) }

    var nameText by remember { mutableStateOf("") }
    var nameError by remember { mutableStateOf<String?>(null) }

    var passwordText by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf<String?>(null) }

    val firstFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }

    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                scope.launch {
                    val result = snackbarHostState.showSnackbar(
                        message = context.getString(R.string.no_network),
                        actionLabel = context.getString(R.string.retry),
                        duration = SnackbarDuration.Long
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        Toast.makeText(context, "Retrying", Toast.LENGTH_SHORT).show()
                    }
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.Send,
                    contentDescription = "send"
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(scrollState)
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = firstText,
                onValueChange = {
                    firstText = it
                    if (firstError != null) firstError = null
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(firstFocusRequester),
                label = { Text(text = stringResource(id = R.string.enter_firstname)) },
                isError = firstError != null,
                singleLine = true
            )
            if (firstError != null) {
                Text(
                    text = firstError!!,
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
                value = passwordText,
                onValueChange = {
                    passwordText = it
                    if (passwordError != null) passwordError = null
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(passwordFocusRequester),
                label = { Text(text = "Password") },
                visualTransformation = PasswordVisualTransformation(),
                isError = passwordError != null,
                singleLine = true
            )
            if (passwordError != null) {
                Text(
                    text = passwordError!!,
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
                value = nameText,
                onValueChange = {
                    nameText = it
                    if (nameError != null) nameError = null
                },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = stringResource(id = R.string.type_your_name)) },
                isError = nameError != null,
                singleLine = true
            )
            if (nameError != null) {
                Text(
                    text = nameError!!,
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
                    // showMessageSurChamp equivalent: set error on password and focus + scroll
                    passwordError = context.getString(R.string.error_password)
                    scope.launch {
                        passwordFocusRequester.requestFocus()
                        // crude scroll to bottom area where password is likely located
                        scrollState.animateScrollTo(scrollState.maxValue)
                    }
                }) {
                    Text(text = stringResource(id = R.string.send))
                }
                Button(onClick = {
                    // showASnackBar equivalent: set error on first field and show snackbar with action
                    firstError = context.getString(R.string.error_example)
                    scope.launch {
                        val result = snackbarHostState.showSnackbar(
                            message = context.getString(R.string.snack_message),
                            actionLabel = context.getString(R.string.go_there),
                            duration = SnackbarDuration.Long
                        )
                        if (result == SnackbarResult.ActionPerformed) {
                            firstFocusRequester.requestFocus()
                            scope.launch { scrollState.animateScrollTo(0) }
                        }
                    }
                }) {
                    Text(text = "E 2")
                }
                Button(onClick = {
                    // showADialog equivalent
                    showDialog = true
                }) {
                    Text(text = "E 3")
                }
            }

            Spacer(modifier = Modifier.height(40.dp))
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(text = stringResource(id = R.string.no_network)) },
                confirmButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text(text = stringResource(id = R.string.ok))
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        Toast.makeText(context, "Retrying", Toast.LENGTH_SHORT).show()
                        showDialog = false
                    }) {
                        Text(text = stringResource(id = R.string.retry))
                    }
                },
                properties = DialogProperties()
            )
        }
    }
}