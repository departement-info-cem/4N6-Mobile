package ca.cem.composeretrofitbase

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ca.cem.composeretrofitbase.api.Repo
import ca.cem.composeretrofitbase.api.RetrofitInstance
import ca.cem.composeretrofitbase.ui.theme.ComposeRetrofitObjetsTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeRetrofitObjetsTheme {
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
    var repos by remember { mutableStateOf<List<Repo>>(emptyList()) }
    LaunchedEffect(Unit) {
        RetrofitInstance.api.listRepos("jorisdeguet").enqueue(object : Callback<List<Repo>> {
            override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                if (response.isSuccessful) {
                    repos = response.body() ?: emptyList()
                }
            }

            override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
                Log.e("EcranReposGitHub", "Erreur lors de la récupération des repos", t)
            }
        })
    }
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
                RetrofitInstance.api.listRepos(nomUtilisateur).enqueue(object : Callback<List<Repo>> {
                    override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                        if (response.isSuccessful) {
                            repos = response.body() ?: emptyList()
                        }
                    }

                    override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
                        Log.e("EcranReposGitHub", "Erreur lors de la récupération des repos", t)
                    }
                })
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Récupérer les repos")
        }
        Spacer(modifier = Modifier.height(24.dp))
        if (repos.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                items(repos) { repo ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = repo.name,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = "ID: ${repo.id}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EcranReposGitHubPreview() {
    ComposeRetrofitObjetsTheme {
        EcranReposGitHub()
    }
}