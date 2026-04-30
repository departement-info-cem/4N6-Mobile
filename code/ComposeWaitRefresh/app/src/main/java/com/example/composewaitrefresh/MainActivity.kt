package com.example.composewaitrefresh

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.composewaitrefresh.api.RetrofitInstance
import com.example.composewaitrefresh.api.Truc
import com.example.composewaitrefresh.ui.theme.ComposeWaitRefreshTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState

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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EcranPrincipal() {

    var estCache : Boolean by remember{mutableStateOf(true)}
    var isLoadingTexteCache : Boolean by remember{mutableStateOf(false)}
    var donneesRepos : List<Truc> by remember{mutableStateOf(emptyList())}

    var isAlternateLoadingTexteCache : Boolean by remember{mutableStateOf(false)}

    var isRefreshing by remember { mutableStateOf(false) }
    val pullRefreshState = rememberPullToRefreshState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {Text("Essayez aussi un swipe to refresh sur la liste une fois qu'elle est affichée")}
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
            if (isAlternateLoadingTexteCache) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(androidx.compose.ui.graphics.Color.Black.copy(alpha = 0.3f)),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            Column(
                modifier = Modifier.padding(innerPadding).fillMaxSize().padding(5.dp)
            ) {


                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        modifier = Modifier.weight(1f),
                        enabled = !isLoadingTexteCache,
                        onClick = {
                            estCache = false
                            isLoadingTexteCache = true
                            RetrofitInstance.api.listReposString()
                                .enqueue(object : Callback<List<Truc>> {
                                    override fun onResponse(
                                        call: Call<List<Truc>>,
                                        response: Response<List<Truc>>
                                    ) {
                                        isLoadingTexteCache = false
                                        Log.d(
                                            "EcranReposGitHub",
                                            "Entrée dans la fonction onResponse : code=${response.code()}"
                                        )
                                        if (response.isSuccessful) {
                                            donneesRepos = response.body() ?: emptyList()
                                        }
                                        Log.d(
                                            "EcranReposGitHub",
                                            "Sortie de la fonction onResponse"
                                        )
                                    }

                                    override fun onFailure(call: Call<List<Truc>>, t: Throwable) {
                                        isLoadingTexteCache = false
                                        Log.d(
                                            "EcranReposGitHub",
                                            "Entrée dans la fonction onFailure : ${t.message}"
                                        )
                                        Log.e(
                                            "EcranReposGitHub",
                                            "Erreur lors de la récupération des repos",
                                            t
                                        )
                                        Log.d("EcranReposGitHub", "Sortie de la fonction onFailure")
                                    }
                                })
                            Log.d("EcranReposGitHub", "Sortie de la fonction onClick")

                        }
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(text = "Charger le contenu")

                            if (isLoadingTexteCache) {
                                Spacer(modifier = Modifier.width(8.dp))

                                CircularProgressIndicator(
                                    modifier = Modifier.size(18.dp),
                                    strokeWidth = 2.dp,
                                    color = Color.Red
                                )
                            }
                        }

                    }
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = {
                            estCache = true
                            donneesRepos = emptyList()
                        }
                    ) {
                        Text("Cacher le contenu")
                    }
                }

                Button(
                    onClick = {
                        estCache = false
                        isAlternateLoadingTexteCache = true
                        RetrofitInstance.api.listReposString()
                            .enqueue(object : Callback<List<Truc>> {
                                override fun onResponse(
                                    call: Call<List<Truc>>,
                                    response: Response<List<Truc>>
                                ) {
                                    Log.d(
                                        "EcranReposGitHub",
                                        "Entrée dans la fonction onResponse : code=${response.code()}"
                                    )
                                    if (response.isSuccessful) {
                                        donneesRepos = response.body() ?: emptyList()
                                    }
                                    Log.d("EcranReposGitHub", "Sortie de la fonction onResponse")
                                    isAlternateLoadingTexteCache = false
                                }

                                override fun onFailure(call: Call<List<Truc>>, t: Throwable) {
                                    Log.d(
                                        "EcranReposGitHub",
                                        "Entrée dans la fonction onFailure : ${t.message}"
                                    )
                                    Log.e(
                                        "EcranReposGitHub",
                                        "Erreur lors de la récupération des repos",
                                        t
                                    )
                                    Log.d("EcranReposGitHub", "Sortie de la fonction onFailure")
                                    isAlternateLoadingTexteCache = false
                                }
                            })
                        Log.d("EcranReposGitHub", "Sortie de la fonction onClick")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !isAlternateLoadingTexteCache
                ) {
                    Text("Autre façon de montrer l'attente")
                }

                if (!estCache && donneesRepos.isNotEmpty()) {
                    PullToRefreshBox(
                        isRefreshing = isRefreshing,
                        onRefresh = {
                            isRefreshing = true

                            RetrofitInstance.api.listReposString()
                                .enqueue(object : Callback<List<Truc>> {
                                    override fun onResponse(
                                        call: Call<List<Truc>>,
                                        response: Response<List<Truc>>
                                    ) {
                                        donneesRepos = response.body() ?: emptyList()
                                        isRefreshing = false
                                    }

                                    override fun onFailure(call: Call<List<Truc>>, t: Throwable) {
                                        isRefreshing = false
                                    }
                                })
                        },
                        state = pullRefreshState,
                        modifier = Modifier
                            .fillMaxSize()
                    ) {

                        LazyColumn(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(donneesRepos) { truc ->
                                Text(
                                    modifier = Modifier.padding(16.dp),
                                    text = "a : ${truc.a} ; b = ${truc.b} ; c = ${truc.c}"
                                )
                            }
                        }


                    }
                }


            }
        }
    }
}
