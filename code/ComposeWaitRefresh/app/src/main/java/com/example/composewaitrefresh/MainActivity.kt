package com.example.composewaitrefresh

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composewaitrefresh.api.RetrofitInstance
import com.example.composewaitrefresh.api.Truc
import com.example.composewaitrefresh.ui.theme.ComposeWaitRefreshTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeWaitRefreshTheme {
                EcranPrincipal()
            }
        }
    }
}


@Composable
fun EcranPrincipal() {

    var estCache : Boolean by remember{mutableStateOf(true)}
    var isLoadingTexteCache : Boolean by remember{mutableStateOf(false)}
    var donneesRepos : Truc? by remember{mutableStateOf(null)}

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).fillMaxSize()
        ){
            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        estCache = false
                        RetrofitInstance.api.listReposString("benoit").enqueue(object : Callback<Truc> {
                            override fun onResponse(call: Call<Truc>, response: Response<Truc>) {
                                Log.d("EcranReposGitHub", "Entrée dans la fonction onResponse : code=${response.code()}")
                                if (response.isSuccessful) {
                                    donneesRepos = response.body()
                                }
                                Log.d("EcranReposGitHub", "Sortie de la fonction onResponse")
                            }

                            override fun onFailure(call: Call<Truc>, t: Throwable) {
                                Log.d("EcranReposGitHub", "Entrée dans la fonction onFailure : ${t.message}")
                                Log.e("EcranReposGitHub", "Erreur lors de la récupération des repos", t)
                                Log.d("EcranReposGitHub", "Sortie de la fonction onFailure")
                            }
                        })
                        Log.d("EcranReposGitHub", "Sortie de la fonction onClick")
                    }
                ){
                    Text("Charger le contenu")
                }
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        estCache = true
                    }
                ){
                    Text("Cacher le contenu")
                }
            }

            if(!estCache && donneesRepos != null){
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = "a : ${donneesRepos!!.a} ; b = ${donneesRepos!!.b} ; c = ${donneesRepos!!.c.toString()}"
                    )
                }
            }
        }
    }
}

