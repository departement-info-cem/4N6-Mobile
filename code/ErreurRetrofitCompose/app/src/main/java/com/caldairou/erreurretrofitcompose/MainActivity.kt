package com.caldairou.erreurretrofitcompose

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.caldairou.erreurretrofitcompose.api.Repo
import com.caldairou.erreurretrofitcompose.api.RetrofitInstance
import com.caldairou.erreurretrofitcompose.ui.theme.ErreurRetrofitComposeTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ErreurRetrofitComposeTheme {
                EcranPrincipal()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EcranPrincipal() {

    var texte by remember{mutableStateOf("")}
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("retrofit erreurs") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).padding(5.dp).fillMaxSize()
        ){
            OutlinedTextField(
                value = texte,
                onValueChange = { texte = it },
                label = { Text("Assez long message") }
            )

            Button(
                onClick = {
                    val repo = Repo()
                    repo.nom = texte
                    Log.d("Bouton cliqué", "Entrée dans la fonction onClick : texte = $texte")

                    RetrofitInstance.api.erreurOuPas(repo).enqueue(object : Callback<String>{
                        override fun onResponse(call: Call<String>, response: Response<String>){
                            Log.d("Bouton cliqué", "Entrée dans la fonction onResponse : code = ${response.code()}")
                            if(response!!.isSuccessful()){
                                Log.d("Bouton cliqué", "Réponse réussie : ${response.body()}")
                            }
                            else{
                                try {
                                    val corpsErreur = response.errorBody()!!.string()
                                    Log.d("RETROFIT", "le code " + response.code())
                                    Log.d("RETROFIT", "le message " + response.message())
                                    Log.d("RETROFIT", "le corps " + corpsErreur)
                                    Log.d(
                                        "RETROFIT",
                                        "le corps encore " + response.errorBody()!!.string()
                                    )
                                    if (corpsErreur.contains("TropCourt")) {
                                        // TODO remplacer par un objet graphique mieux qu'un toast
                                        Toast.makeText(context, "Ce message est trop court", Toast.LENGTH_SHORT).show()
                                    }
                                } catch (e: IOException) {
                                    e.printStackTrace()
                                }
                            }
                        }

                        override fun onFailure(call: Call<String>, t: Throwable) {
                            Log.d("RETROFIT", "Erreur de connection : " + t.message)
                            Toast.makeText(context, "Erreur réseau : serveur inaccessible", Toast.LENGTH_LONG).show()

                        }

                    })
                }
            ){
                Text("ALLER AU RÉSEAU")
            }

            Text("Essaie avec un message en dessous ou dessus de 5 caractères")
        }
    }
}
