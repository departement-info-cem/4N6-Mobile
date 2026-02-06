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
import ca.cem.composeretrofitbase.api.RequeteInscription
import ca.cem.composeretrofitbase.api.RequeteConnexion
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
                    EcranKickMyB(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun EcranKickMyB(modifier: Modifier = Modifier) {
    var nomUtilisateur by remember { mutableStateOf("") }
    var motDePasse by remember { mutableStateOf("") }
    var reponseInscription by remember { mutableStateOf("") }
    var reponseConnexion by remember { mutableStateOf("") }
    var reponseAccueil by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "KickMyB - Inscription & Cookies",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        OutlinedTextField(
            value = nomUtilisateur,
            onValueChange = { nomUtilisateur = it },
            label = { Text("Nom d'utilisateur (email)") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = motDePasse,
            onValueChange = { motDePasse = it },
            label = { Text("Mot de passe") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Bouton Inscription
        Button(
            onClick = {
                Log.d("EcranKickMyB", "Entrée dans la fonction onClick inscription : utilisateur=$nomUtilisateur")
                val requete = RequeteInscription(nomUtilisateur, motDePasse)
                RetrofitInstance.api.inscription(requete).enqueue(object : Callback<String> {
                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        Log.d("EcranKickMyB", "Entrée dans la fonction onResponse inscription : code=${response.code()}")
                        if (response.isSuccessful) {
                            reponseInscription = response.body() ?: "Pas de données"
                        } else {
                            reponseInscription = "Erreur HTTP: ${response.code()}"
                        }
                        Log.d("EcranKickMyB", "Sortie de la fonction onResponse inscription")
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        Log.d("EcranKickMyB", "Entrée dans la fonction onFailure inscription : ${t.message}")
                        Log.e("EcranKickMyB", "Erreur inscription", t)
                        reponseInscription = "Erreur: ${t.message}"
                        Log.d("EcranKickMyB", "Sortie de la fonction onFailure inscription")
                    }
                })
                Log.d("EcranKickMyB", "Sortie de la fonction onClick inscription")
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Inscription (POST)")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Bouton Connexion
        Button(
            onClick = {
                Log.d("EcranKickMyB", "Entrée dans la fonction onClick connexion : utilisateur=$nomUtilisateur")
                val requete = RequeteConnexion(nomUtilisateur, motDePasse)
                RetrofitInstance.api.connexion(requete).enqueue(object : Callback<String> {
                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        Log.d("EcranKickMyB", "Entrée dans la fonction onResponse connexion : code=${response.code()}")
                        if (response.isSuccessful) {
                            reponseConnexion = response.body() ?: "Pas de données"
                        } else {
                            reponseConnexion = "Erreur HTTP: ${response.code()}"
                        }
                        Log.d("EcranKickMyB", "Sortie de la fonction onResponse connexion")
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        Log.d("EcranKickMyB", "Entrée dans la fonction onFailure connexion : ${t.message}")
                        Log.e("EcranKickMyB", "Erreur connexion", t)
                        reponseConnexion = "Erreur: ${t.message}"
                        Log.d("EcranKickMyB", "Sortie de la fonction onFailure connexion")
                    }
                })
                Log.d("EcranKickMyB", "Sortie de la fonction onClick connexion")
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Connexion (POST)")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Bouton Accueil
        Button(
            onClick = {
                Log.d("EcranKickMyB", "Entrée dans la fonction onClick accueil")
                RetrofitInstance.api.accueilTache().enqueue(object : Callback<String> {
                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        Log.d("EcranKickMyB", "Entrée dans la fonction onResponse accueil : code=${response.code()}")
                        if (response.isSuccessful) {
                            reponseAccueil = response.body() ?: "Pas de données"
                        } else {
                            reponseAccueil = "Erreur HTTP: ${response.code()}"
                        }
                        Log.d("EcranKickMyB", "Sortie de la fonction onResponse accueil")
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        Log.d("EcranKickMyB", "Entrée dans la fonction onFailure accueil : ${t.message}")
                        Log.e("EcranKickMyB", "Erreur accueil", t)
                        reponseAccueil = "Erreur: ${t.message}"
                        Log.d("EcranKickMyB", "Sortie de la fonction onFailure accueil")
                    }
                })
                Log.d("EcranKickMyB", "Sortie de la fonction onClick accueil")
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Accueil Tâches (GET avec cookie)")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Affichage des réponses
        if (reponseInscription.isNotEmpty()) {
            CarteReponse(titre = "Réponse Inscription:", contenu = reponseInscription)
        }

        if (reponseConnexion.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            CarteReponse(titre = "Réponse Connexion:", contenu = reponseConnexion)
        }

        if (reponseAccueil.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            CarteReponse(titre = "Réponse Accueil:", contenu = reponseAccueil)
        }
    }
}

@Composable
fun CarteReponse(titre: String, contenu: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 100.dp, max = 200.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = titre,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = contenu,
                modifier = Modifier.verticalScroll(rememberScrollState())
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EcranKickMyBPreview() {
    ComposeRetrofitBaseTheme {
        EcranKickMyB()
    }
}