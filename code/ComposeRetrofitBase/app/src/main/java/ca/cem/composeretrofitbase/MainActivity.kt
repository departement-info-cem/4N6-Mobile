package ca.cem.composeretrofitbase

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ca.cem.composeretrofitbase.api.RetrofitInstance
import ca.cem.composeretrofitbase.ui.theme.ComposeRetrofitBaseTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeRetrofitBaseTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    EcranReposGitHub(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun EcranReposGitHub(modifier: Modifier = Modifier) {
    var nomUtilisateur by remember { mutableStateOf("") }
    var donneesRepos by remember { mutableStateOf("") }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Visualiseur de Repos GitHub",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        OutlinedTextField(
            value = nomUtilisateur,
            onValueChange = { nomUtilisateur = it },
            label = { Text("Nom d'utilisateur GitHub") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                RetrofitInstance.api.listReposString(nomUtilisateur).enqueue(object : Callback<String> {
                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        if (response.isSuccessful) {
                            donneesRepos = response.body() ?: "Pas de données"
                        }
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        Log.e("EcranReposGitHub", "Erreur lors de la récupération des repos", t)
                    }
                })
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Récupérer les repos")
        }
        Spacer(modifier = Modifier.height(24.dp))
        if (donneesRepos.isNotEmpty()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(
                    text = donneesRepos,
                    modifier = Modifier
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EcranReposGitHubPreview() {
    ComposeRetrofitBaseTheme {
        EcranReposGitHub()
    }
}