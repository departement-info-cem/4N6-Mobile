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
    var lowLimit by remember { mutableStateOf("") }
    var highLimit by remember { mutableStateOf("") }
    var personnages by remember { mutableStateOf<List<Repo>>(emptyList())}

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Row() {
            OutlinedTextField(
                value = lowLimit,
                onValueChange = { lowLimit = it },
                label = { Text("Limite de puissance basse") },
                modifier = Modifier.fillMaxWidth().weight(1f),
                singleLine = true,
            )
            OutlinedTextField(
                value = highLimit,
                onValueChange = { highLimit = it },
                label = { Text("Limite de puissance haute") },
                modifier = Modifier.fillMaxWidth().weight(1f),
                singleLine = true,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                RetrofitInstance.api.listRepos(lowLimit.toDouble(), highLimit.toDouble()).enqueue(object : Callback<List<Repo>> {
                    override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                        if (response.isSuccessful) {
                            personnages = response.body() ?: emptyList()
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
        if (personnages.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                items(personnages) { perso ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row() {
                                Text(
                                    text = perso.nom,
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Text(
                                    text = "ID: ${perso.classe}",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                            Row(){
                                Text(
                                    text = perso.puissance.toString(),
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Text(
                                    text = perso.equipement.toString(),
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
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